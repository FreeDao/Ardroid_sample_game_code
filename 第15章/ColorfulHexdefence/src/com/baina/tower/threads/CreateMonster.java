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
	public boolean pause = false;			//�߳���ͣ�ı�־λ
	public boolean flag = true;				//�߳�ѭ�����λ
	public int isFirst = 1;					//�����ǻָ���Ϸ�����һ�ε�ʱ�򲻽����ӳ�
	MonsterList list;						//�����б�����
	GameSurfaceView mv;						//��Ϸ����������
	Bitmap creep;							//����ͼƬ
	public int count ;						//��������
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
//			if(mv.threadIsDie == true){		//�������������߳�
//				break;
//			}
			if(count<1){							//�жϹ�������
//				mv.threadIsDie = true;				//�����߳̽���
				break;
			}
			try{
				if(!pause)
				{
			if(mv.boshu%Constants.CYCLE==1)	//�ڶ��������ִ�з���
			{
				
					MainGameActivity.sound.playMusic(Constants.SPAWN_START, 0)  ;
					mv.drawStartDemo = true;//���ƹ������ʱ�Ķ���
					list.add(new Monster_Square(mv,creep,Constants.MASTERNUM1BLOOD+(mv.boshu-1)*Constants.INCREASE_BLOOD1));
					count--;
				
			}
				
			else if(mv.boshu%Constants.CYCLE== 2)//�����������ִ�з���
			{
				
					MainGameActivity.sound.playMusic(Constants.SPAWN_START, 0)  ;
					mv.drawStartDemo = true;
					list.add(new Monster_Triangle(mv,creep,Constants.MASTERNUM2BLOOD+(mv.boshu-1)*Constants.INCREASE_BLOOD2));
					count--;
				
			}
				
			else if(mv.boshu%Constants.CYCLE== 0)//��һ�������ִ�з���
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
//			if(count<1){							//�жϹ�������
////				mv.threadIsDie = true;				//�����߳̽���
//				break;
//			}
			}catch(Exception e){
				e.printStackTrace();				//��ӡ������Ϣ
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
