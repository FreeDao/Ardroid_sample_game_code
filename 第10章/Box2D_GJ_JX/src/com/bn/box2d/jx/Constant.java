package com.bn.box2d.jx;

public class Constant 
{
	public static final float RATE = 10;//��Ļ����ʵ����ı��� 10px��1m;   
	public static final boolean DRAW_THREAD_FLAG=true;//�����̹߳�����־λ
	
	public static final float TIME_STEP = 1.0f/120.0f;//ģ��ĵ�Ƶ��   
	public static final int ITERA = 6;//����Խ��ģ��Լ��ȷ��������Խ��   
	public static boolean PHYSICS_THREAD_FLAG=true;//ģ���̹߳�����־λ
	
	public static int SCREEN_WIDTH=540;  //��Ļ���
	public static int SCREEN_HEIGHT=960; //��Ļ�߶�	
	

    static final int kd=20;//��Ȼ�߶ȵ�λ
    public static boolean zuigao=false;
    public static boolean zuidi=false;
    public static boolean SHANCHU=false;
    public static boolean TuoPan_ZhuanHui=false;
    public static boolean TuoPan1_shanchu=false;//ɾ���ڶ�������
    public static boolean TuoPan2_shanchu=false;//ɾ������������
    
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
