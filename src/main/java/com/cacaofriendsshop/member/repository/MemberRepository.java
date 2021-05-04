package com.cacaofriendsshop.member.repository;

import com.cacaofriendsshop.member.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(String email);

    boolean existsMemberByNickname(String nickname);

    boolean existsByEmailAndPassword(String email, String password);

    Optional<Member> findByEmail(String email);
}
