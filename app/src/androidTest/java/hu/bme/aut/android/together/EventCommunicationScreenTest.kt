package hu.bme.aut.android.together

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.SmallTest
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import com.google.common.truth.Truth
import hu.bme.aut.android.together.features.eventcontrol.communication.fragment.EventDetailsCommunicationFragment
import org.junit.Test

class EventCommunicationScreenTest {

    @Test
    @SmallTest
    fun testOrganisersQuestionInboxNavigation() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        UiThreadStatement.runOnUiThread {
            navController.setGraph(R.navigation.mobile_navigation)
            navController.setCurrentDestination(R.id.eventDetailsCommunicationFragment)
        }
        val eventCommunicationScreenScenario =
            launchFragmentInContainer<EventDetailsCommunicationFragment>(themeResId = R.style.AppTheme,
                fragmentArgs = Bundle().apply {
                    putBoolean("isOrganiser", true)
                })
        eventCommunicationScreenScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        Espresso.onView(ViewMatchers.withId(R.id.actionOrganiserEventQuestions))
            .perform(ViewActions.click())
        Truth.assertThat(navController.currentDestination?.id)
            .isEqualTo(R.id.eventQuestionsFragment)
    }

}