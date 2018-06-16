package com.example.demo;

import org.springframework.jms.annotation.JmsListener;

import org.springframework.stereotype.Component;

@Component
public class Consumer {
	
	@JmsListener(destination="MyQueue")
	public void consume(String message) {
		System.out.println("i received "+message);
	 }

}
