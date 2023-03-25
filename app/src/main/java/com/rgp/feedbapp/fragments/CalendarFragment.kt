package com.rgp.feedbapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.rgp.feedbapp.adapters.CalendarAdapter
import com.rgp.feedbapp.databinding.FragmentCalendarBinding
import com.rgp.feedbapp.helpers.InternetConnectionHelper
import com.rgp.feedbapp.helpers.ToastHelper
import com.rgp.feedbapp.model.CalendarItem
import com.rgp.feedbapp.utils.AppConstants
import com.rgp.feedbapp.utils.CalendarAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CalendarFragment : Fragment() {

    // Properties
    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!
    private val constants = AppConstants

    // Fragment lifecycle methods
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalendarBinding.inflate(LayoutInflater.from(context))
        activity?.let {
            if (InternetConnectionHelper(it).isConnectionEstablished()) {
                requestCalendarEvents()
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

    //Private methods
    private fun requestCalendarEvents() {
        binding.progressBar.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.IO).launch {
            val apiCall = constants.getRetrofit().create(CalendarAPI::class.java).getCalendarEvents(constants.CALENDAR_ENDPOINT)
            apiCall.enqueue(object: Callback<ArrayList<CalendarItem>> {
                override fun onResponse(
                    call: Call<ArrayList<CalendarItem>>,
                    response: Response<ArrayList<CalendarItem>>
                ) {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.rvCalendar.layoutManager = LinearLayoutManager(requireContext())
                    binding.rvCalendar.adapter = CalendarAdapter(requireContext(), response.body()!!)
                }

                override fun onFailure(call: Call<ArrayList<CalendarItem>>, t: Throwable) {
                    binding.progressBar.visibility = View.INVISIBLE
                    activity?.let {
                        ToastHelper(it).showToast(constants.CALENDAR_SERVICE_NOT_AVAILABLE_TOAST_MESSAGE)
                    }
                }
            })
        }
    }
}