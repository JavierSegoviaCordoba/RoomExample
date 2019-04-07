package com.javisc.photoapi.datasource

import com.javisc.photoapi.model.PhotoDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PhotoService {
    @GET("{id}")
    fun getPhoto(@Path("id") id: Int): Call<PhotoDto>
}