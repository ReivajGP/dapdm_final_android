package com.rgp.feedbapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.rgp.feedbapp.adapters.CalendarAdapter
import com.rgp.feedbapp.databinding.FragmentCalendarBinding
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        requestCalendarEvents()
        _binding = FragmentCalendarBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    //Private methods
    private fun requestCalendarEvents() {
        CoroutineScope(Dispatchers.IO).launch {
            val apiCall = constants.getRetrofit().create(CalendarAPI::class.java).getCalendarEvents(constants.CALENDAR_ENDPOINT)
            apiCall.enqueue(object: Callback<ArrayList<CalendarItem>> {
                override fun onResponse(
                    call: Call<ArrayList<CalendarItem>>,
                    response: Response<ArrayList<CalendarItem>>
                ) {
                    Log.d("CALENDAR", "Respuesta del servidor: ${response}")
                    Log.d("CALENDAR", "Datos: ${response.body().toString()}")
                    binding.rvCalendar.layoutManager = LinearLayoutManager(requireContext())
                    binding.rvCalendar.adapter = CalendarAdapter(requireContext(), response.body()!!)
                }

                override fun onFailure(call: Call<ArrayList<CalendarItem>>, t: Throwable) {
                    Log.e("CALENDAR", "ERROR: No se pudo conectar al servicio: ${t.message}")
                }
            })
        }
    }
}