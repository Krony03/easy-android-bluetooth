package mfstech.showdown.btscanner

import android.bluetooth.le.ScanSettings.CALLBACK_TYPE_FIRST_MATCH
import android.bluetooth.le.ScanSettings.CALLBACK_TYPE_ALL_MATCHES
import android.bluetooth.le.ScanSettings.CALLBACK_TYPE_MATCH_LOST

enum class ScanCallbackType {

    /**
     * A result callback is only triggered for the first advertisement packet received that matches
     * the filter criteria.
     */
    FIRST_MATCH,

    /**
     * Trigger a callback for every Bluetooth advertisement found that matches the filter criteria.
     * If no filter is active, all advertisement packets are reported.
     */
    ALL_MATCHES,

    /**
     * Receive a callback when advertisements are no longer received from a device that has been
     * previously reported by a first match callback.
     */
    MATCH_LOST;

    internal companion object {
        fun fromCode(code: Int): ScanCallbackType {
            return when (code) {
                CALLBACK_TYPE_FIRST_MATCH -> FIRST_MATCH
                CALLBACK_TYPE_ALL_MATCHES -> ALL_MATCHES
                CALLBACK_TYPE_MATCH_LOST -> MATCH_LOST
                else -> throw UnsupportedOperationException("Unknown ScanCallbackType code: $code")
            }
        }
    }
}