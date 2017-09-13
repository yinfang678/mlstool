package com.homethy.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.homethy.web.annotation.DataSourceListing;
import com.homethy.web.domain.CompareDataBean;

@DataSourceListing
public interface ListingCompareDao {

	String TABLE_NAME = "listing_compare_test";
	
	/**
	 * 查询多条数据
	 * @return
	 */
	@Select(" select * from "
			 + TABLE_NAME
			 + " where mls_org_id=#{mlsId}")
	List<CompareDataBean> getCompareList(@Param("mlsId") int mlsId) ;

}
