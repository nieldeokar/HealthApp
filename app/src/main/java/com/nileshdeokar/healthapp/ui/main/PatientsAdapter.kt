package com.nileshdeokar.healthapp.ui.main;

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.nileshdeokar.healthapp.R
import com.nileshdeokar.healthapp.database.entity.PatientEntity
import java.util.Locale

/**
 * Created by @nieldeokar on 27/05/18.
 */

class PatientsAdapter(var patientEntityList: List<PatientEntity>) : RecyclerView.Adapter<PatientsAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.name)
        var age: TextView = view.findViewById(R.id.age)
        var sex: TextView = view.findViewById(R.id.sex)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_patient, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val patientEntity = patientEntityList[position]

        holder.name.setText(patientEntity.getName())
        holder.age.text = String.format(Locale.US, "Age : %d ", patientEntity.getAge())
        holder.sex.text = String.format(Locale.US, "Sex : %s ", patientEntity.getSex())

    }

    override fun getItemCount(): Int {
        return patientEntityList.size
    }

}
