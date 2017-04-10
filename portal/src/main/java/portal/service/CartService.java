package portal.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.utils.MallResult;
import portal.pojo.CartItem;

public interface CartService {
	MallResult addCartItem(long itemId, int num, HttpServletRequest request, HttpServletResponse response);

	//得到购物车列表
	List<CartItem> getCartItemList(HttpServletRequest request,
                                   HttpServletResponse response);

	//删除
	MallResult deleteCartItem(long itemId, HttpServletRequest request,
                              HttpServletResponse response);

	
	
}
