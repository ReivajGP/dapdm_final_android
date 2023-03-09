package com.rgp.feedbapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TicketItem(
    var ticketNumber: String,
    var ticketStatus: Int,
    var contactName: String,
    var contactPhone: String,
    var contactPhoto: String,
    var eventDate: String,
    var eventTime: String,
    var numberOfSets: Int,
    var totalAmount: Double,
    var paidAmount: Double,
    var locationLatitude: Double,
    var locationLongitude: Double,
    var additionalComments: String? = null
) : Parcelable
