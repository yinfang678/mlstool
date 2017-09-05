package com.homethy.web.service.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homethy.web.dao.ResourceDAO;
import com.homethy.web.domain.ResourceBean;
import com.homethy.web.service.ResourceService;

import java.util.List;

@Service
public class ResourceImpl implements ResourceService {

	@Autowired
	private ResourceDAO resourceDAO;

	/**
	 * 插入数据
	 * @return
	 */
	public int insertResource(ResourceBean resource){
		return resourceDAO.insertResource(resource);
	}

	public int insertResourceSummary(ResourceBean resource){
		return resourceDAO.insertResource(resource);
	}

	/**
	 * 更新图片
	 */
	public int updateResource(ResourceBean resource){
		return resourceDAO.updateResource(resource);
	}


	public int updateResourcePhoto(ResourceBean resource) {
		return resourceDAO.updateResource(resource);
	}

	/**
	 * 查询一条数据
	 * @param id
	 * @return
	 */
	public ResourceBean getResource(@Param("id") long id){System.out.println(id);
		return resourceDAO.getResource(id);
	}

	/**
	 * 查询一条数据
	 * @param mlsNumber
	 * @param mlsId
	 * @return
	 */
	public ResourceBean getListingResource(@Param("mlsNumber") String mlsNumber, @Param("mlsId") int mlsId){
		return resourceDAO.getListingResource(mlsNumber, mlsId);
	}

	/**
	 * 查询多条数据
	 * @param mlsId mls id (allocated by chime)
	 * @param resourceName mls resource sysname
	 * @param className mls resouce class sysname
	 * @param limit 记录条数
	 * @return
	 */
	public List<ResourceBean> getResourceList(@Param("mlsId") int mlsId, @Param("resourceName") String resourceName, @Param("className") String className,  @Param("limit") int limit){
		return resourceDAO.getResourceList(mlsId, resourceName, className, limit);
	}

	public List<ResourceBean> getResourceDt(@Param("mlsId") int mlsId, @Param("resourceName") String resourceName, @Param("className") String className) {
		return resourceDAO.getResourceDt(mlsId, resourceName, className);
	}

	public int deleteResource(@Param("id") long id) {
		return resourceDAO.deleteResource(id);
	}
	
	public int deleteMlsResource(@Param("mlsid") int mlsid) {
		return resourceDAO.deleteMlsResource(mlsid);
	}

	public int undeleteResource(@Param("id") long id) {
		return resourceDAO.undeleteResource(id);
	}

	public int updateResourceSummary(ResourceBean resource) {
		return resourceDAO.updateResourceSummary(resource);
	}

}
