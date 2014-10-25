package com.wl.tableball.model;


import android.graphics.Canvas;
import android.graphics.Paint;

import com.wl.tableball.game.GameView;
import com.wl.tableball.util.Constant;

public class PlayTime1 {
	GameView gameview;
	int runTime=-1;
	
	String timeString;
	int index_m0;
	int index_m1;
	int index_s0;
	int index_s1;
	
	public PlayTime1(GameView gameview)
	{
		this.gameview=gameview;
	}
	
	public void drawself(Canvas canvas,Paint paint)
	{
		canvas.drawBitmap(Constant.TP_ARRAY[37], 600, 5, paint);
		canvas.drawBitmap(Constant.TP_NUMGAME[index_m1], 685, 5, paint);
		canvas.drawBitmap(Constant.TP_ARRAY[38], 710, 5, paint);
		canvas.drawBitmap(Constant.TP_NUMGAME[index_s0], 735, 5, paint);
		canvas.drawBitmap(Constant.TP_NUMGAME[index_s1], 760, 5, paint);
	}
	
	public int getRunTime()
	{
		return this.runTime;
	}
}
