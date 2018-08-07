package com.nileshdeokar.healthapp;

/**
 * Created by nieldeokar on 12/07/18.
 */
public class NStringBuilder {

    static final int INITIAL_SIZE = 10;

    char[] value = new char[INITIAL_SIZE];

    int currentLength = 0;

    public void append(String data){
        if(data == null) data = "null";
        if(value.length < data.length() || currentLength+data.length() > value.length){
            //resize
            resizeArray(data);
        }
        peformCopy(data);
    }

    private void peformCopy(String data) {
        char[] tempArray = data.toCharArray();
        System.arraycopy(tempArray,0,value,currentLength,tempArray.length);
        currentLength += tempArray.length;
    }

    private void resizeArray(String data) {
        char[] newArray = new char[value.length + data.length() + INITIAL_SIZE];
        System.arraycopy(value,0,newArray,0,value.length);
        value = newArray;
        System.out.println("Resized");
    }

    @Override
    public String toString() {
        char[] stringArry = new char[currentLength];
        System.arraycopy(value,0,stringArry,0,currentLength);
        return new String(stringArry);
    }
}
