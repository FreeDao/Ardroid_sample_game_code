package com.baina.tower.impleclass;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public interface Monster {

	public void draw(Canvas canvas);
	
	public void run();
	
	public void findWay();
	public void findWay2();
	
	public void updateWay(int row , int col);//当怪走进地图的方向格子中强制更换路线
	
	public boolean isLive();
	
	public void setLive(boolean live);
	
	public int[] getLocation();//得到处理怪跳的数组
	
	public float[] getCurrentPoint();
	
	public boolean decreaseBlood(float damage);//怪减学的方法
	
	public float getMasterLength();
	
	public void setSpeedDown();
	
	public void setSpeedUp();
	
	public void setVisited(int x,int y);
	
	public boolean  isInCapability();
	
	public void  setInCapability(boolean temp);
	
}
