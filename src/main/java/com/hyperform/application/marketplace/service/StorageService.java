package com.hyperform.application.marketplace.service;

import java.nio.file.Path;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
  void init();

  void store(MultipartFile file);
  void store(MultipartFile file, String filename);

  public void store(String imageData, String fileName);

  public void storeImage(String imageData, String fileName);

  Stream<Path> loadAll();

  Path load(String filename);

  Resource loadAsResource(String filename);
  Resource loadAsImageResource(String filename);

  void deleteAll();
}
