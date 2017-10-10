package com.example.shubham.environmentsensor;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer,mProx,mHumidity,mTemp;
    private TextView AText,PText,TText,HText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AText=(TextView)findViewById(R.id.A);
        PText=(TextView)findViewById(R.id.P);
        TText=(TextView)findViewById(R.id.T);
        HText=(TextView)findViewById(R.id.H);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mProx=mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mTemp=mSensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE);
        mHumidity=mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);

        Button Button=(Button)findViewById(R.id.button);
        Button.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent i = new Intent(MainActivity.this, graph.class);
                        startActivity(i);
                    }
                }
        );

    }

    @Override
    protected void onResume() {
        // Register a listener for the sensor.
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this,mProx,SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this,mHumidity,SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this,mTemp,SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER)
            AText.setText("Accelerometer :  " + event.values[0]);
        if(event.sensor.getType()==Sensor.TYPE_PROXIMITY)
            PText.setText("Light :  " + event.values[0]);
        if(event.sensor.getType()==Sensor.TYPE_RELATIVE_HUMIDITY)
            HText.setText("Humidity :  " + event.values[0]);
        if(event.sensor.getType()==Sensor.TYPE_TEMPERATURE)
            TText.setText("Temperature :  " + event.values[0]);


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
