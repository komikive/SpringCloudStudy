package com.komikive.cloud.controller;

import com.komikive.cloud.entities.Order;
import com.komikive.cloud.resp.ResultData;
import com.komikive.cloud.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Order")
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