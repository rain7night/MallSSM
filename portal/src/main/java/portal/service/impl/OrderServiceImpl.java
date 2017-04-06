package portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import common.utils.HttpClientUtil;
import common.utils.JsonUtils;
import common.utils.TaotaoResult;
import portal.pojo.Order;
import portal.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Value("${ORDER_BASE_URL}")
	private String ORDER_BASE_URL;
	@Value("${ORDER_CREATE_URL}")
	private String ORDER_CREATE_URL;
	
	@Override
	public String createOrder(Order order) {
		//调用order的服务
		String json = HttpClientUtil.doPostJson(ORDER_BASE_URL+ORDER_CREATE_URL, JsonUtils.objectToJson(order));
		
		TaotaoResult taotaoResult = TaotaoResult.format(json);
		
		if(taotaoResult.getStatus()==200){
			Object orderId = taotaoResult.getData();
			return orderId.toString();
		}
		return "";
	}


}
