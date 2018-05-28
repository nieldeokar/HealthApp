package com.nileshdeokar.healthapp.ui.details

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.nileshdeokar.healthapp.database.entity.PatientEntity
import kotlinx.android.synthetic.main.activity_patient_details.*
import kotlinx.android.synthetic.main.row_patient.*
import com.nileshdeokar.healthapp.database.AppDatabase
import android.os.AsyncTask
import com.nileshdeokar.healthapp.HealthApp
import com.nileshdeokar.healthapp.R
import com.nileshdeokar.healthapp.ui.main.PatientsAdapter
import com.nileshdeokar.healthapp.utils.SingleIntBitMaskHandler
import com.qtsoftware.qtconnect.features.DiseasesManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors


class PatientDetailsActivity  : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

    companion object {
        const val BUNDLE_PID = "PID"
    }

    private var patientEntity: PatientEntity? = PatientEntity()

    private var diseasesManager = SingleIntBitMaskHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_details)


        if (intent.hasExtra(BUNDLE_PID)) {


            val executor = Executors.newSingleThreadExecutor()

            executor.execute({
                patientEntity = (application as HealthApp).getDatabase()?.patientDao()?.findByPid(intent.getIntExtra(BUNDLE_PID,0))
            })

            diseasesManager.setIntvalue(patientEntity?.medicalHistory!!)


            if (patientEntity != null) {

                chicken_pox_value.isChecked = diseasesManager.get(DiseasesManager.CHICKEN_POX)
                measles_value.isChecked = diseasesManager.get(DiseasesManager.MEASLES)
                mumps_value.isChecked = diseasesManager.get(DiseasesManager.MUMPS)
                asthma_value.isChecked = diseasesManager.get(DiseasesManager.ASTHMA)
                thyroid_value.isChecked = diseasesManager.get(DiseasesManager.THYROID)
                diabetic_value.isChecked = diseasesManager.get(DiseasesManager.DIABETES)
                anemia_value.isChecked = diseasesManager.get(DiseasesManager.ANEMIA)
                kidney_stone_value.isChecked = diseasesManager.get(DiseasesManager.KIDNEY_STONE)
                malaria_value.isChecked = diseasesManager.get(DiseasesManager.MALARIA)
                heart_attack_value.isChecked = diseasesManager.get(DiseasesManager.HEART_ATTACK)

                name.text = patientEntity?.name
                age.text = "Age : ${patientEntity?.age}"
                sex.text = "Sex : ${patientEntity?.sex}"

                updateBinaryText()

            } else {
                Toast.makeText(this, "PatientEntity not found", Toast.LENGTH_LONG).show()
            }
        }

        chicken_pox_value.setOnCheckedChangeListener(this)
        measles_value.setOnCheckedChangeListener(this)
        mumps_value.setOnCheckedChangeListener(this)
        asthma_value.setOnCheckedChangeListener(this)
        thyroid_value.setOnCheckedChangeListener(this)
        diabetic_value.setOnCheckedChangeListener(this)
        anemia_value.setOnCheckedChangeListener(this)
        kidney_stone_value.setOnCheckedChangeListener(this)
        malaria_value.setOnCheckedChangeListener(this)
        heart_attack_value.setOnCheckedChangeListener(this)

        submit.setOnClickListener { saveData() }
    }

    private fun saveData() {
        if (patientEntity != null) {
            patientEntity?.medicalHistory = diseasesManager.toInts()

            val executor = Executors.newSingleThreadExecutor()

            executor.execute({
                (application as HealthApp).getDatabase()?.patientDao()?.updatePatient(patientEntity)

            })

            Toast.makeText(applicationContext, getString(R.string.save_success), Toast.LENGTH_SHORT).show()
            updateBinaryText()
        }
    }

    private fun updateBinaryText(){
        val strBinary = getString(R.string.binary_representation,intToString(diseasesManager.toInts()))
        array_values.text = strBinary
    }

    fun intToString(number: Int): String {
        val result = StringBuilder()
        val groupSize = 8

        for (i in 31 downTo 0) {
            val mask = 1 shl i
            result.append(if (number and mask != 0) "1" else "0")

            if (i % groupSize == 0)
                result.append(" ")
        }
        result.replace(result.length - 1, result.length, "")

        return result.toString()
    }


    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if (patientEntity == null) return
        when (buttonView?.id) {
            R.id.chicken_pox_value -> {
                diseasesManager.toggleDisease(DiseasesManager.CHICKEN_POX, isChecked)
            }
            R.id.measles_value -> {
                diseasesManager.toggleDisease(DiseasesManager.MEASLES, isChecked)
            }
            R.id.mumps_value -> {
                diseasesManager.toggleDisease(DiseasesManager.MUMPS, isChecked)
            }
            R.id.asthma_value -> {
                diseasesManager.toggleDisease(DiseasesManager.ASTHMA, isChecked)
            }
            R.id.thyroid_value -> {
                diseasesManager.toggleDisease(DiseasesManager.THYROID, isChecked)
            }
            R.id.diabetic_value -> {
                diseasesManager.toggleDisease(DiseasesManager.DIABETES, isChecked)
            }
            R.id.anemia_value -> {
                diseasesManager.toggleDisease(DiseasesManager.ANEMIA, isChecked)
            }
            R.id.kidney_stone_value -> {
                diseasesManager.toggleDisease(DiseasesManager.KIDNEY_STONE, isChecked)
            }
            R.id.malaria_value -> {
                diseasesManager.toggleDisease(DiseasesManager.MALARIA, isChecked)
            }
            R.id.heart_attack_value -> {
                diseasesManager.toggleDisease(DiseasesManager.HEART_ATTACK, isChecked)
            }
        }

        updateBinaryText()
    }

}
