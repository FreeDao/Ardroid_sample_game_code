package com.wyf.hl;

public class Map 
{
   final static float a=50;			//�������εı߳�
   final static float h=(float) (a*Math.cos(Math.toRadians(30)));
   									//�������з�Ϊ�����������κ������εĸ�
   final static float b=(float) (a*Math.sin(Math.toRadians(30)));
   									//�����α߳���һ��
   final static int[][] MAP_DATA=	//���ڻ��Ƶ�ͼ����
   {
	    {0,1,0,0,0},
		{0,0,1,0,0},
		{1,0,1,1,1},
		{1,0,0,0,1},
		{0,0,0,0,1}	
   };
   
}
