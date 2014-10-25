package com.bn.sample4_8;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Sample4_8Activity extends Activity 
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
        myS=mySm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        tv1=(TextView)this.findViewById(R.id.textView1);
        tv2=(TextView)this.findViewById(R.id.textView2);
        StringBuffer str=new StringBuffer();
        str.append("\n名称：");
        str.append(myS.getName());
        str.append("\n耗电量（mA）：");
        str.append(myS.getPower());
        str.append("\n类型编号：");
        str.append(myS.getType());
        str.append("\n版本：");
        str.append(myS.getVersion());
        str.append("\n最大测量范围：");
        str.append(myS.getMaximumRange());
        tv2.setText(str);
        tv2.setTextSize(22);
    }
    
    private SensorEventListener mySel=new SensorEventListener()
    {
		@Override
		public void onAccuracyChanged(Sensor arg0, int arg1) 
		{
			
		}

		@Override
		public void onSensorChanged(SensorEvent event) 
		{
		   float []value=event.values;
		   tv1.setText("距离为："+value[0]);
		   tv1.setTextSize(22);
		}
    };

	@Override
	protected void onResume() 
	{
		super.onResume();
		mySm.registerListener(
				mySel, 
				myS, 
				SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	@Override
	protected void onPause() 
	{
		super.onPause();
		mySm.unregisterListener(mySel);
	}

}