package com.far.bar.bean

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep
import lombok.Data
import java.util.*

@Data
@Keep
class DeviceInfos() : Parcelable {
    // (value = "设备编号")
    var device_code: String? = null
       

    // (value = "设备名")
    var device_name: String? = null
       

    // (value = "设备类型(0无线密取/1终端密取/2人工秘取)")
    var device_type: Int? = null
       

    // (value = "是否分配(0未分配/1分配)")
    var is_allot: Int? = null
       

    // (value = "是否在线(0离线/1在线)")
    var is_online: Int? = null
       

    // (value = "目标人数")
    var target_count: Int? = null
       

    // (value = "上次更改在线离线状态时间")
    var gmt_modified: String? = null
       

    /**
     * 游离字段，设备下绑定的目标
     */
    var targetInfos: ArrayList<TargetInfos>? = null

    constructor(parcel: Parcel) : this() {
        device_code = parcel.readString()
        device_name = parcel.readString()
        device_type = parcel.readValue(Int::class.java.classLoader) as? Int
        is_allot = parcel.readValue(Int::class.java.classLoader) as? Int
        is_online = parcel.readValue(Int::class.java.classLoader) as? Int
        target_count = parcel.readValue(Int::class.java.classLoader) as? Int
        gmt_modified = parcel.readString()
    }


    override fun toString(): String {
        return "DeviceInfo(device_code=$device_code, device_name=$device_name, device_type=$device_type, is_allot=$is_allot, is_online=$is_online, target_count=$target_count, gmt_modified=$gmt_modified, targetInfos=$targetInfos)"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(device_code)
        parcel.writeString(device_name)
        parcel.writeValue(device_type)
        parcel.writeValue(is_allot)
        parcel.writeValue(is_online)
        parcel.writeValue(target_count)
        parcel.writeString(gmt_modified)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DeviceInfos> {
        override fun createFromParcel(parcel: Parcel): DeviceInfos {
            return DeviceInfos(parcel)
        }

        override fun newArray(size: Int): Array<DeviceInfos?> {
            return arrayOfNulls(size)
        }
    }


}