package rest.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import common.utils.JsonUtils;
import common.utils.MallResult;
import mapper.TbItemDescMapper;
import mapper.TbItemMapper;
import mapper.TbItemParamItemMapper;
import po.TbItem;
import po.TbItemDesc;
import po.TbItemParamItem;
import po.TbItemParamItemExample;
import po.TbItemParamItemExample.Criteria;
import rest.dao.JedisClient;
import rest.service.ItemService;

//商品信息管理
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private JedisClient jedisClient;
	


	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;

	@Value("${REDIS_ITEM_EXPIRE}")
	private Integer REDIS_ITEM_EXPIRE;

	@Autowired
	private TbItemDescMapper itemDescMapper;

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	@Override
	public MallResult getItemBaseInfo(long itemId) {
		// 添加缓存
		// 从缓存中取商品信息
		try {
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId
					+ ":base");
			if (!StringUtils.isBlank(json)) {
				// 把json转换为pojo
				TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
				return MallResult.ok(item);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		TbItem item = itemMapper.selectByPrimaryKey(itemId);

		// 把商品信息写入缓存
		try {
			jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":base",
					JsonUtils.objectToJson(item));
			// 设置key的有效期
			jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":base",
					REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MallResult.ok(item);
	}

	@Override
	public MallResult getItemDesc(long itemId) {
		// 添加缓存
		// 从缓存中取商品信息
		try {
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId
					+ ":desc");
			if (!StringUtils.isBlank(json)) {
				// 把json转换为pojo
				TbItemDesc itemDesc = JsonUtils.jsonToPojo(json,
						TbItemDesc.class);
				return MallResult.ok(itemDesc);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);

		// 把商品信息写入缓存
		try {
			jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":desc",
					JsonUtils.objectToJson(itemDesc));
			// 设置key的有效期
			jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":desc",
					REDIS_ITEM_EXPIRE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return MallResult.ok(itemDesc);
	}

	// 根据id查询规格参数
	@Override
	public MallResult getItemParam(long itemId) {

		// 从缓存中取商品信息
		try {
			String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId
					+ ":param");
			if (!StringUtils.isBlank(json)) {
				// 把json转换为pojo
				TbItemParamItem paramItem = JsonUtils.jsonToPojo(json,
						TbItemParamItem.class);
				return MallResult.ok(paramItem);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		TbItemParamItemExample example = new TbItemParamItemExample();

		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);

		List<TbItemParamItem> list = itemParamItemMapper
				.selectByExampleWithBLOBs(example);
		TbItemParamItem paramItem;
		if (list != null && list.size() > 0) {
			paramItem = list.get(0);

			// 把商品信息写入缓存
			try {
				jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":param",
						JsonUtils.objectToJson(paramItem));
				// 设置key的有效期
				jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":param",
						REDIS_ITEM_EXPIRE);
			} catch (Exception e) {
				e.printStackTrace();
			}

			return MallResult.ok(paramItem);
		}

		return MallResult.build(400, "没有该商品规格信息");

	}

}
