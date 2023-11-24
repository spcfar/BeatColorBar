package com.zrt.defense.test

class Bar {

    var value: Int = 0
    var left: Float = 0f
    var top: Float = 0f
    var right: Float = 0f
    var bootom: Float = 0f
    var max: Int = 0

    constructor(value: Int, left: Float, top: Float, right: Float, bootom: Float, max: Int) {
        this.value = value
        this.left = left
        this.top = top
        this.right = right
        this.bootom = bootom
        this.max = max
    }

    constructor(value: Int, left: Float, top: Float, right: Float, bootom: Float) {
        this.value = value
        this.left = left
        this.top = top
        this.right = right
        this.bootom = bootom
    }

    override fun toString(): String {
        return "Bar(value=$value, left=$left, top=$top, right=$right, bootom=$bootom, max=$max)"
    }


}