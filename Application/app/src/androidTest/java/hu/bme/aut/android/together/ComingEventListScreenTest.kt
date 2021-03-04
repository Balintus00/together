package hu.bme.aut.android.together

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import hu.bme.aut.android.together.features.currentevents.fragment.ComingEventListFragment
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComingEventListScreenTest {

    @Test
    fun testNavigationToAddEventScreen() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        runOnUiThread {
            navController.setGraph(R.navigation.mobile_navigation)
        }
        val comingEventListScenario = launchFragmentInContainer<ComingEventListFragment>(themeResId = R.style.AppTheme)
        comingEventListScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(ViewMatchers.withId(R.id.eventListAddFab)).perform(ViewActions.click())
        Assert.assertEquals(R.id.nameAdderFragment, navController.currentDestination?.id)
    }

}