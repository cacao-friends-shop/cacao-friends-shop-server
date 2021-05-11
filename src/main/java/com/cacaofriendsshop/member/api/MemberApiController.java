package com.cacaofriendsshop.member.api;

import com.cacaofriendsshop.member.domain.Member;
import com.cacaofriendsshop.member.dto.MemberDto.LoginRequest;
import com.cacaofriendsshop.member.dto.MemberDto.LoginResponse;
import com.cacaofriendsshop.member.dto.MemberDto.SaveRequest;
import com.cacaofriendsshop.member.dto.MemberDto.WithdrawOrNicknameUpdateRequest;
import com.cacaofriendsshop.etc.config등등.CurrentMember;
import com.cacaofriendsshop.etc.config등등.LoginCheck;
import com.cacaofriendsshop.member.service.LoginService;
import com.cacaofriendsshop.member.service.MemberService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("members")
public class MemberApiController {

    private final MemberService memberService;
    private final LoginService loginService;

    @GetMapping("/duplicated/{email}")
    public boolean duplicateCheckEmail(@PathVariable String email) {
        return memberService.checkEmail(email);
    }

    @GetMapping("/duplicated/{nickname}")
    public boolean duplicateCheckNickname(@PathVariable String nickname) {
        return memberService.checkNickname(nickname);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody SaveRequest saveRequest) {
        Member savedMember = memberService.saveMember(saveRequest);

        loginService.login(saveRequest.getEmail(), saveRequest.getPassword());
        return ResponseEntity.created(URI.create("/" + savedMember.getId())).build();
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        loginService.login(loginRequest.getEmail(), loginRequest.getPassword());

        return loginService.loginInfos();

    }

    @LoginCheck
    @DeleteMapping("/logout")
    public void logout() {
        loginService.logout();
    }

    @LoginCheck
    @PatchMapping
    public void updateNickname(@CurrentMember String email,
        @RequestBody WithdrawOrNicknameUpdateRequest requestDto) {
        memberService.updateNickname(email, requestDto.getNickname());
    }

    @LoginCheck
    @DeleteMapping
    public void withdrawMember(@CurrentMember String email,
        @RequestBody WithdrawOrNicknameUpdateRequest requestDto) {
        memberService.deleteMember(email, requestDto.getPassword());
    }
}
