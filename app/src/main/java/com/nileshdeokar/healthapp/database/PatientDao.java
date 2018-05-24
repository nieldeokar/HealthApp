package com.nileshdeokar.healthapp.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PatientDao {

    @Query("SELECT * FROM patients")
    List<Patient> getAll();

    @Query("SELECT * FROM patients where first_name LIKE  :firstName AND last_name LIKE :lastName")
    Patient findByName(String firstName, String lastName);

    @Query("SELECT COUNT(*) from patients")
    int countUsers();


    @Query("UPDATE patients set medical_history = :medicalHistory where pid = :id ")
    int updateMedicalHistory(int id, int medicalHistory);

    @Insert
    void insertAll(Patient... patients);

    @Insert
    void insertPatient(Patient patient);

    @Delete
    void delete(Patient patient);
}