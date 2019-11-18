package com.lakooz.lpctest

import android.app.Application
import com.lakooz.lpctest.database.AppDatabase
import com.lakooz.lpctest.repositories.PotRepository

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // TODO: initialize database
        database = AppDatabase.getInstance(context = this)


    }

    companion object {

        lateinit var database: AppDatabase
        private set

    }
}