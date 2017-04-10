package controller;

import common.pojo.ContentCatTreeNode;
import common.utils.MallResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ContentCategoryService;

import java.util.List;

//内容分类管理
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
	
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping("/list")
	@ResponseBody
	List<ContentCatTreeNode> getCategoryList(@RequestParam(value = "id", defaultValue = "0") long parentId) {
		return contentCategoryService.getCategoryList(parentId);
	}
	

	
	@RequestMapping("/create")
	@ResponseBody
	public MallResult creatContentCatgory(Long parentId, String name){
		MallResult result = contentCategoryService.insertContentCategory(parentId, name);
		return result;
	}
	
	//删除节点
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public MallResult deleteContentCatgory(Long parentId, Long id){
		
		MallResult result = contentCategoryService.deleteContentCategory(parentId, id);
		return result;
	}
	
	//更新节点
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public MallResult updateContentCategory(Long id, String name) {
		return contentCategoryService.updateContentCategory(id, name);
	}
	
}
