package com.far.bar.bean

import androidx.annotation.Keep
import lombok.Data

@Data
@Keep
class TargetSelect {
    var target_code: String? = null
    var target_name: String? = null
    var target_phone: String? = null
    var country_phone_code: String? = "+86"
    var iSelect: Boolean = false

    constructor(target_code: String?, target_name: String?, target_phone: String?, iSelect: Boolean) {
        this.target_code = target_code
        this.target_name = target_name
        this.target_phone = target_phone
        this.iSelect = iSelect
    }

    constructor(target_code: String?, target_name: String?, target_phone: String?) {
        this.target_code = target_code
        this.target_name = target_name
        this.target_phone = target_phone
    }

    override fun toString(): String {
        return "TargetSelect(target_code=$target_code, target_name=$target_name, target_phone=$target_phone, iSelect=$iSelect)"
    }


}