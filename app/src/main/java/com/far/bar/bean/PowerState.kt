package com.far.bar.bean

import androidx.annotation.Keep

@Keep
class PowerState {
    var iPower = 0

    constructor()

    constructor(iPower: Int) {
        this.iPower = iPower
    }

    override fun toString(): String {
        return "PowerState(iPower=$iPower)"
    }


}