package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.pojo.EUDResult;
import common.utils.TaotaoResult;
import po.TbContent;
import service.ContentService;

@Controller
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/content/save")
	@ResponseBody
	public TaotaoResult insertContent(TbContent content){
		TaotaoResult result = contentService.insertContent(content);
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
	public TaotaoResult deleteContent(String ids){
		return contentService.deleteContent(ids);
	}
	
	//更新
	@RequestMapping("/rest/content/edit")
	@ResponseBody
	public TaotaoResult updateItem(TbContent content){
		TaotaoResult result=contentService.updateContent(content);
		return result;
	}

}
