package com.guba.spring.microserviceuser.web.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class ErrorMessage {
	private Date timestamp;
	private String message;
}
