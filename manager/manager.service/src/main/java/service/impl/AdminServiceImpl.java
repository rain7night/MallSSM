package service.impl;

import common.utils.HttpClientUtil;
import common.utils.MallResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import po.TbAdminUser;
import service.AdminService;


@Service
public class AdminServiceImpl implements  AdminService{

	@Value("${SSO_BASE_URL}")
	public String SSO_BASE_URL;
	
	@Value("${SSO_USER_TOKEN}")
	private String SSO_USER_TOKEN;
	
	@Value("${SSO_PAGE_LOGIN}")
	public String SSO_PAGE_LOGIN;
	
	@Value("${SSO_INTERCEPTOR}")
	public String SSO_INTERCEPTOR;
	
	
	@Override
	public TbAdminUser getUserByToken(String token) {
		
		try {
			String json=HttpClientUtil.doGet(SSO_BASE_URL+SSO_USER_TOKEN+token);
			//把json转换为taotaoresult
			MallResult result= MallResult.formatToPojo(json, TbAdminUser.class);
			if(result.getStatus()==200){
				TbAdminUser  user=(TbAdminUser) result.getData();
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return null;
	}

}
