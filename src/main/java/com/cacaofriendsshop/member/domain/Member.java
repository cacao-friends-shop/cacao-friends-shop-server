package com.cacaofriendsshop.member.domain;

import com.cacaofriendsshop.etc.config등등.MemberLevel;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String nickname;

    private String phone;

    private MemberLevel memberLevel;

    @Builder
    public Member(Long id, String email, String password, String nickname, String phone, MemberLevel memberLevel) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
        this.memberLevel = memberLevel;

    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }
}
