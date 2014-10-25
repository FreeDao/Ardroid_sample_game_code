package com.baina.tower.allactivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import com.baina.tower.utils.SerializableGame;

import com.baina.tower.constant.Constants;
import com.baina.tower.utils.Sound;
import com.baina.tower.view.GameSurfaceView;

public class MainGameActivity extends Activity {
	Button continueBtn;//继续按钮引用
	@Override
	protected void onResume() {

		if(!SerializableGame.check(MainGameActivity.this)){			//检查游戏存档是否存在
			continueBtn.setClickable(false);
			continueBtn.setText("继续（无存档）");
		}else
		{
			continueBtn.setClickable(true);
			continueBtn.setText("继续游戏");
		}
		
		super.onResume();
	}
	Button start;												//开始按钮
	public static Sound sound;									//游戏音乐
	GameSurfaceView gameView;									//进行游戏界面
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); 			//设置无标题
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);              
																//设置为全屏模式
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);		
																//设置为竖屏模式
		sound = new Sound(this);								//创建Sound对象
        setContentView(R.layout.main);							//设置当前界面显示
        start = (Button)findViewById(R.id.start);
        start.setOnClickListener(new OnClickListener(){			//开始按钮添加监听
        @Override
		public void onClick(View v) {
			sound.playMusic(Constants.BUTTON_PRESS, 0) ;		//开始音乐
			Intent intent = new Intent(MainGameActivity.this,ListViewActivity.class);
			MainGameActivity.this.startActivity(intent);		//发送Intent启动ListViewActivity
		}});
        continueBtn = (Button)findViewById(R.id.continuegame);		//继续游戏按钮
        continueBtn.setOnClickListener(new Button.OnClickListener(){		//按钮添加监听
			@Override
			public void onClick(View v){
				if(!SerializableGame.check(MainGameActivity.this)){			//检查游戏存档是否存在
					return;
				}
				if(sound.mp==null){											//创建音乐
					sound.mp =  MediaPlayer.create(MainGameActivity.this, R.raw.bnbg_music) ;
				}
				Bundle bundle = new Bundle();
				Intent intent = new Intent(MainGameActivity.this,StartGameActivity.class);
				bundle.putInt("mapNum", 100);
				intent.putExtra("bundle", bundle);							//绑定数据
				MainGameActivity.this.startActivity(intent);				//启动activity
				sound.playMusic(Constants.BUTTON_PRESS, 0)  ;
				sound.mp.start();											//开始音乐
			}}) ;
        Button gameMethod = (Button)findViewById(R.id.howtoplay)  ;			//游戏方法
        gameMethod.setOnClickListener(new Button.OnClickListener(){			//按钮添加监听
			@Override
			public void onClick(View v) {
				sound.playMusic(Constants.BUTTON_PRESS, 0)  ;
				Intent intent = new Intent(MainGameActivity.this,YouXiFangFaActivity.class);
				MainGameActivity.this.startActivity(intent);				//启动Activity
			}});
        Button aboutBtn = (Button)findViewById(R.id.aboutgame)  ;			//关于游戏
        aboutBtn.setOnClickListener(new Button.OnClickListener(){			//按钮添加监听
			@Override
			public void onClick(View v) {
				sound.playMusic(Constants.BUTTON_PRESS, 0)  ;				//开始音乐
				Intent intent = new Intent(MainGameActivity.this,AboutGameActivity.class);
				MainGameActivity.this.startActivity(intent);
			}})  ;
        Button exitBtn = (Button) this.findViewById(R.id.exit)  ;			//退出游戏按钮
        exitBtn.setOnClickListener(new OnClickListener(){					//按钮添加监听
			@Override
			public void onClick(View arg0){
				sound.playMusic(Constants.BUTTON_PRESS, 0)  ;				
				System.exit(0)  ;											//退出游戏
			}}) ;}   
        final int MENU_GENDER=1;  
        final int MAIN_GROUP=2;        										//外层总菜单项组的编号
        @Override
        public boolean onCreateOptionsMenu(Menu menu){
        	SubMenu subMenuGender = menu.addSubMenu(MAIN_GROUP,MENU_GENDER,0,R.string.about);
        	return true;													//关于单选菜单项组   菜单若编组就是单选菜单项组 
        }
        @Override  
        public boolean onOptionsItemSelected(MenuItem mi){					//单选或复选菜单项选中状态变化后的回调方法
        	switch(mi.getItemId()){
        	   case MENU_GENDER:
        		   Intent intent = new Intent(MainGameActivity.this,AboutGameActivity.class);
        		   MainGameActivity.this.startActivity(intent);				//启动Activity
        		   break;
        	}    	    	
        	return true;
        }   
}