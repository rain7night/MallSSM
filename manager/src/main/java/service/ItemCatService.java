package service;

import java.util.List;

import common.pojo.EUTreeNode;

public interface ItemCatService {
	
	List<EUTreeNode>  getCatList(long parentId);

}
