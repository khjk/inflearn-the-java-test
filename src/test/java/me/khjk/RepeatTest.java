package me.khjk;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.*;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;


class RepeatTest {

    @DisplayName("반복 테스트 만들기")
    @RepeatedTest(value = 10, name ="{displayName}, {currentRepetition}/{totalRepetition}") //10번 반복해서 테스트
    void repeat_test(RepetitionInfo repeatInfo) {
        System.out.println("repeat test 현재반복수" + repeatInfo.getCurrentRepetition() + "/토탈 반복횟수" + repeatInfo.getTotalRepetitions());
    }

    @DisplayName("다른 값들 인자로 받아서 테스트 만들기")
    @ParameterizedTest(name = "{index} {displayName} message={0}") //테스트마다 다른 값들을 가지고 테스트 하고싶다..
    @ValueSource(strings = {"날씨가", "많이", "더워요","!!"})
    @NullAndEmptySource //null 과 empty 한 값 두개를 파라미터로 추가 테스트
    void repeat_other_value_Test(String message) {
        System.out.println(message);
    }

    @DisplayName("다른 값들 인자로 받아서 테스트 만들기")
    @ParameterizedTest(name = "{index} {displayName} message={0}") //테스트마다 다른 값들을 가지고 테스트 하고싶다..
    @ValueSource(ints = {10, 20, 30})
    void repeat_test_using_converter(@ConvertWith(StudyConverter.class) Study study) {
        System.out.println(study.getLimit());
    }

    /**
     * 자동형변환 Converter
     * ArgumentConverter은 한개의 argument에 대한 것...
     * */
    static class StudyConverter extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(Study.class, targetType, "Can only convert to Study");
            return new Study(Integer.parseInt(source.toString()));
        }
    }

    ////////////////////////////////////////////////////////////

    @DisplayName("csvSource 사용 해서 반복 테스트 수행하기")
    @ParameterizedTest(name = "{index} {displayName} message={0}") //테스트마다 다른 값들을 가지고 테스트 하고싶다..
    @CsvSource({"10, '자바 스터디'", "20, '스프링'"})
    void repeat_test_using_csvSource(Integer limit, String name) {
        Study study = new Study(limit, name);
        System.out.println(study);
    }

    @DisplayName("argumentsAccessor 사용 해서 반복 테스트 수행하기")
    @ParameterizedTest(name = "{index} {displayName} message={0}") //테스트마다 다른 값들을 가지고 테스트 하고싶다..
    @CsvSource({"10, '자바 스터디'", "20, '스프링'"})
    void repeat_test_using_csvSource2(ArgumentsAccessor argumentsAccessor) {
        Study study = new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
        System.out.println(study);
    }

    @DisplayName("ArgumentsAggregator 만들어서 해서 반복 테스트 수행하기")
    @ParameterizedTest(name = "{index} {displayName} message={0}") //테스트마다 다른 값들을 가지고 테스트 하고싶다..
    @CsvSource({"10, '자바 스터디'", "20, '스프링'"})
    void repeat_test_using_csvSource3(@AggregateWith(StudyAggregator.class) Study study) {
        System.out.println(study);
    }
    /**
     * argument가 여러개면 ArgumentsAggregator을 생성
     * */
    static class StudyAggregator implements ArgumentsAggregator {
        @Override
        public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
            Study study = new Study(accessor.getInteger(0), accessor.getString(1));
            return study;
        }
    }

}