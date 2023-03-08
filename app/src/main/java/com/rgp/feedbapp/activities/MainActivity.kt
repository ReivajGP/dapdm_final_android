package com.rgp.feedbapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Activity lifecycle methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
    }

    // Public methods
    fun click(view: View) {
        when (view.id) {
            R.id.ivMenu -> {
                binding.dlMain.openDrawer(GravityCompat.START)
            }
        }
    }

    // Private methods
    private fun setupView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController((R.id.navHostFragment))

        val appBarConfig = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_calendar,
                R.id.navigation_contract,
                R.id.navigation_profile
            )
        )

        setupActionBarWithNavController(navController, appBarConfig)
        binding.navView.setupWithNavController(navController)

        val slideMenuNavController = Navigation.findNavController(this, R.id.navHostFragment)
        NavigationUI.setupWithNavController(binding.nvMain, slideMenuNavController)

        navController.addOnDestinationChangedListener(NavController.OnDestinationChangedListener { controller, destination, arguments ->
            binding.tvTitle.text = destination.label
        })
    }
}