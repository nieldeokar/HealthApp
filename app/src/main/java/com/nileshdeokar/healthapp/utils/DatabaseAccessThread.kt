package com.nileshdeokar.healthapp.utils

import com.nileshdeokar.healthapp.database.entity.PatientEntity

class DatabaseAccessThread(name: Runnable) : Thread(name) {
    override fun start() {
        super.start()
    }

    override fun run() {
        super.run()
    }


    interface OnConversationUpdate {
        fun onPatientsListFetched(patientEntities : List<PatientEntity>)

        fun onPatientFetched(patientEntity: PatientEntity)
    }
}