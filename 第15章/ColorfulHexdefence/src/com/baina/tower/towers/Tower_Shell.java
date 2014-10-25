package com.baina.tower.towers;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Style;

import com.baina.tower.allactivity.MainGameActivity;
import com.baina.tower.bullet.BulletList;
import com.baina.tower.bullet.Bullet_Shell;
import com.baina.tower.constant.Constants;
import com.baina.tower.impleclass.Monster;
import com.baina.tower.impleclass.Tower;
import com.baina.tower.utils.LBX;
import com.baina.tower.view.GameSurfaceView;

public class Tower_Shell implements Tower,Externalizable {

	boolean flag = true;
	public int currentPrice = Constants.TOWER1CURRENTPRICE[0];
	float x;
	float y;//�������ĵ�����
	float shotR=Constants.TOWER1_R[0];
	public  Bitmap bitmap;
	public GameSurfaceView mv;
	public Paint paint;
	public ArrayList<Monster> master_list;
	float yAngle;
	public BulletList bulletList = new BulletList();//�ӵ��б�
	int limitCount = 20;//��ֹ�ӵ��������
	
	public int[] rowCol  = new int[2];//�����к���
	public Bitmap all;
	public int currentState = 1;//��ǰ�ǵڼ�������
	//�������ǲ��Ŷ����ı�־λ
	boolean playDongHua = false;
	float shanXingAngle = 0;
	public Tower_Shell(){};
	public Tower_Shell(float x, float y, Bitmap bitmap, GameSurfaceView mv) {
		super();
		this.x = x;
		this.y = y;
		rowCol = LBX.getRowcol(x, y);
		this.bitmap = Bitmap.createBitmap(bitmap, 0, 0, 48, 48); 
		this.all = bitmap;
		this.mv = mv;
		paint = mv.paint;
		master_list = mv.master_list;
	}


	@Override
	public void draw(Canvas canvas) {		
		Matrix m1=new Matrix();
		m1.setTranslate(x-bitmap.getWidth()/2,y-bitmap.getHeight()/2);
		m1.preRotate(yAngle, bitmap.getWidth()/2, bitmap.getHeight()/2);
        canvas.drawBitmap(bitmap, m1,null);
        bulletList.draw(canvas);
        //������������Ķ��� 
        if(playDongHua)
        {  
        	paint.setColor(Color.WHITE);
        	paint.setAlpha(180);
        	paint.setStyle(Style.FILL_AND_STROKE);
            RectF oval2 = new RectF(x-bitmap.getWidth()/2+5, y -bitmap.getHeight()/2+5,x+bitmap.getWidth()/2-5 ,y+bitmap.getHeight()/2-5 );// ���ø��µĳ����Σ�ɨ�����   
            canvas.drawArc(oval2, 200, shanXingAngle, true, paint);  
            // ��������һ��������RectF�������ǵڶ��������ǽǶȵĿ�ʼ�������������Ƕ��ٶȣ����ĸ����������ʱ�����Σ��Ǽٵ�ʱ�򻭻��� 
        }		
	}

	//�����͵Ķ���
	public void DrawShanXing()
	{
		while(true)
		{
			playDongHua = true;
			shanXingAngle = shanXingAngle + 5;
			Constants.sleep(10);
			if(shanXingAngle>=360)
			{
				shanXingAngle = 0;
				break;
			}
		}
	}

	@Override
	public void findMaster() {
		ArrayList<Monster> al = new ArrayList<Monster>();
		Monster m = null;
		for(int i=0; i<master_list.size(); i++){
			Monster a = master_list.get(i);
			float[] point = a.getCurrentPoint();
			if((point[0]-x)*(point[0]-x)+(point[1]-y)*(point[1]-y)<shotR*shotR&&a.isLive()){
				al.add(a);
			}
		}
		m = getNearestMaster(al);
		if(m == null)
		{
			return;
		}
		findJD(m.getCurrentPoint()[0],m.getCurrentPoint()[1]);
		if(bulletList.size()==0)///&&bulletList.size()==0
		{
			//��������������в��ܷ����ڵ�
			if(!playDongHua)
			{
				fire(m);
			}
			
		}

	}
	//�ҵ�����Ĺ�
	public Monster getNearestMaster(ArrayList<Monster> al)
	{
		float distance = 1000000;
		Monster m = null;
		for(int i = 0;i<al.size();i++)
		{
			Monster a = al.get(i);
			float[] point = a.getCurrentPoint();
			float temp = (point[0]-x)*(point[0]-x)+(point[1]-y)*(point[1]-y);
			if(temp<distance)
			{
				distance = temp;
				m = a;
			}
		}
		return m;
	}

	@Override
	public void findJD(float cx,float cy) {


		float xspan=cx-x;
		float yspan=cy-y;
		yAngle=(float)Math.toDegrees(Math.atan(xspan/yspan));	
		if(yspan<=0)
		{
			yAngle=(float)-Math.toDegrees(Math.atan(xspan/yspan));	
		}
		else
		{
			yAngle=-(-180+(float)Math.toDegrees(Math.atan(xspan/yspan)));
		}
		yAngle-=90;
	}
	
	@Override
	public void fire(Monster a) {


		if(limitCount%Constants.TOWER1_SHOOTTIME==0 && a.isLive() )
		{
			MainGameActivity.sound.playMusic(Constants.sf_minigun_soft, 0)  ;
			bulletList.add(new Bullet_Shell((float)(x+24*Math.cos(Math.PI*yAngle/180)),(float)(y+24*Math.sin(Math.PI*yAngle/180)),a,currentState,mv));
		}
		
		limitCount ++;
	}

	@Override
	public BulletList getBullet() {
		// TODO Auto-generated method stub
		return bulletList;
	}


	@Override
	public int[] getRowCol() {
		return rowCol;
	}
	@Override
	public int getCurrentState() {
		return currentState;
	}


	@Override
	public void upDateSelf() {
		switch(currentState)
		{
		case 1:
			//�ж����������Ǯ���Ƿ���
			if(mv.getScore>=Constants.UPDATETOWER1[1])//�˴�������Ҫд�ڳ���������
			{
				//��Ϣ��������һ�������Ĺ���,��ͼ
				DrawShanXing();
				MainGameActivity.sound.playMusic(Constants.towerupdate, 0)  ;
				playDongHua = false;
				bitmap = bitmap.createBitmap(all, 48, 0, 48, 48);
				currentState = currentState + 1;
				currentPrice = Constants.TOWER1CURRENTPRICE[currentState-1];
				shotR = Constants.TOWER1_R[currentState-1];//������ķ�Χ����˺����
				mv.getScore -= Constants.UPDATETOWER1[1];
				
				
				
				
				//ԭ�ȵ�������fire����Ҫ���¸�дһ�£����ݵ�ǰ��״̬�ӵ�����������
			}
			else
			{
				mv.canUpgrade = false;
			}
			break;
		case 2:
			//�ж����������Ǯ���Ƿ���
			if(mv.getScore>=Constants.UPDATETOWER1[2])//�˴�������Ҫд�ڳ���������
			{
				//��Ϣ��������һ�������Ĺ���,��ͼ
				DrawShanXing();
				MainGameActivity.sound.playMusic(Constants.towerupdate, 0)  ;
				playDongHua = false;
				bitmap = bitmap.createBitmap(all, 96, 0, 48, 48);
				currentState = currentState + 1;
				mv.getScore -= Constants.UPDATETOWER1[2];
				currentPrice = Constants.TOWER1CURRENTPRICE[currentState-1];
				shotR = Constants.TOWER1_R[currentState-1];//������ķ�Χ����˺����
				//ԭ�ȵ�������fire����Ҫ���¸�дһ�£����ݵ�ǰ��״̬�ӵ�����������
			}
			else
			{
				mv.canUpgrade = false;
			}
			break;
		case 3:
			//�ж����������Ǯ���Ƿ���
			if(mv.getScore>=Constants.UPDATETOWER1[3])//�˴�������Ҫд�ڳ���������
			{
				//��Ϣ��������һ�������Ĺ���,��ͼ
				DrawShanXing();
				MainGameActivity.sound.playMusic(Constants.towerupdate, 0)  ;
				playDongHua = false;
				bitmap = bitmap.createBitmap(all, 144, 0, 48, 48);
				currentState = currentState + 1;
				mv.getScore -= Constants.UPDATETOWER1[3];
				currentPrice = Constants.TOWER1CURRENTPRICE[currentState-1];
				shotR = Constants.TOWER1_R[currentState-1];//������ķ�Χ����˺����
				//ԭ�ȵ�������fire����Ҫ���¸�дһ�£����ݵ�ǰ��״̬�ӵ�����������
			}
			else
			{
				mv.canUpgrade = false;
			}
			break;
		case 4 :
			//дһ�仰�������������ˣ��Ѿ�����߼������
			
			break;
		}
		
	}


	//�����������ĵ�����
	@Override
	public float[] getXY() {
		return new float[]{x,y};
	}


	@Override
	public void sell() 
	{
		mv.getScore += Constants.TOWER1CURRENTPRICE[currentState-1];
	}


	@Override
	public float getR() {
		// TODO Auto-generated method stub
		return shotR;
	}
	
	@Override
	public int getCurrentUpdatePrice() {
		
		return Constants.UPDATETOWER1[currentState];
	}
	@Override
	public void readExternal(ObjectInput input) throws IOException,
			ClassNotFoundException
	{
		
		this.flag = input.readBoolean();
		this.currentPrice = input.readInt();
		this.x = input.readFloat();
		this.y = input.readFloat();
		this.shotR = input.readFloat();
		this.yAngle = input.readFloat();
		this.limitCount = input.readInt();
		this.rowCol = (int[]) input.readObject();
		this.currentState = input.readInt();
		this.playDongHua = input.readBoolean();
		this.shanXingAngle = input.readFloat();
	}


	@Override
	public void writeExternal(ObjectOutput output) throws IOException
	{

		output.writeBoolean(flag);
		output.writeInt(currentPrice);
		output.writeFloat(x);
		output.writeFloat(y);
		output.writeFloat(shotR);
		output.writeFloat(yAngle);
		output.writeInt(limitCount);
		output.writeObject(rowCol);
		output.writeInt(currentState);
		output.writeBoolean(playDongHua);
		output.writeFloat(shanXingAngle);
	}

}
