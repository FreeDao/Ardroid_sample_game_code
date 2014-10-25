package com.baina.tower.utils;

import java.util.ArrayList;

import com.baina.tower.constant.Constants;
import com.baina.tower.view.GameSurfaceView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

public class Utils extends Thread{

	float x;
	float y;
	int type;
	GameSurfaceView msv;
	Bitmap bmp;float scale=1f;float scale2=0;
	public boolean flag = true;
	ArrayList<Xian> list;		//存放线条
	int color;
	float width = Constants.PMX1;
	float length = Constants.PMY1;     //屏幕的宽高
	float speed;
	public Utils(int type, float x, float y,int count,int color,GameSurfaceView msv,float speed){
		this.x = x;
		this.y = y;
//		this.count = count;
//		list = new ArrayList<Xian>();
		this.type = type;
		this.speed = speed;
		this.msv = msv;
		this.color = color;
		if(type == 3||type == 1){
			createXian3(x,y,count,color,speed);
			if(type==3)scale =0f;
		}else if(type == 2){
			createXian2(x,y,count,color);
			scale =2f;
		}
		
	
	}
	private void createXian3(float x, float y,int count,int color,float speed){ //为列表添加线段
		list = new ArrayList<Xian>();
		for(int i=0; i<count; i++){
			if(type == 3)
			list.add(new Xian(x,y,50+(float)Math.random()*15,(float)(Math.random()*2*Math.PI),color,
					(float)(Math.random()*(speed-4))+4));
			else {
				list.add(new Xian(x,y,20+(float)Math.random()*5,(float)(Math.random()*2*Math.PI),color,
						(float)(Math.random()*(speed-4))+4));
			}
		}
		
	}
	private void createXian2(float x, float y,int count,int color){ //为列表添加线段
		list = new ArrayList<Xian>();
		for(int i=0; i<count; i++){
			list.add(new Xian(x,y,25,(float)(Math.random()*2*Math.PI),color,
					(float)(Math.random()*3)+3));
		}
		
	}
	public void drawBoom(Canvas canvas, Paint paint) {    //绘制线段
		if(type == 3){
			if(scale<1){
				if(!msv.cm.pause){
					scale+=0.09f;
				}
				Bitmap temp = small(msv.boom1,(scale));
				canvas.drawBitmap(temp, x-temp.getWidth()/2, y-temp.getHeight()/2, paint);
				if(msv.life>=1){
					Bitmap temp2 = small(msv.boom2,(float)(1.4-scale));
					canvas.drawBitmap(temp2, x-temp2.getWidth()/2, y-temp2.getHeight()/2, paint);					
				}else{
					Bitmap temp2 = small(msv.boom3,(float)(1.4-scale));
					canvas.drawBitmap(temp2, x-temp2.getWidth()/2, y-temp2.getHeight()/2, paint);
				}
			}else if(scale2<1){
				Bitmap temp = small(msv.boom1,scale-(scale2+=0.12f));
				canvas.drawBitmap(temp, x-temp.getWidth()/2, y-temp.getHeight()/2, paint);
				//canvas.drawBitmap(msv.boom3, x-msv.boom3.getWidth()/2, y-msv.boom3.getHeight()/2, paint);				
			}
			paint.setColor(color);
			for(int i=0; i<list.size(); i++){
				list.get(i).drawSelf(canvas, paint);
			}
		}else if(type == 2){
			if(scale > 0){
				if(!msv.cm.pause){
					scale-=0.18f;
				}
				Bitmap temp = small(msv.boom2,scale);
				canvas.drawBitmap(temp, x-temp.getWidth()/2, y-temp.getHeight()/2, paint);
			}
			paint.setColor(color);
			for(int i=0; i<list.size(); i++){
				list.get(i).drawSelf(canvas, paint);
			}
		}else if(type == 1){
			paint.setColor(color);
			for(int i=0; i<list.size(); i++){
				list.get(i).drawSelf(canvas, paint);
			}
		}
		
	}
	private void changePosition3(){   			//改变线段位置
		if(list.size()==0){
			flag = false;
		}
		for(int i=0; i<list.size(); i++){
			Xian xian = list.get(i);
			if(xian.speed>2.0 )xian.move();    //速度小于一定值时，删除该线段
			else list.remove(i);
		}
	}

	private void changePosition2(){   			//改变线段位置
		if(list.size()==0){
			flag = false;
		}
		for(int i=0; i<list.size(); i++){
			Xian xian = list.get(i);
			if(xian.speed>1.5 )xian.move();    //速度小于一定值时，删除该线段
			else list.remove(i);
		}
	}
	public void run(){
		while(flag){
			if(!msv.cm.pause){
				switch(type){
				case 1:
					changePosition3();break;
				case 2:
					changePosition2();break;
				case 3:
					changePosition3();break;
				}
			}
			try{
				Thread.sleep(20);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	private class Xian{
		float xx1;
		float xy1;
		float xx2;
		float xy2;
		float span;
		float JD;				//以上变量是线段的长度。角度。两个位置点
		float speed=7;
		public Xian(float xx,float xy,float span,float JD,int color,float speed){
			if(type == 3 ){
				this.xx1 = xx + (float)(Math.cos(JD)*80*Math.random());
				if(xx1<0 || xx1>width)xx1=xx;
				this.xy1 = xy + (float)(Math.sin(JD)*80*Math.random());
				if(xy1<0 || xy1>length)xy1=xy;
				this.span = span;
				this.JD = JD;
				this.speed = speed;
				xx2 = xx1 + (float)Math.cos(JD)*span;
				xy2 = xy1 + (float)Math.sin(JD)*span;
			}else if(type == 1 ){
				this.xx1 = xx + (float)(Math.cos(JD)*30*Math.random());
				if(xx1<0 || xx1>width)xx1=xx;
				this.xy1 = xy + (float)(Math.sin(JD)*30*Math.random());
				if(xy1<0 || xy1>length)xy1=xy;
				this.span = span;
				this.JD = JD;
				this.speed = speed;
				xx2 = xx1 + (float)Math.cos(JD)*span;
				xy2 = xy1 + (float)Math.sin(JD)*span;
			}else if(type == 2){
				this.xx1 = xx + (float)(Math.cos(JD)*120);
				this.xy1 = xy + (float)(Math.sin(JD)*120);
				this.span = span;
				this.JD = JD+(float)Math.PI;
				this.speed = speed;
				xx2 = xx1 + (float)Math.cos(JD)*span;
				xy2 = xy1 + (float)Math.sin(JD)*span;
			}
		}
//		public void move(){        //爆炸的线段速度是变化的，这里处理方法是给一个初始速度，然后
//			speed-=0.12f;						//每次移动都给予减速效果，当速度减到一定程度时，线段首先变短，然后消失
//			span-=0.48f;						//......
//			
//			xx1 +=(float)Math.cos(JD)*speed;
//			xy1 +=(float)Math.sin(JD)*speed;				//需要定义一个速度变量
//			
//			xx2 = xx1 + (float)Math.cos(JD)*span;
//			xy2 = xy1 + (float)Math.sin(JD)*span; 
//			
//		}

		
		public void move(){        //爆炸的线段速度是变化的，这里处理方法是给一个初始速度，然后
		if(type == 3 || type == 1){
			speed-=0.09f;						//每次移动都给予减速效果，当速度减到一定程度时，线段首先变短，然后消失
			span-=0.4f;						//......
			
			xx1 +=(float)Math.cos(JD)*speed;
			xy1 +=(float)Math.sin(JD)*speed;				//需要定义一个速度变量
			
			xx2 = xx1 + (float)Math.cos(JD)*span;
			xy2 = xy1 + (float)Math.sin(JD)*span; 
			if(xx1<0 || xx1>width ){						//两个用于处理碰壁的if语句
				this.JD = -JD + (float)Math.PI;
			}
			if(xy1<0 || xy1>length){
				this.JD = -JD;
			}
		}else if(type == 2){
			speed-=0.09f;						//每次移动都给予减速效果，当速度减到一定程度时，线段首先变短，然后消失
			span-=0.48f;						//......
			
			xx1 +=(float)Math.cos(JD)*speed;
			xy1 +=(float)Math.sin(JD)*speed;				//需要定义一个速度变量
			
			xx2 = xx1 + (float)Math.cos(JD)*span;
			xy2 = xy1 + (float)Math.sin(JD)*span; 
		}
		}
		
		public void drawSelf(Canvas canvas,Paint paint){
			paint.setStrokeWidth(0.8f);
			paint.setAlpha(255);
			canvas.drawLine(xx1, xy1, xx2, xy2, paint);
			
		}
	}
	public  static  Bitmap small(Bitmap bmp,float scale)    {   
        int bmpWidth=bmp.getWidth();    
        int bmpHeight=bmp.getHeight();    
        /* 产生reSize后的Bitmap对象 */  
        Matrix matrix = new Matrix();   
        matrix.postScale(scale, scale);   
        Bitmap resizeBmp = Bitmap.createBitmap(bmp,0,0,bmpWidth,    
                bmpHeight,matrix,true);    
        return resizeBmp;   
     
    } 
}
