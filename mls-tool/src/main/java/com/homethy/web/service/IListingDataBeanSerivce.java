package com.homethy.web.service;

public interface IListingDataBeanSerivce {

  public String getListingDataBean();

  public String getGridListingDataBean(int mlsId);

  public String gridJsonToParseJson(String gridJson);

  public String parseJsonToGridJson(String parseJson);
}
