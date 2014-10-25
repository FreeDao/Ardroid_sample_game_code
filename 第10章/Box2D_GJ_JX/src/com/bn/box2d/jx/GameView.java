package com.bn.box2d.jx;
import static com.bn.box2d.jx.Constant.*;

import org.jbox2d.common.Vec2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView 
implements SurfaceHolder.Callback  //ʵ���������ڻص��ӿ�
{
	MyBox2dActivity activity;
	Paint paint;//����		
	DrawThread dt;//�����߳�
	PhysicsThread pt;
	public GameView(MyBox2dActivity activity) 
	{
		super(activity);
		this.activity = activity;	
		this.activity.world.setGravity(new Vec2(0.0f,20.0f));
		//�����������ڻص��ӿڵ�ʵ����
		this.getHolder().addCallback(this);
		//��ʼ������
		paint = new Paint();//��������
		paint.setAntiAlias(true);//�򿪿����

		pt=new PhysicsThread(this);
		pt.start();
		//���������߳�
		dt=new DrawThread(this);
		dt.start();
	} 

	public void onDraw(Canvas canvas)
	{		
		if(canvas==null)
		{
			return;
		}
		canvas.drawARGB(255, 220, 220, 220);
		
		//���Ƴ����е�����
		for(MyBody mb:activity.bl)
		{
			mb.drawSelf(canvas, paint);
		}
		for(MyBody mb:activity.b2)
		{
			mb.drawSelf(canvas, paint);
		}
		for(MyBody mb:activity.b3)
		{
			mb.drawSelf(canvas, paint);
		}
		for(MyBody mb:activity.b4)
		{
			mb.drawSelf(canvas, paint);
		}
		//���ƻ�����
		paint.setColor(Color.RED);
		canvas.drawLine(this.activity.bl.get(2).body.getWorldCenter().x*RATE, (260+y)*ratio, this.activity.b4.get(1).body.getWorldCenter().x*RATE, (260+y)*ratio,paint);
		canvas.drawLine(this.activity.b4.get(1).body.getWorldCenter().x*RATE, (260+y)*ratio,
				this.activity.b4.get(1).body.getWorldCenter().x*RATE,
				this.activity.b4.get(1).body.getPosition().y*RATE,paint);
		canvas.drawLine(this.activity.bl.get(2).body.getWorldCenter().x*RATE, (260+y)*ratio,
				this.activity.bl.get(2).body.getWorldCenter().x*RATE,
				this.activity.bl.get(2).body.getPosition().y*RATE,paint);
		
		if(this.activity.rj3!=null)
		{
			//ʹ�ϲ�ĳ���������ת
			float angle_top=this.activity.rj3.getJointAngle();
			if(angle_top>0)
			{
				TuoPan_ZhuanHui=true;
				TuoPan1_shanchu=false;
			}
			else if(angle_top<(float) (-1.8*ratio))
			{
				this.activity.rj3.setMotorSpeed((float)(Math.PI/2));
			}
		}
	}

	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) 
	{
		
	}

	public void surfaceCreated(SurfaceHolder holder) {//����ʱ������
		repaint();
	}

	public void surfaceDestroyed(SurfaceHolder arg0) {//����ʱ������
		PHYSICS_THREAD_FLAG=false;
		System.exit(0);
		
	}
	
	public void repaint()
	{
		SurfaceHolder holder=this.getHolder();
		Canvas canvas = holder.lockCanvas();//��ȡ����
		try{
			synchronized(holder){
				onDraw(canvas);//����
			}			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(canvas != null){
				holder.unlockCanvasAndPost(canvas);
			}
		}
	}
}