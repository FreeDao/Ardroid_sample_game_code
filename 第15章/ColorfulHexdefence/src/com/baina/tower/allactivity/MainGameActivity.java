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
	Button continueBtn;//������ť����
	@Override
	protected void onResume() {

		if(!SerializableGame.check(MainGameActivity.this)){			//�����Ϸ�浵�Ƿ����
			continueBtn.setClickable(false);
			continueBtn.setText("�������޴浵��");
		}else
		{
			continueBtn.setClickable(true);
			continueBtn.setText("������Ϸ");
		}
		
		super.onResume();
	}
	Button start;												//��ʼ��ť
	public static Sound sound;									//��Ϸ����
	GameSurfaceView gameView;									//������Ϸ����
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); 			//�����ޱ���
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);              
																//����Ϊȫ��ģʽ
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);		
																//����Ϊ����ģʽ
		sound = new Sound(this);								//����Sound����
        setContentView(R.layout.main);							//���õ�ǰ������ʾ
        start = (Button)findViewById(R.id.start);
        start.setOnClickListener(new OnClickListener(){			//��ʼ��ť��Ӽ���
        @Override
		public void onClick(View v) {
			sound.playMusic(Constants.BUTTON_PRESS, 0) ;		//��ʼ����
			Intent intent = new Intent(MainGameActivity.this,ListViewActivity.class);
			MainGameActivity.this.startActivity(intent);		//����Intent����ListViewActivity
		}});
        continueBtn = (Button)findViewById(R.id.continuegame);		//������Ϸ��ť
        continueBtn.setOnClickListener(new Button.OnClickListener(){		//��ť��Ӽ���
			@Override
			public void onClick(View v){
				if(!SerializableGame.check(MainGameActivity.this)){			//�����Ϸ�浵�Ƿ����
					return;
				}
				if(sound.mp==null){											//��������
					sound.mp =  MediaPlayer.create(MainGameActivity.this, R.raw.bnbg_music) ;
				}
				Bundle bundle = new Bundle();
				Intent intent = new Intent(MainGameActivity.this,StartGameActivity.class);
				bundle.putInt("mapNum", 100);
				intent.putExtra("bundle", bundle);							//������
				MainGameActivity.this.startActivity(intent);				//����activity
				sound.playMusic(Constants.BUTTON_PRESS, 0)  ;
				sound.mp.start();											//��ʼ����
			}}) ;
        Button gameMethod = (Button)findViewById(R.id.howtoplay)  ;			//��Ϸ����
        gameMethod.setOnClickListener(new Button.OnClickListener(){			//��ť��Ӽ���
			@Override
			public void onClick(View v) {
				sound.playMusic(Constants.BUTTON_PRESS, 0)  ;
				Intent intent = new Intent(MainGameActivity.this,YouXiFangFaActivity.class);
				MainGameActivity.this.startActivity(intent);				//����Activity
			}});
        Button aboutBtn = (Button)findViewById(R.id.aboutgame)  ;			//������Ϸ
        aboutBtn.setOnClickListener(new Button.OnClickListener(){			//��ť��Ӽ���
			@Override
			public void onClick(View v) {
				sound.playMusic(Constants.BUTTON_PRESS, 0)  ;				//��ʼ����
				Intent intent = new Intent(MainGameActivity.this,AboutGameActivity.class);
				MainGameActivity.this.startActivity(intent);
			}})  ;
        Button exitBtn = (Button) this.findViewById(R.id.exit)  ;			//�˳���Ϸ��ť
        exitBtn.setOnClickListener(new OnClickListener(){					//��ť��Ӽ���
			@Override
			public void onClick(View arg0){
				sound.playMusic(Constants.BUTTON_PRESS, 0)  ;				
				System.exit(0)  ;											//�˳���Ϸ
			}}) ;}   
        final int MENU_GENDER=1;  
        final int MAIN_GROUP=2;        										//����ܲ˵�����ı��
        @Override
        public boolean onCreateOptionsMenu(Menu menu){
        	SubMenu subMenuGender = menu.addSubMenu(MAIN_GROUP,MENU_GENDER,0,R.string.about);
        	return true;													//���ڵ�ѡ�˵�����   �˵���������ǵ�ѡ�˵����� 
        }
        @Override  
        public boolean onOptionsItemSelected(MenuItem mi){					//��ѡ��ѡ�˵���ѡ��״̬�仯��Ļص�����
        	switch(mi.getItemId()){
        	   case MENU_GENDER:
        		   Intent intent = new Intent(MainGameActivity.this,AboutGameActivity.class);
        		   MainGameActivity.this.startActivity(intent);				//����Activity
        		   break;
        	}    	    	
        	return true;
        }   
}