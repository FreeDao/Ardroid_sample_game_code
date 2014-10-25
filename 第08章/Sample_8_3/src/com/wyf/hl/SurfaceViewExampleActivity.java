package com.wyf.hl;

import android.app.Activity;
import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class SurfaceViewExampleActivity extends Activity {
    
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
    
    static final int MAIN_GROUP=0;
    static final int TARGET_GROUP=1;
    static final int ALGORITHM_GROUP=1;
    static final int MENU_TARGET=0;
    static final int MENU_ALGORITHM=0;
    static final int MENU_TARGET_A=1;
    static final int MENU_TARGET_B=2;
    static final int MENU_TARGET_C=3;
    static final int MENU_ALGORITHM_1=4;
    static final int MENU_ALGORITHM_2=5;
    static final int MENU_ALGORITHM_3=6;
    static final int MENU_ALGORITHM_4=7;
    static final int MENU_ALGORITHM_5=8;
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	//目标单选菜单项组   	
    	SubMenu subMenuTarget = menu.addSubMenu(MAIN_GROUP,MENU_TARGET,0,"目标选择");
    	subMenuTarget.setIcon(R.drawable.t);
    	
    	MenuItem target=subMenuTarget.add(TARGET_GROUP, MENU_TARGET_A, 0, "目标1");
    	target.setChecked(true);
    	target.setOnMenuItemClickListener
    	(
    		new OnMenuItemClickListener()
    		{
				@Override
				public boolean onMenuItemClick(MenuItem item) 
				{				
					item.setChecked(true);
					mv.targetId=0; 
		    		mv.game=new Game(mv);
		    	    mv.repaint();
		    	    mv.doSearch();
					return true;
				}
    		}
    	);
    	
    	
    	target=subMenuTarget.add(TARGET_GROUP, MENU_TARGET_B, 0, "目标2");
    	target.setOnMenuItemClickListener
    	(
    		new OnMenuItemClickListener()
    		{
				@Override
				public boolean onMenuItemClick(MenuItem item) 
				{				
					item.setChecked(true);
					mv.targetId=1; 
		    		mv.game=new Game(mv);
		    	    mv.repaint();
		    	    mv.doSearch();
					return true;
				}
    		}
    	);
    	
    	target=subMenuTarget.add(TARGET_GROUP, MENU_TARGET_C, 0, "目标3");
    	target.setOnMenuItemClickListener
    	(
    		new OnMenuItemClickListener()
    		{
				@Override
				public boolean onMenuItemClick(MenuItem item) 
				{				
					item.setChecked(true);
					mv.targetId=2; 
		    		mv.game=new Game(mv);
		    	    mv.repaint();
		    	    mv.doSearch();
					return true;
				}
    		}
    	);    	
    	
    	//设置TARGET_GROUP组是可选择的，互斥的
    	subMenuTarget.setGroupCheckable(TARGET_GROUP, true,true); 
    	
    	//算法单选菜单项组   	
    	SubMenu subMenuAlgorithm= menu.addSubMenu(ALGORITHM_GROUP,MENU_ALGORITHM,0,"算法选择");
    	subMenuAlgorithm.setIcon(R.drawable.a);
    	
    	MenuItem algorithm=subMenuAlgorithm.add(ALGORITHM_GROUP, MENU_ALGORITHM_1, 0, "深度优先");
    	algorithm.setChecked(true);
    	algorithm.setOnMenuItemClickListener
    	(
    		new OnMenuItemClickListener()
    		{
				@Override
				public boolean onMenuItemClick(MenuItem item) 
				{				
					item.setChecked(true);
		    		mv.sfId=0;
		    		mv.game=new Game(mv);
		    	    mv.repaint();
		    	    mv.doSearch();
					return true;
				}
    		}
    	);
    	
    	algorithm=subMenuAlgorithm.add(TARGET_GROUP, MENU_ALGORITHM_2, 0, "广度优先");
    	algorithm.setOnMenuItemClickListener
    	(
    		new OnMenuItemClickListener()
    		{
				@Override
				public boolean onMenuItemClick(MenuItem item) 
				{				
					item.setChecked(true);
		    		mv.sfId=1;
		    		mv.game=new Game(mv);
		    	    mv.repaint();
		    	    mv.doSearch();
					return true;
				}
    		}
    	);
    	
    	algorithm=subMenuAlgorithm.add(TARGET_GROUP, MENU_ALGORITHM_3, 0, "广度优先A*");
    	algorithm.setOnMenuItemClickListener
    	(
    		new OnMenuItemClickListener()
    		{
				@Override
				public boolean onMenuItemClick(MenuItem item) 
				{				
					item.setChecked(true);
		    		mv.sfId=2;
		    		mv.game=new Game(mv);
		    	    mv.repaint();
		    	    mv.doSearch();
					return true;
				}
    		}
    	);
    	
    	algorithm=subMenuAlgorithm.add(TARGET_GROUP, MENU_ALGORITHM_4, 0, "Dijkstra");
    	algorithm.setOnMenuItemClickListener
    	(
    		new OnMenuItemClickListener()
    		{
				@Override
				public boolean onMenuItemClick(MenuItem item) 
				{				
					item.setChecked(true);
		    		mv.sfId=3;
		    		mv.game=new Game(mv);
		    	    mv.repaint();
		    	    mv.doSearch();
					return true;
				}
    		}
    	);
    	
    	algorithm=subMenuAlgorithm.add(TARGET_GROUP, MENU_ALGORITHM_5, 0, "Dijkstra A*");
    	algorithm.setOnMenuItemClickListener
    	(
    		new OnMenuItemClickListener()
    		{
				@Override
				public boolean onMenuItemClick(MenuItem item) 
				{				
					item.setChecked(true);
		    		mv.sfId=4;
		    		mv.game=new Game(mv);
		    	    mv.repaint();
		    	    mv.doSearch();
					return true;
				}
    		}
    	);
    	
    	//设置TARGET_GROUP组是可选择的，互斥的
    	subMenuAlgorithm.setGroupCheckable(ALGORITHM_GROUP, true, true); 
    	return true;
    }
    
    Handler hd=new Handler()
    {
		@Override
		public void handleMessage(Message msg)
		{
    		switch(msg.what)
    		{
        		case 0://去欢迎界面
        			showDialog(RESULT_DIALOG_ID);
        		break;
    		}
		}
    };
    
    
    static final int RESULT_DIALOG_ID=0;
    Dialog resultdialog;
    String msg="";
    
    @Override
    public Dialog onCreateDialog(int id)//创建对话框
    {    	
        Dialog result=null;
    	switch(id)
    	{
	    	case RESULT_DIALOG_ID://姓名输入对话框
	    		resultdialog=new MyDialog(this); 	    
				result=resultdialog;				
			break;	
    	}   
		return result;
    }
    
    //每次弹出对话框时被回调以动态更新对话框内容的方法
    @Override
    public void onPrepareDialog(int id, final Dialog dialog)
    {
    	//若不是等待对话框则返回
    	switch(id)
    	{
    	   case RESULT_DIALOG_ID://姓名输入对话框
    		   //确定按钮
    		   Button bjhmcok=(Button)resultdialog.findViewById(R.id.button1);
    		   //消息文本控件
    		   TextView tv=(TextView)resultdialog.findViewById(R.id.textView1);
    		   tv.setText(msg);
    		   
       		   //给确定按钮添加监听器
       		   bjhmcok.setOnClickListener
               (
    	          new OnClickListener()
    	          {
    				@Override
    				public void onClick(View v) 
    				{
    					resultdialog.cancel();
    				}        	  
    	          }
    	        );          		  
    	   break;	
    	}
    }    
}

