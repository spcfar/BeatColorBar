package com.far.bar.bean

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep

@Keep
class BeanAppChecked : Parcelable {
    var id: Long? = null
    var msg: Long = 0
    var phone: String? = null
    var cate: String? = null
    var appKey: String? = null
    var appName: String? = null
    var appIconUrl: String? = null
    var iCheck = 0

    protected constructor(`in`: Parcel) {
        id = if (`in`.readByte().toInt() == 0) {
            null
        } else {
            `in`.readLong()
        }
        msg = `in`.readLong()
        phone = `in`.readString()
        cate = `in`.readString()
        appKey = `in`.readString()
        appName = `in`.readString()
        appIconUrl = `in`.readString()
        iCheck = `in`.readInt()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        if (id == null) {
            dest.writeByte(0.toByte())
        } else {
            dest.writeByte(1.toByte())
            dest.writeLong(id!!)
        }
        dest.writeLong(msg)
        dest.writeString(phone)
        dest.writeString(cate)
        dest.writeString(appKey)
        dest.writeString(appName)
        dest.writeString(appIconUrl)
        dest.writeInt(iCheck)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun toString(): String {
        return "BeanAppChecked{" +
                "id=" + id +
                ", msg=" + msg +
                ", phone='" + phone + '\'' +
                ", cate='" + cate + '\'' +
                ", AppKey='" + appKey + '\'' +
                ", AppName='" + appName + '\'' +
                ", AppIconUrl='" + appIconUrl + '\'' +
                ", iCheck=" + iCheck +
                '}'
    }

    companion object CREATOR : Parcelable.Creator<BeanAppChecked> {
        override fun createFromParcel(parcel: Parcel): BeanAppChecked {
            return BeanAppChecked(parcel)
        }

        override fun newArray(size: Int): Array<BeanAppChecked?> {
            return arrayOfNulls(size)
        }
    }


}