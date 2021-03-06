package com.cacaofriendsshop.post.domain;

import com.cacaofriendsshop.member.domain.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Post post;

    @ManyToOne
    private Member member;

    private String content;
    private Integer likeCount;
    private String createdDate;

    public boolean isSamePostId(Long id) {
        return this.post.getId().equals(id);
    }

    public boolean isSameMemberId(Long id) {
        return this.member.getId().equals(id);
    }

    public void updateLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

}
