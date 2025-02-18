package com.musicreviewer.music_reviewer.extras;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;

public class MockData {

    public final static String[] review1 = new String[7];

    static {

        Resource file = null;
        String fileContent = null;

        for (int i = 0; i < 7; i++) {
            try {
                file = new ClassPathResource("mockData/review" + (i + 1) + ".txt");

                fileContent = new String(Files.readAllBytes(file.getFile().toPath()));

            } catch (IOException exception) {
                exception.printStackTrace();
            }

            review1[i] = fileContent;
        }


    }
}
