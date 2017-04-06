package rest.service;

import common.utils.TaotaoResult;

public interface RedisService {
	//
	TaotaoResult syncContent(long contentCid);

}
