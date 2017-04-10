package rest.service;

import common.utils.MallResult;

public interface RedisService {
	//
	MallResult syncContent(long contentCid);

}
