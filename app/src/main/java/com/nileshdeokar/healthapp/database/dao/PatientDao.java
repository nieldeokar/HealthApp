package com.nileshdeokar.healthapp.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.nileshdeokar.healthapp.database.entity.PatientEntity;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface PatientDao {

    @Query("SELECT * FROM patients")
    List<PatientEntity> getAll();

    @Query("SELECT * FROM patients where pid LIKE  :firstName LIKE :lastName")
    PatientEntity findByName(String firstName, String lastName);

    @Query("SELECT COUNT(*) from patients")
    int countUsers();

    @Query("SELECT * FROM patients where pid = :id")
    PatientEntity findByPid(int id);

    @Query("UPDATE patients set medical_history = :medicalHistory where pid = :id ")
    int updateMedicalHistory(int id, int medicalHistory);

    @Insert(onConflict = IGNORE)
    void insertAll(List<PatientEntity> patientEntities);

    @Insert(onConflict = IGNORE)
    void insertPatient(PatientEntity patientEntity);

    @Delete
    void delete(PatientEntity patientEntity);

    @Update(onConflict = REPLACE)
    public void updatePatient(PatientEntity patientEntity);
}