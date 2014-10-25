package com.baina.tower.utils;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import com.baina.tower.allactivity.MainGameActivity;
import com.baina.tower.game.Game;
import com.baina.tower.game.TowerAdder;
import com.baina.tower.game.Update;
import com.baina.tower.mapCapability.CapabilityList;
import com.baina.tower.monsters.MonsterList;
import com.baina.tower.towers.TowerList;
import com.baina.tower.view.GameSurfaceView;
import com.baina.tower.view.Score;

import android.graphics.Paint;
import android.media.MediaPlayer;

public class SerializableGame {
	
	public SerializableGame(){
		
	}
	
	//������Ϸ�ķ���
	public static void saveGameStatus(GameSurfaceView gameView){
		OutputStream out = null;
		ObjectOutputStream  oout = null;
		try{
			out = gameView.getContext().openFileOutput("game.ytl", 0);
			oout = new ObjectOutputStream(out);
			
			oout.writeInt(gameView.life);//Ŀ�ĵص�����
			oout.writeInt(gameView.mapNum);//д���ͼ���
			oout.writeInt(gameView.boshu);//д���ǵڼ�����
			oout.writeInt(gameView.cm.count);//д���Ⲩ�ĵڼ�����
			oout.writeObject(gameView.game.map_tower);//д���ͼ
			oout.writeObject(gameView.master_list) ;//д����б�
			oout.writeObject(gameView.tower_list);//д�����б� 
      
			oout.writeBoolean(gameView.isDrawMenu);     
			oout.writeInt(gameView.getScore);
			oout.writeInt(gameView.points);
			oout.writeFloat(gameView.shanXingAngle);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				oout.close();
				out.close();				
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	//������Ϸд����MySurfaceView�У���Ϊǰ�ڼܹ�����
	
	public static boolean check(MainGameActivity h){//����ļ��Ƿ����
		try{
			h.openFileInput("game.ytl");
		}
		catch(Exception e){
			return false;
		}
		return true;
	}
}
