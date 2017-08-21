package com.homethy.web.utils;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonUtil {

  private static ObjectMapper mapper = new ObjectMapper();

  public static JsonNode toJsonNode(String json) {
    try {
      return mapper.readTree(json);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static ObjectNode getObjectNode() {
    return mapper.createObjectNode();
  }

}
