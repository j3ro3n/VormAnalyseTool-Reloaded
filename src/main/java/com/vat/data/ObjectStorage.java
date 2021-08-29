package com.vat.data;

import com.vat.shape.Shape;

import java.io.*;
import java.util.ArrayList;

public class ObjectStorage implements StorageInterface {

    //No location given for load data
    @Override
    public ArrayList<Shape> loadData() {
        throw new UnsupportedOperationException();
    }

    //Location given and load data and convert to a shape in list
    @Override
    public ArrayList<Shape> loadData(String location) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(location);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return (ArrayList<Shape>) objectInputStream.readObject();
    }

    //No location save object to storage
    @Override
    public void saveData(ArrayList<Shape> shapes) {
        throw new UnsupportedOperationException();
    }

    //Location given for save shape to storage
    @Override
    public void saveData(String location, ArrayList<Shape> shapes) throws Exception {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(location));
        objectOutputStream.writeObject(shapes);
    }

}
