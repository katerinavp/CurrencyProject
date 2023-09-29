package com.katerinavp.currency.fragments


import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.katerinavp.currencies_screen_impl.R
import com.katerinavp.currency.AppTest
import com.katerinavp.currency.MainActivity
import com.katerinavp.currency.di.components.AppComponentTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

class CurrencyFragmentTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Inject
    lateinit var idlingResource: CountingIdlingResource

    @Before
    fun init() {
        activityRule.scenario.onActivity {
            (it.application as AppTest).appComponent.inject(this)
        }

        IdlingRegistry.getInstance().register(idlingResource)
    }


    @After
    fun after() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

//    @Test
//    fun filterTest() {
//        Thread.sleep(5000L)
//        onView(withId(R.id.filters))
//            .check(
//                ViewAssertions.matches(isDisplayed())
//            )
//        Thread.sleep(5000L)
//        onView(withId(R.id.filters)).perform(click())
//
////        Thread.sleep(1000L)
////        onView(withId(R.id.search)).perform(ViewActions.typeText("Управление"))
//
//        hideKeyboard()
//
//        Thread.sleep(5000L)
//        onView(withId(R.id.list))
//            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(
//                    0, click()
//                )
//            )
//
//        onView(withId(R.id.apply)).perform(click())
//
//    }

    private fun hideKeyboard() {
        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard())
    }


    @Test
    fun newsTest() {
        Thread.sleep(5000L)
        onView(withId(R.id.list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0, click()
                )
            )
        hideKeyboard()

        onView(withId(R.id.list))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(5))
    }

}