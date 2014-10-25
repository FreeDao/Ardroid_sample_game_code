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
        
        //����Ϊȫ��
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//����Ϊ����ģʽ
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
    	//Ŀ�굥ѡ�˵�����   	
    	SubMenu subMenuTarget = menu.addSubMenu(MAIN_GROUP,MENU_TARGET,0,"Ŀ��ѡ��");
    	subMenuTarget.setIcon(R.drawable.t);
    	
    	MenuItem target=subMenuTarget.add(TARGET_GROUP, MENU_TARGET_A, 0, "Ŀ��1");
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
    	
    	
    	target=subMenuTarget.add(TARGET_GROUP, MENU_TARGET_B, 0, "Ŀ��2");
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
    	
    	target=subMenuTarget.add(TARGET_GROUP, MENU_TARGET_C, 0, "Ŀ��3");
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
    	
    	//����TARGET_GROUP���ǿ�ѡ��ģ������
    	subMenuTarget.setGroupCheckable(TARGET_GROUP, true,true); 
    	
    	//�㷨��ѡ�˵�����   	
    	SubMenu subMenuAlgorithm= menu.addSubMenu(ALGORITHM_GROUP,MENU_ALGORITHM,0,"�㷨ѡ��");
    	subMenuAlgorithm.setIcon(R.drawable.a);
    	
    	MenuItem algorithm=subMenuAlgorithm.add(ALGORITHM_GROUP, MENU_ALGORITHM_1, 0, "�������");
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
    	
    	algorithm=subMenuAlgorithm.add(TARGET_GROUP, MENU_ALGORITHM_2, 0, "�������");
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
    	
    	algorithm=subMenuAlgorithm.add(TARGET_GROUP, MENU_ALGORITHM_3, 0, "�������A*");
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
    	
    	//����TARGET_GROUP���ǿ�ѡ��ģ������
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
        		case 0://ȥ��ӭ����
        			showDialog(RESULT_DIALOG_ID);
        		break;
    		}
		}
    };
    
    
    static final int RESULT_DIALOG_ID=0;
    Dialog resultdialog;
    String msg="";
    
    @Override
    public Dialog onCreateDialog(int id)//�����Ի���
    {    	
        Dialog result=null;
    	switch(id)
    	{
	    	case RESULT_DIALOG_ID://��������Ի���
	    		resultdialog=new MyDialog(this); 	    
				result=resultdialog;				
			break;	
    	}   
		return result;
    }
    
    //ÿ�ε����Ի���ʱ���ص��Զ�̬���¶Ի������ݵķ���
    @Override
    public void onPrepareDialog(int id, final Dialog dialog)
    {
    	//�����ǵȴ��Ի����򷵻�
    	switch(id)
    	{
    	   case RESULT_DIALOG_ID://��������Ի���
    		   //ȷ����ť
    		   Button bjhmcok=(Button)resultdialog.findViewById(R.id.button1);
    		   //��Ϣ�ı��ؼ�
    		   TextView tv=(TextView)resultdialog.findViewById(R.id.textView1);
    		   tv.setText(msg);
    		   
       		   //��ȷ����ť��Ӽ�����
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

