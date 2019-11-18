package com.lakooz.lpctest.networking

import com.google.gson.GsonBuilder
import com.lakooz.lpctest.model.Pot
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

object RestApiClient {

    private const val BASE_URL = "https://recrutement.lepotcommuntest.fr/2019/recruiting/"

    fun getPots(): Single<List<Pot>> {
        // TODO
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiServiceInterface::class.java)
        return retrofit.getPots()


    }

    fun createPot(category: Int): Single<Pot> {
        // TODO
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiServiceInterface::class.java)
        return retrofit.createPot(category)

    }
}