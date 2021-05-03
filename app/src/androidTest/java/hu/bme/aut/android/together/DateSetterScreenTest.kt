package hu.bme.aut.android.together

import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.SmallTest
import hu.bme.aut.android.together.features.addevent.pagerelement.detailsetter.date.fragment.DateSetterFragment
import org.hamcrest.Matchers
import org.junit.Test


class DateSetterScreenTest {

    @Test
    @SmallTest
    fun testFromDateSetting() {
        val dateSetterScreenScenario =
            launchFragmentInContainer<DateSetterFragment>(themeResId = R.style.AppTheme)
        onView(withId(R.id.tvAddEventFromDate))
            .perform(click())
        val mockedYear = 2022
        val mockedMonth = 3
        val mockedDay = 2
        onView(withClassName(Matchers.equalTo(DatePicker::class.java.name))).perform(
            PickerActions.setDate(
                mockedYear,
                mockedMonth,
                mockedDay
            )
        )
        onView(withId(android.R.id.button1)).perform(click())
        onView(withId(R.id.tvAddEventFromDate)).check(matches(withText("$mockedYear.${mockedMonth - 1}.$mockedDay.")))
    }

    @Test
    @SmallTest
    fun testFromTimeSetting() {
        val dateSetterScreenScenario =
            launchFragmentInContainer<DateSetterFragment>(themeResId = R.style.AppTheme)
        onView(withId(R.id.tvAddEventFromHourMinute))
            .perform(click())
        val mockedHour = 14
        val mockedMinute = 45
        onView(withClassName(Matchers.equalTo(TimePicker::class.java.name))).perform(
            PickerActions.setTime(
                mockedHour,
                mockedMinute
            )
        )
        onView(withId(android.R.id.button1)).perform(click())
        onView(withId(R.id.tvAddEventFromHourMinute)).check(matches(withText("$mockedHour:$mockedMinute")))
    }

    @Test
    @SmallTest
    fun testToDateSetting() {
        val dateSetterScreenScenario =
            launchFragmentInContainer<DateSetterFragment>(themeResId = R.style.AppTheme)
        onView(withId(R.id.tvAddEventToDate))
            .perform(click())
        val mockedYear = 2022
        val mockedMonth = 3
        val mockedDay = 2
        onView(withClassName(Matchers.equalTo(DatePicker::class.java.name))).perform(
            PickerActions.setDate(
                mockedYear,
                mockedMonth,
                mockedDay
            )
        )
        onView(withId(android.R.id.button1)).perform(click())
        onView(withId(R.id.tvAddEventToDate)).check(matches(withText("$mockedYear.${mockedMonth - 1}.$mockedDay.")))
    }

    @Test
    @SmallTest
    fun testToTimeSetting() {
        val dateSetterScreenScenario =
            launchFragmentInContainer<DateSetterFragment>(themeResId = R.style.AppTheme)
        onView(withId(R.id.tvAddEventToHourMinute))
            .perform(click())
        val mockedHour = 14
        val mockedMinute = 45
        onView(withClassName(Matchers.equalTo(TimePicker::class.java.name))).perform(
            PickerActions.setTime(
                mockedHour,
                mockedMinute
            )
        )
        onView(withId(android.R.id.button1)).perform(click())
        onView(withId(R.id.tvAddEventToHourMinute)).check(matches(withText("$mockedHour:$mockedMinute")))
    }

}