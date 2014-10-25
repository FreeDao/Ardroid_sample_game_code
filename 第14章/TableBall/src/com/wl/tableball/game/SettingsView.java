package com.wl.tableball.game;

import com.wl.tableball.util.Constant;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import static com.wl.tableball.util.Constant.*;

public class SettingsView extends SurfaceView implements SurfaceHolder.Callback
{
	TableBallActivity tableBallActivity;
	Paint paint;

	public SettingsView(TableBallActivity tableBallActivity ) {
		super(tableBallActivity);
		this.tableBallActivity=tableBallActivity;
		this.getHolder().addCallback(this);
		paint=new Paint();
		paint.setAntiAlias(true);
	}

	public void draw(Canvas canvas){
		super.draw(canvas);
		canvas.translate(Constant.screenScaleResult.lucX, Constant.screenScaleResult.lucY);
		canvas.scale(Constant.screenScaleResult.ratio, Constant.screenScaleResult.ratio);
		
		canvas.drawBitmap(TP_ARRAY[0],xyoffset[0][0], xyoffset[0][1],paint);
		canvas.drawBitmap(TP_ARRAY[23],xyoffset[23][0], xyoffset[23][1],paint);
		canvas.drawBitmap(TP_ARRAY[24],xyoffset[24][0], xyoffset[24][1],paint);
		canvas.drawBitmap(TP_ARRAY[25],xyoffset[25][0], xyoffset[25][1],paint);
		if(!YINYUE_CLOSE)//如果音乐开
		{
			canvas.drawBitmap(TP_ARRAY[26],xyoffset[26][0], xyoffset[26][1],paint);
		}
		else
		{
			canvas.drawBitmap(TP_ARRAY[27],xyoffset[26][0], xyoffset[26][1],paint);
		}
		if(YINXIAO_OPEN)//如果音效打开
		{
			canvas.drawBitmap(TP_ARRAY[26],xyoffset[26][0], xyoffset[26][1]-(xyoffset[23][1]-xyoffset[24][1]),paint);
		}
		else
		{
			canvas.drawBitmap(TP_ARRAY[27],xyoffset[26][0], xyoffset[26][1]-(xyoffset[23][1]-xyoffset[24][1]),paint);
		}
		if(ZHENDONG_OPEN)//如果震动打开
		{
			canvas.drawBitmap(TP_ARRAY[26],xyoffset[26][0], xyoffset[26][1]-2*(xyoffset[23][1]-xyoffset[24][1]),paint);
		}
		else
		{
			canvas.drawBitmap(TP_ARRAY[27],xyoffset[26][0], xyoffset[26][1]-2*(xyoffset[23][1]-xyoffset[24][1]),paint);
		}
		
		canvas.drawBitmap(TP_ARRAY[19],xyoffset[19][0], xyoffset[19][1],paint);
		
	}
	public boolean onTouchEvent(MotionEvent e)
	{
		int currentNUm=e.getAction();
		float x=e.getX();
		float y=e.getY();		
		switch(currentNUm)
		{
			case MotionEvent.ACTION_DOWN:
				if(x>xyoffsetEvent[26][0]&&x<xyoffsetEvent[26][0]+TP_ARRAY[26].getWidth()*Constant.screenScaleResult.ratio&&//如果音乐打开按钮
					y>xyoffsetEvent[26][1]&&y<xyoffsetEvent[26][1]+TP_ARRAY[26].getHeight()*Constant.screenScaleResult.ratio)
				{
					if(!Constant.YINYUE_CLOSE)//如果音乐开
					{
						this.tableBallActivity.soundutil.stop_bg_sound();
						Constant.bg_music_sound=false;
					}
					else if(Constant.YINYUE_CLOSE)//如果音乐关
					{
						this.tableBallActivity.soundutil.play_bg_sound();
						Constant.bg_music_sound=true;
					}
					this.repaint();
				}
				else if(x>xyoffsetEvent[26][0]&&x<xyoffsetEvent[26][0]+TP_ARRAY[26].getWidth()*Constant.screenScaleResult.ratio&&//如果音效打开按钮
					y>xyoffsetEvent[26][1]-(xyoffsetEvent[23][1]-xyoffsetEvent[24][1])&&y<xyoffsetEvent[26][1]-(xyoffsetEvent[23][1]-xyoffsetEvent[24][1])+TP_ARRAY[26].getHeight()*Constant.screenScaleResult.ratio)
				{
					YINXIAO_OPEN=!YINXIAO_OPEN;
					this.repaint();
				}
				else if(x>xyoffsetEvent[26][0]&&x<xyoffsetEvent[26][0]+TP_ARRAY[26].getWidth()*Constant.screenScaleResult.ratio&&//如果震动打开按钮
					y>xyoffsetEvent[26][1]-2*(xyoffsetEvent[23][1]-xyoffsetEvent[24][1])&&y<xyoffsetEvent[26][1]-2*(xyoffsetEvent[23][1]-xyoffsetEvent[24][1])+TP_ARRAY[26].getHeight()*Constant.screenScaleResult.ratio)
				{
					ZHENDONG_OPEN=!ZHENDONG_OPEN;
					this.repaint();
				}
				else if(x>xyoffsetEvent[19][0]&&x<xyoffsetEvent[19][0]+TP_ARRAY[19].getWidth()*Constant.screenScaleResult.ratio&&//如果差模的是返回按钮
					y>xyoffsetEvent[19][1]&&y<xyoffsetEvent[19][1]+TP_ARRAY[19].getHeight()*Constant.screenScaleResult.ratio)
				{
					tableBallActivity.hd.sendEmptyMessage(1);
				}
			break;
		}
		return true;
	}
	
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Canvas canvas=holder.lockCanvas();
		try
		{
			synchronized(holder)
			{
				draw(canvas);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(canvas!=null)
			{
				holder.unlockCanvasAndPost(canvas);
			}
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		
	}
	public void repaint()
	{
		SurfaceHolder holder=this.getHolder();
		Canvas canvas = holder.lockCanvas();//获取画布
		try{
			synchronized(holder){
				draw(canvas);//绘制 
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