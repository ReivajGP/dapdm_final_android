package com.rgp.feedbapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.rgp.feedbapp.R
import com.rgp.feedbapp.activities.MainActivity
import com.rgp.feedbapp.model.TicketItem
import com.rgp.feedbapp.utils.AppConstants

class TicketAdapter(val context: Context, val ticketItems: ArrayList<TicketItem>): RecyclerView.Adapter<TicketAdapter.TicketItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketItemViewHolder {
        val ticketViewHolder: View = LayoutInflater.from(context).inflate(R.layout.item_ticket, parent, false)
        return TicketItemViewHolder(ticketViewHolder)
    }

    override fun getItemCount(): Int = ticketItems.size

    override fun onBindViewHolder(holder: TicketItemViewHolder, position: Int) {
        if (ticketItems[position].ticketStatus == AppConstants.TICKET_STATUS_INACTIVE) {
            holder.ivTicketPicture.setImageResource(R.drawable.ticket_expired)
            holder.ivExpired.isVisible = true
        } else {
            holder.ivTicketPicture.setImageResource(R.drawable.ticket_ok)
            holder.ivExpired.isVisible = false
        }
        holder.tvTicketNumber.text = ticketItems[position].ticketNumber
        holder.tvDate.text = ticketItems[position].eventDate
        holder.tvTime.text = ticketItems[position].eventTime
        holder.itemView.setOnClickListener {
            if(context is MainActivity) context.ticketSelected(ticketItems[position])
        }
    }

    class TicketItemViewHolder(ticketItemView: View) : RecyclerView.ViewHolder(ticketItemView) {
        val ivTicketPicture: ImageView = ticketItemView.findViewById(R.id.iv_ticket)
        val ivExpired: ImageView = ticketItemView.findViewById(R.id.iv_expired)
        val tvTicketNumber: TextView = ticketItemView.findViewById(R.id.tv_ticket_number)
        val tvDate: TextView = ticketItemView.findViewById(R.id.tv_event_date)
        val tvTime: TextView = ticketItemView.findViewById(R.id.tv_event_time)
    }
}