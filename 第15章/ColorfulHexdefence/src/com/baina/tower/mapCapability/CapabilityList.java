package com.baina.tower.mapCapability;

import java.io.Serializable;
import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.baina.tower.impleclass.Capability;
import com.baina.tower.monsters.MonsterList;
import com.baina.tower.view.GameSurfaceView;

public class CapabilityList extends ArrayList<Capability>{
	Paint paint;
	MonsterList master_list;
	public CapabilityList(GameSurfaceView mv){
		 master_list= mv.master_list;
		 paint = new Paint();
		 paint = new Paint();// ´´½¨»­±Ê
		 paint.setAntiAlias(true);
	}
	public void go(){
		for(int i=0; i<this.size(); i++){
			this.get(i).go(master_list);
		}
	}
	
	public void draw(Canvas canvas){
		for(int i=0; i<this.size(); i++){
			this.get(i).draw(canvas,paint);
		}
	}
}
