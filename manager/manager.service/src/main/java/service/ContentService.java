package service;

import common.pojo.EUDResult;
import common.utils.TaotaoResult;
import po.TbContent;

public interface ContentService {
	/**
	 * 插入操作
	 * 
	 * @param content
	 * @return
	 */
	TaotaoResult insertContent(TbContent content);

	/**
	 * 列表
	 * @param page
	 * @param pageSize
	 * @return
	 */
	EUDResult getContentList(long page, long pageSize);

	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	TaotaoResult deleteContent(String ids);

	/**
	 * 更新操作
	 * 
	 * @param content
	 * @return
	 */
	TaotaoResult updateContent(TbContent content);



}
