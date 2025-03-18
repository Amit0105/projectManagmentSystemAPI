package com.myStudy.projectmanagement.controller;

import com.myStudy.projectmanagement.modal.Comment;
import com.myStudy.projectmanagement.modal.User;
import com.myStudy.projectmanagement.repository.CommentRepository;
import com.myStudy.projectmanagement.request.CreateCommentRequest;
import com.myStudy.projectmanagement.response.MessageResponse;
import com.myStudy.projectmanagement.service.CommentService;
import com.myStudy.projectmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;
    @Autowired
    private CommentRepository commentRepository;

    @PostMapping
    public ResponseEntity<Comment> createComment(
            @RequestBody CreateCommentRequest req,
            @RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findUserProfileByJwt(jwt);
        Comment createComment = commentService.createComment(
                req.getIssueId(),
                user.getId(),
                req.getContent());

        return new ResponseEntity<>(createComment, HttpStatus.CREATED);

    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<MessageResponse> deleteComment(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long commentId
            ) throws Exception{

        User user = userService.findUserProfileByJwt(jwt);
        commentService.deleteComment(commentId,user.getId());
        MessageResponse res = new MessageResponse();
        res.setMessage("Comment Deleted Successfully");

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{issueId}")
    public ResponseEntity<List<Comment>> getCommentsByIssueId(
            @PathVariable Long issueId) throws Exception{

        List<Comment> comments = commentService.findCommentByIssueId(issueId);
        return new ResponseEntity<>(comments, HttpStatus.OK);

    }

}
