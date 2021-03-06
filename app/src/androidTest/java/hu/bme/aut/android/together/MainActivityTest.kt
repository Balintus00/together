package hu.bme.aut.android.together

import android.util.Log
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.findFragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import hu.bme.aut.android.together.features.currentevents.fragment.CurrentEventsListsContainerFragment
import hu.bme.aut.android.together.features.shared.activity.MainActivity
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun testMainBottomNavBar() {
        val scenario = launchActivity<MainActivity>()
        onView(withId(R.id.profileFragment)).perform(ViewActions.click())
        scenario.onActivity { activity ->
            Assert.assertEquals(
                R.id.profileFragment,
                activity.navController.currentDestination?.id
            )
        }
    }

}