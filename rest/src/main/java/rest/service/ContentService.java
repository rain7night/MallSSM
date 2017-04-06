package rest.service;

import java.util.List;

import po.TbContent;

public interface ContentService {
	
	List<TbContent>  getContentList(long contentCid);

}
