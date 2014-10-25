package com.wyf.hl;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import static com.wyf.hl.Map.*;

public class MySurfaceView extends SurfaceView 
implements SurfaceHolder.Callback  //实现生命周期回调接口
{
	MyActivity activity;
	Paint paint;//画笔
	LBX lbx=new LBX();	
	
	public MySurfaceView(MyActivity activity) 
	{
		super(activity);
		this.activity = activity;
		this.getHolder().addCallback(this);//设置生命周期回调接口的实现者
		paint = new Paint();//创建画笔
		paint.setAntiAlias(true);//打开抗锯齿		
	} 

	public void onDraw(Canvas canvas)
	{		
		//绘制地图
		lbx.drawMap(canvas, paint);
		paint.setPathEffect(null);   
	    
	    
	}

	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		  
	}

	public void surfaceCreated(SurfaceHolder holder) {//创建时被调用
		repaint();
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

	public void surfaceDestroyed(SurfaceHolder arg0) {//销毁时被调用

	}
}