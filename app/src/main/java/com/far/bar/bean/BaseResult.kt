package com.far.bar.bean

import androidx.annotation.Keep
import lombok.Data

@Data
@Keep
class BaseResult<T> {
    var data: BaseData<T>? = null
    var message: String? = null
    var ret: Int? = null
    override fun toString(): String {
        return "BaseResult(data=$data, message=$message, ret=$ret)"
    }


}