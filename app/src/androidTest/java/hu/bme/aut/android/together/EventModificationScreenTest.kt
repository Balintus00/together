package hu.bme.aut.android.together

import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.SmallTest
import hu.bme.aut.android.together.features.eventdetails.fragment.details.ModifyEventDetailsFragment
import org.hamcrest.Matchers
import org.junit.Test

class EventModificationScreenTest {

    @Test
    @SmallTest
    fun testFromDateTimeSettingBehaviour() {
        val eventModificationScreenScenario =
            launchFragmentInContainer<ModifyEventDetailsFragment>(themeResId = R.style.AppTheme)
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
            .check(ViewAssertions.matches(ViewMatchers.withText("$mockedYear.${mockedMonth - 1}.$mockedDay $mockedHour:$mockedMinute")))
    }

    @Test
    @SmallTest
    fun testToDateTimeSettingBehaviour() {
        val eventModificationScreenScenario =
            launchFragmentInContainer<ModifyEventDetailsFragment>(themeResId = R.style.AppTheme)
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
            .check(ViewAssertions.matches(ViewMatchers.withText("$mockedYear.${mockedMonth - 1}.$mockedDay $mockedHour:$mockedMinute")))
    }
}