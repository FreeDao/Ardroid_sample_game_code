package com.baina.tower.bullet;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;

import com.baina.tower.constant.Constants;
import com.baina.tower.impleclass.BulletInterface;
import com.baina.tower.impleclass.Monster;
import com.baina.tower.view.GameSurfaceView;

public class Bullet_Shell implements BulletInterface{

	Monster master;
	Paint paint;
	float yAngle;
	private float currentX;
	private float currentY;//当前坐标
	private float speed = 8;
	private boolean live=true;
	int damage = 0;//子弹伤害
	int currentState;
	GameSurfaceView mv;
	public Bullet_Shell(float bumbX,float bumbY,Monster a ,int currentState,GameSurfaceView mv)
	{
		this.mv = mv;
		this.currentX = bumbX;
		this.currentY = bumbY;
		master = a;
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		this.currentState = currentState;
		damage = Constants.BULLETNUMBER1DAMAGE[currentState-1];
	}

	@Override
	public void drawSelf(Canvas canvas)
	{
		Matrix m1=new Matrix();
		m1.setTranslate(currentX-mv.bulletShell.getWidth()/2,currentY-mv.bulletShell.getHeight()/2);
		m1.preRotate((float)Math.toDegrees(yAngle), mv.bulletShell.getWidth()/2, mv.bulletShell.getHeight()/2);
        canvas.drawBitmap(mv.bulletShell, m1,null);
	}

	@Override
	public void run()
	{
			if(master.isLive())
			{
				float xspan=master.getCurrentPoint()[0]-currentX;
				float yspan=master.getCurrentPoint()[1]-currentY;
				if(xspan*xspan+yspan*yspan<20){
					live = false;
					if(!master.decreaseBlood(damage))
					{
						master.setLive(false);
					}
					
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
					yAngle =(float)Math.PI+(float)Math.atan(Math.abs(yspan/xspan))         ; // 第三项限

				else 
					yAngle = (float)(2*Math.PI)-(float)Math.atan(Math.abs(yspan/xspan))   ;      // 第四项限

			}
			
			if(this.currentX<0||this.currentY<0||currentX>Constants.PMX||currentY>Constants.PMY)
				this.live=false;
			currentX+=speed*Math.cos(yAngle);
			currentY+=speed*Math.sin(yAngle);
	}

	@Override
	public boolean isLive() {
		return live;
	}
}
