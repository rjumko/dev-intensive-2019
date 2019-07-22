package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.util.Log
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
    val (screenHeight, keyboardHeight) = this.screenKeyboardHeight()
    Log.d("M_Activity", "$screenHeight $keyboardHeight")
    return (keyboardHeight > screenHeight / 3)
}

fun Activity.isKeyboardClosed(): Boolean {
    val (screenHeight, keyboardHeight) = this.screenKeyboardHeight()
    Log.d("M_Activity", "$screenHeight $keyboardHeight")
    return (keyboardHeight < screenHeight / 3)
}

fun Activity.screenKeyboardHeight(): Pair<Int, Int> {
    val rect = Rect()
    val rootView = this.window.decorView
    rootView.getWindowVisibleDisplayFrame(rect)
    val screenHeight =rootView.height
    val keyboardHeight = screenHeight - (rect.bottom - rect.top)
    Log.d("M_Activity", "$screenHeight $keyboardHeight ${rect.bottom} ${rect.top}")
    return screenHeight to keyboardHeight
}
