package com.nileshdeokar.healthapp.ui.details

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.nileshdeokar.healthapp.database.entity.PatientEntity
import kotlinx.android.synthetic.main.activity_patient_details.*
import kotlinx.android.synthetic.main.row_patient.*
import android.util.Log
import com.nileshdeokar.healthapp.HealthApp
import com.nileshdeokar.healthapp.R
import com.nileshdeokar.healthapp.utils.Constants
import com.nileshdeokar.healthapp.utils.SingleIntBitMaskHandler
import java.util.concurrent.Executors

/**
 * Created by @nieldeokar on 27/05/18.
 */

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

            val pid = intent.getIntExtra(BUNDLE_PID,0)

            executor.execute({
                Log.d("TAG2","PID RECEIVED $pid")
                patientEntity = (application as HealthApp).getDatabase()?.patientDao()?.findByPid(pid)

                runOnUiThread({setValues()})
            })
        }else {
            return
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

    private fun setValues() {

        if (patientEntity != null) {
            diseasesManager.intValue = patientEntity?.medicalHistory!!

            chicken_pox_value.isChecked = diseasesManager.get(Constants.CHICKEN_POX)
            measles_value.isChecked = diseasesManager.get(Constants.MEASLES)
            mumps_value.isChecked = diseasesManager.get(Constants.MUMPS)
            asthma_value.isChecked = diseasesManager.get(Constants.ASTHMA)
            thyroid_value.isChecked = diseasesManager.get(Constants.THYROID)
            diabetic_value.isChecked = diseasesManager.get(Constants.DIABETES)
            anemia_value.isChecked = diseasesManager.get(Constants.ANEMIA)
            kidney_stone_value.isChecked = diseasesManager.get(Constants.KIDNEY_STONE)
            malaria_value.isChecked = diseasesManager.get(Constants.MALARIA)
            heart_attack_value.isChecked = diseasesManager.get(Constants.HEART_ATTACK)

            name.text = patientEntity?.name
            age.text = getString(R.string.age,patientEntity?.age)
            sex.text = getString(R.string.sex,patientEntity?.sex)

            updateBinaryText()

        } else {
            Toast.makeText(this, getString(R.string.not_found), Toast.LENGTH_LONG).show()
        }
    }

    private fun saveData() {
        if (patientEntity != null) {
            patientEntity?.medicalHistory = diseasesManager.intValue

            val executor = Executors.newSingleThreadExecutor()

            executor.execute({
                (application as HealthApp).getDatabase()?.patientDao()?.updatePatient(patientEntity)
            })

            Toast.makeText(applicationContext, getString(R.string.save_success), Toast.LENGTH_SHORT).show()
            updateBinaryText()
        }
    }

    private fun updateBinaryText(){
        val strBinary = getString(R.string.binary_representation,intToString(diseasesManager.intValue)) +
                "\n\n" + getString(R.string.integer_representation,patientEntity?.medicalHistory)
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
                diseasesManager.toggleDisease(Constants.CHICKEN_POX, isChecked)
            }
            R.id.measles_value -> {
                diseasesManager.toggleDisease(Constants.MEASLES, isChecked)
            }
            R.id.mumps_value -> {
                diseasesManager.toggleDisease(Constants.MUMPS, isChecked)
            }
            R.id.asthma_value -> {
                diseasesManager.toggleDisease(Constants.ASTHMA, isChecked)
            }
            R.id.thyroid_value -> {
                diseasesManager.toggleDisease(Constants.THYROID, isChecked)
            }
            R.id.diabetic_value -> {
                diseasesManager.toggleDisease(Constants.DIABETES, isChecked)
            }
            R.id.anemia_value -> {
                diseasesManager.toggleDisease(Constants.ANEMIA, isChecked)
            }
            R.id.kidney_stone_value -> {
                diseasesManager.toggleDisease(Constants.KIDNEY_STONE, isChecked)
            }
            R.id.malaria_value -> {
                diseasesManager.toggleDisease(Constants.MALARIA, isChecked)
            }
            R.id.heart_attack_value -> {
                diseasesManager.toggleDisease(Constants.HEART_ATTACK, isChecked)
            }
        }
        patientEntity?.medicalHistory = diseasesManager.intValue
        updateBinaryText()
    }

}
