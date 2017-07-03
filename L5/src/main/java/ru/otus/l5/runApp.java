package ru.otus.l5;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by SSS on 01.07.2017.
 */
public class runApp {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, NoSuchFieldException {
        TestFramework.run(TestClassA.class, TestClassB.class);
        //Результаты выполнения:
        //beforeClassA
        //methodAClassA
        //afterBClassA
        //beforeClassA
        //methodBClassA
        //afterBClassA
        //beforeClassB
        //methodAClassB
        //afterBClassB
        //beforeClassB
        //methodBClassB
        //afterBClassB

        TestFramework.run("ru.otus.l5");
        //Результаты выполнения:
//        beforeClassC
//        methodAClassC
//        afterBClassC
//        beforeClassC
//        methodBClassC
//        afterBClassC
//        beforeClassA
//        methodAClassA
//        afterBClassA
//        beforeClassA
//        methodBClassA
//        afterBClassA
//        beforeClassB
//        methodAClassB
//        afterBClassB
//        beforeClassB
//        methodBClassB
//        afterBClassB

        TestFramework.run("ru.otus.l5.inPack");
//        beforeClassC
//        methodAClassC
//        afterBClassC
//        beforeClassC
//        methodBClassC
//        afterBClassC
    }
}
