package com.far.bar.bean

import androidx.annotation.Keep

@Keep
class ControlState {
    var state = 0

    constructor(state: Int) {
        this.state = state
    }
}