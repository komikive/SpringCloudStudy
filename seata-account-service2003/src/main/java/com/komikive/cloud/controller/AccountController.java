package com.komikive.cloud.controller;

import com.komikive.cloud.resp.ResultData;
import com.komikive.cloud.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {
	@Autowired
	private AccountService accountService;

	@RequestMapping("/account/decrease")
	public ResultData decrease(@RequestParam("userId") Long userId, @RequestParam("money") Long money){
		accountService.decrease(userId,money);
		return ResultData.success("扣减账户余额成功！");
	}
}
