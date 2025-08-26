# react-native-nitro-keyevent

A high-performance React Native module built with Nitro Modules for device metrics, DPI measurements, and screen calculations. This library provides native-level performance for device information retrieval and unit conversions.

[![Version](https://img.shields.io/npm/v/react-native-nitro-keyevent.svg)](https://www.npmjs.com/package/react-native-nitro-keyevent)
[![Downloads](https://img.shields.io/npm/dm/react-native-nitro-keyevent.svg)](https://www.npmjs.com/package/react-native-nitro-keyevent)
[![License](https://img.shields.io/npm/l/react-native-nitro-keyevent.svg)](https://github.com/tconns/react-native-nitro-keyevent/LICENSE)

## Features

- **High Performance**: Built with Nitro Modules for native-level performance
- **Device Detection**: Detect if device is a tablet or phone
- **DPI Measurements**: Get accurate device DPI (dots per inch)
- **Screen Metrics**: Calculate device screen size in inches
- **Navigation Bar Height**: Get system navigation bar height
- **Unit Conversion**: Convert centimeters to pixels with DPI accuracy
- **Cross Platform**: Works on both Android and iOS

## Requirements

- React Native v0.76.0 or higher
- Node 18.0.0 or higher
- Android API level 21 or higher (for Android)
- iOS 12.0 or higher (for iOS)

> [!IMPORTANT]  
> This library uses Nitro Modules. Make sure to install `react-native-nitro-modules` as a dependency.

## Installation

```sh
npm install react-native-nitro-keyevent react-native-nitro-modules
# or
yarn add react-native-nitro-keyevent react-native-nitro-modules
```

## Usage

```ts
import {
  getNavBarHeight,
  isTablet,
  deviceInch,
  getDpi,
  cmToPx
} from 'react-native-nitro-keyevent';

// Get navigation bar height
const navBarHeight = getNavBarHeight();
console.log('Navigation bar height:', navBarHeight);

// Check if device is a tablet
const tablet = isTablet();
console.log('Is tablet:', tablet);

// Get device screen size in inches
const screenInches = deviceInch();
console.log('Device screen size:', screenInches, 'inches');

// Get device DPI (dots per inch)
const dpi = getDpi();
console.log('Device DPI:', dpi);

// Convert centimeters to pixels
const pixelSize = cmToPx(2.5); // Convert 2.5cm to pixels
console.log('2.5cm in pixels:', pixelSize);
```

## API Reference

### `getNavBarHeight(): number`

Returns the height of the system navigation bar in pixels.

**Returns:** `number` - The navigation bar height in pixels

### `isTablet(): boolean`

Determines if the current device is a tablet based on screen size and density.

**Returns:** `boolean` - `true` if the device is a tablet, `false` if it's a phone

### `deviceInch(): number`

Calculates the diagonal screen size of the device in inches.

**Returns:** `number` - The screen size in inches (diagonal measurement)

### `getDpi(): number`

Gets the device's DPI (dots per inch) value, which represents the screen density.

**Returns:** `number` - The DPI value of the device screen

### `cmToPx(cm: number): number`

Converts centimeters to pixels using the device's actual DPI and pixel ratio.

**Parameters:**

- `cm: number` - The value in centimeters to convert

**Returns:** `number` - The equivalent value in pixels

**Example:**

```ts
// Convert various measurements
const oneInchInPx = cmToPx(2.54); // 1 inch = 2.54cm
const fiveCmInPx = cmToPx(5);     // 5 centimeters
const halfInchInPx = cmToPx(1.27); // 0.5 inch = 1.27cm
```

## Use Cases

### Responsive Design

```ts
import { isTablet, deviceInch } from 'react-native-nitro-keyevent';

const MyComponent = () => {
  const tablet = isTablet();
  const screenSize = deviceInch();
  
  return (
    <View style={[
      styles.container,
      tablet && styles.tabletContainer,
      screenSize > 10 && styles.largeScreenContainer
    ]}>
      {/* Your content */}
    </View>
  );
};
```

### Physical Measurements

```ts
import { cmToPx, getDpi } from 'react-native-nitro-keyevent';

// Create a button that's exactly 1cm wide regardless of device
const ButtonComponent = () => {
  const buttonWidth = cmToPx(1); // 1cm in pixels
  
  return (
    <TouchableOpacity style={[styles.button, { width: buttonWidth }]}>
      <Text>1cm Button</Text>
    </TouchableOpacity>
  );
};

// Display DPI information
const DeviceInfo = () => {
  const dpi = getDpi();
  const inches = deviceInch();
  
  return (
    <View>
      <Text>Device DPI: {dpi}</Text>
      <Text>Screen Size: {inches.toFixed(1)}" diagonal</Text>
      <Text>Device Type: {isTablet() ? 'Tablet' : 'Phone'}</Text>
    </View>
  );
};
```

### Layout Adjustments

```ts
import { getNavBarHeight, isTablet } from 'react-native-nitro-keyevent';

const FullScreenComponent = () => {
  const navBarHeight = getNavBarHeight();
  const tablet = isTablet();
  
  return (
    <View style={[
      styles.fullScreen,
      {
        paddingBottom: navBarHeight,
        paddingHorizontal: tablet ? 40 : 20, // More padding on tablets
      }
    ]}>
      {/* Your full-screen content */}
    </View>
  );
};
```

## DPI and Unit Conversion

This library uses the device's actual DPI to provide accurate physical measurements:

- **DPI (Dots Per Inch)**: The number of individual dots that can be placed in a line within the span of 1 inch
- **Physical Accuracy**: The `cmToPx` function accounts for device pixel ratio and actual DPI for precise conversions
- **Cross-Platform**: Works consistently across different devices and screen densities

### Understanding DPI Values

- **Low DPI (120-160)**: Older or budget devices
- **Medium DPI (160-240)**: Standard density screens  
- **High DPI (240-320)**: High-density screens
- **Extra High DPI (320-480)**: Very high-density screens
- **Ultra High DPI (480+)**: Premium devices with extremely sharp displays

## Platform Support

This library works on both Android and iOS platforms:

- **Android**: Uses Android's DisplayMetrics and Configuration APIs
- **iOS**: Uses UIScreen and UIDevice APIs for device detection
- **Consistent API**: Same function calls work across both platforms

## Contributing

When changing spec files (`*.nitro.ts`), please re-run nitrogen:

```sh
npx nitro-codegen
```

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## Project Structure

- [`android/`](android/): Android native implementation (Kotlin, C++)
- [`ios/`](ios/): iOS native implementation (Swift, C++)
- [`nitrogen/`](nitrogen/): Auto-generated files by Nitro Modules
- [`src/`](src/): TypeScript source code and API definitions
- [`nitro.json`](nitro.json): Nitro Module configuration
- [`package.json`](package.json): Package metadata and dependencies

## License

MIT © [Thành Công](https://github.com/tconns)
