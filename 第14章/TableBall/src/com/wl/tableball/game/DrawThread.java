package com.wl.tableball.game;

public class DrawThread extends Thread{
	GameView gameview;
	//¹¹ÔìÆ÷
	public DrawThread(GameView gameview)
	{
		this.gameview=gameview;
	}
	@Override
	public void run() {
		
		//int i=0;
		while(gameview.DRAW_THREAD_FLAG)
		{
			if(!gameview.isGamePause)
			{
				gameview.repaint();
			}
			
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		gameview.DRAW_THREAD_FLAG=true;
	}
}
