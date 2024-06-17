package com.komikive.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.komikive.cloud.mapper")
@EnableFeignClients
@EnableDiscoveryClient
public class Main2002 {
	public static void main(String[] args) {
		SpringApplication.run(Main2002.class);
	}
}