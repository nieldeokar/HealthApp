package com.nileshdeokar.healthapp;

/**
 * Created by niel on 27/05/18.
 */

public class SIngleLongHandler {

    private long healthValue = 0L;

    private static final int sizeOfLongInbits = 64;


    public boolean set(int position)
    {
        return setValue(position,true);
    }

    private boolean setValue(int position,boolean value) {

        int offset   = position % sizeOfLongInbits ;
        if(!value) {
            healthValue &= ~ (1L << offset);
        }
        else
        {
            healthValue |= 1L << offset;

        }
        System.out.println("set : "+position + " " + value);
        return true;
    }

    public boolean get(int position) {

        int offset   = position % sizeOfLongInbits;
        long bit = (healthValue >> offset) & 1L;
        System.out.println("get : "+position + " " + (bit!=0));
        return bit!=0;
    }

    public void clear(int position)
    {
        setValue(position,false);
    }

    public long toLongs()
    {
        return this.healthValue;
    }

    public void setLongvalue(long longvalue){
        healthValue = longvalue;
    }

    public void toggleDisease(int diseaseToSet, boolean value) {
        if(value) set(diseaseToSet); else clear(diseaseToSet);
    }
}
