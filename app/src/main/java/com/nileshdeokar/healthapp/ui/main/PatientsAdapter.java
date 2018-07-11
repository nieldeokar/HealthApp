package com.nileshdeokar.healthapp.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nileshdeokar.healthapp.R;
import com.nileshdeokar.healthapp.database.entity.PatientEntity;

import java.util.List;
import java.util.Locale;

/**
 * Created by @nieldeokar on 27/05/18.
 */

public class PatientsAdapter extends RecyclerView.Adapter<PatientsAdapter.MyViewHolder> {

    public List<PatientEntity> patientEntityList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, age, sex;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            age = view.findViewById(R.id.age);
            sex = view.findViewById(R.id.sex);
        }
    }


    public PatientsAdapter(List<PatientEntity> patientEntities) {
        this.patientEntityList = patientEntities;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_patient, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PatientEntity patientEntity = patientEntityList.get(position);

        holder.name.setText(patientEntity.getName());
        holder.age.setText(String.format(Locale.US,"Age : %d ", patientEntity.getAge()));
        holder.sex.setText(String.format(Locale.US,"Sex : %s ", patientEntity.getSex()));

    }

    @Override
    public int getItemCount() {
        return patientEntityList.size();
    }

}