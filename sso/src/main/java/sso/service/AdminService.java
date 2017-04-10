package sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.utils.MallResult;
import po.TbAdminUser;

public interface AdminService {
	
	MallResult checkData(String content, Integer type);
	MallResult createUser(TbAdminUser user);
	MallResult userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);

	MallResult getUserByToken(String token);
}
