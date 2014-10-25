package com.baina.tower.allactivity;

import com.baina.tower.constant.Constants;
import com.baina.tower.view.YouXiFangFaSurfaceView;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class YouXiFangFaActivity extends Activity
{
	YouXiFangFaSurfaceView mv;
	
	public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,    
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//设置为横屏模式
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		WindowManager windowManager = this.getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		Constants.PMX1 = display.getWidth();
		Constants.PMY1 = display.getHeight();
		mv = new YouXiFangFaSurfaceView(this);
		this.setContentView(mv);
    }
}
