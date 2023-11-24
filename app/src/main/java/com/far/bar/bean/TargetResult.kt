package com.far.bar.bean

import androidx.annotation.Keep
import lombok.Data
import java.util.*

@Data
@Keep
class TargetResult {
    var data: TargetInfos = TODO()
    var errorMsg: Objects? = null
    var message: String? = null
    var ret: Int? = null
    override fun toString(): String {
        return "BaseResult(data=$data, errorMsg=$errorMsg, message=$message, ret=$ret)"
    }


}