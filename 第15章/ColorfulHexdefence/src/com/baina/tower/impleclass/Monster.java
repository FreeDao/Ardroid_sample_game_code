package com.baina.tower.impleclass;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public interface Monster {

	public void draw(Canvas canvas);
	
	public void run();
	
	public void findWay();
	public void findWay2();
	
	public void updateWay(int row , int col);//�����߽���ͼ�ķ��������ǿ�Ƹ���·��
	
	public boolean isLive();
	
	public void setLive(boolean live);
	
	public int[] getLocation();//�õ��������������
	
	public float[] getCurrentPoint();
	
	public boolean decreaseBlood(float damage);//�ּ�ѧ�ķ���
	
	public float getMasterLength();
	
	public void setSpeedDown();
	
	public void setSpeedUp();
	
	public void setVisited(int x,int y);
	
	public boolean  isInCapability();
	
	public void  setInCapability(boolean temp);
	
}
