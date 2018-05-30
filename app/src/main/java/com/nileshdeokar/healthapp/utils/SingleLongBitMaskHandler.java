package com.nileshdeokar.healthapp.utils;

/**
 * Created by niel on 27/05/18.
 */

public class SingleLongBitMaskHandler {

    private long healthValue = 0L;

    private static final int sizeOfIntInbits = 64;


    public boolean set(int position)
    {
        return setValue(position,true);
    }

    private boolean setValue(int position,boolean value) {

        int offset   = position % sizeOfIntInbits;
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

        int offset   = position % sizeOfIntInbits;
        long bit = (healthValue >> offset) & 1L;
        System.out.println("get : "+position + " " + (bit!=0));
        return bit!=0;
    }

    public void clear(int position)
    {
        setValue(position,false);
    }

    public Long toLongs()
    {
        return this.healthValue;
    }

    public void setLongvalue(Long longValue){
        healthValue = longValue;
    }

    public void toggleDisease(int diseaseToSet, boolean value) {
        if(value) set(diseaseToSet); else clear(diseaseToSet);
    }
}
