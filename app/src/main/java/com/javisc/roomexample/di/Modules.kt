package com.javisc.roomexample.di

import com.javisc.roomexample.datasource.service.PhotoApi
import com.javisc.roomexample.datasource.service.PhotoApiImpl
import com.javisc.roomexample.datasource.service.Retrofit
import com.javisc.roomexample.repository.PhotoRepo
import com.javisc.roomexample.ui.photofragment.PhotoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {
    single<PhotoApi> { PhotoApiImpl(Retrofit.service) }
    single { PhotoRepo() }
    viewModel { PhotoViewModel(get()) }
}