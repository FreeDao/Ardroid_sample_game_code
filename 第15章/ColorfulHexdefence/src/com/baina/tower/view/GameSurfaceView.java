package com.baina.tower.view;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.baina.tower.allactivity.MainGameActivity;
import com.baina.tower.allactivity.R;
import com.baina.tower.allactivity.StartGameActivity;
import com.baina.tower.bullet.BulletList;
import com.baina.tower.constant.Constants;
import com.baina.tower.constant.Map;
import com.baina.tower.game.Game;
import com.baina.tower.game.TowerAdder;
import com.baina.tower.game.Update;
import com.baina.tower.impleclass.Monster;
import com.baina.tower.impleclass.Tower;
import com.baina.tower.mapCapability.CapabilityList;
import com.baina.tower.monsters.MonsterList;
import com.baina.tower.monsters.Monster_Circle;
import com.baina.tower.monsters.Monster_Square;
import com.baina.tower.monsters.Monster_Triangle;
import com.baina.tower.threads.BulletRunThread;
import com.baina.tower.threads.CreateMonster;
import com.baina.tower.threads.DrawMonsterThread;
import com.baina.tower.threads.MonsterRunThread;
import com.baina.tower.threads.UtilThread;
import com.baina.tower.towers.TowerList;
import com.baina.tower.towers.Tower_Laser;
import com.baina.tower.towers.Tower_Missile;
import com.baina.tower.towers.Tower_Shell;
import com.baina.tower.towers.Tower_Wave;
import com.baina.tower.utils.LBX;
import com.baina.tower.utils.SerializableGame;
import com.baina.tower.utils.UpdateBitmap;
import com.baina.tower.utils.Utils;

public class GameSurfaceView extends SurfaceView implements
		SurfaceHolder.Callback // ʵ���������ڻص��ӿ�
{
	StartGameActivity activity;
	public Paint paint;// ����
	public LBX lbx;
	public Update update;//�����ڵ���
	MonsterRunThread mrt;
	DrawMonsterThread dmt;
	public CreateMonster cm;
	public BulletRunThread tft;
	public int mapNum;// Ŀ����
	public Score score;
	public boolean threadIsDie = false;
	public Game game;
	public TowerAdder ta;
	public MonsterList master_list;
	public ArrayList<Utils> utils;
	public TowerList tower_list;
	public CapabilityList capability_list;
	public Bitmap creep_red;
	public Bitmap creep_yellow;
	public Bitmap creep_purple;
	public Bitmap vectoryBitmap;
	Bitmap button_0;
	Bitmap button_1;
	Bitmap button_2;
	Bitmap button_3;
	Bitmap tower1;
	Bitmap tower2;
	Bitmap tower3;
	Bitmap tower4;
	public Bitmap towerUpgrade;
	Bitmap black;
	Bitmap red_put;
	Bitmap start;
	Bitmap end;
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
	public Bitmap sellTower;
			public Bitmap boom1;
			public Bitmap boom2;
			public Bitmap boom3;
			public Bitmap backblack;
			public Bitmap guanQiaDeFen;
			public Bitmap bulletShell;
			boolean firstDie;
			public boolean playDongHua;
			public float shanXingAngle = 0;
			public boolean gameisover = false;
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Bitmap pause;
	Bitmap continueplay;
	Bitmap replay;
	Bitmap mainmenu;
	Bitmap set;
	public Bitmap gameOver;
	Bitmap deFen;
	Bitmap huiHe;
	public Bitmap baoZha1;
	public Bitmap baoZha2;
	public Bitmap baoZha3;
	public Bitmap baoZha4;
	public Bitmap baoZha5;
	public Bitmap baoZha6;
	public boolean drawStartDemo = false;
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//ÿ�����Ƿ���Ե���ı�־λ
	public boolean pao1Click = true;
	public boolean pao2Click = true;
	public boolean pao3Click = true;
	public boolean pao4Click = true;
	//�Ƿ����������ť�ı�־λ
	public boolean isPressOnTower = false;
	public boolean isSell = false;//�����İ�ť
	public boolean isDrawMenu = false;//���Ʋ˵��ı�־λ
	public boolean canUpgrade = true;
	public int getScore = 150;//��ʼ���ķ�����150
	public int totalScore=0;
	public int boshu = 1;
	public int points = getScore;
	
	public int boShuCount = Constants.MASTER_COUNT;//���ڼ�¼������Ϸʱ�ǵڼ����ֵĵڼ�����

	public boolean huiFu = false;     
	InputStream in = null;
	ObjectInputStream oin = null;
	public int life = 10;//��ʼ��ʱ����10����
	public ScreenScaleResult screenScaleResult;
	public boolean vectory = false;
	public boolean isLiang = false;//�ֽ���Ŀ�ĵ��ʱ����һ��
	
	public GameSurfaceView(StartGameActivity activity) {
		super(activity);
		//��Ļ����Ӧ
		screenScaleResult = ScreenScaleUtil.calScale(Constants.PMX1,Constants.PMY1);
		//�洢�����������
		Constants.LOX = screenScaleResult.lucX;
		Constants.LOY = screenScaleResult.lucY;
		Constants.RADIO = screenScaleResult.ratio;
		
		this.activity = activity;
		this.getHolder().addCallback(this);// �����������ڻص��ӿڵ�ʵ����
		paint = new Paint();// ��������
		paint.setAntiAlias(true);// �򿪿����
		mapNum = activity.mapNum;
		//��ȡ��Ϸ����
		if(mapNum == 100)
		{
			huiFu = true;
			try {
				in = this.getContext().openFileInput("game.ytl");
				oin = new ObjectInputStream(in);
				this.life = oin.readInt();
				this.mapNum = oin.readInt();
				this.boshu = oin.readInt();
				this.boShuCount =oin.readInt();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		//�������������һ��Ҫд����Ȼ���ǵļ��̵ļ�����������
		this.setFocusable(true);
		this.setFocusableInTouchMode(true);

	}

	//���ڻ���Surfaceview�����menu
    public boolean onKeyDo(int keyCode, KeyEvent event) 
	{
        if(keyCode == KeyEvent.KEYCODE_MENU)
		{
			//��������˲˵����ͻ��Ʋ˵�  
			isDrawMenu = !isDrawMenu;
			return true;
		}
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
        	if(!gameisover)
        	SerializableGame.saveGameStatus(this);
        }
        return true;
    }
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(gameisover)return true;
		ta.touchEvent(event);
		return true;	
	}

	public void onDraw(Canvas canvas) {
		
		
		paint.setColor(Color.BLACK);
		paint.setStyle(Style.FILL);
		canvas.drawRect(-10, -10, Constants.PMX1+10, Constants.PMY1+10, paint);
		
		canvas.save();
		canvas.translate(Constants.LOX, Constants.LOY);
		canvas.scale(Constants.RADIO, Constants.RADIO);
		paint.setColor(Color.BLACK);
		paint.setStyle(Style.FILL);
		canvas.drawRect(-10, -10, Constants.PMX+10, Constants.PMY+10, paint);
		// ���Ƶ�ͼ
		
//		if(lbx.anmiIndex<lbx.baoZhaShuZu.length-1)
//    	{//����û�в����궯����֡
//    		lbx.anmiIndex=lbx.anmiIndex+1;
//    	}
//		else
//		{
//			lbx.anmiIndex = 0;
//			this.drawStartDemo  = false;
//		}
		lbx.drawMap(canvas, paint);
		
		////////////////////////////////////////////////
		if(playDongHua)
        {  
        	paint.setColor(Color.WHITE);
        	paint.setAlpha(180);
        	paint.setStyle(Style.FILL_AND_STROKE);
        	float startPosition[] = LBX.getPosition(game.source[1], game.source[0]);
            RectF oval2 = new RectF(startPosition[0]-start.getWidth()/2+5, startPosition[1] -start.getHeight()/2+5,startPosition[0]+start.getWidth()/2-5 ,startPosition[1]+start.getHeight()/2-5 );// ���ø��µĳ����Σ�ɨ�����   
            canvas.drawArc(oval2, 90, shanXingAngle, true, paint);  
            // ��������һ��������RectF�������ǵڶ��������ǽǶȵĿ�ʼ�������������Ƕ��ٶȣ����ĸ����������ʱ�����Σ��Ǽٵ�ʱ�򻭻��� 
        }
		paint.setAlpha(255);
		capability_list.draw(canvas);
		
		
		
		// ���б�Ļ���
		master_list.draw(canvas);

		// ���Ʒ�����ʱ���ƶ�ͼ
		ta.draw(canvas);

		// �������б�
		tower_list.draw(canvas);

		// ����ť�Ļ滭
		if(!gameisover){
		if(getScore>=Constants.PUTTOWER1CONSUMESCORE && !isPressOnTower)
		{
			canvas.drawBitmap(button_0, Constants.BUTTON_TOWER_POSITION_X,
					Constants.PMY - Constants.BUTTON_TOWER_LENGTH, paint);
			pao1Click = true;
		}else
		{
			if(!isPressOnTower)
			{
				canvas.drawBitmap(UpdateBitmap.liangDu(button_0,0.4f), Constants.BUTTON_TOWER_POSITION_X,
						Constants.PMY - Constants.BUTTON_TOWER_LENGTH, paint);
				pao1Click = false;
			}
			
		}
		/////////////////
		if(getScore>=Constants.PUTTOWER2CONSUMESCORE && !isPressOnTower)
		{
			canvas.drawBitmap(button_1, Constants.BUTTON_TOWER_POSITION_X+Constants.TOWER_BUTTON_SPAN,
					Constants.PMY - Constants.BUTTON_TOWER_LENGTH, paint);
			pao2Click = true;
		}else
		{
			if(!isPressOnTower)
			{
				canvas.drawBitmap(UpdateBitmap.liangDu(button_1,0.4f), Constants.BUTTON_TOWER_POSITION_X+Constants.TOWER_BUTTON_SPAN,
						Constants.PMY - Constants.BUTTON_TOWER_LENGTH, paint);
				pao2Click = false;
			}
			
		}
		//////////////////////////////////
		if(getScore>=Constants.PUTTOWER3CONSUMESCORE && !isPressOnTower)
		{
			canvas.drawBitmap(button_2, Constants.BUTTON_TOWER_POSITION_X+2*Constants.TOWER_BUTTON_SPAN,
					Constants.PMY - Constants.BUTTON_TOWER_LENGTH, paint);
			pao3Click = true;
		}else
		{
			if(!isPressOnTower)
			{
				canvas.drawBitmap(UpdateBitmap.liangDu(button_2,0.4f), Constants.BUTTON_TOWER_POSITION_X+2*Constants.TOWER_BUTTON_SPAN,
						Constants.PMY - Constants.BUTTON_TOWER_LENGTH, paint);
				pao3Click = false;
			}
			
		}
		///////////////////////////////////////////////////
		if(getScore>=Constants.PUTTOWER4CONSUMESCORE && !isPressOnTower)
		{
			canvas.drawBitmap(button_3, Constants.BUTTON_TOWER_POSITION_X+3*Constants.TOWER_BUTTON_SPAN,
					Constants.PMY - Constants.BUTTON_TOWER_LENGTH, paint);
			pao4Click =  true;
		}else
		{
			if(!isPressOnTower)
			{
				canvas.drawBitmap(UpdateBitmap.liangDu(button_3,0.4f), Constants.BUTTON_TOWER_POSITION_X+3*Constants.TOWER_BUTTON_SPAN,
						Constants.PMY - Constants.BUTTON_TOWER_LENGTH, paint);
				pao4Click = false;
			}
			
		}
		}
		//���Ʒ���
		paint.setStyle(Style.STROKE);
		canvas.drawBitmap(huiHe,Constants.PMX/2-70, 0, paint);
		score.drawSelf(canvas, paint,2);
		canvas.drawBitmap(deFen,0, 0, paint);
		score.drawSelf(canvas, paint,1);
		
		//�����ڵİ�ť�Ļ滭
		if(isPressOnTower)
		{
			
			if(canUpgrade)
			{
				canvas.drawBitmap(towerUpgrade, Constants.PMX/4,
						Constants.PMY - towerUpgrade.getHeight(), paint);
			}
			else
			{
				canvas.drawBitmap(UpdateBitmap.liangDu(towerUpgrade,0.4f), Constants.PMX/4,
						Constants.PMY - towerUpgrade.getHeight(), paint);
			}
			//���������İ�ť
			canvas.drawBitmap(sellTower, 2*Constants.PMX/4,
					Constants.PMY - towerUpgrade.getHeight(), paint);		
			
		}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		if(cm.pause)
		{
			canvas.drawBitmap(continueplay, Constants.PMX-this.pause.getWidth(),
					0, paint);
		}
		else
		{
			canvas.drawBitmap(pause,Constants.PMX-this.pause.getWidth(),
					0, paint);
		}
		
		//��������˵�
		if(isDrawMenu)
		{
			paint.setColor(Color.GRAY);
			paint.setStyle(Style.FILL_AND_STROKE);
			canvas.drawBitmap(replay, 0,
					4*Constants.PMY/5+Constants.PMY/10, paint);
		}
		
		//���ǵ�Ѫ��Ϊ0ʱ
		if(this.life<=0 && !firstDie)
		{
//			this.gameOver();
//			MainGameActivity.sound.playMusic(Constants.sf_game_over, 0)  ;
//			paint.setColor(Color.BLACK);
//			paint.setStyle(Style.FILL);
//			canvas.drawRect(-10, -10, Constants.PMX1+10, Constants.PMY1+10, paint);
//			canvas.drawBitmap(gameOver, 0,0, paint);
			
			
			MainGameActivity.sound.playMusic(Constants.sf_game_over, 0)  ;
			cm.setFlag(false);
			tft.setFlag(false);//ֹͣ�ӵ������ɹ����߳�
			//ֹͣ��Ӧ�߳�֮����Ҫ��ӱ�־λ�� �����ǳصı�ը�����������̲߳���һֹͣ��ֱ������
			float temp[] = LBX.getPosition(game.target[1], game.target[0]);
			Utils util = new Utils(3,temp[0], temp[1], 100,Color.RED,this,15);
			utils.add(util);
			util.start();
			firstDie = true;
			new UtilThread(this,1).start();
			//�˴���Ҫֹͣ���д���========================
		}
//		
//		if(vectory)//���ʤ����
//		{
//			this.gameOver();
//			paint.setColor(Color.BLACK);
//			paint.setStyle(Style.FILL);
//			canvas.drawRect(-10, -10, Constants.PMX1+10, Constants.PMY1+10, paint);
//			canvas.drawBitmap(vectoryBitmap, 0,0, paint);
//			MainGameActivity.sound.playMusic(Constants.vectory, 0)  ;
//		}
		for(int i=0; i<utils.size(); i++){
			if(utils.get(i).flag==true)
				utils.get(i).drawBoom(canvas,paint);
			else {
				utils.remove(i);
			}
		}
		paint.setAlpha(255);
		score.drawSelf(canvas, paint,3);
		if(gameisover)
		{
			canvas.drawBitmap(backblack, 0,0, paint);
			canvas.drawBitmap(gameOver, 176,368, paint);
			canvas.drawBitmap(guanQiaDeFen, 176,368+gameOver.getHeight()+20, paint);
			score.drawSelf(canvas,paint,4);
		}
		canvas.restore();

	}
	
	public void boomTM(){

		for(int i=0; i<master_list.size(); i++){
			Monster monster = master_list.get(i);
			float[] te = monster.getCurrentPoint();
			int color =Color.WHITE;
			if(monster instanceof Monster_Triangle)color =Color.YELLOW;
			else if(monster instanceof Monster_Square)color =Color.RED;
			else if(monster instanceof Monster_Square)color =Color.RED;
			Utils utila = new Utils(1,te[0], te[1], 100,color,this,10);
			utils.add(utila);
			utila.start();
		}
		for(int i=0; i<tower_list.size(); i++){
			int color =Color.WHITE;
			Tower tower = tower_list.get(i);
			if(tower instanceof Tower_Shell)color =Color.WHITE;
			else if(tower instanceof Tower_Laser)color =Color.GREEN;
			else if(tower instanceof Tower_Missile)color =Color.RED;
			else if(tower instanceof Tower_Wave)color =Color.BLUE;
			float[] te = tower.getXY();
			Utils utila = new Utils(1,te[0], te[1], 100,color,this,12);
			utils.add(utila);
			utila.start();
		}
		tower_list.clear();
		master_list.clear();
		cm.setFlag(false);
		mrt.setFlag(false);
		tft.setFlag(false);
		new UtilThread(this,2).start();				//������Ϸ����gamevoer���棬֮����Ϸ���׽���
	}
	public void gameComingToOver(){
		gameisover=true;
		
		new UtilThread(this,3).start();
		
	}
	//��Ϸ����
	public void gameOver()
	{
		//ֹͣ�߳�
		cm.setFlag(false);
		mrt.setFlag(false);
		dmt.setFlag(false);
		tft.setFlag(false);
		//ֹͣ����
		if(MainGameActivity.sound.mp!=null)
		{//�˴��Ĳ������ͷ��Ժ�Ҫ����Ϊ�գ���Ȼ�Ͳ���ȷ��
			MainGameActivity.sound.mp.release(); 
			MainGameActivity.sound.mp = null;
		}
	}

	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {

	}
	
	///////////////////////////////////////////////////////////////////////////////
	//�߳���ͣ
	public void pauseOrContinueThreads()
	{
		cm.pause = !cm.pause;
		mrt.pause = !mrt.pause;
		tft.pause = !tft.pause;
	}
	//����ķ���
	public void replay()
	{
		activity.replay();
	}
	///////////////////////////////////////////////////////////////////////////////
	
	public Bitmap[] getButton(){
		return new Bitmap[]{button_0,button_1,button_2,button_3,
				tower1,tower2,tower3,tower4,red_put};
	}
	
	public Bitmap[] getLBXBit(){
		return new Bitmap[]{	
				black,
			start,
			end,
			blood_up,
			blood_down,
			speed_up,
			speed_down,
			turn_num1,
			turn_num2,
			turn_num3,
			turn_num4,
			turn_num5,
			turn_num6
			};
	}

	public void surfaceCreated(SurfaceHolder holder) {// ����ʱ������  
		guanQiaDeFen = BitmapFactory.decodeResource(this.getResources(),  
				R.drawable.guanqiadefen);
		deFen = BitmapFactory.decodeResource(this.getResources(),  
				R.drawable.defen);
		huiHe = BitmapFactory.decodeResource(this.getResources(),  
				R.drawable.huihe);
		vectoryBitmap= BitmapFactory.decodeResource(this.getResources(),  
				R.drawable.vectory);
		creep_red = BitmapFactory.decodeResource(this.getResources(),  
				R.drawable.bn_creep_red);
		creep_yellow = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.bn_creep_yellow);
		creep_purple = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.bn_creep_purple);
		button_0 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.button_0);
		button_1 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.button_1);
		button_2 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.button_2);
		button_3 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.button_3);
		black = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.black_cell);
		tower1 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.bn_tower_white);
		tower2 = BitmapFactory.decodeResource(this.getResources(),  
				R.drawable.bn_tower_green);
		tower3 = BitmapFactory.decodeResource(this.getResources(),   
				R.drawable.bn_tower_red);
		tower4 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.bn_tower_blue);
		red_put = BitmapFactory.decodeResource(this.getResources(),  
				R.drawable.red_put);
		start= BitmapFactory.decodeResource(this.getResources(),  
				R.drawable.bn_spawn_cell);
		end= BitmapFactory.decodeResource(this.getResources(),
				R.drawable.bn_end_cell);
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
		towerUpgrade= BitmapFactory.decodeResource(this.getResources(),
				R.drawable.bn_tower_update);
		sellTower=BitmapFactory.decodeResource(this.getResources(),
				R.drawable.bn_tower_sell);
		baoZha1=BitmapFactory.decodeResource(this.getResources(),
				R.drawable.explode1);
		baoZha2=BitmapFactory.decodeResource(this.getResources(),
				R.drawable.explode2);
		baoZha3=BitmapFactory.decodeResource(this.getResources(),
				R.drawable.explode3);
		baoZha4=BitmapFactory.decodeResource(this.getResources(),
				R.drawable.explode4);
		baoZha5=BitmapFactory.decodeResource(this.getResources(),
				R.drawable.explode5);
		baoZha6=BitmapFactory.decodeResource(this.getResources(),
				R.drawable.explode6);
		boom1 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.boom4);
		boom3 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.boom10);
		boom2 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.boom6);
		backblack = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.backblack);
		///////////////////////////////////////////////////////////////////////////////
		set=BitmapFactory.decodeResource(this.getResources(),
				R.drawable.set);  
		continueplay=BitmapFactory.decodeResource(this.getResources(),
				R.drawable.continueplay);
		replay=BitmapFactory.decodeResource(this.getResources(),
				R.drawable.replay);  
		mainmenu=BitmapFactory.decodeResource(this.getResources(),
				R.drawable.mainmenu);
		pause=BitmapFactory.decodeResource(this.getResources(),
				R.drawable.pause);
		gameOver=BitmapFactory.decodeResource(this.getResources(),
				R.drawable.gameover);
		bulletShell=BitmapFactory.decodeResource(this.getResources(),
				R.drawable.bullet_shell);
		//////////////////////////////////////////////////////////////////////////////
		//��game����з����л�
		game = new Game(this);
		if(huiFu)
		{
			try
			{
				game.map_tower = (int[][]) oin.readObject();
			}catch(Exception e){}
			
		}
		score = new Score(this)	;
		if(huiFu)
		{
			try{
				this.master_list = (MonsterList) oin.readObject();
				this.master_list.mv = this;
			}catch(Exception e){}
			
			for(Monster m:this.master_list)
			{
				if(m instanceof Monster_Square)
				{
					((Monster_Square) m).creep = this.creep_red;
					((Monster_Square) m).game = this.game;
					((Monster_Square) m).mv = this;
					((Monster_Square) m).paint = this.paint;
					((Monster_Square) m).paint.setAntiAlias(true);
					((Monster_Square) m).paint.setStyle(Style.FILL);
					((Monster_Square) m).paint.setColor(Color.RED);
					((Monster_Square) m).baoZhaShuZu = new Bitmap[]{this.baoZha1,this.baoZha2,this.baoZha3,this.baoZha4,this.baoZha5,this.baoZha6};
				}
				else if(m instanceof Monster_Triangle)
				{
					((Monster_Triangle) m).creep = this.creep_yellow;
					((Monster_Triangle) m).game = this.game;
					((Monster_Triangle) m).mv = this;
					((Monster_Triangle) m).paint = this.paint;
					((Monster_Triangle) m).paint.setAntiAlias(true);
					((Monster_Triangle) m).paint.setStyle(Style.FILL);
					((Monster_Triangle) m).paint.setColor(Color.RED);
					((Monster_Triangle) m).baoZhaShuZu = new Bitmap[]{this.baoZha1,this.baoZha2,this.baoZha3,this.baoZha4,this.baoZha5,this.baoZha6};
				}
				else if(m instanceof Monster_Circle)
				{
					((Monster_Circle) m).creep = this.creep_purple;
					((Monster_Circle) m).game = this.game;
					((Monster_Circle) m).mv = this;
					((Monster_Circle) m).paint = this.paint;
					((Monster_Circle) m).paint.setAntiAlias(true);
					((Monster_Circle) m).paint.setStyle(Style.FILL);
					((Monster_Circle) m).paint.setColor(Color.RED);
					((Monster_Circle) m).baoZhaShuZu = new Bitmap[]{this.baoZha1,this.baoZha2,this.baoZha3,this.baoZha4,this.baoZha5,this.baoZha6};
				}
			}
		}
		else
		{
			master_list = new MonsterList(this);
		}
		
		
		if(huiFu)
		{
			try{
				tower_list = (TowerList) oin.readObject();
				
				this.isDrawMenu = oin.readBoolean(); 
				this.getScore = oin.readInt();
				this.points = oin.readInt();
				this.shanXingAngle = oin.readFloat();
				in.close();
				oin.close();
				//�ָ�ͼƬ
				for(Tower m:this.tower_list)
				{
					
					if(m instanceof Tower_Shell)
					{
						((Tower_Shell) m).bitmap = ((Tower_Shell) m).bitmap.createBitmap(this.tower1,(((Tower_Shell) m).currentState-1)*48,0, 48, 48);
						((Tower_Shell) m).mv = this;
						((Tower_Shell) m).paint = this.paint;
						((Tower_Shell) m).master_list = this.master_list;
						((Tower_Shell) m).bulletList = new BulletList(); 
						((Tower_Shell) m).all = this.tower1;
					}
					else if(m instanceof Tower_Laser)
					{
						((Tower_Laser) m).bitmap = ((Tower_Laser) m).bitmap.createBitmap(this.tower2,(((Tower_Laser) m).currentState-1)*48,0, 48, 48);
						((Tower_Laser) m).mv = this;
						((Tower_Laser) m).paint = this.paint;  
						((Tower_Laser) m).master_list = this.master_list;
						((Tower_Laser) m).bulletList = new BulletList();
						((Tower_Laser) m).all = this.tower2;
					}
					else if(m instanceof Tower_Missile)
					{
						((Tower_Missile) m).bitmap = ((Tower_Missile) m).bitmap.createBitmap(this.tower3,(((Tower_Missile) m).currentState-1)*48,0, 48, 48);
						((Tower_Missile) m).mv = this;
						((Tower_Missile) m).paint = this.paint;
						((Tower_Missile) m).master_list = this.master_list;
						((Tower_Missile) m).bulletList = new BulletList();
						((Tower_Missile) m).all = this.tower3;
					}
					else if(m instanceof Tower_Wave)
					{
						((Tower_Wave) m).bitmap = ((Tower_Wave) m).bitmap.createBitmap(this.tower4,(((Tower_Wave) m).currentState-1)*48,0, 48, 48);
						((Tower_Wave) m).mv = this;
						((Tower_Wave) m).paint = this.paint;
						((Tower_Wave) m).master_list = this.master_list;
						((Tower_Wave) m).bulletList = new BulletList();
						((Tower_Wave) m).all = this.tower4;
					}
				}
			}catch(Exception e){}
		}else
		{
			tower_list = new TowerList();
		}
		
		capability_list = new CapabilityList(this);
		update = new Update();
		utils = new ArrayList<Utils>();
		
		lbx = new LBX(black,this);
		if(huiFu)
		{
			tft = new BulletRunThread(tower_list);
			tft.start();
		}
		else
		{
			tft = new BulletRunThread(tower_list);//����̵߳�����Ϊ�����ʱ
		}
		
		ta = new TowerAdder(this);
		if(this.boshu%3==1)
		cm = new CreateMonster(master_list, this, creep_red);
		if(this.boshu%3==2)
			cm = new CreateMonster(master_list, this, creep_yellow);
		if(this.boshu%3==0)
			cm = new CreateMonster(master_list, this, creep_purple);
		if(huiFu)
		{
			cm.count = this.boShuCount;
			cm.isFirst = 2;
		}
		cm.start();
		boShuCount = Constants.MASTER_COUNT;//����ֵ��̵߳�һ��������ͻָ�boShuCount��ֵ
		
		
		mrt = new MonsterRunThread(this);
		mrt.start();
		dmt = new DrawMonsterThread(this);
		dmt.start();
		

	}

	public void repaint() {
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
	


	public void surfaceDestroyed(SurfaceHolder arg0) {// ����ʱ������
		// ������м��ϣ��������õ��Ķ�����ı���
		activity.executeDatebase();
		game.clearState();
		master_list.clear();
		tower_list.clear();
		
		// ͣ��run �滭 �߳�
		cm.setFlag(false);
		mrt.setFlag(false);
		dmt.setFlag(false);
		tft.setFlag(false);
		try {
			dmt.join();
			mrt.join();
			tft.join();
			cm.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(MainGameActivity.sound.mp!=null)
		{//�˴��Ĳ������ͷ��Ժ�Ҫ����Ϊ�գ���Ȼ�Ͳ���ȷ��
			MainGameActivity.sound.mp.release(); 
			MainGameActivity.sound.mp = null;
		}
	}
}