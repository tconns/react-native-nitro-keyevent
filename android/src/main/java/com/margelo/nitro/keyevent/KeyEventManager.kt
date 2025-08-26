package com.margelo.nitro.keyevent

import android.view.KeyEvent

class KeyEventManager private constructor() {
    companion object {
        private var instance: KeyEventManager? = null

        fun getInstance(): KeyEventManager {
            if (instance == null) {
                instance = KeyEventManager()
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