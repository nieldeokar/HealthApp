package com.nileshdeokar.healthapp.utils

/**
 * Created by niel on 27/05/18.
 */

class SingleIntBitMaskHandler {

    var intValue = 0

    fun set(position: Int): Boolean {
        return setValue(position, true)
    }

    private fun setValue(position: Int, value: Boolean): Boolean {

        val offset = position % sizeOfIntInbits
        intValue = if (!value) {
            intValue and (1 shl offset).inv()
        } else {
            intValue or (1 shl offset)

        }
        println("set : $position $value")
        return true
    }

    operator fun get(position: Int): Boolean {

        val offset = position % sizeOfIntInbits
        val bit = (intValue shr offset and 1).toLong()
        println("get : " + position + " " + (bit != 0L))
        return bit != 0L
    }

    fun clear(position: Int) {
        setValue(position, false)
    }

    fun toggleDisease(diseaseToSet: Int, value: Boolean) {
        if (value) set(diseaseToSet) else clear(diseaseToSet)
    }

    companion object {

        private const val sizeOfIntInbits = 32
    }
}
