package hu.bme.aut.android.together

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import hu.bme.aut.android.together.ui.screen.eventcontrol.details.fragment.EventDetailsFragment
import hu.bme.aut.android.together.hilt.launchFragmentInHiltContainer
import hu.bme.aut.android.together.mock.FakeNetworkDataSource
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class DetailsScreenTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    @SmallTest
    fun testNavigationToFullDescriptionScreen() {
        val fakeParticipantEventId = FakeNetworkDataSource.participantEventId
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        UiThreadStatement.runOnUiThread {
            navController.setGraph(R.navigation.mobile_navigation)
            navController.setCurrentDestination(R.id.eventDetailsFragment)
        }
        launchFragmentInHiltContainer<EventDetailsFragment>(themeResId = R.style.AppTheme,
            fragmentArgs = Bundle().apply {
                putLong("eventId", fakeParticipantEventId)
            }) {
            Navigation.setViewNavController(this.requireView(), navController)
        }
        onView(withId(R.id.clEventDetailsCoordinator)).perform(swipeUp())
        onView(withId(R.id.tvShowMoreInformationEventDetails))
            .perform(ViewActions.click())
        Truth.assertThat(navController.currentDestination?.id)
            .isEqualTo(R.id.eventDetailsFullDescriptionFragment)
    }

    @Test
    @SmallTest
    fun testParticipantNavigationToCommunicationScreen() {
        val fakeParticipantEventId = FakeNetworkDataSource.participantEventId
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        UiThreadStatement.runOnUiThread {
            navController.setGraph(R.navigation.mobile_navigation)
            navController.setCurrentDestination(R.id.eventDetailsFragment)
        }
        launchFragmentInHiltContainer<EventDetailsFragment>(themeResId = R.style.AppTheme,
            fragmentArgs = Bundle().apply {
                putLong("eventId", fakeParticipantEventId)
            }) {
            Navigation.setViewNavController(this.requireView(), navController)
        }
        onView(withId(R.id.fabActionEventDetails))
            .perform(ViewActions.click())
        Truth.assertThat(navController.currentDestination?.id)
            .isEqualTo(R.id.eventDetailsCommunicationFragment)
    }

    /**
     * TODO These tests cause native error on TravisCI. This should be investigated, and fixed.
    @Test
    @SmallTest
    fun testOrganiserOptionsNavigationToCommunicationScreen() {
    val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
    UiThreadStatement.runOnUiThread {
    navController.setGraph(R.navigation.mobile_navigation)
    navController.setCurrentDestination(R.id.eventDetailsFragment)
    }
    val detailsScreenScenario =
    launchFragmentInContainer<EventDetailsFragment>(themeResId = R.style.AppTheme,
    fragmentArgs = Bundle().apply {
    putBoolean("isOrganiser", true)
    putBoolean("isParticipant", true)
    putBoolean("isPrivate", true)
    putBoolean("isParticipantCountLimited", false)
    })
    detailsScreenScenario.onFragment { fragment ->
    Navigation.setViewNavController(fragment.requireView(), navController)
    }
    onView(withId(R.id.fabActionEventDetails))
    .check(matches(isDisplayed()))
    onView(withId(R.id.organiserSheet))
    .check(matches(not(isDisplayed())))
    onView(withId(R.id.fabActionEventDetails))
    .perform(ViewActions.click())
    onView(withId(R.id.organiserSheet))
    .check(matches(isDisplayed()))
    onView(withId(R.id.tvOrganiserActionShowMessages))
    .perform(ViewActions.click())
    Truth.assertThat(navController.currentDestination?.id)
    .isEqualTo(R.id.eventDetailsCommunicationFragment)
    }

    @Test
    @SmallTest
    fun testOrganiserOptionsNavigationToInvitationSenderScreen() {
    val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
    UiThreadStatement.runOnUiThread {
    navController.setGraph(R.navigation.mobile_navigation)
    navController.setCurrentDestination(R.id.eventDetailsFragment)
    }
    val detailsScreenScenario =
    launchFragmentInContainer<EventDetailsFragment>(themeResId = R.style.AppTheme,
    fragmentArgs = Bundle().apply {
    putBoolean("isOrganiser", true)
    putBoolean("isParticipant", true)
    putBoolean("isPrivate", true)
    putBoolean("isParticipantCountLimited", false)
    })
    detailsScreenScenario.onFragment { fragment ->
    Navigation.setViewNavController(fragment.requireView(), navController)
    }
    onView(withId(R.id.fabActionEventDetails))
    .perform(ViewActions.click())
    onView(withId(R.id.organiserSheet))
    .check(matches(isDisplayed()))
    onView(withId(R.id.tvOrganiserActionInvitePeople))
    .perform(ViewActions.click())
    Truth.assertThat(navController.currentDestination?.id)
    .isEqualTo(R.id.eventInvitationSenderFragment)
    }

    @Test
    @SmallTest
    fun testOrganiserOptionsNavigationToModificationScreen() {
    val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
    UiThreadStatement.runOnUiThread {
    navController.setGraph(R.navigation.mobile_navigation)
    navController.setCurrentDestination(R.id.eventDetailsFragment)
    }
    val detailsScreenScenario =
    launchFragmentInContainer<EventDetailsFragment>(themeResId = R.style.AppTheme,
    fragmentArgs = Bundle().apply {
    putBoolean("isOrganiser", true)
    putBoolean("isParticipant", true)
    putBoolean("isPrivate", true)
    putBoolean("isParticipantCountLimited", false)
    })
    detailsScreenScenario.onFragment { fragment ->
    Navigation.setViewNavController(fragment.requireView(), navController)
    }
    onView(withId(R.id.fabActionEventDetails))
    .perform(ViewActions.click())
    onView(withId(R.id.organiserSheet))
    .check(matches(isDisplayed()))
    onView(withId(R.id.tvOrganiserActionModifyEvent))
    .perform(ViewActions.click())
    Truth.assertThat(navController.currentDestination?.id)
    .isEqualTo(R.id.modifyEventDetailsFragment)
    }
     */

    @Test
    @SmallTest
    fun testOrganiserMenuNavigationToEventSettingsScreen() {
        val fakeOrganiserEventId = FakeNetworkDataSource.organiserEventId
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        UiThreadStatement.runOnUiThread {
            navController.setGraph(R.navigation.mobile_navigation)
            navController.setCurrentDestination(R.id.eventDetailsFragment)
        }
        launchFragmentInHiltContainer<EventDetailsFragment>(themeResId = R.style.AppTheme,
            fragmentArgs = Bundle().apply {
                putLong("eventId", fakeOrganiserEventId)
            }) {
            Navigation.setViewNavController(this.requireView(), navController)
        }
        onView(withId(R.id.actionOrganiserEventSettings))
            .perform(ViewActions.click())
        Truth.assertThat(navController.currentDestination?.id)
            .isEqualTo(R.id.eventSettingsFragment)
    }

}