package com.zyz;

public class Constant 
{
	public static final float GRAVITY=0.1f;//重力系数
	public static final float RANGE=50f;//光滑核核半径
	public static final float RANGE2=RANGE * RANGE;//光滑核半径的平方
	public static final float DENSITY=1f;//平静时的密度
	public static final float PRESSURE=2f;//压力系数
	public static final float VISCOSITY=0.075f;//粘稠度系数
	public static final int SLEEPTIME=20;//线程休眠时间
	public static final int BOTTLE_X=40;//瓶子初始位置x坐标
	public static final int BOTTLE_Y=150;//瓶子初始位置y坐标
	public static final int BOTTLE_WIDTH=400;//瓶子宽度
	public static final int BOTTLE_HEIGHT=500;//瓶子高度
	
	public static boolean DRAW_THREAD_FLAG=true;//绘制线程控制位
	public static float GRAVITY_X=0;//重力x方向分量
	public static float GRAVITY_Y=0;//重力y方向分量
}
