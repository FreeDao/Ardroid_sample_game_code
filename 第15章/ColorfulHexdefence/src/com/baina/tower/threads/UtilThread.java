package com.baina.tower.threads;

import com.baina.tower.view.GameSurfaceView;

public class UtilThread extends Thread{
	GameSurfaceView gameSurfaceView;
	int type;
	public UtilThread(GameSurfaceView gameSurfaceView,int type) {
		// TODO Auto-generated constructor stub
		this.gameSurfaceView = gameSurfaceView;
		this.type = type;
		this.setName("utilthread");
	}

	public void run(){
		while(true){
			try {
				Thread.sleep(2600);
			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(type==1){gameSurfaceView.boomTM();break;}
			
			if(type==2){gameSurfaceView.gameComingToOver();break;}
			
			if(type==3){gameSurfaceView.gameOver();break;}
		}
	}
}
