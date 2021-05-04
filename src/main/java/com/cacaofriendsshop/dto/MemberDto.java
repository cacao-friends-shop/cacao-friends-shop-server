package com.cacaofriendsshop.dto;

import com.cacaofriendsshop.domain.Member;
import com.cacaofriendsshop.etc.config등등.MemberLevel;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberDto {

    @Getter
    @NoArgsConstructor
    public static class SaveRequest {
        @NotBlank(message = "이메일 주소를 입력해주세요.")
        @Email(message = "올바른 이메일 주소를 입력해주세요.")
        private String email;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하로 입력해주세요.")
        private String password;

        @NotBlank(message = "닉네임을 입력해주세요.")
        @Size(min = 2, max = 10, message = "닉네임은 2자 이상 10자 이하로 입력해주세요.")
        private String nickname;

        @NotBlank(message = "휴대폰 번호를 입력해주세요.")
        @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바른 휴대폰 번호를 입력해주세요.")
        private String phone;

        public Member toMember() {
            return Member.builder()
                .email(email)
                .nickname(nickname)
                .password(password)
                .phone(phone)
                .memberLevel(MemberLevel.DEFAULT)
                .build();
        }
    }

    @Getter
    public static class LoginRequest {
        private String email;
        private String password;
    }

    @Getter
    public static class WithdrawOrNicknameUpdateRequest{
        private String nickname;
        private String password;
    }
}
