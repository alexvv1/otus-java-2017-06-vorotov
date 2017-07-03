package ru.otus.l5;

import ru.otus.l5.Annotation.MyAfter;
import ru.otus.l5.Annotation.MyBefore;
import ru.otus.l5.Annotation.MyTest;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TestFramework {

    public static void run(Class... testClasses) throws InvocationTargetException, IllegalAccessException {
        for (Class cl : testClasses) {
            List<Method> beforeMethods = getMethodsAnnotationPresent(cl, MyBefore.class);
            List<Method> afterMethods = getMethodsAnnotationPresent(cl, MyAfter.class);

            for (Method mth : getMethodsAnnotationPresent(cl, MyTest.class)) {
                Object obj = ReflectionHelper.instantiate(cl);

                for (Method beforeMth : beforeMethods) {
                    beforeMth.invoke(obj, null);
                }

                mth.invoke(obj, null);

                for (Method afterMth : afterMethods) {
                    afterMth.invoke(obj, null);
                }
            }
        }
    }


    public static void run(String packageName) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        URL root = Thread.currentThread().getContextClassLoader().getResource(packageName.replace(".", "/"));
        //Перед путем стоит символ "/", который надо убрать
        String rootPath = root.getPath().replaceAll("^/", "");
        List<String> nameClasses = listFilesForFolder(new File(root.getFile()));
        nameClasses.replaceAll(k->k.substring(rootPath.length() - packageName.length())
                .replaceFirst(".class$", "")
                .replace("\\","."));
        for (String nameClass : nameClasses) {
            run(Class.forName(nameClass));
        }
    }

    private static List<String> listFilesForFolder(final File folder) {
        ArrayList<String> classesName = new ArrayList<>();

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                classesName.addAll(listFilesForFolder(fileEntry));
            } else {
                classesName.add(fileEntry.getPath());
            }
        }
        return classesName;
    }

    //Получить методы класса с определённой аннотацией
    private static List<Method> getMethodsAnnotationPresent(Class clazz, Class<? extends Annotation> annotationClass) {
        ArrayList<Method> methodsAnnotationPresent = new ArrayList<>();

        for (Method mth : clazz.getMethods()) {
            if (mth.isAnnotationPresent(annotationClass)) {
                methodsAnnotationPresent.add(mth);
            }
        }
        return methodsAnnotationPresent;
    }


}
