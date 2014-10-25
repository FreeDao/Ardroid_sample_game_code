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
		
		//����Ϊȫ��
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//����Ϊ����ģʽ
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
		{//�˴��Ĳ������ͷ��Ժ�Ҫ����Ϊ�գ���Ȼ�Ͳ���ȷ��
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
        if(MainGameActivity.sound.mp==null){			//���õ������
			MainGameActivity.sound.mp =  MediaPlayer.create(
					StartGameActivity.this, R.raw.bnbg_music) ;
		}
		MainGameActivity.sound.mp.start();				//��ʼ����
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		 	mv.onKeyDo(keyCode, event);
	        //����ִ�и������������¼�
	        return super.onKeyDown(keyCode, event);
	}
}
