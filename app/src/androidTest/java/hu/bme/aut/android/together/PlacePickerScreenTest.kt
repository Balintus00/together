package hu.bme.aut.android.together

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.SmallTest
import hu.bme.aut.android.together.ui.screen.addevent.pagerelement.detailsetter.place.fragment.PlacePickerFragment
import org.hamcrest.CoreMatchers.*
import org.junit.Test


class PlacePickerScreenTest {

    @Test
    @SmallTest
    fun testOnlinePlaceOptionBehaviour() {
        val placePickerScreenScenario =
            launchFragmentInContainer<PlacePickerFragment>(themeResId = R.style.AppTheme)
        onView(withId(R.id.spinnerAddEventPickPlaceCategories)).perform(click())
        val optionText = "Online"
        onData(allOf(`is`(instanceOf(String::class.java)), `is`(optionText))).perform(click())
        onView(withId(R.id.spinnerAddEventPickPlaceCategories)).check(matches(withSpinnerText(containsString(optionText))))
        onView(withId(R.id.etAddEventPickPlace)).check(matches(withText(optionText)))
        onView(withId(R.id.etAddEventPickPlace)).check(matches(not(isEnabled())))
    }

    @Test
    @SmallTest
    fun testVenuePlaceOptionBehaviour() {
        val placePickerScreenScenario =
            launchFragmentInContainer<PlacePickerFragment>(themeResId = R.style.AppTheme)
        onView(withId(R.id.spinnerAddEventPickPlaceCategories)).perform(click())
        val optionText = "Venue"
        onData(allOf(`is`(instanceOf(String::class.java)), `is`(optionText))).perform(click())
        onView(withId(R.id.spinnerAddEventPickPlaceCategories)).check(matches(withSpinnerText(containsString(optionText))))
        onView(withId(R.id.etAddEventPickPlace)).check(matches(isEnabled()))
        val mockedText = "Budapest"
        onView(withId(R.id.etAddEventPickPlace)).perform(typeText(mockedText))
        onView(withId(R.id.etAddEventPickPlace)).check(matches(withText(mockedText)))
    }

}