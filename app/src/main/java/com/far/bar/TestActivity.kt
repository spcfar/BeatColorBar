package com.far.bar

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.far.bar.databinding.BindingAcTest
import com.far.bar.test.PinpuBarCallBack


class TestActivity : Activity(), View.OnClickListener, PinpuBarCallBack {
    private lateinit var bindingAcTest: BindingAcTest
    private lateinit var mHandler: Handler
    private lateinit var mContext: Context

    var freqStart = 1.0
    var freqEnd = 6000.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingAcTest = DataBindingUtil.setContentView(this, R.layout.activity_test)
        mContext = this
        initHandler()
        bindingAcTest.barDefault.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                bindingAcTest.barTest.clearDataMax()
            }

        })
        mHandler.sendEmptyMessageDelayed(0, 1000)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    @SuppressLint("HandlerLeak")
    private fun initHandler() {
        mHandler = object : Handler() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            override fun handleMessage(msg: Message) {
                when (msg.what) {
                    0 -> {
                        var frqMStart: Long = if (freqStart == 1.0) 0 else freqStart.toLong()
                        var frqMEnd: Long = freqEnd.toLong()
                        var perFrq: Long = (frqMEnd - frqMStart) / 10
                        var frq_0 = frqMStart
                        var frq_1 = frqMStart + perFrq
                        var frq_2 = frqMStart + perFrq * 2
                        var frq_3 = frqMStart + perFrq * 3
                        var frq_4 = frqMStart + perFrq * 4
                        var frq_5 = frqMStart + perFrq * 5
                        var frq_6 = frqMStart + perFrq * 6
                        var frq_7 = frqMStart + perFrq * 7
                        var frq_8 = frqMStart + perFrq * 8
                        var frq_9 = frqMStart + perFrq * 9

                        var map = java.util.LinkedHashMap<String, Int?>()
                        map.put(frq_0.toString() + "-" + frq_1.toString(), (Math.random() * 100).toInt())
                        map.put(frq_1.toString() + "-" + frq_2.toString(), (Math.random() * 100).toInt())
                        map.put(frq_2.toString() + "-" + frq_3.toString(), (Math.random() * 100).toInt())
                        map.put(frq_3.toString() + "-" + frq_4.toString(), (Math.random() * 100).toInt())
                        map.put(frq_4.toString() + "-" + frq_5.toString(), (Math.random() * 100).toInt())
                        map.put(frq_5.toString() + "-" + frq_6.toString(), (Math.random() * 100).toInt())
                        map.put(frq_6.toString() + "-" + frq_7.toString(), (Math.random() * 100).toInt())
                        map.put(frq_7.toString() + "-" + frq_8.toString(), (Math.random() * 100).toInt())
                        map.put(frq_8.toString() + "-" + frq_9.toString(), (Math.random() * 100).toInt())
                        map.put(frq_9.toString() + "-" + frqMEnd.toString(), (Math.random() * 100).toInt())

                        bindingAcTest.barTest.setData(frqMStart.toInt(), frqMEnd.toInt(), map, this@TestActivity)
                        mHandler.sendEmptyMessageDelayed(0, 200)
                    }
                }
            }


        }
    }

    override fun clickBar(xName: String, value: Int) {
        if (xName != null && xName.contains("-")) {
            var split = xName.split("-")
            if (split.size == 2) {
                var start = split[0]
                var end = split[1]
                var replace1 = end.replace("M", "")
                freqStart = start.toDouble()
                freqEnd = replace1.toDouble()
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.bar_default -> {
                bindingAcTest.barTest.clearDataMax()
            }
        }
    }

}