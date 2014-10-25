package com.bn.sample4_10;
import android.app.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Sample4_10Activity extends Activity
{
	
	//SensorManager对象引用
	SensorManager mySensorManager;	
	Sensor sensor;
	Bitmap yuan;
	Bitmap shang;
	Bitmap zuo;
	Bitmap xian;
	Bitmap qiuzuo;
	Bitmap qiushang;
	Bitmap qiuzhong;
	String str1;
	String str2;
	String str3;
	String str4;
	String str5;
	MyView mv;
	
	private SensorEventListener mel=new SensorEventListener()
	{

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {}

		@Override
		public void onSensorChanged(SensorEvent event) 
		{
			float []values=event.values;
			
			mv.dx=values[0];
			mv.dy=values[1];
			mv.dz=values[2];
			
		}
		
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        //全屏
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);		
		
		//获得SensorManager对象
        mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);	
        sensor=mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        str1="名称:"+sensor.getName();
        str2="耗电量（mA）："+sensor.getPower();
        str3="类型编号："+sensor.getType();
        str4="版本："+sensor.getVersion();
        str5="最大测量范围："+sensor.getMaximumRange();
       
        yuan = BitmapFactory.decodeResource(getResources(), R.drawable.yuan);
        zuo = BitmapFactory.decodeResource(getResources(), R.drawable.zuo);
        shang = BitmapFactory.decodeResource(getResources(), R.drawable.shang);
        xian = BitmapFactory.decodeResource(getResources(), R.drawable.xian);
        qiuzuo = BitmapFactory.decodeResource(getResources(), R.drawable.qiuzuo);
        qiushang = BitmapFactory.decodeResource(getResources(), R.drawable.qiushang);
        qiuzhong = BitmapFactory.decodeResource(getResources(), R.drawable.qiuzhong);
        mv = new MyView(this);
        this.setContentView(mv);       
        
    }
    
	@Override
	protected void onResume() 
	{						//重写onResume方法
		mySensorManager.registerListener(mel, sensor, SensorManager.SENSOR_DELAY_UI);
		mv.mvdt.pauseFlag=false;
		
		super.onResume();
	}
	
	@Override
	protected void onPause() 
	{									//重写onPause方法
		mySensorManager.unregisterListener(mel);	//取消注册监听器
		mv.mvdt.pauseFlag=true;
		
		super.onPause();
	}
}