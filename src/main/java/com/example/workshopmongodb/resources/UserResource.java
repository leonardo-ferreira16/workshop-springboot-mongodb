package com.example.workshopmongodb.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.workshopmongodb.domain.User;
import com.example.workshopmongodb.services.UserServices;

@RestController
@RequestMapping(value = "/users")//Para informar o caminho do endpoint
public class UserResource {

	@Autowired
	private UserServices userServices;
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> list = userServices.findAll();
		return ResponseEntity.ok().body(list);
	}
}
