package com.bn.sample4_11;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
public class Sample4_11Activity extends Activity
{
	SensorManager mySensorManager;	
	Sensor sensorAccelerometer;
	Sensor sensorMagneticField;
	Bitmap jiantou;
	Bitmap beijing;
	MyView mv;
	Object lock=new Object();
	//磁场传感器的监听器
	private SensorEventListener mel=new SensorEventListener()
	{

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) 
		{
			
		}

		@Override
		public void onSensorChanged(SensorEvent event) 
		{
			synchronized(lock)
			{
				mv.mx=event.values[0];
				mv.my=event.values[1];
				mv.mz=event.values[2];
			}
		}
		
	};
	
	//重力传感器的监听器
	private SensorEventListener mek=new SensorEventListener()
	{

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) 
		{
			
		}

		@Override
		public void onSensorChanged(SensorEvent event) 
		{
			synchronized(lock)
			{
				mv.ax=event.values[0];
				mv.ay=event.values[1];
				mv.az=event.values[2];
			}
		}
		
	};
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);		
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
        mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);	
        sensorAccelerometer=mySensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sensorMagneticField=mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        jiantou = BitmapFactory.decodeResource(getResources(), R.drawable.jiantou);
        beijing = BitmapFactory.decodeResource(getResources(), R.drawable.beijing);
        mv = new MyView(this);
        this.setContentView(mv);       
    }
    
	@Override
	protected void onResume() 
	{						
		mySensorManager.registerListener
		(mel, sensorMagneticField, SensorManager.SENSOR_DELAY_UI);
		mySensorManager.registerListener
		(mek, sensorAccelerometer, SensorManager.SENSOR_DELAY_UI);
		mv.mvdt.pauseFlag=false;
		
		super.onResume();
	}
	
	@Override
	protected void onPause() 
	{									
		mySensorManager.unregisterListener(mel);	
		mySensorManager.unregisterListener(mek);	
		mv.mvdt.pauseFlag=true;
		super.onPause();
	}
}