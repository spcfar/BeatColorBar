package com.far.bar

import android.app.Application
import androidx.annotation.Keep


@Keep
class App : Application() {


    override fun onCreate() {
        super.onCreate()
    }

    companion object {

    }
}