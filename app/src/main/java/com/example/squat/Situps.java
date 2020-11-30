package com.example.squat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Situps extends AppCompatActivity implements SensorEventListener{ //kan gøre sådan her i stedet. Så bare sætte metoderne ind.
    private SensorManager sensorManager;
    private Sensor acceleroMeter;
    private SensorEventListener acceleroSensorListener;
    TextView repText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situps);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        acceleroMeter = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(Situps.this, acceleroMeter,  sensorManager.SENSOR_DELAY_NORMAL);
        TextView repText = (TextView) findViewById(R.id.textView3);

    }

    @Override
    protected void onDestroy() {
        sensorManager.unregisterListener(acceleroSensorListener);
        super.onDestroy();
    }

    int reps = 0;
    boolean situp;
    double currentValue;
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        currentValue = sensorEvent.values[1];

        //i++;
        //System.out.println(i);

        //for(int i = 0; reps < 10; i++){
        if(/*sensorEvent.values[1] > 0 lastValue < 0.0 && */currentValue < 0.0) {
            situp = true;
            //int a = 1;
        }
        if(situp==true && currentValue > 0.0){
            //reps++;
            situp=false;
            reps++;

        }

        repText.setText("reps: " + reps);
        //repText.setText(sensorEvent.values[1] + " " + reps);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}