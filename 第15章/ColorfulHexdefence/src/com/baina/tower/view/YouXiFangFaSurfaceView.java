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

//��Ϸ��������ĳ���
public class YouXiFangFaSurfaceView extends SurfaceView implements SurfaceHolder.Callback // ʵ���������ڻص��ӿ�
{
	//���ڴ����̨ͼ����bitmap����
	Bitmap[] towerBitmaps;
	Bitmap tower1;
	Bitmap tower2;
	Bitmap tower3;
	Bitmap tower4;
	//���ڴ�Ź���ͼ����bitmap����
	Bitmap[] monsterBitmaps;
	public Bitmap creep_red;
	public Bitmap creep_yellow;
	public Bitmap creep_purple;
	//��ͼ����ͼ��bitmap����
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
	//�Ը�����������صĽ���
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
	//������ͼ
	Bitmap backGround;
	Bitmap xinFeng; //��ʬд������ŵ�ͼƬ
	Bitmap tuJian;  //��Ϸͼ����ť
	Bitmap showAllDisplays;
	Bitmap lookGeZis;
	Bitmap lookTowers;
	Bitmap lookMonsters;
	Bitmap tuJianGongNengGeZi;
	Bitmap tuJianTower;
	Bitmap tuJianMonster;
	//����ͼƬ����
	public Bitmap gunDongTxt;
	public float x = Constants.PMX;//������Ļ��ʼ������
	public float y = 150;;//������Ļ��ʼ������
	//��Ϸ��������Ļ����߳�
	DrawMonsterThread yXFFDrawThread;
	GunDongTxtThread gunDongTxtThread;
	//��������
	Paint paint;
	
	boolean allShow = false;
	boolean clickGeZiBtn = false;
	boolean clickTowerBtn = false;
	boolean clickMonstersBtn = false;
	//��ͼ���ܸ��ӵ���ر���
	int mapIndex = 0;
	int towerIndex = 0;
	int txtGeZiIndex = 2;
	int txtTowerIndex = 0;
	int monsterIndex = 0;
	int txtMonsterIndex = 0;
	//չʾ����Ŀ�Ƭ�Ŀ��
	int cardWidth = 50;
	int cardHeight = 70;
	public ScreenScaleResult screenScaleResult;//��Ļ����Ӧ�����
	
	public YouXiFangFaSurfaceView(Context context) 
	{
		super(context);
		
		//��Ļ����Ӧ
		screenScaleResult = ScreenScaleUtil.calScale(Constants.PMX1,Constants.PMY1);
		//�洢�����������
		Constants.LOX = screenScaleResult.lucX;
		Constants.LOY = screenScaleResult.lucY;
		Constants.RADIO = screenScaleResult.ratio;
		
		paint = new Paint();// ��������
		paint.setAntiAlias(true);// �򿪿����
		this.getHolder().addCallback(this);// �����������ڻص��ӿڵ�ʵ����
		//�������������һ��Ҫд����Ȼ���ǵļ��̵ļ�����������
		this.setFocusable(true);
		this.setFocusableInTouchMode(true);
		//����ViewΪ�ɼ�
		this.setVisibility(0);
	}
	
	//���Ʒ���
	public void onDraw(Canvas canvas)
	{
		//ÿ�λ���֮ǰ��Ҫ����
		paint.setColor(Color.BLACK);
		paint.setStyle(Style.FILL);
		canvas.drawRect(-10, -10, Constants.PMX1+10, Constants.PMY1+10, paint);
		
		canvas.save();
		canvas.translate(Constants.LOX, Constants.LOY);
		canvas.scale(Constants.RADIO, Constants.RADIO);
		paint.setColor(Color.BLACK);
		paint.setStyle(Style.FILL);
		canvas.drawRect(-10, -10, Constants.PMX+10, Constants.PMY+10, paint);
		
		//���û�е����Ϸͼ��ͼ��ͻ��ư����׽���
		if(!allShow && !clickGeZiBtn && !clickTowerBtn && !clickMonstersBtn)
		{
			canvas.drawBitmap(xinFeng, 0,0, paint);
			canvas.drawBitmap(tuJian, (Constants.PMX-tuJian.getWidth())/2,Constants.PMY-tuJian.getHeight()-20,paint);
		}
		//�������Ϸͼ��ͼ��
		if(allShow && !clickGeZiBtn && !clickTowerBtn && !clickMonstersBtn)
		{
			canvas.drawBitmap(showAllDisplays, 0,0, paint);
			canvas.drawBitmap(lookGeZis, 163,220, paint);
			canvas.drawBitmap(lookTowers, 140,440, paint);
			canvas.drawBitmap(lookMonsters, 167,690, paint);
		}
		//������˲鿴���ܸ��ӵ�ͼ���
		if(!allShow && clickGeZiBtn && !clickTowerBtn && !clickMonstersBtn)
		{
			canvas.drawBitmap(tuJianGongNengGeZi, 0,0, paint);
			//���ƹ�����Ļ
			canvas.drawBitmap(gunDongTxt, x,y, paint);
			canvas.drawBitmap(mapBitmaps[mapIndex], 80,320, paint);
			canvas.drawBitmap(geZiIntroduces[txtGeZiIndex], 225,214, paint);
		}
		//������˲鿴�����ͼ���
		if(!allShow && !clickGeZiBtn && !clickTowerBtn && clickMonstersBtn)
		{
			canvas.drawBitmap(tuJianMonster, 0,0, paint);
			//���ƹ�����Ļ
			canvas.drawBitmap(gunDongTxt, x,y, paint);
			if(monsterIndex == 2)
				canvas.drawBitmap(monsterBitmaps[monsterIndex],68,308, paint);
			else
				canvas.drawBitmap(monsterBitmaps[monsterIndex],76,316, paint);
			canvas.drawBitmap(monstersIntroduces[txtMonsterIndex], 225,214, paint);
		}
		//������˲鿴������ͼ���
		if(!allShow && !clickGeZiBtn && clickTowerBtn && !clickMonstersBtn)
		{
			canvas.drawBitmap(tuJianTower, 0,0, paint);
			//���ƹ�����Ļ
			canvas.drawBitmap(gunDongTxt, x,y, paint);
			canvas.drawBitmap(towerBitmaps[towerIndex], 62,295, paint);
			canvas.drawBitmap(towersIntroduces[txtTowerIndex],225,214, paint);
		}
		canvas.restore();
	}
	//�ֻ�������
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
        //����ִ�и������������¼�
        return super.onKeyDown(keyCode, event);
    }
	//�����¼��ķ���
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
					//������Ļ�߳�����
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
						//������Ļ�߳�����
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
						//������Ļ�߳�����
						gunDongTxtThread = new GunDongTxtThread(this);
						gunDongTxtThread.start();
						MainGameActivity.sound.playMusic(Constants.BUTTON_PRESS, 0)  ;
					}
				
				//�л������ܸ���display����Ĵ���
				if(x<(45+cardWidth+Constants.LOX)*Constants.RADIO
						&& x>(45+Constants.LOX)*Constants.RADIO
						&& y<(570+cardHeight+Constants.LOY)*Constants.RADIO
						&& y>(570+Constants.LOY)*Constants.RADIO
						&& clickGeZiBtn
					  )
				{
					mapIndex = 2; //���ٸ���
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
					mapIndex = 3; //���ٸ���
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
					mapIndex = 1; //��Ѫ����
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
					mapIndex = 0; //��Ѫ����
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
				

				//�л�������display����Ĵ���
				if(x<(40+cardWidth+Constants.LOX)*Constants.RADIO
						&& x>(40+Constants.LOX)*Constants.RADIO
						&& y<(565+cardHeight+Constants.LOY)*Constants.RADIO
						&& y>(565+Constants.LOY)*Constants.RADIO
						&& clickTowerBtn
					  )
				{
					towerIndex = 0; //��1
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
					towerIndex = 1; //��2
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
					towerIndex = 2; //��3
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
					towerIndex = 3; //��4
					txtTowerIndex = 3;
					MainGameActivity.sound.playMusic(Constants.BUTTON_PRESS, 0)  ;
				}
				
				//�л�������display����Ĵ���
				if(x<(40+cardWidth+Constants.LOX)*Constants.RADIO
						&& x>(40+Constants.LOX)*Constants.RADIO
						&& y<(570+cardHeight+Constants.LOY)*Constants.RADIO
						&& y>(570+Constants.LOY)*Constants.RADIO
						&& clickMonstersBtn
					  )
				{
					monsterIndex = 0; //����1
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
					monsterIndex = 2; //����3
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
					monsterIndex = 1; //����2
					txtMonsterIndex = 1;
					MainGameActivity.sound.playMusic(Constants.BUTTON_PRESS, 0)  ;
				}
			break;
		}
		return super.onTouchEvent(event);
	}
	
	//����ʱ������
	@Override
	public void surfaceCreated(SurfaceHolder holder)
	{
		//��������ͼƬ
		gunDongTxt = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.gundongtxt);
		//introduces���ͼƬ
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
		//����չʾ����
		tuJianGongNengGeZi = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.tujiangongnenggezi);
		tuJianTower = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.tujiantower);
		tuJianMonster = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.tujianmonsters);
		//�������汳��ͼƬ
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
		//��ʼ����ͼ����ͼƬ����ͼ����ͼƬ����
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
		//��ʼ����̨����������
		tower1 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.button_0);
		tower2 = BitmapFactory.decodeResource(this.getResources(),  
				R.drawable.button_1);
		tower3 = BitmapFactory.decodeResource(this.getResources(),   
				R.drawable.button_2);
		tower4 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.button_3);
		towerBitmaps = new Bitmap[]{tower1,tower2,tower3,tower4};
		//��ʼ�����Ｐ��������
		creep_red = BitmapFactory.decodeResource(this.getResources(),  
				R.drawable.bn_creep_red);
		creep_yellow = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.bn_creep_yellow);
		creep_purple = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.bn_creep_purple);
		monsterBitmaps = new Bitmap[]{creep_red,creep_yellow,creep_purple};
		//��ʼ������ͼƬ
		backGround = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.back);
		//��ʼ���ŷ�
		xinFeng = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.xinfeng);
		//��ʼ��ͼ��ͼƬ
		tuJian = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.tujian);
		//������Ϸ������������̣߳�������
		yXFFDrawThread = new DrawMonsterThread(this);
		yXFFDrawThread.start();
	}
	
	public void repaint() 
	{
		SurfaceHolder holder = this.getHolder();
		Canvas canvas = holder.lockCanvas();// ��ȡ����
		try {
			synchronized (holder) {
				onDraw(canvas);// ����
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
	{// ����ʱ������
		yXFFDrawThread.setFlag(false);	
		//���ٵ�ǰSurfaceView
	}

}
