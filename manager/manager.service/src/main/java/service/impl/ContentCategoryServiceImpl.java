package service.impl;

import common.pojo.ContentCatTreeNode;
import common.utils.MallResult;
import mapper.TbContentCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import po.TbContentCategory;
import po.TbContentCategoryExample;
import po.TbContentCategoryExample.Criteria;
import service.ContentCategoryService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContentCategoryServiceImpl implements  ContentCategoryService{

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	
	@Override
	public List<ContentCatTreeNode> getCategoryList(long parentId) {
		// 根据parentId查询数据库
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = contentCategoryMapper
				.selectByExample(example);
		// 创建返回值list 并包装数据
		List<ContentCatTreeNode> listResult = new ArrayList<ContentCatTreeNode>();
		for (TbContentCategory contentCategory : list) {
			ContentCatTreeNode node = new ContentCatTreeNode();
			node.setId(contentCategory.getId());
			node.setState(contentCategory.getIsParent() ? "closed" : "open");
			node.setText(contentCategory.getName());
			node.setParentId(parentId);
			listResult.add(node);
		}
		return listResult;
	}
	

	@Override
	public MallResult insertContentCategory(long parentId, String name) {
		
		TbContentCategory  contentCategory=new TbContentCategory();
		contentCategory.setName(name);
		contentCategory.setIsParent(false);
		contentCategory.setStatus(1);  //1是正常，2是删除
		contentCategory.setParentId(parentId);
		contentCategory.setSortOrder(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		contentCategoryMapper.insert(contentCategory);
		//查看父节点的isParent列是否为true
		TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
		//判断是否为true
		if(!parentCat.getIsParent()){
			parentCat.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKey(parentCat);
		}
		return MallResult.ok(contentCategory);
	}

	//删除节点
	@Override
	public MallResult deleteContentCategory(Long parentId, Long id) {
		
		contentCategoryMapper.deleteByPrimaryKey(id);
		// 判断父节点下是否还有子节点
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = contentCategoryMapper
				.selectByExample(example);
		// 父节点
		TbContentCategory parentCat = contentCategoryMapper
				.selectByPrimaryKey(parentId);
		// 如果没有子节点，设置为false
		if (list != null && list.size() > 0) {
			parentCat.setIsParent(true);
		} else {
			parentCat.setIsParent(false);
		}
		return MallResult.ok();
	}


	//更新
	@Override
	public MallResult updateContentCategory(Long id, String name) {
		TbContentCategory tcc = contentCategoryMapper.selectByPrimaryKey(id);
		
		TbContentCategory  contentCategory=new TbContentCategory();
		contentCategory.setCreated(tcc.getCreated());
		contentCategory.setId(id);
		contentCategory.setIsParent(tcc.getIsParent());
		contentCategory.setName(name);
		contentCategory.setParentId(tcc.getParentId());
		contentCategory.setSortOrder(tcc.getSortOrder());
		contentCategory.setStatus(tcc.getStatus());
		contentCategory.setUpdated(new Date());
		
		contentCategoryMapper.updateByPrimaryKey(contentCategory);
		return MallResult.ok();
		
		
	}

}
