package search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.utils.MallResult;
import search.service.ItemService;

@Controller
@RequestMapping("/manager")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	/**
	 * 导入商品数据库到索引库
	 */
	@RequestMapping("/importAll")
	@ResponseBody
	public MallResult importAllItems() {
		MallResult result = itemService.importAllItems();
		return result;
	}
}
