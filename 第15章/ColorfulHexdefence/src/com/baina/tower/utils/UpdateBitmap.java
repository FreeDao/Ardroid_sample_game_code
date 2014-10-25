package com.baina.tower.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;

public class UpdateBitmap {

	 //处理图片的亮度
	 public static Bitmap liangDu(Bitmap bitmap,float f)
	 {
		 Bitmap show = bitmap; //这就是原始的图片
		 int wi =show.getWidth(); //得到宽度
		 int he =show.getHeight(); //得到高度
		 Bitmap bmp =Bitmap.createBitmap(wi, he,Bitmap.Config.ARGB_8888); 
		 //创建一个相同尺寸的可变的位图区,用于绘制调色后的图片
		 Canvas canvas = new Canvas(bmp); //得到画笔对象
		 Paint paint = new Paint(); //新建paint
		 paint.setAntiAlias(true); //设置抗锯齿,也即是边缘做平滑处理
		 ColorMatrix cm1=new ColorMatrix(); //用于颜色变换的矩阵，android 位图颜色变化处理主要是靠该对象完成 
		 ColorMatrix cm3=new ColorMatrix(); 
		 cm1.reset(); //设为默认值

		 //f 表示亮度比例，取值小于1，表示亮度减弱，否则亮度增强

		 cm3.reset(); 
		cm3.setScale(f, f, f, 1); //红、绿、蓝三分量按相同的比例,最后一个参数1表示透明度不做变化，此函数详细说明参考 android doc
		 cm1.postConcat(cm3); //效果叠加
		 paint.setColorFilter(new ColorMatrixColorFilter(cm1));//设置颜色变换效果
		 canvas.drawBitmap(show,0, 0, paint); //将颜色变化后的图片输出到新创建的位图区
		 return bmp; //返回新的位图，也即调色处理后的图片
	 }
}