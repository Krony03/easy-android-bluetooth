package mfstech.showdown.btscanner

import android.bluetooth.le.ScanFilter
import android.bluetooth.le.ScanResult
import android.bluetooth.le.ScanSettings
import android.content.Context
import mfstech.showdown.btscanner.internal.OnBatchScanResult
import mfstech.showdown.btscanner.internal.OnScanFailed
import mfstech.showdown.btscanner.internal.OnScanResult
import android.bluetooth.le.ScanCallback as AndroidScanCallback

class ScanCallback private constructor(
    private val onScanResult: OnScanResult?,
    private val onBatchScanResult: OnBatchScanResult?,
    private val onScanFailed: OnScanFailed?
) : AndroidScanCallback() {

    private constructor(builder: Builder) : this(
        builder.onScanResult,
        builder.onBatchScanResult,
        builder.onScanFailed
    )

    override fun onScanResult(callbackType: Int, result: ScanResult) {
        super.onScanResult(callbackType, result)
        this.onScanResult?.invoke(ScanCallbackType.fromCode(callbackType), result)
    }

    override fun onBatchScanResults(results: MutableList<ScanResult>) {
        super.onBatchScanResults(results)
        this.onBatchScanResult?.invoke(results)
    }

    override fun onScanFailed(errorCode: Int) {
        super.onScanFailed(errorCode)
        this.onScanFailed?.invoke(BluetoothScanException.fromCode(errorCode))
    }

    class Builder {
        var onScanResult: OnScanResult? = null
        var onBatchScanResult: OnBatchScanResult? = null
        var onScanFailed: OnScanFailed? = null

        fun setOnScanResult(onScanResult: OnScanResult) = apply {
            this.onScanResult = onScanResult
        }

        fun setOnBatchScanResult(onBatchScanResult: OnBatchScanResult) = apply {
            this.onBatchScanResult = onBatchScanResult
        }

        fun setOnScanFailed(onScanFailed: OnScanFailed) = apply {
            this.onScanFailed = onScanFailed
        }

        fun build(): ScanCallback = ScanCallback(this)
    }

    companion object {
        fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }
}

fun test(context: Context) {
    val scanCallback = ScanCallback.Builder()
        .setOnScanFailed {  }
        .setOnBatchScanResult {  }
        .setOnScanResult { _, _ ->  }
        .build()

    EasyBluetoothScanner.Builder(context, scanCallback)
        .setScanFilters(emptyList())
        .setScanSettings(ScanSettings.Builder().build())
        .build()
}