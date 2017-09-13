package com.homethy.web.domain;

/**
 * 自动化测试用databean
 * 
 * @author huifeng.ma 2017.09.13
 *
 */
public class CompareDataBean {

	private int id;
	private int mlsOrgId;
	private String mlsListingId;
	private String streetAddress;
	private String zipCode;
	private String resource;
	private String mlsData;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getMlsOrgId() {
		return mlsOrgId;
	}
	
	public void setMlsOrgId(int mlsOrgId) {
		this.mlsOrgId = mlsOrgId;
	}
	
	public String getMlsListingId() {
		return mlsListingId;
	}
	public void setMlsListingId(String mlsListingId) {
		this.mlsListingId = mlsListingId;
	}
	
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	
	public String getMlsData() {
		return mlsData;
	}
	public void setMls_data(String mlsData) {
		this.mlsData = mlsData;
	}
	
}
