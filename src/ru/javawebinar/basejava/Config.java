package ru.javawebinar.basejava;

import ru.javawebinar.basejava.storage.SqlStorage;
import ru.javawebinar.basejava.storage.Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final File PROPS = new File("config\\resumes.properties");
    private static final Config INSTANCE = new Config();
    private final Properties PROPERTIES = new Properties();
    private File storageDir;
    private final Storage storage;

    public static Config get() {
        return INSTANCE;
    }

    private Config() {
        try (InputStream is = new FileInputStream(PROPS)) {
            PROPERTIES.load(is);
            storageDir = new File(PROPERTIES.getProperty("storage.dir"));
            storage = new SqlStorage(PROPERTIES.getProperty("db.url"), PROPERTIES.getProperty("db.user"), PROPERTIES.getProperty("db.password"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS.getAbsolutePath());
        }
    }

    public File getStorageDir() {
        return storageDir;
    }

    public Storage getStorage() {
        return storage;
    }

    public String getUrl() {
        return PROPERTIES.getProperty("db.url");
    }

    public String getUser() {
        return PROPERTIES.getProperty("db.user");
    }

    public String getPassword() {
        return PROPERTIES.getProperty("db.password");
    }
}
