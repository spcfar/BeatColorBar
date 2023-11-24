package com.far.bar.bean

import androidx.annotation.Keep

@Keep
class TimeState {
    var iTime = 0L

    constructor()

    constructor(time: Long) {
        this.iTime = time
    }

    override fun toString(): String {
        return "TimeState(iTime=$iTime)"
    }


}