package com.far.bar.test

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.Rect
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.zrt.defense.test.Bar
import com.far.bar.R
import java.util.concurrent.CopyOnWriteArrayList


class BarColorView : View {
    private var mPinpuBarCallBack: PinpuBarCallBack? = null
    private val bottomTextHeight = 60  //给底部文字留下高度
    private val topMaginHeight = 20
    private var textPaint: Paint? = null
    private var textMaxPaint: Paint? = null
    private var maxPaint: Paint? = null
    private var textNowPaint: Paint? = null
    private var linePaint: Paint? = null
    private var rectPaint: Paint? = null
    private var kuangPaint: Paint? = null
    private var _maxValue = 0
    private var _minValue = 0
    private var unitHeight = 0f
    private val ArrYName = intArrayOf(0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100)
    private val ArrXName = CopyOnWriteArrayList<String>()
    private val bars = CopyOnWriteArrayList<Bar>()
    private val ArrXData = CopyOnWriteArrayList<Int>()
    private val colors = intArrayOf(R.color.google_green, R.color.google_blue, R.color.google_yellow, R.color.google_red)
    private var unitWidth = 0f
    private var widthY: Float = 0f
    private var objBarMarginLeftRight = 10f   //柱状图整体左边距
    private val rectPadding = 12
    private var maxMargin = 40f
    private var xNameMargin = 40f
    private var textMargin = 60f
    private var leftMargin = 10f
    private var _marginTop = 10f
    private var _marginTopWater = 4
    private var _marginBottom = 10f
    private var _marginRight = 10f
    private var showValuePos = -1
    private var _width = 0
    private var _widthLast = 0
    private var _height = 0
    private var barHeight = 0f
    private var kuangBottom = 0f
    private var _heightTop = 0
    private var _heightBottom = 0
    private var _showBmp: Bitmap? = null
    private var _showCanvas: Canvas? = null
    private var _spectrumBmp: Bitmap? = null
    private var _spectrumCanvas: Canvas? = null
    private var _waterfallBmp: Bitmap? = null
    private var _waterfallCanvas: Canvas? = null
    private val _rainRow = 30 // 瀑布图显示的行数
    private var _waterfallPaint: Paint? = null // 瀑布图画笔
    private var _paint: Paint? = null
    private var _gradientColors: IntArray? = null
    private var _barColors: IntArray? = null
    var _dbDataArr: IntArray? = null
    var mStartM: Int = 0
    var mEndM: Int = 0

    fun setData(startM: Int, endM: Int, xMap: LinkedHashMap<String, Int?>, pinpuBarCallBack: PinpuBarCallBack?) {
        if (pinpuBarCallBack != null)
            mPinpuBarCallBack = pinpuBarCallBack
        if (xMap == null)
            return
        if (bars != null && bars.size > 0) {
            bars.clear()
        }
        if (startM != mStartM || endM != mEndM || _dbDataArr == null) {
            _dbDataArr = IntArray(ArrYName.size)
            mStartM = startM
            mEndM = endM
        }
        if (ArrXName != null && ArrXName.size > 0) {
            ArrXName.clear()
        }
        if (ArrXData != null && ArrXData.size > 0) {
            ArrXData.clear()
        }
        var index = 0
        for (key in xMap.keys) {
            var fl = xMap[key]
            ArrXName.add(key)
            ArrXData.add(fl!!)
            index++
        }
        _minValue = ArrYName[0]
        _maxValue = ArrYName[ArrYName.size - 1]
        invalidate()
    }

    fun executeDraw(blDraw: Boolean) {
        if (blDraw) {
            invalidate()
        }

        if (_spectrumBmp != null) {
            _showCanvas!!.drawBitmap(_spectrumBmp!!, 0f, 0f, _paint)
        }
        if (_waterfallBmp != null) {
            _showCanvas!!.drawBitmap(_waterfallBmp!!, 0f, (_height / 3 * 2).toFloat(), _paint)
        }
//        if (_showBmp != null) {
//            _showCanvas!!.drawBitmap(_showBmp!!, 20f, 0f, _paint)
//        }
//        invalidate()
    }

    constructor(context: Context?) : super(context) {
        initPaint()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initPaint()
    }

    private fun initPaint() {
        val font = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
        textPaint = Paint()
        textPaint!!.color = resources.getColor(R.color.white_s)
        textPaint!!.textSize = 26f
        textPaint!!.isAntiAlias = true
        textMaxPaint = Paint()
        textMaxPaint!!.color = resources.getColor(R.color.white)
        textMaxPaint!!.textSize = 38f
        textMaxPaint!!.isAntiAlias = true
        textMaxPaint!!.typeface = font
        maxPaint = Paint()
        maxPaint!!.color = resources.getColor(R.color.white)
        maxPaint!!.textSize = 26f
        maxPaint!!.isAntiAlias = true

        textNowPaint = Paint()
        textNowPaint!!.color = resources.getColor(R.color.white)
        textNowPaint!!.textSize = 38f
        textNowPaint!!.isAntiAlias = true
        textNowPaint!!.typeface = font
        linePaint = Paint()
        linePaint!!.color = resources.getColor(R.color.blue_s)
        linePaint!!.isAntiAlias = true
        linePaint!!.style = Paint.Style.FILL
        linePaint!!.strokeWidth = 2f
        rectPaint = Paint()
        rectPaint!!.isDither = true
        rectPaint!!.isAntiAlias = true
        rectPaint!!.style = Paint.Style.FILL
        _waterfallPaint = Paint()
        _waterfallPaint!!.color = Color.parseColor("#398f89")
        _paint = Paint()
        kuangPaint = Paint()
        kuangPaint!!.strokeWidth = 2f
        kuangPaint!!.style = Paint.Style.STROKE
        kuangPaint!!.color = resources.getColor(R.color.white_s)
        _gradientColors = intArrayOf(
//            Color.parseColor("#ffffff"),
            Color.parseColor("#f8545f"),  //                        Color.rgb(221, 105, 1),
            Color.parseColor("#ec837e"),  //                        Color.rgb(122, 231, 1),
            Color.parseColor("#ffa700"),
            Color.parseColor("#008744"),  //                        Color.parseColor("#398f89"),
            Color.parseColor("#0057e7"),  //                        Color.parseColor("#398f89"),
//            Color.parseColor("#398f89"),
//            Color.parseColor("#2d5b58"),  //                        Color.parseColor("#2e5451"),
//            Color.parseColor("#2e5451")
        )

        _barColors = intArrayOf(
            Color.parseColor("#1aff00"),
            Color.parseColor("#33ff00"),
            Color.parseColor("#4dff00"),
            Color.parseColor("#66ff00"),
            Color.parseColor("#80ff00"),
            Color.parseColor("#99ff00"),
            Color.parseColor("#b3ff00"),
            Color.parseColor("#ccff00"),
            Color.parseColor("#e6ff00"),
            Color.parseColor("#ffff00"),
            Color.parseColor("#ffe600"),
            Color.parseColor("#ffcc00"),
            Color.parseColor("#ffb300"),
            Color.parseColor("#ff9900"),
            Color.parseColor("#ff8000"),
            Color.parseColor("#ff6600"),
            Color.parseColor("#ff4c00"),
            Color.parseColor("#ff3300"),
            Color.parseColor("#ff1a00"),
            Color.parseColor("#ff0000")
        )
    }

    private fun drawBars(canvas: Canvas) {
        if (bars?.size != ArrXName?.size) {
            return
        }
        //画矩形，并左右设置间距
        //根据该项数值获取实际的柱形高度
        //Y轴每格单元高度为10数值

        var barHeightPer = barHeight / 20
        for (i in bars.indices) {
            val bar = bars[i]
            val value = bar.value.toString()
//            Log.d("ssss", value + "==0000==" + _dbDataArr!![i])
            if (_dbDataArr != null && _dbDataArr!!.size > i && bar.value > _dbDataArr!![i]) {
                _dbDataArr!![i] = bar.value
            }
            // 画框框
            canvas!!.drawRect(bar.left, 10f, bar.right, kuangBottom, kuangPaint!!)
            canvas!!.drawLine(bar.left, textMargin, bar.right, textMargin, kuangPaint!!)  //顶部线
            canvas!!.drawLine(bar.left, 2 * textMargin + barHeight - 10, bar.right, 2 * textMargin + barHeight - 10, kuangPaint!!)  //底部线上
            canvas!!.drawLine(bar.left, _heightTop - 2 * xNameMargin, bar.right, _heightTop - 2 * xNameMargin, kuangPaint!!)  //底部线下

            //绘制柱形数值
            val rectMax = Rect()
            var textMax = _dbDataArr!![i].toString()
            textMaxPaint!!.getTextBounds(textMax, 0, textMax.length, rectMax)
            val textLeft = bar.left + (bar.right - bar.left - rectMax.width()) / 2 //计算使文字在柱形居中位置
            canvas!!.drawText(textMax, textLeft, textMargin - rectMax.height() / 2, textMaxPaint!!) //绘制柱顶部数值

            val rectNow = Rect()
            textNowPaint!!.getTextBounds(value, 0, value.length, rectNow)
            val textLeftNow = bar.left + (bar.right - bar.left - rectNow.width()) / 2 //计算使文字在柱形居中位置
            canvas!!.drawText(value, textLeftNow, _heightTop - 2 * xNameMargin - rectNow.height() / 2, textNowPaint!!) //绘制柱低部数值

            //x轴数据
            val rectX = Rect()
            var xName = ArrXName[i]
            if (xName != null && xName.contains("-")) {
                var split = xName.split("-")
                if (split.size == 2) {
                    var xStart = split[0] + "M"
                    textPaint!!.getTextBounds(xStart, 0, xStart.length, rectX)
                    var textLeft = bar.left + (bar.right - bar.left - rectX.width()) / 2 //计算使文字在柱形居中位置
                    canvas!!.drawText(xStart, textLeft, _heightTop - 1 * xNameMargin - rectX.height() / 2, textPaint!!)
                    var xEnd = split[1] + "M"
                    textPaint!!.getTextBounds(xEnd, 0, xEnd.length, rectX)
                    var textLeft2 = bar.left + (bar.right - bar.left - rectX.width()) / 2 //计算使文字在柱形居中位置
                    canvas!!.drawText(xEnd, textLeft2, (_heightTop - rectX.height() / 2 - 8).toFloat(), textPaint!!)
                }
            }

            // 画彩色条纹
            var count: Int = bar.value / 5
            if (bar.value % 5 == 0) {
                count = bar.value / 5
            } else {
                count = bar.value / 5 + 1
            }
            var countMax: Int = _dbDataArr!![i] / 5
            if (bar.max % 5 == 0) {
                countMax = _dbDataArr!![i] / 5
            } else {
                countMax = _dbDataArr!![i] / 5 + 1
            }
            for (i in 0 until count) {
                rectPaint!!.color = _barColors!![i]
                canvas!!.drawRect(
                    bar.left + 4,
                    barHeight - barHeightPer * (i + 1) + textMargin + maxMargin,
                    bar.right - 4,
                    barHeight - barHeightPer * i - 4 + textMargin + maxMargin,
                    rectPaint!!
                )
            }

            // 画最大值保持
            var maxTop = barHeight - barHeightPer * (countMax + 1) + textMargin + maxMargin - 10
            canvas!!.drawRect(
                bar.left + 4,
                maxTop,
                bar.right - 4,
                maxTop + 6,
                maxPaint!!
            )
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        _width = measuredWidth
        _height = measuredHeight
        _heightTop = _height * 2 / 3
        _heightBottom = _height * 1 / 3
//        Log.d("ffff", "==onMeasure==_width==" + _width)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        _width = w
        _height = h
        _heightTop = _height * 2 / 3
        _heightBottom = _height * 1 / 3

        if (_spectrumBmp == null || _widthLast != _width) {
            _spectrumBmp = Bitmap.createBitmap(_width, _height / 3 * 2, Bitmap.Config.ARGB_8888)
            _spectrumCanvas = Canvas(_spectrumBmp!!)
//            Log.d("ssss", "==_waterfallBmp==")
        }

        if (_waterfallBmp == null || _widthLast != _width) {
            _waterfallBmp = Bitmap.createBitmap(_width, _height / 3 * 1, Bitmap.Config.ARGB_8888)
            _waterfallCanvas = Canvas(_waterfallBmp!!)
//            Log.d("ffff", "==_width==" + _width)
        }

        if (_showBmp == null || _widthLast != _width) {
            _showBmp = Bitmap.createBitmap(_width, _height, Bitmap.Config.ARGB_8888)
            _showCanvas = Canvas(_showBmp!!)
        } else {
            _showCanvas!!.drawColor(Color.BLACK, PorterDuff.Mode.CLEAR) // 清空画布
        }
        _widthLast = _width
//        Log.d("ffff", "==onSizeChanged==_width==" + _width)
    }

    override fun onDraw(canvas: Canvas) {
        if (_width == 0 || _height == 0)
            return
        kuangBottom = (_height / 3 * 2 - 10).toFloat()
        barHeight = (_height / 3 * 2 - textMargin * 2 - maxMargin - xNameMargin * 2).toFloat()
        executeDraw(false)
        drawYText(canvas)
        drawXText(canvas)
        drawBars(canvas)
        drawWaterfall()
        if (_showBmp != null) {
            canvas.drawBitmap(_showBmp!!, 20f, 20f, _paint)
        }
        super.onDraw(canvas)
    }

    private fun drawWaterfall() {
        if (ArrXData == null || ArrXData.size == 0 || bars?.size != ArrXName?.size) {
            return
        }
        if (_waterfallBmp == null) return
        val widthWater: Int = _waterfallBmp!!.width
        val heightWater: Int = _waterfallBmp!!.height
        if (widthWater == 0 || heightWater == 0) {
            return
        }
        val yMargin: Float = _marginTop + _marginBottom
        val perHeight = (heightWater - yMargin) / (_rainRow.toFloat())
        var fl = widthWater - objBarMarginLeftRight - _marginRight
        var fl1 = heightWater - yMargin - perHeight

        var bitmap = Bitmap.createBitmap(_waterfallBmp!!, objBarMarginLeftRight.toInt(), 0, fl.toInt(), fl1.toInt())
        _waterfallCanvas?.drawBitmap(bitmap, objBarMarginLeftRight, 0 + perHeight, _waterfallPaint) // 下移 perHeight

        for (index in ArrXData.indices) {
            var colorByDb = getColorByDb(ArrXData[index])
            _waterfallPaint?.color = colorByDb
            val rectLeft: Float = index * unitWidth + widthY + objBarMarginLeftRight - 6
            val rectRight = rectLeft + unitWidth - rectPadding * 2
//            Log.d("ssss", index.toString() + "==unitWidth==" + unitWidth + "==rectLeft==" + rectLeft)
//            Log.d("ssss", "==rectLeft==" + rectLeft + "==_marginTop==" + _marginTop + "==rectRight==" + rectRight + "==rectBottom==" + rectBottom);
            _waterfallCanvas?.drawRect(rectLeft, 0f, rectRight, perHeight, _waterfallPaint!!)
//            Log.d("ffff", "==index==" + index + "==widthY==" + widthY + "==rectLeft==" + rectLeft + "==_waterfallCanvas==" + _waterfallCanvas);
//            Log.d("ffff", ArrXData.size.toString() + "==ArrXData.size==" + index);
        }
    }

    fun getColorByDb(dbm: Int): Int {
        var color = resources.getColor(R.color.rain_0)
        when (dbm) {
            in 0 until 30 -> {
                color = resources.getColor(R.color.rain_0)
            }

            in 30 until 50 -> {
                color = resources.getColor(R.color.rain_2)
            }

            in 50 until 70 -> {
                color = resources.getColor(R.color.rain_3)
            }

            in 70 until 100 -> {
                color = resources.getColor(R.color.rain_4)
            }
        }
        return color
    }

    /**
     * 绘制Y轴文字及基准线
     */
    private fun drawYText(canvas: Canvas) {
        var size = ArrXData.size
        if (size == 0) return
        if (_spectrumCanvas == null)
            return
        var top_bottom = barHeight - 16
        unitHeight = ((top_bottom) / (ArrYName.size - 1)).toFloat()
        //        unitHeight = _height / (ArrYName.length - 1) - bottomTextHeight;
        for (num in ArrYName) {
            val rect = Rect()
            val text = num.toString() + ""
            textPaint!!.getTextBounds(text, 0, text.length, rect)
            canvas!!.drawText(text, 0f, top_bottom.toFloat() + textMargin + maxMargin + 4, textPaint!!) //画刻度文字
            widthY = rect.width().toFloat()
            leftMargin = widthY + objBarMarginLeftRight
//            canvas.drawLine(leftMargin, top_bottom.toFloat(), width.toFloat(), top_bottom.toFloat(), linePaint!!) //画横线
            top_bottom -= unitHeight.toInt()
        }

        var textTip = "now"
        val rect = Rect()
        textPaint!!.getTextBounds(textTip, 0, textTip.length, rect)
        canvas!!.drawText(textTip, 0f, _heightTop - 2 * xNameMargin - rect.height() / 2 - 4, textPaint!!) //画实时值文字

        var textTipMax = "max"
        val rectMax = Rect()
        textPaint!!.getTextBounds(textTipMax, 0, textTipMax.length, rectMax)
        canvas!!.drawText(textTipMax, 0f, textMargin - rectMax.height() / 2 - 4, textPaint!!) //画实时值文字

        var xStart = "start"
        val rectStart = Rect()
        textPaint!!.getTextBounds(xStart, 0, xStart.length, rectStart)
        canvas!!.drawText(xStart, 0f, _heightTop - 1 * xNameMargin - rectStart.height() / 2, textPaint!!)

        var xEnd = "end"
        val rectEnd = Rect()
        textPaint!!.getTextBounds(xEnd, 0, xEnd.length, rectEnd)
        canvas!!.drawText(xEnd, 0f, (_heightTop - rectEnd.height() / 2 - 8).toFloat(), textPaint!!)
    }

    /**
     * 绘制X轴文字
     */
    private fun drawXText(canvas: Canvas) {
        var size = ArrXData.size
        if (size == 0) return
        if (width / size < 5) return
        unitWidth = ((width - (objBarMarginLeftRight) * 2) / size - 5).toFloat()
        var bar: Bar
        for (i in ArrXData.indices) {
            val Xname = ArrXName[i]
            //            canvas.drawText(ArrXName.get(i), left + unitWidth / 4, getHeight(), textPaint);  //画文字
            var txtSize = -textPaint!!.ascent() + textPaint!!.descent()
            if (textPaint!!.style == Paint.Style.FILL_AND_STROKE || textPaint!!.style == Paint.Style.STROKE) {
                txtSize += textPaint!!.strokeWidth // add stroke width to the text
            }
            val lineSpace = txtSize * 0.1f // default line spacing
//            val lines = Xname.split("-").toTypedArray()
//            for (j in lines.indices) {
//                canvas.drawText(lines[j], leftMargin + unitWidth / 4 - lineSpace, _heightTop - (txtSize + lineSpace) * j - 10, textPaint!!)
//            }
//            canvas.drawText(Xname, leftMargin + unitWidth / 4 + 8, _heightTop - (txtSize + lineSpace) - 4, textPaint!!)
            val value = ArrXData[i]
            val top = _heightTop - value / 10 * unitHeight
            //            LogUtil.Logd("ssss", "==unitHeight==" + unitHeight + "==top==" + top);
            var color = resources.getColor(R.color.google_blue)
//            if (value > 0 && value < 30) {
//                color = resources.getColor(R.color.google_green)
//            } else if (value >= 30 && value < 70) {
//                color = resources.getColor(R.color.google_yellow)
//            } else {
//                color = resources.getColor(R.color.google_red)
//            }
            when (value) {
                in 0..19 -> {
                    color = resources.getColor(R.color.google_blue)
                }

                in 20..39 -> {
                    color = resources.getColor(R.color.google_green)
                }

                in 40..59 -> {
                    color = resources.getColor(R.color.google_yellow)
                }

                in 60..79 -> {
                    color = resources.getColor(R.color.google_pink)
                }

                in 80..99 -> {
                    color = resources.getColor(R.color.google_red)
                }
            }

            bar = Bar(value, (leftMargin + rectPadding).toFloat(), top, leftMargin + unitWidth - rectPadding, (_heightTop - bottomTextHeight).toFloat())
            bars.add(bar)
            leftMargin += unitWidth
        }
    }

    var disTanceX = 0f
    var touchTime = 0L
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if ((System.currentTimeMillis() - touchTime) < 200) {
            return true
        }
        touchTime = System.currentTimeMillis()
        var action = event.action
//        var x0 = event.getX(0)
//        var x1 = 0f
//        try {
//            x1 = event.getX(1)
//        } catch (e: Exception) {
//
//        }
//        Log.d("dddd", "==action==" + action)
//        if (x1 == 0f) {
//            Log.d("dddd", "==点击事件==")
//        } else {
//            var xNow = Math.abs(x0 - x1)
//            if (Math.abs(xNow - disTanceX) > 10) {
//                if (xNow > disTanceX) {
//                    Log.d("dddd", "==放大==" + xNow + "=====disTanceX=====" + disTanceX)
//                } else {
//                    Log.d("dddd", "==缩小==" + xNow + "=====disTanceX=====" + disTanceX)
//                }
//                disTanceX = xNow
//            }
//        }


//        when (event.action) {
//            MotionEvent.ACTION_DOWN -> {
//                Log.d("dddd", "==ACTION_DOWN==")
//            }
//
//            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
//                Log.d("dddd", "==ACTION_UP==")
//            }
//
//            MotionEvent.ACTION_POINTER_DOWN -> {
//                Log.d("dddd", "==ACTION_POINTER_DOWN==")
//            }
//
//            MotionEvent.ACTION_POINTER_UP -> {
//                Log.d("dddd", "==ACTION_POINTER_UP==")
//            }
//        }
//        //        Log.d("time", "==onTouch==" + (System.currentTimeMillis() - start));
//        return true


        if (action == MotionEvent.ACTION_DOWN) {
            for (i in bars.indices) {
                val bar = bars[i]
                if (event.x > bar.left && event.x < bar.right) {
                    //按下事件在当前柱形内
                    showValuePos = i
//                    invalidate()
                    dealClick(ArrXName[i], bar.value)
                    break
                }
            }
        }
        return true
    }

    private fun dealClick(xName: String, value: Int) {
        Log.d("ssss", xName + "==value==" + value)
        mPinpuBarCallBack?.clickBar(xName, value)
    }

    private fun levelToColorIndex(): ByteArray {
        val colors = ByteArray(ArrXData.size)
        for (i in ArrXData.indices) {
            var index: Byte
            val value = ArrXData[i]
            index = if (value <= _minValue) {
                (1).toByte()
            } else if (value >= _maxValue) {
                (_gradientColors!!.size - 1).toByte()
            } else {
                ((value - _minValue) / (_maxValue - _minValue) * (_gradientColors!!.size - 1)).toInt().toByte()
            }
            colors[i] = index
        }
        return colors
    }

    fun clearDataMax() {
        if (ArrYName != null && ArrYName.size > 0)
            _dbDataArr = IntArray(ArrYName.size)
        invalidate()
    }
}