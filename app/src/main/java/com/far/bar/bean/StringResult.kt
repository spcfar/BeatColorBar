package com.far.bar.bean

import androidx.annotation.Keep
import lombok.Data
import java.util.*

@Data
@Keep
class StringResult {
    var data: String? = null
    var errorMsg: Objects? = null
    var message: String? = null
    var ret: Int? = null
    override fun toString(): String {
        return "BaseResult(data=$data, errorMsg=$errorMsg, message=$message, ret=$ret)"
    }


}