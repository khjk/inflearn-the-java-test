package me.khjk.study;

import me.khjk.member.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class StudyServiceTest {
    @Mock
    MemberService memberService;

    @Mock
    StudyRepository studyRepository;

    @Test
    void createStudyService( @Mock MemberService memberService, @Mock StudyRepository studyRepository ) {
        /**
         *  1. Mock 메소드 활용하기
         *    MemberService memberService = mock(MemberService.class);
         *    StudyRepository studyRepository = mock(StudyRepository.class);
         *  2. 애노테이션 활용하기
         *    ExtendWith 선언뒤 Mock 애노테이션 활용
         * */
        StudyService studyService = new StudyService(memberService, studyRepository);

        assertNotNull(studyService);
    }
}
