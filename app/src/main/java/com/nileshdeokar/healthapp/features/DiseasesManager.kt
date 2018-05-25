package com.qtsoftware.qtconnect.features

import com.nileshdeokar.healthapp.features.BitMaskHandler

class DiseasesManager {

    private var mBitMaskHandler = BitMaskHandler()

    companion object {

        const val TOTAL_SIZE_OF_DISEASE_ARRAY = 1

        /*
         * Features declaration starts here
         * Total size is 3L = 64 * 3 = 192 bits = 24 bytes
         * Can be changed using TOTAL_SIZE_OF_SUPPORTED_FEATURES_ARRAY
         */
        const val CHICKEN_POX           = 1
        const val MEASLES               = 2
        const val ASTHMA                = 3
        const val THYROID               = 4
        const val DIABETES              = 5
        const val ANEMIA                = 6
        const val KIDNEY_STONE          = 7
        const val MALARIA               = 8
        const val HEART_ATTACK          = 9
        const val MUMPS                 = 10

    }

    init {
        val list = ArrayList<Int>()
        list.add(CHICKEN_POX)
        list.add(MEASLES)
        list.add(ASTHMA)
        list.add(THYROID)
        list.add(DIABETES)
        list.add(ANEMIA)
        list.add(KIDNEY_STONE)
        list.add(MALARIA)
        list.add(HEART_ATTACK)

        initialise(list)
    }


    private fun initialise(list: ArrayList<Int>) {
        for (feature in list) {
            mBitMaskHandler.set(feature)
        }
    }


    fun getDisease(diseaseToCheck: Int): Boolean {
        return mBitMaskHandler.get(diseaseToCheck)
    }

    fun toggleDisease(diseaseToSet: Int, value: Boolean) {
        if(value) mBitMaskHandler.set(diseaseToSet) else mBitMaskHandler.clear(diseaseToSet)
    }

}