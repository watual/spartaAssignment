package org.sparta.spartaassignment2.repository;

import org.sparta.spartaassignment2.entity.Comment;
import org.sparta.spartaassignment2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
