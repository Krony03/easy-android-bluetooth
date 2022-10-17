package mfstech.showdown.btscanner

import android.bluetooth.le.ScanCallback.SCAN_FAILED_INTERNAL_ERROR
import android.bluetooth.le.ScanCallback.SCAN_FAILED_FEATURE_UNSUPPORTED
import android.bluetooth.le.ScanCallback.SCAN_FAILED_ALREADY_STARTED
import android.bluetooth.le.ScanCallback.SCAN_FAILED_APPLICATION_REGISTRATION_FAILED

sealed class BluetoothScanException(override val message: String) : Exception(message) {
    internal companion object {
        fun fromCode(code: Int): BluetoothScanException {
            return when (code) {
                SCAN_FAILED_ALREADY_STARTED -> AlreadyStartedException()
                SCAN_FAILED_APPLICATION_REGISTRATION_FAILED -> ApplicationRegistrationException()
                SCAN_FAILED_FEATURE_UNSUPPORTED -> FeatureUnsupportedException()
                SCAN_FAILED_INTERNAL_ERROR -> InternalErrorException()
                else -> InternalErrorException()
            }
        }
    }
}
class AlreadyStartedException : BluetoothScanException("Scan already started")
class ApplicationRegistrationException : BluetoothScanException("Bluetooth Application registration failed")
class FeatureUnsupportedException : BluetoothScanException("Feature unsupported")
class InternalErrorException : BluetoothScanException("Internal error")