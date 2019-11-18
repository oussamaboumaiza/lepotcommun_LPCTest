package com.lakooz.lpctest.database

import android.content.Context
import com.lakooz.lpctest.model.Pot
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.migration.Migration
import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import androidx.room.*


// TODO
@Database (entities = arrayOf(Pot::class),version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    //TODO

    abstract fun PotDao(): PotTodoDao
   companion object {

        private const val DATABASE_NAME = "db_lpc_test"
        private var instance: AppDatabase? = null
       val MIGRATION_1_2: Migration = object : Migration(2,1) {
           override fun migrate(database: SupportSQLiteDatabase) {
               // Since we didn't alter the table, there's nothing else to do here.
           }
       }

        // TODO : implement
        fun buildDatabase(context: Context) : AppDatabase {
            return Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java, DATABASE_NAME)
                .addMigrations(MIGRATION_1_2)
                .allowMainThreadQueries()
                .build()
        }

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                synchronized(AppDatabase::class.java) {
                    if (instance == null) {
                        instance =
                            buildDatabase(context.applicationContext)
                    }
                }
            }
            return instance!!
        }

    }
}