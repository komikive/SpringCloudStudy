package com.komikive.cloud.exception;

import com.komikive.cloud.resp.ResultData;
import com.komikive.cloud.resp.ReturnCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)//抛出指定类型的异常时才会执行该方法
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)//指定客户端收到的状态码
	public ResultData<String> exception(Exception e){
		log.error("全局异常处理信息:",e);
		return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
	}
}
