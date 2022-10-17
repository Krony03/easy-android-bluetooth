# Easy Bluetooth Scanner for Android

## Implement
1. Add `jitpack` to your project's `build.gradle` file:
    ```groovy
    allprojects {
        repositories {           
            maven { url 'https://jitpack.io' }
        }
    }
    ```
2. Add the dependency to your module's `build.gradle` file:
    ```groovy
    dependencies {
        implementation 'com.github.Krony03:easy-android-btscanner:<relase-name>'
    }
    ```

## Examples

### Default
```
val scanCallback = ScanCallback.Builder()
        .setOnScanFailed {  }
        .setOnBatchScanResult {  }
        .setOnScanResult { _, _ ->  }
        .build()

    EasyBluetoothScanner.Builder(context, scanCallback)
        .setScanFilters(emptyList())
        .setScanSettings(ScanSettings.Builder().build())
        .build()
```

### First-Class functions
```
  EasyBluetoothScanner.build(
    context = context,
    scanCallback = ScanCallback.build {
      setOnScanResult { callbackType, result ->
        // do something
      }
      setOnBatchScanResult { results ->
        // do something
      }
      setOnScanFailed { exception ->
        // do something
      }
    }
  ) {
    // Native Scan Filters are already prepared with builder
    scanFilters = listOf<ScanFilter>()

    // Native Scan Settings is already prepared with builder
    scanSettings = ScanSettings.Builder().build()
  }
```