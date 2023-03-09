package com.rgp.feedbapp.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppConstants {

    // Intents
    const val INTENT_MAIN_TO_TICKET_SUMMARY_TICKET_ID = "TICKET"

    // URLs
    const val CALENDAR_BASE_URL = "https://private-5acf0e-feedbapp.apiary-mock.com"
    const val CALENDAR_ENDPOINT = "/events"
    const val TICKETS_ENDPOINT = "/tickets"

    // Map
    const val MAP_STARTING_ANIMATION_ZOOM = 15f
    const val MAP_STARTING_ANIMATION_MILLISECONDS = 4000

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(CALENDAR_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}