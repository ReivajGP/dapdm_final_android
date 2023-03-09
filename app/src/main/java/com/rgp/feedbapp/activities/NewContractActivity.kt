package com.rgp.feedbapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rgp.feedbapp.databinding.ActivityNewContractBinding
import com.rgp.feedbapp.utils.AppConstants

class NewContractActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewContractBinding
    private val constants = AppConstants

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewContractBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}