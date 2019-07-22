package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    val inputManager = this?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val focusedView = this?.currentFocus
    if (focusedView != null) {
        inputManager.hideSoftInputFromWindow(focusedView.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS)
    }
}