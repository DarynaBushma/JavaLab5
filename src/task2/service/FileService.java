package task2.service;

import task2.model.Shape;
import java.io.*;

public class FileService {
    public void saveShapes(Shape[] shapes, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(shapes);
        }
    }

    public Shape[] loadShapes(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (Shape[]) ois.readObject();
        }
    }
}