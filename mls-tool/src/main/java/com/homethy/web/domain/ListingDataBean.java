package com.homethy.web.domain;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.homethy.web.utils.Constants;
import com.homethy.web.utils.FormatDataTool;


/**
 * 房源数据信息
 * 
 * @author WangMeng
 *
 */
public class ListingDataBean extends Databean {

	private String purchaseType = PurchaseType.SALE.desc;
	private String propertyDescription = Constants.DefaultValue;
	private int locationId;
	private String dwellingType = Constants.DefaultValue;
	private String description = Constants.DefaultValue;
	private int listingPicturesId;
	private String link = Constants.DefaultValue;

	protected String lenderOwned = Constants.DefaultValue; // 是否借贷方拥有房子,即银行收走的房子
	protected String shortSales = Constants.DefaultValue;
	protected String contractStatus = Constants.DefaultValue;

	private Date createTime;
	private Date updateTime;

	// open house 相关信息
	private String openHouseSchedules;
	private int openHouseFlag;

	private String dataResource;
	/**
	 * 购买类型(sale lease)
	 * 
	 * @author wangmeng
	 *
	 */
	public enum PurchaseType {
		SALE("For Sale"), LEASE("For Lease");
		private String desc;

		private PurchaseType(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDetailsDescribe() {

		return detailsDescribe;
	}

	public void setDetailsDescribe(String detailsDescribe) {
		if (StringUtils.isNotEmpty(detailsDescribe)) {
			this.detailsDescribe = detailsDescribe;
		}
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}

	public String getPropertyDescription() {
		return propertyDescription;
	}

	public void setPropertyDescription(String propertyDescription) {
		this.propertyDescription = propertyDescription;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		if (StringUtils.isNotEmpty(streetAddress)) {
			this.streetAddress = streetAddress;
		}
	}

	public String getDwellingType() {
		return dwellingType;
	}

	public void setDwellingType(String dwellingType) {
		this.dwellingType = dwellingType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getListingPicturesId() {
		return listingPicturesId;
	}

	public void setListingPicturesId(int listingPicturesId) {
		this.listingPicturesId = listingPicturesId;
	}

	public String getListingPictures() {
		return listingPictures;
	}

	public void setListingPictures(String listingPictures) {
		this.listingPictures = listingPictures;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public void formatInfo() {
		super.formatInfo();
		purchaseType = FormatDataTool.formatStringField(purchaseType);
		propertyDescription = FormatDataTool.formatStringField(propertyDescription);
		dwellingType = FormatDataTool.formatStringField(dwellingType);
		description = FormatDataTool.formatStringField(description);
		lenderOwned = FormatDataTool.formatStringField(lenderOwned);

		if (StringUtils.isEmpty(purchaseType) || purchaseType.equalsIgnoreCase("null")
				|| purchaseType.equalsIgnoreCase(Constants.DefaultValue)) {
			purchaseType = PurchaseType.SALE.desc;
		}

		// 图片修改时间合法性检查
		if (!FormatDataTool.isVaildTimeStamp(pictureModifiedTime)) {
			pictureModifiedTime = updateTime;
		}
		// 房源创建时间合法性检查
		if (!FormatDataTool.isVaildTimeStamp(createTime)) {
			if (pictureModifiedTime != null && pictureModifiedTime.before(updateTime)) {
				createTime = pictureModifiedTime;
			} else {
				createTime = updateTime;
			}
		}
	}

	/**
	 * 返回房源的第一张图片
	 * 
	 * @return
	 */
	public String getFirstPicture() {
		String[] pictureList = listingPictures.split("\\|");
		if (pictureList != null && pictureList.length > 0) {
			return pictureList[0];
		}
		return null;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(super.toString());
		Field[] fields = ListingDataBean.class.getDeclaredFields();
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

	public String getOpenHouseSchedules() {
		return openHouseSchedules;
	}

	public void setOpenHouseSchedules(String openHouseSchedules) {
		this.openHouseSchedules = openHouseSchedules;
	}

	public int getOpenHouseFlag() {
		return openHouseFlag;
	}

	public void setOpenHouseFlag(int openHouseFlag) {
		this.openHouseFlag = openHouseFlag;
	}

	public static void main(String[] args) {
	}

	public String getNeighborhoodName() {
		return neighborhoodName;
	}

	public void setNeighborhoodName(String neighborhoodName) {
		this.neighborhoodName = neighborhoodName;
	}

	public long getNeighborhoodId() {
		return neighborhoodId;
	}

	public void setNeighborhoodId(long neighborhoodId) {
		this.neighborhoodId = neighborhoodId;
	}

	public String getShortSales() {
		return shortSales;
	}

	public void setShortSales(String shortSales) {
		this.shortSales = shortSales;
	}

	public String getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}

	public String getLenderOwned() {
		return lenderOwned;
	}

	public void setLenderOwned(String lenderOwned) {
		this.lenderOwned = lenderOwned;
	}

	public String getDataResource() {
		return dataResource;
	}

	public void setDataResource(String dataResource) {
		this.dataResource = dataResource;
	}

}
