package com.baina.tower.threads;

import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.baina.tower.view.GameSurfaceView;
import com.baina.tower.view.YouXiFangFaSurfaceView;

public class DrawMonsterThread extends Thread{
	SurfaceView mv;
	boolean flag = true;
	
	public DrawMonsterThread(SurfaceView mv){
		this.mv = mv;
		this.setName("drawthread");
	}
	
	public void run(){
	
		try{
			while(flag){
				if(mv instanceof GameSurfaceView)
					((GameSurfaceView)mv).repaint();
				else
					((YouXiFangFaSurfaceView)mv).repaint();
				Thread.sleep(20);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void setFlag(boolean flag){
		this.flag = flag;
	}
}
