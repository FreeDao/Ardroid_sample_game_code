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
	
	//SensorManager��������
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
        
        //ȫ��
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);		
		
		//���SensorManager����
        mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);	
        sensor=mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        str1="����:"+sensor.getName();
        str2="�ĵ�����mA����"+sensor.getPower();
        str3="���ͱ�ţ�"+sensor.getType();
        str4="�汾��"+sensor.getVersion();
        str5="��������Χ��"+sensor.getMaximumRange();
       
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
	{						//��дonResume����
		mySensorManager.registerListener(mel, sensor, SensorManager.SENSOR_DELAY_UI);
		mv.mvdt.pauseFlag=false;
		
		super.onResume();
	}
	
	@Override
	protected void onPause() 
	{									//��дonPause����
		mySensorManager.unregisterListener(mel);	//ȡ��ע�������
		mv.mvdt.pauseFlag=true;
		
		super.onPause();
	}
}