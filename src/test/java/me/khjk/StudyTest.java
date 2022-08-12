package me.khjk;

import me.khjk.Study;
import me.khjk.StudyStatus;
import org.apache.logging.log4j.util.Supplier;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    @Test
    @DisplayName("조건에 따라 테스트 실행하기")
    @EnabledOnOs(OS.WINDOWS) //실행환경이 윈도우인 경우에만 수행
    @EnabledOnJre({JRE.JAVA_8, JRE.JAVA_11}) //JAVA 11인 경우에만 수행
    @EnabledIfEnvironmentVariable(named="TEST_ENV", matches="LOCAL") //환경변수가 LOCAL인 경우에만 수행
    void test_conditionally() {
        /**
         * 환경변수가 로컬인 경우에만 다음 테스트를 수행
         * */
        assumeTrue("LOCAL".equalsIgnoreCase(System.getenv("TEST_ENV")));
        Study actual = new Study(10);

        /**
         * 조건별로 실행할 함수 설정
         * */
        assumingThat("LOCAL".equalsIgnoreCase(System.getenv("TEST_ENV")), () -> {
            System.out.println("로컬");
            Study test = new Study(100);
            assertThat(actual.getLimit()).isGreaterThan(9);
        });

        assumingThat("PROD".equalsIgnoreCase(System.getenv("TEST_ENV")), () -> {
            System.out.println("운영");
            Study test = new Study(10);
            assertThat(actual.getLimit()).isGreaterThan(9);
        });
    }


    @Test
    @DisplayName("스터디 만들기 😘")
    void create_new_study() {
        /**
         * Junit5부터는 굳이 public 키워드 안 붙여도 됨. 리플렉션 사용하면 private한 메소드도 어차피 호출이 가능하기 때문...
         * */
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        String message = exception.getMessage();
        assertEquals("limit은 0보다 커야 한다.", exception.getMessage());

        Study study = new Study(-10);
        /**
         * Executable 타입으로 네개 전달함... 한개가 실패해도 밑에꺼 실행됨
         * */
        assertAll(
                () -> assertNotNull(study),
                //expected , actual, 메시지 or Supplier
                //메시지 대신 람다식으로 만들어주면 필요한시점에만 실행함
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태값이 DRAFT여야 한다."),
                () -> assertTrue(1 < 2),
                () ->  assertTrue(study.getLimit() > 0 , "스터디 최대 참석가능 인원은 0보다 커야한다.")
        );

    System.out.println("create");
    }


    @Test
    @Disabled
    void create_new_study_again() {
        System.out.println("create1");
    }
    /**
     * 모든 테스트가 실행되기전에 딱 한번만 호출됨. private는 안되고 default는 됨. 리턴타입 있으면 안됨. static만 사용해야함
     * */
    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }
    /**
     * 모든 테스트가 실행된 이후에 딱 한번만 호출됨.
     * */
    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }
    /**
     *모든 테스트를 실행하기 이전에 실행됨. static일 필요 없음
     * */
    @BeforeEach
    void beforeEach() {
        System.out.println("before each");
    }
    @AfterEach
    void afterEach() {
        System.out.println("after each");
    }
}