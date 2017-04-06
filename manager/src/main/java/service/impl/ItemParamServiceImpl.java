package service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import common.pojo.EUDResult;
import common.utils.TaotaoResult;
import mapper.TbItemCatMapper;
import mapper.TbItemMapper;
import mapper.TbItemParamItemMapper;
import mapper.TbItemParamMapper;
import po.TbItem;
import po.TbItemCat;
import po.TbItemCatExample;
import po.TbItemDescExample;
import po.TbItemExample;
import po.TbItemParam;
import po.TbItemParamExample;
import po.TbItemParamExample.Criteria;
import po.TbItemParamItem;
import po.TbItemParamItemExample;
import service.ItemParamService;


//商品规格参数模板
@Service
public class ItemParamServiceImpl implements ItemParamService{

	@Autowired
	private TbItemParamMapper itemParamMapper;
	@Autowired
	private TbItemMapper  itemMapper;
	@Autowired
	private TbItemParamItemMapper  itemParamItemMapper;
	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	
	@Override
	public TaotaoResult getItemParamByCid(long cid) {
		TbItemParamExample example=new TbItemParamExample();
		Criteria  criteria=example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		//判断是否查询到结果
		if(list!=null && list.size()>0){
			return TaotaoResult.ok(list.get(0));
		}
		return TaotaoResult.ok();
	}


	//规格参数模板
	@Override
	public TaotaoResult insertItemParam(TbItemParam itemParam) {
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		
		itemParamMapper.insert(itemParam);

		return TaotaoResult.ok();
	}

	//分页查询
	@Override
	public EUDResult getItemList(int page, int rows) {
		TbItemParamExample example=new TbItemParamExample();
		TbItemCatExample example1=new TbItemCatExample();
		PageHelper.startPage(page, rows);
		
		EUDResult result=new EUDResult();
		
		List<TbItemParam> list = itemParamMapper.selectByExample(example);
		/*if(list!=null && list.size()>0){
			for (TbItemParam item : list) {
				Long itemId = item.getItemCatId();
				cn.tf.taotao.po.TbItemCatExample.Criteria createCriteria = example1.createCriteria();
				createCriteria.andIdEqualTo(itemId);
				List<TbItemCat> list1 = itemCatMapper.selectByExample(example1);
				
				result.setRows(list1);
				
			}

		}*/
		result.setRows(list);
		
		PageInfo<TbItemParam>  pageInfo=new PageInfo<TbItemParam>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}


	@Override
	public TbItemParamItem listParamDesc(Long id) {
		
		TbItemExample example=new TbItemExample();
		po.TbItemExample.Criteria createCriteria = example.createCriteria();
		createCriteria.andIdEqualTo(id);
		
		List<TbItem> list = itemMapper.selectByExample(example);
		if(list!=null && list.size()>0){
			TbItem item=list.get(0);
			Long cid = item.getCid();
			
			//通过cid查询tb_item_cat的id
			TbItemParamExample example1=new TbItemParamExample();
			po.TbItemParamExample.Criteria createCriteria1 = example1.createCriteria();
			createCriteria1.andItemCatIdEqualTo(cid);
			
			List<TbItemParam> list1 = itemParamMapper.selectByExample(example1);
			if(list1!=null && list1.size()>0){
				TbItemParam item1=list1.get(0);
				Long id2 = item1.getId();
				
				//通过id2取规格参数
				TbItemParamItem result = itemParamItemMapper.selectByPrimaryKey(id2);
				return result;
			}
			
		}
		return null;
	}


	@Override
	public TaotaoResult deleteParam(String ids) {
		try {
			String[] idsArray = ids.split(",");
			List<Long> values = new ArrayList<Long>();
			for(String id : idsArray) {
				values.add(Long.parseLong(id));
			}
			TbItemParamExample e = new TbItemParamExample();
			TbItemParamExample.Criteria c = e.createCriteria();
			c.andIdIn(values);
			itemParamMapper.deleteByExample(e);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return TaotaoResult.ok();
	}

}
