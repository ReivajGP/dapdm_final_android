package com.rgp.feedbapp.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rgp.feedbapp.databinding.FragmentHomeBinding
import com.rgp.feedbapp.helpers.InternetConnectionHelper
import com.rgp.feedbapp.helpers.ToastHelper
import com.rgp.feedbapp.utils.AppConstants
import com.rgp.feedbapp.utils.SocialMedia


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // Fragment lifecycle methods
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(LayoutInflater.from(context))
        setSocialMediaClickListeners()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    // Private methods
    private fun setSocialMediaClickListeners() {
        binding.vSocialNetworks.btYoutube.setOnClickListener {
            openBrowserFor(SocialMedia.YOUTUBE)
        }

        binding.vSocialNetworks.btInstagram.setOnClickListener {
            openBrowserFor(SocialMedia.INSTAGRAM)
        }

        binding.vSocialNetworks.btFacebook.setOnClickListener {
            openBrowserFor(SocialMedia.FACEBOOK)
        }
    }

    private fun openBrowserFor(socialMedia: SocialMedia) {
        activity?.let {
            val constants = AppConstants
            if (InternetConnectionHelper(it).isConnectionEstablished()) {
                var browserIntent: Intent = when (socialMedia) {
                    SocialMedia.FACEBOOK -> {
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(constants.FACEBOOK_URL)
                        )
                    }

                    SocialMedia.INSTAGRAM -> {
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(constants.INSTAGRAM_URL)
                        )
                    }

                    SocialMedia.YOUTUBE -> {
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(constants.YOUTUBE_URL)
                        )
                    }
                }
                startActivity(browserIntent)
            } else {
                ToastHelper(it).showToast(AppConstants.NO_INTERNET_CONNECTION)
            }
        }
    }
}