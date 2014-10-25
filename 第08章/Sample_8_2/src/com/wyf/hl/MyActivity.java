package com.wyf.hl;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import android.view.Window;
import android.view.WindowManager;



public class MyActivity extends Activity {
    
	MySurfaceView mv;
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        
        //设置为全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//设置为横屏模式
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        
        mv = new MySurfaceView(this);
        this.setContentView(mv);
    }
 
}