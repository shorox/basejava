package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainString {

    public static void main(String[] args) {

        String[] strArray = new String[]{"1", "2", "3", "4", "5"};
//        String result = "";
        StringBuilder sb = new StringBuilder();
        for (String str : strArray) {
            sb.append(str).append(", ");
        }
        System.out.println(sb.toString());

        String str1 = "abc";
        String str3 = "c";
        String str2 = ("ab" + str3).intern();
        System.out.println(str1 == str2);

        List<String> list = new ArrayList<>(Arrays.asList("ONE", "TWO", "THREE"));

        String fromList = list.stream().collect(Collectors.joining("/n"));

        System.out.println(fromList);
        List<String> listOut = new ArrayList<>(Arrays.asList(fromList.split("/n")));
        listOut.forEach(System.out::println);
    }
}