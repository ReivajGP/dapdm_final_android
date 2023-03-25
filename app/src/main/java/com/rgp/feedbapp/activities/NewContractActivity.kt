package com.rgp.feedbapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rgp.feedbapp.databinding.ActivityNewContractBinding
import com.rgp.feedbapp.model.TicketItem

class NewContractActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewContractBinding
    var ticket: TicketItem = TicketItem(
        "",
        0,
        "",
        "",
        "",
        "",
        "",
        0,
        0.0,
        0.0,
        0.0,
        0.0,
        null
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewContractBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
    }
}