package com.wl.tableball.game;


import com.wl.tableball.util.Constant;
import com.wl.tableball.util.DBUtil;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import static com.wl.tableball.util.Constant.*;

public class NextView extends SurfaceView implements SurfaceHolder.Callback
{
	TableBallActivity tableBallActiviy;
	Paint paint;

	public NextView(TableBallActivity tableBallActiviy ) {
		super(tableBallActiviy);
		this.tableBallActiviy=tableBallActiviy;
		this.getHolder().addCallback(this);
		paint=new Paint();
		paint.setAntiAlias(true);
	}

	public void draw(Canvas canvas){
		super.draw(canvas);
		canvas.translate(Constant.screenScaleResult.lucX, Constant.screenScaleResult.lucY);
		canvas.scale(Constant.screenScaleResult.ratio, Constant.screenScaleResult.ratio);
		
		canvas.drawBitmap(TP_ARRAY[0],xyoffset[0][0], xyoffset[0][1],paint);
		canvas.drawBitmap(TP_ARRAY[32],xyoffset[32][0], xyoffset[32][1],paint);
		canvas.drawBitmap(TP_ARRAY[14], xyoffset[14][0], xyoffset[14][1], paint);
		canvas.drawBitmap(TP_ARRAY[12], xyoffset[12][0], xyoffset[12][1], paint);
		canvas.drawBitmap(TP_ARRAY[13], xyoffset[13][0], xyoffset[13][1], paint);
		
	}
	public boolean onTouchEvent(MotionEvent e)
	{
		int currentNUm=e.getAction();
		float x=e.getX();
		float y=e.getY();		
		switch(currentNUm)
		{
			case MotionEvent.ACTION_DOWN:
				
				if(x>xyoffsetEvent[14][0]&&x<xyoffsetEvent[14][0]+TP_ARRAY[14].getWidth()*Constant.screenScaleResult.ratio&&//如果触摸的是重新开始
				   y>xyoffsetEvent[14][1]&&y<xyoffsetEvent[14][1]+TP_ARRAY[14].getHeight()*Constant.screenScaleResult.ratio)
				{
					tableBallActiviy.hd.sendEmptyMessage(0);
				}
				else if(x>xyoffsetEvent[12][0]&&x<xyoffsetEvent[12][0]+TP_ARRAY[12].getWidth()*Constant.screenScaleResult.ratio&&//如果触摸的是返回主界面
						y>xyoffsetEvent[12][1]&&y<xyoffsetEvent[12][1]+TP_ARRAY[12].getHeight()*Constant.screenScaleResult.ratio)
				{
					tableBallActiviy.hd.sendEmptyMessage(1);
				}
				else if(x>xyoffsetEvent[13][0]&&x<xyoffsetEvent[13][0]+TP_ARRAY[13].getWidth()*Constant.screenScaleResult.ratio&&//如果触摸的是下一关
						y>xyoffsetEvent[13][1]&&y<xyoffsetEvent[13][1]+TP_ARRAY[13].getHeight()*Constant.screenScaleResult.ratio)
				{
					if(Constant.LEVEL==5)
					{
						tableBallActiviy.hd.sendEmptyMessage(1);
					}
					else
					{
						if(DBUtil.getLock(Constant.PLAY_MODEL, Constant.LEVEL+1)==1)
						{
							Constant.LEVEL=Constant.LEVEL+1;
							tableBallActiviy.hd.sendEmptyMessage(0);
						}
					}
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
		Constant.from_nextview=true;
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
