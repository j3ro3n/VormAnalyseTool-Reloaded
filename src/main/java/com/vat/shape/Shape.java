package com.vat.shape;

import java.io.Serializable;
import java.util.HashMap;

public interface Shape extends Serializable {
    //Store type of shape
    String getType();

    //Calculate the volume of the shape
    double calculateVolume();

    //Convert teh shape into text for saving to textfile
    String toString ();

    //Receives the fields of the shape
    HashMap<String, String> getFields();

    //Get the data for the shape
    HashMap<String, Integer> getData();

    //Set the data for the shape
    void setData(HashMap<String, Integer> data);
}
