package com.qianmi.books.exception;

/**
 * 自定义异常---非执行异常
 * 
 * @author caowei
 * @date   2011-04-14
 */
public class CheckedException extends Exception {
	private static final long serialVersionUID = 1L;
	

	public CheckedException(){
		super();
	}
	public CheckedException(String msg) {
		super(msg);
	}
	

	public CheckedException(String msg, Throwable e) {
		super(msg, e);
	}

	
}
