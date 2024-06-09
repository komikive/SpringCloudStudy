package com.komikive.cloud.controller;

import com.komikive.cloud.apis.PayFeignApi;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
public class OrderCircuitController
{
	@Resource
	private PayFeignApi payFeignApi;

	@GetMapping(value = "/feign/pay/circuit/{id}")
	@CircuitBreaker(name = "cloud-payment-service",
			/*遇到错误就直接降级*/
			fallbackMethod = "myCircuitFallback")
	public String myCircuitBreaker(@PathVariable("id") Integer id)
	{
		return payFeignApi.myCircuit(id);
	}

	//myCircuitFallback就是服务降级后的兜底处理方法
	public String myCircuitFallback(Integer id,Throwable t) {
		// 这里是容错处理逻辑，返回备用结果
		return "myCircuitFallback，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~";
	}

//	/**
//	 *(船的)舱壁,隔离
//	 * @param id
//	 * @return
//	 */
//	@GetMapping(value = "/feign/pay/bulkhead/{id}")
//	@Bulkhead(name = "cloud-payment-service",fallbackMethod = "myBulkheadFallback",
//			/*两种类型，先使用信号量*/
//			type = Bulkhead.Type.SEMAPHORE)
//	public String myBulkhead(@PathVariable("id") Integer id)
//	{
//		return payFeignApi.myBulkhead(id);
//	}
//	//服务降级方法
//	public String myBulkheadFallback(Throwable t)
//	{
//		return "myBulkheadFallback，隔板超出最大数量限制，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~";
//	}

	/**
	 *(船的)舱壁,隔离ThreadPool
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/feign/pay/bulkhead/{id}")
	@Bulkhead(name = "cloud-payment-service",fallbackMethod = "myBulkheadPoolFallback",
			/*两种类型，先使用信号量*/
			type = Bulkhead.Type.THREADPOOL)
	public CompletableFuture<String> myBulkheadThreadPool(@PathVariable("id") Integer id)
	{
		System.out.println(Thread.currentThread().getName()+"\t开始进入-----");
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		System.out.println(Thread.currentThread().getName()+"\t准备离开-----");
		return CompletableFuture.supplyAsync(()->payFeignApi.myBulkhead(id)+"\t Bulkhead.Type.THREADPOOL");
	}

	//服务降级方法
	public CompletableFuture<String> myBulkheadPoolFallback(Integer id,Throwable t)
	{
		return CompletableFuture.supplyAsync(()->"Bulkhead.Type.THREADPOOL系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~");
	}

	@GetMapping(value = "/feign/pay/ratelimit/{id}")
	@RateLimiter(name = "cloud-payment-service",fallbackMethod = "myRatelimitFallback")
	public String myBulkhead(@PathVariable("id") Integer id)
	{
		return payFeignApi.myRatelimit(id);
	}

	public String myRatelimitFallback(Integer id,Throwable t)
	{
		return "你被限流了，禁止访问/(ㄒoㄒ)/~~";
	}

}