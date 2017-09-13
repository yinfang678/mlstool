package com.homethy.web.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.homethy.web.annotation.BatchOperationLanguageDriver;
import com.homethy.web.annotation.DataSourceListing;
import com.homethy.web.domain.ListingDataBean;

@DataSourceListing
public interface ListingDataDAO {

	String tableName = "listing_info_compare";
	/**
	 * 查询一个区域内未过期的房源mls listing id
	 * @param mlsOrgId
	 * @return
	 */
	@Select("select mls_listing_id from " + tableName +" where mls_org_id=#{mlsOrgId} and delete_flag=0")
	Set<String> getListingIds(@Param("mlsOrgId") int mlsOrgId);
	
	
	/**
	 * 分段查询数据
	 * @param mlsOrgId
	 * @return
	 */
	@Select(" select "
			+ " * "
			+ " from "
			+ tableName 
			+ " where "
			+ " mls_org_id=#{mlsOrgId} "
			+ " and delete_flag=0 "
			+ " and id > #{startId} "
			+ " order by id asc "
			+ " limit #{limit}")
	List<ListingDataBean> getSeqListings(@Param("mlsOrgId") int mlsOrgId, @Param("startId") long startId, @Param("limit") int limit);
	
	
	/**
	 * 查询一个区域内所有房子的id和date
	 * @param mlsOrgId
	 * @return
	 */
	@Select("select id,chime_updated from " + tableName +" where mls_org_id=#{mlsOrgId}")
	List<Map<Long, Date>>  getIdAndDate(@Param("mlsOrgId") int mlsOrgId);
	
	/**
	 * 查询一个区域内未过期的房源信息
	 * @param mlsOrgId
	 * @return
	 */
	@Select("select * from " + tableName +" where mls_org_id=#{mlsOrgId} and delete_flag=0")
	List<ListingDataBean> getAllListings(@Param("mlsOrgId") int mlsOrgId);
	
	/**
	 * 查询一个房源信息
	 * @param mlsOrgId
	 * @param mlsListingId
	 * @return
	 */
	@Select("select * from " + tableName +" where mls_listing_id=#{mlsListingId} and mls_org_id=#{mlsOrgId}")
	ListingDataBean getListingData(@Param("mlsOrgId") long mlsOrgId, @Param("mlsListingId") String mlsListingId);
	

	
	/**
	 * 插入数据
	 * @param 
	 * @return
	 */
	@Options(useGeneratedKeys = true)
	@Insert("insert into "+tableName+" set "
			+ ""
			+ "mls_listing_id=#{mlsListingId},"
			+ "data_resource=#{dataResource},"
			+ "mls_org_id=#{mlsOrgId},"
			+ "price=#{price},"
			+ "price_Max=#{priceMax},"
			+ "bedrooms=#{bedrooms},"
			+ "bathrooms=#{bathrooms},"
			+ "full_baths=#{fullBaths},"
			+ "half_baths=#{halfBaths},"
			+ "quarter_baths=#{quarterBaths},"
			+ "three_quarter_baths=#{threeQuarterBaths},"
			+ "stories=#{stories},"
			+ "building_description=#{buildingDescription},"
			+ "special_listing_condition=#{specialListingCondition},"
			+ "pool=#{pool},"
			+ "listing_status=#{listingStatus},"
			+ "built_year=#{builtYear},"
			+ "utilities=#{utilities},"
			+ "details_describe=#{detailsDescribe},"
			+ "property_type=#{propertyType},"
			+ "hoa_fee=#{hoaFee},"
			+ "hoa_fee_frequency=#{hoaFeeFrequency},"
			+ "purchase_type=#{purchaseType},"
			+ "property_description=#{propertyDescription},"
			+ "zoning_type=#{zoningType},"
			+ "location_id=#{locationId},"
			+ "street_address=#{streetAddress},"
			+ "city=#{city},"
			+ "state=#{state},"
			+ "zip_code=#{zipCode},"
			+ "county=#{county},"
			+ "community=#{community},"
			+ "longitude=#{longitude},"
			+ "latitude=#{latitude},"
			+ "flooring=#{flooring},"
			+ "age_of_dwelling=#{ageOfDwelling},"
			+ "foundation=#{foundation},"
			+ "fireplaces_type=#{fireplacesType},"
			+ "fireplaces_number=#{fireplacesNumber},"
			+ "roof_type=#{roofType},"
			+ "total_building_sqft=#{totalBuildingSqft},"
			+ "dwelling_type=#{dwellingType},"
			+ "view=#{view},"
			+ "total_available_acres=#{totalAvailableAcres},"
			+ "sqft=#{sqft},"
			+ "fence=#{fence},"
			+ "garage_description=#{garageDescription},"
			+ "garage_parking_spaces=#{garageParkingSpaces},"
			+ "heating=#{heating},"
			+ "sewage_type=#{sewageType},"
			+ "exiting_water=#{exitingWater},"
			+ "laundry=#{laundry},"
			+ "dining_room=#{diningRoom},"
			+ "family_room=#{familyRoom},"
			+ "other_rooms=#{otherRooms},"
			+ "kitchen=#{kitchen},"
			+ "style=#{style},"
			+ "school_district=#{schoolDistrict},"
			+ "community_features=#{communityFeatures},"
			+ "assessment_amount=#{assessmentAmount},"
			+ "tax_amount=#{taxAmount},"
			+ "tax_year=#{taxYear},"
			+ "assessor_plat=#{assessorPlat},"
			+ "assessor_lot=#{assessorLot},"
			+ "cooling=#{cooling},"
			+ "hot_water=#{hotWater},"
			+ "basement_num=#{basementNum},"
			+ "basement_entrance=#{basementEntrance},"
			+ "basement_flag=#{basementFlag},"
			+ "equipment=#{equipment},"
			+ "plumbing=#{plumbing},"
			+ "parking_on_site=#{parkingOnSite},"
			+ "lot_description=#{lotDescription},"
			+ "exterior=#{exterior},"
			+ "waterfront_flag=#{waterFrontFlag},"
			+ "adult_community=#{adultCommunity},"
			+ "mbr_level=#{mbrLevel},"
			+ "lender_owned=#{lenderOwned},"
			+ "gated=#{gated},"
			+ "guard_gated=#{guardGated},"
			+ "contract_status=#{contractStatus},"
			+ "short_sales=#{shortSales},"
			+ "waterfront_description=#{waterfrontDescription},"
			+ "listing_terms=#{listingTerms},"
			+ "land_tenure=#{landTenure},"
			+ "middle_school=#{middleSchool},"
			+ "high_school=#{highSchool},"
			+ "property_type_origin=#{propertyTypeOrigin},"
			+ "property_sub_type1=#{propertySubType1},"
			+ "property_sub_type2=#{propertySubType2},"
			+ "status_origin=#{statusOrigin},"
			+ "underground_sqft=#{undergroundSqft},"
			+ "neighborhood_id=#{neighborhoodId},"
			+ "neighborhood_name=#{neighborhoodName},"
			+ "neighborhood_m_id=#{neighborhoodMId},"
			+ "neighborhood_m_name=#{neighborhoodMName},"
			+ "neighborhood_s_id=#{neighborhoodSId},"
			+ "neighborhood_s_name=#{neighborhoodSName},"
			+ "neighborhood_r_id=#{neighborhoodRId},"
			+ "neighborhood_r_name=#{neighborhoodRName},"
			+ "description=#{description},"
			+ "listing_pictures_id=#{listingPicturesId},"
			+ "listing_pictures=#{listingPictures},"
			+ "picture_modified_time=#{pictureModifiedTime},"
			+ "link=#{link},"
			+ "elementary_school=#{elementarySchool},"
			+ "create_time=#{createTime},"
			+ "update_time=#{updateTime},"
			+ "delete_flag=#{deleteFlag},"
			+ "agent_name=#{agentName},"
			+ "agent_id=#{agentId},"
			+ "agent_license=#{agentLicense},"
			+ "agent_organization_id=#{agentOrganizationId},"
			+ "agent_organization_name=#{agentOrganizationName},"
			+ "agent_organization_license=#{agentOrganizationLicense},"
			+ "co_agent_name=#{coAgentName},"
			+ "co_agent_id=#{coAgentId},"
			+ "co_agent_org_name=#{coAgentOrgName},"
			+ "co_agent_org_id=#{coAgentOrgId},"
			+ "co_agent_org_license=#{coAgentOrgLicense},"
			+ "co_agent_license=#{coAgentLicense},"	
			+ "address_partial_flag=#{addressPartialFlag},"
			+ " neighborhood_searched_flag=#{neighborhoodSearchedFlag}")
	int insertListingInfo(ListingDataBean bean);

	
	@Update("update " 
			+ tableName 
			+" set delete_flag=1 "
			+ " where "
			+ " mls_listing_id=#{mlsListingId} "
			+ " and mls_org_id=#{mlsOrgId}")
	int deleteListingData(@Param("mlsOrgId") long mlsOrgId, @Param("mlsListingId") String mlsListingId);
	
	@Lang(BatchOperationLanguageDriver.class)
	@Update("update " 
			+ tableName 
			+" set delete_flag=1 "
			+ " where "
			+ " mls_org_id=#{mlsOrgId}"
			+ " and"
			+ " mls_listing_id in (#{mlsListingIds})")
	int deleteListingDatas(@Param("mlsOrgId") long mlsOrgId, @Param("mlsListingIds") List<String> mlsListingIds);
	
	@Update("update " 
			+ tableName 
			+" set listing_status=#{listingStatus} "
			+ " where "
			+ " mls_listing_id=#{mlsListingId} "
			+ " and mls_org_id=#{mlsOrgId}")
	int updateStatus(@Param("mlsOrgId") long mlsOrgId, @Param("mlsListingId") String mlsListingId, @Param("listingStatus") String listingStatus);
	
	/**
	 * 更新一条记录
	 * @param bean
	 */
	@Update("update "+ tableName +" set "
			+ "price=#{price},"
			+ "price_Max=#{priceMax},"
			+ "bedrooms=#{bedrooms},"
			+ "bathrooms=#{bathrooms},"
			+ "full_baths=#{fullBaths},"
			+ "half_baths=#{halfBaths},"
			+ "quarter_baths=#{quarterBaths},"
			+ "three_quarter_baths=#{threeQuarterBaths},"
			+ "stories=#{stories},"
			+ "building_description=#{buildingDescription},"
			+ "special_listing_condition=#{specialListingCondition},"
			+ "pool=#{pool},"
			+ "listing_status=#{listingStatus},"
			+ "built_year=#{builtYear},"
			+ "utilities=#{utilities},"
			+ "details_describe=#{detailsDescribe},"
			+ "property_type=#{propertyType},"
			+ "hoa_fee=#{hoaFee},"
			+ "hoa_fee_frequency=#{hoaFeeFrequency},"
			+ "purchase_type=#{purchaseType},"
			+ "property_description=#{propertyDescription},"
			+ "zoning_type=#{zoningType},"
			+ "location_id=#{locationId},"
			+ "street_address=#{streetAddress},"
			+ "city=#{city},"
			+ "state=#{state},"
			+ "zip_code=#{zipCode},"
			+ "county=#{county},"
			+ "community=#{community},"
			+ "longitude=#{longitude},"
			+ "latitude=#{latitude},"
			+ "flooring=#{flooring},"
			+ "age_of_dwelling=#{ageOfDwelling},"
			+ "foundation=#{foundation},"
			+ "fireplaces_type=#{fireplacesType},"
			+ "fireplaces_number=#{fireplacesNumber},"
			+ "roof_type=#{roofType},"
			+ "short_sales=#{shortSales},"
			+ "contract_status=#{contractStatus},"
			+ "total_building_sqft=#{totalBuildingSqft},"
			+ "dwelling_type=#{dwellingType},"
			+ "view=#{view},"
			+ "total_available_acres=#{totalAvailableAcres},"
			+ "sqft=#{sqft},"
			+ "fence=#{fence},"
			+ "garage_description=#{garageDescription},"
			+ "garage_parking_spaces=#{garageParkingSpaces},"
			+ "heating=#{heating},"
			+ "sewage_type=#{sewageType},"
			+ "exiting_water=#{exitingWater},"
			+ "laundry=#{laundry},"
			+ "dining_room=#{diningRoom},"
			+ "family_room=#{familyRoom},"
			+ "other_rooms=#{otherRooms},"
			+ "kitchen=#{kitchen},"
			+ "style=#{style},"
			+ "school_district=#{schoolDistrict},"
			+ "community_features=#{communityFeatures},"
			+ "assessment_amount=#{assessmentAmount},"
			+ "tax_amount=#{taxAmount},"
			+ "tax_year=#{taxYear},"
			+ "assessor_plat=#{assessorPlat},"
			+ "assessor_lot=#{assessorLot},"
			+ "cooling=#{cooling},"
			+ "hot_water=#{hotWater},"
			+ "basement_num=#{basementNum},"
			+ "basement_entrance=#{basementEntrance},"
			+ "basement_flag=#{basementFlag},"
			+ "equipment=#{equipment},"
			+ "plumbing=#{plumbing},"
			+ "parking_on_site=#{parkingOnSite},"
			+ "lot_description=#{lotDescription},"
			+ "exterior=#{exterior},"
			+ "waterfront_flag=#{waterFrontFlag},"
			+ "adult_community=#{adultCommunity},"
			+ "mbr_level=#{mbrLevel},"
			+ "lender_owned=#{lenderOwned},"
			+ "gated=#{gated},"
			+ "guard_gated=#{guardGated},"
			+ "waterfront_description=#{waterfrontDescription},"
			+ "listing_terms=#{listingTerms},"
			+ "land_tenure=#{landTenure},"
			+ "middle_school=#{middleSchool},"
			+ "high_school=#{highSchool},"
			+ "property_type_origin=#{propertyTypeOrigin},"
			+ "property_sub_type1=#{propertySubType1},"
			+ "property_sub_type2=#{propertySubType2},"
			+ "status_origin=#{statusOrigin},"
			+ "underground_sqft=#{undergroundSqft},"
			+ "neighborhood_id=#{neighborhoodId},"
			+ "neighborhood_name=#{neighborhoodName},"
			+ "neighborhood_m_id=#{neighborhoodMId},"
			+ "neighborhood_m_name=#{neighborhoodMName},"
			+ "neighborhood_s_id=#{neighborhoodSId},"
			+ "neighborhood_s_name=#{neighborhoodSName},"
			+ "neighborhood_r_id=#{neighborhoodRId},"
			+ "neighborhood_r_name=#{neighborhoodRName},"
			+ "description=#{description},"
			+ "listing_pictures_id=#{listingPicturesId},"
			+ "listing_pictures=#{listingPictures},"
			+ "picture_modified_time=#{pictureModifiedTime},"
			+ "link=#{link},"
			+ "elementary_school=#{elementarySchool},"
			+ "create_time=#{createTime},"
			+ "update_time=#{updateTime},"
			+ "delete_flag=0,"
			+ "agent_name=#{agentName},"
			+ "agent_id=#{agentId},"
			+ "agent_organization_id=#{agentOrganizationId},"
			+ "agent_organization_name=#{agentOrganizationName},"
			+ "agent_organization_license=#{agentOrganizationLicense},"
			+ "agent_license=#{agentLicense},"
			+ "co_agent_name=#{coAgentName},"
			+ "co_agent_id=#{coAgentId},"
			+ "co_agent_org_name=#{coAgentOrgName},"
			+ "co_agent_org_id=#{coAgentOrgId},"
			+ "co_agent_org_license=#{coAgentOrgLicense},"
			+ "co_agent_license=#{coAgentLicense},"
			+ " neighborhood_searched_flag=#{neighborhoodSearchedFlag},"
//			+ "open_house_schedules=#{openHouseSchedules},"
//			+ "open_house_flag=#{openHouseFlag},"
			+ "address_partial_flag=#{addressPartialFlag}"
			+  " where mls_listing_id=#{mlsListingId}"
			+ " and mls_org_id=#{mlsOrgId}")
	void updateListingInfo(ListingDataBean bean);

	
	/**
	 * 更新openhouse的信息
	 * @param mlsOrgId
	 * @param mlsListingId
	 * @param openHouseSchedules
	 * @return
	 */
	@Update(""
			+ " update "
			+ tableName
			+ " set "
			+ " open_house_flag=1"
			+ ", open_house_schedules=#{openHouseSchedules}"
			+ " where mls_org_id=#{mlsOrgId}"
			+ " and mls_listing_id=#{mlsListingId}"
			+ " and delete_flag=0")
	int updateOpenHouseByMlsListingId(@Param("mlsOrgId") int mlsOrgId, @Param("mlsListingId") String mlsListingId, @Param("openHouseSchedules") String openHouseSchedules);
	
	
	@Lang(BatchOperationLanguageDriver.class)
	@Update(""
			+ " update "
			+ tableName
			+ " set "
			+ " open_house_flag=0"
			+ " ,open_house_schedules=NULL"
			+ " where mls_org_id=#{mlsOrgId}")
	int deleteExpriedOpenHouse(@Param("mlsOrgId") int mlsOrgId);
	
	
}