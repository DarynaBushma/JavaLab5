package task2.model;
import java.util.Comparator;

public class AreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape s1, Shape s2) {
        return Double.compare(s1.calcArea(), s2.calcArea());
    }
}