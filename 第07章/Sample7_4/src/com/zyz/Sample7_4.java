package com.zyz;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

public class Sample7_4 extends Activity 
{
	public GameView gv;//游戏界面
	SensorManager mySensorManager;
    Sensor accelerometerSensor;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        //设置为全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);   
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,   
        WindowManager.LayoutParams. FLAG_FULLSCREEN);
        //设置为竖屏模式
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometerSensor=mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        
        gv=new GameView(this);   
        setContentView(gv);
    }
    
    private SensorEventListener mySensorListener = new SensorEventListener()
    {
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {}
		@Override
		public void onSensorChanged(SensorEvent event) 
		{
			float[] values=event.values;
			//计算出重力在屏幕上的投影方向
			Constant.GRAVITY_X=values[0];
			Constant.GRAVITY_Y=values[1];
		}		
	};
	
	@Override
	protected void onPause() 
	{
		super.onPause();		
		mySensorManager.unregisterListener(mySensorListener);
		
	}
    @Override
	protected void onResume() 
    {
		super.onResume();
		mySensorManager.registerListener
		(
				mySensorListener, 
				accelerometerSensor, 
				SensorManager.SENSOR_DELAY_GAME
		);
	}
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) 
    {     
    	switch (keyCode) 
    	{
    	case KeyEvent.KEYCODE_BACK:
    		System.exit(0);
    		break;
    	}
    	
    	return true;
    }
}