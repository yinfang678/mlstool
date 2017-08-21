package com.homethy.web.domain;

/**
 * 房源的Agent信息
 * 
 * @author wu.chen 2017.7.22
 *
 */
public class ResourceBean {
	private long id;
	private int mlsId;
	private String data;

	private String mlsNumber;
	private String resourceKey;
	private String moddt;
	private String photoModdt;
	private String status;

	private String dataUpdated;

	private String cdnPhotoUrls;
	private String cdnPhotoModdt;


	private String cdnUpdated;

	private String resourceName;
	private String className;

	private String deleted;


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCdnPhotoModdt() {
		return cdnPhotoModdt;
	}

	public void setCdnPhotoModdt(String cdnPhotoModdt) {
		this.cdnPhotoModdt = cdnPhotoModdt;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getMlsId() {
		return mlsId;
	}

	public void setMlsId(int mlsId) {
		this.mlsId = mlsId;
	}

	public String getMlsNumber() {
		return mlsNumber;
	}

	public void setMlsNumber(String mlsNumber) {
		this.mlsNumber = mlsNumber;
	}

	public String getResourceKey() {
		return resourceKey;
	}

	public void setResourceKey(String resourceKey) {
		this.resourceKey = resourceKey;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getModdt() {
		return moddt;
	}

	public void setModdt(String moddt) {
		this.moddt = moddt;
	}

	public String getDataUpdated() {
		return dataUpdated;
	}

	public void setDataUpdated(String dataUpdated) {
		this.dataUpdated = dataUpdated;
	}

	public String getCdnPhotoUrls() {
		return cdnPhotoUrls;
	}

	public void setCdnPhotoUrls(String cdnPhotoUrls) {
		this.cdnPhotoUrls = cdnPhotoUrls;
	}

	public String getPhotoModdt() {
		return photoModdt;
	}

	public void setPhotoModdt(String photoModdt) {
		this.photoModdt = photoModdt;
	}

	public String getCdnUpdated() {
		return cdnUpdated;
	}

	public void setCdnUpdated(String cdnUpdated) {
		this.cdnUpdated = cdnUpdated;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}



}
