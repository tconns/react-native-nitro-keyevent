import { NitroModules } from 'react-native-nitro-modules'
import type { NitroKeyEvent as NitroKeyEventSpec } from './specs/nitro-key-event.nitro'

export const NitroKeyEvent =
  NitroModules.createHybridObject<NitroKeyEventSpec>('NitroKeyEvent')