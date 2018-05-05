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

        String filePath = ".\\.gitignore";
        Path pathSource = Paths.get("C:/projects/basejava/");

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src/ru/javawebinar/basejava");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Files.walkFileTree(pathSource,new HashSet<>(), MAX_VALUE, new MyFileVisitor());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

