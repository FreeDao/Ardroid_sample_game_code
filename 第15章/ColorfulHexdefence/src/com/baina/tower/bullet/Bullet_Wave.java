package com.baina.tower.bullet;

import java.io.Serializable;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.baina.tower.constant.Constants;
import com.baina.tower.impleclass.BulletInterface;
import com.baina.tower.impleclass.Monster;
import com.baina.tower.impleclass.Tower;
import com.baina.tower.monsters.MonsterList;

public class Bullet_Wave implements BulletInterface {
	Monster master;
	Paint paint;
	float yAngle;
	private float currentX;
	private float currentY;//当前坐标
	private float speed = 3;
	private boolean live=true;
	int damage;//子弹伤害
	MonsterList master_list;
	float currentR;
	Tower tower;
	int currentState;
	public Bullet_Wave(float bumbX,float bumbY,Monster a ,MonsterList master_list,Tower tower,int currentState)
	{
		this.currentX = bumbX;
		this.currentY = bumbY;
		master = a;
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setARGB(50, 0, 0, 255);
		this.tower = tower;
		this.master_list = master_list;
		this.currentState = currentState;
		damage = Constants.BULLETNUMBER4DAMAGE[currentState-1];
	}

	@Override
	public void drawSelf(Canvas canvas)
	{
		
		canvas.drawCircle(currentX, currentY, currentR, paint);
	}

	@Override
	public void run()
	{
		if(currentR<=Constants.TOWER4_R[tower.getCurrentState()-1]){
			currentR+=4;
			shoot();
		}
		else{
			this.live = false;
		}
		
	}
	
	public void shoot(){
		for(int i=0;i<master_list.size();i++)
		{
			Monster mm = master_list.get(i);
			float x2 = mm.getCurrentPoint()[0];
			float y2 = mm.getCurrentPoint()[1];
			if((x2-currentX)*(x2-currentX)+(y2-currentY)*(y2-currentY)<=Constants.TOWER4_R[tower.getCurrentState()-1]*Constants.TOWER4_R[tower.getCurrentState()-1]){
				if(!mm.decreaseBlood(damage))
					mm.setLive(false);
			}
		}
	}

	@Override
	public boolean isLive() {
		return live;
	}

}
