package task4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlTagCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть URL (наприклад, https://uk.wikipedia.org/wiki/Головна_сторінка): ");
        String urlString = scanner.nextLine();

        Map<String, Integer> tagMap = new HashMap<>();

        try {
            URL url = new URL(urlString);

            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String inputLine;
            Pattern pattern = Pattern.compile("<([a-zA-Z][a-zA-Z0-9]*)\\b[^>]*>");

            while ((inputLine = in.readLine()) != null) {
                Matcher matcher = pattern.matcher(inputLine);
                while (matcher.find()) {
                    String tag = matcher.group(1).toLowerCase();
                    tagMap.put(tag, tagMap.getOrDefault(tag, 0) + 1);
                }
            }
            in.close();

            if (tagMap.isEmpty()) {
                System.out.println("Тегів не знайдено.");
                return;
            }

            // a. Сортування в лексикографічному порядку
            System.out.println("\n--- a. Теги в лексикографічному порядку (за алфавітом) ---");
            Map<String, Integer> sortedByKey = new TreeMap<>(tagMap);
            for (Map.Entry<String, Integer> entry : sortedByKey.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            // b. Сортування за частотою появи
            System.out.println("\n--- b. Теги за зростанням частоти появи ---");
            List<Map.Entry<String, Integer>> list = new ArrayList<>(tagMap.entrySet());
            list.sort(Map.Entry.comparingByValue());
            for (Map.Entry<String, Integer> entry : list) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

        } catch (Exception e) {
            System.out.println("Помилка завантаження URL. Перевірте посилання або інтернет-з'єднання. " + e.getMessage());
        }
    }
}