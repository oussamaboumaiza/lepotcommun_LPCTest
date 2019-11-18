package com.lakooz.lpctest.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.lakooz.lpctest.model.Pot

@Dao
interface PotTodoDao {

    @Insert
    fun createOrUpdate(pot: Pot)
    @Query("SELECT * FROM Pot WHERE CATEGORY LIKE :category")
    fun getPots(category: Int): List<Pot>
    @Insert
    fun insertAllAndSynchronize( Pots: List<Pot>)
    @Query("DELETE  FROM Pot ")
    fun delete()
}