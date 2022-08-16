package me.khjk;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) //Test Instance를 클래스당 한개만 만듬
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestInstanceStudy {

    int value = 1;

    @BeforeAll
    void beforeAll() { //인스턴스를 한번만 만들기 때문에 static 키워드가 필요없어짐
        System.out.println("beforeAll");
    }

    @Order(2) //spring이 아닌 Junit이 제공하는거 써야함
    @FastTest
    @DisplayName("스터디 만들기 fast")
    void create_new_study() {
        System.out.println(this);
        /**
         * 테스트마다 실행되는 클래스 인스턴스가 다르다. (테스트마다 각각 생선한다. 따라서 value는 항상 1 && 해시값도 다름)
         * 왜냐하면 테스트간 의존성을 없애기 위해서..
         * 테스트 순서는 보장 되지 않는다.
         * */
        Study test = new Study(100);
        assertThat(test.getLimit()).isGreaterThan(9);

        System.out.println("create" + value++);
    }
    @Order(1)
    @SlowTest
    @DisplayName("스터디 만들기 slow")
    void create_new_study_again() {
        System.out.println(this);
        System.out.println("create1" + value++);
    }


}