package com.fundoonotes.noteservice;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Listener {
	
	@JmsListener(destination="FundooNoteQueue")
	public void consume(Note note) {
		System.out.println(note);
		
	 }
}
