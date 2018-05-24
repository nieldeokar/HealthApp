package com.nileshdeokar.healthapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.nileshdeokar.healthapp.database.AppDatabase
import com.nileshdeokar.healthapp.database.Patient

class MainActivity : AppCompatActivity() {

    private var appDatabase : AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appDatabase = AppDatabase.getAppDatabase(applicationContext)


        for (i in 1..10){
            val patient = Patient()
            patient.firstName = "FirstName "+ i
            patient.lastName = "LastName "+ i
            patient.age = i * 2 + 17

            if(i % 2 == 0){
                patient.sex = "M"
                patient.anemia = 1
                patient.asthma = 1
                patient.chickenPox = 1
                patient.diabetic = 1
                patient.thyroid = 1
            }else{
                patient.sex = "F"
                patient.kidneyStone = 1
                patient.malaria = 1
                patient.measels = 1
                patient.heartAttack = 1
                patient.mumps = 1
            }
            appDatabase?.patientDao()?.insertPatient(patient)
        }

        val list = appDatabase?.patientDao()?.all ?: return

        for (pat in list){
            Log.d("TAG",pat.firstName)
        }
    }
}
