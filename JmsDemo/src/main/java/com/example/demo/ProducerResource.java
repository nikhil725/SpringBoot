package com.example.demo;

import javax.jms.Queue;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/publish")
public class ProducerResource {
		
	@Autowired
	Queue queue;
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@GetMapping( "/{message}")
	public String publish(@PathVariable ("message") final String message)
	 {
		jmsTemplate.convertAndSend(queue, message);//destn,msg
		
		return "hi am nikhil ";
	 }

}
