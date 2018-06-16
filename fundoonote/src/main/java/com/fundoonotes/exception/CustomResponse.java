package com.fundoonotes.exception;

import java.io.Serializable;

public class CustomResponse implements Serializable {
	
	 private static final long serialVersionUID = 1L;

	   private String errorMessage;
	   private int statusCode;

	   public String getMessage()
	   {
	      return errorMessage;
	   }

	   public void setMessage(String errorMessage)
	   {
	      this.errorMessage = errorMessage;
	   }

	   public int getStatusCode()
	   {
	      return statusCode;
	   }

	   public void setStatusCode(int statusCode)
	   {
	      this.statusCode = statusCode;
	   }
}
