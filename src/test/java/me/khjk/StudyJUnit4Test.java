package me.khjk;

import org.junit.Before;
import org.junit.jupiter.api.Test;

/**
 * JUNIT4 -> JUNIT5 마이그레이션
 * junit-vintage-engine을 의존성으로 추가하면,
 * Junit5외 junit-platform으로 JUnit3과 4로 작성된 테스트를 실행할 수 있다.
 *
 * @Rule은 기본적으로 지원하지 않지만, junit-jupiter-migrationsupport 모듈이 제공하는
 * @EnableRuleMigrationSupport를 사용하면 다음 타입의 Rule을 제공한다.
 * - ExternalResource
 * - Verifier
 * - ExpectedException
 * */
public class StudyJUnit4Test {

    @Before
    public void before() {
        System.out.println("before");
    }

    @Test
    public void createTest() {
        System.out.println("test");
    }

    @Test
    public void createTest2() {
        System.out.println("test2");
    }
}
