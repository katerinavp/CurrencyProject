package com.katerinavp.currency

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
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

//        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
//        binding.bottomNavigation.setOnNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.nav_favourites -> navGraph.startDestination = R.id.nav_favourites
//                R.id.nav_currency -> navGraph.startDestination = R.id.nav_currency
//                R.id.nav_exchange -> navGraph.startDestination = R.id.nav_exchange
//                else -> navGraph.startDestination = R.id.nav_currency
//
//            }
//        }

        if (savedInstanceState == null) {
            setStartDestination()
        }

        binding.bottomNavigation.setupWithNavController(navController)
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            when (destination.id) {
//
//                else -> binding.bottomNavigation.visibility = View.VISIBLE
//            }
//
//        }
    }

    private fun setStartDestination() {
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
//        when (binding.bottomNavigation.menu.) {
//            R.id.nav_favourites -> navGraph.startDestination= R.id.nav_favourites
//            R.id.nav_currency -> navGraph.startDestination = R.id.nav_currency
//            R.id.nav_exchange-> navGraph.startDestination = R.id.nav_exchange
//            else -> navGraph.startDestination = R.id.nav_currency
//        }
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