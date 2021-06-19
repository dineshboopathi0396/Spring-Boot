package com.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.rest.webservices.restfulwebservices.post.Post;
import com.rest.webservices.restfulwebservices.post.PostRepository;

import io.swagger.annotations.ApiModelProperty;

@RestController
public class UserJPAResource {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	// GET /users
	// retriveAllusers
	@GetMapping("jpa/users")
	public List<User> retrieveAllusers() {
		return userRepository.findAll();
	}

	// HATEOAS

	// GET /Users/{id}
	// retrieveUser(int id)
	@GetMapping("jpa/users/{id}")
	public EntityModel<Optional> retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}
		// "all-users", SERVER_PATH + "/users"
		// retrieveAllUsers
		EntityModel<Optional> resource = EntityModel.of(user);

		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllusers());

		resource.add(linkTo.withRel("all-users"));

		return resource;

	}

	// CREATED
	// input - details of the user
	// output - CREATED & Return the Created URL
	@PostMapping("jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		// CREATED
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	// DELETE
	// Input - id
	// remove the user
	@DeleteMapping("jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
//		if (user == null) {
//			throw new UserNotFoundException("id-" + id);
//		}
	}

	// GET /Posts
	// retriveAllPosts
	@GetMapping("jpa/users/{Id}/posts")
	public List<Post> retrieveAllposts(@PathVariable int Id) {
		Optional<User> userOptional = userRepository.findById(Id);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("id-" + Id);
		}
		return userOptional.get().getPosts();
	}

	@PostMapping("jpa/users/{Id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int Id, @RequestBody Post post) {
		Optional<User> userOptional = userRepository.findById(Id);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("id-" + Id);
		}

		User savedUser = userOptional.get();

		post.setUser(savedUser);

		postRepository.save(post);

		// CREATED
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	// DELETE
	// Input - id
	// remove the Post
	@DeleteMapping("jpa/users/{Id}/posts/{Pid}")
	public void deletePost(@PathVariable int Id, @PathVariable int Pid) {
		Optional<User> userOptional = userRepository.findById(Id);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("id-" + Id);
		}
		Optional<Post> post = postRepository.findById(Pid);
		if (!post.isPresent()) {
			throw new UserNotFoundException("id-" + Pid);
		}

		postRepository.deleteById(Pid);
	}

	// GET /Users/{id}
	// retrieveUser(int id)
	@GetMapping("jpa/users/{id}/posts/{Pid}")
	public EntityModel<Optional> retrievePost(@PathVariable int id, @PathVariable int Pid) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}
		Optional<Post> post = postRepository.findById(Pid);
		if (!post.isPresent()) {
			throw new UserNotFoundException("id-" + Pid);
		}
		// "all-users", SERVER_PATH + "/users"
		// retrieveAllUsers
		EntityModel<Optional> resource = EntityModel.of(post);

		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllposts(Pid));

		resource.add(linkTo.withRel("all-users"));

		return resource;

	}

}
