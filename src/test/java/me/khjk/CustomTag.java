package me.khjk;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CustomTag {
    /**
     * 기본 애노테이션은 메타 애노테이션라고 부르는데 커스텀 애노테이션 만들수있음
     * */
    @FastTest
    @DisplayName("스터디 만들기 fast")
    void create_new_study() {
        Study test = new Study(100);
        assertThat(test.getLimit()).isGreaterThan(9);

        System.out.println("create");
    }

    @SlowTest
    @DisplayName("스터디 만들기 slow")
    void create_new_study_again() {
        System.out.println("create1");
    }

}