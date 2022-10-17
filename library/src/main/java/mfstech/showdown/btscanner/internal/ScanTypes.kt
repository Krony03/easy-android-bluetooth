package mfstech.showdown.btscanner.internal

import android.bluetooth.le.ScanResult
import mfstech.showdown.btscanner.BluetoothScanException
import mfstech.showdown.btscanner.ScanCallbackType

internal typealias OnScanResult = (callbackType: ScanCallbackType, result: ScanResult) -> Unit
internal typealias OnBatchScanResult = (results: List<ScanResult>) -> Unit
internal typealias OnScanFailed = (exception: BluetoothScanException) -> Unit