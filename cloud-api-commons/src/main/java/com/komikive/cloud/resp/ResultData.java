package com.komikive.cloud.resp;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResultData<T> {
	private String code;
	private String message;
	private T data;
	private long timestamp;

	public ResultData(){
		this.timestamp=System.currentTimeMillis();
	}

	public static <T> ResultData<T> success(){
		ResultData<T> resultData = new ResultData<>();
		resultData.setCode(ReturnCodeEnum.RC200.getCode());
		resultData.setMessage(ReturnCodeEnum.RC200.getMessage());
		return resultData;
	}

	public static <V> ResultData<V> success(V data){
		ResultData<V> resultData = new ResultData<>();
		resultData.setCode(ReturnCodeEnum.RC200.getCode());
		resultData.setMessage(ReturnCodeEnum.RC200.getMessage());
		resultData.setData(data);
		return resultData;
	}

	public static <T> ResultData<T> fail(String code,String message){
		ResultData<T> resultData = new ResultData<>();
		resultData.setCode(code);
		resultData.setMessage(message);
		resultData.setData(null);
		return resultData;
	}


}
