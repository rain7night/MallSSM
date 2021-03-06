package controller;

import common.pojo.EUDResult;
import common.utils.MallResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import po.TbItemParam;
import po.TbItemParamItem;
import service.ItemParamService;

//参数模板，返回json数据
@Controller
@RequestMapping("/item/param")
public class ItemParamController {
	
	
	@Autowired
	private ItemParamService itemParamService;
	
	
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public MallResult getItemParamByCid(@PathVariable Long itemCatId){
		MallResult result=itemParamService.getItemParamByCid(itemCatId);
		return result;
	}
	
	
	//接收cid，规格参数模板
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public MallResult inserTaotaoResult(@PathVariable Long cid, String paramData){
		TbItemParam itemParam=new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		MallResult result = itemParamService.insertItemParam(itemParam);
		return result;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public EUDResult getItemList(Integer page,Integer rows){
		EUDResult result = itemParamService.getItemList(page, rows);
		return result;
	}
	
	
	//加载商品规格
	@RequestMapping("/item/query/{id}")
	@ResponseBody
	public TbItemParamItem listItemDesc(@PathVariable Long id) {
		return itemParamService.listParamDesc(id);
	}
		
	
	//删除商品规格参数模板
	@RequestMapping("/delete")
	@ResponseBody
	public MallResult deleteParam(String ids){
		return itemParamService.deleteParam(ids);
		
	}
	

}
