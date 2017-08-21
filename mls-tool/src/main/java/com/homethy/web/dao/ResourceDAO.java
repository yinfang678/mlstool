package com.homethy.web.dao;

import org.apache.ibatis.annotations.*;

import com.homethy.web.annotation.DataSourceListing;
import com.homethy.web.domain.ResourceBean;

import java.util.List;

@DataSourceListing
public interface ResourceDAO {
	String TABLE_NAME = "resource";
	String ALL_COLS = "id, mls_id, data, resource_key, mls_number, moddt, photo_moddt, status, data_updated, cdn_photo_urls, cdn_photo_moddt, cdn_updated, resource_name, class_name, deleted";

	/**
	 * 插入数据
	 * @return
	 */
	@Options(useGeneratedKeys = true)
	@Insert("insert ignore into "+ TABLE_NAME +" set "
			+ "mls_id=#{mlsId},"
			+ "mls_number=#{mlsNumber},"
			+ "resource_key=#{resourceKey},"
			+ "data=#{data},"
			+ "moddt=#{moddt},"
			+ "photo_moddt=#{photoModdt},"
			+ "status=#{status},"
			+ "data_updated=now(),"
			+ "resource_name=#{resourceName},"
			+ "class_name=#{className}")
	int insertResource(ResourceBean resource);

	// different: no data field
	@Options(useGeneratedKeys = true)
	@Insert("insert ignore into "+ TABLE_NAME +" set "
			+ "mls_id=#{mlsId},"
			+ "mls_number=#{mlsNumber},"
			+ "resource_key=#{resourceKey},"
			+ "moddt=#{moddt},"
			+ "photo_moddt=#{photoModdt},"
			+ "status=#{status},"
			+ "data_updated=now(),"
			+ "resource_name=#{resourceName},"
			+ "class_name=#{className}")
	int insertResourceSummary(ResourceBean resource);



	/**
	 * 更新一条记录
	 */
	@Update("update "+ TABLE_NAME +" set "
			+ "mls_id=#{mlsId},"
			+ "data=#{data},"

			+ "mls_number=#{mlsNumber},"
			+ "resource_key=#{resourceKey},"
			+ "moddt=#{moddt},"
			+ "photo_moddt=#{photoModdt},"
			+ "status=#{status},"

			+ "data_updated=now(),"
			+ "resource_name=#{resourceName},"
			+ "class_name=#{className}"
			+ " where id = #{id}")
	int updateResource(ResourceBean resource);

	/**
	 * 更新一条记录
	 */
	@Update("update "+ TABLE_NAME +" set "
			+ "mls_id=#{mlsId},"

			+ "mls_number=#{mlsNumber},"
			+ "resource_key=#{resourceKey},"
			+ "moddt=#{moddt},"
			+ "photo_moddt=#{photoModdt},"
			+ "status=#{status},"

			+ "data_updated=now(),"
			+ "resource_name=#{resourceName},"
			+ "class_name=#{className}"
			+  "where id = #{id}")
	int updateResourceSummary(ResourceBean resource);

	/**
	 * 更新图片
	 */
	@Update("update "+ TABLE_NAME +" set "
			+ "cdn_photo_urls=#{cdnPhotoUrls},"
			+ "cnd_photo_moddt=#{cdnPhotoModdt},"
			+ "cdn_updated=now()"
			+ " where id = #{id}")
	int updateResourcePhoto(ResourceBean resource);

	/**
	 * 软删除
	 */
	@Update("update "+ TABLE_NAME +" set "
			+ "deleted=now() "
			+ " where id = #{id}")
	int deleteResource(@Param("id") long id);


	/**
	 * 软删除
	 */
	@Update("update "+ TABLE_NAME +" set "
			+ "deleted=NULL "
			+ " where id = #{id}")
	int undeleteResource(@Param("id") long id);

	/**
	 * 查询一条数据
	 * @param id
	 * @return
	 */
	@Select("select * from " + TABLE_NAME +" where id=#{id} ")
	ResourceBean getResource(@Param("id") long id);

	/**
	 * 查询一条数据
	 * @param mlsNumber
	 * @param mlsId
	 * @return
	 */
	@Select("select * from " + TABLE_NAME +" where mls_number=#{mlsNumberr} and mls_id={mlsId}")
	ResourceBean getListingResource(@Param("mlsNumber") String mlsNumber, @Param("mlsId") int mlsId);

	/**
	 * 查询多条数据
	 * @param mlsId mls id (allocated by chime)
	 * @param resourceName mls resource sysname
	 * @param className mls resouce class sysname
	 * @param limit 记录条数
	 * @return
	 */
	@Select(" select * from "
			 + TABLE_NAME
			 + " where mls_id=#{mlsId}"
				+ " and resource_name=#{resourceName}"
				+ " and class_name=#{className}"
				+ " order by id asc limit #{limit}")
	List<ResourceBean> getResourceList(@Param("mlsId") int mlsId, @Param("resourceName") String resourceName, @Param("className") String className,  @Param("limit") int limit) ;


	/**
	 * 查询多条数据
	 * @param mlsId mls id (allocated by chime)
	 * @param resourceName mls resource sysname
	 * @param className mls resouce class sysname
	 * @return
	 */
	@Select(" select id, resource_key, moddt, data_updated from "
			+ TABLE_NAME
			+ " where mls_id=#{mlsId}"
			+ " and resource_name=#{resourceName}"
			+ " and class_name=#{className}"
			+ " order by id desc")
	List<ResourceBean> getResourceDt(@Param("mlsId") int mlsId, @Param("resourceName") String resourceName, @Param("className") String className) ;


}
