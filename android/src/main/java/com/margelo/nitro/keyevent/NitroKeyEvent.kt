package com.margelo.nitro.keyevent

import com.facebook.proguard.annotations.DoNotStrip
import com.margelo.nitro.NitroModules
import android.view.KeyEvent

@DoNotStrip
class NitroKeyEvent : HybridNitroKeyEventSpec(), KeyEventListeners {
  private val reactContext = NitroModules.applicationContext ?: throw Exception("Context is null")

    private var keyDownCallback: ((KeyEventData) -> Unit)? = null
    private var keyUpCallback: ((KeyEventData) -> Unit)? = null

    override fun sum(num1: Double, num2: Double): Double {
        return num1 + num2
    }

    override fun onKeyDownListener(callback: (KeyEventData) -> Unit) {
        keyDownCallback = callback
    }

    override fun onKeyUpListener(callback: (KeyEventData) -> Unit) {
        keyUpCallback = callback
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent) {
        val data = KeyEventData(
            keyCode = keyCode.toDouble(),
            action = event.getAction().toDouble(),
            pressedKey = event.getUnicodeChar().toChar().toString(),
            repeatCount = if (event.getRepeatCount() > 0) event.getRepeatCount().toDouble() else null
        )
        keyDownCallback?.invoke(data)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent) {
        val data = KeyEventData(
            keyCode = keyCode.toDouble(),
            action = event.getAction().toDouble(),
            pressedKey = event.getUnicodeChar().toChar().toString(),
            repeatCount = null
        )
        keyDownCallback?.invoke(data)
    }

    init {
        KeyEventManager.getInstance().registerListeners(this)
    }
}
