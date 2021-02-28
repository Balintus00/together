package hu.bme.aut.android.together.features.shared.activity

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import hu.bme.aut.android.together.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)

        handleBottomNavBarVisibility(navView)
    }

    private fun handleBottomNavBarVisibility(navView: BottomNavigationView) {
        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.currentEventsListFragment -> showBottomNav(navView)
                R.id.profileFragment -> showBottomNav(navView)
                R.id.eventQueryFragment -> showBottomNav(navView)
                else -> hideBottomNav(navView)
            }
        }
    }

    private fun showBottomNav(bottomNav: BottomNavigationView) {
        bottomNav.visibility = View.VISIBLE

    }

    private fun hideBottomNav(bottomNav: BottomNavigationView) {
        bottomNav.visibility = View.GONE

    }
}