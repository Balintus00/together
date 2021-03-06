package hu.bme.aut.android.together.activity

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.android.together.R

/**
 * The application implements the Single Activity Architecture. It is the application's activity,
 * that contains the application's fragments.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        navView.setupWithNavController(navController)

        handleBottomNavBarVisibility(navView)
    }

    /**
     * Sets [navController]'s onDestinationChanged behaviour.
     * @param navView the application's main bottom navigation bar, that should be displayed on
     * several destinations.
     */
    private fun handleBottomNavBarVisibility(navView: BottomNavigationView) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.currentEventsListFragment -> showBottomNav(navView)
                R.id.profileFragment -> showBottomNav(navView)
                R.id.eventQueryFragment -> showBottomNav(navView)
                R.id.eventSearchResultFragment -> showBottomNav(navView)
                R.id.eventDetailsFragment -> showBottomNav(navView)
                R.id.eventDetailsCommunicationFragment -> showBottomNav(navView)
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