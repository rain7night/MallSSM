package search.service;

import org.apache.solr.client.solrj.SolrQuery;

import search.pojo.SearchResult;

public interface SearchService {

	SearchResult  search(String queryString, int page, int rows)  throws Exception;
}
