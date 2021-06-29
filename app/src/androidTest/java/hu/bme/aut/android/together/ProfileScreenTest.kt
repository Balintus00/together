package hu.bme.aut.android.together

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.filters.SmallTest
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import hu.bme.aut.android.together.ui.screen.profile.fragment.ProfileFragment
import hu.bme.aut.android.together.hilt.launchFragmentInHiltContainer
import hu.bme.aut.android.together.mock.FakeNetworkDataSource
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class ProfileScreenTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    // TODO using @UiThreadTest annotation might result in better code
    @Test
    @SmallTest
    fun testNavigationToSettingsScreen() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        UiThreadStatement.runOnUiThread {
            navController.setGraph(R.navigation.mobile_navigation)
            navController.setCurrentDestination(R.id.profileFragment)
        }
        launchFragmentInHiltContainer<ProfileFragment>(themeResId = R.style.AppTheme) {
            Navigation.setViewNavController(this.requireView(), navController)
        }
        onView(withId(R.id.actionProfileSettingsOption))
            .perform(ViewActions.click())
        Truth.assertThat(navController.currentDestination?.id).isEqualTo(R.id.settingsFragment)
    }

    @Test
    @SmallTest
    fun testNavigationToInvitesInboxScreen() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        UiThreadStatement.runOnUiThread {
            navController.setGraph(R.navigation.mobile_navigation)
            navController.setCurrentDestination(R.id.profileFragment)
        }
        launchFragmentInHiltContainer<ProfileFragment>(themeResId = R.style.AppTheme) {
            Navigation.setViewNavController(this.requireView(), navController)
        }
        onView(withId(R.id.actionInvitations))
            .perform(ViewActions.click())
        Truth.assertThat(navController.currentDestination?.id).isEqualTo(R.id.eventInvitationsFragment)
    }

    @Test
    @MediumTest
    fun testRepresentedProfileDataIsCorrect() {
        launchFragmentInHiltContainer<ProfileFragment>(themeResId = R.style.AppTheme)
        onView(withId(R.id.tvProfileName)).check(matches(withText(FakeNetworkDataSource.usedProfileData.name)))
        onView(withId(R.id.tvProfileUsername)).check(matches(withText(FakeNetworkDataSource.usedProfileData.username)))
        onView(withId(R.id.tvProfileBirthValue)).check(matches(withText(FakeNetworkDataSource.usedDateString)))
    }

}