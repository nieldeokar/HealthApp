package com.nileshdeokar.healthapp.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.PrimaryKey
import java.sql.Blob


/**
 * Created by niel on 23/05/18.
 */

@Entity(tableName = "patients")
class Patient {

    @PrimaryKey(autoGenerate = true)
    var pid: Int = 0

    @ColumnInfo(name = "first_name")
    var firstName: String? = null

    @ColumnInfo(name = "last_name")
    var  lastName: String? = null

    @ColumnInfo(name = "sex")
    var  sex: String? = null

    @ColumnInfo(name = "age")
    var  age: Int = 0

    @ColumnInfo(name = "medical_history",typeAffinity = ColumnInfo.BLOB)
    var medicalHistory : ByteArray? = null

    @ColumnInfo(name = "medical_history_long")
    var medicalHistory2 = 0L

    @ColumnInfo(name = "chicken_pox")
    var  chickenPox : Int = 0

    @ColumnInfo(name = "measles")
    var  measels: Int = 0

    @ColumnInfo(name = "mumps")
    var  mumps: Int = 0

    @ColumnInfo(name = "asthma")
    var  asthma: Int = 0

    @ColumnInfo(name = "thyroid")
    var  thyroid: Int = 0

    @ColumnInfo(name = "diabetic")
    var  diabetic: Int = 0

    @ColumnInfo(name = "anemia")
    var  anemia: Int = 0

    @ColumnInfo(name = "kidney_stone")
    var  kidneyStone: Int = 0

    @ColumnInfo(name = "malaria")
    var  malaria: Int = 0

    @ColumnInfo(name = "heartAttack")
    var heartAttack: Int = 0

   /* @ColumnInfo(name = "medical_history")
    private val medicalHistory: Int = 0

    @ColumnInfo(name = "medical_history")
    private val medicalHistory: Int = 0

    @ColumnInfo(name = "medical_history")
    private val medicalHistory: Int = 0

    @ColumnInfo(name = "medical_history")
    private val medicalHistory: Int = 0

    @ColumnInfo(name = "medical_history")
    private val medicalHistory: Int = 0

    @ColumnInfo(name = "medical_history")
    private val medicalHistory: Int = 0

    @ColumnInfo(name = "medical_history")
    private val medicalHistory: Int = 0

    @ColumnInfo(name = "medical_history")
    private val medicalHistory: Int = 0

    @ColumnInfo(name = "medical_history")
    private val medicalHistory: Int = 0

    @ColumnInfo(name = "medical_history")
    private val medicalHistory: Int = 0

    @ColumnInfo(name = "medical_history")
    private val medicalHistory: Int = 0

    @ColumnInfo(name = "medical_history")
    private val medicalHistory: Int = 0

    @ColumnInfo(name = "medical_history")
    private val medicalHistory: Int = 0

    @ColumnInfo(name = "medical_history")
    private val medicalHistory: Int = 0
*/

}