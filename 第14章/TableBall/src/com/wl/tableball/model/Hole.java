package com.wl.tableball.model;

import org.jbox2d.dynamics.Body;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.wl.tableball.game.GameView;
import com.wl.tableball.util.Constant;

public class Hole extends Ball{
	
	//flag是标志位，标志是否画连续的背景
	boolean flag=false;
	
	Bitmap bitmapother=Constant.TP_ARRAY[5];//球洞
	
	Hole(Body body,Bitmap bitmap,GameView gameview)
	{
		super(body,bitmap,gameview);
	}

	//小球落洞后旋转小球
	public  void drawself(Canvas canvas,Paint paint)
	{
		super.drawself(canvas, paint);
		if(flag)
		{
			canvas.save();
			//pic 旋转
			angle=body.getAngle();//角度
			canvas.rotate((float)angle+Constant.ROTEANGLE+Constant.ROTEANGLEOFFSET, x, y);
			Matrix m3=new Matrix();
			m3.setTranslate(x-bitmapother.getWidth()/2, y-bitmapother.getHeight()/2);//平移
			canvas.drawBitmap(bitmapother, m3, paint);
			canvas.restore();
		}
	}
	
	//球和洞发生碰撞时发生的方法
	//只有洞才执行这个方法，球不会执行这个方法，此处洞只换一下图
	public void doAction()
	{
		BitmapChangeThread bitmapchanagethread=new BitmapChangeThread(this);//刷新小球落洞动画的线程
		bitmapchanagethread.start();
		flag=true;
		
	}
	
	private class BitmapChangeThread extends Thread
	{
		Hole ball;
		int i=0;
		BitmapChangeThread(Hole ball)
		{
			this.ball=ball;
		}
		@Override
		public void run() {
			while(i<15)
			{
				ball.bitmapother=Constant.BALL_IN_HOLE[i][0];
				i++;
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			ball.bitmapother=null;
			flag=false;
		}
	}
}

