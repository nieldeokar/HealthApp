package com.nileshdeokar.healthapp.utils

/**
 * Created by niel on 27/05/18.
 */

class SingleLongHandler {

    private var healthValue = 0L


    fun set(position: Int): Boolean {
        return setValue(position, true)
    }

    private fun setValue(position: Int, value: Boolean): Boolean {

        val offset = position % sizeOfLongInbits
        if (!value) {
            healthValue = healthValue and (1L shl offset).inv()
        } else {
            healthValue = healthValue or (1L shl offset)

        }
        println("set : $position $value")
        return true
    }

    operator fun get(position: Int): Boolean {

        val offset = position % sizeOfLongInbits
        val bit = healthValue shr offset and 1L
        println("get : " + position + " " + (bit != 0L))
        return bit != 0L
    }

    fun clear(position: Int) {
        setValue(position, false)
    }

    fun toLongs(): Long {
        return this.healthValue
    }

    fun setLongvalue(longvalue: Long) {
        healthValue = longvalue
    }

    fun toggleDisease(diseaseToSet: Int, value: Boolean) {
        if (value) set(diseaseToSet) else clear(diseaseToSet)
    }

    companion object {

        private const val sizeOfLongInbits = 64
    }
}
