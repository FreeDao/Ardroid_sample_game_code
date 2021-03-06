package com.bn.sample4_6;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
public class Sample4_6Activity extends Activity 
{
    SensorManager mySm;
    Sensor myS;
    TextView tv1;
    TextView tv2;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mySm=(SensorManager)this.getSystemService(SENSOR_SERVICE);
        myS=mySm.getDefaultSensor(Sensor.TYPE_LIGHT);
        tv2=(TextView)this.findViewById(R.id.textView2);
        tv1=(TextView)this.findViewById(R.id.textView1);
        StringBuffer str=new StringBuffer();
        str.append("\n名称：");
        str.append(myS.getName());
        str.append("\n类型编号：");
        str.append(myS.getType());
        str.append("\n耗电量（mA）：");
        str.append(myS.getPower());
        str.append("\n测量最大范围:");
        str.append(myS.getMaximumRange());
        str.append("\n版本：");
        str.append(myS.getVersion());
        tv2.setText(str);
        tv2.setTextSize(25);
    }
    
    private SensorEventListener mySel=new SensorEventListener()
    {
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) 
		{
			
		}

		@Override
		public void onSensorChanged(SensorEvent event) 
		{
			float[] value=event.values;
			tv1.setText("光照强度是："+value[0]);
			tv1.setTextSize(25);
		}
    };

	@Override
	protected void onResume()
	{
		super.onResume();
		mySm.registerListener
		(
				mySel, 
				myS, 
				SensorManager.SENSOR_DELAY_NORMAL
	    );
	}
	@Override
	protected void onPause() 
	{
		super.onPause();
		mySm.unregisterListener(mySel);
	}

    
}