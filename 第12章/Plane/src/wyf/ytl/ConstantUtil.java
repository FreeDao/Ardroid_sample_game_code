package wyf.ytl;
public class ConstantUtil {
	/**
	 * GameView与GameViewBackGroundThread中用到的常量
	 */
	
//	public static int PMX = 320;  //此处一会试一下将宽和高反转一下
//	public static int PMY = 480;
	
	public static int PMX1 = 320;
	public static int PMY1 = 480;
	
	public static int LOX = 0;
	public static int LOY = 0;
	public static float RADIO = 1.0f;
	
	public static final int pictureWidth = 50;//单元图的宽度
	public static final int pictureHeight = 320;//单元图的宽度
	public static final int screenWidth = 480;//屏幕宽度    
	public static final int screenHeight = 320;//屏幕宽度    
	public static final int pictureCount = 40;//背景图片数量
	public static final int top = 0;//距上边沿的距离
	/**
	 * 下面是游戏中所用到的方向常量
	 * 0静止，1上, 2右上，3右，4右下，5下，6左下，7左，8左上
	 */
	public static final int DIR_STOP = 0;
	public static final int DIR_UP = 1;
	public static final int DIR_RIGHT_UP = 2;
	public static final int DIR_RIGHT = 3;
	public static final int DIR_RIGHT_DOWN = 4;
	public static final int DIR_DOWN = 5;
	public static final int DIR_LEFT_DOWN = 6;
	public static final int DIR_LEFT = 7;
	public static final int DIR_LEFT_UP = 8;
	public static final double BooletSpan = 0.1;//敌机发子弹的概率
	public static final double BooletSpan2 = 0.2;//关口发子弹的概率
	public static final int life = 5;//玩家飞机的生命
	
	public static final int RECT_SPACE = 0;
	public static final int RECT_FIRE =1;
	public static final int RECT_UP =2;
	public static final int RECT_DOWN =3;
	public static final int RECT_LEFT =4;
	public static final int RECT_RIGHT =5;
}