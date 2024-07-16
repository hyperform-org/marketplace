package com.hyperform.application.marketplace.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

  /**
   * Folder location for storing files
   */
  private String location = "files-storage";

  public String getImages() {
    return getLocation() + "/images";
  }

  public String getFiles() {
    return getLocation() + "/files";
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }
}
