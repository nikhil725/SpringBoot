package com.fundoonotes.userservice;

public interface UserService {
	  public void register(UserDto userDto, String requestURL);
	  public String login(UserDto userDto);
	  public User getUserById(int userId);
	  int userActivation(String randomId);
	  public boolean forgetPassword(String email, String url);
	  public int resetPassword(UserDto userDto);


}
