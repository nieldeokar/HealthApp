package com.nileshdeokar.healthapp.ui.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.nileshdeokar.healthapp.database.entity.PatientEntity
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.DefaultItemAnimator
import com.nileshdeokar.healthapp.*
import com.nileshdeokar.healthapp.ui.details.PatientDetailsActivity
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.Observer
import android.util.Log
import android.view.View
import com.nileshdeokar.healthapp.database.DataGenerator
import java.util.concurrent.Executors


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16))

        recyclerView.itemAnimator = DefaultItemAnimator()



        val executor = Executors.newSingleThreadExecutor()



        executor.execute({
            val patients = (application as HealthApp).getDatabase()?.patientDao()?.all

            if(patients?.size == 0){
                (application as HealthApp).getDatabase()?.patientDao()?.insertAll(DataGenerator.generatePatients())
            }
            runOnUiThread({
                recyclerView.adapter =  PatientsAdapter(patients)
                Log.d("TAG2","${patients?.size} Size of list")
            })
        })



        recyclerView.addOnItemTouchListener(RecyclerTouchListener(applicationContext, recyclerView, object : RecyclerTouchListener.ClickListener {
            override fun onClick(view: View?, position: Int) {
                moveToPatientDetails(position + 1)
            }

            override fun onLongClick(view: View?, position: Int) {

            }
        }))

    }

    private fun moveToPatientDetails(position: Int) {
        val intent = Intent(this, PatientDetailsActivity::class.java)
        intent.putExtra(PatientDetailsActivity.BUNDLE_PID,position)
        startActivity(intent)
    }

/*
    private fun moveToPatientDetails(patientEntity: PatientEntity) {
        val intent = Intent(this, PatientDetailsActivity::class.java)
        intent.putExtra(PatientDetailsActivity.BUNDLE_PID,patientEntity)
        startActivity(intent)
    }


    */
}
