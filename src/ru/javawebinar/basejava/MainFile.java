package ru.javawebinar.basejava;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;

import static java.lang.Integer.MAX_VALUE;

public class MainFile {

    public static void main(String[] args) {
//
//        String filePath = ".\\.gitignore";
//
//        File file = new File(filePath);
//        try {
//            System.out.println(file.getCanonicalPath());
//        } catch (IOException e) {
//            throw new RuntimeException("Error", e);
//        }
//
//        File dir = new File("./src/ru/javawebinar/basejava");
//        System.out.println(dir.isDirectory());
//        String[] list = dir.list();
//        if (list != null) {
//            for (String name : list) {
//                System.out.println(name);
//            }
//        }
//
//        try (FileInputStream fis = new FileInputStream(filePath)) {
//            System.out.println(fis.read());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
////        try {
////            Files.walkFileTree(pathSource,new HashSet<>(), MAX_VALUE, new MyFileVisitor());
////        } catch (IOException e) {
////            e.printStackTrace();
////        }

        //First solution

//        File folder = new File((args.length >= 1 && args[0].length() > 0) ? args[0] : ".");
//
//        if (!folder.isDirectory()) {
//            throw new IllegalArgumentException("Invalid directory: " + folder.getName());
//        }
//        int level = 0;
//        System.out.println(renderFolder(folder, level, new StringBuilder(), false));

        try {
            searchFile(new File("C:/projects/basejava/"), "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static StringBuilder renderFolder(File folder, int level, StringBuilder sb, boolean isLast) {
        indent(sb, level, isLast).append("\u001B[35m ").append(folder.getName()).append("\u001B[0m" + "\n");

        File[] objects = folder.listFiles();

        for (int i = 0; i < objects.length; i++) {
            boolean last = ((i + 1) == objects.length);
            if (objects[i].isDirectory()) {
                renderFolder(objects[i], level + 1, sb, last);
            } else {
                renderFile(objects[i], level + 1, sb, last);
            }
        }
        return sb;
    }

    private static StringBuilder renderFile(File file, int level, StringBuilder sb, boolean isLast) {
        return indent(sb, level, isLast).append("- ").append(file.getName()).append("\n");
    }

    private static StringBuilder indent(StringBuilder sb, int level, boolean isLast) {
        for (int i = 1; i < level; i++) {
            sb.append(" ");
        }
        return sb;
    }

    //Second solution
    private static void searchFile(File dir, String add) throws IOException {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println(add + " " + "* " + "\u001B[35m" + file.getName() + "\u001B[0m");
                    searchFile(file, add + "  ");
                } else {
                    System.out.println(add + "- " + "\u001B[34m" + file.getName() + "\u001B[0m");
                }
            }
        }
    }

}
