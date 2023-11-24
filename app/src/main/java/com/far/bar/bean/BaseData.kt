package com.far.bar.bean

import androidx.annotation.Keep
import lombok.Data
import java.util.concurrent.CopyOnWriteArrayList

@Data
@Keep
class BaseData<T> {
    var pageIndex: Int = 0
    var pageSize: Int = 0
    var totalPage: Int = 0
    var totalCount: Int = 0
    var rows: CopyOnWriteArrayList<T>? = null
    override fun toString(): String {
        return "BaseData(pageIndex=$pageIndex, pageSize=$pageSize, totalPage=$totalPage, totalCount=$totalCount, rows=$rows)"
    }


}