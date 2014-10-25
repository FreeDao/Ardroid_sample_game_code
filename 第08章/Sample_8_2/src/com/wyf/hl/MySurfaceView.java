package com.wyf.hl;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import static com.wyf.hl.Map.*;

public class MySurfaceView extends SurfaceView 
implements SurfaceHolder.Callback  //ʵ���������ڻص��ӿ�
{
	MyActivity activity;
	Paint paint;//����
	LBX lbx=new LBX();	
	
	public MySurfaceView(MyActivity activity) 
	{
		super(activity);
		this.activity = activity;
		this.getHolder().addCallback(this);//�����������ڻص��ӿڵ�ʵ����
		paint = new Paint();//��������
		paint.setAntiAlias(true);//�򿪿����		
	} 

	public void onDraw(Canvas canvas)
	{		
		//���Ƶ�ͼ
		lbx.drawMap(canvas, paint);
		paint.setPathEffect(null);   
	    
	    
	}

	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		  
	}

	public void surfaceCreated(SurfaceHolder holder) {//����ʱ������
		repaint();
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

	public void surfaceDestroyed(SurfaceHolder arg0) {//����ʱ������

	}
}