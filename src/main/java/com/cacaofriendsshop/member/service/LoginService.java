package com.cacaofriendsshop.member.service;

import com.cacaofriendsshop.etc.config등등.MemberLevel;
import com.cacaofriendsshop.etc.exception.UserNotFoundException;
import com.cacaofriendsshop.member.domain.Member;
import com.cacaofriendsshop.member.dto.MemberDto.LoginRequest;
import com.cacaofriendsshop.member.dto.MemberDto.LoginResponse;
import com.cacaofriendsshop.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

import static com.cacaofriendsshop.etc.config등등.Constants.AUTHORITY;
import static com.cacaofriendsshop.etc.config등등.Constants.LOGIN_ID;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;
    private final HttpSession session;

    public void login(String email, String password) {
        existByEmailAndPassword(email, password);
        setMemberLevel(email);
        session.setAttribute(LOGIN_ID, email);
    }

    private void setMemberLevel(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(IllegalArgumentException::new);
        session.setAttribute(AUTHORITY, member.getMemberLevel());
    }

    private void existByEmailAndPassword(String email, String password) {
        if(!memberRepository.existsByEmailAndPassword(email, password)) {
            throw new UserNotFoundException();
        }
    }

    public MemberLevel getMemberLevel() {
        return (MemberLevel) session.getAttribute(AUTHORITY);
    }

    public String getLoginUser() {
        return (String) session.getAttribute(LOGIN_ID);
    }

    public void logout() {
        session.removeAttribute(LOGIN_ID);
    }

    public LoginResponse loginInfos() {

        Member member = memberRepository.findByEmail(getLoginUser())
            .orElseThrow(UserNotFoundException::new);

        return LoginResponse.builder()
            .email(member.getEmail())
            .memberLevel(member.getMemberLevel())
            .nickname(member.getNickname())
            .build();


    }
}
