package com.rgp.feedbapp.utils

import com.rgp.feedbapp.model.CalendarItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface CalendarAPI {
    @GET
    fun getCalendarEvents(
        @Url url: String
    ): Call<ArrayList<CalendarItem>>
}