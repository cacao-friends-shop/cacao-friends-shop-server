package com.cacaofriendsshop.post.dto;

import com.cacaofriendsshop.post.domain.Comment;
import com.cacaofriendsshop.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private String characterType;
    private String createdDateTime;
    private Integer likeCount;
    private List<String> imageUrls;
    private Integer numberOfComments;
    private String firstContent;


    public static PostResponseDto of(Post post, List<Comment> comments) {
        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .characterType(post.getCharacterType())
                .createdDateTime(post.getCreatedDateTime())
                .likeCount(post.getLikeCount())
                .imageUrls(post.getImageUrls())
                .numberOfComments(comments.size())
                .firstContent(getContent(comments))
                .build();
    }

    private static String getContent(List<Comment> comments) {
        if (comments.isEmpty()) {
            return null;
        }
        return comments.get(0).getContent();
    }

}
