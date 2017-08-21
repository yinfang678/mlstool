package com.homethy.web.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.homethy.web.dao.RetsMDAO;
import com.homethy.web.domain.Column;
import com.homethy.web.domain.RetsM;
import com.homethy.web.service.IListingDataBeanSerivce;
import com.homethy.web.utils.MappingUtil;

@Service
public class ListingDataBeanSerivceImpl implements IListingDataBeanSerivce {

  private final static String DATA = "data";
  private ObjectMapper mapper = new ObjectMapper();

  @Autowired
  private RetsMDAO retsMDAO;


  @Override
  public String getListingDataBean() {
    String databean = null;
    InputStream input = this.getClass().getClassLoader().getResourceAsStream("databean.json");
    StringBuffer out = new StringBuffer();
    byte[] b = new byte[4096];
    try {
      for (int n; (n = input.read(b)) != -1;) {
        out.append(new String(b, 0, n));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    databean = out.toString();
    return databean;
  }

  @Override
  public String getGridListingDataBean(int mlsId) {
    String databean = null;
    RetsM rmd = retsMDAO.getRetsMByMlsId(mlsId);
    if (rmd == null) {
      return "";
    }
    // if (StringUtils.isNoneEmpty(rmd.getGridData())) {
    // return rmd.getGridData();
    // }
    String result = "";
    databean = getListingDataBean();

    String meta = rmd.getMetaData();
    String parse = rmd.getParserData();
    // if (StringUtils.isNotEmpty(parse)) {
    // // parse = parse.replaceAll("\\\\", "\\\\\\\\");
    // parse = parse.replaceAll("\\\\\\\"", "\\\\\\\\\\\\\\\"");
    // }
    // System.out.println(parse);
    if (StringUtils.isEmpty(databean) || StringUtils.isEmpty(meta)) {
      return result;
    }
    try {
      JsonNode dataNode = mapper.readTree(databean);
      JsonNode parseNode = StringUtils.isEmpty(parse) ? null : mapper.readTree(parse);
      JsonNode commNode = dataNode.get("DataBean");
      JsonNode listingNode = dataNode.get("listDataBean");
      JsonNode soldNode = dataNode.get("soldListingDataBean");
      JsonNode agentNode = dataNode.get("agentBean");
      JsonNode officeNode = dataNode.get("officeBean");
      JsonNode retmdNode = mapper.readTree(meta);
      Iterator<Entry<String, JsonNode>> retmdFields = retmdNode.fields();
      while (retmdFields.hasNext()) {
        Entry<String, JsonNode> property = retmdFields.next();
        ObjectNode propertyNode = (ObjectNode) property.getValue();
        if (MappingUtil.isProperty(property.getKey().toLowerCase())) {
          ObjectNode cfNode = mapper.createObjectNode();
          cfNode.set("data", mapper.createObjectNode());
          propertyNode.set("COMMONFIELD", cfNode);
        }
        JsonNode ppNode = null == parseNode ? null : parseNode.get(property.getKey());
        Iterator<Entry<String, JsonNode>> propertyFields = propertyNode.fields();

        while (propertyFields.hasNext()) {
          Entry<String, JsonNode> clazz = propertyFields.next();
          JsonNode pcNode = null == ppNode ? null : ppNode.get(clazz.getKey());
          if (MappingUtil.isAgent(clazz.getKey().toLowerCase())) {
            setData(clazz.getValue(), agentNode, pcNode);
          } else if (MappingUtil.isOffice(clazz.getKey().toLowerCase())) {
            setData(clazz.getValue(), officeNode, pcNode);
          } else {
            setData(clazz.getValue(), joinNodes(commNode, listingNode, soldNode), pcNode);
          }
        }
      }
      result = retmdNode.toString().replaceAll("\\\\t", "");
      result = handleSpecStr(result);
      System.out.println(result);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return result;
  }


  @Override
  public String gridJsonToParseJson(String gridJson) {
    ObjectNode parseNode = mapper.createObjectNode();
    try {
      JsonNode gridNode = mapper.readTree(gridJson);
      Iterator<Entry<String, JsonNode>> fields = gridNode.fields();
      while (fields.hasNext()) {
        Entry<String, JsonNode> field = fields.next();
        JsonNode propertyNode = field.getValue();
        ObjectNode pNode = mapper.createObjectNode();
        parseNode.set(field.getKey(), pNode);
        Iterator<Entry<String, JsonNode>> classFields = propertyNode.fields();
        while (classFields.hasNext()) {
          Entry<String, JsonNode> classField = classFields.next();
          JsonNode classNode = classField.getValue();
          ObjectNode cNode = mapper.createObjectNode();
          pNode.set(classField.getKey(), cNode);
          Iterator<Entry<String, JsonNode>> dataFields = classNode.fields();
          while (dataFields.hasNext()) {
            Entry<String, JsonNode> dataField = dataFields.next();
            if (DATA.equals(dataField.getKey())) {
              StringBuilder sb = new StringBuilder();
              sb.append("{");
              ArrayNode array = (ArrayNode) dataField.getValue();
              for (JsonNode node : array) {
                String colname = node.get("columnName").toString();
                String mlsCols = node.get("mlsCols").toString();
                String js = node.get("js").toString();
                String nStr = null;
                String[] cols = mlsCols.replaceAll("\"", "").split(",");
                if (StringUtils.isEmpty(js) || "\"\"".equals(js)) {
                  nStr = colname + ":" + mlsCols;
                } else if (cols.length > 1) {
                  nStr = colname + ":{\"function\":" + js + ",\"parameter\":" + stringToArray(cols)
                      + "}";
                } else {
                  nStr = colname + ":{\"function\":" + js + ",\"parameter\":" + mlsCols + "}";
                }
                sb.append(nStr);
                sb.append(",");
              }
              sb.deleteCharAt(sb.length() - 1);
              sb.append("}");
              JsonNode dNode = mapper.readTree(sb.toString());
              cNode.set(DATA, dNode);
            }
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    // System.out.println(parseNode.toString());
    return parseNode.toString();
  }

  @Override
  public String parseJsonToGridJson(String parseJson) {
    // TODO Auto-generated method stub
    return null;
  }

  private String stringToArray(String[] cols) {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (String col : cols) {
      sb.append("\"");
      sb.append(col);
      sb.append("\",");
    }
    sb.deleteCharAt(sb.length() - 1);
    sb.append("]");
    return sb.toString();
  }

  private void setData(JsonNode classNode, JsonNode dataNode, JsonNode parseNode) {
    JsonNode pNode = null == parseNode ? null : parseNode.get("data");
    Iterator<Entry<String, JsonNode>> classFields = classNode.fields();
    while (classFields.hasNext()) {
      Entry<String, JsonNode> field = classFields.next();
      if (field.getKey().toLowerCase().equals(DATA)) {
        field.setValue(toArrayNode(dataNode, pNode));
      }
    }
  }

  private JsonNode joinNodes(JsonNode... nodes) {
    JsonNode dataNode = null;
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    for (JsonNode node : nodes) {
      String nodeStr = node.toString();
      sb.append(nodeStr.substring(1, nodeStr.length() - 1));
      sb.append(",");
    }
    sb.deleteCharAt(sb.length() - 1);
    sb.append("}");
    try {
      dataNode = mapper.readTree(sb.toString());
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return dataNode;
  }

  private ArrayNode toArrayNode(JsonNode node, JsonNode pNode) {
    ArrayNode array = mapper.createArrayNode();
    Iterator<Entry<String, JsonNode>> fields = node.fields();
    while (fields.hasNext()) {
      Entry<String, JsonNode> field = fields.next();
      String colname = field.getKey();
      String desc = field.getValue().toString().replaceAll("\"", "");
      Column col = new Column();
      col.setColumnName(colname);
      col.setColumnComment(desc);
      if (null != pNode) {
        JsonNode valueNode = pNode.get(colname);
        if (valueNode != null) {
          JsonNode func = valueNode.get("function");
          if (null == func) {
            String mCols = valueNode.toString();
            col.setMlsCols(filter(mCols));
          } else {
            col.setJs(filter(func.toString()));
            col.setMlsCols(filter(valueNode.get("parameter").toString()).replaceAll("\"", ""));
          }
        }
      }
      try {
        JsonNode colNode = mapper.readTree(col.toString());
        array.add(colNode);
      } catch (JsonProcessingException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return array;
  }

  private String filter(String str) {
    return str.substring(1, str.length() - 1);
  }

  private String handleSpecStr(String str) {
    str = str.replaceAll("\\'", "&039&");
    str = str.replaceAll("\\\\\\\"", "&034&");
    str = str.replaceAll("\\\\", "\\\\\\\\");
    str = str.replaceAll("&039&", "\\\\'");
    str = str.replaceAll("&034&", "\\\\\\\\\\\\\\\"");
    return str;
  }

}
