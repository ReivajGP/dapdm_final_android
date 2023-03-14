package com.rgp.feedbapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.rgp.feedbapp.R
import com.rgp.feedbapp.activities.NewContractActivity
import com.rgp.feedbapp.databinding.FragmentNewContractStepFourBinding
import com.rgp.feedbapp.utils.AppConstants

class NewContractStepFourFragment : Fragment(), OnMapReadyCallback {

    // Properties
    private lateinit var map: GoogleMap
    private var markerCoordinates: LatLng = LatLng(AppConstants.DEFAULT_MAP_LATITUDE, AppConstants.DEFAULT_MAP_LONGITUDE)
    private var _binding: FragmentNewContractStepFourBinding? = null
    private val binding get() = _binding!!
    private val constants = AppConstants

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewContractStepFourBinding.inflate(LayoutInflater.from(context))
        setUpNumberPicker()
        setNextButtonOnClickListener()
        initMap()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    // Private methods
    private fun setUpNumberPicker() {
        binding.numberPicker.maxValue = constants.MAXIMUM_PERFORMANCE_SETS
        binding.numberPicker.minValue = constants.MINIMUM_PERFORMANCE_SETS
        binding.numberPicker.value = constants.DEFAULT_PERFORMANCE_SETS
    }

    private fun initMap() {
        val mapView: SupportMapFragment = parentFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapView.getMapAsync(this)
    }

    private fun setNextButtonOnClickListener() {
        binding.btNext.setOnClickListener {
            (activity as NewContractActivity).ticket.numberOfSets = binding.numberPicker.value
            (activity as NewContractActivity).ticket.locationLatitude = markerCoordinates.latitude
            (activity as NewContractActivity).ticket.locationLongitude = markerCoordinates.longitude
            goToNextStep()
        }
    }

    private fun goToNextStep() {
        val nextFragment = NewContractFinalStepFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(com.rgp.feedbapp.R.id.container, nextFragment)
            .addToBackStack(null)
            .commit()
    }

    // Override methods
    override fun onMapReady(map: GoogleMap) {
        this.map = map

        val coordinates = LatLng(AppConstants.DEFAULT_MAP_LATITUDE, AppConstants.DEFAULT_MAP_LONGITUDE)

        val marker = MarkerOptions()
            .position(coordinates)

        map.addMarker(marker)

        map.setOnMapClickListener { markerCoordinates ->
            map.clear()
            map.addMarker(MarkerOptions().position(markerCoordinates))
            this.markerCoordinates = markerCoordinates
        }

        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinates, constants.MAP_STARTING_ANIMATION_ZOOM),
            constants.MAP_STARTING_ANIMATION_MILLISECONDS,
            null
        )
    }
}