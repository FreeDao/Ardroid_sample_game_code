package com.bn.sample4_11;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.hardware.SensorManager;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
public class MyView extends SurfaceView implements SurfaceHolder.Callback  //ʵ���������ڻص��ӿ�
{
	Sample4_11Activity activity;
	MyViewDrawThread mvdt;
	Paint paint;
	Paint paint1;
	Paint paint2;
	float angle;
	float mx;//�ų���������xֵ
	float my;//�ų���������yֵ
	float mz;//�ų���������zֵ
	float ax;//������������xֵ
	float ay;//������������yֵ
	float az;//������������zֵ
	
	public MyView(Sample4_11Activity activity)
	{
		super(activity);
		this.activity = activity;
		this.getHolder().addCallback(this);//�����������ڻص��ӿڵ�ʵ����
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
			//������ת����
			float[] R=new float[9];
			//��ȡ��ת����ĸ���ֵ
			SensorManager.getRotationMatrix
			(
				R, 
				null, 
				new float[]{ax,ay,az}, 
				new float[]{mx,my,mz}
		    );
			//��ֵ̬����
			float[] values=new float[3];
			//��ȡ��ֵ̬
			SensorManager.getOrientation(R, values);
			//����ֵ̬�еķ�λ�ǣ�Yaw��azimuth��ת��Ϊ�Ƕ�
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