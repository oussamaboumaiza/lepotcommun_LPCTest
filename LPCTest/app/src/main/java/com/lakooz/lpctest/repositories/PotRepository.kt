package com.lakooz.lpctest.repositories

import androidx.lifecycle.LiveData
import com.lakooz.lpctest.database.AppDatabase
import com.lakooz.lpctest.database.PotDao
import com.lakooz.lpctest.database.PotTodoDao
import com.lakooz.lpctest.model.Pot
import io.reactivex.Single

class PotRepository(private val potDao: PotTodoDao) {

    fun createOrUpdate(pot: Pot) {
        potDao.createOrUpdate(pot)
    }

    fun insertAllAndSynchronize(pots: List<Pot>) {
        potDao.insertAllAndSynchronize(pots)
    }

    fun getPots(category: Int) :List<Pot> {

        return potDao.getPots(category)
    }
    fun deleteAll(){
        potDao.delete()
    }
    companion object {
        // TODO : initialize
        var instance : PotRepository? = null


            fun getInstance(potDao: PotTodoDao)= instance ?: synchronized(this)
        {
            instance ?: PotRepository(potDao).also { instance = it }
        }

    }

}