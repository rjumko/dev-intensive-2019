package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    val inputManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val focusedView = this.currentFocus
    if (focusedView != null) {
        inputManager.hideSoftInputFromWindow(
            focusedView.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
}

fun Activity.isKeyboardOpen(): Boolean {


    val rect = Rect()
    val rootView = this.window.decorView
    rootView.getWindowVisibleDisplayFrame(rect)
    val visibleDisplayFrameHeight = rect.top
    val screenHeight = this.windowManager.defaultDisplay.height
    val diff = (screenHeight - visibleDisplayFrameHeight)
    return diff > 100

}

fun Activity.isKeyboardClosed(): Boolean {

    val rect = Rect()
    val rootView = this.window.decorView
    rootView.getWindowVisibleDisplayFrame(rect)
    val visibleDisplayFrameHeight = rect.top
    val screenHeight = this.windowManager.defaultDisplay.height
    val diff = (screenHeight - visibleDisplayFrameHeight)
    return diff < 100
}
