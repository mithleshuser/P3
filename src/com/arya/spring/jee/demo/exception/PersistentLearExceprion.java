package com.arya.spring.jee.demo.exception;

public class PersistentLearExceprion extends Throwable {
	private String msg;

	public PersistentLearExceprion(String msg1) {
		this.msg = msg1;
	}
	public String getMessage(){
		return msg;
	}
	@Override
	public String toString() {
		return "PersistentLearExceprion "+msg;
	}
}
