package portal.service;

import po.TbUser;


public interface UserService {
	
	TbUser  getUserByToken(String token);

}
