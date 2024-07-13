package com.komikive.cloud.service.impl;

import com.komikive.cloud.mapper.AccountMapper;
import com.komikive.cloud.service.AccountService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {
	@Resource
	private AccountMapper accountMapper;

	@Override
	public void decrease(Long userId, Long money) {
		log.info("---------->开始扣减用户余额");
		accountMapper.decrease(userId,money);
		//模拟异常
//		timeout();
//		int age=10/0;
		log.info("---------->扣减完成");
	}

	private static void timeout(){
		try {
			TimeUnit.SECONDS.sleep(65);
		}catch (InterruptedException e){
			e.printStackTrace();
		}
	}
}
