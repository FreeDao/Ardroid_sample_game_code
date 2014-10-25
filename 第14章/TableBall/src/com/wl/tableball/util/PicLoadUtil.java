package com.wl.tableball.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
//处理图片
public class PicLoadUtil 
{
	public static Bitmap loadBM(Resources res,String fname)//通过IO得到了图片的bitmap
    {
    	Bitmap result=null;    	
    	try
    	{
    		InputStream in=res.getAssets().open(fname);
			int ch=0;
		    ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    while((ch=in.read())!=-1)
		    {
		      	baos.write(ch);
		    }      
		    byte[] buff=baos.toByteArray();
		    baos.close();
		    in.close();
		    result=BitmapFactory.decodeByteArray(buff, 0, buff.length);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}    	
    	return result;
    }
	
	//缩放图片的方法
	public static Bitmap scaleToFitXYRatio(Bitmap bm,float xRatio,float yRatio)//缩放图片的方法
	{
	   	float width = bm.getWidth(); //图片宽度
	   	float height = bm.getHeight();//图片高度
	   	Bitmap bmResult=Bitmap.createScaledBitmap(bm, (int)(width*xRatio), (int)(height*yRatio),true);
	   	return bmResult;
	}
	
	//缩放图片
	public static Bitmap scaleBitmap(Bitmap bm,int width,int height)//缩放图片的方法
	{
	   	Bitmap bmResult=Bitmap.createScaledBitmap(bm, width, height,true);
	   	return bmResult;
	}
	
	
	//根据行列切割图片
	public static Bitmap[][] splitPic
   (
		   int cols,//切割的行数 
		   int rows,//切割的列数    
		   Bitmap srcPic,//被切割的图片  
		   int dstWitdh,//切割后调整的目标宽度
		   int dstHeight//切割后调整的目标高度  
   ) 
   {   
	   final float width=srcPic.getWidth();
	   final float height=srcPic.getHeight();
	   
	   final int tempWidth=(int)(width/cols);
	   final int tempHeight=(int)(height/rows);
	   
	   Bitmap[][] result=new Bitmap[rows][cols];
	   
	   for(int i=0;i<rows;i++)
	   {
		   for(int j=0;j<cols;j++)
		   {
			   Bitmap tempBm=Bitmap.createBitmap(srcPic, j*tempWidth, i*tempHeight,tempWidth, tempHeight);		
			   result[i][j]=scaleToFit(tempBm,dstWitdh,dstHeight);
		   }
	   }
	   
	   return result;
    }
	
	public static Bitmap combineRec
	   (   
			   Bitmap srcPic,//被切割的图片  
			   float dstWitdh,//目标宽度
			   float dstHeight//目标高度  
	   ) 
	   {   
		   final float width=srcPic.getWidth();//原始宽度
		   final float height=srcPic.getHeight();//原始高度
		   Bitmap result[]=new Bitmap[4];
		   
		   result[0]=Bitmap.createBitmap(srcPic, 0, 0,(int)dstWitdh-4, (int)dstHeight-7);		
		   result[1]=Bitmap.createBitmap(srcPic, (int) (width-7), 0,7, (int)dstHeight-7);		
		   result[2]=Bitmap.createBitmap(srcPic, 0, (int) (height-7),(int)dstWitdh-4, 7);		
		   result[3]=Bitmap.createBitmap(srcPic, (int)width-7, (int)height-7, 7, 7);
		   Bitmap bm=Bitmap.createBitmap((int)dstWitdh, (int)dstHeight,Config.ARGB_8888 );
		   
		   Canvas canvas= new Canvas(bm);
		   Paint paint;
		   paint=new Paint();
		   paint.setAntiAlias(true);
		   
		   canvas.drawBitmap(result[3], dstWitdh-7, dstHeight-7, paint);
		   canvas.drawBitmap(result[2], 0, dstHeight-7, paint);
		   canvas.drawBitmap(result[1], dstWitdh-7, 0, paint);
		   canvas.drawBitmap(result[0], 0, 0, paint);
		   
		   return bm;
	    }
	
	 //缩放旋转图片的方法
	   public static Bitmap scaleToFit(Bitmap bm,int dstWidth,int dstHeight)//缩放图片的方法
	   {
	   	float width = bm.getWidth(); //图片宽度
	   	float height = bm.getHeight();//图片高度
	   	float wRatio=dstWidth/height;
	   	float hRatio=dstHeight/width;
	   	
	   	Matrix m1 = new Matrix(); 
	   	m1.postScale(wRatio, hRatio);
	   	Matrix m2= new Matrix();
	   	m2.setRotate(45, dstWidth/2, dstHeight/2);
	   	Matrix mz=new Matrix();
		mz.setConcat(m1, m2);
	   	
	   	Bitmap bmResult = Bitmap.createBitmap(bm, 0, 0, (int)width, (int)height, mz, true);//声明位图        	
	   	return bmResult;
	   }
}
