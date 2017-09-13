package com.homethy.web.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.homethy.web.domain.CompareDataBean;

public interface ListingCompareService {

	List<CompareDataBean> getCompareList(@Param("mlsId") int mlsId) ;

}
