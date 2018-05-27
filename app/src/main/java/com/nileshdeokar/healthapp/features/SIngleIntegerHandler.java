package com.nileshdeokar.healthapp.features;

/**
 * Created by niel on 27/05/18.
 */

public class SIngleIntegerHandler {

    private int healthValue = 0;

    private static final int sizeOfLongInbits = 64;


    public boolean set(int position)
    {
        return setValue(position,true);
    }

    private boolean setValue(int position,boolean value) {

        int offset   = position % sizeOfLongInbits ;
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

        int offset   = position % sizeOfLongInbits;
        long bit = (healthValue >> offset) & 1;
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

    public void setLongvalue(int longvalue){
        healthValue = longvalue;
    }

    public void toggleDisease(int diseaseToSet, boolean value) {
        if(value) set(diseaseToSet); else clear(diseaseToSet);
    }
}
