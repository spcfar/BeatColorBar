package com.far.bar.bean

import lombok.Data

@Data
class HackPost {
    var cmd: String = ""

    constructor(cmd: String) {
        this.cmd = cmd
    }

    constructor()


}