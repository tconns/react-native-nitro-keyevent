import { NitroModules } from 'react-native-nitro-modules'
import type {
  KeyEventData,
  NitroKeyEvent as NitroKeyEventSpec,
} from './specs/NitroKeyEvent.nitro'

export const NitroKeyEvent =
  NitroModules.createHybridObject<NitroKeyEventSpec>('NitroKeyEvent')

export const onKeyDownListener = (callback: (event: KeyEventData) => void) => {
  NitroKeyEvent.onKeyDownListener(callback)
}

export const onKeyUpListener = (callback: (event: KeyEventData) => void) => {
  NitroKeyEvent.onKeyUpListener(callback)
}

export const removeKeyDownListener = () => {
  NitroKeyEvent.onKeyDownListener(() => {})
}

export const removeKeyUpListener = () => {
  NitroKeyEvent.onKeyUpListener(() => {})
}

export type { KeyEventData }
