package task2.view;

import task2.model.Shape;

public class View {
    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printShapes(Shape[] shapes) {
        if (shapes == null || shapes.length == 0) {
            System.out.println("Масив порожній.");
            return;
        }
        for (Shape shape : shapes) {
            System.out.println(shape.toString());
        }
        System.out.println("--------------------------------------------------");
    }

    public void showMenu() {
        System.out.println("\n--- МЕНЮ ---");
        System.out.println("1. Згенерувати новий набір фігур");
        System.out.println("2. Відобразити масив фігур та статистику");
        System.out.println("3. Пошук фігур за кольором");
        System.out.println("4. Зберегти об'єкти у файл");
        System.out.println("5. Завантажити об'єкти з файлу");
        System.out.println("0. Вихід");
        System.out.print("Ваш вибір: ");
    }
}