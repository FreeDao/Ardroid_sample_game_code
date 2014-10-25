package com.wl.tableball.game;

import static com.wl.tableball.util.Constant.*;
import static com.wl.tableball.util.Constant.xyoffset;
import java.util.ArrayList;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import com.wl.tableball.util.Constant;
import com.wl.tableball.util.DBUtil;

public class HistoryView extends SurfaceView implements SurfaceHolder.Callback
{
	TableBallActivity tableBallActivity;
	Paint paint;
	
	ArrayList<String> history1;
	ArrayList<String> history2;
	ArrayList<int[]> history;
	int position=40;
	int position_1=100;
	int position_2=100;
	//构造器
	public HistoryView(TableBallActivity tableBallActivity ) {
		super(tableBallActivity);
		this.tableBallActivity=tableBallActivity;
		this.getHolder().addCallback(this);
		paint=new Paint();
		paint.setAntiAlias(true);
		history=DBUtil.searchAll();
	}
	//绘画背景和按钮
	public void draw(Canvas canvas){
		super.draw(canvas);
		canvas.translate(Constant.screenScaleResult.lucX, Constant.screenScaleResult.lucY);
		canvas.scale(Constant.screenScaleResult.ratio, Constant.screenScaleResult.ratio);
		
		canvas.drawBitmap(TP_ARRAY[0],xyoffset[0][0], xyoffset[0][1],paint);
		canvas.drawBitmap(TP_ARRAY[28],xyoffset[28][0], xyoffset[28][1],paint);
		canvas.drawBitmap(TP_ARRAY[39],xyoffset[39][0], xyoffset[39][1],paint);
		canvas.drawBitmap(HISTORY_ARRAY[6], 130,60, paint);//计时
		canvas.drawBitmap(HISTORY_ARRAY[7], 430,60, paint);//练习
		for(int i=0;i<HIS_NUM.length-5;i++)
		{
			canvas.drawBitmap(HISTORY_ARRAY[i], 130, 110+50*i, paint);//关数
			canvas.drawBitmap(HISTORY_ARRAY[i], 430, 110+50*i, paint);//关数
		}
		for(int i=0;i<2;i++)
		{
			for(int j=0;j<6;j++)
			{
				if(DBUtil.getLock(i, j)==0)
				{
					canvas.drawBitmap(HISTORY_ARRAY[10], 
							Constant.historypostion[i][j][0], 
							Constant.historypostion[i][j][1],
							 paint);
				}
				else if(DBUtil.getTimeplay(i, j)==0)
				{
					canvas.drawBitmap(HISTORY_ARRAY[9], 
							Constant.historypostion[i][j][0], 
							Constant.historypostion[i][j][1],
							 paint);
				}
			}
		}
		
		for(int[] result:history)
		{
			int number=result[2];
			if(number==0)
			{
				continue;
			}
			
			String s=""+number;
			int length=s.length();
			int num_3=number/100;
			int num_2=number%100/10;
			int num_1=number%10;
			if(length>2)
			{
				canvas.drawBitmap(TP_NUMPIC[num_3],
					Constant.historypostion[result[0]][result[1]][0]-20, 
					Constant.historypostion[result[0]][result[1]][1], 
					 paint);
			}
			if(length>1)
			{
				canvas.drawBitmap(TP_NUMPIC[num_2],
					Constant.historypostion[result[0]][result[1]][0]+20-20, 
					Constant.historypostion[result[0]][result[1]][1], 
					 paint);
			}
			if(length>0)
			{
				canvas.drawBitmap(TP_NUMPIC[num_1],
					Constant.historypostion[result[0]][result[1]][0]+40-20, 
					Constant.historypostion[result[0]][result[1]][1], 
					 paint);
			}
			canvas.drawBitmap(HISTORY_ARRAY[8], 
					Constant.historypostion[result[0]][result[1]][0]+70-20, 
					Constant.historypostion[result[0]][result[1]][1],
					 paint);
			
		}
	}
	//触摸监听      
	public boolean onTouchEvent(MotionEvent e)
	{
		int currentNUm=e.getAction();
		float x=e.getX();
		float y=e.getY();		
		switch(currentNUm)
		{
			case MotionEvent.ACTION_DOWN:
				
				if(x>Constant.xyoffsetEvent[39][0]&&x<Constant.xyoffsetEvent[39][0]+TP_ARRAY[39].getWidth()*Constant.screenScaleResult.ratio&&//如果差模的返回按钮
						y>Constant.xyoffsetEvent[39][1]&&y<Constant.xyoffsetEvent[39][1]+TP_ARRAY[39].getHeight()*Constant.screenScaleResult.ratio)
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