package com.komikive.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.komikive.cloud.mapper")
@EnableFeignClients
public class Main2001 {
	public static void main(String[] args) {
		SpringApplication.run(Main2001.class);
	}
}