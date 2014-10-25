package com.baina.tower.allactivity;

import com.baina.tower.constant.Constants;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ListViewActivity extends Activity {
	SQLiteDatabase sld;
	Button kunNan;
	Button puTong;

	//所有资源字符串（andy、bill、edgar、torvalds、turing）id的数组
	int[] msgIds={R.string.number1,R.string.number2,R.string.number3,
			R.string.number4,R.string.number5,R.string.number6,
			R.string.number7,R.string.number8,R.string.number9,
			R.string.number10,R.string.number11,R.string.number12,
			R.string.number13,R.string.number14,R.string.number15,
			R.string.number16,R.string.number17,R.string.number18,
			R.string.number19,R.string.number20,
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); 				//设置为无标题
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
																	//设置为竖屏模式
		createOrOpenDatabase();										//创建数据库
        setContentView(R.layout.listviewactivitymain);				//设置显示界面
      
        puTong = (Button)findViewById(R.id.putong)  ;		//普通按钮监听事件
        puTong.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				if(MainGameActivity.sound.mp==null){
	        		MainGameActivity.sound.mp =                     //点击音乐
	        			MediaPlayer.create(ListViewActivity.this, R.raw.bnbg_music);
				}
				MainGameActivity.sound.playMusic(Constants.sf_swish, 0)  ;
				puTong.setText("普通（设定）");
				kunNan.setText("困难");
				Constants.MASTERNUM1BLOOD = 30;						//设置怪物血量
				Constants.MASTERNUM2BLOOD = 35;
				Constants.MASTERNUM3BLOOD = 45;
				Constants.INCREASE_BLOOD1 = 30;
				Constants.INCREASE_BLOOD2 = 35;
				Constants.INCREASE_BLOOD3 = 45;
			}
        	
        }) ;
       
        kunNan = (Button)findViewById(R.id.kunnan)  ;		 //困难按钮监听事件
        kunNan.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v){
				if(MainGameActivity.sound.mp==null){				//点击音乐
	        		MainGameActivity.sound.mp =  MediaPlayer.create(
	        				ListViewActivity.this, R.raw.bnbg_music) ;
				}
				MainGameActivity.sound.playMusic(Constants.sf_swish, 0)  ;
				puTong.setText("普通");
				kunNan.setText("困难（设定）");
				Constants.MASTERNUM1BLOOD = 40;						//设置怪物血量
				Constants.MASTERNUM2BLOOD = 45;
				Constants.MASTERNUM3BLOOD = 55;
				Constants.INCREASE_BLOOD1 = 40;
				Constants.INCREASE_BLOOD2 = 45;
				Constants.INCREASE_BLOOD3 = 55;
			}
        	
        }) ;
        																
        ListView lv=(ListView)this.findViewById(R.id.ListView01);	//初始化ListView
        
        
        BaseAdapter ba=new BaseAdapter(){							//为ListView准备内容适配器	
        	LayoutInflater inflater=LayoutInflater.from(
        			ListViewActivity.this);
			@Override
			public int getCount() {
				return msgIds.length;								//总共20个选项
			}
			@Override
			public Object getItem(int arg0) { return null; }
			@Override
			public long getItemId(int arg0) { return 0; }
			@Override
			public View getView(int arg0, View arg1, ViewGroup arg2) {
				LinearLayout ll = (									//拿到LinearLayout
				  LinearLayout)(inflater.inflate(
						  R.layout.row, null).findViewById(R.id.LinearLayout_row));
				TextView tv=(TextView)ll.getChildAt(0);
				tv.setText(getResources().getText(msgIds[arg0]));	//设置关卡内容	
				TextView tv1=(TextView)ll.getChildAt(1);			//设置每个关卡最高得分
				tv1.setText(getResources().getText(R.string.highpoint)+" "+query(arg0+1));
				return ll;
			}};
        lv.setAdapter(ba);											//为ListView设置内容适配器
        

        
        lv.setOnItemClickListener(									//设置选项被单击的监听器
           new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
					if(MainGameActivity.sound.mp==null){			//设置点击音乐
						MainGameActivity.sound.mp =  MediaPlayer.create(
								ListViewActivity.this, R.raw.bnbg_music) ;
					}
					MainGameActivity.sound.mp.start();				//开始音乐
					MainGameActivity.sound.playMusic(Constants.sf_swish, 0)  ;
					Bundle bundle = new Bundle();
					Intent intent = new Intent(						//创建Intent（开始游戏）
							ListViewActivity.this,StartGameActivity.class);
					int highpoint = query(arg2+1);
					bundle.putInt("mapNum", arg2);		//传递关卡号与最高得分给StartGameActivity
					bundle.putInt("highpoint",highpoint);
					intent.putExtra("bundle", bundle);
					ListViewActivity.this.startActivity(intent);	//启动StartGameActivity
			} });
        lv.setPadding(20, 20, 20, 20);								//ListView的相关设置
        lv.setBackgroundColor(Color.BLACK);
    } 
    
    
    
    public void createOrOpenDatabase(){
    	try{
	    	sld=SQLiteDatabase.openDatabase(
	    			"/data/data/com.baina.tower.allactivity/mydb", //数据库所在路径
	    			null, 								//CursorFactory
	    			SQLiteDatabase.OPEN_READWRITE|SQLiteDatabase.CREATE_IF_NECESSARY //读写、若不存在则创建
	    	);
	    	String sql="create table if not exists hextower(gamecount integer primary key,gname varchar(20),points integer)";
	    	sld.execSQL(sql);	
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	int result = 0;
    	String sql3 = "select count(gamecount) from hextower ";
    	Cursor cur=sld.rawQuery(sql3, null);
    	if(cur.moveToNext()){
    		result = cur.getInt(0);
    	}
    	if(result!=20){
    		for(int i=1; i<21; i++){
				String sql = "insert into hextower values ("+i+",'',0)";
				sld.execSQL(sql);
			}
    	}
    	cur.close();
    }
    //关闭数据库的方法
    public void closeDatabase() {
    	try{
	    	sld.close();        		
    	}catch(Exception e){
			e.printStackTrace();
		}
    }
    public void update(int gamecount , int points){    	
    	try{
    		String sql2="update hextower set points=? where gamecount = ?";
        	sld.execSQL(sql2,new Object[]{points,gamecount+1});
    	}
		catch(Exception e){
			e.printStackTrace();
		}
    }
    @Override
	protected void onDestroy() {
		super.onDestroy();
		this.closeDatabase();
	}


	//查询的方法
    public int query(int count){
    	int temp = 0;
    	try{
        	String sql="select * from hextower where gamecount =?";    	
        	Cursor cur=sld.rawQuery(sql, new String[]{count+""});
        	while(cur.moveToNext()){
        		temp = cur.getInt(cur.getColumnIndex("points"));
        	}
        	cur.close();		
    	}catch(Exception e){
			e.printStackTrace();
		}
		return temp;
    }
}