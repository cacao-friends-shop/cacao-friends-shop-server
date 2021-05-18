package com.cacaofriendsshop.post.dto;

import com.cacaofriendsshop.post.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class CommentResponseDto {

    private Long id;
    private Long postId;
    private Long memberId;
    private String content;
    private Integer likeCount;
    private String createdDate;

    public static CommentResponseDto from(Comment comment) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .postId(comment.getPost().getId())
                .memberId(comment.getMember().getId())
                .content(comment.getContent())
                .likeCount(comment.getLikeCount())
                .createdDate(comment.getCreatedDate())
                .build();
    }
}
