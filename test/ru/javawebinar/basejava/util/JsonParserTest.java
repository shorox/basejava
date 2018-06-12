package ru.javawebinar.basejava.util;

import org.junit.Assert;
import org.junit.Test;
import ru.javawebinar.basejava.model.Category;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.StringCategory;

import static ru.javawebinar.basejava.TestData.R1;

public class JsonParserTest {
    @Test
    public void testResume() {
        String json = JsonParser.write(R1);
        System.out.println(json);
        Resume resume = JsonParser.read(json, Resume.class);
        Assert.assertEquals(R1, resume);
    }

    @Test
    public void write() {
        Category category1 = new StringCategory("Objective1");
        String json = JsonParser.write(category1, Category.class);
        System.out.println(json);
        Category section2 = JsonParser.read(json, Category.class);
        Assert.assertEquals(category1, section2);
    }
}
