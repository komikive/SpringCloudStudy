package com.komikive.cloud.controller;

import com.komikive.cloud.entities.Order;
import com.komikive.cloud.resp.ResultData;
import com.komikive.cloud.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


@RestController
public class OrderController {

	@Resource
	private OrderService orderService;

	/**
	 * 创建订单
	 */
	@GetMapping ("/order/create")
	public ResultData create(Order order)
	{
		orderService.create(order);
		return ResultData.success(order);
	}
}