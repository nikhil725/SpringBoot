package com.fundoonotes.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MailService {

	@Autowired
	EmailProperties emailProperties;
	@Autowired
	private MailSender mailSender;
	@Value("${emailAddress}")
    private String emailAddress;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Async
	public boolean sendMail(String to, String subject, String msg) {
		System.out.println("At @async " + Thread.currentThread().getName());
		String base = to;
		boolean flag = false;
		SimpleMailMessage message = new SimpleMailMessage();
		try {
			
			if (emailProperties.getEmailAddress() != null && emailProperties.getEmailAddress() != ""
					&& emailProperties.getEmailAddress().isEmpty() == false
					&& !emailProperties.getEmailAddress().equals("null")) {
				System.out.println("Email  :" + emailProperties.getEmailAddress());
				to = emailProperties.getEmailAddress();
			}

			System.out.println("mail send to --> :" + to);
			message.setFrom(emailProperties.getEmail());
			message.setTo(to);
			message.setSubject(subject);
			message.setText(msg);
			mailSender.send(message);
			flag = true;
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

}
