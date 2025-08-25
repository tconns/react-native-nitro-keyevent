package com.nitrokeyevent

import android.view.KeyEvent

interface KeyEventListeners {
    fun onKeyDown(keyCode: Int, event: KeyEvent)
    fun onKeyUp(keyCode: Int, event: KeyEvent)
}