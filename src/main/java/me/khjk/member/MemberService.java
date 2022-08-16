package me.khjk.member;

import me.khjk.domain.Member;

import java.util.Optional;

public interface MemberService {
    //void validate(Long memberId) throws InvalidMemberException;
    //Member findById(Long memberId) throws MemberNotFoundException;
    Optional<Member> findById(Long memberId);
}
