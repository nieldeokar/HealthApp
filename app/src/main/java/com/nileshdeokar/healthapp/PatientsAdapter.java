package com.nileshdeokar.healthapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nileshdeokar.healthapp.database.Patient;

import java.util.List;

public class PatientsAdapter extends RecyclerView.Adapter<PatientsAdapter.MyViewHolder> {

    private List<Patient> patientList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, age, sex;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            age = (TextView) view.findViewById(R.id.age);
            sex = (TextView) view.findViewById(R.id.sex);
        }
    }


    public PatientsAdapter(List<Patient> moviesList) {
        this.patientList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_patient, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Patient patient = patientList.get(position);

        holder.name.setText(patient.getFirstName());
        holder.age.setText("Age : "+patient.getAge());
        holder.sex.setText("Sex : "+patient.getSex());

    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }
}