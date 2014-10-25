package com.baina.tower.game;

import java.io.Serializable;

import com.baina.tower.impleclass.Monster;
import com.baina.tower.impleclass.Tower;
import com.baina.tower.towers.TowerList;
import com.baina.tower.towers.Tower_Shell;
import com.baina.tower.towers.Tower_Laser;
import com.baina.tower.towers.Tower_Missile;
import com.baina.tower.towers.Tower_Wave;
import com.baina.tower.view.GameSurfaceView;

public class Update
{
	//升级塔的方法
	public void upDateTower(Tower t )//传进来的是行和列
	{
		
		//判断这是那一类炮
		if(t instanceof Tower_Shell)//如果是第一种炮
		{
			t.upDateSelf();
		}
		else if (t instanceof Tower_Laser)//如果是第二中炮
		{
			t.upDateSelf();
		}
		else if (t instanceof Tower_Missile)//如果是第三中炮
		{
			t.upDateSelf();
		}
		else if (t instanceof Tower_Wave)//如果是第四中炮
		{
			t.upDateSelf();
		}
	}
	
	public void sell(Tower t)
	{
		//判断这是那一类炮
		if(t instanceof Tower_Shell)//如果是第一种炮
		{
			t.sell();
		}
		else if (t instanceof Tower_Laser)//如果是第二中炮
		{
			t.sell();
		}
		else if (t instanceof Tower_Missile)//如果是第三中炮
		{
			t.sell();
		}
		else if (t instanceof Tower_Wave)//如果是第四中炮
		{
			t.sell();
		}
	}
}
