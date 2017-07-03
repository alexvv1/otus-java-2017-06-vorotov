package ru.otus.l5;

import ru.otus.l5.Annotation.MyAfter;
import ru.otus.l5.Annotation.MyBefore;
import ru.otus.l5.Annotation.MyTest;

/**
 * Created by SSS on 01.07.2017.
 */
public class TestClassA {
    @MyBefore
    public void before() {
        System.out.println("beforeClassA");
    }

    @MyTest
    public void methodA() {
        System.out.println("methodAClassA");
    }

    @MyTest
    public void methodB() {
        System.out.println("methodBClassA");
    }

    @MyAfter
    public void after() {
        System.out.println("afterBClassA");
    }

}



