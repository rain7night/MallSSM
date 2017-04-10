package service;

import common.pojo.EUDResult;
import common.utils.MallResult;
import po.TbItemParam;
import po.TbItemParamItem;

public interface ItemParamService {
	
	MallResult getItemParamByCid(long cid);
	MallResult insertItemParam(TbItemParam itemParam);
	
	EUDResult getItemList(int page, int rows);
	
	TbItemParamItem listParamDesc(Long id);
	/*
	 * 删除
	 */
	MallResult deleteParam(String ids);
	
}
