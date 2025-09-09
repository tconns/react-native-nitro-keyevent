import type { HybridObject } from 'react-native-nitro-modules'

export interface KeyEventData{
  keyCode: number
  action: number
  pressedKey: string
  repeatCount?: number | null
}

export interface NitroKeyEvent
  extends HybridObject<{ ios: 'swift'; android: 'kotlin' }> {
  onKeyDownListener(callback: (keyEvent: KeyEventData) => void): void
  onKeyUpListener(callback: (keyEvent: KeyEventData) => void): void
}
