package com.cacaofriendsshop.member.service;

import static com.cacaofriendsshop.etc.config등등.Constants.AUTHORITY;
import static com.cacaofriendsshop.etc.config등등.Constants.LOGIN_ID;

import com.cacaofriendsshop.member.domain.Member;
import com.cacaofriendsshop.member.dto.MemberDto.LoginRequest;
import com.cacaofriendsshop.etc.exception.UserNotFoundException;
import com.cacaofriendsshop.etc.config등등.MemberLevel;
import com.cacaofriendsshop.member.repository.MemberRepository;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;
    private final HttpSession session;

    public void login(LoginRequest loginRequest) {
        existByEmailAndPassword(loginRequest);
        setMemberLevel(loginRequest.getEmail());
        session.setAttribute(LOGIN_ID, loginRequest.getEmail());
    }

    private void setMemberLevel(String email) {
        Member member = memberRepository.findByEmail(email).get();
        session.setAttribute(AUTHORITY, member.getMemberLevel());
    }

    private void existByEmailAndPassword(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        if (!memberRepository.existsByEmailAndPassword(email, password)) {
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
}
