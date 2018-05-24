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




class MainActivity : AppCompatActivity() {

    private var appDatabase : AppDatabase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appDatabase = AppDatabase.getAppDatabase(applicationContext)




        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16))

        recyclerView.itemAnimator = DefaultItemAnimator()

/*        AsyncTask.execute {

            for (pat in list){
                Log.d("TAG",pat.firstName)
            }

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
                /*val movie = list[position]
                Toast.makeText(applicationContext, movie.firstName + " is selected!", Toast.LENGTH_SHORT).show()*/
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
