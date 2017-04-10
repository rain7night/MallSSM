package rest.service;

import common.utils.MallResult;

public interface ItemService {
	
	MallResult getItemBaseInfo(long itemId);
	MallResult getItemDesc(long itemId);
	
	MallResult getItemParam(long itemId);
	
	
}
