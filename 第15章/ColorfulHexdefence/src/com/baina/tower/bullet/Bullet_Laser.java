package com.baina.tower.bullet;



import java.io.Serializable;
import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.baina.tower.constant.Constants;
import com.baina.tower.impleclass.Monster;
import com.baina.tower.impleclass.BulletInterface;
import com.baina.tower.monsters.MonsterList;

public class Bullet_Laser implements BulletInterface {
	Monster master;
	Paint paint;
	private float currentX;
	private float currentY;// ��ǰ����
	private boolean live = true;
	int damage;// �ӵ��˺�
	float damageX;
	float damageY;
	private float currentX2;
	private float currentY2;
	float damageX1;
	float damageY1;
	private float currentX1;
	private float currentY1;
	float damageX2;
	float damageY2;
	private float currentX3;
	private float currentY3;
	float damageX3;
	float damageY3;
	MonsterList list;// �õ��ֵ��б�
	float yAngle;// �ӵ��͹�֮��ĽǶ�
	int currentState;

	public Bullet_Laser(float bumbX, float bumbY, Monster a, MonsterList master_list, int currentState) {
		this.currentX = bumbX;
		this.currentY = bumbY;
		master = a;
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStrokeWidth(2);
		list = master_list;
		this.currentState = currentState;
		damage = Constants.BULLETNUMBER2DAMAGE[currentState-1];
	}

	@Override
	public void drawSelf(Canvas canvas) {
		if(!live)return;
//����go�������õ���Ҫ����ֵ��		
		go();
		
		
		int color = (int)(Math.random()*5);
		switch(color){
		case 0:
			paint.setColor(Color.WHITE);break;
		case 1:
			paint.setColor(Color.BLUE);break;
		case 2:
			paint.setColor(Color.GREEN);break;
		case 3:
			paint.setColor(Color.YELLOW);break;
		case 4:
			paint.setColor(Color.CYAN);break;
		}
		
		switch(currentState){
		case 1:
				canvas.drawLine((float)(currentX+16*Math.cos(yAngle)), 
						(float)(currentY+16*Math.sin(yAngle)), damageX, damageY, paint);

			break;
		case 2:
			canvas.drawLine((float)(currentX1+10*Math.cos(yAngle)), 
					(float)(currentY1+10*Math.sin(yAngle)), damageX1, damageY1, paint);
			canvas.drawLine((float)(currentX2+10*Math.cos(yAngle)), 
					(float)(currentY2+10*Math.sin(yAngle)), damageX2, damageY2, paint);
			
			
			break;
		case 3:
			canvas.drawLine((float)(currentX1+10*Math.cos(yAngle)), 
					(float)(currentY1+10*Math.sin(yAngle)), damageX1, damageY1, paint);
			canvas.drawLine((float)(currentX2+10*Math.cos(yAngle)), 
					(float)(currentY2+10*Math.sin(yAngle)), damageX2, damageY2, paint);
			canvas.drawLine((float)(currentX+16*Math.cos(yAngle)), 
					(float)(currentY+16*Math.sin(yAngle)), damageX, damageY, paint);
			break;
		case 4:
			paint.setStrokeWidth(4);
			canvas.drawLine((float)(currentX+15*Math.cos(yAngle)), 
					(float)(currentY+15*Math.sin(yAngle)), damageX, damageY, paint);
			paint.setStrokeWidth(2);
			break;
		}
		isInLine();
		live = false;
	}

	public void run(){
		
	}

	public void go() {
		// ��һ �� ��� ��
			float x1 = currentX;
			float y1 = currentY;
			float x2 = master.getCurrentPoint()[0];
			float y2 = master.getCurrentPoint()[1];
			

//����������
			float tempX = 0;
			if (Math.abs(x1 - x2) <= 1) {
				if (y2 > y1) {
					damageX = x1;
					damageY = Constants.PMY;
				} else {
					damageY = 0;
					damageX = x1;
				}
			} else if (x2 - x1 > 1) {
				tempX = Constants.PMX;
				damageY = (tempX - x1) / (x2 - x1) * (y2 - y1) + y1;
				damageX = tempX;
			} else if (x2 - x1 < -1) {
				tempX = 0;
				damageY = (tempX - x1) / (x2 - x1) * (y2 - y1) + y1;
				damageX = tempX;
			}
			
			
//������ĽǶ�			
			float xspan=master.getCurrentPoint()[0]-currentX;
			float yspan=master.getCurrentPoint()[1]-currentY;
			if( xspan == 0 )
			{
			    if( master.getCurrentPoint()[1] >= this.currentY)             // �ӵ���Ҫ����
			    	xspan = 0.0000001f;
			    else                                 // �ӵ���Ҫ����
			    	xspan = -0.0000001f;
			}
			if( yspan == 0 )
			{
				if( master.getCurrentPoint()[0] >= this.currentX)             // �ӵ���Ҫ����
			    	yspan = 0.0000001f;
			    else                                 // �ӵ���Ҫ����
			    	yspan = -0.0000001f;
			}
			if( xspan>0 && yspan>0 ){
				yAngle = (float)Math.atan(Math.abs(yspan/xspan));           // ��һ����   ��������������
				switch(currentState){
				case 1:
					break;
				case 2:
					damageX1=damageX+2;damageY1=damageY-2;currentX1=currentX+2;currentY1=currentY-2;
					damageX2=damageX-2; damageY2=damageY+2;currentX2=currentX-2;currentY2=currentY+2;
					break;
				case 3:
					damageX1=damageX-3;damageY1=damageY+3;currentX1=currentX-3;currentY1=currentY+3;
					damageX2=damageX+3; damageY2=damageY-3;currentX2=currentX+3;currentY2=currentY-3;
					damageX3=damageX;damageY3=damageY;currentX3=currentX;currentY3=currentY;
					break;
				case 4:
					
					break;
				}
				
				
			}
			else if( xspan<0 && yspan>0 ){
				yAngle = (float)Math.PI-(float)Math.atan(Math.abs(yspan/xspan));         // �ڶ�����       dfdfdfdfd  ����
				switch(currentState){
				case 1:
					break;
				case 2:
					damageX1=damageX-2;damageY1=damageY-2;currentX1=currentX-2;currentY1=currentY-2;
					damageX2=damageX+2; damageY2=damageY+2;currentX2=currentX+2;currentY2=currentY+2;
					break;
				case 3:
					damageX1=damageX-3;damageY1=damageY-3;currentX1=currentX-3;currentY1=currentY-3;
					damageX2=damageX+3; damageY2=damageY+3;currentX2=currentX+3;currentY2=currentY+3;
					damageX3=damageX;damageY3=damageY;currentX3=currentX;currentY3=currentY;
					break;
				case 4:
					
					break;
				}
			}
			else if( xspan<0 && yspan<0 ) {                    
				yAngle =(float)Math.PI+(float)Math.atan(Math.abs(yspan/xspan))         ; // ��������
				switch(currentState){
				case 1:
					break;
				case 2:
					damageX1=damageX+2;damageY1=damageY-2;currentX1=currentX+2;currentY1=currentY-2;
					damageX2=damageX-2; damageY2=damageY+2;currentX2=currentX-2;currentY2=currentY+2;
					break;
				case 3:
					damageX1=damageX-3;damageY1=damageY+3;currentX1=currentX-3;currentY1=currentY+3;
					damageX2=damageX+3; damageY2=damageY-3;currentX2=currentX+3;currentY2=currentY-3;
					damageX3=damageX;damageY3=damageY;currentX3=currentX;currentY3=currentY;
					break;
				case 4:
					
					break;
				}
			}
			else {
				yAngle = (float)(2*Math.PI)-(float)Math.atan(Math.abs(yspan/xspan))   ;      // ��������
				switch(currentState){
				case 1:
					break;
				case 2:
					damageX1=damageX-2;damageY1=damageY-2;currentX1=currentX-2;currentY1=currentY-2;
					damageX2=damageX+2; damageY2=damageY+2;currentX2=currentX+2;currentY2=currentY+2;
					break;
				case 3:
					damageX1=damageX-3;damageY1=damageY-3;currentX1=currentX-3;currentY1=currentY-3;
					damageX2=damageX+3; damageY2=damageY+3;currentX2=currentX+3;currentY2=currentY+3;
					damageX3=damageX;damageY3=damageY;currentX3=currentX;currentY3=currentY;
					break;
				case 4:
					
					break;
				}
			}
		
			
		

	}

	public void isInLine() {
		if (list.size() <= 0)
			return;
		float x1 = currentX;
		float y1 = currentY;
		for (int i = 0; i < list.size(); i++) {
			Monster mm = list.get(i);
			// ���ߵķ��������Ͻ�
			if (x1 <= damageX && y1 >= damageY) {
				// ����������߿���
				if (isInRect(1, x1, y1, damageX, damageY, mm)) {
					if (!mm.decreaseBlood(damage))
						mm.setLive(false);
				}
			}// ���ߵķ��������Ͻ�
			else if (x1 >= damageX && y1 >= damageY) {
				if (isInRect(2, damageX, y1, x1, damageY, mm)) {
					if (!mm.decreaseBlood(damage))
						mm.setLive(false);
				}
			}// ���ߵķ��������½�
			else if (x1 >= damageX && y1 <= damageY) {
				if (isInRect(3, damageX, damageY, x1, y1, mm)) {
					if (!mm.decreaseBlood(damage))
						mm.setLive(false);
				}
			}// ���ߵķ��������½�
			else if (x1 <= damageX && y1 <= damageY) {
				if (isInRect(4, x1, damageY, damageX, y1, mm)) {
					if (!mm.decreaseBlood(damage))
						mm.setLive(false);
				}
			}

		}
	}

	// �жϹ��Ƿ��ھ��ο���
	public boolean isInRect(int number, float startX, float startY, float endX,
			float endY, Monster mm) {
		float x2 = mm.getCurrentPoint()[0];
		float y2 = mm.getCurrentPoint()[1];
		float[] f1 = new float[2];
		float[] f2 = new float[2];
		float distance2 = 0;
		if (x2 >= startX && x2 <= endX && y2 >= endY && y2 <= startY) {
			switch (number) {
			case 1:
				// ����1
				f1 = new float[] { startX - x2, startY - y2 };
				// ����2
				f2 = new float[] { endX - x2, endY - y2 };
				// ���ߵĳ���
				distance2 = (startX - endX) * (startX - endX) + (startY - endY)
						* (startY - endY);
				break;
			case 2:
				// ����1
				f1 = new float[] { endX - x2, startY - y2 };
				// ����2
				f2 = new float[] { startX - x2, endY - y2 };
				// ���ߵĳ���
				distance2 = (endX - startX) * (endX - startX) + (endY - startY)
						* (endY - startY);
				break;
			case 3:
				// ����1
				f1 = new float[] { endX - x2, endY - y2 };
				// ����2
				f2 = new float[] { startX - x2, startY - y2 };
				// ���ߵĳ���
				distance2 = (startX - endX) * (startX - endX) + (startY - endY)
						* (startY - endY);
				break;
			case 4:
				// ����1
				f1 = new float[] { startX - x2, endY - y2 };
				// ����2
				f2 = new float[] { endX - x2, startY - y2 };
				// ���ߵĳ���
				distance2 = (startX - endX) * (startX - endX) + (startY - endY)
						* (startY - endY);
				break;

			}

			// �����㹹�ɵ������ε����
			float mianJi2 = (f1[0] * f2[1] - f1[1] * f2[0])
					* (f1[0] * f2[1] - f1[1] * f2[0]);
			// �������������жϹֺ�ֱ�ߵľ���
			float juLi2 = mianJi2 / distance2;

			float masterR = mm.getMasterLength();
			
			if (masterR*masterR >= juLi2) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean isLive() {
		// TODO Auto-generated method stub
		return live;
	}
}
