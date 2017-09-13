package com.homethy.web.domain;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

import com.homethy.web.utils.Constants;
import com.homethy.web.utils.FormatDataTool;


/**
 * 房源数据的基类
 * 
 * @author WangMeng
 *
 */
public class Databean {

	protected long id;
	protected String mlsListingId;
	protected int mlsOrgId;

	// 房子基本信息
	protected int price;
	protected int priceMax;
	
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	protected int bedrooms;
	protected float bathrooms;
	protected int fullBaths;
	protected int halfBaths;
	protected int quarterBaths;
	protected int threeQuarterBaths;
	protected String listingStatus = "NULL";
	protected int builtYear;
	protected int ageOfDwelling;
	protected int sqft;
	protected int totalBuildingSqft;
	protected int totalAvailableAcres;
	protected String propertyType = "NULL";

	// 房子地理位置信息
	protected String streetAddress = Constants.DefaultValue;
	protected String city = Constants.DefaultValue;
	protected String state = Constants.DefaultValue;
	protected String zipCode = Constants.DefaultValue;
	protected String county = Constants.DefaultValue;
	protected String community = Constants.DefaultValue;
	protected String longitude = Constants.DefaultValue;
	protected String latitude = Constants.DefaultValue;

	// neighborhood 相关信息
	protected long neighborhoodId;
	protected String neighborhoodName = Constants.DefaultValue;
	protected long neighborhoodMId;
	protected String neighborhoodMName = Constants.DefaultValue;
	protected long neighborhoodSId;
	protected String neighborhoodSName = Constants.DefaultValue;
	protected long neighborhoodRId;
	protected String neighborhoodRName = Constants.DefaultValue;

	// 房子描述信息
	protected int hoaFee;
	protected String hoaFeeFrequency = Constants.DefaultValue;
	protected String zoningType = Constants.DefaultValue;
	protected String detailsDescribe;
	protected String utilities = Constants.DefaultValue;
	protected String flooring = Constants.DefaultValue;
	protected String foundation = Constants.DefaultValue;
	protected String fireplacesType = Constants.DefaultValue;
	protected String fireplacesNumber = Constants.DefaultValue;
	protected String roofType = Constants.DefaultValue;
	protected String view = Constants.DefaultValue;
	protected String fence = Constants.DefaultValue;
	protected String garageDescription = Constants.DefaultValue;
	protected String garageParkingSpaces = Constants.DefaultValue;
	protected String heating = Constants.DefaultValue;
	protected String sewageType = Constants.DefaultValue;
	protected String exitingWater = Constants.DefaultValue;
	protected String laundry = Constants.DefaultValue;
	protected String diningRoom = Constants.DefaultValue;
	protected String familyRoom = Constants.DefaultValue;
	protected String otherRooms = Constants.DefaultValue;
	protected String kitchen = Constants.DefaultValue;
	
	// 图片信息
	protected String listingPictures = Constants.DefaultPicture;
	protected Date pictureModifiedTime;

	// list 的agent 和 office 字段
	protected String agentName = Constants.DefaultValue;
	protected String agentId;
	protected String agentLicense;
	protected String agentSearchKey;// 搜索agent信息的key
	protected String agentOrganizationId; // office id(用于feature listing搜索公司代理的房源)
	protected String agentOrganizationName = Constants.DefaultValue;
	protected String agentOrganizationLicense;
	protected String officeSearchKey;
	

	// co list 的agent 和office 信息
	protected String coAgentName = Constants.DefaultValue;;
	protected String coAgentId;
	protected String coAgentLicense;
	protected String coAgentSearchKey;// 搜索co agent的信息的key
	protected String coAgentOrgName = Constants.DefaultValue;
	protected String coAgentOrgId;
	protected String coAgentOrgLicense;
	protected String coOfficeSearchKey;

	// 标记位
	protected int deleteFlag;
	protected int addressPartialFlag = 0;// 是否可以展示地址
	protected int distributeToInternet = 0;// 是否展示到网站上
	protected int neighborhoodSearchedFlag;// 是否查询过 neighborhood id

	public int getNeighborhoodSearchedFlag() {
		return neighborhoodSearchedFlag;
	}

	public void setNeighborhoodSearchedFlag(int neighborhoodSearchedFlag) {
		this.neighborhoodSearchedFlag = neighborhoodSearchedFlag;
	}

	// 新增
	protected String pool;
	protected String stories;
	protected String buildingDescription;
	protected String specialListingCondition;
	protected String elementarySchool;
	protected String style = Constants.DefaultValue;
	protected String schoolDistrict = Constants.DefaultValue;
	protected String communityFeatures = Constants.DefaultValue;
	protected int assessmentAmount;
	protected int taxAmount;
	protected int taxYear;
	protected String assessorPlat = Constants.DefaultValue;
	protected String assessorLot = Constants.DefaultValue;
	protected String cooling = Constants.DefaultValue;
	protected String hotWater = Constants.DefaultValue;
	protected int basementNum;
	protected String basementEntrance = Constants.DefaultValue;
	protected String basementFlag = Constants.DefaultValue;
	protected String equipment = Constants.DefaultValue;
	protected String plumbing = Constants.DefaultValue;
	protected int parkingOnSite;
	protected String lotDescription = Constants.DefaultValue;
	protected String exterior = Constants.DefaultValue;
	protected int undergroundSqft;
	protected String gated = Constants.DefaultValue;
	protected String guardGated = Constants.DefaultValue;

	protected String waterFrontFlag = Constants.DefaultValue;; //是否有waterfront，值为yes或no
	protected String adultCommunity = Constants.DefaultValue;;//是否是老年人(55岁以上)社区，值为yes或no
	protected String mbrLevel = Constants.DefaultValue;;//主卧的楼层

	protected String landTenure = Constants.DefaultValue;
	protected String listingTerms = Constants.DefaultValue;
	protected String waterfrontDescription = Constants.DefaultValue;
	protected String middleSchool = Constants.DefaultValue;
	protected String highSchool = Constants.DefaultValue;
	protected String propertyTypeOrigin = Constants.DefaultValue;
	protected String propertySubType1 = Constants.DefaultValue;
	protected String propertySubType2 = Constants.DefaultValue;
	protected String statusOrigin = Constants.DefaultValue;
	
	protected int photoCount;
	protected String propertyId;

	protected Date chimeUpdated;

	public Date getChimeUpdated() {
		return chimeUpdated;
	}

	public void setChimeUpdated(Date chimeUpdated) {
		this.chimeUpdated = chimeUpdated;
	}

	public String getElementarySchool() {
		return elementarySchool;
	}

	public void setElementarySchool(String elementarySchool) {
		this.elementarySchool = elementarySchool;
	}

	public String getSpecialListingCondition() {
		return specialListingCondition;
	}

	public void setSpecialListingCondition(String specialListingCondition) {
		this.specialListingCondition = specialListingCondition;
	}

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public int getPhotoCount() {
		return photoCount;
	}

	public void setPhotoCount(int photoCount) {
		this.photoCount = photoCount;
	}

	public String getBuildingDescription() {
		return buildingDescription;
	}

	public void setBuildingDescription(String buildingDescription) {
		this.buildingDescription = buildingDescription;
	}

	public String getPool() {
		return pool;
	}

	public void setPool(String pool) {
		this.pool = pool;
	}

	public String getStories() {
		return stories;
	}

	public void setStories(String stories) {
		this.stories = stories;
	}

	/**
	 * 地址展示类型
	 * 
	 * @author wangmeng
	 *
	 */
	public enum AddressDisplayEnum {

		FULL(0, "address can be display"), Partial(1, "address can not be display");

		private int flag;

		AddressDisplayEnum(int flag, String desc) {
			this.setFlag(flag);
		}

		public int getFlag() {
			return flag;
		}

		public void setFlag(int flag) {
			this.flag = flag;
		}
	}

	/**
	 * 是否展示到网上
	 * 
	 * @author wangmeng
	 *
	 */
	public enum DisplayToInternetEnum {

		DISPLAY(0, "address can be display"), Not_DISPLAY(1, "address can not be display");

		private int flag;

		DisplayToInternetEnum(int flag, String desc) {
			this.setFlag(flag);
		}

		public int getFlag() {
			return flag;
		}

		public void setFlag(int flag) {
			this.flag = flag;
		}
	}

	public int getAddressPartialFlag() {
		return addressPartialFlag;
	}

	public String getAgentSearchKey() {
		return agentSearchKey;
	}

	public void setAgentSearchKey(String agentSearchKey) {
		this.agentSearchKey = agentSearchKey;
	}

	public String getCoAgentSearchKey() {
		return coAgentSearchKey;
	}

	public void setCoAgentSearchKey(String coAgentSearchKey) {
		this.coAgentSearchKey = coAgentSearchKey;
	}

	public int getQuarterBaths() {
		return quarterBaths;
	}

	public void setQuarterBaths(int quarterBaths) {
		this.quarterBaths = quarterBaths;
	}

	public int getThreeQuarterBaths() {
		return threeQuarterBaths;
	}

	public void setThreeQuarterBaths(int threeQuarterBaths) {
		this.threeQuarterBaths = threeQuarterBaths;
	}

	public Date getPictureModifiedTime() {
		return pictureModifiedTime;
	}

	public void setPictureModifiedTime(Date pictureModifiedTime) {
		this.pictureModifiedTime = pictureModifiedTime;
	}

	public String getCoAgentName() {
		return coAgentName;
	}

	public void setCoAgentName(String coAgentName) {
		this.coAgentName = coAgentName;
	}
	
	public String getCoAgentId() {
		return coAgentId;
	}

	public void setCoAgentId(String coAgentId) {
		this.coAgentId = coAgentId;
	}


	public String getCoAgentOrgName() {
		return coAgentOrgName;
	}

	public void setCoAgentOrgName(String coAgentOrgName) {
		this.coAgentOrgName = coAgentOrgName;
	}
	
	public String getCoAgentOrgId() {
		return coAgentOrgId;
	}

	public void setCoAgentOrgId(String coAgentOrgId) {
		this.coAgentOrgId = coAgentOrgId;
	}
	
	public String getCoAgentOrgLicense() {
		return coAgentOrgLicense;
	}

	public void setCoAgentOrgLicense(String coAgentOrgLicense) {
		this.coAgentOrgLicense = coAgentOrgLicense;
	}

	public String getCoAgentLicense() {
		return coAgentLicense;
	}

	public void setCoAgentLicense(String coAgentLicense) {
		this.coAgentLicense = coAgentLicense;
	}

	public int getDistributeToInternet() {
		return distributeToInternet;
	}

	public void setDistributeToInternet(int distributeToInternet) {
		this.distributeToInternet = distributeToInternet;
	}

	public long getNeighborhoodMId() {
		return neighborhoodMId;
	}

	public void setNeighborhoodMId(long neighborhoodMId) {
		this.neighborhoodMId = neighborhoodMId;
	}

	public String getNeighborhoodMName() {
		return neighborhoodMName;
	}

	public void setNeighborhoodMName(String neighborhoodMName) {
		this.neighborhoodMName = neighborhoodMName;
	}

	public long getNeighborhoodSId() {
		return neighborhoodSId;
	}

	public void setNeighborhoodSId(long neighborhoodSId) {
		this.neighborhoodSId = neighborhoodSId;
	}

	public String getNeighborhoodSName() {
		return neighborhoodSName;
	}

	public void setNeighborhoodSName(String neighborhoodSName) {
		this.neighborhoodSName = neighborhoodSName;
	}

	public long getNeighborhoodRId() {
		return neighborhoodRId;
	}

	public void setNeighborhoodRId(long neighborhoodRId) {
		this.neighborhoodRId = neighborhoodRId;
	}

	public String getNeighborhoodRName() {
		return neighborhoodRName;
	}

	public void setNeighborhoodRName(String neighborhoodRName) {
		this.neighborhoodRName = neighborhoodRName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUtilities() {
		return utilities;
	}

	public void setUtilities(String utilities) {
		this.utilities = utilities;
	}

	public int getHoaFee() {
		return hoaFee;
	}

	public void setHoaFee(int hoaFee) {
		this.hoaFee = hoaFee;
	}
	
	public String getHoaFeeFrequency() {
		return hoaFeeFrequency;
	}

	public void setHoaFeeFrequency(String hoaFeeFrequency) {
		this.hoaFeeFrequency = hoaFeeFrequency;
	}

	public String getZoningType() {
		return zoningType;
	}

	public void setZoningType(String zoningType) {
		this.zoningType = zoningType;
	}

	public String getFlooring() {
		return flooring;
	}

	public void setFlooring(String flooring) {
		this.flooring = flooring;
	}

	public int getAgeOfDwelling() {
		return ageOfDwelling;
	}

	public void setAgeOfDwelling(int ageOfDwelling) {
		this.ageOfDwelling = ageOfDwelling;
	}

	public String getFoundation() {
		return foundation;
	}

	public void setFoundation(String foundation) {
		this.foundation = foundation;
	}

	public String getFireplacesType() {
		return fireplacesType;
	}

	public void setFireplacesType(String fireplacesType) {
		this.fireplacesType = fireplacesType;
	}

	public String getFireplacesNumber() {
		return fireplacesNumber;
	}

	public void setFireplacesNumber(String fireplacesNumber) {
		this.fireplacesNumber = fireplacesNumber;
	}

	public String getRoofType() {
		return roofType;
	}

	public void setRoofType(String roofType) {
		this.roofType = roofType;
	}

	public int getTotalBuildingSqft() {
		return totalBuildingSqft;
	}

	public void setTotalBuildingSqft(int totalBuildingSqft) {
		this.totalBuildingSqft = totalBuildingSqft;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public int getTotalAvailableAcres() {
		return totalAvailableAcres;
	}

	public void setTotalAvailableAcres(int totalAvailableAcres) {
		this.totalAvailableAcres = totalAvailableAcres;
	}

	public String getFence() {
		return fence;
	}

	public void setFence(String fence) {
		this.fence = fence;
	}

	public String getGarageDescription() {
		return garageDescription;
	}

	public void setGarageDescription(String garageDescription) {
		this.garageDescription = garageDescription;
	}

	public String getGarageParkingSpaces() {
		return garageParkingSpaces;
	}

	public void setGarageParkingSpaces(String garageParkingSpaces) {
		this.garageParkingSpaces = garageParkingSpaces;
	}

	public String getHeating() {
		return heating;
	}

	public void setHeating(String heating) {
		this.heating = heating;
	}

	public String getSewageType() {
		return sewageType;
	}

	public void setSewageType(String sewageType) {
		this.sewageType = sewageType;
	}

	public String getExitingWater() {
		return exitingWater;
	}

	public void setExitingWater(String exitingWater) {
		this.exitingWater = exitingWater;
	}

	public String getLaundry() {
		return laundry;
	}

	public void setLaundry(String laundry) {
		this.laundry = laundry;
	}

	public String getDiningRoom() {
		return diningRoom;
	}

	public void setDiningRoom(String diningRoom) {
		this.diningRoom = diningRoom;
	}

	public String getFamilyRoom() {
		return familyRoom;
	}

	public void setFamilyRoom(String familyRoom) {
		this.familyRoom = familyRoom;
	}

	public String getOtherRooms() {
		return otherRooms;
	}

	public void setOtherRooms(String otherRooms) {
		this.otherRooms = otherRooms;
	}

	public String getKitchen() {
		return kitchen;
	}

	public void setKitchen(String kitchen) {
		this.kitchen = kitchen;
	}
	
	public String getSchoolDistrict() {
		return schoolDistrict;
	}

	public void setSchoolDistrict(String schoolDistrict) {
		this.schoolDistrict = schoolDistrict;
	}

	public String getMlsListingId() {
		return mlsListingId;
	}

	public void setMlsListingId(String mlsListingId) {
		this.mlsListingId = mlsListingId;
	}

	public int getMlsOrgId() {
		return mlsOrgId;
	}

	public void setMlsOrgId(int mlsOrgId) {
		this.mlsOrgId = mlsOrgId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getBedrooms() {
		return bedrooms;
	}

	public void setBedrooms(int bedrooms) {
		this.bedrooms = bedrooms;
	}

	public float getBathrooms() {
		return bathrooms;
	}

	public void setBathrooms(float bathrooms) {
		this.bathrooms = bathrooms;
	}

	public int getFullBaths() {
		return fullBaths;
	}

	public void setFullBaths(int fullBaths) {
		this.fullBaths = fullBaths;
	}

	public int getHalfBaths() {
		return halfBaths;
	}

	public void setHalfBaths(int halfBaths) {
		this.halfBaths = halfBaths;
	}

	public String getListingStatus() {
		return listingStatus;
	}

	public void setListingStatus(String listingStatus) {
		this.listingStatus = listingStatus;
	}

	public int getBuiltYear() {
		return builtYear;
	}

	public void setBuiltYear(int builtYear) {
		this.builtYear = builtYear;
	}

	public String getDetailsDescribe() {
		return detailsDescribe;
	}

	public void setDetailsDescribe(String detailsDescribe) {
		this.detailsDescribe = detailsDescribe;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public int getSqft() {
		return sqft;
	}

	public void setSqft(int sqft) {
		this.sqft = sqft;
	}

	public long getNeighborhoodId() {
		return neighborhoodId;
	}

	public void setNeighborhoodId(long neighborhoodId) {
		this.neighborhoodId = neighborhoodId;
	}

	public String getNeighborhoodName() {
		return neighborhoodName;
	}

	public void setNeighborhoodName(String neighborhoodName) {
		this.neighborhoodName = neighborhoodName;
	}

	public String getListingPictures() {
		return listingPictures;
	}

	public void setListingPictures(String listingPictures) {
		this.listingPictures = listingPictures;
	}

	public int getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	
	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getAgentOrganizationName() {
		return agentOrganizationName;
	}

	public void setAgentOrganizationName(String agentOrganizationName) {
		this.agentOrganizationName = agentOrganizationName;
	}

	public String getAgentOrganizationLicense() {
		return agentOrganizationLicense;
	}

	public void setAgentOrganizationLicense(String agentOrganizationLicense) {
		this.agentOrganizationLicense = agentOrganizationLicense;
	}

	public void setAddressPartialFlag(int addressPartialFlag) {
		this.addressPartialFlag = addressPartialFlag;
	}

	public String getAgentLicense() {
		return agentLicense;
	}

	public void setAgentLicense(String agentLicense) {
		this.agentLicense = agentLicense;
	}

	public String getOfficeSearchKey() {
		return officeSearchKey;
	}

	public void setOfficeSearchKey(String officeSearchKey) {
		this.officeSearchKey = officeSearchKey;
	}

	public String getCoOfficeSearchKey() {
		return coOfficeSearchKey;
	}

	public void setCoOfficeSearchKey(String coOfficeSearchKey) {
		this.coOfficeSearchKey = coOfficeSearchKey;
	}

	public String getCommunityFeatures() {
		return communityFeatures;
	}

	public void setCommunityFeatures(String communityFeatures) {
		this.communityFeatures = communityFeatures;
	}

	public int getAssessmentAmount() {
		return assessmentAmount;
	}

	public void setAssessmentAmount(int assessmentAmount) {
		this.assessmentAmount = assessmentAmount;
	}

	public int getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(int taxAmount) {
		this.taxAmount = taxAmount;
	}

	public int getTaxYear() {
		return taxYear;
	}

	public void setTaxYear(int taxYear) {
		this.taxYear = taxYear;
	}

	public String getAssessorPlat() {
		return assessorPlat;
	}

	public void setAssessorPlat(String assessorPlat) {
		this.assessorPlat = assessorPlat;
	}

	public String getAssessorLot() {
		return assessorLot;
	}

	public void setAssessorLot(String assessorLot) {
		this.assessorLot = assessorLot;
	}

	public String getCooling() {
		return cooling;
	}

	public void setCooling(String cooling) {
		this.cooling = cooling;
	}

	public String getHotWater() {
		return hotWater;
	}

	public void setHotWater(String hotWater) {
		this.hotWater = hotWater;
	}

	public int getBasementNum() {
		return basementNum;
	}

	public void setBasementNum(int basementNum) {
		this.basementNum = basementNum;
	}

	public String getBasementEntrance() {
		return basementEntrance;
	}

	public void setBasementEntrance(String basementEntrance) {
		this.basementEntrance = basementEntrance;
	}

	public String getBasementFlag() {
		return basementFlag;
	}

	public void setBasementFlag(String basementFlag) {
		this.basementFlag = basementFlag;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public String getPlumbing() {
		return plumbing;
	}

	public void setPlumbing(String plumbing) {
		this.plumbing = plumbing;
	}

	public int getParkingOnSite() {
		return parkingOnSite;
	}

	public void setParkingOnSite(int parkingOnSite) {
		this.parkingOnSite = parkingOnSite;
	}

	public String getLotDescription() {
		return lotDescription;
	}

	public void setLotDescription(String lotDescription) {
		this.lotDescription = lotDescription;
	}

	public String getExterior() {
		return exterior;
	}

	public void setExterior(String exterior) {
		this.exterior = exterior;
	}

	public int getUndergroundSqft() {
		return undergroundSqft;
	}

	public void setUndergroundSqft(int undergroundSqft) {
		this.undergroundSqft = undergroundSqft;
	}
	
	public String getGated() {
		return gated;
	}

	public void setGated(String gated) {
		this.gated = gated;
	}
	
	public String getGuardGated() {
		return guardGated;
	}

	public void setGuardGated(String guardGated) {
		this.guardGated = guardGated;
	}

	public String getWaterFrontFlag() {
		return waterFrontFlag;
	}

	public void setWaterFrontFlag(String waterFrontFlag) {
		this.waterFrontFlag = waterFrontFlag;
	}

	public String getAdultCommunity() {
		return adultCommunity;
	}

	public void setAdultCommunity(String adultCommunity) {
		this.adultCommunity = adultCommunity;
	}

	public String getMbrLevel() {
		return mbrLevel;
	}

	public void setMbrLevel(String mbrLevel) {
		this.mbrLevel = mbrLevel;
	}

	public int getPriceMax() {
		return priceMax;
	}

	public void setPriceMax(int priceMax) {
		this.priceMax = priceMax;
	}

	public String getLandTenure() {
		return landTenure;
	}

	public void setLandTenure(String landTenure) {
		this.landTenure = landTenure;
	}

	public String getListingTerms() {
		return listingTerms;
	}

	public void setListingTerms(String listingTerms) {
		this.listingTerms = listingTerms;
	}

	public String getWaterfrontDescription() {
		return waterfrontDescription;
	}

	public void setWaterfrontDescription(String waterfrontDescription) {
		this.waterfrontDescription = waterfrontDescription;
	}

	public String getMiddleSchool() {
		return middleSchool;
	}

	public void setMiddleSchool(String middleSchool) {
		this.middleSchool = middleSchool;
	}

	public String getHighSchool() {
		return highSchool;
	}

	public void setHighSchool(String highSchool) {
		this.highSchool = highSchool;
	}

	public String getPropertyTypeOrigin() {
		return propertyTypeOrigin;
	}

	public void setPropertyTypeOrigin(String propertyTypeOrigin) {
		this.propertyTypeOrigin = propertyTypeOrigin;
	}

	public String getPropertySubType1() {
		return propertySubType1;
	}

	public void setPropertySubType1(String propertySubType1) {
		this.propertySubType1 = propertySubType1;
	}

	public String getPropertySubType2() {
		return propertySubType2;
	}

	public void setPropertySubType2(String propertySubType2) {
		this.propertySubType2 = propertySubType2;
	}

	public String getStatusOrigin() {
		return statusOrigin;
	}

	public void setStatusOrigin(String statusOrigin) {
		this.statusOrigin = statusOrigin;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		Field[] fields = Databean.class.getDeclaredFields();
		for (Field field : fields) {
			buffer.append(field.getName());
			buffer.append("=");
			try {
				buffer.append(field.get(this)).append("\n");
			} catch (IllegalArgumentException e) {
			} catch (IllegalAccessException e) {
			}
		}
		return buffer.toString();
	}

	/**
	 * 格式化数据信息
	 */
	public void formatInfo() {
		hoaFeeFrequency = FormatDataTool.formatStringField(hoaFeeFrequency);
		zoningType = FormatDataTool.formatStringField(zoningType);
		flooring = FormatDataTool.formatStringField(flooring);
		foundation = FormatDataTool.formatStringField(foundation);
		fireplacesType = FormatDataTool.formatStringField(fireplacesType);
		fireplacesNumber = FormatDataTool.formatStringField(fireplacesNumber);
		roofType = FormatDataTool.formatStringField(roofType);
		view = FormatDataTool.formatStringField(view);
		fence = FormatDataTool.formatStringField(fence);
		garageDescription = FormatDataTool.formatStringField(garageDescription);
		garageParkingSpaces = FormatDataTool.formatStringField(garageParkingSpaces);
		heating = FormatDataTool.formatStringField(heating);
		sewageType = FormatDataTool.formatStringField(sewageType);
		exitingWater = FormatDataTool.formatStringField(exitingWater);
		laundry = FormatDataTool.formatStringField(laundry);
		diningRoom = FormatDataTool.formatStringField(diningRoom);
		familyRoom = FormatDataTool.formatStringField(familyRoom);
		otherRooms = FormatDataTool.formatStringField(otherRooms);
		kitchen = FormatDataTool.formatStringField(kitchen);
		style = FormatDataTool.formatStringField(style);
		stories = FormatDataTool.formatStringField(stories);
		buildingDescription = FormatDataTool.formatStringField(buildingDescription);
		specialListingCondition=FormatDataTool.formatStringField(specialListingCondition);
		pool = FormatDataTool.formatStringField(pool);
		elementarySchool=FormatDataTool.formatStringField(elementarySchool);
		schoolDistrict = FormatDataTool.formatStringField(schoolDistrict);
		communityFeatures = FormatDataTool.formatStringField(communityFeatures);
		assessorPlat = FormatDataTool.formatStringField(assessorPlat);
		assessorLot = FormatDataTool.formatStringField(assessorLot);
		cooling = FormatDataTool.formatStringField(cooling);
		hotWater = FormatDataTool.formatStringField(hotWater);
		basementEntrance = FormatDataTool.formatStringField(basementEntrance);
		basementFlag = FormatDataTool.formatStringField(basementFlag);
		equipment = FormatDataTool.formatStringField(equipment);
		plumbing = FormatDataTool.formatStringField(plumbing);
		lotDescription = FormatDataTool.formatStringField(lotDescription);
		exterior = FormatDataTool.formatStringField(exterior);
		gated = FormatDataTool.formatStringField(gated);
		guardGated = FormatDataTool.formatStringField(guardGated);
		waterFrontFlag = FormatDataTool.formatStringField(waterFrontFlag);
		adultCommunity = FormatDataTool.formatStringField(adultCommunity);
		mbrLevel = FormatDataTool.formatStringField(mbrLevel);
		waterfrontDescription = FormatDataTool.formatStringField(waterfrontDescription);
		listingTerms = FormatDataTool.formatStringField(listingTerms);
		landTenure = FormatDataTool.formatStringField(landTenure);
		middleSchool = FormatDataTool.formatStringField(middleSchool);
		highSchool = FormatDataTool.formatStringField(highSchool);
		propertyTypeOrigin = FormatDataTool.formatStringField(propertyTypeOrigin);
		propertySubType1 = FormatDataTool.formatStringField(propertySubType1);
		propertySubType2 = FormatDataTool.formatStringField(propertySubType2);
		statusOrigin = FormatDataTool.formatStringField(statusOrigin);

		//bathroom
		if(bathrooms==0){
			bathrooms=fullBaths*(1.0f)+halfBaths*(0.5f)+quarterBaths*(0.25f)+threeQuarterBaths*(0.75f);
		}
		// 填充房龄
		if (ageOfDwelling <= 0) {
			try {
				if (builtYear > 1500 && builtYear <= Calendar.getInstance().get(Calendar.YEAR)) {
					ageOfDwelling = Calendar.getInstance().get(Calendar.YEAR) - builtYear;
				}else{
					builtYear=0;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (ageOfDwelling < 0) {
				ageOfDwelling = 0;
			}
		}
		
		if (StringUtils.isNotEmpty(city)) {
			city = WordUtils.capitalize(city.toLowerCase().trim());
		}
		if (StringUtils.isNotEmpty(county)) {
			county = WordUtils.capitalize(county.toLowerCase().trim());
		}
		streetAddress = FormatDataTool.formatStringField(streetAddress);
		if (StringUtils.isNotEmpty(streetAddress)) {
			streetAddress = streetAddress.trim().replaceAll("\"", "");
			if (streetAddress.equalsIgnoreCase("Address not disclosed")
					|| streetAddress.equalsIgnoreCase(Constants.DefaultValue)) {
				addressPartialFlag = AddressDisplayEnum.Partial.getFlag();
			}
		}
		
		if (addressPartialFlag == AddressDisplayEnum.Partial.getFlag()) {
			longitude = Constants.DefaultValue;
			latitude = Constants.DefaultValue;
		}

		community = FormatDataTool.formatStringField(community);
		
		neighborhoodName = FormatDataTool.formatStringField(neighborhoodName);		
		if (StringUtils.isNotEmpty(neighborhoodName)) {
			neighborhoodName = neighborhoodName.replaceAll("\"", "");
		}
		neighborhoodMName = FormatDataTool.formatStringField(neighborhoodMName);
		if (StringUtils.isNotEmpty(neighborhoodMName)) {
			neighborhoodMName = neighborhoodMName.replaceAll("\"", "");
		}
		neighborhoodSName = FormatDataTool.formatStringField(neighborhoodSName);
		if (StringUtils.isNotEmpty(neighborhoodSName)) {
			neighborhoodSName = neighborhoodSName.replaceAll("\"", "");
		}
		neighborhoodRName = FormatDataTool.formatStringField(neighborhoodRName);
		if (StringUtils.isNotEmpty(neighborhoodRName)) {
			neighborhoodRName = neighborhoodRName.replaceAll("\"", "");
		}
		agentName = FormatDataTool.formatStringField(agentName);
		agentId = FormatDataTool.formatStringField(agentId);
		agentOrganizationId = FormatDataTool.formatStringField(agentOrganizationId);
		agentOrganizationName = FormatDataTool.formatStringField(agentOrganizationName);
		agentOrganizationLicense = FormatDataTool.formatStringField(agentOrganizationLicense);
		coAgentName = FormatDataTool.formatStringField(coAgentName);
		coAgentId = FormatDataTool.formatStringField(coAgentId);
		coAgentOrgName = FormatDataTool.formatStringField(coAgentOrgName);
		coAgentOrgId = FormatDataTool.formatStringField(coAgentOrgId);
		coAgentOrgLicense = FormatDataTool.formatStringField(coAgentOrgLicense);
		longitude = FormatDataTool.formatStringField(longitude);
		latitude = FormatDataTool.formatStringField(latitude);

		
		if (addressPartialFlag == AddressDisplayEnum.Partial.getFlag()) {
			longitude = Constants.DefaultValue;
			latitude = Constants.DefaultValue;
		}
	}

	

	/**
	 * 是否是合法的经纬度信息
	 * 
	 * @return
	 */
	public boolean isLegaleCoordinate() {
		if (StringUtils.isEmpty(latitude) || latitude.equals(Constants.DefaultValue)
				|| (int) Float.parseFloat(latitude) == 0 || StringUtils.isEmpty(longitude)
				|| longitude.equals(Constants.DefaultValue) || (int) Float.parseFloat(longitude) == 0) {
			return false;
		}
		return true;
	}

	/**
	 * 判断picturesurl中的图片链接是否符合http开头，。jpg结尾
	 * 
	 * @param pictureURL
	 *            （以|分割的图片链接）
	 * @return
	 */
	public  boolean pictureIsCorrect() {
		if (StringUtils.isNotEmpty(listingPictures)) {
			listingPictures = listingPictures.trim();
			String[] pictures = listingPictures.split("\\|");
			for (String picture : pictures) {
				if (!picture.startsWith("http")) {
					return false;
				}
			}
			return true;
		} else {
			return true;
		}
	}

	public String getAgentOrganizationId() {
		return agentOrganizationId;
	}

	public void setAgentOrganizationId(String agentOrganizationId) {
		this.agentOrganizationId = agentOrganizationId;
	}

	public static void main(String[] args) {
		String aString = "  jdfjkjs    ";
		aString = aString.trim();
		System.out.println(aString);
		System.exit(0);
	}
	
}
