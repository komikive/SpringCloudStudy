package com.komikive.cloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*
测试SentinelResource注解
 */
@RestController
@Slf4j
public class ResourceController {

	@GetMapping("/rest/url")
	public String rest(){
		return "按照rest地址限流";
	}

	//测试使用注解，blockHandler
	@GetMapping("/rest/resource")
	@SentinelResource(value = "resource",blockHandler = "handlerCustom")
	public String resource(){
		return "按照资源名称测试限流 ：)";
	}
	public String handlerCustom(BlockException blockException){
		return "服务不可用，你被限流了T.T，触发了@SentinelResource";
	}


	@GetMapping("/rateLimit/doAction/{p1}")
	@SentinelResource(value = "doActionSentinelResource",
			blockHandler = "doActionBlockHandler", fallback = "doActionFallback")
	public String doAction(@PathVariable("p1") Integer p1) {
		if (p1 == 0){
			throw new RuntimeException("p1等于零直接异常");
		}
		return "doAction";
	}

	public String doActionBlockHandler(@PathVariable("p1") Integer p1,BlockException e){
		log.error("sentinel配置自定义限流了:{}", e);
		return "sentinel配置自定义限流了";
	}

	public String doActionFallback(@PathVariable("p1") Integer p1,Throwable e){
		log.error("程序逻辑异常了:{}", e);
		return "程序逻辑异常了"+"\t"+e.getMessage();
	}

	@GetMapping("/testHotKey")
	@SentinelResource(value = "testHotKey",blockHandler = "dealHandler_testHotKey")
	public String testHotKey(@RequestParam(value = "p1",required = false) String p1,

							 @RequestParam(value = "p2",required = false) String p2){
		return "------testHotKey";
	}
	public String dealHandler_testHotKey(String p1,String p2,BlockException exception)
	{
		return "-----dealHandler_testHotKey";
	}


}
