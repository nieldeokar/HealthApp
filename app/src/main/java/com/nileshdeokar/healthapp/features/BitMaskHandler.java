package com.nileshdeokar.healthapp.features;

import com.qtsoftware.qtconnect.features.DiseasesManager;

import java.nio.ByteBuffer;

public class BitMaskHandler {

    private long longs[] = new long[DiseasesManager.TOTAL_SIZE_OF_DISEASE_ARRAY]; // We are initialising it with 24 bytes

    private static final int sizeOfLongInbits = 64;

    public BitMaskHandler(){}

    public boolean set(int position)
    {
        return setValue(position,true);
    }

    private boolean setValue(int position,boolean value) {
        int location = (position)/ sizeOfLongInbits;
        if(location >= longs.length){
            return false;
        }
        int offset   = position % sizeOfLongInbits;
        if(!value) {
            longs[location] &= ~ (1L << offset);
        }
        else
        {
            longs[location] |= 1L << offset;

        }
        System.out.println("set : "+position + " " + value);
        return true;
    }

    public boolean get(int position) {
        int location = (position)/ sizeOfLongInbits;
        if(location >= longs.length){
            return false;
        }
        int offset   = position % sizeOfLongInbits;
        long bit = (longs[location] >> offset) & 1L;
        System.out.println("get : "+position + " " + (bit!=0));
        return bit!=0;
    }

    /**
     * Constructor using total size of an array
     *
     * @param size size of long[] in bits
     *
     */
    private BitMaskHandler(int size) {
        if(size % sizeOfLongInbits !=0)
        {
            throw new IllegalArgumentException("Size should be a multiple of 64");
        }
        else
        {
            longs = new long[size/32];
        }
    }


    private BitMaskHandler(long[] value)
    {
        this.longs = value;
    }

    /*public BitMaskHandler(long value)
    {
        this.longs = new long[]{value};
    }
*/
    public BitMaskHandler(byte[] values) {
        if(values.length%8!=0)
        {
            throw new IllegalArgumentException("Size of byte array should be multiple of 8");
        }
        int longsNeeded = values.length/8;
        this.longs = new long[longsNeeded];
        ByteBuffer buffer = ByteBuffer.wrap(values);
        for(int i=0;i<longsNeeded;i++)
        {
            longs[i]=buffer.getLong();
        }
    }

    public long[] toLongs()
    {
        return this.longs;
    }

    public byte[] toByteArray() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(longs.length*8);
        for(long value:longs)
            byteBuffer.putLong(value);
        return byteBuffer.array();
    }



    public void clear(int position)
    {
        setValue(position,false);
    }

    public void toggle(int position) {
        int location = (position)/ sizeOfLongInbits;
        int offset   = position % sizeOfLongInbits;
        longs[location] ^= 1L << offset;

    }

}