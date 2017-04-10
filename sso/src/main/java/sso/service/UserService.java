package sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.utils.MallResult;
import po.TbUser;

public interface UserService {
	
	MallResult checkData(String content, Integer type);
	MallResult createUser(TbUser user);
	MallResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);

	MallResult getUserByToken(String token);
	
	/*
	 * 
	 * 退出登录
	 */
	MallResult userLogout(String token);
}
