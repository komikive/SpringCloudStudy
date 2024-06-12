package com.komikive.cloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class CircuitBreakerController {

	/**
	 * 测试--慢调用比例
	 */
	@GetMapping("/testF")
	public String testF(){
		try {
			TimeUnit.SECONDS.sleep(1);
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("----测试熔断规则，慢调用比例");
		return "----测试熔断规则，慢调用比例";
	}

	/**
	 * 新增熔断规则-异常比例
	 * @return
	 */
	@GetMapping("/testG")
	public String testG()
	{
		System.out.println("----测试:新增熔断规则-异常比例 ");
		int age = 10/0;
		return "------testG,新增熔断规则-异常比例 ";
	}

}
