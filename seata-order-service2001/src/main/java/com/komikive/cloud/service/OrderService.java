package com.komikive.cloud.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.komikive.cloud.entities.Order;

public interface OrderService extends IService<Order> {
	void create(Order order);
}
