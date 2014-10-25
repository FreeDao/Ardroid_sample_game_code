package com.wyf.hl;

public class Map 
{
   final static float a=50;			//正六边形的边长
   final static float h=(float) (a*Math.cos(Math.toRadians(30)));
   									//六边形切分为六个正三角形后，三角形的高
   final static float b=(float) (a*Math.sin(Math.toRadians(30)));
   									//六边形边长的一半
   final static int[][] MAP_DATA=	//由于绘制地图数组
   {
	    {0,1,0,0,0},
		{0,0,1,0,0},
		{1,0,1,1,1},
		{1,0,0,0,1},
		{0,0,0,0,1}	
   };
   
}
