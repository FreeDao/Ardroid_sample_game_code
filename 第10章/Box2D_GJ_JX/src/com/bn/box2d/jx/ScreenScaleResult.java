package com.bn.box2d.jx;

enum ScreenOrien
{
	HP,  
	SP   
}

public class ScreenScaleResult
{
	public int lucX;
	public  int lucY;
	public  float ratio;
	public  ScreenOrien so;
	
	public ScreenScaleResult(int lucX,int lucY,float ratio,ScreenOrien so)
	{
		this.lucX=lucX;
		this.lucY=lucY;
		this.ratio=ratio;
		this.so=so;
	}
	
	public String toString()
	{
		return "lucX="+lucX+", lucY="+lucY+", ratio="+ratio+", "+so;
	}
}