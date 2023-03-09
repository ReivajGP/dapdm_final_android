package com.rgp.feedbapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.rgp.feedbapp.R
import com.rgp.feedbapp.databinding.ActivityTicketSummaryBinding
import com.rgp.feedbapp.model.TicketItem
import com.rgp.feedbapp.utils.AppConstants

class TicketSummaryActivity : AppCompatActivity(), OnMapReadyCallback {

    // Properties
    private lateinit var binding: ActivityTicketSummaryBinding
    private lateinit var ticket: TicketItem
    private lateinit var map: GoogleMap
    private val constants = AppConstants

    // Activity lifecycle methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketSummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ticket = intent.getParcelableExtra<TicketItem>(constants.INTENT_MAIN_TO_TICKET_SUMMARY_TICKET_ID)!!
        setupView()
    }

    // Maps interface methods
    override fun onMapReady(map: GoogleMap) {
        this.map = map
        val coordinates = LatLng(ticket.locationLatitude, ticket.locationLongitude)

        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinates, constants.MAP_STARTING_ANIMATION_ZOOM),
            constants.MAP_STARTING_ANIMATION_MILLISECONDS,
            null
        )

        map.addMarker(
            MarkerOptions()
                .position(coordinates)
        )
    }

    // Private methods
    private fun setupView() {
        binding.tvTicketNumber.text = ticket.ticketNumber
        Glide.with(this)
            .load(ticket.contactPhoto)
            .into(binding.rivProfile)
        binding.tvContactName.text = ticket.contactName
        binding.tvContactPhone.text = ticket.contactPhone
        binding.tvEventDate.text = ticket.eventDate
        binding.tvEventTime.text = ticket.eventTime
        binding.tvEventSetsNumber.text = "${ticket.numberOfSets} turnos"
        binding.tvPaidAmount.text = "$${ticket.paidAmount} de $${ticket.totalAmount}"
        if (ticket.additionalComments != null) {
            binding.tvDetails.text = ticket.additionalComments
            binding.tvDetails.isVisible = true
            binding.tvDetailsTitle.isVisible = true
        } else {
            binding.tvDetails.isVisible = false
            binding.tvDetailsTitle.isVisible = false
        }

        val mapView: SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapView.getMapAsync(this)
    }
}