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
        
        //����Ϊȫ��
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//����Ϊ����ģʽ
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        
        mv = new MySurfaceView(this);
        this.setContentView(mv);
    }
 
}