package com.learning.taskflow.controller;

import com.learning.taskflow.dto.UserDTO;
import com.learning.taskflow.exception.PageNumberSizeException;
import com.learning.taskflow.service.UserService;

import jakarta.validation.constraints.Min;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;



@Validated
@RestController
public class UserController {

    
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @GetMapping("get/{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Long id) {
        
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUserUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/update/{id}/{email}")
    public ResponseEntity<UserDTO> updateEmail(@PathVariable Long id, @PathVariable String email) {
        UserDTO userDto = userService.updateUserEmail(id, email);
        return ResponseEntity.ok().body(userDto);
    }

    @GetMapping("/getId/{email}")
    public ResponseEntity<Long> getId(@PathVariable String email) {
        return ResponseEntity.ok().body(userService.getUserByEmail(email));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers(@RequestParam Integer pageNo,
        @RequestParam Integer noOfUsers) {
        if(noOfUsers<1){
            throw new PageNumberSizeException("number of users can't be less than 1");
        }

        if(pageNo<0){
            throw new PageNumberSizeException("Invalid page number");
        }
        List<UserDTO> users = userService.getUsersPaginated(pageNo, noOfUsers);
        
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/delete/users")
    public ResponseEntity<String> deleteUserList(@RequestBody List<Long> ids){
        userService.deleteUsers(ids);
        return ResponseEntity.ok().body("deleted");

    }
    
    
    
    
    
    
    
    

}
