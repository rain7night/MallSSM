package portal.service;


import portal.pojo.ItemInfo;

public interface ItemService {
	ItemInfo  getItemById(long itemId);
	
	String getItemDescById(long itemId);
	
	String getItemParam(long itemId);

}
