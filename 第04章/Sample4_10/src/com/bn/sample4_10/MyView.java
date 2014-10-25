package com.bn.sample4_10;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MyView extends SurfaceView implements SurfaceHolder.Callback  //实现生命周期回调接口
{
	Sample4_10Activity activity;
	MyViewDrawThread mvdt;
	Paint paint;//画笔
	float dx;
	float dy;
	float dz;
	float x;
	float y;
	float rx;
	float ry;
	float juli2;
	float juli;
	public MyView(Sample4_10Activity activity)
	{
		super(activity);
		this.activity = activity;
		this.getHolder().addCallback(this);
		paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setTextSize(30);
		paint.setAntiAlias(true);
		mvdt=new MyViewDrawThread(this);
	}

	@Override
	public void draw(Canvas canvas) 
	{
		super.draw(canvas);
		
		canvas.drawBitmap(activity.shang, 0, 0,paint);
		canvas.drawBitmap(activity.yuan, 0, 0,paint);
		canvas.drawBitmap(activity.zuo, 0, 0,paint);
		y=dy*34;
		if(y>170)y=170;
		if(y<-170)y=-170;
		canvas.drawBitmap(activity.qiuzuo, 0, -y,paint);
		x=dx*34;
		if(x>170)x=170;
		if(x<-170)x=-170;
		canvas.drawBitmap(activity.qiushang, x,0,paint);
		juli=(float) Math.sqrt((dx*34)*(dx*34)+(dy*34)*(dy*34));
		juli2=juli/170;
		if(juli2<=1)
		{
			rx=(dx*34)/170;
			ry=(dy*34)/170;
			
		}else
		{
			if(dx>0)
			{
				rx=(float) Math.sqrt(dx*dx/(dx*dx+dy*dy));
			}
			else
			{
				rx=-(float) Math.sqrt(dx*dx/(dx*dx+dy*dy));
			}
			
			ry=dy/dx*rx;                               	
		}
		
		canvas.drawBitmap(activity.qiuzhong, rx*110, -ry*110,paint);
		
		canvas.drawBitmap(activity.xian, 0, 0,paint);
		
		canvas.drawText("x轴 : "+dx, 0, 510, paint);
		canvas.drawText("y轴 : "+dy, 0, 540, paint);
		canvas.drawText("z轴 : "+dz, 0, 570, paint);
		canvas.drawText(activity.str1, 0, 600, paint);
		canvas.drawText(activity.str2, 0, 630, paint);
		canvas.drawText(activity.str3, 0, 660, paint);
		canvas.drawText(activity.str4, 0, 690, paint);
		canvas.drawText(activity.str5, 0, 720, paint);
		
	}

	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		
	}

	public void surfaceCreated(SurfaceHolder holder) 
	{
		
		mvdt.start();
	}

	public void surfaceDestroyed(SurfaceHolder arg0) 
	{
		
	}
}