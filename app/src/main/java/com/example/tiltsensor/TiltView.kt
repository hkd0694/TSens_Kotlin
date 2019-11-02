package com.example.tiltsensor

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.SensorEvent
import android.view.View

class TiltView(context: Context?) : View(context) {

    private val greenPaint : Paint = Paint()
    private val blackPaint : Paint = Paint()

    private var cX : Float = 0f
    private var cY : Float = 0f

    private var xCoord : Float = 0f
    private var yCoord : Float = 0f

    fun onSensorEvent(event : SensorEvent?){
        yCoord = event!!.values[0]*20
        xCoord = event!!.values[1]*20
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        cX = w/2f
        cY = h/2f
    }

    init {
        greenPaint.color = Color.GREEN
        //FILL : 색을 채운다. 획 관련된 설정을 무시한다.
        //FILL_AND_STROKE : 획과 관련된 설정을 유지하면서 색을 채웁니다.
        //STROKE : 획 관련 설정을 유지하여 외곽선만 그립니다.

        //기본값은 FILL 이며 여기서는 외곽선만 그리므로 STROKE를 설정합니다.
        blackPaint.style = Paint.Style.STROKE
    }

    override fun onDraw(canvas : Canvas?) {

        //바깥 원
        canvas?.drawCircle(cX,cY,100f,blackPaint)
        //녹색 원
        canvas?.drawCircle(xCoord + cX,yCoord + cY,100f,greenPaint)
        //가운데 십자가
        canvas?.drawLine(cX-20,cY,cX+20,cY,blackPaint)
        canvas?.drawLine(cX,cY-20,cX,cY+20,blackPaint)

    }
}