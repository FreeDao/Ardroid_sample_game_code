package com.wl.tableball.model;

import com.wl.tableball.game.GameView;

public class PlayTimeExercise1 extends PlayTime1{

	public PlayTimeExercise1(GameView gameview)
	{
		super(gameview);
		TimeGoThread timegothread=new TimeGoThread();
		timegothread.start();
	}
	
	private class TimeGoThread extends Thread
	{
		@Override
		public void run() {
			while(gameview.heroislive)
			{
				if(!gameview.isGamePause)
				{
					runTime++;
				
					index_m0=(runTime/60)%60/10;//练习模式下的10分位
					index_m1=(runTime/60)%60%10;//练习模式下的分位
					index_s0=runTime%60/10;//练习模式下的10秒位
					index_s1=runTime%60%10;//练习模式下的秒位
				}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
