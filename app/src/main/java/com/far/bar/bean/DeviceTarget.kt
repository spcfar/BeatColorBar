package com.far.bar.bean

import androidx.annotation.Keep
import lombok.Data
import java.util.concurrent.CopyOnWriteArrayList

@Data
@Keep
class DeviceTarget {
    var device_code: String = ""
    var target_codes: CopyOnWriteArrayList<String>? = null

    constructor(device_code: String, target_codes: CopyOnWriteArrayList<String>?) {
        this.device_code = device_code
        this.target_codes = target_codes
    }
}