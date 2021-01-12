package com.example.parkinglot

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.parkinglot.vehicle.view.VehicleViewFragment
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Pattern.matches

@RunWith(AndroidJUnit4::class)
open class ListVehicle {
    /*@get: Rule
    val activityTest: ActivityScenarioRule<VehicleViewFragment> =
        ActivityScenarioRule<VehicleViewFragment>(
            VehicleViewFragment::class.java
        )

    @Test
    fun test_isRecyclerViewVisible_onAppLaunch() {
        onView(withId(R.id.recyclerViewSearchResults)).check(matches(isDisplayed()))
    }*/

}