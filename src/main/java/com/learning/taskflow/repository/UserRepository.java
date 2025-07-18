package com.learning.taskflow.repository;

import com.learning.taskflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
/*
    User create(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    void deleteUserById(Long id);
*/
}
