package com.homethy.web.service;

import java.io.IOException;
import java.util.List;

import com.homethy.web.domain.RetsM;

public interface IRetsMService {

  String getRetsMByMlsId(int mlsId) throws IOException;

  void updateParserData(String parserData, int mlsId);

  List<RetsM> getRetsMList();

  void insert(RetsM rm);
}
