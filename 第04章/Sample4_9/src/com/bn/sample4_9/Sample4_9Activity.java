package com.bn.sample4_9;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class Sample4_9Activity extends Activity {
	SensorManager mySm;
	Sensor myS;
	TextView tvX;
	TextView tvY;
	TextView tvZ;
	TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        tvX=(TextView)findViewById(R.id.textView1);
        tvY=(TextView)findViewById(R.id.textView2);
        tvZ=(TextView)findViewById(R.id.textView3);
        tv=(TextView)findViewById(R.id.textView4);
        
        mySm =(SensorManager)getSystemService(SENSOR_SERVICE);
        myS =mySm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        StringBuffer str=new StringBuffer();
        str.append("\n名称：");
        str.append(myS.getName());
        str.append("\n耗电量（mA）");
        str.append(myS.getPower());
        str.append("\n类型编号");
        str.append(myS.getType());
        str.append("\n版本");
        str.append(myS.getVersion());
        str.append("\n最大测量范围");
        str.append(myS.getMaximumRange());
        
        tv.setText(str);
        
       
    }
    
    private SensorEventListener mySel=new SensorEventListener()
    {
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {}

		@Override
		public void onSensorChanged(SensorEvent event) {
			float []values=event.values;
			tvX.setText("X轴方向上的磁场强度为："+values[0]);
			tvY.setText("Y轴方向上的磁场强度为："+values[1]);
			tvZ.setText("Z轴方向上的磁场强度为："+values[2]);
		}
    };
    
	@Override
	protected void onResume() {
		super.onResume();
		mySm.registerListener(
				mySel, 
				myS, 
				SensorManager.SENSOR_DELAY_NORMAL);
	}
    
	@Override
	protected void onPause() {
		super.onPause();
		mySm.unregisterListener(mySel);
	}
}