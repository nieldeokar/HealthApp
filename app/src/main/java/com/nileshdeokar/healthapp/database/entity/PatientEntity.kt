package com.nileshdeokar.healthapp.database.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.PrimaryKey


/**
 * Created by niel on 23/05/18.
 */

@Entity(tableName = "patients")
class PatientEntity {

    @PrimaryKey(autoGenerate = true)
    var pid: Int = 0

    @ColumnInfo(name = "first_name")
    var name: String = ""

    @ColumnInfo(name = "sex")
    var  sex: String = ""

    @ColumnInfo(name = "age")
    var  age: Int = 0

    @ColumnInfo(name = "medical_history")
    var medicalHistory = 0
}