package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainReflection {

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException {

        Resume resume = new Resume();
        Field field = resume.getClass().getDeclaredFields()[0];
        Method method = Resume.class.getMethod("toString", null);

        field.setAccessible(true);

        System.out.println(field.getName());
        System.out.println(field.get(resume));

        field.set(resume, "new_uuid");

        System.out.println(resume);
        System.out.println(method.getReturnType());
    }
}
