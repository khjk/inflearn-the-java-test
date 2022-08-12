package me.khjk;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 두개의 애노테이션을 조합해서 새로운 애노테이션을 만듬
 * */
@Target(ElementType.METHOD) //메소드에 애노테이션을 사용
@Retention(RetentionPolicy.RUNTIME) //이 애노테이션 정보를 Runtime까지 유지해야한다
@Test //테스트에 애노테이션을 사용
@Tag("fast") //이 애노테이션은 fast라는 Tag가 달려있는거...
public @interface FastTest {

}
