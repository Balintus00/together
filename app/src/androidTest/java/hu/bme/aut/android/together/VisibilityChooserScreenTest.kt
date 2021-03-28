package hu.bme.aut.android.together

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.filters.SmallTest
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import hu.bme.aut.android.together.features.addevent.fragment.pagerelement.detailsetter.VisibilityChooserFragment
import org.hamcrest.Matchers.not
import org.junit.Test

class VisibilityChooserScreenTest {

    @Test
    @SmallTest
    fun testClickingOnPublicCardShowsTheSpecialPublicOptions() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        UiThreadStatement.runOnUiThread {
            navController.setGraph(R.navigation.mobile_navigation)
        }
        val visibilityChooserScenario =
            launchFragmentInContainer<VisibilityChooserFragment>(themeResId = R.style.AppTheme)
        visibilityChooserScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        Espresso.onView(ViewMatchers.withId(R.id.cardPublic)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.clPublicOptions)).check(matches(isDisplayed()))
    }

    @Test
    @SmallTest
    fun testClickingOnPublicCardShowsTheSpecialPublicOptionsThenClickingOnPrivateCardHidesIt() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        UiThreadStatement.runOnUiThread {
            navController.setGraph(R.navigation.mobile_navigation)
        }
        val visibilityChooserScenario =
            launchFragmentInContainer<VisibilityChooserFragment>(themeResId = R.style.AppTheme)
        visibilityChooserScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        Espresso.onView(ViewMatchers.withId(R.id.cardPublic)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.cardPrivate)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.clPublicOptions))
            .check(matches(not(isDisplayed())))
    }
}