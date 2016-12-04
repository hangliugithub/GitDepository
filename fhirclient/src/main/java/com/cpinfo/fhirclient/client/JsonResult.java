package com.cpinfo.fhirclient.client;

import java.io.Serializable;

/**
 * 用于封装JSON返回值数据的值
 * @author Jesse
 *
 * @param <T> 需要返回的数据类型
 */
public class JsonResult <T> implements Serializable{
	
	private static final long serialVersionUID = 262361393733780960L;
	public static final int SUCCESS = 0;
	public static final int ERROR = 1;
	
	private int state;
	private String message;
	private T data;
	
	public JsonResult() {
		
	}

	public JsonResult(int state, String message) {
		super();
		this.state = state;
		this.message = message;
	}
	

	public JsonResult(int state, String message, T data) {
		super();
		this.state = state;
		this.message = message;
		this.data = data;
	}

	public JsonResult(String errorMessage) {
		this(ERROR,errorMessage,null);
	}
	
	public JsonResult(T data) {
		this(SUCCESS,"",data);
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		state = ERROR;
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		state = SUCCESS;
		this.data = data;
	}

	@Override
	public String toString() {
		return "JsonResult [state=" + state + ", message=" + message + ", data=" + data + "]";
	}
	
	

}
