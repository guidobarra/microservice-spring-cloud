package com.guba.spring.microserviceuser.web.model.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDetailsRequestModel {

	@NotNull(message="First name cannot be null")
	@Size(min=2, message = "First name must not be less than 2 characters")
	private String firstName;
	
	@NotNull(message="Last name cannot be null")
	@Size(min=2, message = "Last name must not be less than 2 characters")
	private String lastName;
	
}
