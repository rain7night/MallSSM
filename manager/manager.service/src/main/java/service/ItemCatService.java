package service;

import common.pojo.EUTreeNode;

import java.util.List;

public interface ItemCatService {
	
	List<EUTreeNode>  getCatList(long parentId);

}
