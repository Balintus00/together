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
import hu.bme.aut.android.together.features.addevent.pagerelement.detailsetter.visibility.fragment.VisibilityChooserFragment
import org.hamcrest.Matchers.not
import org.junit.Test

class VisibilityChooserScreenTest {

    /**
     * TODO:
     *  These tests are currently disabled, because they throw the following error:
     *  java.lang.IllegalStateException:ConstraintLayout does not have a NavController set
     *  The problem is explained here: https://dev.to/sh3lan93/android-navigation-component-issue-ik3
     *  The explained workaround doesn't work in this situation, because instead of the deprecated fragment
     *  tag FragmentContainerView is being used.
     *  The part of application works fine that this tests check, but this "bug" should be somehow fixed later.
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
    */
}