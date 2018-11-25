package com.mezmeraiz.rumyancevo.network.common

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.mezmeraiz.anonym.TIMEOUT
import io.realm.Realm
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "http://dev.apianon.ru:3000/"

open class ServiceImpl<S>(val clazz: Class<S>){

    fun getService(baseUrl: String? = BASE_URL): S {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getClient())
                .build()
                .create(clazz)
    }

    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain!!.request()
                    Realm.getDefaultInstance().refresh()
                    val request = original.newBuilder()
                            .header("Content-Type", "application/json")
                            .header("X_APPLICATION_VERSION", "2")
                            .build()
                    chain.proceed(request)
                }
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build()
    }

}