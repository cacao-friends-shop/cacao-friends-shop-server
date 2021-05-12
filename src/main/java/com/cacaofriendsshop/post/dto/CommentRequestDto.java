package com.cacaofriendsshop.post.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {

    private Long id;
    private Long postId;
    private Long memberId;
    private String content;
    private Integer likeCount;
    private String createdDate;

}
