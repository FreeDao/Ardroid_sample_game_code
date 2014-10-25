package com.wl.tableball.util;

import java.io.IOException;
import java.util.HashMap;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import com.wl.tableball.game.TableBallActivity;
import com.wl.tableball.game.R;

public class SoundUtil
{
	public MediaPlayer mp;
	SoundPool soundPool;//声音池
	HashMap<Integer, Integer> soundPoolMap;
	TableBallActivity tableBallActivity;
	public SoundUtil(TableBallActivity tableBallActivity)//构造器
	{
		this.tableBallActivity=tableBallActivity;
	}
	//声音缓冲池的初始化
    public void initSounds()
    {
    	 //创建声音缓冲池
	     soundPool = new SoundPool
	     (
	    		 6, 							//同时能最多播放的个数
	    		 AudioManager.STREAM_MUSIC,     //音频的类型
	    		 100							//声音的播放质量，目前无效
	     );
	     
	     //创建声音资源Map	     
	     soundPoolMap = new HashMap<Integer, Integer>();   
	     //将加载的声音资源id放进此Map
	     soundPoolMap.put(0, soundPool.load(tableBallActivity, R.raw.hole_sounds, 1));//进洞
	     soundPoolMap.put(1, soundPool.load(tableBallActivity, R.raw.wall_sound, 1));//撞木头
	     soundPoolMap.put(2, soundPool.load(tableBallActivity, R.raw.ping_sounds, 1));//过关
	     
	     //有几个音效就有当前这个几句  R.raw.gamestart返回编号 不定     后面的1为优先级 目前不考虑
	} 
       
   //播放音效的方法
   public void playEffectsSound(int sound, int loop) {
	   if(true)
	   {
		   AudioManager mgr = (AudioManager)tableBallActivity.getSystemService(Context.AUDIO_SERVICE);
		    float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);//当前音量   
		    float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);//最大音量       
		    float volume = streamVolumeCurrent / streamVolumeMax;   
		    
		    soundPool.play
		    (
	    		soundPoolMap.get(sound), //声音资源id
	    		volume, 				 //左声道音量
	    		volume, 				 //右声道音量
	    		1, 						 //优先级				 
	    		loop, 					 //循环次数 -1带表永远循环
	    		1f					 //回放速度0.5f～2.0f之间
		    );
	   }
	}
   public void play_bg_sound()//
   {
	   if(Constant.YINYUE_CLOSE)
	   {
		   //通过assets 加载音乐
	     AssetManager assetManager = tableBallActivity.getAssets();  
	     try {  
	     mp = new MediaPlayer();  
	     String s;
	     if(Constant.YINYUE1)
	     {
	    	 s="main.mp3";
	     }
	     else
	     {
	    	 s= "game.mp3"; 
	     }
	     AssetFileDescriptor fileDescriptor= assetManager.openFd(s); 
	     mp.setDataSource(fileDescriptor.getFileDescriptor(),  
	     fileDescriptor.getStartOffset(),  
	     fileDescriptor.getLength()); 
	     mp.setLooping(true);
	     mp.prepare();  
	     mp.start();  
	     } catch (IOException e) 
	     {  
	      e.printStackTrace();  
	     } 
	     Constant.YINYUE_CLOSE=false;
	   }
   }
   
   public void stop_bg_sound()
   {
	   if(!Constant.YINYUE_CLOSE&&mp!=null)
	   {
		   mp.stop();
		   mp.release();
		   Constant.YINYUE_CLOSE=true;
	   }
   }
}
