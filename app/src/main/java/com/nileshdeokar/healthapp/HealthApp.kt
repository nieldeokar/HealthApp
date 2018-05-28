package com.nileshdeokar.healthapp

import android.app.Application
import com.nileshdeokar.healthapp.database.AppDatabase
import com.nileshdeokar.healthapp.database.AppExecutors


class HealthApp  : Application() {

    private var mAppExecutors: AppExecutors? = null

    override fun onCreate() {
        super.onCreate()

        mAppExecutors = AppExecutors()
    }

    fun getDatabase(): AppDatabase? {
        return AppDatabase.getInstance(this, mAppExecutors!!)
    }
}