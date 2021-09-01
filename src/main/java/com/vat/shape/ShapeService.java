package com.vat.shape;

import java.util.ArrayList;
import java.util.HashMap;

public class ShapeService {

    //List of all shapes in the shape service
    private ArrayList<Shape> shapes = new ArrayList<>();

    //Get the fields for the shape type
    public static HashMap<String, String> getFields(String shapeType) {
        switch (shapeType) {
            case "Cuboid":
                return Cuboid.fields;
            case "Sphere":
                return Sphere.fields;
            case "Cylinder":
                return Cylinder.fields;
            case "Cone":
                return Cone.fields;
            case "Cube":
                return Cube.fields;
            case "Pyramid":
                return Pyramid.fields;
            default:
                return null;
        }
    }

    //Get all current shapes
    public ArrayList<Shape> getShapes() {
        return this.shapes;
    }

    //Set the shapes
    public void setShapes(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }

    //Calculate all the volumes combined in the shape service
    public double calculateTotalVolume() {
        double totalVolume = 0.0;

        for (Shape shape : shapes) {
            totalVolume += shape.calculateVolume();
        }

        return totalVolume;
    }

    //Add shape to list
    public void addShape(Shape shape) {
        this.shapes.add(shape);
    }

    //Add Shape
    public Shape addShape(String shapeType, HashMap<String, Integer> data) {
        Shape newShape;

        switch (shapeType) {
            case "Cuboid":
                newShape = new Cuboid(data.get("length"), data.get("width"), data.get("height"));
                break;
            case "Sphere":
                newShape = new Sphere(data.get("radius"));
                break;
            case "Cylinder":
                newShape = new Cylinder(data.get("radius"), data.get("height"));
                break;
            case "Cone":
                newShape = new Cone(data.get("radius"), data.get("height"));
                break;
            case "Cube":
                newShape = new Cube(data.get("width"));
                break;
            case "Pyramid":
                newShape = new Pyramid(data.get("length"), data.get("width"), data.get("height"));
                break;
            default:
                return null;
        }

        this.shapes.add(newShape);
        return newShape;
    }
}