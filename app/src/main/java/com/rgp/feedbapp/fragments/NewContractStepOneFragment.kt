package com.rgp.feedbapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rgp.feedbapp.activities.NewContractActivity
import com.rgp.feedbapp.databinding.FragmentNewContractStepOneBinding
import com.rgp.feedbapp.helpers.ToastHelper
import com.rgp.feedbapp.utils.AppConstants


class NewContractStepOneFragment : Fragment() {

    // Properties
    private var _binding: FragmentNewContractStepOneBinding? = null
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
        _binding = FragmentNewContractStepOneBinding.inflate(LayoutInflater.from(context))
        setOnClickListeners()
        return binding.root
    }

    // Private methods
    private fun setOnClickListeners() {
        binding.btNext.setOnClickListener {
            if (isValidTextEntry(binding.etName.text.toString()) &&
                isValidTextEntry(binding.etPhone.text.toString())
            ) {
                (activity as NewContractActivity).ticket.contactName = binding.etName.text.toString()
                (activity as NewContractActivity).ticket.contactPhone = binding.etPhone.text.toString()
                goToNextStep()
            } else {
                activity.let {
                    if (it is NewContractActivity) {
                        ToastHelper(it).showToast(constants.EMPTY_TEXTFIELD_ENTRIES)
                    }
                }
            }
        }
    }

    private fun isValidTextEntry(string: String) : Boolean {
        return string.isNotEmpty()
    }

    private fun goToNextStep() {
        val nextFragment = NewContractStepTwoFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(com.rgp.feedbapp.R.id.container, nextFragment)
            .addToBackStack(null)
            .commit()
    }
}