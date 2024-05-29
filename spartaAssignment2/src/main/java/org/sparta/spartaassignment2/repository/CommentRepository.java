package org.sparta.spartaassignment2.repository;

import org.sparta.spartaassignment2.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
