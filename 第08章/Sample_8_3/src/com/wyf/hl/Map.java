package com.wyf.hl;

public class Map 
{
   final static float a=27;//正六边形的边长
   final static float h=(float) (a*Math.cos(Math.toRadians(30)));
   final static float b=(float) (a*Math.sin(Math.toRadians(30)));
   
   final static int[][] MAP_DATA=
   {
	   {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
	   {1,1,1,0,0,0,0,0,0,0,1,1,1,1,1,1},
	   {1,1,1,1,0,0,1,1,1,0,0,0,0,0,0,1},
	   {1,1,1,0,0,0,1,0,0,0,1,1,1,1,1,1},
	   {1,1,1,1,0,0,1,1,1,0,1,1,1,1,1,1},
	   {1,1,1,0,0,1,1,0,0,0,0,0,0,0,0,1},
	   {1,0,0,1,1,1,1,1,1,0,0,1,1,1,0,1},
	   {1,0,0,1,1,1,0,0,1,0,1,1,0,0,1,1},
	   {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
	   {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
   };
   
   static int[] source={4,1};//出发点 col,row
   
   static int[][] target={{1,6},{4,5},{13,7}};//结束点 col,row
}
