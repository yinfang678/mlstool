package com.homethy.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.homethy.web.annotation.DataSourceListing;
import com.homethy.web.domain.RetsM;

@DataSourceListing
public interface RetsMDAO {

  @Select("" + "SELECT * FROM rets_md WHERE parser_data is null;")
  List<RetsM> getRetsMAndParserDataNotNull();

  @Select("" + "SELECT mls_org_id,create_time,update_time,meta_data FROM rets_md;")
  List<RetsM> getRetsMList();

  @Select("SELECT * FROM rets_md WHERE mls_org_id = #{mlsId}")
  RetsM getRetsMByMlsId(@Param("mlsId") int mlsId);

  @Update("UPDATE rets_md SET parser_data=#{parserData} WHERE mls_org_id=#{mlsId}")
  void updateParserDataByMlsId(@Param("parserData") String parserData,
      @Param("gridData") String gridData, @Param("mlsId") int mlsId);

  @Insert("INSERT INTO rets_md SET mls_org_id=#{mlsOrgId}")
  void insert(RetsM rm);
}
