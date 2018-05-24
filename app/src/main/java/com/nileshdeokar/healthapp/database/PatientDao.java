package com.nileshdeokar.healthapp.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface PatientDao {

    @Query("SELECT * FROM patients")
    List<Patient> getAll();

    @Query("SELECT * FROM patients where pid LIKE  :firstName AND last_name LIKE :lastName")
    Patient findByName(String firstName, String lastName);

    @Query("SELECT COUNT(*) from patients")
    int countUsers();

    @Query("SELECT * FROM patients where pid = :id")
    Patient findByPid(int id);

    @Query("UPDATE patients set medical_history = :medicalHistory where pid = :id ")
    int updateMedicalHistory(int id, int medicalHistory);

    @Insert(onConflict = IGNORE)
    void insertAll(Patient... patients);

    @Insert(onConflict = IGNORE)
    void insertPatient(Patient patient);

    @Delete
    void delete(Patient patient);

    @Update(onConflict = REPLACE)
    public void updatePatient(Patient patient);
}