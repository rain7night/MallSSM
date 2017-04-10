package portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import common.utils.HttpClientUtil;
import common.utils.MallResult;
import portal.pojo.SearchResult;
import portal.service.SearchService;

@Service
public class SearchServiceImpl implements  SearchService{

	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;
	
	
	//调用服务
	@Override
	public SearchResult search(String queryString, int page) {
		
		Map<String,String>  param=new HashMap<>();
		param.put("q", queryString);
		param.put("page", page+"");
		
		//执行调用服务
		try {
			String json=HttpClientUtil.doGet(SEARCH_BASE_URL,param);
			
			//把字符串转换
			MallResult result= MallResult.formatToPojo(json, SearchResult.class);
		
			if(result.getStatus()==200){
				SearchResult searchResult=(SearchResult) result.getData();
				return searchResult;
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
