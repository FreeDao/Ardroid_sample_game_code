package com.baina.tower.impleclass;

import android.graphics.Canvas;

import com.baina.tower.bullet.BulletList;

public interface Tower{
	public void draw(Canvas canvas);
	
	public void fire(Monster a);
	
	public void findMaster();
	
	public void findJD(float x,float y) ;
	
	public BulletList getBullet();
	
	public int[] getRowCol();
	
	public int getCurrentState();
	
	//���Լ������ķ���
	public void upDateSelf();
	//�������Լ�
	public void sell();
	
	public float[] getXY();
	
	public float getR();
	
	public int getCurrentUpdatePrice();
}
