package com.zyz;

import static com.zyz.Constant.*;

import java.util.ArrayList;

//������
public class Particle  
{
	//λ��
	public float x;
	public float y;
	//�ٶ�
	public float vx;
	public float vy;
	//����
	public float fx;
	public float fy;
	//�ܶ�
	public float density;
	//�ڲ�ѹ��
	public float pressure;
	//�ھ�
	public ArrayList<Particle> neighbors;
	public int numNeighbors;
	
	public Particle(float x,float y)
	{
		this.x=x;
		this.y=y;
		vx=vy=fx=fy=0;
		neighbors=new ArrayList<Particle>();
	}
	
	//���������ó��ٶȺ�λ��
	public void move(GameView mv)
	{
		if(neighbors.size()>10)
		{
			neighbors.clear(); 
		}
		
		int d_x=mv.bottle_x-BOTTLE_X;
		int d_y=mv.bottle_y-BOTTLE_Y;
		//���ۼƵ��ٶ�
		vx+=fx;
		vy+=fy;
		//�ٶ��ۼƵ�λ��
		x+=vx;
		y+=vy;
		//���ײǽ���ٶ�Ϊ0���������ǽ�����ܵ��ͳ���������ͬ�����ķ���
		if (x<6+d_x) vx+=6+d_x-x;
		if (y<6+d_y) vy+=6+d_y-y;
		if (x>BOTTLE_WIDTH-6+d_x) vx+=BOTTLE_WIDTH-6+d_x-x;
		if (y>BOTTLE_HEIGHT-6+d_y) vy+=BOTTLE_HEIGHT-6+d_y-y;
	}
}
