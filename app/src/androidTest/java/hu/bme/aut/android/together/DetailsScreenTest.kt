package hu.bme.aut.android.together

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.SmallTest
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import com.google.common.truth.Truth
import hu.bme.aut.android.together.features.eventdetails.fragment.details.EventDetailsFragment
import org.junit.Test

class DetailsScreenTest {

    @Test
    @SmallTest
    fun testNavigationToFullDescriptionScreen() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        UiThreadStatement.runOnUiThread {
            navController.setGraph(R.navigation.mobile_navigation)
            navController.setCurrentDestination(R.id.eventDetailsFragment)
        }
        val detailsScreenScenario =
            launchFragmentInContainer<EventDetailsFragment>(themeResId = R.style.AppTheme,
                fragmentArgs = Bundle().apply {
                    putBoolean("isOrganiser", false)
                    putBoolean("isParticipant", true)
                    putBoolean("isPrivate", true)
                    putBoolean("isParticipantCountLimited", false)
                })
        detailsScreenScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
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
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        UiThreadStatement.runOnUiThread {
            navController.setGraph(R.navigation.mobile_navigation)
            navController.setCurrentDestination(R.id.eventDetailsFragment)
        }
        val detailsScreenScenario =
            launchFragmentInContainer<EventDetailsFragment>(themeResId = R.style.AppTheme,
                fragmentArgs = Bundle().apply {
                    putBoolean("isOrganiser", false)
                    putBoolean("isParticipant", true)
                    putBoolean("isPrivate", true)
                    putBoolean("isParticipantCountLimited", false)
                })
        detailsScreenScenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.fabActionEventDetails))
            .perform(ViewActions.click())
        Truth.assertThat(navController.currentDestination?.id)
            .isEqualTo(R.id.eventDetailsCommunicationFragment)
    }

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

    @Test
    @SmallTest
    fun testOrganiserMenuNavigationToEventSettingsScreen() {
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
        onView(withId(R.id.actionOrganiserEventSettings))
            .perform(ViewActions.click())
        Truth.assertThat(navController.currentDestination?.id)
            .isEqualTo(R.id.eventSettingsFragment)
    }

}