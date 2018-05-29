package com.nileshdeokar.healthapp.utils


import java.nio.ByteBuffer

/**
 * Created by @nieldeokar on 27/05/18.
 */

class MultipleLongArrayBitMaskHandler {

    private var longs = LongArray(2)

    constructor() {}

    fun set(position: Int): Boolean {
        return setValue(position, true)
    }

    private fun setValue(position: Int, value: Boolean): Boolean {
        val location = position / sizeOfLongInbits
        if (location >= longs.size) {
            return false
        }
        val offset = position % sizeOfLongInbits
        if (!value) {
            longs[location] = longs[location] and (1L shl offset).inv()
        } else {
            longs[location] = longs[location] or (1L shl offset)

        }
        println("set : $position $value")
        return true
    }

    operator fun get(position: Int): Boolean {
        val location = position / sizeOfLongInbits
        if (location >= longs.size) {
            return false
        }
        val offset = position % sizeOfLongInbits
        val bit = longs[location] shr offset and 1L
        println("get : " + position + " " + (bit != 0L))
        return bit != 0L
    }

    /**
     * Constructor using total size of an array
     *
     * @param size size of long[] in bits
     */
    private constructor(size: Int) {
        if (size % sizeOfLongInbits != 0) {
            throw IllegalArgumentException("Size should be a multiple of 64")
        } else {
            longs = LongArray(size / 32)
        }
    }


    private constructor(value: LongArray) {
        this.longs = value
    }


    constructor(values: ByteArray) {
        if (values.size % 8 != 0) {
            throw IllegalArgumentException("Size of byte array should be multiple of 8 size is " + values.size)
        }
        val longsNeeded = values.size / 8
        this.longs = LongArray(longsNeeded)
        val buffer = ByteBuffer.wrap(values)
        for (i in 0 until longsNeeded) {
            longs[i] = buffer.long
        }
    }

    fun toLongs(): LongArray {
        return this.longs
    }

    fun toByteArray(): ByteArray {
        val byteBuffer = ByteBuffer.allocate(longs.size * 8)
        for (value in longs)
            byteBuffer.putLong(value)
        return byteBuffer.array()
    }


    fun clear(position: Int) {
        setValue(position, false)
    }

    fun toggle(position: Int) {
        val location = position / sizeOfLongInbits
        val offset = position % sizeOfLongInbits
        longs[location] = longs[location] xor (1L shl offset)

    }

    companion object {

        private const val sizeOfLongInbits = 64
    }

}