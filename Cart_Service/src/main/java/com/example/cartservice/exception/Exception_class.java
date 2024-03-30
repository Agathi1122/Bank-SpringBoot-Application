package com.example.cartservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class Exception_class  extends RuntimeException {

	private static final long serialVersionUID=1L;
	
	public Exception_class(String msg)
	{
		super(msg);
	}
}
