package com.baina.tower.monsters;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.ArrayList;

import android.graphics.Canvas;

import com.baina.tower.constant.Constants;
import com.baina.tower.impleclass.Monster;
import com.baina.tower.threads.CreateMonster;
import com.baina.tower.view.GameSurfaceView;

public class MonsterList extends ArrayList<Monster> implements Externalizable{

	public GameSurfaceView mv;
	//在序列化的过程中我们如果实现了Externalizable，就必须提供无参数的构造器，否则反序列化的时候是不会调用readExternal方法的
	public MonsterList()
	{
		
	}
	public MonsterList(GameSurfaceView mv)
	{
		this.mv = mv;
	}
	public void draw(Canvas canvas) {
		for(int i=0; i<this.size(); i++){
			this.get(i).draw(canvas);
		}
		
	}
	
	public void run(){
		if(mv.threadIsDie == true)
		{
			mv.boshu++;
			mv.shanXingAngle=0;
//			if(mv.boshu==21)
//			{
//				return;
//			}
			mv.threadIsDie = false;
			switch(mv.boshu%Constants.CYCLE)
			{
			case 1:
				mv.cm = new CreateMonster(mv.master_list,mv,mv.creep_red);
				mv.cm.start();
				break;
			case 2:
				//如果没有怪了，重新添加
				mv.cm = new CreateMonster(mv.master_list,mv,mv.creep_yellow);
				mv.cm.start();
				break;
			case 0:
				mv.cm = new CreateMonster(mv.master_list,mv,mv.creep_purple);
				mv.cm.start();
				break;
			}
			
		}
			for(int i=0; i<this.size(); i++){
				if(mv.cm.pause)
				{
					break;
				}
				Monster master = this.get(i);
				master.run();
				if(!master.isLive()){
					this.remove(master);
				}
			}
		
	}
	
	public void findWayAll(float [] location){
		for(int i=0; i<this.size(); i++){
			int [] temp = this.get(i).getLocation();
			if(temp[0]!=location[0] && temp[1]!=location[1]){
				this.get(i).findWay();
			}
		
		}
		
	}
	@Override
	public void readExternal(ObjectInput input) throws IOException,
			ClassNotFoundException
	{
		int size = input.readInt();
		this.ensureCapacity(size);
		for(int i=0; i<size; i++)
		{
			this.add((Monster) input.readObject());
		}
		
	}
	@Override
	public void writeExternal(ObjectOutput output) throws IOException
	{
		output.writeInt(this.size());

		for(int i=0; i<this.size(); i++)
		{
			Monster master = this.get(i);   
			output.writeObject(master);
		}
	}

	
	
}
