package com.baina.tower.bullet;

import java.io.Serializable;
import java.util.ArrayList;

import com.baina.tower.impleclass.BulletInterface;

import android.graphics.Canvas;

public class BulletList extends ArrayList<BulletInterface>{

	public void draw(Canvas canvas) {
		for(int i=0; i<this.size(); i++){
			this.get(i).drawSelf(canvas);
		}
	}
	
	public void run(){
		for(int i=0; i<this.size(); i++){
			
			if(get(i).isLive()){
				this.get(i).run();
			}else{
				this.remove(i);
			}
		}
	}
}
