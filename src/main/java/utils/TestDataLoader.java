package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestDataLoader {
    public static String load(String relativePath) {
        try {
            return new String(Files.readAllBytes(Paths.get("src/test/resources/testdata/" + relativePath)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load test data from: " + relativePath, e);
        }
    }
}
