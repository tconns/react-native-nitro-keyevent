import type { HybridObject } from 'react-native-nitro-modules'

export interface KeyEventData{
  keyCode: number
  action: number
  pressedKey: string
  repeatCount?: number | null
}

export interface NitroKeyEvent
  extends HybridObject<{ ios: 'swift'; android: 'kotlin' }> {
  sum(num1: number, num2: number): number
  onKeyDownListener(callback: (keyEvent: KeyEventData) => void): void
  onKeyUpListener(callback: (keyEvent: KeyEventData) => void): void
}
