package com.far.bar.bean

import androidx.annotation.Keep

@Keep
class PinpuSet {
    var iSet = 0

    constructor()

    constructor(iSet: Int) {
        this.iSet = iSet
    }

    override fun toString(): String {
        return "PowerState(iSet=$iSet)"
    }


}