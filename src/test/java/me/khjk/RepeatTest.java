package me.khjk;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RepeatTest {

    @DisplayName("반복 테스트 만들기")
    @RepeatedTest(value = 10, name ="{displayName}, {currentRepetition}/{totalRepetition}") //10번 반복해서 테스트
    void repeat_test(RepetitionInfo repeatInfo) {
        System.out.println("repeat test 현재반복수" + repeatInfo.getCurrentRepetition() + "/토탈 반복횟수" + repeatInfo.getTotalRepetitions());
    }

    @DisplayName("다른 값들 인자로 받아서 테스트 만들기")
    @ParameterizedTest(name = "{index} {displayName} message={0}") //테스트마다 다른 값들을 가지고 테스트 하고싶다..
    @ValueSource(strings = {"날씨가", "많이", "더워요","!!"})
    void repeat_other_value_Test(String message) {
        System.out.println(message);
    }
}