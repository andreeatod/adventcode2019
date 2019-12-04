package common;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFromFile {

    public static List<Integer> readIntegers(String fileName) {
        List<Integer> modules = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNext()) {
                modules.add(scanner.nextInt());
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        }

        return modules;
    }
}
