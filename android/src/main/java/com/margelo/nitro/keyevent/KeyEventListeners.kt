package com.margelo.nitro.keyevent

import android.view.KeyEvent

interface KeyEventListeners {
    fun onKeyDown(keyCode: Int, event: KeyEvent)
    fun onKeyUp(keyCode: Int, event: KeyEvent)
}