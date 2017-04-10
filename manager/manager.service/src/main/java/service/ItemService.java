package service;

import common.pojo.EUDResult;
import common.utils.MallResult;
import po.TbItem;
import po.TbItemDesc;
import po.TbItemParamItem;

public interface  ItemService {
	TbItem getItemById(long itemId);

	EUDResult getItemList(int page, int rows);
	
	MallResult createItem(TbItem item, String desc, String itemparam) throws Exception;

	MallResult deleteItem(String ids);

	TbItemDesc listItemDesc(Long id);

	/**
	 * 
	 * 更新商品
	 * @param item
	 * @param desc
	 * @param itemParamss
	 * @return
	 */
	
	MallResult updateItem(TbItem item, TbItemDesc desc, TbItemParamItem itemParamss);

	/**
	 * 
	 * 下架商品
	 * @param ids
	 * @return
	 */
	MallResult instockItem(String ids);

	//上架
	MallResult reshelfItem(String ids);
}
