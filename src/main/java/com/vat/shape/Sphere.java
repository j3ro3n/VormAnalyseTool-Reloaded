package com.vat.shape;

import java.util.HashMap;

public class Sphere implements Shape {

    public static HashMap<String, String> fields = new HashMap<String, String>() {{
        put("radius", "Radius:");
    }};

    public final String type = "Sphere";

    private int radius;

    public Sphere(int radius) {
        this.setRadius(radius);
    }

    public int getRadius() {
        return this.radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public HashMap<String, String> getFields() {
        return Sphere.fields;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public HashMap<String, Integer> getData() {
        HashMap<String, Integer> data = new HashMap<>();

        data.put("radius", this.radius);

        return data;
    }

    @Override
    public void setData(HashMap<String, Integer> data) {
        this.radius = data.getOrDefault("radius", 0);
    }

    @Override
    public double calculateVolume() {
        return (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
    }

    @Override
    public String toString() {
        return String.format("Bol (radius: %d)", radius);
    }
}