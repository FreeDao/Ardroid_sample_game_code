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

public class Go_Direction implements Capability {
	boolean isInIt;
	boolean isDraw;
	boolean flag;
	int x;
	int y;
	int type;
	float pointX;
	float pointY;
	transient Bitmap bitmap;
	public Go_Direction(Bitmap bitmap, int x, int y, int k) {
		this.x = x;
		this.y = y;
		pointX = LBX.getPosition(x, y)[0];
		pointY = LBX.getPosition(x, y)[1];
		this.type = k;
		this.bitmap = bitmap;
	}

	@Override
	public void go(MonsterList master_list) {
		for(int i=0; i<master_list.size(); i++){//ѭ���ҵ�
			Monster master = master_list.get(i);
			float[] temp = master.getCurrentPoint();
			if((temp[0]-pointX)*(temp[0]-pointX)+(temp[1]-pointY)*(temp[1]-pointY)<=Map.b*Map.b){
				isInIt = true;
				isDraw = true;
				if(master.getLocation()[0]==y && master.getLocation()[1]==x)
				{
					//Ѱ·ʱ�������к�ż���в�һ����UPdateWay�����������ݵ��Ƿ����ָ�����һ�����ӵ�����
					if(x%2==0){
						switch(type){
						case 6://��ͼ������Ϊ�У�����Ϊ��
							master.updateWay(0, -1);	//����				
							break;
						case 7:
							master.updateWay(1, -1);//����Ϊ˳ʱ��
							break;
						case 8:
							master.updateWay(1, 0);
							break;
						case 9:
							master.updateWay(0, 1);
							break;
						case 10:
							master.updateWay(-1, 0);
							break;
						case 11:
							master.updateWay(-1, -1);
							break;
							
						}
					}else{
						switch(type){
						case 6:
							master.updateWay(0, -1);					
							break;
						case 7:
							master.updateWay(1, 0);
							break;
						case 8:
							master.updateWay(1, 1);
							break;
						case 9:
							master.updateWay(0, 1);
							break;
						case 10:
							master.updateWay(-1, 1);
							break;
						case 11:
							master.updateWay(-1, 0);
							break;
							
						}
					}
				}else{
					
						master.setVisited(x, y);
						master.findWay2();
				}	
			}
		}
		if(!isInIt){
			isDraw = false;
		}
		isInIt = false;
	}

	@Override
	public void draw(Canvas canvas, Paint paint) {
		if(isDraw){
			canvas.drawBitmap(UpdateBitmap.liangDu(bitmap,6f), pointX-bitmap.getWidth()/2, pointY-bitmap.getHeight()/2, paint);
		}else{
			canvas.drawBitmap(bitmap, pointX-bitmap.getWidth()/2, pointY-bitmap.getHeight()/2, paint);
		}
		
	
	}
	
	

}