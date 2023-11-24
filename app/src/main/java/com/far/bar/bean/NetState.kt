package com.far.bar.bean

import androidx.annotation.Keep

@Keep
class NetState {
    var type = 0

    constructor(type: Int) {
        this.type = type
    }

    override fun toString(): String {
        return "NetState(type=$type)"
    }
}