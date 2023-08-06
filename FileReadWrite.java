import java.io.*;
import java.util.Scanner;

public class FileReadWrite {
    private static final String PATH = "./src/main/java/";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    public void writeToFile(String filename, String data) {
        String currentData = null;
        File file = new File(PATH + filename);

        if (file.exists()) {
            currentData = readFromFile(filename);
        }

        try (FileWriter writer = new FileWriter(PATH + filename)) {
            if (currentData != null) {
                writer.write(currentData + data);
            } else {
                writer.write(data);
            }
            System.out.println(ANSI_GREEN + "Данные сохранены в файл: " + filename + ANSI_RESET);
        } catch (IOException e) {
            System.err.println(ANSI_RED + "Ошибка записи в файл: " + e.getMessage() + ANSI_RESET);
            e.printStackTrace();
        }
    }

    public String readFromFile(String filename) {
        String contents = "";

        try {
            File file = new File(PATH + filename);
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                contents = contents.concat(reader.nextLine() + "\n");
            }
            reader.close();

        } catch (FileNotFoundException e) {
            System.err.println(ANSI_RED + "Ошибка чтения из файла: " + e.getMessage() + ANSI_RESET);
            e.printStackTrace();
        }
        return contents;
    }
}
