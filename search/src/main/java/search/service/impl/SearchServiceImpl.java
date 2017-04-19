package search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import search.dao.SearchDao;
import search.pojo.SearchResult;
import search.service.SearchService;


//搜索service
@Service
public class SearchServiceImpl implements SearchService {
	@Autowired
	private SearchDao  searchDao;


	@Override
	public SearchResult search(String queryString, int page, int rows) throws Exception {
		
		SolrQuery  query=new SolrQuery();
		//设置查询条件
		query.setQuery(queryString);
		//设置分页
		query.setStart((page-1)*rows);
		query.setRows(rows);
		//设置默认搜索域
		query.set("df", "item_keywords");
		//设置高亮显示
		
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em>");
		//执行查询
		SearchResult  searchResult=searchDao.searchItem(query);
		
		long recordCount= searchResult.getRecordCount(); 
		long pageCount=recordCount/rows;
		if(recordCount%rows>0){
			pageCount++;
		}
		searchResult.setPageCount(pageCount);
		searchResult.setCurPage(page);
		return searchResult;
	}

}
