package com.lakooz.lpctest

import android.app.Application
import android.os.AsyncTask
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lakooz.lpctest.database.AppDatabase
import com.lakooz.lpctest.database.PotDao
import com.lakooz.lpctest.databinding.ActivityMainBinding
import com.lakooz.lpctest.databinding.PotItemBinding
import com.lakooz.lpctest.databinding.PotsFragmentBinding
import com.lakooz.lpctest.model.Pot
import com.lakooz.lpctest.networking.RestApiClient
import com.lakooz.lpctest.repositories.PotRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository =
        PotRepository.getInstance(AppDatabase.getInstance(context = application.baseContext).PotDao())


    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean>
        get() = _isRefreshing


    fun getPots() {

        RestApiClient.getPots()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<Pot>> {

                var disposable: Disposable? = null

                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onSuccess(pots: List<Pot>) {
                    disposable?.dispose()
                    // TODO
                    MyApplication
                    repository.deleteAll()

                    repository.insertAllAndSynchronize(pots)
                    _isRefreshing.value = false
                    PotAdapter(getApplication(),null,pots).setPots(pots)


                   }

                override fun onError(e: Throwable) {
                    // TODO
                    println(e)
                }

            }

            )
    }

    fun createPot(category: Int) {

        RestApiClient.createPot(category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Pot> {

                var disposable: Disposable? = null

                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onSuccess(pot: Pot) {
                    disposable?.dispose()
                    //TODO
                    repository.createOrUpdate(pot)



                }

                override fun onError(e: Throwable) {
                    //TODO
                    print(e)
                }

            }

            )
    }



}