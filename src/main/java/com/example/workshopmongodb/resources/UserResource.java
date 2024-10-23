package com.example.workshopmongodb.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.workshopmongodb.domain.User;
import com.example.workshopmongodb.dto.UserDTO;
import com.example.workshopmongodb.services.UserServices;

@RestController
@RequestMapping(value = "/users")//Para informar o caminho do endpoint
public class UserResource {

	@Autowired
	private UserServices userServices;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		List<User> list = userServices.findAll();
		List<UserDTO> listDTO = list.stream().map(obj -> new UserDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		User user = userServices.findById(id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO userDto){
		User user = userServices.fromDTO(userDto);
		user = userServices.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id){
		userServices.delete(id);
		return ResponseEntity.noContent().build();
	}
}
