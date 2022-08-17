package me.khjk.study;

import me.khjk.domain.Member;
import me.khjk.domain.Study;
import me.khjk.member.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudyServiceTest {

    @Mock
    MemberService memberService;

    @Mock
    StudyRepository studyRepository;

    @Test
    void createNewStudy( @Mock MemberService memberService, @Mock StudyRepository studyRepository ) {
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


    @Test
    void createNewStudy2( @Mock MemberService memberService, @Mock StudyRepository studyRepository ) {
        /**
         * 모든 Mock 객체의 행동
         * 1. Null을 리턴한다. (Optional 타입은 Optional.empty 리턴)
         * 2. Primitive 타입은 기본 Primitive 값
         * 3. 콜렉션은 비어있는 콜렉션
         * 4. Void 메소드는 예외를 던지지 않고 아무런 일도 발생하지 않는다.
         * */
        StudyService studyService = new StudyService(memberService, studyRepository);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);

        Optional<Member> optional = memberService.findById(1L);
        /**
         * Mock 객체 Stubbing
         * 1. 특정한 매개변수를 받은 경우 특정한 값을 리턴하거나 예외를 던지도록 만들 수 있다.
         * 2. Void 메소드 특정 매개변수를 받거나 호출된 경우 에외를 발생시킬 수 있다.
         * 3. 메소드가 동일한 매개변수로 호출될 때 각기 다르게 행동하도록 조작할 수도 있다.
         * */
        //첫번째 호출될떄는 member를 리턴하고 두번쨰 호출될때는 RuntimeException 나게, 세번쨰 호출되면 비어있는게 나오도록
        when(memberService.findById(any())).thenReturn(Optional.of(member)).thenThrow(new RuntimeException()).thenReturn(Optional.empty());
        //1이라는 값으로 findById가 호출되면 Exception 호출되게
        when(memberService.findById(1L)).thenThrow(new RuntimeException());
        //1이라는 값으로 validate 가 호출되면 예외를 발생시키게 Stubbing (원래는 validate 가 void 메소드이기 때문에 아무일도 안일어 남 (4번룰))
        doThrow(new IllegalArgumentException()).when(memberService).validate(1L);

        //첫번째 호출시 리턴값 stubbing 한거 확인
        Optional<Member> findById = memberService.findById(1L);
        assertEquals("keesun@email.com", findById.get().getEmail()); //동일하다!!!!

        //두번째 호출시 예외 던진거 확인하기
        assertThrows(IllegalArgumentException.class, () -> {
            memberService.validate(1L);
        });

        //세번째 호출시 Empty 리턴 확인
        assertEquals(Optional.empty(), memberService.findById(3L));

        memberService.validate(2L); //2인값에서는 에러안남

    }
}
