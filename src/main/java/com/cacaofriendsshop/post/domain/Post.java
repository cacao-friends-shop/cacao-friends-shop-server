package com.cacaofriendsshop.post.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String characterType;
    private String createdDateTime;
    private Integer likeCount;

    @ElementCollection
    private List<String> imageUrls;

    public void update(Post post) {
        if (post.title != null) {
            this.title = post.title;
        }
        if (post.content != null) {
            this.content = post.content;
        }
        if (post.characterType != null) {
            this.characterType = post.characterType;
        }
        if (post.createdDateTime != null) {
            this.createdDateTime = post.createdDateTime;
        }
        if (post.likeCount != null) {
            this.likeCount = post.likeCount;
        }
        if (post.imageUrls != imageUrls) {
            this.imageUrls = post.imageUrls;
        }
    }
}
