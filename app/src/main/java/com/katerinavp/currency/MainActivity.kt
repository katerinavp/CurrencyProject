package com.katerinavp.currency

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.katerinavp.currency.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val navController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.frame_main
        ) as NavHostFragment

        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            setStartDestination()
        }

        binding.bottomNavigation.setupWithNavController(navController)

    }

    private fun setStartDestination() {
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_favourites -> navGraph.startDestination = R.id.nav_favourites
                R.id.nav_currency -> navGraph.startDestination = R.id.nav_currency
                R.id.nav_exchange -> navGraph.startDestination = R.id.nav_exchange

            }
            false
        }

        navController.graph = navGraph


    }

    //bottom nav
    override fun onOptionsItemSelected(item: MenuItem) =
        item.onNavDestinationSelected(navController) ||
                super.onOptionsItemSelected(item)


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.frame_main)
        return navController.navigateUp()
    }

}