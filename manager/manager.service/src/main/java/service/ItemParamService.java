package service;

import common.pojo.EUDResult;
import common.utils.TaotaoResult;
import po.TbItemParam;
import po.TbItemParamItem;

public interface ItemParamService {
	
	TaotaoResult getItemParamByCid(long cid);
	TaotaoResult insertItemParam(TbItemParam itemParam);
	
	EUDResult getItemList(int page, int rows);
	
	TbItemParamItem listParamDesc(Long id);
	/*
	 * 删除
	 */
	TaotaoResult deleteParam(String ids);
	
}
