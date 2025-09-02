# react-native-nitro-keyevent

A high-performance React Native module built with Nitro Modules for capturing external keyboard keys and hardware button events. This library provides native-level performance for hardware key event handling with modern React Native architecture.

[![Version](https://img.shields.io/npm/v/react-native-nitro-keyevent.svg)](https://www.npmjs.com/package/react-native-nitro-keyevent)
[![Downloads](https://img.shields.io/npm/dm/react-native-nitro-keyevent.svg)](https://www.npmjs.com/package/react-native-nitro-keyevent)
[![License](https://img.shields.io/npm/l/react-native-nitro-keyevent.svg)](https://github.com/tconns/react-native-nitro-keyevent/LICENSE)

## Features

- **High Performance**: Built with Nitro Modules for native-level performance
- **Key Event Capture**: Capture external keyboard keys and hardware button events
- **Real-time Monitoring**: Listen to keyDown and keyUp events in real-time
- **Key Information**: Get detailed key information including keyCode, action, and pressed key
- **Repeat Count**: Track key repeat events for long press detection
- **Modern Architecture**: Uses Nitro Modules for better performance than bridge-based solutions
- **Cross Platform**: Works on both Android and iOS

## Requirements

- React Native v0.76.0 or higher
- Node 18.0.0 or higher

> [!IMPORTANT]  
> This library uses Nitro Modules. Make sure to install `react-native-nitro-modules` as a dependency.
>
> For Android, you need to manually implement the key event handlers in your MainActivity to capture hardware key events.

## Installation

```sh
npm install react-native-nitro-keyevent react-native-nitro-modules
# or
yarn add react-native-nitro-keyevent react-native-nitro-modules
```

## Android Configuration

### Required: MainActivity Setup

To capture hardware key events on Android, you must override the key event methods in your `MainActivity.java` or `MainActivity.kt`:

#### For Java (MainActivity.java)

```java
import android.view.KeyEvent;
import com.margelo.nitro.keyevent.KeyEventManager;

public class MainActivity extends ReactActivity {

  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    // Forward key events to the KeyEvent module
    KeyEventManager.getInstance().onKeyDown(keyCode, event);

    // Option 1: Override default behavior (recommended for external keyboards)
    super.onKeyDown(keyCode, event);
    return true;

    // Option 2: Keep default behavior (uncomment line below, comment lines above)
    // return super.onKeyDown(keyCode, event);
  }

  @Override
  public boolean onKeyUp(int keyCode, KeyEvent event) {
    // Forward key events to the KeyEvent module
    KeyEventManager.getInstance().onKeyUp(keyCode, event);

    // Option 1: Override default behavior (recommended for external keyboards)
    super.onKeyUp(keyCode, event);
    return true;

    // Option 2: Keep default behavior (uncomment line below, comment lines above)
    // return super.onKeyUp(keyCode, event);
  }
}
```

#### For Kotlin (MainActivity.kt)

```kotlin
import android.view.KeyEvent
import com.margelo.nitro.keyevent.KeyEventManager

class MainActivity : ReactActivity() {

  override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
    // Forward key events to the KeyEvent module
    KeyEventManager.getInstance().onKeyDown(keyCode, event)

    // Option 1: Override default behavior (recommended for external keyboards)
    super.onKeyDown(keyCode, event)
    return true

    // Option 2: Keep default behavior (uncomment line below, comment lines above)
    // return super.onKeyDown(keyCode, event)
  }

  override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
    // Forward key events to the KeyEvent module
    KeyEventManager.getInstance().onKeyUp(keyCode, event)

    // Option 1: Override default behavior (recommended for external keyboards)
    super.onKeyUp(keyCode, event)
    return true

    // Option 2: Keep default behavior (uncomment line below, comment lines above)
    // return super.onKeyUp(keyCode, event)
  }
}
```

### Preventing Multiple Events on Long Press

If you want to prevent multiple events when a key is held down, modify the `onKeyDown` method:

```java
@Override
public boolean onKeyDown(int keyCode, KeyEvent event) {
  // Only forward the first event, ignore repeats
  if (event.getRepeatCount() == 0) {
    KeyEventManager.getInstance().onKeyDown(keyCode, event);
  }

  super.onKeyDown(keyCode, event);
  return true;
}
```

## iOS Configuration

iOS support is currently in development. The module structure is ready for iOS implementation.

## Usage

```typescript
import { 
  onKeyDownListener, 
  onKeyUpListener, 
  removeKeyDownListener, 
  removeKeyUpListener,
  type KeyEventData 
} from 'react-native-nitro-keyevent';

const MyComponent = () => {
  useEffect(() => {
    // Listen for key down events
    onKeyDownListener((keyEvent: KeyEventData) => {
      console.log('Key Down:', {
        keyCode: keyEvent.keyCode,
        action: keyEvent.action,
        pressedKey: keyEvent.pressedKey,
        repeatCount: keyEvent.repeatcount || 0
      });
    });

    // Listen for key up events
    onKeyUpListener((keyEvent: KeyEventData) => {
      console.log('Key Up:', {
        keyCode: keyEvent.keyCode,
        action: keyEvent.action,
        pressedKey: keyEvent.pressedKey
      });
    });

    // Cleanup listeners on unmount
    return () => {
      removeKeyDownListener();
      removeKeyUpListener();
    };
  }, []);

  return (
    <View>
      <Text>Press any hardware key to see events in console</Text>
    </View>
  );
};
```

## API Reference

### Functions

The library exports simple functions for handling keyboard and hardware key events.

#### `onKeyDownListener(callback: (event: KeyEventData) => void): void`

Registers a listener for key down events. This is triggered when a hardware key is pressed down.

**Parameters:**

- `callback: (event: KeyEventData) => void` - Function to call when a key down event occurs

**Example:**

```typescript
import { onKeyDownListener } from 'react-native-nitro-keyevent';

onKeyDownListener((keyEvent) => {
  console.log('Key pressed:', keyEvent.pressedKey)
  console.log('Key code:', keyEvent.keyCode)
  console.log('Repeat count:', keyEvent.repeatcount)
})
```

#### `onKeyUpListener(callback: (event: KeyEventData) => void): void`

Registers a listener for key up events. This is triggered when a hardware key is released.

**Parameters:**

- `callback: (event: KeyEventData) => void` - Function to call when a key up event occurs

**Example:**

```typescript
import { onKeyUpListener } from 'react-native-nitro-keyevent';

onKeyUpListener((keyEvent) => {
  console.log('Key released:', keyEvent.pressedKey)
  console.log('Key code:', keyEvent.keyCode)
})
```

#### `removeKeyDownListener(): void`

Removes the current key down event listener.

**Example:**

```typescript
import { removeKeyDownListener } from 'react-native-nitro-keyevent';

removeKeyDownListener()
```

#### `removeKeyUpListener(): void`

Removes the current key up event listener.

**Example:**

```typescript
import { removeKeyUpListener } from 'react-native-nitro-keyevent';

removeKeyUpListener()
```

### KeyEventData Interface

The data structure passed to event listeners containing key information:

```typescript
interface KeyEventData {
  keyCode: number // The hardware key code
  action: number // The key action (down/up)
  pressedKey: string // The character representation of the key
  repeatcount?: number // Number of times key has repeated (only for keyDown)
  characters?: string // Multiple characters (for special cases)
}
```

### Common Key Codes (Android)

| Key         | Key Code | Description          |
| ----------- | -------- | -------------------- |
| Volume Up   | 24       | Volume up button     |
| Volume Down | 25       | Volume down button   |
| Power       | 26       | Power button         |
| Back        | 4        | Back button          |
| Home        | 3        | Home button          |
| Menu        | 82       | Menu button          |
| Space       | 62       | Space bar            |
| Enter       | 66       | Enter/Return key     |
| Del         | 67       | Delete/Backspace key |

For a complete list of Android key codes, see [Android KeyEvent Documentation](https://developer.android.com/reference/android/view/KeyEvent.html).

## Usage Examples

### Volume Button Control

```typescript
import { onKeyDownListener, removeKeyDownListener } from 'react-native-nitro-keyevent';

const VolumeController = () => {
  useEffect(() => {
    onKeyDownListener((keyEvent) => {
      switch (keyEvent.keyCode) {
        case 24: // Volume Up
          console.log('Volume Up pressed');
          // Custom volume up handling
          break;
        case 25: // Volume Down
          console.log('Volume Down pressed');
          // Custom volume down handling
          break;
      }
    });

    return () => {
      removeKeyDownListener();
    };
  }, []);

  return (
    <View>
      <Text>Use volume buttons to control app</Text>
    </View>
  );
};
```

### External Keyboard Support

```typescript
import { 
  onKeyDownListener, 
  onKeyUpListener, 
  removeKeyDownListener, 
  removeKeyUpListener 
} from 'react-native-nitro-keyevent';

const ExternalKeyboardHandler = () => {
  const [pressedKeys, setPressedKeys] = useState<string[]>([]);

  useEffect(() => {
    onKeyDownListener((keyEvent) => {
      // Handle arrow keys for navigation
      switch (keyEvent.keyCode) {
        case 19: // DPAD_UP
          console.log('Navigate up');
          break;
        case 20: // DPAD_DOWN
          console.log('Navigate down');
          break;
        case 21: // DPAD_LEFT
          console.log('Navigate left');
          break;
        case 22: // DPAD_RIGHT
          console.log('Navigate right');
          break;
        case 66: // ENTER
          console.log('Select/Enter pressed');
          break;
        default:
          // Add pressed key to list
          setPressedKeys(prev => [...prev, keyEvent.pressedKey]);
          break;
      }
    });

    onKeyUpListener((keyEvent) => {
      // Remove key from pressed keys list
      setPressedKeys(prev =>
        prev.filter(key => key !== keyEvent.pressedKey)
      );
    });

    return () => {
      removeKeyDownListener();
      removeKeyUpListener();
    };
  }, []);

  return (
    <View>
      <Text>Pressed Keys: {pressedKeys.join(', ')}</Text>
      <Text>Use external keyboard for navigation</Text>
    </View>
  );
};
```

### Gaming Controls

```typescript
import { 
  onKeyDownListener, 
  onKeyUpListener, 
  removeKeyDownListener, 
  removeKeyUpListener 
} from 'react-native-nitro-keyevent';

const GameController = () => {
  const [gameState, setGameState] = useState({
    isMoving: false,
    direction: null,
    isJumping: false
  });

  useEffect(() => {
    onKeyDownListener((keyEvent) => {
      // Prevent multiple events on key repeat for jump action
      if (keyEvent.repeatcount && keyEvent.repeatcount > 0) {
        return; // Ignore repeated events
      }

      switch (keyEvent.keyCode) {
        case 62: // SPACE - Jump
          setGameState(prev => ({ ...prev, isJumping: true }));
          setTimeout(() => {
            setGameState(prev => ({ ...prev, isJumping: false }));
          }, 500);
          break;
        case 19: // DPAD_UP
          setGameState(prev => ({
            ...prev,
            isMoving: true,
            direction: 'up'
          }));
          break;
        case 20: // DPAD_DOWN
          setGameState(prev => ({
            ...prev,
            isMoving: true,
            direction: 'down'
          }));
          break;
        case 21: // DPAD_LEFT
          setGameState(prev => ({
            ...prev,
            isMoving: true,
            direction: 'left'
          }));
          break;
        case 22: // DPAD_RIGHT
          setGameState(prev => ({
            ...prev,
            isMoving: true,
            direction: 'right'
          }));
          break;
      }
    });

    onKeyUpListener((keyEvent) => {
      // Stop movement when key is released
      switch (keyEvent.keyCode) {
        case 19: // DPAD_UP
        case 20: // DPAD_DOWN
        case 21: // DPAD_LEFT
        case 22: // DPAD_RIGHT
          setGameState(prev => ({
            ...prev,
            isMoving: false,
            direction: null
          }));
          break;
      }
    });

    return () => {
      removeKeyDownListener();
      removeKeyUpListener();
    };
  }, []);

  return (
    <View style={styles.gameContainer}>
      <Text>Game State:</Text>
      <Text>Moving: {gameState.isMoving ? 'Yes' : 'No'}</Text>
      <Text>Direction: {gameState.direction || 'None'}</Text>
      <Text>Jumping: {gameState.isJumping ? 'Yes' : 'No'}</Text>
    </View>
  );
};
```

## Hardware Key Events

This library captures physical hardware key events from:

- **External Keyboards**: USB, Bluetooth keyboards
- **Hardware Buttons**: Volume buttons, power button, back button
- **Gaming Controllers**: Bluetooth game controllers with D-pad
- **Remote Controls**: TV remote controls and media devices
- **Custom Hardware**: Any device that sends Android KeyEvent

### Event Flow

1. **Hardware Key Press** → Android KeyEvent generated
2. **MainActivity Handler** → Forwards to KeyEventManager
3. **KeyEventManager** → Notifies registered listeners
4. **NitroKeyEvent Module** → Processes and formats event data
5. **React Native Bridge** → Sends event to JavaScript
6. **KeyEvent Listeners** → Your callback functions receive the event

## Platform Support

### Android

- ✅ Full support for all hardware key events
- ✅ Key repeat detection
- ✅ Custom key event processing

### iOS

- 🚧 In development
- 🚧 External keyboard support planned
- 🚧 Hardware button support planned

## Troubleshooting

### Android Events Not Received

1. **Check MainActivity Setup**: Ensure you've added the key event handlers
2. **Verify Import**: Make sure `KeyEventManager` is imported correctly
3. **Test with External Keyboard**: Some built-in keys may be handled by the system
4. **Check Logs**: Enable React Native debugging to see if events are being generated

### Performance Considerations

- **Remove Listeners**: Always remove listeners in cleanup to prevent memory leaks
- **Debounce Events**: For high-frequency events, consider debouncing in your handlers
- **Key Repeat**: Handle `repeatcount` appropriately for long-press scenarios

## Contributing

When changing spec files (`*.nitro.ts`), please re-run nitrogen:

```sh
npx nitro-codegen
```

### Development Setup

1. Clone the repository
2. Install dependencies: `npm install`
3. Set up the example project
4. Run codegen after spec changes: `npx nitro-codegen`

### Adding New Features

When adding new key event features:

1. Update the `NitroKeyEvent.nitro.ts` spec file
2. Run `npx nitro-codegen` to generate native interfaces
3. Implement the native Android code in `NitroKeyEvent.kt`
4. Update the `KeyEvent` class in TypeScript
5. Add tests and update documentation

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## Project Structure

- [`android/`](android/): Android native implementation (Kotlin, C++)
  - [`NitroKeyEvent.kt`](android/src/main/java/com/margelo/nitro/keyevent/NitroKeyEvent.kt): Main Android implementation
  - [`KeyEventManager.kt`](android/src/main/java/com/margelo/nitro/keyevent/KeyEventManager.kt): Key event manager singleton
  - [`KeyEventListeners.kt`](android/src/main/java/com/margelo/nitro/keyevent/KeyEventListeners.kt): Event listener interface
- [`ios/`](ios/): iOS native implementation (Swift, C++) - In development
- [`nitrogen/`](nitrogen/): Auto-generated files by Nitro Modules
- [`src/`](src/): TypeScript source code and API definitions
  - [`index.ts`](src/index.ts): Main export file with KeyEvent class
  - [`specs/NitroKeyEvent.nitro.ts`](src/specs/NitroKeyEvent.nitro.ts): Nitro module specification
- [`nitro.json`](nitro.json): Nitro Module configuration
- [`package.json`](package.json): Package metadata and dependencies

## Acknowledgements

Special thanks to the following open-source projects which inspired and supported the development of this library:

- [mrousavy/nitro](https://github.com/mrousavy/nitro) – for the Nitro Modules architecture and tooling
- [kevinejohn/react-native-keyevent](https://github.com/kevinejohn/react-native-keyevent) – for the original idea of handling hardware key events in React Native

## License

MIT © [Thành Công](https://github.com/tconns)
