package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import common.pojo.EUTreeNode;
import mapper.TbItemCatMapper;
import po.TbItemCat;
import po.TbItemCatExample;
import po.TbItemCatExample.Criteria;
import po.TbItemExample;
import service.ItemCatService;


@Service
public class ItemCatServiceImpl implements ItemCatService{

	@Autowired
	private TbItemCatMapper  itemCatMapper;
	
	
	@Override
	public List<EUTreeNode> getCatList(long parentId) {
		TbItemCatExample  example=new TbItemCatExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andParentIdEqualTo(parentId);
		//根据条件查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		List<EUTreeNode>  resultList=new ArrayList<>();
		//把列表转换为treenodelist
		for (TbItemCat tbItemCat : list) {
			EUTreeNode node=new EUTreeNode();
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			node.setState(tbItemCat.getIsParent()?"closed":"open");
			resultList.add(node);
		}
		return resultList;
		
	}


}
