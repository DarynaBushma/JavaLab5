package task3;

import java.io.*;
import java.util.Scanner;

// a. Клас для шифрування
class EncryptFilterWriter extends FilterWriter {
    private final int key;
    public EncryptFilterWriter(Writer out, int key) {
        super(out);
        this.key = key;
    }
    @Override
    public void write(int c) throws IOException {
        super.write(c + key);
    }
    @Override
    public void write(String str, int off, int len) throws IOException {
        for (int i = off; i < off + len; i++) {
            write(str.charAt(i));
        }
    }
}

// b. Клас для дешифрування
class DecryptFilterReader extends FilterReader {
    private final int key;
    public DecryptFilterReader(Reader in, int key) {
        super(in);
        this.key = key;
    }
    @Override
    public int read() throws IOException {
        int c = super.read();
        if (c == -1) return -1;
        return c - key;
    }
}

public class CipherApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть текст для шифрування: ");
        String text = scanner.nextLine();

        System.out.print("Введіть ключовий символ (наприклад, 'K'): ");
        String keyInput = scanner.nextLine();
        int key = keyInput.isEmpty() ? 1 : keyInput.charAt(0);

        System.out.print("Введіть шлях та ім'я файлу для збереження (наприклад, encrypted.txt): ");
        String fileName = scanner.nextLine();

        // c. Використання FilterWriter для шифрування
        try (EncryptFilterWriter efw = new EncryptFilterWriter(new FileWriter(fileName), key)) {
            efw.write(text);
            System.out.println("Зашифровано у файл: " + fileName);
        } catch (IOException e) {
            System.out.println("Помилка при записі у файл: " + e.getMessage());
        }

        // c. Використання FilterReader для дешифрування
        try (DecryptFilterReader dfr = new DecryptFilterReader(new FileReader(fileName), key)) {
            StringBuilder decrypted = new StringBuilder();
            int c;
            while ((c = dfr.read()) != -1) {
                decrypted.append((char) c);
            }
            System.out.println("Розшифрований текст: " + decrypted);
        } catch (IOException e) {
            System.out.println("Помилка при читанні з файлу: " + e.getMessage());
        }
    }
}