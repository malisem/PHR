package com.project.phr.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.project.phr.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?

        // Set the Toolbar to act as the ActionBar for this Activity window.
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        if (navHostFragment == null) {
            // Log an error or throw an exception
            Log.e("MainActivity", "NavHostFragment not found.")
            return
        }


        // Now you can set up the NavController
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.medicationsFragment,
                R.id.appointmentsFragment,
                R.id.testResultsFragment,
                R.id.notesFragment
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}

