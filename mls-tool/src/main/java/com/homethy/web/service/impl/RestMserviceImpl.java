package com.homethy.web.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.homethy.web.dao.RetsMDAO;
import com.homethy.web.domain.RetsM;
import com.homethy.web.service.IListingDataBeanSerivce;
import com.homethy.web.service.IRetsMService;
import com.homethy.web.utils.MappingUtil;

@Service
public class RestMserviceImpl implements IRetsMService {

  @Autowired
  RetsMDAO retsMDAO;

  @Autowired
  private IListingDataBeanSerivce listingDataBeanSerivce;

  private ObjectMapper mapper = new ObjectMapper();

  private Set<String> commKeys = new HashSet<>();

  @Override
  public String getRetsMByMlsId(int mlsId) throws IOException {
    RetsM retsM = retsMDAO.getRetsMByMlsId(mlsId);
    String mlsMeta = retsM.getMetaData();
    List<String> filterRes = new ArrayList<>();
    ObjectNode mlsNode = (ObjectNode) mapper.readTree(mlsMeta);
    Iterator<Map.Entry<String, JsonNode>> resourceNodeIter = mlsNode.fields();
    while (resourceNodeIter.hasNext()) {
      Map.Entry<String, JsonNode> resourceNode = resourceNodeIter.next();
      if (!MappingUtil.isRequiredRes(resourceNode.getKey())) {
        filterRes.add(resourceNode.getKey());
      }
    }
    for (String key : filterRes) {
      mlsNode.remove(key);
    }
    resourceNodeIter = mlsNode.fields();
    while (resourceNodeIter.hasNext()) {
      Map.Entry<String, JsonNode> resourceNode = resourceNodeIter.next();
      ObjectNode propertyNode = (ObjectNode) resourceNode.getValue();
      if (MappingUtil.isProperty(resourceNode.getKey().toLowerCase())) {
        propertyNode.set("COMMONFIELD", getCommonFieldNode(mlsNode));
      }
      Iterator<Map.Entry<String, JsonNode>> classNodeIter = propertyNode.fields();
      while (classNodeIter.hasNext()) {
        Map.Entry<String, JsonNode> classNode = classNodeIter.next();
        if (null == classNode.getValue().get("data")) {
          continue;
        }
        Iterator<Map.Entry<String, JsonNode>> dataNodeIter =
            classNode.getValue().get("data").fields();
        // 构造一个表格的对象节点
        ObjectNode root = mapper.createObjectNode();
        root.putArray("dataDisplay");
        while (dataNodeIter.hasNext()) {
          Map.Entry<String, JsonNode> dataNode = dataNodeIter.next();

          // 表格的每一行
          ObjectNode node = mapper.createObjectNode();
          node.put("colname", dataNode.getKey());
          node.put("name", dataNode.getValue().get(0).textValue());
          node.put("stdname", dataNode.getValue().get(1).textValue());
          node.put("type", dataNode.getValue().get(2).textValue());
          node.put("sample", "");
          if (commKeys.contains(dataNode.getKey())) {
            node.put("comm", "1");
          } else {
            node.put("comm", "0");
          }
          ((ArrayNode) root.get("dataDisplay")).add(node);
        }
        classNode.setValue(root);
      }
    }
    return mlsNode.toString().replace("'", "");
  }

  @Override
  public void updateParserData(String parserData, int mlsId) {
    String gridData = null;
    try {
      gridData = new String(parserData.getBytes("utf-8"), "utf-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    parserData = listingDataBeanSerivce.gridJsonToParseJson(parserData);
    retsMDAO.updateParserDataByMlsId(parserData, gridData, mlsId);
  }

  private ObjectNode getCommonFieldNode(JsonNode mlsNode) {
    ObjectNode result = mapper.createObjectNode();
    Map<String, Integer> countMap = new HashMap<>();
    Map<String, JsonNode> nodeMap = new HashMap<>();
    Iterator<Map.Entry<String, JsonNode>> reses = mlsNode.fields();
    while (reses.hasNext()) {
      Map.Entry<String, JsonNode> res = reses.next();
      if (MappingUtil.isProperty(res.getKey())) {
        JsonNode proNode = res.getValue();
        Iterator<Map.Entry<String, JsonNode>> clazzes = proNode.fields();
        while (clazzes.hasNext()) {
          Map.Entry<String, JsonNode> claszz = clazzes.next();
          if (null != claszz.getValue().get("data")) {
            Iterator<Map.Entry<String, JsonNode>> datas = claszz.getValue().get("data").fields();
            while (datas.hasNext()) {
              Map.Entry<String, JsonNode> data = datas.next();
              JsonNode dataNode = data.getValue();
              String dataKey = data.getKey();
              if (null == countMap.get(dataKey)) {
                countMap.put(dataKey, 1);
                nodeMap.put(dataKey, dataNode);
              } else {
                int count = countMap.get(dataKey);
                countMap.put(dataKey, count + 1);
              }
            }
          }
        }
      }
    }
    for (Entry<String, Integer> count : countMap.entrySet()) {
      if (count.getValue() < 2) {
        nodeMap.remove(count.getKey());
      } else {
        commKeys.add(count.getKey());
      }
    }
    ObjectNode rDataNode = mapper.createObjectNode();
    rDataNode.setAll(nodeMap);
    result.set("data", rDataNode);
    return result;
  }

  @Override
  public List<RetsM> getRetsMList() {
    List<RetsM> result = retsMDAO.getRetsMList();
    for (RetsM rm : result) {
      rm.setMetaData(null == rm.getMetaData() ? null : "NOT NULL");
    }
    return result;
  }

  @Override
  public void insert(RetsM rm) {
    retsMDAO.insert(rm);
  }

  private String handleSpecStr(String str) {
    if (null == str) {
      return null;
    }
    str = str.replaceAll("\\\\t", "");
    str = str.replaceAll("\\'", "&039&");
    str = str.replaceAll("\\\\\\\"", "&034&");
    str = str.replaceAll("\\\\", "\\\\\\\\");
    str = str.replaceAll("&039&", "\\\\'");
    str = str.replaceAll("&034&", "\\\\\\\\\\\\\\\"");
    return str;
  }

  @Override
  public RetsM get(int mlsId) {
    return retsMDAO.getRetsMByMlsId(mlsId);
  }
}
