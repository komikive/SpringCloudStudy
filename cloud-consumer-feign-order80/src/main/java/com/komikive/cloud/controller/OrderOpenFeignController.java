package com.komikive.cloud.controller;

import cn.hutool.core.date.DateUtil;
import com.komikive.cloud.apis.PayFeignApi;
import com.komikive.cloud.entities.PayDTO;
import com.komikive.cloud.resp.ResultData;
import com.komikive.cloud.resp.ReturnCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

//测试openfeign自带的超时控制，重试策略，修改h5客户端，请求压缩，日志打印，
@RestController
public class OrderOpenFeignController {

	@Resource
	private PayFeignApi payFeignApi;

	@PostMapping("/feign/pay/add")
	public ResultData addOrder(@RequestBody PayDTO payDTO){
		return payFeignApi.addPay(payDTO);
	}


	@GetMapping("/feign/pay/get/{id}")
	public ResultData getPayInfo(@PathVariable("id") Integer id){
		ResultData payById=null;
		try{
			System.out.println("开始调用"+ DateUtil.now());
			payById = payFeignApi.getPayById(id);
		}catch (Exception e){
			e.printStackTrace();
			System.out.println(DateUtil.now());
			return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
		}
		return payById;
	}

	@GetMapping("/feign/pay/info")
	public String getInfo(){
		return payFeignApi.getInfo();
	}
}
