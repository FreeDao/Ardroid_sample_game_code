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

	//������Դ�ַ�����andy��bill��edgar��torvalds��turing��id������
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
        requestWindowFeature(Window.FEATURE_NO_TITLE); 				//����Ϊ�ޱ���
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
																	//����Ϊ����ģʽ
		createOrOpenDatabase();										//�������ݿ�
        setContentView(R.layout.listviewactivitymain);				//������ʾ����
      
        puTong = (Button)findViewById(R.id.putong)  ;		//��ͨ��ť�����¼�
        puTong.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				if(MainGameActivity.sound.mp==null){
	        		MainGameActivity.sound.mp =                     //�������
	        			MediaPlayer.create(ListViewActivity.this, R.raw.bnbg_music);
				}
				MainGameActivity.sound.playMusic(Constants.sf_swish, 0)  ;
				puTong.setText("��ͨ���趨��");
				kunNan.setText("����");
				Constants.MASTERNUM1BLOOD = 30;						//���ù���Ѫ��
				Constants.MASTERNUM2BLOOD = 35;
				Constants.MASTERNUM3BLOOD = 45;
				Constants.INCREASE_BLOOD1 = 30;
				Constants.INCREASE_BLOOD2 = 35;
				Constants.INCREASE_BLOOD3 = 45;
			}
        	
        }) ;
       
        kunNan = (Button)findViewById(R.id.kunnan)  ;		 //���Ѱ�ť�����¼�
        kunNan.setOnClickListener(new Button.OnClickListener() {
			@Override
			public void onClick(View v){
				if(MainGameActivity.sound.mp==null){				//�������
	        		MainGameActivity.sound.mp =  MediaPlayer.create(
	        				ListViewActivity.this, R.raw.bnbg_music) ;
				}
				MainGameActivity.sound.playMusic(Constants.sf_swish, 0)  ;
				puTong.setText("��ͨ");
				kunNan.setText("���ѣ��趨��");
				Constants.MASTERNUM1BLOOD = 40;						//���ù���Ѫ��
				Constants.MASTERNUM2BLOOD = 45;
				Constants.MASTERNUM3BLOOD = 55;
				Constants.INCREASE_BLOOD1 = 40;
				Constants.INCREASE_BLOOD2 = 45;
				Constants.INCREASE_BLOOD3 = 55;
			}
        	
        }) ;
        																
        ListView lv=(ListView)this.findViewById(R.id.ListView01);	//��ʼ��ListView
        
        
        BaseAdapter ba=new BaseAdapter(){							//ΪListView׼������������	
        	LayoutInflater inflater=LayoutInflater.from(
        			ListViewActivity.this);
			@Override
			public int getCount() {
				return msgIds.length;								//�ܹ�20��ѡ��
			}
			@Override
			public Object getItem(int arg0) { return null; }
			@Override
			public long getItemId(int arg0) { return 0; }
			@Override
			public View getView(int arg0, View arg1, ViewGroup arg2) {
				LinearLayout ll = (									//�õ�LinearLayout
				  LinearLayout)(inflater.inflate(
						  R.layout.row, null).findViewById(R.id.LinearLayout_row));
				TextView tv=(TextView)ll.getChildAt(0);
				tv.setText(getResources().getText(msgIds[arg0]));	//���ùؿ�����	
				TextView tv1=(TextView)ll.getChildAt(1);			//����ÿ���ؿ���ߵ÷�
				tv1.setText(getResources().getText(R.string.highpoint)+" "+query(arg0+1));
				return ll;
			}};
        lv.setAdapter(ba);											//ΪListView��������������
        

        
        lv.setOnItemClickListener(									//����ѡ������ļ�����
           new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
					if(MainGameActivity.sound.mp==null){			//���õ������
						MainGameActivity.sound.mp =  MediaPlayer.create(
								ListViewActivity.this, R.raw.bnbg_music) ;
					}
					MainGameActivity.sound.mp.start();				//��ʼ����
					MainGameActivity.sound.playMusic(Constants.sf_swish, 0)  ;
					Bundle bundle = new Bundle();
					Intent intent = new Intent(						//����Intent����ʼ��Ϸ��
							ListViewActivity.this,StartGameActivity.class);
					int highpoint = query(arg2+1);
					bundle.putInt("mapNum", arg2);		//���ݹؿ�������ߵ÷ָ�StartGameActivity
					bundle.putInt("highpoint",highpoint);
					intent.putExtra("bundle", bundle);
					ListViewActivity.this.startActivity(intent);	//����StartGameActivity
			} });
        lv.setPadding(20, 20, 20, 20);								//ListView���������
        lv.setBackgroundColor(Color.BLACK);
    } 
    
    
    
    public void createOrOpenDatabase(){
    	try{
	    	sld=SQLiteDatabase.openDatabase(
	    			"/data/data/com.baina.tower.allactivity/mydb", //���ݿ�����·��
	    			null, 								//CursorFactory
	    			SQLiteDatabase.OPEN_READWRITE|SQLiteDatabase.CREATE_IF_NECESSARY //��д�����������򴴽�
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
    //�ر����ݿ�ķ���
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


	//��ѯ�ķ���
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