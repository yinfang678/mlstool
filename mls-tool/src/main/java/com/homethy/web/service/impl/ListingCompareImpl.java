package com.homethy.web.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homethy.web.dao.ListingCompareDao;
import com.homethy.web.domain.CompareDataBean;
import com.homethy.web.service.ListingCompareService;

@Service
public class ListingCompareImpl implements ListingCompareService{
	
	@Autowired
	private ListingCompareDao compareDao;

	public List<CompareDataBean> getCompareList(@Param("mlsId") int mlsId){System.out.println(mlsId);
		return compareDao.getCompareList(mlsId);
	}

	
}
