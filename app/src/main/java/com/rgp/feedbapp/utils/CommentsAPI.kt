package com.rgp.feedbapp.utils

import com.rgp.feedbapp.model.CommentResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface CommentsAPI {
    @GET
    fun sendComment(
        @Url url: String
    ) : Call<CommentResponse>
}