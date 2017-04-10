package rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.utils.ExceptionUtil;
import common.utils.MallResult;
import po.TbContent;
import rest.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {
	
	//发布服务，接收查询参数
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/list/{cid}")
	@ResponseBody
	public MallResult getContentList(@PathVariable Long cid){
		try {
			List<TbContent> list = contentService.getContentList(cid);
			return MallResult.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
			return MallResult.build(500, ExceptionUtil.getStackTrace(e));
		}		
	}
	

}
