package com.cacaofriendsshop.post.dto;

import com.cacaofriendsshop.post.domain.Comment;
import com.cacaofriendsshop.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PostResponseWithCommentDto {

    private Long id;
    private String title;
    private String content;
    private String characterType;
    private String createdDateTime;
    private Integer likeCount;
    private List<String> imageUrls;
    private List<CommentResponseDto> comments;

    public static PostResponseWithCommentDto of(Post post, List<Comment> comments) {
        List<CommentResponseDto> commentResponseDtos = comments.stream()
                .map(CommentResponseDto::from)
                .collect(Collectors.toList());

        return PostResponseWithCommentDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .characterType(post.getCharacterType())
                .createdDateTime(post.getCreatedDateTime())
                .likeCount(post.getLikeCount())
                .imageUrls(post.getImageUrls())
                .comments(commentResponseDtos)
                .build();
    }

}
