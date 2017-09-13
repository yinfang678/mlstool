package com.homethy.web.service;

import java.util.List;
import java.util.Set;

import com.homethy.web.domain.ListingDataBean;

public interface ListingDataService {

	/**
	 * 查询一个区域内的房源id
	 * @param mlsOrgId
	 * @return
	 */
	Set<String> getListingIds( int mlsOrgId);
	
	/**
	 * 查询一个区域内的全部房源信息
	 * @param mlsOrgId
	 * @return
	 */
	List<ListingDataBean> getAllListings(int mlsOrgId);
	
	/**
	 * 分段查询
	 * @param mlsOrgId
	 * @return
	 */
	List<ListingDataBean> getSeqListings(int mlsOrgId,long startId,int limit);
	
	/**
	 * 查询一个房源信息
	 * @param mlsOrgId
	 * @param mlsListingId
	 * @return
	 */
	ListingDataBean selectListingInfo(long mlsOrgId,String mlsListingId);
	
	/**
	 * 插入房源信息
	 * @param bean
	 * @return
	 */
	int insertListingInfo(ListingDataBean bean);
	/**
	 * 按照mls_listing_id 和mls_org_id更新房源信息
	 * @param bean
	 */
	void updateListingInfo(ListingDataBean bean);

	/**
	 * 删除单条数据
	 * @param mlsOrgId
	 * @param mlsListingId
	 * @return
	 */
	int deleteListingData(long mlsOrgId, String mlsListingId);
	
	/**
	 * 批量删除数据
	 * @param mlsOrgId
	 * @param mlsListingIds
	 * @return
	 */
	int deleteListingDatas(long mlsOrgId,List<String> mlsListingIds);

	/**
	 * 更新房源的状态
	 * @param mlsOrgId
	 * @param mlsListingId
	 * @param listingStatus
	 * @return
	 */
	int updateStatus( long mlsOrgId, String mlsListingId,String listingStatus);
	
	/**
	 * 删除指定mls org id 下过期的openhouse信息
	 * @param mlsOrgId
	 * @param mlslistingId
	 * @return
	 */
	int deleteExpriedOpenHouse(int mlsOrgId);
	/**
	 * 更新open house的数据
	 */
	int updateOpenHouseByMlsListingId(int mlsOrgId,String mlsListingId,String openHouseSchedules);
	
	
}
