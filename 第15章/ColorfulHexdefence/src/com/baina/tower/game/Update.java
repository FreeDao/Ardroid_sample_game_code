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
	//�������ķ���
	public void upDateTower(Tower t )//�����������к���
	{
		
		//�ж�������һ����
		if(t instanceof Tower_Shell)//����ǵ�һ����
		{
			t.upDateSelf();
		}
		else if (t instanceof Tower_Laser)//����ǵڶ�����
		{
			t.upDateSelf();
		}
		else if (t instanceof Tower_Missile)//����ǵ�������
		{
			t.upDateSelf();
		}
		else if (t instanceof Tower_Wave)//����ǵ�������
		{
			t.upDateSelf();
		}
	}
	
	public void sell(Tower t)
	{
		//�ж�������һ����
		if(t instanceof Tower_Shell)//����ǵ�һ����
		{
			t.sell();
		}
		else if (t instanceof Tower_Laser)//����ǵڶ�����
		{
			t.sell();
		}
		else if (t instanceof Tower_Missile)//����ǵ�������
		{
			t.sell();
		}
		else if (t instanceof Tower_Wave)//����ǵ�������
		{
			t.sell();
		}
	}
}
