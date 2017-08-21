package com.homethy.web.domain;

public class Column {

  private String columnName = "";
  private String columnComment = "";
  private String dataType = "";
  private String mlsCols = "";
  private String js = "";

  public String getColumnName() {
    return columnName;
  }

  public void setColumnName(String columnName) {
    this.columnName = columnName;
  }

  public String getColumnComment() {
    return columnComment;
  }

  public void setColumnComment(String columnComment) {
    this.columnComment = columnComment;
  }

  public String getDataType() {
    return dataType;
  }

  public void setDataType(String dataType) {
    this.dataType = dataType;
  }

  public String getMlsCols() {
    return mlsCols;
  }

  public void setMlsCols(String mlsCols) {
    this.mlsCols = mlsCols;
  }

  public String getJs() {
    return js;
  }

  public void setJs(String js) {
    this.js = js;
  }

  @Override
  public String toString() {
    return "{\"columnName\":\"" + this.columnName + "\",\"columnComment\":\"" + this.columnComment
        + "\",\"dataType\":\"" + this.dataType + "\",\"mlsCols\":\"" + this.mlsCols + "\",\"js\":\""
        + this.js + "\"}";
  }

}
