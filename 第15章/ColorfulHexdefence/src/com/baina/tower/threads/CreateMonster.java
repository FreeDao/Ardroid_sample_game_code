package com.baina.tower.threads;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.baina.tower.allactivity.MainGameActivity;
import com.baina.tower.constant.Constants;
import com.baina.tower.monsters.MonsterList;
import com.baina.tower.monsters.Monster_Circle;
import com.baina.tower.monsters.Monster_Square;
import com.baina.tower.monsters.Monster_Triangle;
import com.baina.tower.utils.LBX;
import com.baina.tower.utils.Utils;
import com.baina.tower.view.GameSurfaceView;

public class CreateMonster extends Thread{
	public boolean pause = false;			//线程暂停的标志位
	public boolean flag = true;				//线程循环标记位
	public int isFirst = 1;					//假如是恢复游戏，则第一次的时候不进行延迟
	MonsterList list;						//怪物列表引用
	GameSurfaceView mv;						//游戏主界面引用
	Bitmap creep;							//怪物图片
	public int count ;						//怪物数量
	public CreateMonster(MonsterList list, GameSurfaceView mv,Bitmap creep){
		this.list = list;
		this.mv = mv;
		this.creep = creep;
		count = mv.boShuCount;
	}
	
	public void run(){
		mv.playDongHua = true;
		//if(isFirst == 1){
			while(mv.playDongHua){
				
				Constants.sleep(20);
				if(!pause){
					mv.shanXingAngle+=1.2f;
					if(mv.shanXingAngle>=360f){
						mv.playDongHua=false;
						//mv.shanXingAngle=0;
						break;
					}
				}
			}
		//}
		while(flag){
//			if(mv.threadIsDie == true){		//怪物出尽后结束线程
//				break;
//			}
			if(count<1){							//判断怪物数量
//				mv.threadIsDie = true;				//控制线程结束
				break;
			}
			try{
				if(!pause)
				{
			if(mv.boshu%Constants.CYCLE==1)	//第二波怪物的执行方法
			{
				
					MainGameActivity.sound.playMusic(Constants.SPAWN_START, 0)  ;
					mv.drawStartDemo = true;//绘制怪物出现时的动画
					list.add(new Monster_Square(mv,creep,Constants.MASTERNUM1BLOOD+(mv.boshu-1)*Constants.INCREASE_BLOOD1));
					count--;
				
			}
				
			else if(mv.boshu%Constants.CYCLE== 2)//第三波怪物的执行方法
			{
				
					MainGameActivity.sound.playMusic(Constants.SPAWN_START, 0)  ;
					mv.drawStartDemo = true;
					list.add(new Monster_Triangle(mv,creep,Constants.MASTERNUM2BLOOD+(mv.boshu-1)*Constants.INCREASE_BLOOD2));
					count--;
				
			}
				
			else if(mv.boshu%Constants.CYCLE== 0)//第一波怪物的执行方法
			{
				
					MainGameActivity.sound.playMusic(Constants.SPAWN_START, 0)  ;
					mv.drawStartDemo = true;
					list.add(new Monster_Circle(mv,creep,Constants.MASTERNUM3BLOOD+(mv.boshu-1)*Constants.INCREASE_BLOOD3));
					count--;
				
			}
			float temp[] = LBX.getPosition(mv.game.source[1], mv.game.source[0]);
			Utils util = new Utils(2,temp[0], temp[1], 40,Color.WHITE,mv,16);
			mv.utils.add(util);
			util.start();
			}
			Thread.sleep(Constants.CREATE_TIME_SPAN);
//			if(count<1){							//判断怪物数量
////				mv.threadIsDie = true;				//控制线程结束
//				break;
//			}
			}catch(Exception e){
				e.printStackTrace();				//打印错误信息
			}
		}
		while(list.size()!=0){
			Constants.sleep(1000);
		}
		mv.threadIsDie = true;
	}
	
	public void setFlag(boolean flag) {
		this.flag = flag;
		mv.playDongHua = flag;
		mv.threadIsDie = flag;
	}
}
