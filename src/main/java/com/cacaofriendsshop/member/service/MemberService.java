package com.cacaofriendsshop.member.service;

import com.cacaofriendsshop.etc.exception.ResourceDuplicateException;
import com.cacaofriendsshop.etc.exception.UserNotFoundException;
import com.cacaofriendsshop.member.domain.Member;
import com.cacaofriendsshop.member.dto.MemberDto.SaveRequest;
import com.cacaofriendsshop.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member saveMember(SaveRequest saveRequest) {
        if(checkEmail(saveRequest.getEmail()) || checkNickname(saveRequest.getNickname())) {
            throw new ResourceDuplicateException();
        }
        return memberRepository.save(saveRequest.toMember());
    }

    public boolean checkEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    public boolean checkNickname(String nickname) {
        return memberRepository.existsMemberByNickname(nickname);
    }

    @Transactional
    public void updateNickname(String email, String nickname) {
        Member member = memberRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        if (checkNickname(nickname)) {
            throw new ResourceDuplicateException();
        }
        member.updateNickname(nickname);
    }

    public void deleteMember(String email, String password) {
        Member member = memberRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        existByEmailAndPassword(email, password);

        memberRepository.delete(member);
    }

    private void existByEmailAndPassword(String email, String password) {
        if (!memberRepository.existsByEmailAndPassword(email, password)) {
            throw new UserNotFoundException();
        }
    }

    public Member findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
    }
}
