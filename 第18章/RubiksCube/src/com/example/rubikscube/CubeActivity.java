package com.example.rubikscube;


import java.util.Date;

import com.example.rubikscube.util.Constant;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.Toast;
import android.widget.ToggleButton;
import static com.example.rubikscube.util.Constant.*;

public class CubeActivity extends Activity {

	private MySurfaceView mGLSurfaceView;
	private MySurfaceView helpGLSurfaceView; 
	private WelcomeView mWelcomeView;
	private TableLayout rankList;
	Button bstart;
	Button boption;
	Button bhelp;
	Button bsetting;
	Button babout;
	Button bexit;
	WebView wHelp;
	boolean ismain = true;
	boolean isplay = false;
	
    final int MAIN_GROUP=0;
    
    final int MENU_UPSET=0; 
    final int MENU_RESET=1;
    final int MENU_AUTORESET=2;
    
    MenuItem mUpset = null;
    MenuItem mReset = null;
    MenuItem mAutoReset = null;
    
    Handler handler;
    
	static MediaPlayer mpBack;//游戏背景音乐播放器
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //设置为全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		mWelcomeView = new WelcomeView(CubeActivity.this);
				
		setContentView(mWelcomeView);  


		
      
		
        handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);

				switch (msg.what) {
				case 0:
					//到主界面去
					loadMain();
					
					break;
				case 1:
					Toast.makeText(CubeActivity.this, "请打乱后再玩,不要耍赖啊", 1000).show();
					break;
				case -23:
					break;
				}
			}
		};
			
		
        
    }

    



	@Override
	protected Dialog onCreateDialog(int id) {
		  Builder b=new AlertDialog.Builder(this);  
		  //b.setIcon(R.drawable.icon);//设置图标
		  b.setTitle("已还原");//设置标题
		  b.setMessage("恭喜，魔方已还原！按确定打乱");//设置信息
		  b.setPositiveButton//为对话框设置按钮
		  (
				"确定", 
				new DialogInterface.OnClickListener()
      		{
					@Override
					public void onClick(DialogInterface dialog, int which) {
						new UpsetThread(mGLSurfaceView.sr.cc,mGLSurfaceView.tt).start();	
					}      			
      		}
		  );
		  Dialog dialog=b.create();
		return dialog;
	}





	public void initSound()
    {
    	if(mpBack!=null)
    	{
    		return;
    	}
    	
    	//背景音乐
    	mpBack = MediaPlayer.create(this, R.raw.bgm); 
    	mpBack.setLooping(true);
    	mpBack.start();
    }
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_MENU)
		{
			if(!isplay)
			{
				return true;
			}
			return false;
		}
		if(keyCode==KeyEvent.KEYCODE_BACK){
			if(!ismain)
			{
				loadMain();
				return true;
			}
			else
			{
		        SharedPreferences sp = this.getSharedPreferences("setinf", Context.MODE_PRIVATE);
		        SharedPreferences.Editor editor=sp.edit();
		        editor.putInt("ORDER", ORDER);
		        editor.putInt("STYLE", STYLE);
		        editor.putBoolean("bbgm", bbgm);
		        editor.putBoolean("bse", bse);  
		        editor.commit();
				System.exit(0);
				return false;
			}
		}
		return false;
	}
    
    public void loadMain()
    {
		setContentView(R.layout.main);
		ismain = true;
		isplay = false;
        bstart = (Button) findViewById(R.id.button1);
        boption = (Button) findViewById(R.id.button2);
        bhelp = (Button) findViewById(R.id.button3);
        bsetting = (Button) findViewById(R.id.button4);
        babout = (Button) findViewById(R.id.button5);
        bexit = (Button) findViewById(R.id.button6);
        
        if(Constant.bbgm)
        {
        	initSound();//初始化声音
        }
		
        bstart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		        //获取屏幕尺寸
		        DisplayMetrics dm=new DisplayMetrics();
		        getWindowManager().getDefaultDisplay().getMetrics(dm);  

		        mGLSurfaceView = new MySurfaceView(CubeActivity.this,false);
		        mGLSurfaceView.screenHeight=dm.heightPixels;
		        mGLSurfaceView.screenWidth=dm.widthPixels;
		        //计算屏幕宽高比
		        mGLSurfaceView.ratio=
		        	mGLSurfaceView.screenWidth/mGLSurfaceView.screenHeight;        
		 
		        
		        mGLSurfaceView.requestFocus();//获取焦点
		        mGLSurfaceView.setFocusableInTouchMode(true);//设置为可触控
		        mGLSurfaceView.ishelp = false;
		        //mGLSurfaceView.tt.ms = 0;
				
		        setContentView(mGLSurfaceView);  
		        
		        
		        
		        
		        ismain = false;
		        isplay = true;
			}
		});
        
        boption.setOnClickListener(new OnClickListener() {
			
        	ToggleButton tbgm;
        	ToggleButton tse;
        	RadioGroup rg;
        	RadioButton r3;
        	RadioButton r2;
        	RadioGroup rgst;
        	RadioButton rst1;
        	RadioButton rst2;
        	
        	
			@Override
			public void onClick(View v) {				
		        setContentView(R.layout.setting);
		        tbgm = (ToggleButton) findViewById(R.id.tbgm);
		        tse = (ToggleButton) findViewById(R.id.tse);
		        rg = (RadioGroup)findViewById(R.id.RadioGroup01);
		        r3 = (RadioButton) findViewById(R.id.r3);
		        r2 = (RadioButton) findViewById(R.id.r2);
		        //findViewById()
		        
		        rgst = (RadioGroup)findViewById(R.id.RadioGroup02);
		        rst1 = (RadioButton) findViewById(R.id.rbc);
		        rst2 = (RadioButton) findViewById(R.id.rbp);
		        
		        tbgm.setChecked(Constant.bbgm);
		        tse.setChecked(Constant.bse);
		        if(Constant.ORDER==2)
		        {
		        	r2.setChecked(true);
		        }
		        else if(Constant.ORDER==3)
		        {
		        	r3.setChecked(true);
		        }
		        if(Constant.STYLE==0)
		        {
		        	rst1.setChecked(true);
		        }
		        else if(Constant.STYLE==1)
		        {
		        	rst2.setChecked(true);
		        }
		        tbgm.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						Constant.bbgm = tbgm.isChecked();
				        if(Constant.bbgm)
				        {
				        	initSound();//初始化声音
				        }
				        else
				        {
				        	mpBack.stop();
				        	mpBack = null;
				        }
					}
				});
		        tse.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		        	
		        	@Override
		        	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		        		Constant.bse = tse.isChecked();
		        		
		        	}
		        });
		        
		        rg.setOnCheckedChangeListener(new android.widget.RadioGroup.OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						if(r3.isChecked())
						{
							Constant.ORDER = 3;
						}
						else if(r2.isChecked())
						{
							Constant.ORDER = 2;
						}
					}

				});
		        rgst.setOnCheckedChangeListener(new android.widget.RadioGroup.OnCheckedChangeListener() {
		        	
		        	@Override
		        	public void onCheckedChanged(RadioGroup group, int checkedId) {
		        		if(rst1.isChecked())
		        		{
		        			Constant.STYLE = 0;
		        		}
		        		else if(rst2.isChecked())
		        		{
		        			Constant.STYLE = 1;
		        		}
		        	}
		        	
		        });
		        
		        
		        
		        ismain = false;
		        
		        
			}
		});

        
        bhelp.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		DisplayMetrics dm=new DisplayMetrics();
        		getWindowManager().getDefaultDisplay().getMetrics(dm);  
        		helpGLSurfaceView = new MySurfaceView(CubeActivity.this,true);
        		
        		helpGLSurfaceView.screenHeight=dm.heightPixels;
        		helpGLSurfaceView.screenWidth=dm.widthPixels;
        		//计算屏幕宽高比
        		helpGLSurfaceView.ratio=
        				helpGLSurfaceView.screenWidth/helpGLSurfaceView.screenHeight;        
        		
        		
        		helpGLSurfaceView.requestFocus();//获取焦点
        		helpGLSurfaceView.setFocusableInTouchMode(true);//设置为可触控
        		helpGLSurfaceView.ishelp = true;
        		helpGLSurfaceView.helpStart = true;
        		
        		
        		setContentView(helpGLSurfaceView);  
        		
        		
        		
        		ismain = false;
        	}
        });
        
        bsetting.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {				
		        setContentView(R.layout.goldlist);
		        rankList = (TableLayout) findViewById(R.id.tl);
		        new RankingLoad(rankList, CubeActivity.this);
		        ismain = false;
			}
		});
        babout.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {				
        		setContentView(R.layout.about);
        		ismain = false;
        	}
        });
        
        bexit.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {				
		        SharedPreferences sp = CubeActivity.this.getSharedPreferences("setinf", Context.MODE_PRIVATE);
		        SharedPreferences.Editor editor=sp.edit();
		        editor.putInt("ORDER", ORDER);
		        editor.putInt("STYLE", STYLE);
		        editor.putBoolean("bbgm", bbgm);
		        editor.putBoolean("bse", bse);  
		        editor.commit();
				System.exit(0);
        	}
        });
    }
    

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		mUpset = menu.add(MAIN_GROUP,MENU_UPSET,0,"打乱");
		mUpset.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				new UpsetThread(mGLSurfaceView.sr.cc,mGLSurfaceView.tt).run();
				return true;
			}
		});
		mReset = menu.add(MAIN_GROUP,MENU_RESET,0,"强制还原");
		mReset.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
					mGLSurfaceView.sr.cc.cubeData = new CubeData();
					mGLSurfaceView.sr.cc.reSetColor();
					mGLSurfaceView.canmove = false;
					mGLSurfaceView.tt.timestop();
				return true;
			}
		});
		mAutoReset = menu.add(MAIN_GROUP,MENU_AUTORESET,0,"自动还原");
		mAutoReset.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				mGLSurfaceView.sr.cc.reSetCube();
				mGLSurfaceView.canmove = false;
				mGLSurfaceView.tt.timestop();
				return true;
			}
		});
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		return super.onOptionsItemSelected(item);
	}

	@Override
    protected void onResume() {
        super.onResume();
        if(mGLSurfaceView!=null)
        {
        	mGLSurfaceView.tt.isStop = false;
        }
        if(mpBack!=null)
        {
        	mpBack.start();
        }
        
		SharedPreferences sp = this.getSharedPreferences("setinf", Context.MODE_PRIVATE);
		ORDER = sp.getInt("ORDER", 3);
		STYLE = sp.getInt("STYLE", 0);		
		bbgm = sp.getBoolean("bbgm", true);
		bse = sp.getBoolean("bse", false);
		System.out.println(ORDER+"="+STYLE+"="+bbgm+"="+bse);
		
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mGLSurfaceView!=null)
        {
        	mGLSurfaceView.tt.isStop = true;
        }
        if(mpBack!=null)
        {
        	mpBack.pause();
        }
        
        SharedPreferences sp = this.getSharedPreferences("setinf", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putInt("ORDER", ORDER);
        editor.putInt("STYLE", STYLE);
        editor.putBoolean("bbgm", bbgm);
        editor.putBoolean("bse", bse);  
        editor.commit();

    }



    
    
}
