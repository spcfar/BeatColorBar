package com.far.bar.bean

import androidx.annotation.Keep

@Keep
class ViewState {
    var state = ""

    constructor(state: String) {
        this.state = state
    }

    constructor()
}