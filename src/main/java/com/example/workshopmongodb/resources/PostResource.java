package com.example.workshopmongodb.resources;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.workshopmongodb.domain.Post;
import com.example.workshopmongodb.resources.util.URL;
import com.example.workshopmongodb.services.PostServices;

@RestController
@RequestMapping(value = "/posts")//Para informar o caminho do endpoint
public class PostResource {

	@Autowired
	private PostServices postServices;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id){
		Post post = postServices.findById(id);
		return ResponseEntity.ok().body(post);
	}
	
	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue = "") String text){
		text = URL.decodeParam(text);
		List<Post> posts = postServices.findByTitle(text);
		return ResponseEntity.ok().body(posts);
	}
	
	
}
