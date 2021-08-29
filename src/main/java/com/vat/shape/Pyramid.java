package com.vat.shape;

import java.util.HashMap;

public class Pyramid implements Shape {

    //The fields of the pyramid
    public static HashMap<String, String> fields = new HashMap<String, String>() {{
        put("length", "Length:");
        put("width", "Width:");
        put("height", "Height:");
    }};

    //The type of the shape
    public final String type = "Pyramid";
    private int length;
    private int width;
    private int height;

    //Create a pyramid with the given parameters
    public Pyramid(int length, int width, int height) {
        this.setLength(length);
        this.setWidth(width);
        this.setHeight(height);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    //All fields of the pyramid
    @Override
    public HashMap<String, String> getFields() {
        return Pyramid.fields;
    }

    //The type of the pyramid in string
    @Override
    public String getType() {
        return this.type;
    }

    //Pyramid to data hashmap
    @Override
    public HashMap<String, Integer> getData() {
        HashMap<String, Integer> data = new HashMap<>();

        data.put("length", this.length);
        data.put("width", this.width);
        data.put("height", this.height);

        return data;
    }

    //Convert hashmap into properties of pyramid
    @Override
    public void setData(HashMap<String, Integer> data) {
        this.setLength(data.getOrDefault("length", 0));
        this.setWidth(data.getOrDefault("width", 0));
        this.setHeight(data.getOrDefault("height", 0));
    }

    //Calculate volume pyramid
    @Override
    public double calculateVolume() {
        return ((width * length) * height) / 3.0;
    }

    //Convert calculation of volume to string
    @Override
    public String toString() {
        return String.format("Pyramid (length: %d, width: %d, height: %d)", length, width, height);
    }
}
