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
import android.arch.persistence.room.Room
import android.support.v7.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private var appDatabase : AppDatabase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appDatabase = AppDatabase.getAppDatabase(applicationContext)




        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16))

        recyclerView.itemAnimator = DefaultItemAnimator()

        /*for (i in 1..10){
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
        }*/

        object : AsyncTask<Void, Void, List<Patient>>() {
            override fun doInBackground(vararg voids: Void): List<Patient>? {
                return appDatabase?.patientDao()?.all
            }

            override fun onPostExecute(list: List<Patient>?) {
                super.onPostExecute(list)
                recyclerView.adapter = PatientsAdapter(list)
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
        val intent = Intent(this,PatientDetailsActivity::class.java)
        intent.putExtra(PatientDetailsActivity.BUNDLE_PID,position)
        startActivity(intent)
    }
}
