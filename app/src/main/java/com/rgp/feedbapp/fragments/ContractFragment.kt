package com.rgp.feedbapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.rgp.feedbapp.adapters.CalendarAdapter
import com.rgp.feedbapp.adapters.TicketAdapter
import com.rgp.feedbapp.databinding.FragmentContractBinding
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
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentContractBinding.inflate(LayoutInflater.from(context))
        setupClickListeners()
        requestTickets()
        return binding.root
    }

    // Private methods
    private fun setupClickListeners() {
        binding.cvNewHiring.setOnClickListener {
            Log.d("CONTRACT", "ADD BUTTON PRESSED")
        }
    }

    private fun requestTickets() {
        CoroutineScope(Dispatchers.IO).launch {
            val apiCall = constants.getRetrofit().create(TicketAPI::class.java).getTicketsData(constants.TICKETS_ENDPOINT)
            apiCall.enqueue(object: Callback<ArrayList<TicketItem>> {
                override fun onResponse(
                    call: Call<ArrayList<TicketItem>>,
                    response: Response<ArrayList<TicketItem>>
                ) {
                    Log.d("TICKET", "Respuesta del servidor: ${response}")
                    Log.d("TICKET", "Datos: ${response.body().toString()}")
                    binding.rvContracts.layoutManager = LinearLayoutManager(requireContext())
                    binding.rvContracts.adapter = TicketAdapter(requireContext(), response.body()!!)
                }

                override fun onFailure(call: Call<ArrayList<TicketItem>>, t: Throwable) {
                    Log.e("TICKET", "ERROR: No se pudo conectar al servicio: ${t.message}")
                }
            })
        }
    }
}