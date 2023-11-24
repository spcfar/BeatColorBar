package com.far.bar.bean

import lombok.Data

@Data
class SendBean {

    var typeName: String = ""
    var cmd: String = ""

    constructor(typeName: String, cmd: String) {
        this.typeName = typeName
        this.cmd = cmd
    }

    override fun toString(): String {
        return "SendBean(typeName='$typeName', cmd='$cmd')"
    }
}