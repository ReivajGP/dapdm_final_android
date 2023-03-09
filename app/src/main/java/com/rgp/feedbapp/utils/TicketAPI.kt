package com.rgp.feedbapp.utils

import com.rgp.feedbapp.model.TicketItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface TicketAPI {
    @GET
    fun getTicketsData(
        @Url url: String
    ): Call<ArrayList<TicketItem>>
}