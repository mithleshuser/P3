package com.arya.spring.jee.demo.exception;

public final class ServiceLearException extends Throwable{
	private String msg;
	
	public ServiceLearException(String msg1){
		this.msg=msg1;
	}
	public String getMessage(){
		return msg;
		
	}
	@Override
	public String toString() {
		return "ServiceLearException "+msg;
	}
}
