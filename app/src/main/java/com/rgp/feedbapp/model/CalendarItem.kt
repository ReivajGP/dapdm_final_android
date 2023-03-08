package com.rgp.feedbapp.model

data class CalendarItem(
    var title: String = "",
    var description: String = "",
    var schedule: String = "",
    var image: String = "",
    var additionalInfo: String? = null
)
