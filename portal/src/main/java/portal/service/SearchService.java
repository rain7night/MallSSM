package portal.service;

import portal.pojo.SearchResult;

public interface SearchService {
	
	SearchResult  search(String queryString, int page);

}
