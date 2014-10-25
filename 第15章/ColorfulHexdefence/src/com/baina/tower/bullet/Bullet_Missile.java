package com.baina.tower.bullet;

import java.io.Serializable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.baina.tower.allactivity.MainGameActivity;
import com.baina.tower.allactivity.R;
import com.baina.tower.constant.Constants;
import com.baina.tower.impleclass.BulletInterface;
import com.baina.tower.impleclass.Monster;
import com.baina.tower.view.GameSurfaceView;

public class Bullet_Missile implements BulletInterface{
	Monster master;
	Paint paint;
	float yAngle;
	float _yAngle;
	private float currentX;
	private float currentY;//当前坐标
	private float speed = 5;
	private boolean live=true;
	int damage ;//子弹伤害
	transient Bitmap rocket;
	int currentState;
	transient private GameSurfaceView mv;
	float pianYiAngle = 5f/3;//炮寻进怪时每次角度的增减量
	
	int special ;//四级炮时炮弹的特殊性
	float angleCha = 60;//四级炮是炮管之间的角度差
	
	public Bullet_Missile(float bumbX,float bumbY,Monster a,GameSurfaceView mv ,int currentState,int special)
	{
		this.mv = mv;
		this.currentX = bumbX;
		this.currentY = bumbY;
		master = a;
		this.special = special;
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		rocket=BitmapFactory.decodeResource(mv.getResources(),
				R.drawable.bn_rocket);
		this.currentState = currentState;
		damage = Constants.BULLETNUMBER3DAMAGE[currentState-1];
	}

	@Override
	public void drawSelf(Canvas canvas)
	{
		
		Matrix m1=new Matrix();
		m1.setTranslate(currentX-rocket.getWidth()/2,currentY-rocket.getHeight()/2);
		m1.preRotate((float)Math.toDegrees(_yAngle), rocket.getWidth()/2, rocket.getHeight()/2);
        canvas.drawBitmap(rocket, m1,null);
		
	}

	@Override
	public void run()
	{
		if(master.isLive())
		{
			float xspan=master.getCurrentPoint()[0]-currentX;
			float yspan=master.getCurrentPoint()[1]-currentY;
			if(xspan*xspan+yspan*yspan<20){
				MainGameActivity.sound.playMusic(Constants.sf_rocket_hit, 0)  ;
				live = false;
				if(!master.decreaseBlood(damage))
				master.setLive(false);
				return;
			}
				if( xspan == 0 )
				{
				    if( master.getCurrentPoint()[1] >= this.currentY)             // 子弹需要下移
				    	xspan = 0.0000001f;
				    else                                 // 子弹需要上移
				    	xspan = -0.0000001f;
				}
				if( yspan == 0 )
				{
					if( master.getCurrentPoint()[0] >= this.currentX)             // 子弹需要下移
				    	yspan = 0.0000001f;
				    else                                 // 子弹需要上移
				    	yspan = -0.0000001f;
				}
				if( xspan>0 && yspan>0 )
					yAngle = (float)Math.atan(Math.abs(yspan/xspan));           // 第一项限

				else if( xspan<0 && yspan>0 )
					yAngle = (float)Math.PI-(float)Math.atan(Math.abs(yspan/xspan));         // 第二项限

				else if( xspan<0 && yspan<0 )                     
					yAngle =(float)Math.PI+(float)Math.atan(Math.abs(yspan/xspan)); // 第三项限

				else 
					yAngle = (float)(2*Math.PI)-(float)Math.atan(Math.abs(yspan/xspan));      // 第四项限
					
		}
		
		if(this.currentX<0||this.currentY<0||currentX>Constants.PMX||currentY>Constants.PMY)
			this.live=false;
		
		if(special == 0)
		{
			_yAngle = yAngle;
			currentX+=speed*Math.cos(_yAngle);
			currentY+=speed*Math.sin(_yAngle);
		}
		else if(special == 1)
		{
			angleCha = angleCha - pianYiAngle;
			if(angleCha<=0)
				special = 0;
			_yAngle = (float) (yAngle + angleCha*Math.PI/180);
			currentX+=speed*Math.cos(_yAngle);
			currentY+=speed*Math.sin(_yAngle);
		}
		else if(special == 2)
		{
			angleCha = angleCha - pianYiAngle;
			if(angleCha<=0)
				special = 0;
			_yAngle = (float) (yAngle - angleCha*Math.PI/180);
			currentX+=(speed+1.1)*Math.cos(_yAngle);//炮弹的速度的修正
			currentY+=(speed+1.1)*Math.sin(_yAngle);//炮弹的速度的修正
		}
			
	
		
	}

	@Override
	public boolean isLive() {
		
		return live;
	}
}
