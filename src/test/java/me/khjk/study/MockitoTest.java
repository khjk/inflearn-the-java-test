package me.khjk.study;

import me.khjk.domain.Member;
import me.khjk.domain.Study;
import me.khjk.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MockitoTest {

    @Mock
    MemberService memberService;

    @Mock
    StudyRepository studyRepository;

    @Test
    void createNewStudy() {
        /**
         * BDD : 애플리케이션이 어떻게 "행동"해야 하는지에 대한 공통된 이해를 구성하는 방법으로, TDD에서 창안했다.
         * Mockito는 BddMockito라는 클래스를 통해 BDD 스타일의 API를 제공한다.
         * 행동에 대한 스펙
         * - Title
         * - Narrative ( As a / I want / so that )
         * - Acceptance criteria ( Given / When / Then )
         * */
        // Given
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("keesun@email.com");

        Study study = new Study(10, "테스트");

        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        when(studyRepository.save(study)).thenReturn(study);

        /**
         * When -> Given
         * */
        given(memberService.findById(1L)).willReturn(Optional.of(member));
        given(studyRepository.save(study)).willReturn(study);

        // When
        studyService.createNewStudy(1L, study);

        // Then
        assertEquals(member, study.getOwner());

        /**
         * Verify -> Then
         * */
        verify(memberService, times(1)).notify(study);
        then(memberService).should(times(1)).notify(study);

        verifyNoMoreInteractions(memberService);
        then(memberService).shouldHaveNoMoreInteractions();
    }


    @DisplayName("다른 사용자가 볼 수 있도록 스터디를 공개한다.")
    @Test
    void openStudy() {
        //Given
        StudyService studyService = new StudyService(memberService, studyRepository);
        Study study = new Study(10, "더자바테스트");
        assertNull(study.getOpenedDateTime());
        given(studyRepository.save(study)).willReturn(study);

        //When
        studyService.openStudy(study);

        //Then
        assertEquals(StudyStatus.OPENED, study.getStatus());
        assertNotNull(study.getOpenedDateTime());
        then(memberService).should().notify(study);
    }
}
