package order.pojo;

import java.util.List;

import po.TbOrder;
import po.TbOrderItem;
import po.TbOrderShipping;

public class Order extends TbOrder{
	private List<TbOrderItem>  orderItems;
	private TbOrderShipping orderShipping;
	public List<TbOrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<TbOrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public TbOrderShipping getOrderShipping() {
		return orderShipping;
	}
	public void setOrderShipping(TbOrderShipping orderShipping) {
		this.orderShipping = orderShipping;
	}

}
