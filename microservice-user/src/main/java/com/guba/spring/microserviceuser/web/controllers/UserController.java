package com.guba.spring.microserviceuser.web.controllers;

import com.guba.spring.microserviceuser.service.UserService;
import com.guba.spring.microserviceuser.web.model.request.UpdateUserDetailsRequestModel;
import com.guba.spring.microserviceuser.web.model.request.UserDetailsRequestModel;
import com.guba.spring.microserviceuser.web.model.response.UserRest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Log4j2
public class UserController {

	private static final Map<String, UserRest> USERS = new HashMap<>();

	private final UserService userService;
	
	@GetMapping
	public String getUsers(
			@RequestParam(value="page", defaultValue="1") int page,
			@RequestParam(value="limit", defaultValue="50") int limit,
			@RequestParam(value="sort", defaultValue = "desc", required = false) String sort) {
		return "get users was called with page = " + page + " and limit = " + limit + " and sort = " + sort;
	}
	
	@GetMapping(
			path= "/{id}",
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
			})
	public ResponseEntity<UserRest> getUser(@PathVariable String id) {
		log.debug("get user {}", id);
		if(USERS.containsKey(id)) {
			return new ResponseEntity<>(USERS.get(id), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping(
			consumes = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
			},
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
			})
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {
		log.debug("create user");

		UserRest returnValue = userService.createUser(userDetails);
		return new ResponseEntity<>(returnValue, HttpStatus.OK);
	}
	
	@PutMapping(
			path= "/{id}",
			consumes = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
			},
			produces =  { 
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
			})
	public UserRest updateUser(@PathVariable String id, @Valid @RequestBody UpdateUserDetailsRequestModel userDetails) {
		log.debug("update user {}", id);

		UserRest storedUserDetails = USERS.get(id);
		storedUserDetails.setFirstName(userDetails.getFirstName());
		storedUserDetails.setLastName(userDetails.getLastName());

		USERS.put(id, storedUserDetails);

		return storedUserDetails;
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id) {
		log.debug("remove user {}", id);

		USERS.remove(id);
		
		return ResponseEntity.noContent().build();
	}
}
