package com.far.bar.bean

import lombok.Data

@Data
class DlgPost {

    var code: Int = 0
    var cmd: String = ""

    constructor(code: Int, cmd: String) {
        this.code = code
        this.cmd = cmd
    }

    override fun toString(): String {
        return "DlgPost(code=$code, cmd='$cmd')"
    }


}