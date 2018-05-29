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

/**
 * Created by @nieldeokar on 27/05/18.
 */

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16))

        recyclerView.itemAnimator = DefaultItemAnimator()



        val executor = Executors.newSingleThreadExecutor()



        executor.execute({
            var patients = (application as HealthApp).getDatabase()?.patientDao()?.all

            if(patients?.size == 0){
                patients =  DataGenerator.generatePatients()
                (application as HealthApp).getDatabase()?.patientDao()?.insertAll(patients)

                Log.d("TAG","List was empty inserted items")


                val patient23 = (application as HealthApp).getDatabase()?.patientDao()?.all

                Log.d("TAG","Fetched items size ${patient23?.size}")
            }
            runOnUiThread({
                recyclerView.adapter =  PatientsAdapter(patients)
            })
        })



        recyclerView.addOnItemTouchListener(RecyclerTouchListener(applicationContext, recyclerView, object : RecyclerTouchListener.ClickListener {
            override fun onClick(view: View?, position: Int) {
                val pid = (recyclerView.adapter as PatientsAdapter).patientEntityList[position].pid
                moveToPatientDetails(pid)
            }

            override fun onLongClick(view: View?, position: Int) {

            }
        }))

    }

    private fun moveToPatientDetails(pid: Int) {
        val intent = Intent(this, PatientDetailsActivity::class.java)
        intent.putExtra(PatientDetailsActivity.BUNDLE_PID,pid)
        startActivity(intent)
    }

}
