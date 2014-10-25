package com.baina.tower.allactivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import com.baina.tower.constant.Constants;
import com.baina.tower.utils.SerializableGame;
import com.baina.tower.view.GameSurfaceView;

public class StartGameActivity extends Activity{

	GameSurfaceView mv;
	Handler handler;
	public int mapNum;
	int highpoint;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//设置为全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//设置为竖屏模式
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		WindowManager windowManager = this.getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		Constants.PMX1 = display.getWidth();
		Constants.PMY1 = display.getHeight();
System.out.println(Constants.PMX1+"                         "+Constants.PMY1);
		Intent intent = this.getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        mapNum = bundle.getInt("mapNum");
        highpoint = bundle.getInt("highpoint");
        mv = new GameSurfaceView(this);
        this.setContentView(mv);
        
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(MainGameActivity.sound.mp!=null)
		{//此处的播放器释放以后还要设置为空，不然就不正确了
			MainGameActivity.sound.mp.release(); 
			MainGameActivity.sound.mp = null;
		}
	}

	public void executeDatebase(){
		if(mv.totalScore>highpoint){
			Uri uri = Uri.parse("content://com.baina.hextower/tower/"+(mapNum+1));
			ContentResolver resolver = this.getContentResolver();
			ContentValues values = new ContentValues();
			values.put("points",mv.totalScore);
			resolver.update(uri, values, null, null);
		}
	}
	
	protected void onPause() 
	{
		super.onPause();
		if(MainGameActivity.sound.mp!=null)
		{
			MainGameActivity.sound.mp.pause();
		}
	}
	
	public void replay()
	{
		mv = new GameSurfaceView(this);
        this.setContentView(mv);
        if(MainGameActivity.sound.mp==null){			//设置点击音乐
			MainGameActivity.sound.mp =  MediaPlayer.create(
					StartGameActivity.this, R.raw.bnbg_music) ;
		}
		MainGameActivity.sound.mp.start();				//开始音乐
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		 	mv.onKeyDo(keyCode, event);
	        //继续执行父类的其他点击事件
	        return super.onKeyDown(keyCode, event);
	}
}
