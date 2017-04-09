package service;

import po.TbAdminUser;


public interface AdminService {
	
	TbAdminUser  getUserByToken(String token);

}
