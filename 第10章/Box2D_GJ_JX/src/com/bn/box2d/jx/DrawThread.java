package com.bn.box2d.jx;
import static com.bn.box2d.jx.Constant.*;
//ªÊ÷∆œﬂ≥Ã
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
				Thread.sleep(100);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
}
