package com.homethy.web.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

  private static Properties pps = new Properties();

  public static void load(String filename) {
    try {
      pps.clear();
      pps.load(new FileInputStream(
          PropertiesUtil.class.getClassLoader().getResource(filename).getFile()));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static String AgentStrs() {
    load("mlsClassMapping.properties");
    return String.valueOf(pps.get("Agent"));
  }

  public static String OfficeStrs() {
    load("mlsClassMapping.properties");
    return String.valueOf(pps.get("Office"));
  }

  public static String PropertyStrs() {
    load("mlsClassMapping.properties");
    return String.valueOf(pps.get("Property"));
  }

}
