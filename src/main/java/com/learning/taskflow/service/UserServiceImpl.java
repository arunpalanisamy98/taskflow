package com.learning.taskflow.service;

import com.learning.taskflow.dto.UserDTO;
import com.learning.taskflow.exception.EmailAlreadyExistsException;
import com.learning.taskflow.exception.InvalidEmailException;
import com.learning.taskflow.exception.InvalidUserIdException;
import com.learning.taskflow.exception.UserAlreadyExistsException;
import com.learning.taskflow.model.User;
import com.learning.taskflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public Long createUser(UserDTO userDTO) {

        if(userRepository.existsByEmail(userDTO.getEmail())){
            throw new UserAlreadyExistsException("user with email "+userDTO.getEmail()+" already exists");
        }

        User user = mapToEntity(userDTO);
        user = userRepository.save(user);
        return user.getId();
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

    @Override
    public UserDTO updateUserEmail(Long id, String email){

        User user = userRepository.findById(id)
            .orElseThrow(
                () -> new InvalidUserIdException("User id not found")
                );
        if(user.getEmail().equals(email)){
            throw new EmailAlreadyExistsException("New email is the same as the old email");
        }
        if(userRepository.existsByEmail(email)){
            throw new EmailAlreadyExistsException("This email is already used");
        }
        userRepository.updateEmail(id, email);

        return userRepository.findById(id).map(this::mapToDTO).orElse(null);
    }

    @Override
    public Long getUserByEmail(String email){
        return userRepository.findByEmail(email)
            .map(u->u.getId())
            .orElseThrow(
                ()->new InvalidEmailException("email does not exist")
                );
    }

    @Override
    public List<UserDTO> getUsersPaginated(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.getContent()
            .stream()
            .map(this::mapToDTO)
            .collect(Collectors.toList());

    }

    @Override
    public void deleteUsers(List<Long> ids){
        userRepository.deleteAllById(ids);
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
