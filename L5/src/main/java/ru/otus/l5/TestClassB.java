package ru.otus.l5;

import ru.otus.l5.Annotation.MyAfter;
import ru.otus.l5.Annotation.MyBefore;
import ru.otus.l5.Annotation.MyTest;

/**
 * Created by SSS on 01.07.2017.
 */
public class TestClassB {
    @MyBefore
    public void before() {
        System.out.println("beforeClassB");
    }
    @MyTest
    public void methodA() {
        System.out.println("methodAClassB");
    }
    @MyTest
    public void methodB() {
        System.out.println("methodBClassB");
    }
    @MyAfter
    public void after() {
        System.out.println("afterBClassB");
    }

}
