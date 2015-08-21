package com.qianmi.books.exception;

/**
 * 自定义异常---非执行异常
 * 
 * @author caowei
 * @date   2011-04-14
 */
public class CheckedException extends Exception {
	private static final long serialVersionUID = 1L;
	
	/* 错误码,用于返回接口code */
	private String errCode;
	
	public CheckedException(){
		super();
	}
	public CheckedException(String errCode, String msg) {
		super(msg);
		this.errCode = errCode;
	}
	
	public CheckedException(String errCode, Throwable e) {
		super(e);
		this.errCode = errCode;
	}
	
	public CheckedException(String errCode, String msg, Throwable e) {
		super(msg, e);
		this.errCode = errCode;
	}
	
	
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
}
