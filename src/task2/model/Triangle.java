package task2.model;

public class Triangle extends Shape {
    private double base;
    private double height;

    public Triangle(String shapeColor, double base, double height) {
        super(shapeColor);
        this.base = base;
        this.height = height;
    }

    @Override
    public double calcArea() {
        return 0.5 * base * height;
    }

    @Override
    public void draw() {
        System.out.println("Малюємо трикутник кольором " + shapeColor);
    }

    @Override
    public String toString() {
        return "Трикутник  [" + super.toString() + ", основа=" + String.format("%.2f", base) + ", висота=" + String.format("%.2f", height) + "]";
    }
}