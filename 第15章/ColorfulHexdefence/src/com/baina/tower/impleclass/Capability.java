package com.baina.tower.impleclass;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.baina.tower.monsters.MonsterList;

public interface Capability {

	public void go(MonsterList master_list);
	
	public void draw(Canvas canvas,Paint paint);
}
