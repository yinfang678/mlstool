package com.homethy.web.service.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homethy.web.dao.ListingDataDAO;
import com.homethy.web.domain.ListingDataBean;
import com.homethy.web.service.ListingDataService;

@Service
public class ListingDataImpl implements ListingDataService{

	@Autowired
	private ListingDataDAO listingDataDAO;
	
	@Override
	public Set<String> getListingIds(int mlsOrgId) {
		return listingDataDAO.getListingIds(mlsOrgId);
	}

	@Override
	public ListingDataBean selectListingInfo(long mlsOrgId, String mlsListingId) {
		return listingDataDAO.getListingData(mlsOrgId, mlsListingId);
	}

	@Override
	public int insertListingInfo(ListingDataBean bean) {
		bean.formatInfo();
		return listingDataDAO.insertListingInfo(bean);
	}

	@Override
	public void updateListingInfo(ListingDataBean bean) {
		bean.formatInfo();
		listingDataDAO.updateListingInfo(bean);
	}


	@Override
	public int updateOpenHouseByMlsListingId(int mlsOrgId, String mlsListingId,String openHouseSchedules) {
		return listingDataDAO.updateOpenHouseByMlsListingId(mlsOrgId, mlsListingId, openHouseSchedules);
	}


	
	@Override
	public int deleteExpriedOpenHouse(int mlsOrgId) {
		return listingDataDAO.deleteExpriedOpenHouse(mlsOrgId);
	}

	@Override
	public int deleteListingData(long mlsOrgId, String mlsListingId) {
		listingDataDAO.deleteListingData(mlsOrgId, mlsListingId);
		return 0;
	}

	@Override
	public int updateStatus(long mlsOrgId, String mlsListingId, String listingStatus) {
		listingDataDAO.updateStatus(mlsOrgId, mlsListingId, listingStatus);
		return 0;
	}

	@Override
	public int deleteListingDatas(long mlsOrgId, List<String> mlsListingIds) {
		listingDataDAO.deleteListingDatas(mlsOrgId, mlsListingIds);
		return 0;
	}

	@Override
	public List<ListingDataBean> getAllListings(int mlsOrgId) {
		return listingDataDAO.getAllListings(mlsOrgId);
	}

	@Override
	public List<ListingDataBean> getSeqListings(int mlsOrgId, long startId, int limit) {
		return listingDataDAO.getSeqListings(mlsOrgId, startId, limit);
	}

}
