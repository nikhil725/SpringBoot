package com.bridgeit.javabasic;

import java.util.HashSet;
import java.util.Set;

public class User {

	int id;
	String name;


	public User(int id, String name) {
		
	}
	
	public static void main(String[] args) {
		
		User user1 = new User(1, "ABC");
		User user2 = new User(2, "ABC");
		
		Set<User> set = new HashSet<>();
		
		set.add(user1);
		set.add(user2);
		
		System.out.println("Size of set is => "+set.size());
		
	}

}
