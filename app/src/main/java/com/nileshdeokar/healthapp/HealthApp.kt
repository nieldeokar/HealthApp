package com.nileshdeokar.healthapp

import android.app.Application
import com.nileshdeokar.healthapp.database.AppDatabase

/**
 * Created by @nieldeokar on 27/05/18.
 */

class HealthApp  : Application() {

    override fun onCreate() {
        super.onCreate()

    }

    fun getDatabase(): AppDatabase? {
        return AppDatabase.getInstance(this)
    }

}