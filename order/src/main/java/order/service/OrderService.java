package order.service;

import java.util.List;

import common.utils.MallResult;
import po.TbOrder;
import po.TbOrderItem;
import po.TbOrderShipping;

public interface OrderService {
	
	MallResult createOrder(TbOrder order, List<TbOrderItem> itemList, TbOrderShipping orderShipping);

}
