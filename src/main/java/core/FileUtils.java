package core;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static List<String> read(String name) throws IOException {
        List<String> list = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new FileReader(name))) {
            String line;
            while ((line = r.readLine()) != null) {
                list.add(line);
            }
        }
        return list;
    }

    public static void write(String name, List<String> data) throws IOException {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(name))) {
            for (String line : data) {
                w.write(line);
                w.newLine();
            }
        }
    }
}