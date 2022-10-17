package mfstech.showdown.btscanner

import android.annotation.SuppressLint
import android.bluetooth.BluetoothManager
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanSettings
import android.content.Context

@SuppressLint("MissingPermission")
class EasyBluetoothScanner(
    private val context: Context,
    private val scanSettings: ScanSettings,
    private val scanCallback: ScanCallback,
    private val scanFilters: List<ScanFilter>
) {

    private val scanner: BluetoothLeScanner by lazy {
        val manager = (context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager)
        manager.adapter.bluetoothLeScanner
    }

    fun start() {
        scanner.startScan(scanFilters, scanSettings, scanCallback)
    }

    fun stop() {
        scanner.stopScan(scanCallback)
    }

    class Builder(
        private val context: Context,
        private val scanCallback: ScanCallback
    ) {
        var scanSettings: ScanSettings = ScanSettings.Builder().build()
        var scanFilters: List<ScanFilter> = emptyList()

        fun setScanSettings(scanSettings: ScanSettings): Builder {
            this.scanSettings = scanSettings
            return this
        }

        fun setScanFilters(scanFilters: List<ScanFilter>): Builder {
            this.scanFilters = scanFilters
            return this
        }

        fun build(): EasyBluetoothScanner {
            return EasyBluetoothScanner(context, scanSettings, scanCallback, scanFilters)
        }
    }

    companion object {
        fun build(
            context: Context,
            scanCallback: ScanCallback,
            block: Builder.() -> Unit
        ) = Builder(context, scanCallback).apply { block() }.build()
    }
}