package com.vat.frame.shape;

import com.vat.frame.MainFrame;
import com.vat.shape.Shape;
import com.vat.shape.ShapeService;
import java.util.HashMap;

public class NewShape {

    public static Shape display(String shapeType) {
        ShapeScene scene = new ShapeScene();

        HashMap<String, Integer> data = scene.createWindowAndShow("Create a shape: " + shapeType, ShapeService.getFields(shapeType));

        if (data == null) {
            return null;
        }

        return MainFrame.shapeService.addShape(shapeType, data);
    }

}
