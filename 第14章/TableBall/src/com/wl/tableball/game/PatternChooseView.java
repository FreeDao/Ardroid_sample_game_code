package com.wl.tableball.game;

import com.wl.tableball.util.Constant;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import static com.wl.tableball.util.Constant.*;

public class PatternChooseView extends SurfaceView implements SurfaceHolder.Callback
{
	TableBallActivity tableBallActivity;
	Paint paint;
	boolean moshi=false;

	public PatternChooseView(TableBallActivity tableBallActivity ) {
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
		//绘制名字
		canvas.drawBitmap(TP_ARRAY[29], xyoffset[29][0], xyoffset[29][1],paint);
		canvas.drawBitmap(TP_ARRAY[17],xyoffset[17][0], xyoffset[17][1],paint);
		canvas.drawBitmap(TP_ARRAY[18],xyoffset[18][0], xyoffset[18][1],paint);
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
				
				if(x>xyoffsetEvent[17][0]&&x<xyoffsetEvent[17][0]+TP_ARRAY[17].getWidth()*Constant.screenScaleResult.ratio&&//如果差模的是限时模式
					y>xyoffsetEvent[17][1]&&y<xyoffsetEvent[17][1]+TP_ARRAY[17].getHeight()*Constant.screenScaleResult.ratio)
				{
					Constant.PLAY_MODEL=Constant.PLAY_MODEL1;
					tableBallActivity.hd.sendEmptyMessage(6);//去选关界面
				}
				else if(x>xyoffsetEvent[18][0]&&x<xyoffsetEvent[18][0]+TP_ARRAY[18].getWidth()*Constant.screenScaleResult.ratio&&//如果差模的是练习模式
					y>xyoffsetEvent[18][1]&&y<xyoffsetEvent[18][1]+TP_ARRAY[18].getHeight()*Constant.screenScaleResult.ratio)
				{
					Constant.PLAY_MODEL=Constant.PLAY_MODEL2;
					tableBallActivity.hd.sendEmptyMessage(6);//去选关界面
				}
				else if(x>xyoffsetEvent[19][0]&&x<xyoffsetEvent[19][0]+TP_ARRAY[19].getWidth()*Constant.screenScaleResult.ratio&&//如果差模的是返回按钮
					y>xyoffsetEvent[19][1]&&y<xyoffsetEvent[19][1]+TP_ARRAY[19].getHeight()*Constant.screenScaleResult.ratio)
				{
					tableBallActivity.hd.sendEmptyMessage(1);//返回主界面
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
}
