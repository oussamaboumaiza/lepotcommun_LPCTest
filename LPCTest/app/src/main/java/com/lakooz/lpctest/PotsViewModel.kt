package com.lakooz.lpctest

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lakooz.lpctest.database.AppDatabase
import com.lakooz.lpctest.model.Pot
import com.lakooz.lpctest.repositories.PotRepository

class PotsViewModel(context: Context) : ViewModel() {

   private val repository =  PotRepository.getInstance(AppDatabase.getInstance(context = context).PotDao())

    //PotRepository.instance


    // TODO : initialize
    val pots : LiveData<List<Pot>>? = MutableLiveData<List<Pot>>()

     fun  get(category: Int): List<Pot>
    {
        return repository.getPots(category)
    }

}