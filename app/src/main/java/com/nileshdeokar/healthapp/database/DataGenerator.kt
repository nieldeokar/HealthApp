package com.nileshdeokar.healthapp.database

import com.nileshdeokar.healthapp.database.entity.PatientEntity
import com.nileshdeokar.healthapp.utils.SingleIntBitMaskHandler
import com.qtsoftware.qtconnect.features.DiseasesManager

class DataGenerator {

    companion object {

    fun generatePatients(): List<PatientEntity> {
        val patients = ArrayList<PatientEntity>()
        for (i in 1..10) {
            val patient = PatientEntity()
            val diseasesManager = SingleIntBitMaskHandler()

            patient.name = "PatientEntity " + i
            patient.age = i * 2 + 17

            if (i % 2 == 0) {
                patient.sex = "M"


                diseasesManager.set(DiseasesManager.MEASLES)
                diseasesManager.set(DiseasesManager.ASTHMA)
                diseasesManager.set(DiseasesManager.DIABETES)
                diseasesManager.set(DiseasesManager.KIDNEY_STONE)
                diseasesManager.set(DiseasesManager.HEART_ATTACK)

            } else {
                patient.sex = "F"


                diseasesManager.set(DiseasesManager.CHICKEN_POX)
                diseasesManager.set(DiseasesManager.MUMPS)
                diseasesManager.set(DiseasesManager.THYROID)
                diseasesManager.set(DiseasesManager.ANEMIA)
                diseasesManager.set(DiseasesManager.MALARIA)
            }

            patient.medicalHistory = diseasesManager.toInts()

            patients.add(patient)
        }
        return patients
    }
    }
}