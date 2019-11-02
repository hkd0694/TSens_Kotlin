package com.example.tiltsensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity(),SensorEventListener {


    //센서 정밀도가 변겨오디면 호출된다.
    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //센서 값이 변경되면 호출된다.
    override fun onSensorChanged(event: SensorEvent?) {
        //센서값이 변경되면 호추로디는 메서드
        // value[0] : x 축 값 : 위로 기울이면 -10~0, 아래로 기울이면 0~10
        // value[1] : y 축 값 : 왼쪽으로 기울이면 -10~0, 오른쪽으로 기울이면 0~10
        // value[2] : z 축 값 : 미사용
        event?.let {
            Log.e("Start","OnSensorChanged x : + ${event.values[0]}, y : ${event.values[1]}, z : ${event.values[2]}")
        }
    }

    private val sensorManager by lazy{
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL)
        //SENSOR_DELAY_FASTEST  : 가능한 가장 자주 센서값을 얻는다.
        //SENSOR_DELAY_GAME     : 게임에 적합한 정도로 센서값을 얻는다.
        //SENSOR_DELAY_NORMAL   : 화면 방향이 전환될 때 적합한 정도로 센서값을 얻는다.
        //SENSOR_DELAY_UI       : 사용자 인터페이스를 표시하기에 적합한 정도로 센서값을 얻는다.
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }




}
