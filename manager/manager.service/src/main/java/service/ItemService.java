package service;

import common.pojo.EUDResult;
import common.utils.TaotaoResult;
import po.TbItem;
import po.TbItemDesc;
import po.TbItemParamItem;

public interface  ItemService {
	TbItem getItemById(long itemId);

	EUDResult getItemList(int page, int rows);
	
	TaotaoResult createItem(TbItem item, String desc, String itemparam) throws Exception;

	TaotaoResult deleteItem(String ids);

	TbItemDesc listItemDesc(Long id);

	/**
	 * 
	 * 更新商品
	 * @param item
	 * @param desc
	 * @param itemParamss
	 * @return
	 */
	
	TaotaoResult updateItem(TbItem item, TbItemDesc desc, TbItemParamItem itemParamss);

	/**
	 * 
	 * 下架商品
	 * @param ids
	 * @return
	 */
	TaotaoResult instockItem(String ids);

	//上架
	TaotaoResult reshelfItem(String ids);
}
