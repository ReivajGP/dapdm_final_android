package com.rgp.feedbapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.rgp.feedbapp.R
import com.rgp.feedbapp.databinding.ActivityNewContractBinding
import com.rgp.feedbapp.model.TicketItem
import com.rgp.feedbapp.utils.AppConstants

class NewContractActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewContractBinding
    private lateinit var navController: NavController
    private val constants = AppConstants
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

        Log.i("NEWCONTRACT","AQUÍ ANDAMOS... CHINGÁNDOLE, NO HAY DE OTRA :3!")
       // navController = findNavController(R.id.container)
    }

    //override fun onSupportNavigateUp(): Boolean {
    //    return NavigationUI.navigateUp(navController, null)
    //}

    override fun onResume() {
        super.onResume()
    }
}