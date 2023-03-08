package com.rgp.feedbapp.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rgp.feedbapp.databinding.FragmentHomeBinding


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

    enum class SocialMedia {
        FACEBOOK,
        INSTAGRAM,
        YOUTUBE
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
        var browserIntent: Intent = when (socialMedia) {
            SocialMedia.FACEBOOK -> {
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/feedback.cdmx/"))
            }

            SocialMedia.INSTAGRAM -> {
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/feedback_rock/"))
            }

            SocialMedia.YOUTUBE -> {
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/@feedbackmxoldies7833"))
            }
        }
        startActivity(browserIntent)
    }
}