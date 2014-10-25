package com.baina.tower.view;

import com.baina.tower.allactivity.MainGameActivity;
import com.baina.tower.allactivity.R;
import com.baina.tower.constant.Constants;
import com.baina.tower.threads.DrawMonsterThread;
import com.baina.tower.threads.GunDongTxtThread;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

//游戏方法界面的呈现
public class YouXiFangFaSurfaceView extends SurfaceView implements SurfaceHolder.Callback // 实现生命周期回调接口
{
	//用于存放炮台图鉴的bitmap数组
	Bitmap[] towerBitmaps;
	Bitmap tower1;
	Bitmap tower2;
	Bitmap tower3;
	Bitmap tower4;
	//用于存放怪物图鉴的bitmap数组
	Bitmap[] monsterBitmaps;
	public Bitmap creep_red;
	public Bitmap creep_yellow;
	public Bitmap creep_purple;
	//地图格子图鉴bitmap数组
	Bitmap[] mapBitmaps;
	Bitmap blood_up;
	Bitmap blood_down;
	Bitmap speed_up;
	Bitmap speed_down;
	Bitmap turn_num1;
	Bitmap turn_num2;
	Bitmap turn_num3;
	Bitmap turn_num4;
	Bitmap turn_num5;
	Bitmap turn_num6;
	//对各个东西的相关的介绍
	Bitmap speeduptxt;
	Bitmap speeddowntxt;
	Bitmap boolduptxt;
	Bitmap boolddowntxt;
	Bitmap directiontxt;
	Bitmap[]  geZiIntroduces;
	//////////////////////
	Bitmap tower1txt;
	Bitmap tower2txt;
	Bitmap tower3txt;
	Bitmap tower4txt;
	Bitmap[]  towersIntroduces;
	////////////////////////
	Bitmap monster1txt;
	Bitmap monster2txt;
	Bitmap monster3txt;
	Bitmap[]  monstersIntroduces;
	//背景贴图
	Bitmap backGround;
	Bitmap xinFeng; //僵尸写给你的信的图片
	Bitmap tuJian;  //游戏图鉴按钮
	Bitmap showAllDisplays;
	Bitmap lookGeZis;
	Bitmap lookTowers;
	Bitmap lookMonsters;
	Bitmap tuJianGongNengGeZi;
	Bitmap tuJianTower;
	Bitmap tuJianMonster;
	//滚动图片字条
	public Bitmap gunDongTxt;
	public float x = Constants.PMX;//滚动字幕初始横坐标
	public float y = 150;;//滚动字幕初始纵坐标
	//游戏方法界面的绘制线程
	DrawMonsterThread yXFFDrawThread;
	GunDongTxtThread gunDongTxtThread;
	//画笔引用
	Paint paint;
	
	boolean allShow = false;
	boolean clickGeZiBtn = false;
	boolean clickTowerBtn = false;
	boolean clickMonstersBtn = false;
	//地图功能格子的相关变量
	int mapIndex = 0;
	int towerIndex = 0;
	int txtGeZiIndex = 2;
	int txtTowerIndex = 0;
	int monsterIndex = 0;
	int txtMonsterIndex = 0;
	//展示界面的卡片的宽度
	int cardWidth = 50;
	int cardHeight = 70;
	public ScreenScaleResult screenScaleResult;//屏幕自适应结果类
	
	public YouXiFangFaSurfaceView(Context context) 
	{
		super(context);
		
		//屏幕自适应
		screenScaleResult = ScreenScaleUtil.calScale(Constants.PMX1,Constants.PMY1);
		//存储画布相关数据
		Constants.LOX = screenScaleResult.lucX;
		Constants.LOY = screenScaleResult.lucY;
		Constants.RADIO = screenScaleResult.ratio;
		
		paint = new Paint();// 创建画笔
		paint.setAntiAlias(true);// 打开抗锯齿
		this.getHolder().addCallback(this);// 设置生命周期回调接口的实现者
		//下面这两句代码一定要写，不然我们的键盘的监听不起作用
		this.setFocusable(true);
		this.setFocusableInTouchMode(true);
		//设置View为可见
		this.setVisibility(0);
	}
	
	//绘制方法
	public void onDraw(Canvas canvas)
	{
		//每次绘制之前都要清屏
		paint.setColor(Color.BLACK);
		paint.setStyle(Style.FILL);
		canvas.drawRect(-10, -10, Constants.PMX1+10, Constants.PMY1+10, paint);
		
		canvas.save();
		canvas.translate(Constants.LOX, Constants.LOY);
		canvas.scale(Constants.RADIO, Constants.RADIO);
		paint.setColor(Color.BLACK);
		paint.setStyle(Style.FILL);
		canvas.drawRect(-10, -10, Constants.PMX+10, Constants.PMY+10, paint);
		
		//如果没有点击游戏图鉴图标就绘制帮助首届面
		if(!allShow && !clickGeZiBtn && !clickTowerBtn && !clickMonstersBtn)
		{
			canvas.drawBitmap(xinFeng, 0,0, paint);
			canvas.drawBitmap(tuJian, (Constants.PMX-tuJian.getWidth())/2,Constants.PMY-tuJian.getHeight()-20,paint);
		}
		//点击了游戏图鉴图标
		if(allShow && !clickGeZiBtn && !clickTowerBtn && !clickMonstersBtn)
		{
			canvas.drawBitmap(showAllDisplays, 0,0, paint);
			canvas.drawBitmap(lookGeZis, 163,220, paint);
			canvas.drawBitmap(lookTowers, 140,440, paint);
			canvas.drawBitmap(lookMonsters, 167,690, paint);
		}
		//当点击了查看功能格子的图标后
		if(!allShow && clickGeZiBtn && !clickTowerBtn && !clickMonstersBtn)
		{
			canvas.drawBitmap(tuJianGongNengGeZi, 0,0, paint);
			//绘制滚动字幕
			canvas.drawBitmap(gunDongTxt, x,y, paint);
			canvas.drawBitmap(mapBitmaps[mapIndex], 80,320, paint);
			canvas.drawBitmap(geZiIntroduces[txtGeZiIndex], 225,214, paint);
		}
		//当点击了查看怪物的图标后
		if(!allShow && !clickGeZiBtn && !clickTowerBtn && clickMonstersBtn)
		{
			canvas.drawBitmap(tuJianMonster, 0,0, paint);
			//绘制滚动字幕
			canvas.drawBitmap(gunDongTxt, x,y, paint);
			if(monsterIndex == 2)
				canvas.drawBitmap(monsterBitmaps[monsterIndex],68,308, paint);
			else
				canvas.drawBitmap(monsterBitmaps[monsterIndex],76,316, paint);
			canvas.drawBitmap(monstersIntroduces[txtMonsterIndex], 225,214, paint);
		}
		//当点击了查看炮塔的图标后
		if(!allShow && !clickGeZiBtn && clickTowerBtn && !clickMonstersBtn)
		{
			canvas.drawBitmap(tuJianTower, 0,0, paint);
			//绘制滚动字幕
			canvas.drawBitmap(gunDongTxt, x,y, paint);
			canvas.drawBitmap(towerBitmaps[towerIndex], 62,295, paint);
			canvas.drawBitmap(towersIntroduces[txtTowerIndex],225,214, paint);
		}
		canvas.restore();
	}
	//手机键监听
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
        	if(clickGeZiBtn || clickTowerBtn || clickMonstersBtn)
        	{
        		allShow = true;
				clickGeZiBtn = false;
				clickTowerBtn = false;
				clickMonstersBtn = false;
				gunDongTxtThread.setFlag(false);
				return true;
        	}
        	else if(allShow)
        	{
        		allShow = false;
        		clickGeZiBtn = false;
				clickTowerBtn = false;
				clickMonstersBtn = false;
				
				return true;
        	}
        }
        //继续执行父类的其他点击事件
        return super.onKeyDown(keyCode, event);
    }
	//触摸事件的方法
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		float x = event.getX();
		float y = event.getY();
		switch(event.getAction())
		{
			case MotionEvent.ACTION_DOWN:
				if(x<(163+lookGeZis.getWidth()+Constants.LOX)*Constants.RADIO
					&& x>(163+Constants.LOX)*Constants.RADIO
					&& y<(220+lookGeZis.getHeight()+Constants.LOY)*Constants.RADIO
					&& y>(220+Constants.LOY)*Constants.RADIO
					&& allShow
				  )
				{
					allShow = false;
					clickGeZiBtn = true;
					clickTowerBtn = false;
					clickMonstersBtn = false;
					//滚动字幕线程启动
					gunDongTxtThread = new GunDongTxtThread(this);
					gunDongTxtThread.start();
					MainGameActivity.sound.playMusic(Constants.BUTTON_PRESS, 0)  ;
				}
				else if(x<((Constants.PMX-tuJian.getWidth())/2+tuJian.getWidth()+Constants.LOX)*Constants.RADIO
						&& x>((Constants.PMX-tuJian.getWidth())/2+Constants.LOX)*Constants.RADIO
						&& y<(Constants.PMY-20+Constants.LOY)*Constants.RADIO
						&& y>(Constants.PMY-tuJian.getHeight()-20+Constants.LOY)*Constants.RADIO
						&& !allShow
						&& !clickGeZiBtn
						&& !clickTowerBtn
						&& !clickMonstersBtn
						)
				{
					allShow = true;
					clickGeZiBtn = false;
					clickTowerBtn = false;
					clickMonstersBtn = false;
					MainGameActivity.sound.playMusic(Constants.BUTTON_PRESS, 0)  ;
				}else if(x<(140+lookTowers.getWidth()+Constants.LOX)*Constants.RADIO
						&& x>(140+Constants.LOX)*Constants.RADIO
						&& y<(440+lookTowers.getHeight()+Constants.LOY)*Constants.RADIO
						&& y>(440+Constants.LOY)*Constants.RADIO
						&& allShow
					  )
					{
						allShow = false;
						clickGeZiBtn = false;
						clickTowerBtn = true;
						clickMonstersBtn = false;
						//滚动字幕线程启动
						gunDongTxtThread = new GunDongTxtThread(this);
						gunDongTxtThread.start();
						MainGameActivity.sound.playMusic(Constants.BUTTON_PRESS, 0)  ;
					}else if(x<(167+lookTowers.getWidth()+Constants.LOX)*Constants.RADIO
						&& x>(167+Constants.LOX)*Constants.RADIO
						&& y<(690+lookTowers.getHeight()+Constants.LOY)*Constants.RADIO
						&& y>(690+Constants.LOY)*Constants.RADIO
						&& allShow
					  )
					{
						allShow = false;
						clickGeZiBtn = false;
						clickTowerBtn = false;
						clickMonstersBtn = true;
						//滚动字幕线程启动
						gunDongTxtThread = new GunDongTxtThread(this);
						gunDongTxtThread.start();
						MainGameActivity.sound.playMusic(Constants.BUTTON_PRESS, 0)  ;
					}
				
				//切换到功能格子display界面的触控
				if(x<(45+cardWidth+Constants.LOX)*Constants.RADIO
						&& x>(45+Constants.LOX)*Constants.RADIO
						&& y<(570+cardHeight+Constants.LOY)*Constants.RADIO
						&& y>(570+Constants.LOY)*Constants.RADIO
						&& clickGeZiBtn
					  )
				{
					mapIndex = 2; //加速格子
					txtGeZiIndex = 0;
					MainGameActivity.sound.playMusic(Constants.BUTTON_PRESS, 0)  ;
				}
				else if(x<(130+cardWidth+Constants.LOX)*Constants.RADIO
						&& x>(130+Constants.LOX)*Constants.RADIO
						&& y<(570+cardHeight+Constants.LOY)*Constants.RADIO
						&& y>(570+Constants.LOY)*Constants.RADIO
						&& clickGeZiBtn
					  )
				{
					mapIndex = 3; //减速格子
					txtGeZiIndex = 1;
					MainGameActivity.sound.playMusic(Constants.BUTTON_PRESS, 0)  ;
				}
				else if(x<(210+cardWidth+Constants.LOX)*Constants.RADIO
						&& x>(210+Constants.LOX)*Constants.RADIO
						&& y<(570+cardHeight+Constants.LOY)*Constants.RADIO
						&& y>(570+Constants.LOY)*Constants.RADIO
						&& clickGeZiBtn
					  )
				{
					mapIndex = 1; //减血格子
					txtGeZiIndex = 3;
					MainGameActivity.sound.playMusic(Constants.BUTTON_PRESS, 0)  ;
				}
				else if(x<(300+cardWidth+Constants.LOX)*Constants.RADIO
						&& x>(300+Constants.LOX)*Constants.RADIO
						&& y<(570+cardHeight+Constants.LOY)*Constants.RADIO
						&& y>(570+Constants.LOY)*Constants.RADIO
						&& clickGeZiBtn
					  )
				{
					mapIndex = 0; //加血格子
					txtGeZiIndex = 2;
					MainGameActivity.sound.playMusic(Constants.BUTTON_PRESS, 0)  ;
				}
				else if(x<(380+cardWidth+Constants.LOX)*Constants.RADIO
						&& x>(380+Constants.LOX)*Constants.RADIO
						&& y<(570+cardHeight+Constants.LOY)*Constants.RADIO
						&& y>(570+Constants.LOY)*Constants.RADIO
						&& clickGeZiBtn
					  )
				{
					mapIndex = 9;
					txtGeZiIndex = 4;
					MainGameActivity.sound.playMusic(Constants.BUTTON_PRESS, 0)  ;
				}
				else if(x<(45+cardWidth+Constants.LOX)*Constants.RADIO
						&& x>(45+Constants.LOX)*Constants.RADIO
						&& y<(675+cardHeight+Constants.LOY)*Constants.RADIO
						&& y>(675+Constants.LOY)*Constants.RADIO
						&& clickGeZiBtn
					  )
				{
					mapIndex = 5;
					txtGeZiIndex = 4;
					MainGameActivity.sound.playMusic(Constants.BUTTON_PRESS, 0)  ;
				}
				else if(x<(130+cardWidth+Constants.LOX)*Constants.RADIO
						&& x>(130+Constants.LOX)*Constants.RADIO
						&& y<(675+cardHeight+Constants.LOY)*Constants.RADIO
						&& y>(675+Constants.LOY)*Constants.RADIO
						&& clickGeZiBtn
					  )
				{
					mapIndex = 7;
					txtGeZiIndex = 4;
					MainGameActivity.sound.playMusic(Constants.BUTTON_PRESS, 0)  ;
				}
				else if(x<(210+cardWidth+Constants.LOX)*Constants.RADIO
						&& x>(210+Constants.LOX)*Constants.RADIO
						&& y<(675+cardHeight+Constants.LOY)*Constants.RADIO
						&& y>(675+Constants.LOY)*Constants.RADIO
						&& clickGeZiBtn
					  )
				{
					mapIndex = 4;
					txtGeZiIndex = 4;
					MainGameActivity.sound.playMusic(Constants.BUTTON_PRESS, 0)  ;
				}
				else if(x<(300+cardWidth+Constants.LOX)*Constants.RADIO
						&& x>(300+Constants.LOX)*Constants.RADIO
						&& y<(675+cardHeight+Constants.LOY)*Constants.RADIO
						&& y>(675+Constants.LOY)*Constants.RADIO
						&& clickGeZiBtn
					  )
				{
					mapIndex = 6;
					txtGeZiIndex = 4;
					MainGameActivity.sound.playMusic(Constants.BUTTON_PRESS, 0)  ;
				}
				else if(x<(380+cardWidth+Constants.LOX)*Constants.RADIO
						&& x>(380+Constants.LOX)*Constants.RADIO
						&& y<(675+cardHeight+Constants.LOY)*Constants.RADIO
						&& y>(675+Constants.LOY)*Constants.RADIO
						&& clickGeZiBtn
					  )
				{
					mapIndex = 8;
					txtGeZiIndex = 4;
					MainGameActivity.sound.playMusic(Constants.BUTTON_PRESS, 0)  ;
				}
				

				//切换到炮塔display界面的触控
				if(x<(40+cardWidth+Constants.LOX)*Constants.RADIO
						&& x>(40+Constants.LOX)*Constants.RADIO
						&& y<(565+cardHeight+Constants.LOY)*Constants.RADIO
						&& y>(565+Constants.LOY)*Constants.RADIO
						&& clickTowerBtn
					  )
				{
					towerIndex = 0; //塔1
					txtTowerIndex = 0;
					MainGameActivity.sound.playMusic(Constants.BUTTON_PRESS, 0)  ;
				}
				else if(x<(130+cardWidth+Constants.LOX)*Constants.RADIO
						&& x>(130+Constants.LOX)*Constants.RADIO
						&& y<(565+cardHeight+Constants.LOY)*Constants.RADIO
						&& y>(565+Constants.LOY)*Constants.RADIO
						&& clickTowerBtn
					  )
				{
					towerIndex = 1; //塔2
					txtTowerIndex = 1;
					MainGameActivity.sound.playMusic(Constants.BUTTON_PRESS, 0)  ;
				}
				else if(x<(210+cardWidth+Constants.LOX)*Constants.RADIO
						&& x>(210+Constants.LOX)*Constants.RADIO
						&& y<(565+cardHeight+Constants.LOY)*Constants.RADIO
						&& y>(565+Constants.LOY)*Constants.RADIO
						&& clickTowerBtn
					  )
				{
					towerIndex = 2; //塔3
					txtTowerIndex = 2;
					MainGameActivity.sound.playMusic(Constants.BUTTON_PRESS, 0)  ;
				}
				else if(x<(300+cardWidth+Constants.LOX)*Constants.RADIO
						&& x>(300+Constants.LOX)*Constants.RADIO
						&& y<(565+cardHeight+Constants.LOY)*Constants.RADIO
						&& y>(565+Constants.LOY)*Constants.RADIO
						&& clickTowerBtn
					  )
				{
					towerIndex = 3; //塔4
					txtTowerIndex = 3;
					MainGameActivity.sound.playMusic(Constants.BUTTON_PRESS, 0)  ;
				}
				
				//切换到怪物display界面的触控
				if(x<(40+cardWidth+Constants.LOX)*Constants.RADIO
						&& x>(40+Constants.LOX)*Constants.RADIO
						&& y<(570+cardHeight+Constants.LOY)*Constants.RADIO
						&& y>(570+Constants.LOY)*Constants.RADIO
						&& clickMonstersBtn
					  )
				{
					monsterIndex = 0; //怪物1
					txtMonsterIndex = 0;
					MainGameActivity.sound.playMusic(Constants.BUTTON_PRESS, 0)  ;
				}
				else if(x<(130+cardWidth+Constants.LOX)*Constants.RADIO
						&& x>(130+Constants.LOX)*Constants.RADIO
						&& y<(570+cardHeight+Constants.LOY)*Constants.RADIO
						&& y>(570+Constants.LOY)*Constants.RADIO
						&& clickMonstersBtn
					  )
				{
					monsterIndex = 2; //怪物3
					txtMonsterIndex = 2;
					MainGameActivity.sound.playMusic(Constants.BUTTON_PRESS, 0)  ;
				}
				else if(x<(210+cardWidth+Constants.LOX)*Constants.RADIO
						&& x>(210+Constants.LOX)*Constants.RADIO
						&& y<(570+cardHeight+Constants.LOY)*Constants.RADIO
						&& y>(570+Constants.LOY)*Constants.RADIO
						&& clickMonstersBtn
					  )
				{
					monsterIndex = 1; //怪物2
					txtMonsterIndex = 1;
					MainGameActivity.sound.playMusic(Constants.BUTTON_PRESS, 0)  ;
				}
			break;
		}
		return super.onTouchEvent(event);
	}
	
	//创建时被调用
	@Override
	public void surfaceCreated(SurfaceHolder holder)
	{
		//滚动字条图片
		gunDongTxt = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.gundongtxt);
		//introduces相关图片
		speeduptxt = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.speeduptxt);
		speeddowntxt = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.speeddowntxt);
		boolduptxt = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.boolduptxt);
		boolddowntxt = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.boolddowntxt);
		directiontxt = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.directiontxt);
		tower1txt = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.tower1txt);
		tower2txt = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.tower2txt);
		tower3txt = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.tower3txt);
		tower4txt = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.tower4txt);
		monster1txt = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.monster1txt);
		monster2txt = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.monster2txt);
		monster3txt = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.monster3txt);
		monstersIntroduces = new Bitmap[]{monster1txt,monster2txt,monster3txt};
		towersIntroduces = new Bitmap[]{tower1txt,tower2txt,tower3txt,tower4txt};
		geZiIntroduces = new Bitmap[]{speeduptxt,speeddowntxt,boolduptxt,boolddowntxt,directiontxt};
		//具体展示界面
		tuJianGongNengGeZi = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.tujiangongnenggezi);
		tuJianTower = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.tujiantower);
		tuJianMonster = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.tujianmonsters);
		//帮助界面背景图片
		backGround = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.background);
		showAllDisplays = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.showalldisplays);
		lookGeZis = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.lookgezis);
		lookMonsters = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.lookmonsters);
		lookTowers = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.looktowers);
		//初始化地图格子图片及地图格子图片数组
		blood_up= BitmapFactory.decodeResource(this.getResources(),
				R.drawable.bn_healing_cell);  
		blood_down= BitmapFactory.decodeResource(this.getResources(),
				R.drawable.damage_cell);
		speed_up= BitmapFactory.decodeResource(this.getResources(),
				R.drawable.bn_fast_cell);
		speed_down= BitmapFactory.decodeResource(this.getResources(),
				R.drawable.bn_slow_cell);
		turn_num1= BitmapFactory.decodeResource(this.getResources(),
				R.drawable.bn_directional_s);
		turn_num2= BitmapFactory.decodeResource(this.getResources(),
				R.drawable.bn_directional_se);
		turn_num3= BitmapFactory.decodeResource(this.getResources(),
				R.drawable.bn_directional_ne);
		turn_num4= BitmapFactory.decodeResource(this.getResources(),
				R.drawable.bn_directional_n);
		turn_num5= BitmapFactory.decodeResource(this.getResources(),
				R.drawable.bn_directional_nw);
		turn_num6= BitmapFactory.decodeResource(this.getResources(),
				R.drawable.bn_directional_sw);
		mapBitmaps = new Bitmap[]{blood_up,blood_down,speed_up,speed_down,turn_num1,turn_num2,turn_num3,turn_num4,turn_num5,turn_num6};
		//初始化炮台及炮塔数组
		tower1 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.button_0);
		tower2 = BitmapFactory.decodeResource(this.getResources(),  
				R.drawable.button_1);
		tower3 = BitmapFactory.decodeResource(this.getResources(),   
				R.drawable.button_2);
		tower4 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.button_3);
		towerBitmaps = new Bitmap[]{tower1,tower2,tower3,tower4};
		//初始化怪物及怪物数组
		creep_red = BitmapFactory.decodeResource(this.getResources(),  
				R.drawable.bn_creep_red);
		creep_yellow = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.bn_creep_yellow);
		creep_purple = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.bn_creep_purple);
		monsterBitmaps = new Bitmap[]{creep_red,creep_yellow,creep_purple};
		//初始化背景图片
		backGround = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.back);
		//初始化信封
		xinFeng = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.xinfeng);
		//初始化图鉴图片
		tuJian = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.tujian);
		//创建游戏方法界面绘制线程，并启动
		yXFFDrawThread = new DrawMonsterThread(this);
		yXFFDrawThread.start();
	}
	
	public void repaint() 
	{
		SurfaceHolder holder = this.getHolder();
		Canvas canvas = holder.lockCanvas();// 获取画布
		try {
			synchronized (holder) {
				onDraw(canvas);// 绘制
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (canvas != null) {
				holder.unlockCanvasAndPost(canvas);
			}
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) 
	{// 销毁时被调用
		yXFFDrawThread.setFlag(false);	
		//销毁当前SurfaceView
	}

}
