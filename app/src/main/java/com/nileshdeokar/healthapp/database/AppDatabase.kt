package com.nileshdeokar.healthapp.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.LiveData
import com.nileshdeokar.healthapp.database.dao.PatientDao
import com.nileshdeokar.healthapp.database.entity.PatientEntity


@Database(entities = arrayOf(PatientEntity::class), version = 1)

abstract class AppDatabase : RoomDatabase() {

    abstract fun patientDao(): PatientDao

    private val mIsDatabaseCreated = MutableLiveData<Boolean>()

    companion object {

        private var sInstance: AppDatabase? = null

        private var DATABASE_NAME = "patients-db"

        fun destroyInstance() {
            sInstance = null
        }

        fun getInstance(context: Context, executors: AppExecutors): AppDatabase? {
            if (sInstance == null) {
                synchronized(AppDatabase::class.java) {
                    if (sInstance == null) {
                        sInstance = buildDatabase(context.applicationContext, executors)
                        sInstance?.updateDatabaseCreated(context.applicationContext)
                    }
                }
            }
            return sInstance
        }

        private fun buildDatabase(appContext: Context,
                                  executors: AppExecutors): AppDatabase {
            return Room.databaseBuilder(appContext, AppDatabase::class.java, DATABASE_NAME)
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            executors.diskIO().execute({
                                // Generate the data for pre-population
                                val database = AppDatabase.getInstance(appContext, executors)
                                val products = DataGenerator.generatePatients()

                                insertData(database, products)
                                // notify that the database was created and it's ready to be used
                                database?.setDatabaseCreated()
                            })
                        }
                    }).build()
        }

        private fun insertData(database: AppDatabase?, patientEntities: List<PatientEntity>){
            database?.runInTransaction {
                database.patientDao().insertAll(patientEntities)
            }
        }


    }

    private fun updateDatabaseCreated(context: Context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated()
        }
    }


    private fun setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true)
    }

    fun getDatabaseCreated(): LiveData<Boolean> {
        return mIsDatabaseCreated
    }


}