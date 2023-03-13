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


class NewContractStepTwoFragment : Fragment() {

    // Properties
    private var _binding: FragmentNewContractStepTwoBinding? = null
    private val binding get() = _binding!!

    // Lifecycle methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewContractStepTwoBinding.inflate(LayoutInflater.from(context))
        setOnClickListeners()
        return binding.root
    }

    // Private methods
    private fun setOnClickListeners() {
        binding.btNext.setOnClickListener {
            val day: String = binding.datePicker.dayOfMonth.toString()
            val month: String = getMonthString(binding.datePicker.month)
            val year: String = binding.datePicker.year.toString()
            (activity as NewContractActivity).ticket.eventDate = "$day/$month/$year"
            Log.d("STEP_TWO", "${(activity as NewContractActivity).ticket}")
            goToNextStep()
        }
    }

    private fun getMonthString(monthNumber: Int): String {
        return when (monthNumber) {
            0 -> "ENE"
            1 -> "FEB"
            2 -> "MAR"
            3 -> "ABR"
            4 -> "MAY"
            5 -> "JUN"
            6 -> "JUL"
            7 -> "AGO"
            8 -> "SEP"
            9 -> "OCT"
            10 -> "NOV"
            11 -> "DIC"
            else -> ""
        }
    }

    private fun goToNextStep() {
        val nextFragment = NewContractStepThreeFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(com.rgp.feedbapp.R.id.container, nextFragment)
            .addToBackStack(null)
            .commit()
    }
}
