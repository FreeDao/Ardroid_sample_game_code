package com.baina.tower.constant;

public class Constants {

	public static int PMX = 480;
	public static int PMY = 800;
	
	public static int PMX1 = 480;
	public static int PMY1 = 800;
	
	public static int LOX = 0;
	public static int LOY = 0;
	public static float RADIO = 1.0f;
	
	public static final int FINGERTOTARGET = 22*2;//两个六边形的高 
	public static final int BUTTON_TOWER_LENGTH = 64;
	public static final int BUTTON_TOWER_POSITION_X=10;
	public static final int BUTTON_TOWER_POSITION_Y=400;	
	
	public static final int[] TOWER1_R = new int[]{60,120,140,160};
	public static final int[] TOWER2_R = new int[]{80,140,160,180};
	public static final int[] TOWER3_R = new int[]{80,140,160,180};
	public static final int[] TOWER4_R = new int[]{40,50,60,70};

	public static final int CREATE_TIME_SPAN = 1200;
	public static final int MASTER_COUNT= 10;
	public static final int MASTER1_UNIT_SPEED = 20;
	public static final float MASTER1_BODY_WIDTH = 15;	
	
	
	public static final int TOWER1_SHOOTTIME =8;//塔发射子弹的速度
	public static final int TOWER2_SHOOTTIME =15;
	public static final int TOWER4_SHOOTTIME =40;	
	public static final int TOWER3_SHOOTTIME =25;	
	
	public static final int TOWER_BUTTON_SPAN=70;
	
	//音乐编号
	public static final int BUTTON_PRESS = 0  ;
	public static final int BGMUSIC_STOP = 1 ;
	public static final int TOWER_PICKUP = 2  ;
	public static final int TOWER_PLACE = 3  ;
	public static final int SPAWN_START = 4	 ;
	public static final int sf_creep_die_0 = 5;
	public static final int sf_creep_die_1 = 6;
	public static final int sf_creep_die_2 = 7;
	public static final int sf_game_over = 8;
	public static final int sf_rocket_launch = 9;
	public static final int sf_rocket_hit = 10;
	public static final int sf_swish =11;
	public static final int sf_minigun_soft = 12;
	public static final int jiguang =13;
	public static final int targetbaozha =14;
	public static final int vectory =15;
	public static final int towerupdate =16;

	//子弹的伤害
	public static int BULLETNUMBER1DAMAGE[] = new int[]{10,20,30,40};
	public static int BULLETNUMBER2DAMAGE[] = new int[]{10,20,40,80};
	public static int BULLETNUMBER3DAMAGE [] = new int[]{20,40,60,80};
	public static int BULLETNUMBER4DAMAGE [] = new int[]{2,4,6,8} ;
	
	//怪的血量
	public static int MASTERNUM1BLOOD = 30;
	public static int  MASTERNUM2BLOOD = 35;
	public static int MASTERNUM3BLOOD = 45;
	
	public static int INCREASE_BLOOD1 = 30;
	public static int INCREASE_BLOOD2 = 35;
	public static int INCREASE_BLOOD3 = 45;
	
	//每四波怪进行一循环
	public static final int CYCLE = 3;

	//杀死不同的怪所得到的分数
	public static final int KILLMASTER1GETSCORE = 15;
	public static final int KILLMASTER2GETSCORE = 20;
	public static final int KILLMASTER3GETSCORE = 30;
	
	//点击一个炮所消耗的分数
	public static final int PUTTOWER1CONSUMESCORE = 15;
	public static final int PUTTOWER2CONSUMESCORE = 25;
	public static final int PUTTOWER3CONSUMESCORE = 35;
	public static final int PUTTOWER4CONSUMESCORE = 50;
	
	//炮升级所需的钱数
	public static int[] UPDATETOWER1 = new int[]{0,15,40,70};
	public static int[] UPDATETOWER2 = new int[]{0,50,100,240};
	public static int[] UPDATETOWER3 = new int[]{0,60,100,240};	
	public static int[] UPDATETOWER4 = new int[]{0,75,100,240};	

	
	//炮升级后，假如我们卖掉时的价钱
	//tower1
	public static int[] TOWER1CURRENTPRICE = new int[]{10,15,20,25};
	//tower2
	public static int[] TOWER2CURRENTPRICE = new int[]{15,20,25,30};
	//tower3
	public static int[] TOWER3CURRENTPRICE = new int[]{20,25,30,35};
	//tower4
	public static int[] TOWER4CURRENTPRICE = new int[]{25,30,35,40};
	//线程睡眠一段时间的方法
	public static void sleep(long time)
	{
		try
		{
			Thread.sleep(time);
		} 
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
