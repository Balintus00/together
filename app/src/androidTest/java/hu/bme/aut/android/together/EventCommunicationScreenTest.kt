package hu.bme.aut.android.together

import android.os.Bundle
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
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import hu.bme.aut.android.together.features.eventcontrol.communication.pager.fragment.EventCommunicationPagerFragment
import hu.bme.aut.android.together.hilt.launchFragmentInHiltContainer
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class EventCommunicationScreenTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    @SmallTest
    fun testOrganisersQuestionInboxNavigation() {
        val exampleEventId = 1L
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        UiThreadStatement.runOnUiThread {
            navController.setGraph(R.navigation.mobile_navigation)
            navController.setCurrentDestination(R.id.eventDetailsCommunicationFragment)
        }
        launchFragmentInHiltContainer<EventCommunicationPagerFragment>(themeResId = R.style.AppTheme,
            fragmentArgs = Bundle().apply {
                putLong("eventId", exampleEventId)
            }) {
            Navigation.setViewNavController(this.requireView(), navController)
        }
        Espresso.onView(ViewMatchers.withId(R.id.actionOrganiserEventQuestions))
            .perform(ViewActions.click())
        Truth.assertThat(navController.currentDestination?.id)
            .isEqualTo(R.id.eventQuestionsFragment)
    }

}