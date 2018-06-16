package com.bridgeit.todo.user.service;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.todo.user.dao.IUserDao;
import com.bridgeit.todo.user.model.User;
import com.bridgeit.todo.util.Mail;
import com.bridgeit.todo.util.Token;
import com.bridgeit.todo.util.Validation;

public class UserServiceImpl implements IUserService {
	@Autowired
	IUserDao userDao;

	@Autowired
	BCryptPasswordEncoder encoder;

	@Transactional
	public String addUser(User user, HttpServletRequest req) {
		System.out.println("In side add user service");

		String msg = Validation.userValidation(user.getName(), user.getEmail(), user.getPassword());

		if (msg != null) {
			return msg;
		}

		System.out.println("URl... " + req.getRequestURL());

		String hashcode = encoder.encode(user.getPassword());
		user.setPassword(hashcode);
		//		String randomID = UUID.randomUUID().toString();
		//		user.setRandomId(randomID);
		int id = userDao.addUser(user);
		System.out.println("My id is..."+ id);
		String token = Token.generateToken(id);
		System.out.println("my Token.... "+token);

		int id1 = Token.getId(token);
		System.out.println("My id via JWT token..."+ id);

		//user.setName("abc");

		String url = req.getRequestURL().toString().substring(0, req.getRequestURL().lastIndexOf("/"))
				+ "/activateUser/" + token ;


		System.out.println("emailID.." + user.getEmail());
		String mailTo = user.getEmail();
		String message = url;
		String subject = "link to activate your account";

		Mail.sendMail(mailTo,message,subject);

		return null;

	}

	@Transactional
	public String validateUser(User user) {

		User user2 = userDao.getUserByEmaiId(user.getEmail());

		System.out.println("plainText " +user.getPassword());
		System.out.println("hashCode " +user2.getPassword());

		if(user2!=null && BCrypt.checkpw(user.getPassword(), user2.getPassword()) == true)
		{

			//			 int id = user2.getId();
			String token = Token.generateToken(user2.getId());
			System.out.println("Generated token.."+token);

			return token;
		}
		return null;	
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public User getUserByEmaiId(String email) {
		System.out.println("service get user");
		User userInformation = userDao.getUserByEmaiId(email);
		return userInformation;
	}

	@Transactional(propagation=Propagation.SUPPORTS)
	public User getUserById(int id) {

		User userDetails = userDao.getUserById(id);
		return userDetails;
	}

	@Transactional
	public boolean forgotPassword(User user, HttpServletRequest req) {

		User userInformation = userDao.getUserByEmaiId(user.getEmail());
		
		if(userInformation!= null) {

			String token = Token.generateToken(userInformation.getId());
			String url = req.getRequestURL().toString().substring(0, req.getRequestURL().lastIndexOf("/")) + "/resetPasswordLink/" + token;	
			String mailTo = user.getEmail();			
			String msg = "click on given link to rest yor password "+url;
			String subject = "reset password link";
			Mail.sendMail(mailTo, msg, subject);
			System.out.println("in forgot");

			return true;

		}
		return false;
	}

	// Required for UPDATE
	// Supports for READ

	@Transactional(propagation=Propagation.REQUIRED)
	public String activateUser(String token, HttpServletRequest request) {

		//String randomId = null; 	
		System.out.println("Token in active user "+token);
		int id = Token.getId(token);
		System.out.println("Id in active user "+token);

		//User user = userDao.getUserByRandomId(randomId);
		User user= userDao.getUserById(id);

		System.out.println(user.getName()+"..."+user.getPassword()+"..."+user.getEmail()+"...."+user.getStatus());
		user.setStatus(true);
		User user2 = userDao.updateRecord(user);

		System.out.println(user2.getName()+"..."+user2.getPassword()+"..."+user2.getEmail()+"...."+user2.getStatus());

		return null;
	}

	@Transactional
	public String resetPassword(HttpServletRequest request, String newPassword, String token) {

		int id = Token.getId(token);
		User user = userDao.getUserById(id);
		//String newPassword = user.getPassword();
		String hashCodePassword = encoder.encode(newPassword);
		user.setPassword(hashCodePassword);
		userDao.updateRecord(user);
		System.out.println("password reset successfully");
		return null;
	}

	@Transactional
	public boolean isEmailIdPresent(String email) {

		List<User> userList = userDao.checkEmailId(email);
		if (userList.size() != 0) {
			return true;
		}
		return false;
	}

}
