package com.homethy.web.domain;


import java.sql.Date;

public class RetsM {

  private int id;
  private int mlsOrgId;
  private String metaData;
  private String parserData;
  private String gridData;
  private Date createTime;
  private Date updateTime;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getMlsOrgId() {
    return mlsOrgId;
  }

  public void setMlsOrgId(int mlsOrgId) {
    this.mlsOrgId = mlsOrgId;
  }

  public String getMetaData() {
    return metaData;
  }

  public void setMetaData(String metaData) {
    this.metaData = metaData;
  }

  public String getParserData() {
    return parserData;
  }

  public void setParserData(String parserData) {
    this.parserData = parserData;
  }

  public String getGridData() {
    return gridData;
  }

  public void setGridData(String gridData) {
    this.gridData = gridData;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  @Override
  public String toString() {
    return "{\"mlsOrgId\":\"" + this.mlsOrgId + "\",\"createTime\":\"" + this.createTime
        + "\",\"updateTime\":\"" + this.updateTime + "\",\"metaData\":\"" + this.metaData + "\"}";
  }
}
