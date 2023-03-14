package com.rgp.feedbapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.lottie.LottieDrawable
import com.rgp.feedbapp.R
import com.rgp.feedbapp.databinding.FragmentNewContractFinalStepBinding
import com.rgp.feedbapp.utils.AppConstants

class NewContractFinalStepFragment : Fragment() {

    private var _binding: FragmentNewContractFinalStepBinding? = null
    private val binding get() = _binding!!
    private val constants = AppConstants

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewContractFinalStepBinding.inflate(LayoutInflater.from(context))
        launchAnimation()
        binding.tvDetails.text = constants.NEXT_STEPS_INSTRUCTIONS
        binding.btFinish.setOnClickListener { backToHomeActivity() }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    // Private methods
    private fun launchAnimation() {
        binding.avApproved.setAnimation(R.raw.approved)
        binding.avApproved.repeatCount = LottieDrawable.INFINITE
        binding.avApproved.playAnimation()
        binding.avApproved.speed = constants.NEW_CONTRACT_APPROVED_ANIMATION_SPEED
    }

    private fun backToHomeActivity() {
        activity?.finish()
    }
}