package com.myStudy.projectmanagement.request;

import lombok.Data;

@Data
public class CreateCommentRequest {

    private String content;

    private Long issueId;
}
