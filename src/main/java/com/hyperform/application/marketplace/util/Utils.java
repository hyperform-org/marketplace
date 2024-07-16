package com.hyperform.application.marketplace.util;

//import com.hyperform.application.marketplace.controller.WorkspaceController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;

public class Utils {

  private static final Logger logger = LoggerFactory.getLogger(Utils.class);

  private static final String ISO_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

  public static Sort byOrder(String[] sort) {
    Set<String> allowedSortFields = Set.of("createdDate", "updatedDate", "title", "description"); // Add other valid fields as needed
    if (sort == null || sort.length == 0) {
      return Sort.unsorted(); // Return unsorted if no sort parameters provided
    }
    return Arrays.stream(sort)
      .filter(s -> s.contains(":")) // Ensure the string contains a colon
      .map(spec -> {
        String[] args = spec.split(":");
        if (args.length == 2 && allowedSortFields.contains(args[0].trim())) {
          try {
            return new Sort.Order(Sort.Direction.fromString(args[1].trim()), args[0].trim());
          } catch (IllegalArgumentException e) {
            logger.error("Invalid sort direction: " + args[1], e);
          }
        }
        return null;
      })
      .filter(Objects::nonNull)
      .collect(Collectors.collectingAndThen(Collectors.toList(), Sort::by));
  }

  public static Date parseDate(String dateString) {
    if (dateString == null || dateString.isEmpty()) {
      return null;
    }

    SimpleDateFormat isoFormat = new SimpleDateFormat(ISO_DATE_FORMAT);
    isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    try {
      return isoFormat.parse(dateString);
    } catch (ParseException e) {
      logger.error("Invalid date format: " + dateString, e);
      throw new RuntimeException("Invalid date format: " + dateString);
    }
  }

  public static String toISODateString(Date date) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
    return sdf.format(date);
  }

  public static <T> T getOrDefault(T value, T defaultValue) {
    return value == null ? defaultValue : value;
  }

  public static String getFileExtension(String fileName) {
    String extension = "";
    int lastIndex = fileName.lastIndexOf('.');
    if (lastIndex > 0) {
      extension = fileName.substring(lastIndex + 1);
    }

    return extension;
  }
}
