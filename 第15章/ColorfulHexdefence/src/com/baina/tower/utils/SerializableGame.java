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
	
	//保存游戏的方法
	public static void saveGameStatus(GameSurfaceView gameView){
		OutputStream out = null;
		ObjectOutputStream  oout = null;
		try{
			out = gameView.getContext().openFileOutput("game.ytl", 0);
			oout = new ObjectOutputStream(out);
			
			oout.writeInt(gameView.life);//目的地的命数
			oout.writeInt(gameView.mapNum);//写入地图编号
			oout.writeInt(gameView.boshu);//写入是第几波怪
			oout.writeInt(gameView.cm.count);//写入这波的第几个怪
			oout.writeObject(gameView.game.map_tower);//写入地图
			oout.writeObject(gameView.master_list) ;//写入怪列表
			oout.writeObject(gameView.tower_list);//写入塔列表 
      
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
	//加载游戏写在了MySurfaceView中，因为前期架构不好
	
	public static boolean check(MainGameActivity h){//检查文件是否存在
		try{
			h.openFileInput("game.ytl");
		}
		catch(Exception e){
			return false;
		}
		return true;
	}
}
