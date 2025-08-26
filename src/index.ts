import { NitroModules } from 'react-native-nitro-modules'
import type { NitroKeyEvent as NitroKeyEventSpec } from './specs/NitroKeyEvent.nitro'
import {
  DeviceEventEmitter,
  NativeEventEmitter,
  NativeModules,
  Platform,
} from 'react-native'

class KeyEventClass {
  listenerKeyDown: any
  listenerKeyUp: any

  onKeyDownListener(cb: { (event: any): void; (data: any): void }) {
    this.removeKeyDownListener()
    if (Platform.OS === 'ios') {
      let keyEvent = new NativeEventEmitter(NativeModules.RNKeyEvent)
      this.listenerKeyDown = keyEvent.addListener('onKeyDown', cb)
    } else {
      this.listenerKeyDown = DeviceEventEmitter.addListener('onKeyDown', cb)
    }
  }

  removeKeyDownListener() {
    if (this.listenerKeyDown) {
      this.listenerKeyDown.remove()
      this.listenerKeyDown = null
    }
  }

  onKeyUpListener(cb: { (event: any): void; (data: any): void }) {
    this.removeKeyUpListener()
    if (Platform.OS === 'ios') {
      let keyEvent = new NativeEventEmitter(NativeModules.RNKeyEvent)
      this.listenerKeyUp = keyEvent.addListener('onKeyUp', cb)
    } else {
      this.listenerKeyUp = DeviceEventEmitter.addListener('onKeyUp', cb)
    }
  }

  removeKeyUpListener() {
    if (this.listenerKeyUp) {
      this.listenerKeyUp.remove()
      this.listenerKeyUp = null
    }
  }
}

export const NitroKeyEvent =
  NitroModules.createHybridObject<NitroKeyEventSpec>('NitroKeyEvent')

export const KeyEvent = new KeyEventClass()
