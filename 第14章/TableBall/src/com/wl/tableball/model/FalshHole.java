package com.wl.tableball.model;

import org.jbox2d.dynamics.Body;

import android.graphics.Bitmap;

import com.wl.tableball.game.GameView;
import com.wl.tableball.util.Constant;

public class FalshHole extends Hole{
	
	int timeDeadSpan;//洞闭合，不起作用的时间，单位为秒
	int timeLiveSpan;//洞打开，起作用的时间，单位为秒
	public boolean isFlashDead=true;//是不是可以闪的洞
	FalshHole(Body body,Bitmap bitmap,int timeDeadSpan,int timeLiveSpan,GameView gameview)
	{
		super(body,bitmap,gameview);
		this.timeDeadSpan=timeDeadSpan;
		this.timeLiveSpan=timeLiveSpan;
		//开启一个换图，控制碰撞标志位的线程
		FlashThread flashThread=new FlashThread();
		flashThread.start();
	}
	
	//换图，控制碰撞标志位的线程
	private class FlashThread extends Thread{
		
		@Override
		public void run() {
			while(gameview.heroislive)
			{
				//按了停止就不再进行控制了
				if(!gameview.isGamePause)
				{
					isFlashDead=true;
				
					//进行换图
					for(int i=1;i<Constant.TRUE_FLASHHOLE.length*2-1;i++)
					{
						while(gameview.isGamePause)
						{
							try {
								Thread.sleep(1000);//在按了暂停时就定在这里，以使洞闭合画面连续
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
						bitmap=Constant.TRUE_FLASHHOLE[Math.abs(Constant.TP_FLASHHOLE.length-1-i)];//换图
						try {
							Thread.sleep(timeDeadSpan*1000/(Constant.TRUE_FLASHHOLE.length*2));//设置合理的睡眠时间
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					isFlashDead=false;
					bitmap=Constant.TRUE_FLASHHOLE[13];//洞的图
					
					try {
						Thread.sleep(timeLiveSpan*1000);//睡眠
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				else if(gameview.isGamePause)//当按了暂停按钮的时侯进行睡眠
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
