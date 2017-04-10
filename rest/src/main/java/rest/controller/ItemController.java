package rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.utils.MallResult;
import rest.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	//接收商品id,调用service查询商品信息，返回商品对象，使用taotaoResult包装
	@Autowired
	private ItemService itemService;
	

	@RequestMapping("/info/{itemId}")
	@ResponseBody
	public MallResult getItemBaseInfo(@PathVariable Long itemId){
		MallResult result = itemService.getItemBaseInfo(itemId);
		return result;
	}
	
	@RequestMapping("/desc/{itemId}")
	@ResponseBody
	public MallResult getItemDesc(@PathVariable Long itemId){
		MallResult result = itemService.getItemDesc(itemId);
		return result;
	}
	
	@RequestMapping("/param/{itemId}")
	@ResponseBody
	public MallResult getItemParam(@PathVariable Long itemId){
		MallResult result = itemService.getItemParam(itemId);
		return result;
	}

}
