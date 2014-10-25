package com.bn.sample4_11;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.hardware.SensorManager;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
public class MyView extends SurfaceView implements SurfaceHolder.Callback  //实现生命周期回调接口
{
	Sample4_11Activity activity;
	MyViewDrawThread mvdt;
	Paint paint;
	Paint paint1;
	Paint paint2;
	float angle;
	float mx;//磁场传感器的x值
	float my;//磁场传感器的y值
	float mz;//磁场传感器的z值
	float ax;//重力传感器的x值
	float ay;//重力传感器的y值
	float az;//重力传感器的z值
	
	public MyView(Sample4_11Activity activity)
	{
		super(activity);
		this.activity = activity;
		this.getHolder().addCallback(this);//设置生命周期回调接口的实现者
		paint = new Paint();
		mvdt=new MyViewDrawThread(this);
	}

	@Override
	public void draw(Canvas canvas) 
	{
		super.draw(canvas);
		canvas.drawBitmap(activity.beijing,0,0, paint);
		
		Matrix m1=new Matrix();
		m1.setTranslate(0,0);
		Matrix m2=new Matrix();
		m2.setRotate(getDirection(), 240, 240);
		Matrix mz=new Matrix();
		mz.setConcat(m1, m2);
		canvas.drawBitmap(activity.jiantou, mz, paint);
	}
	
	public float getDirection()
	{
		float result=0;
		synchronized(activity.lock)
		{
			//声明旋转矩阵
			float[] R=new float[9];
			//获取旋转矩阵的各项值
			SensorManager.getRotationMatrix
			(
				R, 
				null, 
				new float[]{ax,ay,az}, 
				new float[]{mx,my,mz}
		    );
			//姿态值数组
			float[] values=new float[3];
			//获取姿态值
			SensorManager.getOrientation(R, values);
			//将姿态值中的方位角（Yaw或azimuth）转换为角度
			result=(float) Math.toDegrees(values[0]);
			result=(result+360)%360.0f;
		}
		return result;
	}

	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) 
	{
		
	}

	public void surfaceCreated(SurfaceHolder holder) 
	{
		mvdt.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		
	}
}