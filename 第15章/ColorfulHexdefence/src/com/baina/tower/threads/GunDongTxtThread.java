package com.baina.tower.threads;

import com.baina.tower.constant.Constants;
import com.baina.tower.view.YouXiFangFaSurfaceView;

public class GunDongTxtThread extends Thread{

	YouXiFangFaSurfaceView mv;
	boolean flag = true;
	float speed = 0.5f;
	
	public GunDongTxtThread(YouXiFangFaSurfaceView mv)
	{
		this.mv = mv;
		mv.x = Constants.PMX;
		this.setName("gunthread");
	}
	
	public void run(){
	
		try{
			while(flag){
				if(mv.x < -mv.gunDongTxt.getWidth())
				{
					mv.x = Constants.PMX;
				}
				mv.x = mv.x - speed;
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
