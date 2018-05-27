package com.nileshdeokar.healthapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.nileshdeokar.healthapp.database.Patient
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.DefaultItemAnimator
import android.widget.Toast
import android.graphics.Movie
import android.os.AsyncTask
import android.view.View
import com.nileshdeokar.healthapp.database.AppDatabase
import com.qtsoftware.qtconnect.features.DiseasesManager
import java.util.*


class MainActivity : AppCompatActivity() {

    private var appDatabase : AppDatabase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appDatabase = AppDatabase.getAppDatabase(applicationContext)




        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16))

        recyclerView.itemAnimator = DefaultItemAnimator()

        val list = appDatabase?.patientDao()?.all

        if(list == null || list.size == 0) {

            for (i in 1..10) {
                val patient = Patient()
                val diseasesManager = SIngleLongHandler()

                patient.firstName = "Patient " + i
                patient.lastName = "LastName " + i
                patient.age = i * 2 + 17

                if (i % 2 == 0) {
                    patient.sex = "M"
                    patient.measels = 1
                    patient.asthma = 1
                    patient.diabetic = 1
                    patient.kidneyStone = 1
                    patient.heartAttack= 1

                    diseasesManager.set(DiseasesManager.MEASLES)
                    diseasesManager.set(DiseasesManager.ASTHMA)
                    diseasesManager.set(DiseasesManager.DIABETES)
                    diseasesManager.set(DiseasesManager.KIDNEY_STONE)
                    diseasesManager.set(DiseasesManager.HEART_ATTACK)

                } else {
                    patient.sex = "F"
                    patient.chickenPox = 1
                    patient.mumps = 1
                    patient.thyroid= 1
                    patient.anemia = 1
                    patient.malaria = 1

                    diseasesManager.set(DiseasesManager.CHICKEN_POX)
                    diseasesManager.set(DiseasesManager.MUMPS)
                    diseasesManager.set(DiseasesManager.THYROID)
                    diseasesManager.set(DiseasesManager.ANEMIA)
                    diseasesManager.set(DiseasesManager.MALARIA)
                }

                patient.medicalHistory2 = diseasesManager.toLongs()

                appDatabase?.patientDao()?.insertPatient(patient)
            }
        }


        object : AsyncTask<Void, Void, List<Patient>>() {
            override fun doInBackground(vararg voids: Void): List<Patient>? {
                return appDatabase?.patientDao()?.all
            }

            override fun onPostExecute(list: List<Patient>?) {
                super.onPostExecute(list)
                recyclerView.adapter = PatientsAdapter(list)


                Log.d("TAG", Arrays.toString(list?.get(0)?.medicalHistory))
            }
        }.execute()

//        val list = appDatabase?.patientDao()?.all ?: return
//        recyclerView.adapter = PatientsAdapter(list)

        recyclerView.addOnItemTouchListener(RecyclerTouchListener(applicationContext, recyclerView, object : RecyclerTouchListener.ClickListener {
            override fun onClick(view: View?, position: Int) {
                moveToPatientDetails(position+1)
            }

            override fun onLongClick(view: View?, position: Int) {

            }
        }))
    }

    private fun moveToPatientDetails(position: Int) {
        val intent = Intent(this,PatientDetailsActivity2::class.java)
        intent.putExtra(PatientDetailsActivity.BUNDLE_PID,position)
        startActivity(intent)
    }
}
