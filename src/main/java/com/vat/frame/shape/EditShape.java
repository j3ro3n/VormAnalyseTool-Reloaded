package com.vat.frame.shape;

import com.vat.shape.Shape;
import java.util.HashMap;

public class EditShape extends ShapeScene {
    public static Shape display(Shape shape) {
        ShapeScene scene = new ShapeScene();
        System.out.println("Shape: " + shape);
        HashMap<String, Integer> data = scene.createWindowAndShow("Edit shape", shape.getFields(), shape.getData());
        if (data != null) {
            shape.setData(data);
        }
        return shape;
    }
}
