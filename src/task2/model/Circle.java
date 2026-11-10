package task2.model;

public class Circle extends Shape {
    private double radius;

    public Circle(String shapeColor, double radius) {
        super(shapeColor);
        this.radius = radius;
    }

    @Override
    public double calcArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void draw() {
        System.out.println("Малюємо коло кольором " + shapeColor);
    }

    @Override
    public String toString() {
        return "Коло       [" + super.toString() + ", радіус=" + String.format("%.2f", radius) + "]";
    }
}