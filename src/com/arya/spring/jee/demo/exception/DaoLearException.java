package com.arya.spring.jee.demo.exception;

public class DaoLearException extends Exception {

	private String msg;
	public DaoLearException(String message){
		this.msg = message;
	}
	public String getMessage(){
		return msg;
	}
	public String toString(){
		return "DaoLearException"+msg;
	}
}
