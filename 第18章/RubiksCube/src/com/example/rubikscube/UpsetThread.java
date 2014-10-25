package com.example.rubikscube;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.rubikscube.util.Constant;

import static com.example.rubikscube.util.Constant.*;

public class UpsetThread extends Thread{
	CubesControl cc;
	TimeThread tt;
	ExecutorService threadPool=Executors.newFixedThreadPool(1);
	
	public UpsetThread(CubesControl cc, TimeThread tt) {
		this.cc = cc;
		this.tt = tt;
	}

	public void run()
	{
		if(tt!=null)
		{
			tt.timestop();
		}
		for(int i=0;i<30;i++)
		{
			if(!bwait)
			{
				int ll = (int)(Math.random()*18);
				if(Constant.ORDER == 2)
				{
					if(ll==2||ll==3||ll==8||ll==9||ll==14||ll==15)
						continue;
				}
				new RotateThread(ll,cc).run();
			}
			else
			{
				i--;
			}
		}

		if(tt!=null)
		{
			tt.restart();
		}
		
	}
}
