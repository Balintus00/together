package hu.bme.aut.android.together

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.SmallTest
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import com.google.common.truth.Truth
import hu.bme.aut.android.together.features.addevent.fragment.pagerelement.detailsetter.NameAdderFragment
import hu.bme.aut.android.together.features.currentevents.fragment.ComingEventListFragment
import org.junit.Test

class AddEventNameAdderScreenTest {

    // TODO using @UiThreadTest annotation might result in better code
    @Test
    @SmallTest
    fun testCharacterCounterChanges() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        UiThreadStatement.runOnUiThread {
            navController.setGraph(R.navigation.mobile_navigation)
        }
        val nameAdderScenario =
            launchFragmentInContainer<NameAdderFragment>(themeResId = R.style.AppTheme)
        nameAdderScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        val maxLength = 50
        Espresso.onView(ViewMatchers.withId(R.id.etAddEventName)).perform(ViewActions.typeText("333"))
        ViewMatchers.withId(R.id.tvRemainingCharacterCount).matches(withText((maxLength - 3).toString()))
    }

}