package com.katerinavp.currencies_screen_impl

import android.app.Activity
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.RecyclerView

class HideKeyboardTouchListener: RecyclerView.OnItemTouchListener{
    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        val imm = rv.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(rv.applicationWindowToken, 0)
        return false
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) { }
}