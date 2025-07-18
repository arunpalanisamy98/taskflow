package com.learning.taskflow.service;

import com.learning.taskflow.dto.UserDTO;
import com.learning.taskflow.model.User;
import com.learning.taskflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDTO createUser(UserDTO userDTO) {

        User user = mapToEntity(userDTO);
        userRepository.save(user);
        return null;
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id).map(this::mapToDTO).orElse(null);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteUserUser(Long id) {
        userRepository.deleteById(id);
    }



    private UserDTO mapToDTO(User user){
        return new UserDTO(user.getName(), user.getEmail());
    }

    private User mapToEntity(UserDTO userDTO){
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setCreatedAt(Instant.now());
        return user;
    }





}
