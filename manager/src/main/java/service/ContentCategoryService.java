package service;

import java.util.List;

import common.pojo.ContentCatTreeNode;
import common.utils.TaotaoResult;

public interface  ContentCategoryService {
	
	List<ContentCatTreeNode>  getCategoryList(long parentId);
	
	TaotaoResult  insertContentCategory(long parentId, String name);

	/**
	 * 删除节点
	 * 
	 * @param parentId
	 * @param id
	 * @return
	 */
	TaotaoResult deleteContentCategory(Long parentId, Long id);

	/**
	 * 更新节点信息
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	TaotaoResult updateContentCategory(Long id, String name);

	

}
