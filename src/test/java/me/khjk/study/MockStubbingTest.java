package me.khjk.study;

import me.khjk.domain.Member;
import me.khjk.domain.Study;
import me.khjk.member.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MockStubbingTest {

    @Test
    void createStudy(@Mock MemberService memberService, @Mock StudyRepository studyRepository) {
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("begywjd@naver.com");

        Study study = new Study(10, "테스트");

        //memberService 객체에 findById 메소드를 1L 값으로 호출하면 member 객체를 리턴하도록 Stubbing
        when(memberService.findById(1L)).thenReturn(Optional.of(member));

        //studyRepository 객체에 save 메소드를 study 객체로 호출하면 study 객체 그대로 리턴하도록 Stubbing
        when(studyRepository.save(study)).thenReturn(study);

        studyService.createNewStudy(1L, study);
        assertEquals(member, study.getOwner());

        //memberService에서 notify 메소드가 study argument 가지고 실행됐어야한다...
        verify(memberService, times(1)).notify(study);
        verify(memberService, times(1)).notify(member);
        //validate라는 함수는 한번도 호출되지 않았어야 함
        verify(memberService, never()).validate(any());

        //study로 notify가 호출되고나서 member로 notify가 호출되어야함함
       InOrder inOrder = inOrder(memberService);
        inOrder.verify(memberService).notify(study);
        inOrder.verify(memberService).notify(member);

        //어떠한 interaction도 일어나면 안된다...
        verifyNoMoreInteractions(memberService);
    }

}
