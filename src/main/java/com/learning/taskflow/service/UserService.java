package com.learning.taskflow.service;

import com.learning.taskflow.dto.UserDTO;
import java.util.List;

public interface UserService {

    Long createUser(UserDTO userDTO);
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
    void deleteUserUser(Long id);
    UserDTO updateUserEmail(Long id, String email);
    Long getUserByEmail(String email);
    List<UserDTO> getUsersPaginated(int page, int size);
    void deleteUsers(List<Long> users);
}
