package com.hyperform.application.marketplace.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomFile {

  private String file;
  private String fileName;
  private Integer fileSize;
  private String fileType;
}
