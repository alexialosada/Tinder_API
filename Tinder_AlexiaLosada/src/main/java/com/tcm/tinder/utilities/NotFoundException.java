package com.tcm.tinder.utilities;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Invalid parameters")

public class NotFoundException extends Exception {
	
	public NotFoundException() {
		
	}

}
