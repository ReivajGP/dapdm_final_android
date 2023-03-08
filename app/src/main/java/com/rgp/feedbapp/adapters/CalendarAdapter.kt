package com.rgp.feedbapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.rgp.feedbapp.R
import com.rgp.feedbapp.model.CalendarItem

class CalendarAdapter(private val context: Context, private val calendarItems: ArrayList<CalendarItem>) : RecyclerView.Adapter<CalendarAdapter.CalendarItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarItemViewHolder {
        val calendarItemView: View = LayoutInflater.from(context).inflate(R.layout.item_calendar, parent, false)
        return CalendarItemViewHolder(calendarItemView)
    }

    override fun getItemCount(): Int = calendarItems.size

    override fun onBindViewHolder(holder: CalendarItemViewHolder, position: Int) {
        val currentItem = calendarItems[position]
        Glide.with(context)
            .load(calendarItems[position].image)
            .into(holder.ivPic)
        holder.tvTitle.text = currentItem.title
        holder.tvDescription.text = currentItem.description
        holder.tvSchedule.text = currentItem.schedule
    }

    class CalendarItemViewHolder(calendarItemView: View) : RecyclerView.ViewHolder(calendarItemView) {
        val ivPic: ShapeableImageView = calendarItemView.findViewById(R.id.iv_calendar)
        val tvTitle: TextView = calendarItemView.findViewById(R.id.tv_title)
        val tvDescription: TextView = calendarItemView.findViewById(R.id.tv_description)
        val tvSchedule: TextView = calendarItemView.findViewById(R.id.tv_schedule)
    }
}