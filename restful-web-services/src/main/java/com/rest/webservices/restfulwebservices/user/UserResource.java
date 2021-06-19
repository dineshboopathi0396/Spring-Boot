package com.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.ApiModelProperty;

@RestController
public class UserResource {

	@Autowired
	private UserDAOService userService;

	// GET /users
	// retriveAllusers
	@GetMapping("/users")
	public List<User> retrieveAllusers() {
		return userService.findAll();
	}
	
	//HATEOAS

	// GET /Users/{id}
	// retrieveUser(int id)
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user = userService.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("id-" + id);
		}
		//"all-users", SERVER_PATH + "/users"
		//retrieveAllUsers
		EntityModel<User> resource = EntityModel.of(user);
		
		WebMvcLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retrieveAllusers());
		
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
		
	}

	// CREATED
	// input - details of the user
	// output - CREATED & Return the Created URL
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userService.save(user);
		// CREATED
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
	
	//DELETE
	//Input - id
	//remove the user
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = userService.deleteById(id);
		if (user == null) {
			throw new UserNotFoundException("id-" + id);
		}
	}

}
