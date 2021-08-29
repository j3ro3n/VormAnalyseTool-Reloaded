package com.vat.shape;

import java.util.HashMap;

public class Cuboid implements Shape {

    public static HashMap<String, String> fields = new HashMap<String, String>() {{
        put("length", "Length:");
        put("width", "Width:");
        put("height", "Height:");
    }};

    public final String type = "Cuboid";

    private int length;

    private int width;

    private int height;

    public Cuboid(int length, int width, int height) {
        this.setLength(length);
        this.setWidth(width);
        this.setHeight(height);
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public HashMap<String, String> getFields() {
        return Cuboid.fields;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public HashMap<String, Integer> getData() {
        HashMap<String, Integer> data = new HashMap<>();

        data.put("length", this.length);
        data.put("width", this.width);
        data.put("height", this.height);

        return data;
    }

    @Override
    public void setData(HashMap<String, Integer> data) {
        this.length = data.getOrDefault("length", 0);
        this.width = data.getOrDefault("width", 0);
        this.height = data.getOrDefault("height", 0);
    }

    @Override
    public double calculateVolume() {
        return this.length * this.width * this.height;
    }

    @Override
    public String toString() {
        return String.format("Cuboid (length: %d, width: %d, height: %d)", length, width, height);
    }
}