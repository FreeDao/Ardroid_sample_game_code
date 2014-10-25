package com.baina.tower.threads;

import java.util.List;

import com.baina.tower.impleclass.Tower;
import com.baina.tower.towers.TowerList;

public class BulletRunThread extends Thread {

	public boolean flag = true;

	public boolean pause = false;//线程暂停的标志位
	TowerList tower_list;
	
	public BulletRunThread(TowerList list){
		tower_list  = list;
		this.setName("bulletrunthread");
	}
	
	
	public void run(){
		while(flag){
			if(pause)
			{
				continue;
			}
			try{
				tower_list.findMaster();//塔
				
				for(int i=0; i<tower_list.size(); i++){
					tower_list.get(i).getBullet().run();
				}
				Thread.sleep(30);
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}	
	}
	
	public boolean isFlag() {
		return flag;
	}


	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	
}
