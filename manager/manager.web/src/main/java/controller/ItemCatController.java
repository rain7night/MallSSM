package controller;

import common.pojo.EUTreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ItemCatService;

import java.util.List;

//商品分类管理
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	
	@Autowired
	private ItemCatService  itemCatService;
	
	@RequestMapping("/list")
	@ResponseBody
	private List<EUTreeNode>  getCatList(@RequestParam(value="id",defaultValue="0")Long parentId){
		List<EUTreeNode> list= itemCatService.getCatList(parentId);
		return list;
		
	}
	

}
