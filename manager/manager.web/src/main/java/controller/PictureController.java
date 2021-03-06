package controller;

import common.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import service.PictureService;

import java.util.Map;

@Controller
public class PictureController {
	
	@Autowired
	private PictureService  pictureService;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String  pictureUpload(MultipartFile  uploadFile){
		Map result=pictureService.uploadPicture(uploadFile);
		
		//把result转换为json格式的字符串
		
		String json=JsonUtils.objectToJson(result);
		
		return json;
	}


}
