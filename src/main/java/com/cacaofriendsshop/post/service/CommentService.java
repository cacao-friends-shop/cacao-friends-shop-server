package com.cacaofriendsshop.post.service;

import com.cacaofriendsshop.member.service.MemberService;
import com.cacaofriendsshop.post.domain.Comment;
import com.cacaofriendsshop.post.dto.CommentRequestDto;
import com.cacaofriendsshop.post.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;
    private final MemberService memberService;

    public void deleteByPostId(Long id) {
        List<Comment> comments = findByPostId(id);
        commentRepository.deleteAll(comments);
    }

    public List<Comment> findByPostId(Long id) {
        return commentRepository.findAll().stream()
                .filter(comment -> comment.isSamePostId(id))
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    public Comment save(CommentRequestDto commentRequestDto) {
        Comment comment = createComment(commentRequestDto);
        return commentRepository.save(comment);
    }

    private Comment createComment(CommentRequestDto commentRequestDto) {
        Comment comment = Comment.builder()
                .id(commentRequestDto.getId())
                .content(commentRequestDto.getContent())
                .member(memberService.findById(commentRequestDto.getMemberId()))
                .post(postService.findById(commentRequestDto.getPostId()))
                .createdDate(commentRequestDto.getCreatedDate())
                .likeCount(commentRequestDto.getLikeCount())
                .build();
        if (commentRequestDto.getLikeCount() == null) {
            comment.updateLikeCount(0);
        }
        return comment;
    }

}
