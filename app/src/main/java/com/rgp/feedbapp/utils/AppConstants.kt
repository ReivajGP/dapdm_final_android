package com.rgp.feedbapp.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppConstants {
    // URLs
    const val CALENDAR_BASE_URL = "https://private-5acf0e-feedbapp.apiary-mock.com"
    const val CALENDAR_ENDPOINT = "/events"

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(CALENDAR_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}