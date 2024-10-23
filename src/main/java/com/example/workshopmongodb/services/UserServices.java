package com.example.workshopmongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.workshopmongodb.domain.User;
import com.example.workshopmongodb.dto.UserDTO;
import com.example.workshopmongodb.repository.UserRepository;
import com.example.workshopmongodb.services.exception.ObjectNotFoundException;

@Service
public class UserServices {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(String id){
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
	}
	
	public User insert(User user) {
		return userRepository.insert(user);
	}
	
	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);
	}
	
	public User update(String id, User user) {
		
		User entity = userRepository.findById(id)
	            .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado!"));
		updateData(entity, user);
		return userRepository.save(entity);
		
	}
	
	private void updateData(User newUser, User user) {
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());

	}

	public User fromDTO(UserDTO userDto) {
		return new User(userDto.getId(), userDto.getName(),userDto.getEmail());
	}
}
