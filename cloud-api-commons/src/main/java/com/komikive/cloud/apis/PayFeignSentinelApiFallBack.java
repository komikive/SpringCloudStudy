package com.komikive.cloud.apis;

import com.komikive.cloud.resp.ResultData;
import com.komikive.cloud.resp.ReturnCodeEnum;
import org.springframework.stereotype.Component;

@Component
public class PayFeignSentinelApiFallBack implements PayFeignSentinelApi{

	public ResultData getPayByOrderNo(String orderNo) {
		return ResultData.fail(ReturnCodeEnum.RC500.getCode(),"对方服务宕机或不可用，FallBack服务降级o(╥﹏╥)o");
	}
}
