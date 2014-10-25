package com.wl.tableball.model;

import com.wl.tableball.game.GameView;

public class PlayTimeCount1 extends PlayTime1{

	int countTime=1*60;//计时的时间，用1*60,1是分，60是秒
	public PlayTimeCount1(GameView gameview)
	{
		super(gameview);
		TimeGoThread timegothread=new TimeGoThread();
		timegothread.start();
	}
	
	private class TimeGoThread extends Thread
	{
		@Override
		public void run() {
			while(runTime<countTime&&gameview.heroislive)
			{
				if(!gameview.isGamePause)
				{
					runTime++;
				
					index_m0=((countTime-runTime)/60)%60/10;//练习模式下的10分位
					index_m1=((countTime-runTime)/60)%60%10;//练习模式下的分位
					index_s0=(countTime-runTime)%60/10;//练习模式下的10秒位
					index_s1=(countTime-runTime)%60%10;//练习模式下的秒位
				}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(runTime>=countTime)
			{
				gameview.heroislive=false;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				gameview.DRAW_THREAD_FLAG=false;//gameview 画的线程停止
				PlayTimeCount1.this.gameview.tableBallActivity.hd.sendEmptyMessage(2);
			}
		}
	}
}
