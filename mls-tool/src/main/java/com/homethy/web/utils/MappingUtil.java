package com.homethy.web.utils;

public class MappingUtil {

  public static boolean isAgent(String str) {
    String[] agents = PropertiesUtil.AgentStrs().split(",");
    for (String agent : agents) {
      if (agent.equals(str.toLowerCase())) {
        return true;
      }
    }
    return false;
  }

  public static boolean isOffice(String str) {
    String[] offices = PropertiesUtil.OfficeStrs().split(",");
    for (String office : offices) {
      if (office.equals(str.toLowerCase())) {
        return true;
      }
    }
    return false;
  }

  public static boolean isProperty(String str) {
    String[] ps = PropertiesUtil.PropertyStrs().split(",");
    for (String p : ps) {
      if (p.equals(str.toLowerCase())) {
        return true;
      }
    }
    return false;
  }

  public static boolean isRequiredRes(String str) {
    if (isAgent(str) || isOffice(str) || isProperty(str)) {
      return true;
    } else {
      return false;
    }
  }
}
