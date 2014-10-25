package com.bn.box2d.jx;

public class Constant 
{
	public static final float RATE = 10;//屏幕到现实世界的比例 10px：1m;   
	public static final boolean DRAW_THREAD_FLAG=true;//绘制线程工作标志位
	
	public static final float TIME_STEP = 1.0f/120.0f;//模拟的的频率   
	public static final int ITERA = 6;//迭代越大，模拟约精确，但性能越低   
	public static boolean PHYSICS_THREAD_FLAG=true;//模拟线程工作标志位
	
	public static int SCREEN_WIDTH=540;  //屏幕宽度
	public static int SCREEN_HEIGHT=960; //屏幕高度	
	

    static final int kd=20;//宽度或高度单位
    public static boolean zuigao=false;
    public static boolean zuidi=false;
    public static boolean SHANCHU=false;
    public static boolean TuoPan_ZhuanHui=false;
    public static boolean TuoPan1_shanchu=false;//删除第二个托盘
    public static boolean TuoPan2_shanchu=false;//删除第三个托盘
    
    public static ScreenScaleResult screenScaleResult;
    public static float x;
    public static float y;
    public static float ratio;
    public static void ScaleSR()
    {
    	screenScaleResult=ScreenScaleUtil.calScale(SCREEN_WIDTH, SCREEN_HEIGHT);
    	x=screenScaleResult.lucX;
    	y=screenScaleResult.lucY;
    	ratio=screenScaleResult.ratio;
    	System.out.println(x+" "+y+" "+ratio);
    }
    
}
