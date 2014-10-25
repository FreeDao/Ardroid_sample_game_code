package com.wl.tableball.model;

import org.jbox2d.dynamics.Body;

import android.graphics.Bitmap;

import com.wl.tableball.game.GameView;
import com.wl.tableball.util.Constant;

public class FalshHole extends Hole{
	
	int timeDeadSpan;//���պϣ��������õ�ʱ�䣬��λΪ��
	int timeLiveSpan;//���򿪣������õ�ʱ�䣬��λΪ��
	public boolean isFlashDead=true;//�ǲ��ǿ������Ķ�
	FalshHole(Body body,Bitmap bitmap,int timeDeadSpan,int timeLiveSpan,GameView gameview)
	{
		super(body,bitmap,gameview);
		this.timeDeadSpan=timeDeadSpan;
		this.timeLiveSpan=timeLiveSpan;
		//����һ����ͼ��������ײ��־λ���߳�
		FlashThread flashThread=new FlashThread();
		flashThread.start();
	}
	
	//��ͼ��������ײ��־λ���߳�
	private class FlashThread extends Thread{
		
		@Override
		public void run() {
			while(gameview.heroislive)
			{
				//����ֹͣ�Ͳ��ٽ��п�����
				if(!gameview.isGamePause)
				{
					isFlashDead=true;
				
					//���л�ͼ
					for(int i=1;i<Constant.TRUE_FLASHHOLE.length*2-1;i++)
					{
						while(gameview.isGamePause)
						{
							try {
								Thread.sleep(1000);//�ڰ�����ͣʱ�Ͷ��������ʹ���պϻ�������
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
						bitmap=Constant.TRUE_FLASHHOLE[Math.abs(Constant.TP_FLASHHOLE.length-1-i)];//��ͼ
						try {
							Thread.sleep(timeDeadSpan*1000/(Constant.TRUE_FLASHHOLE.length*2));//���ú����˯��ʱ��
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					isFlashDead=false;
					bitmap=Constant.TRUE_FLASHHOLE[13];//����ͼ
					
					try {
						Thread.sleep(timeLiveSpan*1000);//˯��
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				else if(gameview.isGamePause)//��������ͣ��ť��ʱ�����˯��
				{
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
