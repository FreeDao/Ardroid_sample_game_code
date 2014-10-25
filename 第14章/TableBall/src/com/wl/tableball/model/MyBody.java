package com.wl.tableball.model;

import org.jbox2d.dynamics.Body;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.wl.tableball.game.GameView;

public abstract class MyBody {
	
	public Bitmap bitmap;
	public Body body;
	GameView gameview;
	
	float width;//目标宽度
	float height;//目标高度
	float x;
	float y;
	float angle;
	//构造器
	public MyBody(Body body, Bitmap bitmap,GameView gameview) 
	{
		this.body = body;
		this.bitmap = bitmap;
		this.gameview=gameview;
	}
	
	//保留构造器，和demo中的构造器一样
	public MyBody(Body body, float width,float height,Bitmap bitmap) 
	{
		this.body = body;
		this.bitmap = bitmap;
		this.height=height;
		this.width=width;
	}
	
	//绘画方法
	public  void drawself(Canvas canvas,Paint paint)
	{
		x=body.getPosition().x;
		y=body.getPosition().y;
		if(bitmap==null)
		{
			return;
		}
		canvas.save();
		Matrix m3=new Matrix();
		m3.setTranslate(x-bitmap.getWidth()/2, y-bitmap.getHeight()/2);//平移
		
		canvas.drawBitmap(bitmap, m3, paint);
		canvas.restore();
	}
	
	public void doAction()
	{
		
	}
}
