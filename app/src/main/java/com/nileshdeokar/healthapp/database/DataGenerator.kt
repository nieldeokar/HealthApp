package com.nileshdeokar.healthapp.database

import com.nileshdeokar.healthapp.database.entity.PatientEntity
import com.nileshdeokar.healthapp.utils.Constants
import com.nileshdeokar.healthapp.utils.SingleIntBitMaskHandler
/**
 * Created by @nieldeokar on 27/05/18.
 */

class DataGenerator {

    companion object {

    fun generatePatients(): List<PatientEntity> {
        val patients = ArrayList<PatientEntity>()
        for (i in 1..10) {
            val patient = PatientEntity()
            val diseasesManager = SingleIntBitMaskHandler()

            patient.pid = i
            patient.name = "PatientEntity " + i
            patient.age = i * 2 + 17

            if (i % 2 == 0) {
                patient.sex = "M"


                diseasesManager.set(Constants.MEASLES)
                diseasesManager.set(Constants.ASTHMA)
                diseasesManager.set(Constants.DIABETES)
                diseasesManager.set(Constants.KIDNEY_STONE)
                diseasesManager.set(Constants.HEART_ATTACK)

            } else {
                patient.sex = "F"


                diseasesManager.set(Constants.CHICKEN_POX)
                diseasesManager.set(Constants.MUMPS)
                diseasesManager.set(Constants.THYROID)
                diseasesManager.set(Constants.ANEMIA)
                diseasesManager.set(Constants.MALARIA)
            }

            patient.medicalHistory = diseasesManager.intValue

            System.out.println("MedicalHistory : "+patient.medicalHistory)
            patients.add(patient)
        }
        return patients
    }
    }
}