package com.homethy.web.service;

import org.apache.ibatis.annotations.Param;

import com.homethy.web.domain.ResourceBean;

import java.util.List;

public interface ResourceService {

	/**
	 * 插入数据
	 * @return
	 */
	int insertResource(ResourceBean resource);

	int insertResourceSummary(ResourceBean resource);


	/**
	 * 更新一条记录
	 */
	int updateResource(ResourceBean resource);


	/**
	 * 更新一条记录
	 */
	int updateResourcePhoto(ResourceBean resource);

	/**
	 * 查询一条数据
	 * @param id
	 * @return
	 */
	ResourceBean getResource(@Param("id") long id);

	/**
	 * 查询一条数据
	 * @param mlsNumber
	 * @param mlsId
	 * @return
	 */
	ResourceBean getListingResource(@Param("mlsNumber") String mlsNumber, @Param("mlsId") int mlsId);

	/**
	 * 查询多条数据
	 * @param mlsId mls id (allocated by chime)
	 * @param resourceName mls resource sysname
	 * @param className mls resouce class sysname
	 * @param limit 记录条数
	 * @return
	 */
	List<ResourceBean> getResourceList(@Param("mlsId") int mlsId, @Param("resourceName") String resourceName, @Param("className") String className,  @Param("limit") int limit) ;

	List<ResourceBean> getResourceDt(@Param("mlsId") int mlsId, @Param("resourceName") String resourceName, @Param("className") String className) ;


	int deleteResource(@Param("id") long id);

	int undeleteResource(@Param("id") long id);

	int updateResourceSummary(ResourceBean resource);

	}
