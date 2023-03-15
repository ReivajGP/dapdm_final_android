package com.rgp.feedbapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CommentResponse(
    var commentReceived: Boolean
) : Parcelable
