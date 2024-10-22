package com.example.workshopmongodb.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.workshopmongodb.domain.User;

@RestController
@RequestMapping(value = "/users")//Para informar o caminho do endpoint
public class UserResource {

	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		User user = new User("1", "Leonardo Abreu", "leo@gmail.com");
		User user2 = new User("2", "Lucas Ferreira", "lucas@gmail.com");
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(user, user2));
		return ResponseEntity.ok().body(list);
	}
}
