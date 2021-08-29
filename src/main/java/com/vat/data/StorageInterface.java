package com.vat.data;

import com.vat.shape.Shape;
import java.sql.SQLException;
import java.util.ArrayList;

public interface StorageInterface {

    //Get data from MariaDB vat database
    ArrayList<Shape> loadData() throws Exception;

    //Get data from a directory location on device
    ArrayList<Shape> loadData(String location) throws Exception;

    //Insert list of shapes to the vat database
    void saveData(ArrayList<Shape> shapes) throws Exception;

    //Save list of shapes to a filepath on device
    void saveData(String location, ArrayList<Shape> shapes) throws Exception;

}
