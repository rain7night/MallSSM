package order.service;

import java.util.List;

import common.utils.TaotaoResult;
import po.TbOrder;
import po.TbOrderItem;
import po.TbOrderShipping;

public interface OrderService {
	
	TaotaoResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping);

}
