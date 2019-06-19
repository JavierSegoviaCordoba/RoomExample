package com.javisc.photoapi.datasource

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object PhotoApiServiceBuilder {

    private const val baseUrl = "https://jsonplaceholder.typicode.com/photos/"

    private val retrofit = Retrofit.Builder().apply {
        baseUrl(baseUrl)
        addConverterFactory(GsonConverterFactory.create())
    }.build()

    val service: PhotoService = retrofit.create()

}