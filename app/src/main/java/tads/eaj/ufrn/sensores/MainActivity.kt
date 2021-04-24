package tads.eaj.ufrn.sensores

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import tads.eaj.ufrn.sensores.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity(), SensorEventListener {

    lateinit var binding: ActivityMainBinding
    lateinit var mySensorManager:SensorManager
    var myLuz:Sensor? = null
    var myProximidade:Sensor? = null
    var myGiroscopio:Sensor? = null
    var myAcelerometro:Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mySensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        myLuz = mySensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)
        myProximidade = mySensorManager!!.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        myGiroscopio = mySensorManager!!.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        myAcelerometro = mySensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_PROXIMITY){
            binding.xTextView.text = "Proximidade: ${event!!.values[0]}"
        }else if (event?.sensor?.type == Sensor.TYPE_LIGHT){
            binding.wTextView.text = "Luz: ${event!!.values[0]}"
        }else if (event?.sensor?.type == Sensor.TYPE_GYROSCOPE){
            binding.yTextView.text = "Giroscopio:\nX: ${event!!.values[0]}\nY: ${event!!.values[1]}\nZ: ${event!!.values[2]}"
        }else if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER){
            binding.zTextView.text = "Acelerometro:\nX: ${event!!.values[0]}\nY: ${event!!.values[1]}\nZ: ${event!!.values[2]}"
        }

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onResume() {
        super.onResume()
        mySensorManager!!.registerListener(this, myLuz, SensorManager.SENSOR_DELAY_UI)
        mySensorManager!!.registerListener(this, myProximidade, SensorManager.SENSOR_DELAY_UI)
        mySensorManager!!.registerListener(this, myGiroscopio, SensorManager.SENSOR_DELAY_UI)
        mySensorManager!!.registerListener(this, myAcelerometro, SensorManager.SENSOR_DELAY_UI)
    }

    override fun onPause() {
        super.onPause()
        mySensorManager!!.unregisterListener(this)
    }

}