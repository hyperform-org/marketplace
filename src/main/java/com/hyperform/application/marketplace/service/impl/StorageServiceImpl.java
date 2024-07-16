package com.hyperform.application.marketplace.service.impl;

import com.hyperform.application.marketplace.configuration.StorageProperties;
import com.hyperform.application.marketplace.exceptions.StorageException;
import com.hyperform.application.marketplace.exceptions.StorageFileNotFoundException;
import com.hyperform.application.marketplace.service.StorageService;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageServiceImpl implements StorageService {

  private final Path rootLocation;
  private final Path imageLocation;

  @Autowired
  public StorageServiceImpl(StorageProperties properties) {
    if (properties.getLocation().trim().length() == 0) {
      throw new StorageException("File upload location can not be Empty.");
    }

    this.rootLocation = Paths.get(properties.getLocation());
    this.imageLocation = Paths.get(properties.getImages());
  }

  @Override
  public void store(MultipartFile file) {
    try {
      if (file.isEmpty()) {
        throw new StorageException("Failed to store empty file.");
      }
      Path destinationFile = this.rootLocation.resolve(Paths.get(file.getOriginalFilename())).normalize().toAbsolutePath();
      if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
        // This is a security check
        throw new StorageException("Cannot store file outside current directory.");
      }
      try (InputStream inputStream = file.getInputStream()) {
        Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
      }
    } catch (IOException e) {
      throw new StorageException("Failed to store file.", e);
    }
  }

  @Override
  public void store(MultipartFile file, String filename) {
    try {
      if (file.isEmpty()) {
        throw new StorageException("Failed to store empty file " + filename);
      }
      filename = filename.toLowerCase();
      Path destinationFile = this.rootLocation.resolve(Paths.get(filename)).normalize().toAbsolutePath();
      if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
        throw new StorageException("Cannot store file outside current directory.");
      }
      Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      throw new StorageException("Failed to store file " + filename, e);
    }
  }

  @Override
  public void store(String imageData, String fileName) {
    if (StringUtils.isEmpty(imageData) || StringUtils.isEmpty(fileName)) {
      throw new StorageException("Failed to store empty file.");
    }
    byte[] decodedBytes = Base64.getDecoder().decode(imageData);
    Path destinationFile = this.rootLocation.resolve(Paths.get(fileName)).normalize().toAbsolutePath();
    if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
      throw new StorageException("Cannot store file outside current directory.");
    }

    try {
      Path parentDir = destinationFile.getParent();
      if (!Files.exists(parentDir)) {
        Files.createDirectories(parentDir);
      }

      Files.write(destinationFile, decodedBytes);
    } catch (IOException e) {
      throw new StorageException(e.getMessage());
    }
  }

  @Override
  public void storeImage(String imageData, String fileName) {
    if (StringUtils.isEmpty(imageData) || StringUtils.isEmpty(fileName)) {
      throw new StorageException("Failed to store empty file.");
    }
    byte[] decodedBytes = Base64.getDecoder().decode(imageData);
    Path destinationFile = this.imageLocation.resolve(Paths.get(fileName)).normalize().toAbsolutePath();
    if (!destinationFile.getParent().equals(this.imageLocation.toAbsolutePath())) {
      throw new StorageException("Cannot store file outside current directory.");
    }

    try {
      Path parentDir = destinationFile.getParent();
      if (!Files.exists(parentDir)) {
        Files.createDirectories(parentDir);
      }
      Files.write(destinationFile, decodedBytes);
    } catch (IOException e) {
      throw new StorageException(e.getMessage());
    }
  }

  @Override
  public Stream<Path> loadAll() {
    try {
      return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation)).map(this.rootLocation::relativize);
    } catch (IOException e) {
      throw new StorageException("Failed to read stored files", e);
    }
  }

  @Override
  public Path load(String filename) {
    return rootLocation.resolve(filename);
  }

  @Override
  public Resource loadAsResource(String filename) {
    try {
      Path file = load(filename);
      Resource resource = new UrlResource(file.toUri());
      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new StorageFileNotFoundException("Could not read file: " + filename);
      }
    } catch (MalformedURLException e) {
      throw new StorageFileNotFoundException("Could not read file: " + filename, e);
    }
  }

  @Override
  public Resource loadAsImageResource(String filename) {
    try {
      Path file = this.imageLocation.resolve(Paths.get(filename)).normalize().toAbsolutePath();
      Resource resource = new UrlResource(file.toUri());
      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new StorageFileNotFoundException("Could not read file: " + filename);
      }
    } catch (MalformedURLException e) {
      throw new StorageFileNotFoundException("Could not read file: " + filename, e);
    }
  }

  @Override
  public void deleteAll() {
    FileSystemUtils.deleteRecursively(rootLocation.toFile());
  }

  @Override
  public void init() {
    try {
      Files.createDirectories(rootLocation);
    } catch (IOException e) {
      throw new StorageException("Could not initialize storage", e);
    }
  }
}
