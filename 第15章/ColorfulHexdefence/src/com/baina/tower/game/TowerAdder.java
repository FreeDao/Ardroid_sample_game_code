package com.baina.tower.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.MotionEvent;

import com.baina.tower.allactivity.MainGameActivity;
import com.baina.tower.constant.Constants;
import com.baina.tower.constant.Map;
import com.baina.tower.impleclass.Tower;
import com.baina.tower.monsters.MonsterList;
import com.baina.tower.threads.BulletRunThread;
import com.baina.tower.towers.TowerList;
import com.baina.tower.towers.Tower_Shell;
import com.baina.tower.towers.Tower_Laser;
import com.baina.tower.towers.Tower_Missile;
import com.baina.tower.towers.Tower_Wave;
import com.baina.tower.utils.LBX;
import com.baina.tower.view.GameSurfaceView;

public class TowerAdder{
	transient BulletRunThread tft;
	transient GameSurfaceView mv;
	int tower_draw = 0;//绘制塔按钮的编号 0为不画，1234分别代表不同塔
	float []position_botton = new float[2];
	float []move_Button = new float[2];
	int []move_Button_Pre = new int[2];
	TowerList tower_list;
	transient Bitmap bitmap1 ;
	transient Bitmap bitmap2 ;
	transient Bitmap bitmap3 ;
	transient Bitmap bitmap4 ;
	transient Bitmap tower1;
	transient Bitmap tower2;
	transient Bitmap tower3;
	transient Bitmap tower4;
	transient Bitmap temp;
	Paint paint;
	transient Bitmap red_put;
	boolean flagMove = true;
	boolean isDown = false;
	boolean putRed = false;
	boolean isSellTower = false;
	Game game;
	//用于记录我们点击的炮的行和列
	int[] realRowCol = new int[2]; 
	Tower t;//我们点击的炮

	transient static final int MOVE_THRESHOLD=10;
	
	MonsterList master_list ;
	boolean firstAddTower=true;
	public TowerAdder(GameSurfaceView mySurfaceView) {
		this.mv = mySurfaceView;
		tower_list = mySurfaceView.tower_list;
		bitmap1 = mySurfaceView.getButton()[0];
		bitmap2 =  mySurfaceView.getButton()[1];
		bitmap3 =  mySurfaceView.getButton()[2];
		bitmap4 =  mySurfaceView.getButton()[3];
		tower1 =  mySurfaceView.getButton()[4];
		tower2 =  mySurfaceView.getButton()[5];
		tower3 =  mySurfaceView.getButton()[6];
		tower4 =  mySurfaceView.getButton()[7];
		red_put = mySurfaceView.getButton()[8];
		paint = new Paint();// 创建画笔
		paint.setAntiAlias(true);
		game = mySurfaceView.game;
		this.master_list = mySurfaceView.master_list;
		tft = mySurfaceView.tft;
	}
	
	public boolean touchEvent(MotionEvent event){
		float x = event.getX();
		float y = event.getY();
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			if(!mv.cm.pause && x<(Constants.BUTTON_TOWER_POSITION_X+Constants.BUTTON_TOWER_LENGTH +Constants.LOX)*Constants.RADIO
					&& x>(Constants.BUTTON_TOWER_POSITION_X+Constants.LOX)*Constants.RADIO	
					&& y<(Constants.PMY+Constants.LOY)*Constants.RADIO
					&& y >(Constants.PMY-Constants.BUTTON_TOWER_LENGTH+Constants.LOY)*Constants.RADIO
					&&mv.pao1Click
					&& !mv.isPressOnTower
					&& !mv.isDrawMenu)
			{
				MainGameActivity.sound.playMusic(Constants.TOWER_PICKUP, 0)  ;
				flagMove=false;
				isDown =true;
				position_botton[0]=event.getX()/Constants.RADIO-Constants.LOX;
				position_botton[1]=event.getY()/Constants.RADIO-Constants.LOY;
				move_Button[0]=event.getX();
				move_Button[1]=(event.getY()- Constants.FINGERTOTARGET);
				tower_draw = 1;
			}else if(!mv.cm.pause && x<(Constants.BUTTON_TOWER_POSITION_X+Constants.BUTTON_TOWER_LENGTH+Constants.TOWER_BUTTON_SPAN+Constants.LOX)*Constants.RADIO 
					&& x>(Constants.BUTTON_TOWER_POSITION_X+Constants.TOWER_BUTTON_SPAN+Constants.LOX )*Constants.RADIO 
					&& y<(Constants.PMY+Constants.LOY)*Constants.RADIO 
					&& y >(Constants.PMY-Constants.BUTTON_TOWER_LENGTH+Constants.LOY)*Constants.RADIO
					&&mv.pao2Click
					&& !mv.isPressOnTower
					&& !mv.isDrawMenu)
			{
				//这是第二个按钮
				MainGameActivity.sound.playMusic(Constants.TOWER_PICKUP, 0)  ;
				flagMove=false;
				isDown =true;
				position_botton[0]=event.getX()/Constants.RADIO-Constants.LOX;
				position_botton[1]=event.getY()/Constants.RADIO-Constants.LOY;
				move_Button[0]=event.getX();
				move_Button[1]=event.getY()- Constants.FINGERTOTARGET;
				tower_draw = 2;
				
				
				
			}else if(!mv.cm.pause && x<(Constants.BUTTON_TOWER_POSITION_X+Constants.BUTTON_TOWER_LENGTH+2*Constants.TOWER_BUTTON_SPAN+Constants.LOX )*Constants.RADIO
					&& x>(Constants.BUTTON_TOWER_POSITION_X+2*Constants.TOWER_BUTTON_SPAN+Constants.LOX )*Constants.RADIO
					&& y<(Constants.PMY+Constants.LOY)*Constants.RADIO
					&& y >(Constants.PMY-Constants.BUTTON_TOWER_LENGTH+Constants.LOY)*Constants.RADIO
					&&mv.pao3Click
					&& !mv.isPressOnTower
					&& !mv.isDrawMenu)
			{
				MainGameActivity.sound.playMusic(Constants.TOWER_PICKUP, 0)  ;
				flagMove=false;
				isDown =true;
				position_botton[0]=event.getX()/Constants.RADIO-Constants.LOX;
				position_botton[1]=event.getY()/Constants.RADIO-Constants.LOY;
				move_Button[0]=event.getX();
				move_Button[1]=event.getY()- Constants.FINGERTOTARGET;
				tower_draw = 3;
				
				
			}else if(!mv.cm.pause && x<(Constants.BUTTON_TOWER_POSITION_X+Constants.BUTTON_TOWER_LENGTH+3*Constants.TOWER_BUTTON_SPAN +Constants.LOX)*Constants.RADIO
					&& x>(Constants.BUTTON_TOWER_POSITION_X+3*Constants.TOWER_BUTTON_SPAN+Constants.LOX) *Constants.RADIO
					&& y<(Constants.PMY+Constants.LOY) *Constants.RADIO
					&& y >(Constants.PMY-Constants.BUTTON_TOWER_LENGTH+Constants.LOY)*Constants.RADIO
					&&mv.pao4Click
					&& !mv.isPressOnTower
					&& !mv.isDrawMenu)
			{
				MainGameActivity.sound.playMusic(Constants.TOWER_PICKUP, 0)  ;
				flagMove=false;
				isDown =true;
				position_botton[0]=event.getX()/Constants.RADIO-Constants.LOX;//换算成我们800*480的分辨率的进行计算
				position_botton[1]=event.getY()/Constants.RADIO-Constants.LOY;//换算成我们800*480的分辨率的进行计算
				move_Button[0]=event.getX();
				move_Button[1]=event.getY()- Constants.FINGERTOTARGET;
				tower_draw = 4;	
			}
			
			else if(!mv.cm.pause && x<(2*Constants.PMX/4+mv.sellTower.getWidth()+Constants.LOX)*Constants.RADIO
					&& x>(2*Constants.PMX/4+Constants.LOX) *Constants.RADIO
					&& y<(Constants.PMY+Constants.LOY)*Constants.RADIO
					&& y >(Constants.PMY-mv.sellTower.getHeight()+Constants.LOY)*Constants.RADIO
					&&mv.isPressOnTower
					&& !mv.isDrawMenu)
			{
				MainGameActivity.sound.playMusic(Constants.TOWER_PICKUP, 0)  ;
				mv.update.sell(t);
				game.map_tower[t.getRowCol()[0]][t.getRowCol()[1]] = 0;
				mv.tower_list.remove(t);
			}
			else if(!mv.cm.pause && x<(Constants.PMX/4+mv.towerUpgrade.getWidth()+Constants.LOX)*Constants.RADIO
					&& x>(Constants.PMX/4+Constants.LOX) *Constants.RADIO
					&& y<(Constants.PMY+Constants.LOY)*Constants.RADIO
					&& y >(Constants.PMY-mv.towerUpgrade.getHeight()+Constants.LOY)*Constants.RADIO
					&&mv.isPressOnTower
					&& !mv.isDrawMenu)
			{
				MainGameActivity.sound.playMusic(Constants.TOWER_PICKUP, 0)  ;
				mv.update.upDateTower(t);
			}
			mv.isPressOnTower = false;
			
			if(x<(Constants.PMX+Constants.LOX)*Constants.RADIO
					&& x>(Constants.PMX - mv.pause.getWidth()+Constants.LOX) *Constants.RADIO
					&& y<(mv.pause.getHeight()+Constants.LOY)*Constants.RADIO
					&& y >(0+Constants.LOY)*Constants.RADIO
					)
			{
				//暂停按钮
				MainGameActivity.sound.playMusic(Constants.TOWER_PICKUP, 0)  ;
				mv.pauseOrContinueThreads();
			}
			else if(x<(Constants.PMX+Constants.LOX)*Constants.RADIO
					&& x>(0+Constants.LOX )*Constants.RADIO
					&& y<(Constants.PMY+Constants.LOY)*Constants.RADIO
					&& y >(4*Constants.PMY/5+Constants.PMY/10+Constants.LOY)*Constants.RADIO
					&&!mv.isPressOnTower
					&& mv.isDrawMenu)
			{
				//重玩游戏按钮
				MainGameActivity.sound.playMusic(Constants.TOWER_PICKUP, 0)  ;
				mv.replay();
				mv.isDrawMenu = false;
				
			}

			int[] rrll = LBX.getRowcol(event.getX()/Constants.RADIO-Constants.LOX, event.getY()/Constants.RADIO-Constants.LOY);
			if(!mv.cm.pause && rrll[0]>=0 && rrll[0]<= Map.MAP_DATA[0].length && rrll[1]>=0 && rrll[1]<=Map.MAP_DATA[0][0].length)
			{
				for(int i=0;i<mv.tower_list.size();i++)
				{
					t = mv.tower_list.get(i);
					int[] rowCol = t.getRowCol();
					if(rowCol[0]==rrll[0] && rowCol[1]==rrll[1])
					{
						realRowCol[0] = rowCol[0];
						realRowCol[1] = rowCol[1];
						mv.isPressOnTower = true;
						if(t.getCurrentState()<=3 && t.getCurrentUpdatePrice()<=mv.getScore){
							mv.canUpgrade = true;
						}else {
							mv.canUpgrade = false;
						}
						break;
					}
				}
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if(flagMove!=true && isDown ==true)
        	{//若当前还没有判定为移动动作，则需要执行判断逻辑        		
        		//计算X\Y方向的移动差
        		float dx=Math.abs(event.getX()/Constants.RADIO-Constants.LOX-position_botton[0]);
        		float dy=Math.abs(event.getY()/Constants.RADIO-Constants.LOY-position_botton[1]);
        		//若大于阈值则判定为移动动作
        		//之所以要进行阈值判断是因为人手指在真机上总会
        		//有一点抖动，若有一点抖动就算移动就永远不会产生
        		//点击事件了
        		if(dx>MOVE_THRESHOLD||dy>MOVE_THRESHOLD)
        		{
        			flagMove=true;
        		}
        	}else{
        		if(isDown)
        		{
        			
	        		
					position_botton[0]=x/Constants.RADIO-Constants.LOX;////////////////////////////用于不同的分辨率时触摸数据的还原
					position_botton[1]=y/Constants.RADIO-Constants.LOY;////////////////////////////////
					int[] temp1 = LBX.getRowcol(x/Constants.RADIO-Constants.LOX, (y - Constants.FINGERTOTARGET)/Constants.RADIO-Constants.LOY);/////////////////////////
					if(temp1[0]!=move_Button_Pre[0] || temp1[1]!=move_Button_Pre[1]){
						move_Button_Pre[0]=temp1[0];
						move_Button_Pre[1]=temp1[1];
						
						move_Button = LBX.getPosition(temp1[0], temp1[1]);
						if(isEmpty(temp1[0], temp1[1]) && !game.isGetInWay(temp1[0], temp1[1])){
							
							putRed = false;
							
						}else{
							putRed = true;
						}
					
					}
        		}
				
        	}	
			
		
			break;
		case MotionEvent.ACTION_UP:
			isDown = false;
			if(flagMove){
				
				int []temp = getPoint(move_Button[0], move_Button[1]);
//拿到地图坐标判断是否可以置于地图上，可以则需要更新地图，然后怪列表
//循环判断路径，自己实现自己的方法。
				if(isEmpty(temp[0], temp[1]) && !game.isGetInWay(temp[0], temp[1])){
					float [] temp1=LBX.getPosition(temp[0], temp[1]);
					switch(tower_draw){
					case 1:
						if(mv.getScore>=Constants.PUTTOWER1CONSUMESCORE)
						{
							MainGameActivity.sound.playMusic(Constants.TOWER_PLACE, 0)  ;
							tower_list.add(new Tower_Shell(temp1[0], temp1[1],  tower1, mv));
							mv.getScore -= Constants.PUTTOWER1CONSUMESCORE;
							setCurrentMap(temp[0], temp[1]);
						}
						
					break;
					case 2:
						if(mv.getScore>=Constants.PUTTOWER2CONSUMESCORE)
						{
							MainGameActivity.sound.playMusic(Constants.TOWER_PLACE, 0)  ;
							tower_list.add(new Tower_Laser(temp1[0], temp1[1],  tower2, mv));
							mv.getScore -= Constants.PUTTOWER2CONSUMESCORE;
							setCurrentMap(temp[0], temp[1]);
						}
						
					break;
					case 3:
						if(mv.getScore>=Constants.PUTTOWER3CONSUMESCORE)
						{
							MainGameActivity.sound.playMusic(Constants.TOWER_PLACE, 0)  ;
							tower_list.add(new Tower_Missile(temp1[0], temp1[1],  tower3, mv));
							mv.getScore -= Constants.PUTTOWER3CONSUMESCORE;
							setCurrentMap(temp[0], temp[1]);
						}
						
					break;
					case 4:
						if(mv.getScore>=Constants.PUTTOWER4CONSUMESCORE)
						{
							MainGameActivity.sound.playMusic(Constants.TOWER_PLACE, 0)  ;
							tower_list.add(new Tower_Wave(temp1[0], temp1[1],  tower4, mv));
							mv.getScore -= Constants.PUTTOWER4CONSUMESCORE;
							setCurrentMap(temp[0], temp[1]);
						}
						
					break;
					}
					
					

					
									
//将当前的地图设置成加入塔之后的地图,这时需要重新寻找路径，加入一个判断的变量来进行
//Hashmap的更新，以及路线ArrayList的更新
					this.master_list.findWayAll(temp1);
					if(!mv.huiFu)
					{
						if(firstAddTower ){
							tft.start();
							firstAddTower=false;
						}
					}
				}
				flagMove = true;
				putRed = false;
				
			}
			tower_draw = 0;
			break;
		
		}
		return true;
	}
	private void setCurrentMap(int x, int y) {
		game.setMap_Tower(x, y);

	}

	public void draw(Canvas canvas){
		
		//因为这是移动过程中绘制的，所以这里我们画布不用缩放了
		paint.setStyle(Style.FILL_AND_STROKE);
		paint.setARGB(128, 200, 200, 200);
		switch(tower_draw){
		case 0: break;
		case 1:
			canvas.drawCircle(move_Button[0], move_Button[1],
					Constants.TOWER1_R[0], paint);
			canvas.drawBitmap(Bitmap.createBitmap(tower1, 0, 0, 48, 48), move_Button[0]-Bitmap.createBitmap(tower1, 0, 0, 48, 48).getWidth()/2, 
					move_Button[1]-Bitmap.createBitmap(tower1, 0, 0, 48, 48).getHeight()/2, paint);
		       break;
		case 2:
		
		canvas.drawCircle(move_Button[0], move_Button[1],
				Constants.TOWER2_R[0], paint);
		canvas.drawBitmap(Bitmap.createBitmap(tower2, 0, 0, 48, 48), move_Button[0]-Bitmap.createBitmap(tower2, 0, 0, 48, 48).getWidth()/2, 
				move_Button[1]-Bitmap.createBitmap(tower2, 0, 0, 48, 48).getHeight()/2, paint);
		break;
		case 3: 
			canvas.drawCircle(move_Button[0], move_Button[1],
					Constants.TOWER3_R[0], paint);
			canvas.drawBitmap(Bitmap.createBitmap(tower3, 0, 0, 48, 48), move_Button[0]-Bitmap.createBitmap(tower3, 0, 0, 48, 48).getWidth()/2, 
					move_Button[1]-Bitmap.createBitmap(tower3, 0, 0, 48, 48).getHeight()/2, paint);
			break;
		
		case 4:
			canvas.drawCircle(move_Button[0], move_Button[1],
					Constants.TOWER4_R[0], paint);
			canvas.drawBitmap(Bitmap.createBitmap(tower4, 0, 0, 48, 48), move_Button[0]-Bitmap.createBitmap(tower4, 0, 0, 48, 48).getWidth()/2, 
					move_Button[1]-Bitmap.createBitmap(tower4, 0, 0, 48, 48).getHeight()/2, paint);
			break;
		
		}
		
		if(putRed ){
			canvas.drawBitmap(red_put, move_Button[0]-red_put.getWidth()/2, move_Button[1]-red_put.getHeight()/2, paint);
		}
		
		//绘制我们点击的炮周围的圆形
		if(mv.isPressOnTower)
		{
			canvas.drawCircle(t.getXY()[0], t.getXY()[1],
					t.getR(), paint);
		}
		
		
	}
	
	//根据屏幕坐标得到地图坐标
	public int[] getPoint(float x, float y){
		int []temp = LBX.getRowcol(x, y);
		return temp;
	}
	//判断该坐标是否位于现在地图上
	public boolean isEmpty(int x, int y){
		

		if( game.map_tower[x][y]==0){
			return true;
		}
		return false;
	}
	public boolean isFindWay(int[] temp){
		
		return true;	
	}
	
}
