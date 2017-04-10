package controller;

import common.pojo.EUDResult;
import common.utils.MallResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import po.TbContent;
import service.ContentService;

@Controller
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/content/save")
	@ResponseBody
	public MallResult insertContent(TbContent content){
		MallResult result = contentService.insertContent(content);
		return result;
	}
	
	
	//加载列表
	@RequestMapping("/content/query/list")
	@ResponseBody
	public EUDResult getContentList(Long page, Long rows){
		EUDResult result = contentService.getContentList(page, rows);
		return result;
	}
	
	//删除
	@RequestMapping("/content/delete")
	@ResponseBody
	public MallResult deleteContent(String ids){
		return contentService.deleteContent(ids);
	}
	
	//更新
	@RequestMapping("/rest/content/edit")
	@ResponseBody
	public MallResult updateItem(TbContent content){
		MallResult result=contentService.updateContent(content);
		return result;
	}

}
