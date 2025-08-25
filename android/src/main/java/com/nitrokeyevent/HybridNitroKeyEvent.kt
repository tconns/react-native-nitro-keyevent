package com.nitrokeyevent

import android.view.KeyEvent
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import com.facebook.react.bridge.WritableNativeMap
import com.facebook.react.modules.core.DeviceEventManagerModule
import com.margelo.nitro.NitroModules
import com.margelo.nitro.nitrokeyevent.HybridNitroKeyEventSpec


class HybridNitroKeyEvent: HybridNitroKeyEventSpec(), KeyEventListeners {
    private val reactContext = NitroModules.applicationContext ?: throw Exception("Context is null")

    override fun sum(num1: Double, num2: Double): Double {
        return num1 + num2
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent) {
        sendEvent("onKeyDown", getJsEventParams(keyCode, event, event.repeatCount))
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent) {
        sendEvent("onKeyUp", getJsEventParams(keyCode, event, null))
    }

    init {
        KeyEventActivity.getInstance().registerListeners(this)
    }

    private fun getJsEventParams(keyCode: Int, keyEvent: KeyEvent, repeatCount: Int?): WritableMap {
        val params: WritableMap = WritableNativeMap()
        val action = keyEvent.getAction()
        val pressedKey = keyEvent.getUnicodeChar().toChar()

        if (keyEvent.getAction() == KeyEvent.ACTION_MULTIPLE && keyCode == KeyEvent.KEYCODE_UNKNOWN) {
            val chars = keyEvent.getCharacters()
            if (chars != null) {
                params.putString("characters", chars)
            }
        }

        if (repeatCount != null) {
            params.putInt("repeatcount", repeatCount)
        }

        params.putInt("keyCode", keyCode)
        params.putInt("action", action)
        params.putString("pressedKey", pressedKey.toString())

        return params
    }

    private fun sendEvent(event: String, params: WritableMap?) {
        if (!reactContext.hasActiveReactInstance()) return
//        val params = Arguments.createMap().apply { putString("orientation", orientation) }
        reactContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
            .emit(event, params)
    }
}
