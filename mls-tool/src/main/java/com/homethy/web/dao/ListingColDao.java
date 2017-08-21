package com.homethy.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.homethy.web.annotation.DataSourceListing;
import com.homethy.web.domain.Column;

@DataSourceListing
public interface ListingColDao {

  @Select("" + " SELECT COLUMN_NAME,COLUMN_COMMENT,DATA_TYPE FROM information_schema.COLUMNS "
      + " WHERE table_name = 'listing_info_full' AND TABLE_SCHEMA = 'listing';; ")
  List<Column> getListingCols();
}
