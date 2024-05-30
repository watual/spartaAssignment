package org.sparta.spartaassignment2.repository;

import org.sparta.spartaassignment2.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByManager(String manager);
}
