package com.komikive.cloud.config;


import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

	@Bean
	public Retryer retryer(){
		//默认不重试
		return Retryer.NEVER_RETRY;
		//自定义重试策略，每隔一秒一次，最大3次，初始间隔100ms
//		return new Retryer.Default(100,1,3);
	}

	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}
}
