package com.vat.shape;


import java.util.HashMap;

public class Cone implements Shape {

    //Fields for shape cone
    public static HashMap<String, String> fields = new HashMap<String, String>() {{
        put("radius", "Radius:");
        put("height", "Height:");
    }};

    //Type
    public final String type = "Cone";

    //Radius
    private int radius;

    //Height
    private int height;

    //Create cone
    public Cone(int radius, int height) {
        this.setRadius(radius);
        this.setHeight(height);
    }

    //Get radius
    public int getRadius() {
        return radius;
    }

    //Sets radius
    public void setRadius(int radius) {
        this.radius = radius;
    }

    //Get height
    public int getHeight() {
        return height;
    }

    //Set height
    public void setHeight(int height) {
        this.height = height;
    }

    //Get all fields
    @Override
    public HashMap<String, String> getFields() {
        return Cone.fields;
    }

    //Get type in string
    @Override
    public String getType() {
        return this.type;
    }

    //Convert to hashmap
    @Override
    public HashMap<String, Integer> getData() {
        HashMap<String, Integer> data = new HashMap<>();

        data.put("radius", this.radius);
        data.put("height", this.height);

        return data;
    }

    //Convert data to properties of cone
    @Override
    public void setData(HashMap<String, Integer> data) {
        this.radius = data.getOrDefault("radius", 0);
        this.height = data.getOrDefault("height", 0);
    }

    //Calculate volume
    @Override
    public double calculateVolume() {
        return (1.0 / 3.0) * Math.PI * Math.pow(this.radius, 2) * this.height;
    }

    @Override
    public String toString() {
        return String.format("Cone (radius: %d, height: %d)", radius, height);
    }
}
