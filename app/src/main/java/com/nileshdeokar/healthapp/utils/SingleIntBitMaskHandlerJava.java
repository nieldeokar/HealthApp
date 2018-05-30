package com.nileshdeokar.healthapp.utils;

/**
 * Created by niel on 27/05/18.
 */

public class SingleIntBitMaskHandlerJava {

    private int healthValue = 0;

    private static final int sizeOfIntInbits = 32;


    public boolean set(int position)
    {
        return setValue(position,true);
    }

    private boolean setValue(int position,boolean value) {

        int offset   = position % sizeOfIntInbits;
        if(!value) {
            healthValue &= ~ (1 << offset);
        }
        else
        {
            healthValue |= 1 << offset;

        }
        System.out.println("set : "+position + " " + value);
        return true;
    }

    public boolean get(int position) {

        int offset   = position % sizeOfIntInbits;
        long bit = (healthValue >> offset) & 1;
        System.out.println("get : "+position + " " + (bit!=0));
        return bit!=0;
    }

    public void clear(int position)
    {
        setValue(position,false);
    }

    public int toInts()
    {
        return this.healthValue;
    }

    public void setIntvalue(int intvalue){
        healthValue = intvalue;
    }

    public void toggleDisease(int diseaseToSet, boolean value) {
        if(value) set(diseaseToSet); else clear(diseaseToSet);
    }
}
