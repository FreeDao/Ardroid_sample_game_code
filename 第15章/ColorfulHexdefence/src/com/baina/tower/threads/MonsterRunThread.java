package com.baina.tower.threads;

import java.util.HashMap;

import com.baina.tower.mapCapability.CapabilityList;
import com.baina.tower.monsters.MonsterList;
import com.baina.tower.view.GameSurfaceView;

public class MonsterRunThread extends Thread{
	public boolean pause = false;//线程暂停的标志位
	String startString="";
	public boolean runFlag = true;
	GameSurfaceView mv;
	MonsterList list ;
	CapabilityList capability_list;
	public MonsterRunThread(GameSurfaceView mv){
		this.mv = mv;
		list = mv.master_list;
		capability_list = mv.capability_list;
		this.setName("master run thread");
	}
	
	public void run(){
	
		while(runFlag){
			
			
			try{
				if(!pause)
				{
					list.run();
					capability_list.go();
				}
				
				Thread.sleep(40);
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
	}

	public boolean isFlag() {
		return runFlag;
	}

	public void setFlag(boolean flag) {
		this.runFlag = flag;
	}

	public String getString() {
		return startString;
	}
	
}
