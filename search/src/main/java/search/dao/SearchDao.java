package search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import search.pojo.SearchResult;

public interface SearchDao {
	
	SearchResult searchItem(SolrQuery solrQuery) throws Exception;


}
