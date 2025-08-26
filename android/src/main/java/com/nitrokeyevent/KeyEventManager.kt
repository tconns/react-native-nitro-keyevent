package com.margelo.nitro.nitrokeyevent

import android.view.KeyEvent

class KeyEventManager private constructor() {
    companion object {
        private var instance: KeyEventActivity? = null

        fun getInstance(): KeyEventActivity {
            if (instance == null) {
                instance = KeyEventActivity()
            }
            return instance!!
        }
    }

    private var keyEventListeners: KeyEventListeners? = null

    fun registerListeners(listeners: KeyEventListeners) {
        keyEventListeners = listeners
    }

    fun onKeyDown(keyCode: Int, event: KeyEvent) {
        keyEventListeners?.onKeyDown(keyCode, event)
    }

    fun onKeyUp(keyCode: Int, event: KeyEvent) {
        keyEventListeners?.onKeyUp(keyCode, event)
    }
}