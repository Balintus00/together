package hu.bme.aut.android.together

import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import hu.bme.aut.android.together.ui.screen.eventcontrol.modifyevent.fragment.ModifyEventDetailsFragment
import hu.bme.aut.android.together.hilt.launchFragmentInHiltContainer
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class EventModificationScreenTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    @SmallTest
    fun testFromDateTimeSettingBehaviour() {
        val fakeEventId = 1L
        launchFragmentInHiltContainer<ModifyEventDetailsFragment>(themeResId = R.style.AppTheme,
            fragmentArgs = Bundle().apply {
                putLong("eventId", fakeEventId)
            })
        Espresso.onView(ViewMatchers.withId(R.id.tvFromDate))
            .perform(ViewActions.click())
        val mockedYear = 2022
        val mockedMonth = 3
        val mockedDay = 2
        val mockedHour = 14
        val mockedMinute = 45
        Espresso.onView(ViewMatchers.withClassName(Matchers.equalTo(DatePicker::class.java.name)))
            .perform(
                PickerActions.setDate(
                    mockedYear,
                    mockedMonth,
                    mockedDay
                )
            )
        Espresso.onView(ViewMatchers.withId(android.R.id.button1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withClassName(Matchers.equalTo(TimePicker::class.java.name)))
            .perform(
                PickerActions.setTime(
                    mockedHour,
                    mockedMinute
                )
            )
        Espresso.onView(ViewMatchers.withId(android.R.id.button1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.tvFromDate))
            .check(ViewAssertions.matches(ViewMatchers.withText("$mockedYear.${mockedMonth - 1}.$mockedDay. $mockedHour:$mockedMinute")))
    }

    @Test
    @SmallTest
    fun testToDateTimeSettingBehaviour() {
        val fakeEventId = 1L
        launchFragmentInHiltContainer<ModifyEventDetailsFragment>(themeResId = R.style.AppTheme,
            fragmentArgs = Bundle().apply {
                putLong("eventId", fakeEventId)
            })
        Espresso.onView(ViewMatchers.withId(R.id.tvToDate))
            .perform(ViewActions.click())
        val mockedYear = 2022
        val mockedMonth = 3
        val mockedDay = 2
        val mockedHour = 14
        val mockedMinute = 45
        Espresso.onView(ViewMatchers.withClassName(Matchers.equalTo(DatePicker::class.java.name)))
            .perform(
                PickerActions.setDate(
                    mockedYear,
                    mockedMonth,
                    mockedDay
                )
            )
        Espresso.onView(ViewMatchers.withId(android.R.id.button1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withClassName(Matchers.equalTo(TimePicker::class.java.name)))
            .perform(
                PickerActions.setTime(
                    mockedHour,
                    mockedMinute
                )
            )
        Espresso.onView(ViewMatchers.withId(android.R.id.button1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.tvToDate))
            .check(ViewAssertions.matches(ViewMatchers.withText("$mockedYear.${mockedMonth - 1}.$mockedDay. $mockedHour:$mockedMinute")))
    }
}