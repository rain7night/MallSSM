package service;

import common.pojo.ContentCatTreeNode;
import common.utils.MallResult;

import java.util.List;

public interface  ContentCategoryService {
	
	List<ContentCatTreeNode>  getCategoryList(long parentId);
	
	MallResult insertContentCategory(long parentId, String name);

	/**
	 * 删除节点
	 * 
	 * @param parentId
	 * @param id
	 * @return
	 */
	MallResult deleteContentCategory(Long parentId, Long id);

	/**
	 * 更新节点信息
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	MallResult updateContentCategory(Long id, String name);

	

}
