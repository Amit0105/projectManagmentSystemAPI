package com.myStudy.projectmanagement.repository;

import com.myStudy.projectmanagement.modal.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByIssueId(Long issueId);

}
