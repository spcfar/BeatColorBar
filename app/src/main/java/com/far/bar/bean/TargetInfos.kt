package com.far.bar.bean

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep
import lombok.Data

@Data
@Keep
class TargetInfos() : Parcelable {
    /**
     * 案件id
     */
    var target_code: String? = null


    /**
     * 案件名称
     */
    var target_name: String? = null


    /**
     * 目标手机
     */
    var phonenum: String? = null


    // ("国家电话代码")
    var country_phone_code: String? = "+86"


    // ("国家名")
    var country_name_chs: String? = null


    /**
     * imsi信息
     */
    var imsi: String? = null


    /**
     * 目标邮箱
     */
    var email: String? = null


    /**
     * 附加字段
     */
    var extra: String? = null


    /**
     * 创建人id
     */
    var create_id: String? = null


    /**
     * 创建人名字
     */
    var name: String? = null


    /**
     * 目标在线状态
     */
    var target_status = 0


    /**
     * 是否已经检测过
     */
    var checked = 0


    /**
     * APP注册量
     */
    var task_count: Int = 0


    /**
     * APP数据量
     */
    var data_count: Int = 0


    /**
     * 已保活APP数量  离线APP数量
     */
    var keepalive_app_count: Int = 0


    /**
     * 已保活APP取得的数据量  离线APP数据量
     */
    var keepalive_data_count: Int = 0


    /**
     * 在线状态改变时间
     */
    var status_time: String? = null


    /**
     * 在线状态改变时间
     */
    var gmt_modified: String? = null

    constructor(parcel: Parcel) : this() {
        target_code = parcel.readString()
        target_name = parcel.readString()
        phonenum = parcel.readString()
        country_phone_code = parcel.readString()
        country_name_chs = parcel.readString()
        imsi = parcel.readString()
        email = parcel.readString()
        extra = parcel.readString()
        create_id = parcel.readString()
        name = parcel.readString()
        target_status = parcel.readInt()
        checked = parcel.readInt()
        task_count = parcel.readInt()
        data_count = parcel.readInt()
        keepalive_app_count = parcel.readInt()
        keepalive_data_count = parcel.readInt()
        status_time = parcel.readString()
        gmt_modified = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(target_code)
        parcel.writeString(target_name)
        parcel.writeString(phonenum)
        parcel.writeString(country_phone_code)
        parcel.writeString(country_name_chs)
        parcel.writeString(imsi)
        parcel.writeString(email)
        parcel.writeString(extra)
        parcel.writeString(create_id)
        parcel.writeString(name)
        parcel.writeInt(target_status)
        parcel.writeInt(checked)
        parcel.writeInt(task_count)
        parcel.writeInt(data_count)
        parcel.writeInt(keepalive_app_count)
        parcel.writeInt(keepalive_data_count)
        parcel.writeString(status_time)
        parcel.writeString(gmt_modified)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TargetInfos> {
        override fun createFromParcel(parcel: Parcel): TargetInfos {
            return TargetInfos(parcel)
        }

        override fun newArray(size: Int): Array<TargetInfos?> {
            return arrayOfNulls(size)
        }
    }


}