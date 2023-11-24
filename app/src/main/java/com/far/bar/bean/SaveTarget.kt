package com.far.bar.bean

import lombok.Data

@Data
class SaveTargetBean {
    private var target_name: String? = null
    private var phonenum: String? = null
    private var imsi: String? = null
    private var email: String? = null
    private var device_code: String? = null
    private var country_phone_code: String? = null
    private var country_name_chs: String? = null

    constructor(target_name: String?, phonenum: String?, imsi: String?, email: String?, device_code: String?) {
        this.target_name = target_name
        this.phonenum = phonenum
        this.imsi = imsi
        this.email = email
        this.device_code = device_code
        country_phone_code = "+86"
        country_name_chs = "中国"
    }
}