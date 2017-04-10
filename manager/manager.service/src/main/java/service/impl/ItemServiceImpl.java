package service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import common.pojo.EUDResult;
import common.utils.ExceptionUtil;
import common.utils.IDUtils;
import common.utils.MallResult;
import mapper.TbItemDescMapper;
import mapper.TbItemMapper;
import mapper.TbItemParamItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import po.*;
import po.TbItemExample.Criteria;
import service.ItemService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private TbItemMapper  itemMapper;
	
	@Autowired
	private TbItemDescMapper itemDescMapper;
	
	@Autowired
	private TbItemParamItemMapper  itemParamItemMapper;
	
	
	@Override
	public TbItem getItemById(long itemId) {
		//主键查询
		//TbItem item =itemMapper.selectByPrimaryKey(itemId);
		
		//根据条件查询
		TbItemExample example=new TbItemExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andIdEqualTo(itemId);
		
		List<TbItem> list = itemMapper.selectByExample(example);
		if(list!=null && list.size()>0){
			TbItem item=list.get(0);
			return item;
		}
		return null;
	
	}


	
	//商品列表查询
	@Override
	public EUDResult getItemList(int page, int rows) {
			TbItemExample example=new TbItemExample();
			PageHelper.startPage(page, rows);
			List<TbItem> list = itemMapper.selectByExample(example);
	
			EUDResult result=new EUDResult();
			result.setRows(list);
			PageInfo<TbItem>  pageInfo=new PageInfo<>(list);
			result.setTotal(pageInfo.getTotal());
			return result;
	}



	@Override
	public MallResult createItem(TbItem item, String desc, String itemparam) throws Exception {
		//生成商品id
		Long itemId=IDUtils.genItemId();
		item.setId(itemId);
		item.setStatus((byte)1);  //商品的状态
		item.setCreated(new Date());
		item.setUpdated(new Date());
		itemMapper.insert(item);
		//添加商品描述
		MallResult result=insertItemDesc(itemId, desc);
		if(result.getStatus()!=200){
			throw new Exception();
		}
		//添加规格参数
		result=insertItemParamItem(itemId, itemparam);
		if(result.getStatus()!=200){
			throw new Exception();
		}
		return MallResult.ok();
	}

	private MallResult insertItemDesc(Long itemId, String desc){
		
		TbItemDesc itemDesc=new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		
		itemDescMapper.insert(itemDesc);
		return MallResult.ok();
				
	}
	
	//添加规格参数
	private MallResult insertItemParamItem(Long itemId, String itemparam){
		TbItemParamItem  itemParamItem=new TbItemParamItem();
		itemParamItem.setCreated(new Date());
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemparam);
		itemParamItem.setUpdated(new Date());
		
		itemParamItemMapper.insert(itemParamItem);
		return MallResult.ok();
		
	}



	//删除商品
	@Override
	public MallResult deleteItem(String ids) {
		try {
			String[] idsArray = ids.split(",");
			List<Long> values = new ArrayList<Long>();
			for(String id : idsArray) {
				values.add(Long.parseLong(id));
			}
			TbItemExample e = new TbItemExample();
			TbItemExample.Criteria c = e.createCriteria();
			c.andIdIn(values);
		
			List<TbItem> list = itemMapper.selectByExample(e);
			if(list!=null && list.size()>0){
				TbItem item=list.get(0);
				item.setStatus((byte)3);
				itemMapper.updateByExample(item, e);
			}
			return MallResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		/*try {
			String[] idsArray = ids.split(",");
			List<Long> values = new ArrayList<Long>();
			for(String id : idsArray) {
				values.add(Long.parseLong(id));
			}
			TbItemExample e = new TbItemExample();
			TbItemExample.Criteria c = e.createCriteria();
			c.andIdIn(values);
			itemMapper.deleteByExample(e);
			
			
			TbItemDescExample de = new TbItemDescExample();
			cn.tf.taotao.po.TbItemDescExample.Criteria dc = de.createCriteria();
			dc.andItemIdIn(values);
			itemDescMapper.deleteByExample(de);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return TaotaoResult.ok(); */
	}



	//加载商品描述
	@Override
	public TbItemDesc listItemDesc(Long id) {
		return itemDescMapper.selectByPrimaryKey(id);
	}


	//更新
	@Override
	public MallResult updateItem(TbItem item, TbItemDesc desc, TbItemParamItem itemParams) {
		try {
			//更新商品
			TbItemExample e = new TbItemExample();
			Criteria c = e.createCriteria();
			c.andIdEqualTo(item.getId());
			
			TbItem tbItem = itemMapper.selectByPrimaryKey(item.getId());
			item.setCreated(tbItem.getCreated());
			item.setUpdated(new Date());
			item.setStatus((byte)1);
			itemMapper.updateByExample(item, e);
			
			//更新商品描述
			TbItemDescExample de = new TbItemDescExample();
			po.TbItemDescExample.Criteria criteria = de.createCriteria();
			criteria.andItemIdEqualTo(item.getId());
			desc.setItemId(item.getId());
			
			desc.setCreated(tbItem.getCreated());
			desc.setUpdated(new Date());
			itemDescMapper.updateByExample(desc, de);
			
			//更新规格
			/*TaotaoResult result=updateItemParamItem(itemId, itemparam);
			if(result.getStatus()!=200){
				throw new Exception();
			}
			return TaotaoResult.ok();*/
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return MallResult.build(500, ExceptionUtil.getStackTrace(e));
		}
		return MallResult.ok();
		
	}


	//下架商品
	@Override
	public MallResult instockItem(String ids) {
		
		try {
			String[] idsArray = ids.split(",");
			List<Long> values = new ArrayList<Long>();
			for(String id : idsArray) {
				values.add(Long.parseLong(id));
			}
			TbItemExample e = new TbItemExample();
			TbItemExample.Criteria c = e.createCriteria();
			c.andIdIn(values);
		
			List<TbItem> list = itemMapper.selectByExample(e);
			if(list!=null && list.size()>0){
				TbItem item=list.get(0);
				item.setStatus((byte)2);
				itemMapper.updateByExample(item, e);
			}
			return MallResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}



	@Override
	public MallResult reshelfItem(String ids) {
		try {
			String[] idsArray = ids.split(",");
			List<Long> values = new ArrayList<Long>();
			for(String id : idsArray) {
				values.add(Long.parseLong(id));
			}
			TbItemExample e = new TbItemExample();
			TbItemExample.Criteria c = e.createCriteria();
			c.andIdIn(values);
			List<TbItem> list = itemMapper.selectByExample(e);
			if(list!=null && list.size()>0){
				TbItem item=list.get(0);
				item.setStatus((byte)1);
				itemMapper.updateByExample(item, e);
			}
			return MallResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

}
