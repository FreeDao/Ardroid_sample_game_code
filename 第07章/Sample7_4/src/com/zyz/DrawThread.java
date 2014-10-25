package com.zyz;

import static com.zyz.Constant.*;

import com.zyz.GameView;

public class DrawThread extends Thread
{
	GameView gv;
	
	public DrawThread(GameView gv)
	{
		this.gv=gv;
	}
	
	@Override
	public void run()
	{
		while(DRAW_THREAD_FLAG)
		{
			gv.repaint();
			try 
			{
				Thread.sleep(SLEEPTIME);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
}

