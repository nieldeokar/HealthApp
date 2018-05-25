package com.nileshdeokar.healthapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.nileshdeokar.healthapp.database.Patient
import kotlinx.android.synthetic.main.activity_patient_details.*
import kotlinx.android.synthetic.main.row_patient.*
import com.nileshdeokar.healthapp.database.AppDatabase
import android.os.AsyncTask
import com.qtsoftware.qtconnect.features.DiseasesManager


class PatientDetailsActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

    companion object {
        const val BUNDLE_PID = "PID"
    }

    private var appDatabase: AppDatabase? = null

    private var patient: Patient? = null

    private var diseasesManager = DiseasesManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_details)

        appDatabase = AppDatabase.getAppDatabase(applicationContext)



        if (intent.hasExtra(BUNDLE_PID)) {
            patient = appDatabase?.patientDao()?.findByPid(intent.getIntExtra(BUNDLE_PID, 0))

            if (patient != null) {
                chicken_pox_value.isChecked = patient?.chickenPox == 1
                measles_value.isChecked = patient?.measels == 1
                mumps_value.isChecked = patient?.mumps == 1
                asthma_value.isChecked = patient?.asthma == 1
                thyroid_value.isChecked = patient?.thyroid == 1
                diabetic_value.isChecked = patient?.diabetic == 1
                anemia_value.isChecked = patient?.anemia == 1
                kidney_stone_value.isChecked = patient?.kidneyStone == 1
                malaria_value.isChecked = patient?.malaria == 1
                heart_attack_value.isChecked = patient?.heartAttack == 1

                name.text = patient?.firstName
                age.text = "Age : ${patient?.age}"
                sex.text = "Sex : ${patient?.sex}"

            } else {
                Toast.makeText(this, "Patient not found", Toast.LENGTH_LONG).show()
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
        if (patient != null) {
            patient.
            AsyncTask.execute {
                appDatabase?.patientDao()?.updatePatient(patient)
            }
            Toast.makeText(applicationContext, "Saved successfully !", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if (patient == null) return
        when (buttonView?.id) {
            R.id.chicken_pox_value ->{
                if (isChecked) patient?.chickenPox = 1 else patient?.chickenPox = 0
                diseasesManager.toggleDisease(DiseasesManager.CHICKEN_POX,isChecked)
            }
            R.id.measles_value -> {
                if (isChecked) patient?.measels = 1 else patient?.measels = 0
                diseasesManager.toggleDisease(DiseasesManager.MEASLES,isChecked)
            }
            R.id.mumps_value -> {
                if (isChecked) patient?.mumps = 1 else patient?.mumps = 0
                diseasesManager.toggleDisease(DiseasesManager.MUMPS,isChecked)
            }
            R.id.asthma_value -> {
                if (isChecked) patient?.asthma = 1 else patient?.asthma = 0
                diseasesManager.toggleDisease(DiseasesManager.ASTHMA,isChecked)
            }
            R.id.thyroid_value -> {
                if (isChecked) patient?.thyroid = 1 else patient?.thyroid = 0
                diseasesManager.toggleDisease(DiseasesManager.THYROID,isChecked)
            }
            R.id.diabetic_value -> {
                if (isChecked) patient?.diabetic = 1 else patient?.diabetic = 0
                diseasesManager.toggleDisease(DiseasesManager.DIABETES,isChecked)
            }
            R.id.anemia_value -> {
                if (isChecked) patient?.anemia = 1 else patient?.anemia = 0
                diseasesManager.toggleDisease(DiseasesManager.ANEMIA,isChecked)
            }
            R.id.kidney_stone_value -> {
                if (isChecked) patient?.kidneyStone = 1 else patient?.kidneyStone = 0
                diseasesManager.toggleDisease(DiseasesManager.KIDNEY_STONE,isChecked)
            }
            R.id.malaria_value -> {
                if (isChecked) patient?.malaria = 1 else patient?.malaria = 0
                diseasesManager.toggleDisease(DiseasesManager.MALARIA,isChecked)
            }
            R.id.heart_attack_value ->{
                if (isChecked) patient?.heartAttack = 1 else patient?.heartAttack = 0
                diseasesManager.toggleDisease(DiseasesManager.HEART_ATTACK,isChecked)
            }
        }
    }

}
