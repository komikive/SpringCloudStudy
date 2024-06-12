package com.komikive.cloud.controller;


import com.komikive.cloud.service.FlowLimitService;
import io.micrometer.core.instrument.util.TimeUtils;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.concurrent.TimeUnit;


@RestController
public class FlowLimitController
{

	@GetMapping("/testA")
	public String testA()
	{
		return "------testA";
	}

	@GetMapping("/testB")
	public String testB()
	{
		return "------testB";
	}

	@Resource
	private FlowLimitService flowLimitService;

	@GetMapping("/testC")
	public String testC(){
		flowLimitService.common();
		return "------testC";
	}

	@GetMapping("/testD")
	public String testD(){
		flowLimitService.common();
		return "------testC";
	}

	@GetMapping("/testE")
	public String testE(){
		System.out.println(System.currentTimeMillis()+"TestE 排队等待");
		return "-------------排队等待";
	}
}
