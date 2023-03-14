package com.rgp.feedbapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rgp.feedbapp.R
import com.rgp.feedbapp.activities.NewContractActivity
import com.rgp.feedbapp.databinding.FragmentNewContractStepOneBinding
import com.rgp.feedbapp.databinding.FragmentNewContractStepTwoBinding
import com.rgp.feedbapp.helpers.ToastHelper
import com.rgp.feedbapp.utils.AppConstants


class NewContractStepTwoFragment : Fragment() {

    // Properties
    private var _binding: FragmentNewContractStepTwoBinding? = null
    private val binding get() = _binding!!
    private val constants = AppConstants

    // Lifecycle methods
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewContractStepTwoBinding.inflate(LayoutInflater.from(context))
        setOnClickListeners()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    // Private methods
    private fun setOnClickListeners() {
        binding.btNext.setOnClickListener {
            val day: String = binding.datePicker.dayOfMonth.toString()
            val month: String = getMonthString(binding.datePicker.month)
            val year: String = binding.datePicker.year.toString()
            (activity as NewContractActivity).ticket.eventDate = "$day/$month/$year"
            goToNextStep()
        }
    }

    private fun getMonthString(monthNumber: Int): String {
        return when (monthNumber) {
            constants.JANUARY_NUMBER -> constants.JANUARY_STRING
            constants.FEBRUARY_NUMBER -> constants.FEBRUARY_STRING
            constants.MARCH_NUMBER -> constants.MARCH_STRING
            constants.APRIL_NUMBER -> constants.APRIL_STRING
            constants.MAY_NUMBER -> constants.MAY_STRING
            constants.JUNE_NUMBER -> constants.JUNE_STRING
            constants.JULY_NUMBER -> constants.JULY_STRING
            constants.AUGUST_NUMBER -> constants.AUGUST_STRING
            constants.SEPTEMBER_NUMBER -> constants.SEPTEMBER_STRING
            constants.OCTOBER_NUMBER -> constants.OCTOBER_STRING
            constants.NOVEMBER_NUMBER -> constants.NOVEMBER_STRING
            constants.DECEMBER_NUMBER -> constants.DECEMBER_STRING
            else -> String()
        }
    }

    private fun goToNextStep() {
        val nextFragment = NewContractStepThreeFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, nextFragment)
            .addToBackStack(null)
            .commit()
    }
}
