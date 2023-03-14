package com.rgp.feedbapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.rgp.feedbapp.activities.MainActivity
import com.rgp.feedbapp.adapters.TicketAdapter
import com.rgp.feedbapp.databinding.FragmentContractBinding
import com.rgp.feedbapp.helpers.AuthenticationHelper
import com.rgp.feedbapp.helpers.InternetConnectionHelper
import com.rgp.feedbapp.helpers.ToastHelper
import com.rgp.feedbapp.model.TicketItem
import com.rgp.feedbapp.utils.AppConstants
import com.rgp.feedbapp.utils.TicketAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContractFragment : Fragment() {

    // Properties
    private var _binding: FragmentContractBinding? = null
    private val binding get() = _binding!!
    private val constants = AppConstants

    // Fragment lifecycle methods
    override fun onStart() {
        super.onStart()
        setupClickListeners()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContractBinding.inflate(LayoutInflater.from(context))
        binding.progressBar.visibility = View.INVISIBLE
        activity?.let {
            if (InternetConnectionHelper(it).isConnectionEstablished()) {
                if (AuthenticationHelper(it).isUserLoggedIn()) {
                    requestTickets()
                } else {
                    ToastHelper(it).showToast(constants.UNABLE_LOAD_CONTENT_UNLESS_LOGGED_IN_TOAST_MESSAGE)
                }
            } else {
                ToastHelper(it).showToast(constants.NO_INTERNET_CONNECTION)
            }
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    // Private methods
    private fun setupClickListeners() {
        binding.cvNewHiring.setOnClickListener {
            activity.let {
                if (it is MainActivity) {
                    it.newContractSelected()
                }
            }
        }
    }

    private fun requestTickets() {
        binding.progressBar.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.IO).launch {
            val apiCall = constants.getRetrofit().create(TicketAPI::class.java).getTicketsData(constants.TICKETS_ENDPOINT)
            apiCall.enqueue(object: Callback<ArrayList<TicketItem>> {
                override fun onResponse(
                    call: Call<ArrayList<TicketItem>>,
                    response: Response<ArrayList<TicketItem>>
                ) {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.rvContracts.layoutManager = LinearLayoutManager(requireContext())
                    binding.rvContracts.adapter = TicketAdapter(requireContext(), response.body()!!)
                }

                override fun onFailure(call: Call<ArrayList<TicketItem>>, t: Throwable) {
                    binding.progressBar.visibility = View.INVISIBLE
                    activity?.let {
                        ToastHelper(it).showToast(constants.TICKETS_SERVICE_NOT_AVAILABLE_TOAST_MESSAGE)
                    }
                }
            })
        }
    }
}