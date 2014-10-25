package com.baina.tower.utils;

import java.util.HashMap;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import com.baina.tower.allactivity.MainGameActivity;
import com.baina.tower.allactivity.R;
import com.baina.tower.constant.Constants;
import  com.baina.tower.constant.Map;

public class Sound
{
	SoundPool sp ;
	HashMap<Integer	,Integer> hm ;
	MainGameActivity activity ;
	
	public MediaPlayer mp  ;
	public Sound(MainGameActivity activity)
	{
		this.activity = activity  ;
		initSound()  ;
	}
	
	//声音 初始化
	public void initSound()
	{
		sp = new SoundPool
		(10, //循环次数
		AudioManager.STREAM_MUSIC, 
		100
		);
		hm = new HashMap<Integer, Integer>();  
		hm.put(Constants.BUTTON_PRESS	, sp.load(activity, R.raw.bnbt_press	, 1) )  ;
		hm.put(Constants.TOWER_PICKUP, sp.load(activity, R.raw.tower_delete, 1))  ;
		hm.put(Constants.TOWER_PLACE, sp.load(activity, R.raw.tower_choice, 1))  ;
		hm.put(Constants.SPAWN_START, sp.load(activity, R.raw.bn_start, 1) ) ;
		hm.put(Constants.sf_creep_die_0, sp.load(activity, R.raw.bn_creepdie_0, 1) ) ;
		hm.put(Constants.sf_creep_die_1, sp.load(activity, R.raw.bn_creepdie_1, 1) ) ;
		hm.put(Constants.sf_creep_die_2, sp.load(activity, R.raw.bn_creepdie_2, 1) ) ;
		hm.put(Constants.sf_game_over, sp.load(activity, R.raw.bn_gameover, 1) ) ;
		hm.put(Constants.sf_rocket_launch, sp.load(activity, R.raw.bn_rocket_launch, 1) ) ;
		hm.put(Constants.sf_rocket_hit, sp.load(activity, R.raw.bn_rocket_hit, 1) ) ;
		hm.put(Constants.sf_swish, sp.load(activity, R.raw.bn_swish, 1) ) ;
		hm.put(Constants.sf_minigun_soft, sp.load(activity, R.raw.bn_gun, 1) ) ;
		hm.put(Constants.jiguang, sp.load(activity, R.raw.jiguang, 1) ) ;
		hm.put(Constants.targetbaozha, sp.load(activity, R.raw.targetbozha, 1) ) ;
		hm.put(Constants.vectory, sp.load(activity, R.raw.vectory, 1) ) ;
		hm.put(Constants.towerupdate, sp.load(activity, R.raw.towerupdate, 1) ) ;
		//指定从资源ID对应的资源文件中来装载音乐文件，并返回MediaPlyaer对象
		mp =  MediaPlayer.create(activity, R.raw.bnbg_music) ;

	}
	public void playMusic(int sound,int loop)
	{
		AudioManager am = (AudioManager)activity.getSystemService(activity.AUDIO_SERVICE)  ;
		float steamVolumCurrent = am.getStreamVolume(AudioManager.STREAM_MUSIC)  ;
		float steamVolumMax = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC)  ;
		float volum = steamVolumCurrent/steamVolumMax  ;
		sp.play(hm.get(sound), volum, volum, 1	, loop, 1)  ;//播放
	}
	

}

