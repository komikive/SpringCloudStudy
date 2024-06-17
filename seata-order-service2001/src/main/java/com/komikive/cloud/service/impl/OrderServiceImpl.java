package com.komikive.cloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.komikive.cloud.apis.AccountFeignApi;
import com.komikive.cloud.apis.StorageFeignApi;
import com.komikive.cloud.entities.Order;
import com.komikive.cloud.mapper.OrderMapper;
import com.komikive.cloud.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper,Order> implements OrderService {
	@Resource
	private OrderMapper orderMapper;
	@Resource
	private StorageFeignApi storageFeignApi;
	@Resource
	private AccountFeignApi accountFeignApi;

	@Override
	@GlobalTransactional(name = "create-order",rollbackFor = Exception.class)
	public void create(Order order) {
		//xid全局事务id的检查
		String xid = RootContext.getXID();
		//1.新建订单
		log.info("---------开始新建订单");
		order.setStatus(0);
		int res = orderMapper.insert(order);
		//插入订单后获取插入的mysql实体对象
		if(res>0){
			log.info("--------->新建订单成功"+order);
			System.out.println();
			//2.扣减库存
			log.info("------------>开始扣减库存count");
			storageFeignApi.decrease(order.getProductId(), order.getCount());

			//3.扣减账户余额
			log.info("------------>订单微服务开始调用Account扣减money");
			accountFeignApi.decrease(order.getUserId(),order.getMoney());
			System.out.println();
			System.out.println("------------>订单微服务调用结束，调用Account账号，做扣减完成");
			//4.修改订单状态
			log.info("------------>修改订单状态");
			order.setStatus(1);

			int status = orderMapper.updateById(order);
			log.info("----------->修改订单状态完成"+status);

		}
		System.out.println();
		log.info("-------------结束新建订单:\t"+"xid:"+xid);

	}
}
