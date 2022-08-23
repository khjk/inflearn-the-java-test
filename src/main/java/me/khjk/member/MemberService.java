package me.khjk.member;

import me.khjk.domain.Member;
import me.khjk.domain.Study;

import java.util.Optional;

public interface MemberService {
    //void validate(Long memberId) throws InvalidMemberException;
    //Member findById(Long memberId) throws MemberNotFoundException;
    Optional<Member> findById(Long memberId);

    void validate(Long memberId);

    void notify(Study study);

    void notify(Member member);
}
