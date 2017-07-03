package ru.otus.l5.inPack;

import ru.otus.l5.Annotation.MyAfter;
import ru.otus.l5.Annotation.MyBefore;
import ru.otus.l5.Annotation.MyTest;

/**
 * Created by SSS on 01.07.2017.
 */
public class TestClassC {
    @MyBefore
    public void before() {
        System.out.println("beforeClassC");
    }
    @MyTest
    public void methodA() {
        System.out.println("methodAClassC");
    }
    @MyTest
    public void methodB() {
        System.out.println("methodBClassC");
    }
    @MyAfter
    public void after() {
        System.out.println("afterBClassC");
    }

}
