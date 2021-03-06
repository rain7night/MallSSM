package portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import common.utils.HttpClientUtil;
import common.utils.MallResult;
import po.TbUser;
import portal.service.UserService;

@Service
public class UserServiceImpl implements  UserService{

	@Value("${SSO_BASE_URL}")
	public String SSO_BASE_URL;
	
	@Value("${SSO_USER_TOKEN}")
	private String SSO_USER_TOKEN;
	
	@Value("${SSO_PAGE_LOGIN}")
	public String SSO_PAGE_LOGIN;
	
	@Value("${SSO_INTERCEPTOR}")
	public String SSO_INTERCEPTOR;
	
	@Value("${TAOTAO_MAIN}")
	public String TAOTAO_MAIN;
	
	
	@Override
	public TbUser getUserByToken(String token) {
		
		try {
			String json=HttpClientUtil.doGet(SSO_BASE_URL+SSO_USER_TOKEN+token);
			//把json转换为taotaoresult
			MallResult result= MallResult.formatToPojo(json, TbUser.class);
			if(result.getStatus()==200){
				TbUser  user=(TbUser) result.getData();
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
