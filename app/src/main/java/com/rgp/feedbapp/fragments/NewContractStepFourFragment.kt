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
    private var markerCoordinates: LatLng = LatLng(19.445724627294464, -99.13744228386442)
    private var _binding: FragmentNewContractStepFourBinding? = null
    private val binding get() = _binding!!
    private val constants = AppConstants

    // Lifecycle methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewContractStepFourBinding.inflate(LayoutInflater.from(context))
        setUpNumberPicker()
        setNextButtonOnClickListener()
        initMap()
        return binding.root
    }

    // Private methods
    private fun setUpNumberPicker() {
        binding.numberPicker.maxValue = 5
        binding.numberPicker.minValue = 1
        binding.numberPicker.value = 2
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
            Log.d("STEP_FOUR", "${(activity as NewContractActivity).ticket}")
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

        val coordinates = LatLng(19.445724627294464, -99.13744228386442)

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