package task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MaxWordsFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть шлях до текстового файлу (наприклад, test.txt): ");
        String filePath = scanner.nextLine();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            String maxWordsLine = "";
            int maxWordsCount = 0;

            while ((line = reader.readLine()) != null) {
                String[] words = line.trim().split("\\s+");
                int currentWordsCount = line.trim().isEmpty() ? 0 : words.length;

                if (currentWordsCount > maxWordsCount) {
                    maxWordsCount = currentWordsCount;
                    maxWordsLine = line;
                }
            }

            if (maxWordsCount > 0) {
                System.out.println("\nРядок з максимальною кількістю слів (" + maxWordsCount + " слів):");
                System.out.println(maxWordsLine);
            } else {
                System.out.println("Файл порожній або не містить слів.");
            }
        } catch (IOException e) {
            System.out.println("Помилка читання файлу (блок try-catch): " + e.getMessage());
        }
    }
}