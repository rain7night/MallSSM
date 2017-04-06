package sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.utils.TaotaoResult;
import po.TbAdminUser;
import po.TbUser;

public interface AdminService {
	
	TaotaoResult checkData(String content, Integer type);
	TaotaoResult createUser(TbAdminUser user);
	TaotaoResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);

	TaotaoResult getUserByToken(String token);
}
