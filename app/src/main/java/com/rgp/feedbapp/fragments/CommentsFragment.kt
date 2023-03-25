package com.rgp.feedbapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rgp.feedbapp.R
import com.rgp.feedbapp.databinding.FragmentCommentsBinding
import com.rgp.feedbapp.helpers.ToastHelper
import com.rgp.feedbapp.model.CommentResponse
import com.rgp.feedbapp.utils.AppConstants
import com.rgp.feedbapp.utils.CommentsAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsFragment : Fragment() {

    // Properties
    private var _binding: FragmentCommentsBinding? = null
    private val binding get() = _binding!!

    // Fragment lifecycle methods
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCommentsBinding.inflate(LayoutInflater.from(context))
        binding.progressBar.visibility = View.INVISIBLE
        setOnClickListeners()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    // Private methods
    private fun setOnClickListeners() {
        binding.btSend.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            CoroutineScope(Dispatchers.IO).launch {
                val apiCall = AppConstants.getRetrofit().create(CommentsAPI::class.java).sendComment(AppConstants.COMMENTS_ENDPOINT)
                apiCall.enqueue(object: Callback<CommentResponse> {
                    override fun onResponse(
                        call: Call<CommentResponse>,
                        response: Response<CommentResponse>
                    ) {
                        binding.progressBar.visibility = View.INVISIBLE
                        if (response.body()!!.commentReceived) {
                            launchSuccessAnimation()
                        } else {
                            activity?.let { ToastHelper(it).showToast(AppConstants.COMMENTS_SERVICE_NOT_AVAILABLE_TOAST_MESSAGE) }
                        }
                    }

                    override fun onFailure(call: Call<CommentResponse>, t: Throwable) {
                        binding.progressBar.visibility = View.INVISIBLE
                        activity?.let { ToastHelper(it).showToast(AppConstants.COMMENTS_SERVICE_NOT_AVAILABLE_TOAST_MESSAGE) }
                    }
                })
            }
        }
    }

    private fun launchSuccessAnimation() {
        binding.avReady.setAnimation(R.raw.ready)
        binding.avReady.playAnimation()
    }
}