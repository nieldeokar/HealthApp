package com.qtsoftware.qtconnect.features

import com.nileshdeokar.healthapp.utils.MultipleLongArrayBitMaskHandler

class DiseasesManager {

    private var mBitMaskHandler = MultipleLongArrayBitMaskHandler()

    companion object {

        const val TOTAL_SIZE_OF_DISEASE_ARRAY = 1

        /*
         * Features declaration starts here
         * Total size is 3L = 64 * 3 = 192 bits = 24 bytes
         * Can be changed using TOTAL_SIZE_OF_SUPPORTED_FEATURES_ARRAY
         */
        const val CHICKEN_POX           = 1
        const val MEASLES               = 2
        const val MUMPS                 = 3
        const val ASTHMA                = 4
        const val THYROID               = 5
        const val DIABETES              = 6
        const val ANEMIA                = 7
        const val KIDNEY_STONE          = 8
        const val MALARIA               = 9
        const val HEART_ATTACK          = 10

    }


    fun setByteArray(diseasesArry : ByteArray) {
        mBitMaskHandler = MultipleLongArrayBitMaskHandler(diseasesArry)
    }

    fun getByteArray() : ByteArray {
        return mBitMaskHandler.toByteArray()
    }

    fun getLongValue() : LongArray? {
        return   mBitMaskHandler.toLongs()
    }

    fun getDisease(diseaseToCheck: Int): Boolean {
        return mBitMaskHandler.get(diseaseToCheck)
    }

    fun toggleDisease(diseaseToSet: Int, value: Boolean) {
        if(value) mBitMaskHandler.set(diseaseToSet) else mBitMaskHandler.clear(diseaseToSet)
    }

}