package me.khjk;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@ExtendWith(FindSlowTestExtension.class) //실행 시간 측정해서 초과하면 알림메시지 띄우는 내가 만든 확장팩 사용
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class Tagging {
    /**
     * 태그를 달아서 특정 태그의 테스트만 실행가능
     * 1. Edit Configuration > Test Kind를 Class에서 Tags로 변경 > TagExpressions 에 fast라고 입력
     * 2. 메이븐 에서 profile 설정
     * 클래스 내의 전체 테스트가 실행 되는게 아닌 fast 태그를 가진 테스트 케이스만 실행 된다
     * */
    @Test
    @DisplayName("스터디 만들기 fast")
    @Tag("fast")
    void create_new_study() throws InterruptedException {
        Study test = new Study(100);
        assertThat(test.getLimit()).isGreaterThan(9);
        Thread.sleep(10000); //Please consider mark method [create_new_study] with @SlowTest. 알림 발생
        System.out.println("create");
    }


    @Test
    @DisplayName("스터디 만들기 slow")
    @Tag("slow")
    void create_new_study_again() {
        System.out.println("create1");
    }


}