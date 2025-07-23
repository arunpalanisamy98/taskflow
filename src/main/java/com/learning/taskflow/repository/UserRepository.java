package com.learning.taskflow.repository;

import com.learning.taskflow.model.User;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {
/*
    User create(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    void deleteUserById(Long id);
*/

    boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query("update User u set u.email = :email where u.id = :id")
    int updateEmail(Long id, String email);


    Optional<User> findByEmail(String email);

    Page<User> findAll(Pageable pageable);

}
