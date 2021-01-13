package com.example.parkinglot

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.example.parkinglot.generic.Utils.Companion.convertDateInMilliseconds
import com.example.parkinglot.parkinglot.view.MainActivity
import com.example.parkinglot.vehicle.view.VehicleViewAdapter
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
open class AddVehicleTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.example.parkinglot", appContext.packageName)
    }

    @get: Rule
    val activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testAddCar(){
        //Arrange
        val licensePlate = "TEST"+convertDateInMilliseconds()
        val listItem = 2

        //Actions
        onView(withId(R.id.recyclerViewList)).perform(RecyclerViewActions.actionOnItemAtPosition<VehicleViewAdapter.ViewHolder>(listItem, click())) // Vista ppal
        onView(withId(R.id.buttonAddCar)).perform(click()) // Vista de vehiculos
        onView(withId(R.id.editTextLicensePlate)).perform(typeText(licensePlate), ViewActions.closeSoftKeyboard()) // Formulario de agregar

        //Assert
        onView(withId(R.id.buttonAddCarFormulary)).perform(click()) // Formulario de agregar*/
    }

    @Test
    fun testAddMotorcycle(){
        //Arrange
        val licensePlate = "TEST"+convertDateInMilliseconds()
        val cylinder = "400"
        val listItem = 2

        //Act
        onView(withId(R.id.recyclerViewList)).perform(RecyclerViewActions.actionOnItemAtPosition<VehicleViewAdapter.ViewHolder>(listItem, click()))
        onView(withId(R.id.buttonAddCar)).perform(click())
        onView(withId(R.id.switchMotorcycleType)).perform(click())
        onView(withId(R.id.editTextLicensePlate)).perform(typeText(licensePlate), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.editTextCylinderCapacity)).perform(typeText(cylinder), ViewActions.closeSoftKeyboard())

        //Assert
        onView(withId(R.id.buttonAddCarFormulary)).perform(click())
    }
}