package com.example.springdemo.logger;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Logger {
    private static Logger LOG;

    private Logger() {
    }

    public static Logger getLOG() {
        if (LOG == null) {
            synchronized (Logger.class) {
                if (LOG == null) {
                    LOG = new Logger();
                }
            }
        }
        createFile();
        return LOG;
    }

    public String addLogs(String str) {
        try (FileWriter writer = new FileWriter("logs//log.txt", true)) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date date = new Date();
            writer.write(format.format(date) + ": " + str);
            writer.write('\n');
            writer.flush();
        } catch (IOException ignored) {
        }
        return str;
    }

    private static void createFile() {
        File dir = new File("logs");
        if (!Files.exists(dir.toPath())) {
            dir.mkdir();
            File file = new File("logs//log.txt");
            try {
                file.createNewFile();
            } catch (IOException ignored) {
            }
        }
    }
}
