package task2.controller;

import task2.model.*;
import task2.view.View;
import task2.service.FileService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Controller {
    private Shape[] shapes;
    private View view;
    private FileService fileService;

    public Controller(View view) {
        this.view = view;
        this.fileService = new FileService();
        this.shapes = generateShapes(10);
    }

    private Shape[] generateShapes(int size) {
        Shape[] generatedShapes = new Shape[size];
        String[] colors = {"Червоний", "Синій", "Зелений", "Жовтий", "Чорний", "Білий"};
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            String color = colors[random.nextInt(colors.length)];
            int type = random.nextInt(3);
            switch (type) {
                case 0: generatedShapes[i] = new Rectangle(color, random.nextDouble() * 10 + 2, random.nextDouble() * 10 + 2); break;
                case 1: generatedShapes[i] = new Circle(color, random.nextDouble() * 10 + 2); break;
                case 2: generatedShapes[i] = new Triangle(color, random.nextDouble() * 10 + 2, random.nextDouble() * 10 + 2); break;
            }
        }
        return generatedShapes;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            view.showMenu();
            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        shapes = generateShapes(10);
                        view.printMessage("Згенеровано новий набір фігур.");
                        break;
                    case "2":
                        displayStats();
                        break;
                    case "3":
                        view.printMessage("Введіть колір для пошуку (Дані з клавіатури - Вимога 5): ");
                        String searchColor = scanner.nextLine();
                        searchByColor(searchColor);
                        break;
                    case "4":
                        view.printMessage("Введіть шлях та ім'я файлу (наприклад, data.ser): ");
                        String savePath = scanner.nextLine();
                        fileService.saveShapes(shapes, savePath);
                        view.printMessage("Дані успішно збережено!");
                        break;
                    case "5":
                        view.printMessage("Введіть шлях та ім'я файлу для завантаження: ");
                        String loadPath = scanner.nextLine();
                        shapes = fileService.loadShapes(loadPath);
                        view.printMessage("Дані успішно завантажено!");
                        break;
                    case "0":
                        running = false;
                        break;
                    default:
                        view.printMessage("Невірний вибір.");
                }
            } catch (IOException e) {
                view.printMessage("Помилка роботи з файлом: " + e.getMessage());
            } catch (ClassNotFoundException e) {
                view.printMessage("Помилка завантаження класу: " + e.getMessage());
            }
        }
    }

    private void searchByColor(String color) {
        boolean found = false;
        for (Shape s : shapes) {
            if (s.getShapeColor().equalsIgnoreCase(color)) {
                view.printMessage(s.toString());
                found = true;
            }
        }
        if (!found) view.printMessage("Фігур кольору '" + color + "' не знайдено.");
    }

    private void displayStats() {
        view.printMessage("\n=== Вихідний масив фігур ===");
        view.printShapes(shapes);

        Arrays.sort(shapes, new AreaComparator());
        view.printMessage("=== Сортування за ПЛОЩЕЮ ===");
        view.printShapes(shapes);
    }
}