package com.komikive.cloud.controller;

import com.komikive.cloud.entities.PayDTO;
import com.komikive.cloud.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/consumer/pay")
public class OrderLoadBalancerController {
	private final String PaymentSrv_URL="http://cloud-payment-service";


	@Resource
	private RestTemplate restTemplate;

	@PostMapping("/add")
	public ResultData addOrder(@RequestBody PayDTO payDTO){
		return restTemplate.postForObject(PaymentSrv_URL+"/pay/add",payDTO, ResultData.class);
	}

	@GetMapping("/get/{id}")
	public ResultData getPayInfo(@PathVariable("id") Integer id){
		return restTemplate.getForObject(PaymentSrv_URL+"/pay/get/"+id, ResultData.class,id);
	}

	@DeleteMapping("/del/{id}")
	public ResultData deleteById(@PathVariable("id") Integer id){
		restTemplate.delete(PaymentSrv_URL+"/pay/del/"+id,id);
		return ResultData.success();
	}

	@PutMapping("/update")
	public ResultData update(@RequestBody PayDTO payDTO){
		restTemplate.put(PaymentSrv_URL+"/pay/update",payDTO,ResultData.class);
		return ResultData.success();
	}

	@GetMapping(value = "/get/info")
	private String getInfoByConsul()
	{
		return restTemplate.getForObject(PaymentSrv_URL + "/pay/getInfo", String.class);
	}

	@Resource
	private DiscoveryClient discoveryClient;
	@GetMapping("/discovery")
	public String discovery()
	{
		List<String> services = discoveryClient.getServices();
		for (String element : services) {
			System.out.println(element);
		}

		System.out.println("===================================");

		List<ServiceInstance> instances = discoveryClient.getInstances("cloud-payment-service");
		for (ServiceInstance element : instances) {
			System.out.println(element.getServiceId()+"\t"+element.getHost()+"\t"+element.getPort()+"\t"+element.getUri());
		}

		return instances.get(0).getServiceId()+":"+instances.get(0).getPort();
	}
}
