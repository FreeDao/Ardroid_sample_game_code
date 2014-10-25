package com.baina.tower.mapCapability;

import java.io.Serializable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.baina.tower.constant.Map;
import com.baina.tower.impleclass.Capability;
import com.baina.tower.impleclass.Monster;
import com.baina.tower.monsters.MonsterList;
import com.baina.tower.utils.LBX;
import com.baina.tower.utils.UpdateBitmap;

public class Blood_Down implements Capability {

	int x;
	int y;
	boolean isInIt;
	boolean isDraw;
	float pointX;
	float pointY;//��Ļ������
	int damage = 2;
	transient Bitmap bitmap;
	public Blood_Down(Bitmap bitmap,int x, int y){
		this.x = x;
		this.y = y; //���ڸ��ӵ��к���
		pointX = LBX.getPosition(x, y)[0];
		pointY = LBX.getPosition(x, y)[1];//���ڸ��ӵ���Ļ���ص�����
		this.bitmap =bitmap; 
	}

	@Override
	public void go(MonsterList master_list) {
		for(int i=0; i<master_list.size(); i++){
			Monster master = master_list.get(i);
			float[] temp = master.getCurrentPoint();
			if((temp[0]-pointX)*(temp[0]-pointX)+(temp[1]-pointY)*(temp[1]-pointY)<Map.b*Map.b){
				master.decreaseBlood(damage);
				isInIt = true;
				isDraw = true;
			}
		}
		//�����߽������ڣ����ܸ���ͼƬ��������
		if(!isInIt){
			isDraw = false;
		}
		isInIt = false;
	}

	@Override
	public void draw(Canvas canvas,Paint paint) {
		if(isDraw){
			canvas.drawBitmap(UpdateBitmap.liangDu(bitmap,6f), pointX-bitmap.getWidth()/2, pointY-bitmap.getHeight()/2, paint);
		}else{
			canvas.drawBitmap(bitmap, pointX-bitmap.getWidth()/2, pointY-bitmap.getHeight()/2, paint);
		}
		
	}
}
