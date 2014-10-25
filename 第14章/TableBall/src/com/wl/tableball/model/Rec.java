package com.wl.tableball.model;

import org.jbox2d.dynamics.Body;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.wl.tableball.game.GameView;

public class Rec extends MyBody{

	public Rec(Body body, Bitmap bitmap,GameView gameview) {
		super(body, bitmap,gameview);
		
	}
	//保留构造器，和demo中的构造器一样
	Rec(Body body, float width,float height,Bitmap bitmap) 
	{
		super(body,width,height,bitmap);
	}
	
	public  void drawself(Canvas canvas,Paint paint)
	{
		super.drawself(canvas, paint);
	}
	
	public void doAction()
	{
		
	}
}
