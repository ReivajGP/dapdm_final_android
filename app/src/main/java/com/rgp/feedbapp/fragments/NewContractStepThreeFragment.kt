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
import com.rgp.feedbapp.databinding.FragmentNewContractStepThreeBinding
import kotlin.math.min

class NewContractStepThreeFragment : Fragment() {

    // Properties
    private var _binding: FragmentNewContractStepThreeBinding? = null
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
        _binding = FragmentNewContractStepThreeBinding.inflate(LayoutInflater.from(context))
        setOnClickListeners()
        return binding.root
    }

    // Private methods
    private fun setOnClickListeners() {
        binding.btNext.setOnClickListener {
            val hour: Int = binding.timePicker.hour
            val minute: Int = binding.timePicker.minute
            (activity as NewContractActivity).ticket.eventTime = formatTime(hour, minute)
            Log.d("STEP_THREE", "${(activity as NewContractActivity).ticket}")
            goToNextStep()
        }
    }

    private fun formatTime(hour: Int, minute: Int) : String {
        if (minute < 10) {
            return "$hour:0$minute hrs"
        }
        return "$hour:$minute hrs"
    }

    private fun goToNextStep() {
        val nextFragment = NewContractStepFourFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(com.rgp.feedbapp.R.id.container, nextFragment)
            .addToBackStack(null)
            .commit()
    }
}