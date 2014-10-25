package com.bn.box2d.jx;
import static com.bn.box2d.jx.Constant.*;

import org.jbox2d.common.Vec2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView 
implements SurfaceHolder.Callback  //实现生命周期回调接口
{
	MyBox2dActivity activity;
	Paint paint;//画笔		
	DrawThread dt;//绘制线程
	PhysicsThread pt;
	public GameView(MyBox2dActivity activity) 
	{
		super(activity);
		this.activity = activity;	
		this.activity.world.setGravity(new Vec2(0.0f,20.0f));
		//设置生命周期回调接口的实现者
		this.getHolder().addCallback(this);
		//初始化画笔
		paint = new Paint();//创建画笔
		paint.setAntiAlias(true);//打开抗锯齿

		pt=new PhysicsThread(this);
		pt.start();
		//启动绘制线程
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
		
		//绘制场景中的物体
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
		//绘制滑轮线
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
			//使上侧的齿轮来回旋转
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

	public void surfaceCreated(SurfaceHolder holder) {//创建时被调用
		repaint();
	}

	public void surfaceDestroyed(SurfaceHolder arg0) {//销毁时被调用
		PHYSICS_THREAD_FLAG=false;
		System.exit(0);
		
	}
	
	public void repaint()
	{
		SurfaceHolder holder=this.getHolder();
		Canvas canvas = holder.lockCanvas();//获取画布
		try{
			synchronized(holder){
				onDraw(canvas);//绘制
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