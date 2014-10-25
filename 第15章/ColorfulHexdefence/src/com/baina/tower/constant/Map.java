package com.baina.tower.constant;

public class Map 
{
   public final static float a=26;//正六边形的边长
   public final static float h=(float) (a*Math.cos(Math.toRadians(30)))+2;
   public final static float b=(float) (a*Math.sin(Math.toRadians(30)));
   //12行   15列
   /**
    * 地图中的0为可以通过 1为不可以通过
    * 2代表减速 3代表加速 4代表减血5加血
    * 6789 10 11 分别代表六个方向：
    * 			6
    * 		11		7
    *		10		8	 
    * 			9
    * 
    */
   public final static int[][][] MAP_DATA=
   {
	   //0
	  {
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,0,1,0,1,0,1,0,1,0,0,1,1,1},
				{1,1,0,0,0,0,0,0,0,0,0,0,1,1,1},
				{1,1,0,1,0,1,0,1,0,1,0,0,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,0,0,1,1,1},
				{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
				{1,0,0,0,1,0,1,0,1,0,1,1,1,1,1},
				{1,0,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,0,0,0,0,0,0,0,0,0,0,1,1,1},
				{1,1,0,1,0,1,0,1,0,1,0,0,1,1,1},
				{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	   }
		,

	   //1
	   {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,1,0,0,0,0,0,0,1,0,0,1,1,1},
			{1,0,0,0,1,0,1,0,0,1,0,0,1,1,1},
			{1,0,1,0,0,0,1,0,0,1,0,0,1,1,1},
			{1,0,0,0,1,0,1,0,0,1,0,0,1,1,1},
			{1,0,1,0,0,0,1,0,0,1,0,0,1,1,1},
			{1,0,0,0,1,0,1,0,0,1,0,0,1,1,1},
			{1,0,1,0,0,0,1,0,0,1,0,0,1,1,1},
			{1,0,0,0,1,0,1,0,0,1,0,0,1,1,1},
			{1,0,1,0,0,0,1,0,0,1,0,0,1,1,1},
			{1,0,0,0,0,0,1,0,0,0,0,0,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	   },
	   //2
	   {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,0,0,0,0,0,0,0,1,1,1,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,1,1,1,1},
			{1,1,0,0,0,0,0,0,0,0,0,1,1,1,1},
			{1,1,0,0,1,1,8,7,1,1,0,0,1,1,1},
			{1,0,0,1,0,1,2,1,0,1,0,0,1,1,1},
			{1,1,0,0,1,1,0,0,1,1,0,0,1,1,1},
			{1,1,0,0,5,6,0,9,5,0,0,1,1,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,1,1,1,1},
			{1,1,1,0,0,0,0,0,0,0,1,1,1,1,1},
			{1,1,1,1,0,0,0,0,0,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	   },
	   //3
	   {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,1,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,1,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,2,2,1,1,1},
			{1,0,0,0,0,0,0,0,0,2,2,2,1,1,1},
			{1,0,0,0,0,0,0,0,0,2,2,0,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	   },
	   //4
	   {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,1,2,1,1,2,1,2,1,1,2,1,1,1,1},
			{1,1,2,2,1,2,1,1,2,1,2,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	   },
	   //第六关
	   {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,1,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,1,0,0,0,0,1,0,0,1,1,1},
			{1,0,0,11,1,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,11,1,0,0,0,0,1,0,1,1,1},
			{1,0,0,1,0,1,0,0,0,1,0,0,1,1,1},
			{1,0,0,0,1,1,0,0,0,0,1,0,1,1,1},
			{1,0,0,0,1,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,1,0,0,0,0,1,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
		   
	   },
	   {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,3,3,3,3,5,0,0,0,0,0,1,1,1},
			{1,1,1,1,1,1,1,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,5,2,2,2,2,0,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	   },
	   {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,1,1,1,0,0,0,0,0,1,1,1},
			{1,0,0,1,0,0,6,5,0,0,0,0,1,1,1},
			{1,0,0,1,0,0,6,4,0,0,0,0,1,1,1},
			{1,0,0,1,0,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,1,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,1,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,1,0,0,0,0,0,0,0,0,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	   },
	   //第九关
	   {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,1,0,1,1,0,1,1,1,0,1,0,1,1,1},
			{1,0,0,0,2,2,2,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,2,2,2,0,0,0,0,0,1,1,1},
			{1,1,0,1,1,1,0,1,1,1,0,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	   },
	   {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,1,0,0,0,0,0,1,1,1},
			{1,1,1,0,0,0,0,0,8,7,0,0,1,1,1},
			{1,0,0,0,0,0,0,9,5,6,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,7,11,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,8,7,0,0,0,0,0,0,1,1,1},
			{1,0,0,9,4,9,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,10,11,0,0,0,0,3,3,1,1,1},
			{1,0,0,0,0,0,0,0,0,3,0,3,1,1,1},
			{1,0,0,0,0,0,1,0,0,0,3,3,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	   },
	   //11
	   {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,1,1,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,1,1,4,0,0,0,0,0,1,1,1},
			{1,0,0,2,1,4,0,0,0,0,0,0,1,1,1},
			{1,0,0,2,2,0,0,0,0,0,0,0,1,1,1},
			{1,0,2,2,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,1,1,0,0,0,0,0,0,8,0,1,1,1},
			{1,1,1,4,0,0,0,0,0,1,8,0,1,1,1},
			{1,1,0,4,0,0,0,0,0,0,1,7,1,1,1},
			{1,0,0,0,0,0,0,0,1,1,0,1,1,1,1},
			{1,0,0,0,0,0,0,0,9,9,10,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
		   
	   },
	   //12
	   {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,4,4,0,1,1,1},
			{1,0,2,0,0,0,0,0,1,1,0,0,1,1,1},
			{1,0,2,1,1,1,1,0,0,0,0,0,1,1,1},
			{1,0,2,0,0,0,1,0,0,0,2,0,1,1,1},
			{1,0,0,0,0,0,0,1,1,1,1,2,1,1,1},
			{1,0,0,1,1,0,0,0,0,0,2,0,1,1,1},
			{1,0,0,4,4,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	   },
	   {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,1,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,1,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,1,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,1,0,0,1,1,1},
			{1,0,1,1,1,0,0,0,1,0,0,0,1,1,1},
			{1,0,0,0,0,1,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,6,6,6,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,1,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,1,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	   },
	   //14
	   {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,6,0,0,1,1,1},
			{1,0,0,0,1,1,1,1,2,0,1,0,1,1,1},
			{1,0,0,1,0,9,0,1,0,0,1,0,1,1,1},
			{1,0,0,1,0,1,1,8,1,0,1,0,1,1,1},
			{1,0,1,0,0,0,1,0,1,0,10,1,1,1,1},
			{1,0,1,0,0,0,0,1,0,1,0,1,1,1,1},
			{1,0,1,0,0,0,2,0,3,0,0,0,1,1,1},
			{1,0,0,1,0,0,0,0,1,0,0,0,1,1,1},
			{1,0,0,1,0,0,0,1,0,1,0,0,1,1,1},
			{1,0,0,0,1,0,0,1,0,1,0,0,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	   },
	   {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,8,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,7,0,0,0,10,0,0,0,1,1,1},
			{1,0,0,8,0,10,0,5,9,0,0,0,1,1,1},
			{1,0,0,0,9,10,0,0,0,0,0,0,1,1,1},
			{1,8,0,0,0,0,0,0,0,10,0,0,1,1,1},
			{1,0,9,9,0,9,4,0,9,10,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,6,6,6,6,6,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	   },
	   //16
	   {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,4,1,1,1,1,1},
			{1,0,0,1,0,0,1,0,0,4,1,0,1,1,1},
			{1,0,0,1,0,4,1,0,0,0,0,0,1,1,1},
			{1,0,0,1,0,0,1,0,1,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,5,1,0,0,0,1,1,1},
			{1,0,0,1,0,0,0,0,1,0,0,0,1,1,1},
			{1,0,4,1,0,1,0,0,0,0,0,0,1,1,1},
			{1,0,0,1,0,4,1,0,0,1,0,0,1,1,1},
			{1,0,0,0,0,1,0,0,4,1,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,1,0,0,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}  
	   },
	   {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,5,1,1,1,1,1},
			{1,0,0,1,1,1,1,1,1,1,5,1,1,1,1},
			{1,0,2,0,2,0,2,0,0,1,0,1,1,1,1},
			{1,0,0,1,1,1,1,1,1,1,5,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,5,1,1,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	   },
	   //18
	   {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,0,0,0,0,0,0,0,0,1,0,1,1,1},
			{1,0,1,0,0,0,1,0,0,0,1,2,1,1,1},
			{1,2,1,0,0,1,2,0,0,0,1,2,1,1,1},
			{1,2,1,0,0,1,0,1,0,0,0,0,1,1,1},
			{1,0,0,0,1,0,0,1,0,0,0,0,1,1,1},
			{1,0,0,0,1,0,0,0,1,0,0,0,1,1,1},
			{1,0,0,1,4,0,0,0,1,0,0,0,1,1,1},
			{1,0,0,1,4,0,0,0,0,1,0,0,1,1,1},
			{1,0,1,4,0,0,0,0,0,1,0,0,1,1,1},
			{1,0,1,0,0,0,0,0,5,5,5,0,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	   },
	   {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,2,0,2,0,2,0,5,5,0,0,1,1,1},
			{1,0,0,1,1,1,1,1,1,1,1,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,1,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,1,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,1,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,1,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,0,1,0,1,1,1},
			{1,0,0,0,0,0,0,0,0,5,1,0,1,1,1},
			{1,0,0,0,0,0,0,0,5,5,0,1,1,1,1},
			{1,0,0,0,0,0,0,0,5,5,5,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	   },
	   //20
	   {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,0,0,3,3,3,3,0,0,0,1,1,1},
			{1,1,1,0,0,0,1,1,1,0,0,0,1,1,1},
			{1,1,0,10,0,1,0,0,0,0,0,0,1,1,1},
			{1,1,0,10,0,1,0,0,1,1,1,0,1,1,1},
			{1,0,0,0,1,0,0,1,0,0,0,0,1,1,1},
			{1,1,0,0,1,0,0,1,0,0,0,0,1,1,1},
			{1,1,2,1,0,0,1,0,0,0,1,0,1,1,1},
			{1,1,1,2,1,0,1,0,0,0,1,0,1,1,1},
			{1,1,1,2,1,0,1,0,0,1,0,0,1,1,1},
			{1,1,1,1,2,2,0,0,0,0,1,0,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	   }
   };
   
   public static final int[][] source={
	   
	  {1,2}, {2,2}, {6,2}, {2,2},{1,4} ,  ///1=====5
	  {4,5},{1,1},{1,1},{5,1},  {1,1} ,                     ///6=====10
	  
	  {1,1},{5,5},{1,1},{6,6},{1,2},
	  {2,3},{8,5},{1,2},{1,2},{1,5}
	   
   };//出发点 col,row
   
   public static final int[][] target={
	   
	  {11,10},  {11,2}, {6,9}, {11,10},{11,7} , ////1===5
	  {10,5},{11,9},{10,9},{5,10},  {10,9}  ,                      ///6===10	  
	  
	  {10,9},{7,5},{11,1},{1,6},{11,2},
	  {11,2},{10,5},{11,1},{10,9},{11,9}
   };//结束点 col,row
   

}
