package com.komikive.cloud.controller;

import com.komikive.cloud.entities.Pay;
import com.komikive.cloud.entities.PayDTO;
import com.komikive.cloud.resp.ResultData;
import com.komikive.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name="支付微服务模块",description = "支付CRUD")
public class PayController {
	@Resource
	private PayService payService;

	@PostMapping("/pay/add")
	@Operation(summary = "插入",description = "插入支付数据")
	public ResultData addPay(@RequestBody Pay pay){
		System.out.println(pay.toString());
		int add = payService.add(pay);
		return ResultData.success("成功插入记录，返回值："+add);
	}

	@DeleteMapping("/pay/del/{id}")
	public ResultData deletePay(@PathVariable("id") Integer id){
		payService.delete(id);
		return ResultData.success();
	}

	@PutMapping("/pay/update")
	public ResultData updatePay(@RequestBody PayDTO payDTO){
		Pay pay = new Pay();
		BeanUtils.copyProperties(payDTO,pay);
		int i = payService.update(pay);
		return ResultData.success("成功修改记录，返回值："+i);
	}

	@GetMapping("/pay/get/{id}")
	public ResultData<Pay> getById(@PathVariable("id") Integer id){
		if(id==0){
			throw new RuntimeException("id不能为0");
		}
		return ResultData.success(payService.getById(id));
	}

	@GetMapping("/pay/getAll")
	public ResultData<List<Pay>> getAll(){
		return ResultData.success(payService.getAll());
	}

	@Value("${server.port}")
	private String port;

	@GetMapping("/pay/get/info")
	public String getInfoByConsul(@Value("${komikive.info}") String info){
		return "info:"+info+"\tport:"+port;
	}

}
