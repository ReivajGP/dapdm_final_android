package com.rgp.feedbapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.rgp.feedbapp.R
import com.rgp.feedbapp.databinding.ActivityMainBinding
import com.rgp.feedbapp.helpers.AuthenticationHelper
import com.rgp.feedbapp.model.TicketItem
import com.rgp.feedbapp.utils.AppConstants

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val constants = AppConstants
    //lateinit var authenticationHelper: AuthenticationHelper

    // Activity lifecycle methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //authenticationHelper = AuthenticationHelper(this)

        setupActionBar()
        setupSideMenu()
    }

    // Public methods
    fun click(view: View) {
        when (view.id) {
            R.id.ivMenu -> {
                binding.dlMain.openDrawer(GravityCompat.START)
            }
        }
    }

    fun ticketSelected(ticket: TicketItem) {
        val intent = Intent(this, TicketSummaryActivity::class.java).apply {
            putExtra(constants.INTENT_MAIN_TO_TICKET_SUMMARY_TICKET_ID, ticket)
        }
        startActivity(intent)
    }

    fun newContractSelected() {
        Log.d("CONTRACT", "ADD BUTTON PRESSED")
        val intent = Intent (this, NewContractActivity::class.java)
        startActivity(intent)
    }

    // Private methods
    private fun setupActionBar() {
        navController = findNavController((R.id.navHostFragment))

        val appBarConfig = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_calendar,
                R.id.navigation_contract,
                R.id.navigation_profile
            )
        )

        //setupActionBarWithNavController(navController, appBarConfig)
        binding.navView.setupWithNavController(navController)
    }

    private fun setupSideMenu() {
        val slideMenuNavController = Navigation.findNavController(this, R.id.navHostFragment)
        NavigationUI.setupWithNavController(binding.nvMain, slideMenuNavController)

        navController.addOnDestinationChangedListener(NavController.OnDestinationChangedListener { _, destination, _ ->
            binding.tvTitle.text = destination.label
        })
    }
}