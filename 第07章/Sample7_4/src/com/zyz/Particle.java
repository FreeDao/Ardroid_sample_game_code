package com.zyz;

import static com.zyz.Constant.*;

import java.util.ArrayList;

//粒子类
public class Particle  
{
	//位置
	public float x;
	public float y;
	//速度
	public float vx;
	public float vy;
	//受力
	public float fx;
	public float fy;
	//密度
	public float density;
	//内部压力
	public float pressure;
	//邻居
	public ArrayList<Particle> neighbors;
	public int numNeighbors;
	
	public Particle(float x,float y)
	{
		this.x=x;
		this.y=y;
		vx=vy=fx=fy=0;
		neighbors=new ArrayList<Particle>();
	}
	
	//将受力作用成速度和位移
	public void move(GameView mv)
	{
		if(neighbors.size()>10)
		{
			neighbors.clear(); 
		}
		
		int d_x=mv.bottle_x-BOTTLE_X;
		int d_y=mv.bottle_y-BOTTLE_Y;
		//力累计到速度
		vx+=fx;
		vy+=fy;
		//速度累计到位移
		x+=vx;
		y+=vy;
		//如果撞墙，速度为0，如果穿过墙，则受到和超过部分相同的力的反馈
		if (x<6+d_x) vx+=6+d_x-x;
		if (y<6+d_y) vy+=6+d_y-y;
		if (x>BOTTLE_WIDTH-6+d_x) vx+=BOTTLE_WIDTH-6+d_x-x;
		if (y>BOTTLE_HEIGHT-6+d_y) vy+=BOTTLE_HEIGHT-6+d_y-y;
	}
}
