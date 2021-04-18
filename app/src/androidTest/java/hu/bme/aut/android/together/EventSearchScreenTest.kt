package hu.bme.aut.android.together

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import com.google.common.truth.Truth
import hu.bme.aut.android.together.features.searchevent.searcher.fragment.EventQueryFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EventSearchScreenTest {

    @Test
    @SmallTest
    fun testNavigationToResultsScreenTest() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        UiThreadStatement.runOnUiThread {
            navController.setGraph(R.navigation.mobile_navigation)
            navController.setCurrentDestination(R.id.eventQueryFragment)
        }
        val eventSearchScreenScenario =
            launchFragmentInContainer<EventQueryFragment>(themeResId = R.style.AppTheme)
        eventSearchScreenScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        Espresso.onView(ViewMatchers.withId(R.id.fabSearchEvent))
            .perform(ViewActions.click())
        Truth.assertThat(navController.currentDestination?.id).isEqualTo(R.id.eventSearchResultFragment)
    }

}