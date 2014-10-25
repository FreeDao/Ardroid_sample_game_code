package com.baina.tower.monsters;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

import com.baina.tower.allactivity.MainGameActivity;
import com.baina.tower.constant.Constants;
import com.baina.tower.constant.Map;
import com.baina.tower.game.Game;
import com.baina.tower.impleclass.Monster;
import com.baina.tower.utils.LBX;
import com.baina.tower.utils.Utils;
import com.baina.tower.view.GameSurfaceView;

public class Monster_Circle implements Monster,Externalizable{

	
	transient static int UNIT_SPEED = Constants.MASTER1_UNIT_SPEED;//这个是地图两个点之间的分隔
	boolean flag_controlDirection;
	float totalBlood = Constants.MASTERNUM3BLOOD;//总血量
	float currentBlood = Constants.MASTERNUM3BLOOD;//当前怪的血量
	boolean inCapability;
	
	public Monster_Circle(){}//实现了Externalizable接口，必须有午餐构造器
	public Monster_Circle(GameSurfaceView mv,Bitmap creep,float totalBlood)
	{
		this.mv = mv;
		this.totalBlood = totalBlood;
		this.currentBlood=totalBlood;
		baoZhaShuZu = new Bitmap[]{mv.baoZha1,mv.baoZha2,mv.baoZha3,mv.baoZha4,mv.baoZha5,mv.baoZha6};
		x = Map.source[mv.mapNum][0];
		y = Map.source[mv.mapNum][1];
		nextX =Map.source[mv.mapNum][0];
		nextY = Map.source[mv.mapNum][1];
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.RED);
		paint.setStyle(Style.FILL);
		this.creep = creep;
		game = mv.game;
		currentPointX = LBX.getPosition(y, x)[0];
		currentPointY = LBX.getPosition(y, x)[1];
		findWay();
	}

	boolean live = true;
	int bs;

	int x;int y;int nextX;int nextY;
	public transient Bitmap creep;
	public GameSurfaceView mv;
	public Paint paint;
	public Game game;
	HashMap<String,int[][]> hm;
	ArrayList<int[]>path;//存放修正后的路径，方便思维
	int BScount = 0;
	float pianyiX;
	float pianyiY;//这里的偏移是怪从地图的一个点想另一个点行走时后的过度。“视觉上的流畅”
	int location[]=new int[2];//用来解决怪跳
	boolean currentRunning = false;
	public float currentPointX;
	public float currentPointY;//记录当前怪的像素点

	int bloodTime = 0;//血条存在的时间
	//以下是处理加速减速的成员变量
	boolean isSpeed_Up = false;
	boolean isSpeed_Down = false;
	boolean isGo_Direction = false;
	int speedCount = 0;
	//visited数组，保证怪不经过该点
	ArrayList<int[]> visited  = new ArrayList<int[]>();
	//爆炸动画是否开始标志
	boolean anmiStart=false;
	//爆炸动画帧索引
	int anmiIndex=0;
	public Bitmap[] baoZhaShuZu;

	@Override
	public void draw(Canvas canvas) {
		if(!anmiStart)
		{
			canvas.drawBitmap(creep, currentPointX-creep.getWidth()/2, 
					currentPointY-creep.getHeight()/2, paint);
			if(bloodTime<=0){
				return;
			}else{
				if(currentBlood>(totalBlood*2/3)){
					paint.setColor(Color.GREEN);
				}else if(currentBlood>(totalBlood/3)){
					paint.setColor(Color.YELLOW);
				}else{
					paint.setColor(Color.RED);
				}
				paint.setStyle(Style.FILL_AND_STROKE);
				canvas.drawRect(currentPointX-creep.getWidth()/2,currentPointY-creep.getHeight()/2-10,
						currentPointX-creep.getWidth()/2+creep.getWidth()*(currentBlood/totalBlood),currentPointY-creep.getHeight()/2-5,paint);
				bloodTime--;
				
			}
		}
		else
		{
//			canvas.drawBitmap(baoZhaShuZu[anmiIndex], currentPointX-baoZhaShuZu[anmiIndex].getWidth()/2, 
//					currentPointY-baoZhaShuZu[anmiIndex].getHeight()/2, paint);
		}
		
		
	}
	
	

	public void run(){
		if(!anmiStart)
		{
			speedCount++;
			if(BScount>=UNIT_SPEED){
				BScount = 0;
				currentRunning= false;
				pianyiX=0;pianyiY=0;
			}
				if(currentRunning)
				{
					float[] p1=LBX.getPosition(y, x);
					float[] p2=LBX.getPosition(nextY, nextX);	

					pianyiX = (p2[0]-p1[0])/UNIT_SPEED*BScount;
					pianyiY = (p2[1]-p1[1])/UNIT_SPEED*BScount;
					currentPointX = p1[0]+pianyiX;
					currentPointY = p1[1]+pianyiY;
					
					BScount++;
				}
				else
				{
					
					if(bs>0){
						
										
						int[] tempa = path.get(bs);
						this.x = tempa[0];
						this.y = tempa[1];
						
						float[] pp=LBX.getPosition(y, x);
						currentPointX = pp[0]+pianyiX;
						currentPointY = pp[1]+pianyiY;	
						
						int[] tempc = path.get(bs-1);
						nextX = tempc[0];
						nextY = tempc[1];
						
						location[0] = nextX;
						location[1] = nextY;					
						bs--;
						currentRunning = true;
						float[] p1=LBX.getPosition(y, x);
						float[] p2=LBX.getPosition(nextY, nextX);	

						pianyiX = (p2[0]-p1[0])/UNIT_SPEED*BScount;
						pianyiY = (p2[1]-p1[1])/UNIT_SPEED*BScount;
						currentPointX = p1[0]+pianyiX;
						currentPointY = p1[1]+pianyiY;
						
						BScount++;
					}else{
						if(!anmiStart)
						{
							live = false;
							if(mv.life > 0)
							{
								mv.life--;
								mv.isLiang = true;
								float temp[] = LBX.getPosition(game.target[1], game.target[0]);
								Utils util = new Utils(3,temp[0], temp[1], 70,Color.RED,mv,14);
								mv.utils.add(util);
								util.start();
								MainGameActivity.sound.playMusic(Constants.targetbaozha, 0) ;
							}
						}
					}
				}
		}else
		{
			//动画开始后换帧
	    	if(anmiIndex<baoZhaShuZu.length-1)
	    	{//动画没有播放完动画换帧
	    		Constants.sleep(10);
	    		anmiIndex=anmiIndex+1;
	    	}
	    	else
	    	{//动画播放完
	    		setLive(false);
	    		mv.getScore += Constants.KILLMASTER3GETSCORE;
	    		mv.totalScore+= Constants.KILLMASTER3GETSCORE;
	    	}
		}
 
	}
	
	public ArrayList<int[]> getPath(){
		int[] target = new int[]{game.target[0],game.target[1]};
		path = new ArrayList<int[]>();
		while(true){
			int[][]temp = hm.get(target[0]+":"+target[1]);
			path.add(temp[0]);
			if(temp[1][0]==this.nextX && temp[1][1]==nextY)
			{
				path.add(temp[1]);
				break;
			}
			
			target=temp[1];
		}
		bs = path.size()-1;
		return path;
	}
	
	
	
	@Override
	public void findWay() {
		try{
			hm = (HashMap<String,int[][]>)(game.BFSAStar(game.getMap_Tower(),nextX, nextY));
	
		}catch(Exception e){
			
		}
		getPath(); 
		game.clearState();
		isGo_Direction = false;
	}
	
	public void findWay2() {
		isGo_Direction = false;
		if(!flag_controlDirection)return;
		int[][] temp = new int[game.map_tower.length][game.map_tower[0].length];
		for(int i=0; i<game.map_tower.length; i++){
			for(int j=0; j<game.map_tower[0].length; j++){
				temp[i][j] = game.map_tower[i][j];
			}
		}
		for(int i=0; i<visited.size(); i++){
			temp[visited.get(i)[0]][visited.get(i)[1]] = 1;
		}
		try{
		hm = (HashMap<String,int[][]>)(game.BFSAStar(temp,nextX, nextY));
		}catch(Exception e){
			visited.clear();
			game.clearState();
			findWay();
		}
		getPath(); 
		game.clearState();
		flag_controlDirection = false;
	}	
	
	
	@Override
	public void updateWay(int row , int col) {
		flag_controlDirection = true;
		if(isGo_Direction)return;
		
		isGo_Direction = true;
		path = new ArrayList<int[]>();
		path.add(new int[]{location[0]+col,location[1]+row});
		path.add(location);
		bs = path.size() - 1;
	
	}
	@Override
	public boolean isLive() {
		
		return live;
	}
	@Override
	public int[] getLocation() {
		
		return location;
	}
	@Override
	public float[] getCurrentPoint() {
		
		return new float[]{currentPointX,currentPointY};
	}
	@Override
	public void setLive(boolean live) {
		this.live=live;
	}
	@Override
	public boolean decreaseBlood(float damage)
	{
		if(!anmiStart)
		{
			currentBlood -= damage;
			bloodTime = 30;
			if(currentBlood>totalBlood){
				currentBlood = totalBlood;
			}
			if(currentBlood<=0)
			{
				//血量没有时，播放动画
				anmiStart = true;
				Utils util = new Utils(1,currentPointX, currentPointY, 20,Color.MAGENTA,mv,4);
				mv.utils.add(util);
				util.start();
				MainGameActivity.sound.playMusic(Constants.sf_creep_die_2, 0)  ;
			}
		}
			return true;
	}
	@Override
	public float getMasterLength() {
		
		return Constants.MASTER1_BODY_WIDTH;
	}
	@Override
	public void setSpeedUp() {
		BScount+=1;
		
	}
	
	public void setSpeedDown(){
		if(speedCount%3==0)
		{
			BScount-=1;
		}
		speedCount++;
	}
	public void setVisited(int x,int y) {
		
		visited.add(new int[]{x,y});
	
	}
	@Override
	public boolean isInCapability() {
		
		return inCapability;
	}
	
	public void setInCapability(boolean temp){
		this.inCapability = temp;
	}
	
	@Override
	public void readExternal(ObjectInput input) throws IOException,
			ClassNotFoundException
	{
		this.live = input.readBoolean();
		this.bs = input.readInt();
		this.inCapability = input.readBoolean();
		this.flag_controlDirection = input.readBoolean();
		this.x = input.readInt();
		this.y = input.readInt();
		this.nextX = input.readInt();
		this.nextY = input.readInt();
		this.hm = (HashMap<String, int[][]>) input.readObject();
		this.path = (ArrayList<int[]>) input.readObject();
		this.BScount = input.readInt();
		this.pianyiX = input.readFloat();
		this.pianyiY = input.readFloat();
		this.location = (int[]) input.readObject();
		this.currentRunning = input.readBoolean();
		this.currentPointX = input.readFloat();
		this.currentPointY = input.readFloat();
		this.totalBlood = input.readFloat();
		this.currentBlood = input.readFloat();
		this.bloodTime = input.readInt();
		this.isSpeed_Up = input.readBoolean();
		this.isSpeed_Down = input.readBoolean();
		this.isGo_Direction = input.readBoolean();
		this.speedCount = input.readInt();
		this.visited = (ArrayList<int[]>) input.readObject();
		this.anmiStart = input.readBoolean();
		this.anmiIndex = input.readInt();
		
	}

	@Override
	public void writeExternal(ObjectOutput output) throws IOException 
	{
		output.writeBoolean(live);
		output.writeInt(bs);
		output.writeBoolean(inCapability);
		output.writeBoolean(flag_controlDirection);
		output.writeInt(x);
		output.writeInt(y);
		output.writeInt(nextX);
		output.writeInt(nextY);
		output.writeObject(hm);
		output.writeObject(path);
		output.writeInt(BScount);
		output.writeFloat(pianyiX);
		output.writeFloat(pianyiY);
		output.writeObject(location);
		output.writeBoolean(currentRunning);
		output.writeFloat(currentPointX);
		output.writeFloat(currentPointY);
		output.writeFloat(totalBlood);
		output.writeFloat(currentBlood);
		output.writeInt(bloodTime);
		output.writeBoolean(isSpeed_Up);
		output.writeBoolean(isSpeed_Down);
		output.writeBoolean(isGo_Direction);
		output.writeInt(speedCount);
		output.writeObject(visited);
		output.writeBoolean(anmiStart);
		output.writeInt(anmiIndex);

	}
}
