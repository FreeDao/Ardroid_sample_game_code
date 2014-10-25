package com.wl.tableball.util;


import org.jbox2d.common.Vec2;

import android.content.res.Resources;
import android.graphics.Bitmap;

public class Constant {

	public static int SCREEN_WIDTH;
	public static int SCREEN_HEIGHT;
	public static ScreenScaleResult screenScaleResult;
	
	public static float COLLISIONVELOCITY=30.0f;//球于木板碰撞的速度，当速度大于此值时，发出声音
	public static float ROTEANGLEOFFSET=30.0f;//球进洞的动画旋转的偏差调整
	public static float ROTEANGLE;//球进洞的的角度，相对于洞的角度
	
	public static final float E=20;//刚体边框宽
	public static final float R=20;//半径
	public static final float BODYOFF=12;//刚体与对应图片的差值
	public static int LEVEL=0;//当前的关数
	public static int LOCK=0;//当前的关数
	public static int TEMPLEVEL=-1;
	
	public static final int PLAY_MODEL1=0;//计时模式
	public static final int PLAY_MODEL2=1;//练习模式
	public static int PLAY_MODEL=PLAY_MODEL1;
	
	public static long[] COLLISION_SOUND_PATTERN={0l,30l};//第一个参数为执行震动方法后多长时间开始震动
															//第二个参数为震动持续时间，两个参数必须为long类型
	public static final float GRAVITY=10.0f;//重力参考量
	public static  Vec2 GRAVITYTEMP=new Vec2(0.0f,0.f);//临时重力参考量，由重力传感器给他赋值

	public static final float TIME_STEP = 4.0f/60.0f;//模拟的的频率   
	public static final int ITERA = 3;//迭代越大，模拟约精确，但性能越低   
	
	public static boolean LOAD_FINISH=false;//背景音乐关
	public static boolean YINYUE_CLOSE=false;//背景音乐关
	public static boolean YINXIAO_OPEN=true;//音效开
	public static boolean ZHENDONG_OPEN=true;//震动开
	public static boolean YINYUE1=true;//1为播放第一首背景音，2为播放第二首背景音
	public static boolean bg_music_sound=true;//为了调音乐开始按钮设置的标志位
	public static boolean from_nextview=false;//是否从跳转界面而来，为解决主机面游戏背景音乐重放设定

	//创建的小球的反弹补偿率
	public static final float ball_restitution=0.2f;
	//创建的小球的反弹补偿率
	
	public static float colli_j=2;//判断球碰撞的最小间隔，开始值是2
	public static float tempcolli_x=0;//球的碰撞初始点x
	public static float tempcolli_y=0;//球的碰撞初始点y
	public static boolean colli_flag=true;//碰撞后的显示那个小球，true显示球1，false显示球2
	
	//图片
	public static  Bitmap[] TP_ARRAY;//图片数组
	public static  Bitmap[] BOX_TP_ARRAY;//缩放后的墙的数组
	public static  Bitmap[] BOX_TP_ARRAY1;//缩放后的墙的数组
	public static  Bitmap[] LEVEL_ARRAY;//缩放前的关的的数组
	public static  Bitmap[] HISTORY_ARRAY;//缩放前的关的的数组
	
	public static Bitmap[][] BALL_IN_HOLE=new Bitmap[1][15];//球进洞的连续图画
	public static Bitmap[] TP_NUMPIC;//数字图片数组
	public static Bitmap[] TP_NUMGAME;//数字图片数组
	public static Bitmap[][] TP_FLASHHOLE=new Bitmap[1][15];//下标越小，图越小，下标越大，图越大
	public static Bitmap[] TRUE_FLASHHOLE=new Bitmap[14];
	
	public static float[][] ballstartplace=//每关球开始的位置
	{
		{31.0f,394.0f},//第一关开始位置
		{33.0f,430f},//第二关开始位置
		{729.0f,429.0f},//第四关开始位置
		{33.0f,402.0f},//第三关开始位置
		{750,135},//第五关
		{719.0f,57.0f},//第六关
		
	};
	
	//真正过关位置
	public static float[][] ballendplace=//每关球过关的位置
	{
		{760,375},//第一关
		{760,420},//第二关
		{412f,221f},//第四关
		{760,427},//第三关
		{400,230},//第五关
		{109.0f,399.0f},//第六关
	};
	
	public static int[][][] historypostion=
	{
		{
			{275,110},{275,160},{275,210},{275,260},{275,310},{275,360}
		},
		{
			{575,110},{575,160},{575,210},{575,260},{575,310},{575,360}
		}
			
	};
	
	//得到图片编号
	public static String[] PIC_NUM=
	{
		"bg.jpg",//    0      背景
		"main1.png",//      1   play按钮
		"hole_close_flash.png",//       2  声音按钮
		"gv_bg.jpg",//      3      设置按钮背景
		"set_close.png",//      4  静音按钮
		"ball_hole.png",//       5球洞
		"ball_1.png",//			6小球
		"stonestack_hen.jpg",//  7上下边界
		"stonestack_shu.jpg",//    8左右边界
		"fall_hole.png",//9球落尽洞里的连续的图画
		"goal.png",	//10过关目标球
		"box.png",//   11原始box图片100*100
		"mainmenu.png",//12返回主界面的按钮
		"next.png",//13下一关按钮
		"restart.png",//14重新开始按钮
		"main2.png",//15历史记录按钮
		"main3.png",//16设置按钮
		"timeLimited.png",//17限时模式
		"practice.png",//18练习模式
		"goback.png",//19返回按钮
		"1.png",//20第一关按钮
		"2.png",//21第二关按钮
		"3.png",//22第三关按钮
		"music_long.png",//23音乐按钮
		"music_short.png",//24音效按钮
		"vibrate.png",//25震动按钮
		"open.png",//26打开按钮
		"close.png",//27关闭按钮
		"history_bg.png",//28历史记录的背景
		"title1.png",//29游戏名字图片
		"pause.png",//30暂停按钮
		"level_lock.png",//31锁
		"say3.png",//32说话
		"4.png",//33第四关按钮
		"5.png",//34第五关按钮
		"6.png",//35第六关按钮
		"start.png",//36游戏界面开始按钮
		"time.png",//37time图
		"maohao.png",//38冒号
		"his_back.png",//39历史记录界面返回
		"top.png",//40
		"bottom.png",//41
		"left.png",//42
		"right.png"//43依次为上下左右
	};
	
	public static String[] LEVEL_NUM=
	{
		"1.png",
		"2.png",
		"3.png",
		"4.png",
		"5.png",
		"6.png",
		"level_lock.png",//6锁
	};
	public static String[] HIS_NUM=
	{
		"his1.png",
		"his2.png",
		"his3.png",
		"his4.png",
		"his5.png",
		"his6.png",
		"his_time.png",
		"his_practice.png",
		"minute.png",
		"hisna.png",
		"his_lock.png"
	};
	
	public static String[] NUM_PIC=
	{
		"num_hs_0.png",
		"num_hs_1.png",
		"num_hs_2.png",
		"num_hs_3.png",
		"num_hs_4.png",
		"num_hs_5.png",
		"num_hs_6.png",
		"num_hs_7.png",
		"num_hs_8.png",
		"num_hs_9.png",
		
	};
	
	public static String[] NUM_GAME=
	{
		"num_0.png",
		"num_1.png",
		"num_2.png",
		"num_3.png",
		"num_4.png",
		"num_5.png",
		"num_6.png",
		"num_7.png",
		"num_8.png",
		"num_9.png",
	};
	
	//得到图片坐标，下标和[] PIC_NUM数组一一对应
	public static float[][] xyoffset=
	{
		{0f,0f},//0
		{420f,165f},//1
		{330f,30f},//2
		{0F,0f},//3
		{0f,0f},//4静音坐标不用
		{},//5
		{},//6
		{},//7
		{},//8
		{},//9
		{},//10
		{},//11
		{350,320},//12
		{650,320},//13
		{500,320},//14
		{420,260},//15
		{420,355},//16
		{40,255},//17
		{420,255},//18
		{300,360},//19返回按钮
		{50,40},//20
		{290,40},//21
		{530,40},//22
		{360,50},//23
		{360,150},//24
		{360,250},//25
		{560,40},//26
		{},//27
		{0,0},//28历史记录背景
		{200,20},//29
		{1,1},//30gameview中pause暂停按钮的位置
		{},//31
		{380,50},//32
		{50,170},//33第四关
		{290,170},//34
		{530,170},//35
		{1,1},//36
		{},//37
		{},//38
		{628,400},//39
		{},//40
		{},//41
		{},//42
		{},//43
	};
	//触控位置坐标集合
	public static float[][] xyoffsetEvent;
	
	//得到球洞数组
	public static float[][][] bhoffset=
	{
		//第一关
		{
			{170.0f,106.0f},{218.0f,106.0f},{171.0f,186.0f},{218.0f,186.0f},{365.0f,105.0f},{408.0f,105.0f},
			{364.0f,186.0f},{409.0f,184.0f},{446.0f,56.0f},{491.0f,56.0f},{534.0f,56.0f},{572.0f,105.0f},
			{617.0f,104.0f},{571.0f,187.0f},{619.0f,187.0f},{447.0f,231.0f},{492.0f,232.0f},{537.0f,231.0f},
			{447.0f,422.0f},{492.0f,422.0f},{534.0f,423.0f}
		},
		//第二关
		{
			{127.0f,55.0f},{184.0f,193.0f},{181.0f,285.0f},{239.0f,422.0f},{340.0f,384.0f},{389.0f,253.0f},
			{465.0f,386.0f},{583.0f,346.0f},{625.0f,296.0f},{537.0f,223.0f},{577.0f,142.0f},
			{672.0f,144.0f}
		},
		
		//第3关
		{

			{741.0f,49.0f},{273.0f,180.0f},{147.0f,143.0f},{246.0f,424.0f},{627.0f,415.0f},
		},
		
		//第4关
		{
			{106.0f,49.0f},{187.0f,109.0f},{228.0f,196.0f},{231.0f,238.0f},{247.0f,341.0f},{210.0f,435.0f},
			{328.0f,148.0f},{360.0f,334.0f},{392.0f,40.0f},{417.0f,214.0f},{420.0f,438.0f},{492.0f,115.0f},
			{550.0f,41.0f},{557.0f,231.0f},{584.0f,434.0f},{611.0f,105.0f},{691.0f,41.0f},{694.0f,168.0f},
			{640.0f,287.0f}
		},
		//第五关
		{
			{83.0f,67.0f},{260.0f,55.0f},{414.0f,65.0f},{548.0f,45.0f},{690.0f,74.0f},{183.0f,147.0f},
			{69.0f,228.0f},{183.0f,231.0f},{184.0f,315.0f},{513.0f,144.0f},{603.0f,151.0f},{599.0f,232.0f},
			{700.0f,233.0f},{516.0f,316.0f},{606.0f,312.0f},{83.0f,412.0f},{264.0f,419.0f},{415.0f,411.0f},
			{551.0f,434.0f},{707.0f,410.0f},
		},
		//第六关
		{
			{654.0f,130.0f},{693.0f,172.0f},{642.0f,278.0f},{722.0f,318.0f},{432.0f,53.0f},{407.0f,162.0f},
			{381.0f,322.0f},{273.0f,221.0f},{285.0f,99.0f},{91.0f,107.0f},{167.0f,148.0f},

		}
	};
	public static float[][][] flashHolePositon=
	{
		//第一关
		{},
		//第二关
		{},
		
		//第3关
		{
			{628.0f,101.0f},{165.0f,60.0f},{492.0f,55.0f},
			{357.0f,265.0f},{514.0f,363.0f},{612.0f,211.0f},{641.0f,330.0f},
			{154.0f,252.0f},{140.0f,351.0f},{59.0f,291.0f},
		},
		//第4关
		{},
		//第五关
		{
			{400.0f,167.0f},{321.0f,232.0f},{402.0f,290.0f},{467.0f,228.0f},
		},
		//第六关
		{
			{556.0f,74.0f},{527.0f,184.0f},{545.0f,301.0f},{563.0f,404.0f},
			{100.0f,290.0f},{155.0f,294.0f},{177.0f,332.0f},{258.0f,403.0f},
		},
	};
	
	//box中心坐标
	public static float[][][] xycenter=
	{
		//第一关
		{
			{82.0f,333.0f},{715.5f,288.0f},{262.0f,61.5f},{314.0f,61.5f},
			{291.0f,149.0f},{126.0f,232.5f},{263.0f,229.5f},{314.0f,230.5f},{170.0f,278.0f},{215.5f,279.0f},
			{291.0f,328.0f},{170.0f,376.0f},{214.0f,376.0f},{263.0f,422.5f},{314.5f,422.5f},{367.0f,278.5f},
			{412.0f,279.5f},{368.0f,376.0f},{412.5f,376.0f},{487.5f,147.0f},{494.0f,328.5f},
			{622.5f,230.5f},{576.5f,278.0f},{622.0f,278.5f},{577.0f,376.5f},{624.5f,376.5f},{669.0f,376.0f},
			{400.0f,10.0f},{400.0f,470.0f},{10.0f,240.0f},{790.0f,240.0f}
		},
		//第二关
		{
			{83.0f,306.0f},{278.0f,361.5f},{362.0f,291.0f},{354.5f,189.0f},
			{400f,111.5f},{183.0f,124.0f},{187.5f,238.5f},{183.5f,346.0f},{276.0f,133.0f},{404.0f,383.5f},
			{502.5f,218.5f},{450.0f,181.0f},{635.0f,425.0f},{674.5f,285.f},{715.5f,293.0f},{400.0f,10.0f},
			{400.0f,470.0f},{10.0f,240.0f},{790.0f,240.0f}
		},
		
		//第3关
		{
			{400.0f,10.0f},{400.0f,470.0f},{10.0f,240.0f},{790.0f,240.0f},{99.5f,248.5f},{336.0f,101.0f},
			{196.5f,322.0f},{683.0f,275.0f},{317.0f,278.5f},{441.0f,178.0f},{566.5f,282.0f},{461.0f,218.5f},
			{436.0f,263.0f},{386.0f,376.5f},{457.0f,363.0f},
		},
		//第4关
		{
			{150.0f,342.0f},{277.0f,400.0f},{450.0f,362.0f},{525.0f,255.0f},{359.0f,156.0f},{221.5f,88.0f},
			{400.0f,10.0f},{400.0f,470.0f},{10.0f,240.0f},{790.0f,240.0f}
		},
		//第五关
		{
			{151.0f,75.0f},{149.5f,386.5f},{261.0f,108.0f},{266.0f,234.5f},{265.5f,359.0f},{410.0f,120.0f},
			{406.0f,338.0f},{552.0f,105.0f},{523.5f,233.0f},{546.5f,365.0f},
			{400.0f,10.0f},{400.0f,470.0f},{10.0f,240.0f},{790.0f,240.0f}
		},
		//第六关
		{
			{400.0f,10.0f},{400.0f,470.0f},{10.0f,240.0f},{790.0f,240.0f}
		}
	};
	
	public static float[][][] xyScale=//box图片xy的缩放比
	{
		//第一关
		{
			{0.4f,2.88f},{0.41f,3.78f},{0.36f,0.39f},{0.36f,0.39f},{0.36f,0.39f},
			{0.36f,0.39f},{0.36f,0.39f},{0.36f,0.39f},{0.36f,0.39f},{0.36f,0.39f},{0.36f,0.39f},{0.36f,0.39f},
			{0.36f,0.39f},{0.36f,0.39f},{0.36f,0.39f},{0.36f,0.39f},{0.36f,0.39f},{0.36f,0.39f},{0.36f,0.39f},
			{0.23f,0.38f},{0.36f,0.39f},{0.37f,0.37f},{0.37f,0.39f},{0.36f,0.39f},{0.36f,0.39f},
			{0.36f,0.39f},{0.36f,0.39f},{8.0f,0.2f},{8.0f,0.2f},{0.2f,4.8f},{0.2f,4.8f}
		},
		//第二关
		{
			{0.4f,3.44f},{0.26f,1.73f},{1.48f,0.28f},{0.25f,1.74f},{0.68f,0.23f},
			{0.26f,0.72f},{0.39f,0.35f},{0.25f,0.74f},{0.26f,1.62f},{0.84f,0.29f},{0.27f,3.59f},{0.7f,0.3f},
			{1.22f,0.74f},{0.41f,2.05f},{0.41f,3.7f},{8.0f,0.2f},{8.0f,0.2f},{0.2f,4.8f},{0.2f,4.8f}
		},
		//第3关
		{
			{8.0f,0.2f},{8.0f,0.2f},{0.2f,4.8f},{0.2f,4.8f},{0.37f,2.59f},{5.1f,0.4f},
			{0.39f,2.6f},{0.36f,3.54f},{0.36f,2.31f},{2.16f,0.3f},{0.39f,2.38f},{0.42f,0.55f},
			{0.94f,0.34f},{1.08f,0.37f},{0.4f,0.66f},
		},
		//第4关
		{
			{0.2f,2.72f},{0.2f,1.56f},{0.2f,2.34f},{0.2f,2.38f},{0.2f,3.1f},{0.2f,1.74f},
			{8.0f,0.2f},{8.0f,0.2f},{0.2f,4.8f},{0.2f,4.8f}
		},
		//第五关
		{
			{0.2f,0.48f},{0.2f,0.45f},{0.2f,0.12f},{0.26f,1.07f},{0.15f,0.2f},{1.2f,0.24f},{1.26f,0.22f},
			{0.2f,0.2f},{0.27f,1.04f},{0.2f,0.08f},{8.0f,0.2f},{8.0f,0.2f},{0.2f,4.8f},{0.2f,4.8f}
		},
		//第六关
		{
			{8.0f,0.2f},{8.0f,0.2f},{0.2f,4.8f},{0.2f,4.8f}
		}
	};

	//初始化得到图片数组
	public static void initBitmap(Resources resources) {
		TP_ARRAY=null;//初始化先置空
		HISTORY_ARRAY=null;
		TP_ARRAY=new Bitmap[PIC_NUM.length];
		HISTORY_ARRAY=new Bitmap[HIS_NUM.length];
		for(int i=0;i<PIC_NUM.length;i++)
		{
			TP_ARRAY[i]=PicLoadUtil.loadBM(resources, PIC_NUM[i]);
		}
		
		TP_NUMPIC=null;
		TP_NUMPIC=new Bitmap[NUM_PIC.length];
		for(int i=0;i<NUM_PIC.length;i++)
		{
			TP_NUMPIC[i]=PicLoadUtil.loadBM(resources, NUM_PIC[i]);
		}
		TP_NUMGAME=null;
		TP_NUMGAME=new Bitmap[NUM_GAME.length];
		for(int i=0;i<NUM_GAME.length;i++)
		{
			TP_NUMGAME[i]=PicLoadUtil.loadBM(resources, NUM_GAME[i]);
		}
		
		LEVEL_ARRAY=null;
		LEVEL_ARRAY=new Bitmap[LEVEL_NUM.length];
		for(int i=0;i<LEVEL_NUM.length;i++)
		{
			Bitmap bmp=PicLoadUtil.loadBM(resources, LEVEL_NUM[i]);
			LEVEL_ARRAY[i]=PicLoadUtil.scaleBitmap(bmp,(int)(bmp.getWidth()*1.3f) , (int)(bmp.getHeight()*1.3f));
//			LEVEL_ARRAY[i]=PicLoadUtil.loadBM(resources, LEVEL_NUM[i]);
//			LEVEL_ARRAY[i]=PicLoadUtil.scaleBitmap(LEVEL_ARRAY[i],(int)(LEVEL_ARRAY[i].getWidth()*1.3f) , (int)(LEVEL_ARRAY[i].getHeight()*1.3f));

		}
//		for(int i=0;i<LEVEL_NUM.length;i++)
//		{
//			LEVEL_ARRAY[i]=PicLoadUtil.scaleBitmap(LEVEL_ARRAY[i],(int)(LEVEL_ARRAY[i].getWidth()*1.3f) , (int)(LEVEL_ARRAY[i].getHeight()*1.3f));
//		}
		for(int i=0;i<HIS_NUM.length;i++)
		{
			HISTORY_ARRAY[i]=PicLoadUtil.loadBM(resources, HIS_NUM[i]);
		}
		TP_FLASHHOLE=PicLoadUtil.splitPic
		   (
				   1,//切割的行数 
				   15,//切割的列数    
				   TP_ARRAY[2],//被切割的图片  
				   40,//切割后调整的目标宽度
				   40//切割后调整的目标高度  
		   ) ;
		
		for(int i=0;i<14;i++)
		{
			TRUE_FLASHHOLE[i]=TP_FLASHHOLE[i+1][0];
		}
		//得到球进洞的连续动画
		BALL_IN_HOLE=PicLoadUtil.splitPic
		   (
				   1,//切割的行数 
				   15,//切割的列数    
				   TP_ARRAY[9],//被切割的图片  
				   40,//切割后调整的目标宽度
				   40//切割后调整的目标高度  
		   ) ;
		
	}
	
	public static void initBoxBitmap1(Resources resources)
	{
		for(int i=0;i<10;i++)
		BOX_TP_ARRAY1=null;
		BOX_TP_ARRAY1=new Bitmap[xyScale[0].length];
		for(int i=0;i<xyScale[Constant.LEVEL].length;i++)
		{
			BOX_TP_ARRAY1[i]=PicLoadUtil.combineRec(TP_ARRAY[11],100*xyScale[Constant.LEVEL][i][0] , 100*xyScale[Constant.LEVEL][i][1]);
		}
				
	}
	
	static boolean isscale=false;
	public static void scaleCL()
	{
		
		if(isscale) return;
		 screenScaleResult=ScreenScaleUtil.calScale(SCREEN_WIDTH, SCREEN_HEIGHT);
		 
		 xyoffsetEvent=new float[xyoffset.length][2];
		 for(int i=0;i<xyoffset.length;i++)
		 {
			
			 if(xyoffset[i].length>0)
			 {
				 xyoffsetEvent[i][0]=(xyoffset[i][0]+screenScaleResult.lucX)*screenScaleResult.ratio;
				 xyoffsetEvent[i][1]=(xyoffset[i][1]+screenScaleResult.lucY)*screenScaleResult.ratio;
			 }
			 
		 }
		 isscale=true;
	}
	public static void initDB()
	{
		if(DBUtil.getLock(0, 0)==0)
		{
			DBUtil.insert(0, 0, 0, 1);
		}
		if(DBUtil.getLock(1, 0)==0)
		{
			DBUtil.insert(1, 0, 0, 1);
		}
		
		int [] i=DBUtil.SearchSetting();
		if(i[0]==0)
		{
			DBUtil.insertSetting(1,1,1);
		}
		if(i[0]==2)
		{
			YINYUE_CLOSE=true;
			bg_music_sound=false;
		}
		if(i[1]==2)
		{
			YINXIAO_OPEN=false;
		}
		if(i[2]==2)
		{
			ZHENDONG_OPEN=false;
		}
	}
}
