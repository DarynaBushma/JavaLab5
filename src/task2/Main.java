package task2;

import task2.controller.Controller;
import task2.view.View;

public class Main {
    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        controller.run();
    }
}