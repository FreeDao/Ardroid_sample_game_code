package com.example.rubikscube;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class RankingLoad{

	TableLayout rl;
	SQLiteDatabase sld;
	Context context;
	
	public RankingLoad(TableLayout rl,Context context) {
		this.rl = rl;
		this.context = context;
		createOrOpenDatabase();
		query();
		closeDatabase();
	}
	
	public RankingLoad(long time) {
		createOrOpenDatabase();
		insert(time);
		closeDatabase();
	}
	
	
    public void createOrOpenDatabase()
    {
    	try
    	{
	    	sld=SQLiteDatabase.openDatabase
	    	(
	    			"/data/data/com.example.rubikscube/rankdb", //数据库所在路径
	    			null, 								//CursorFactory
	    			SQLiteDatabase.OPEN_READWRITE|SQLiteDatabase.CREATE_IF_NECESSARY //读写、若不存在则创建
	    	);	    
	    	String sql="create table if not exists ranklist(rdate char(10),rtime integer)";
	    	sld.execSQL(sql);		
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }
    
    public void insert(long time)
    {
    	SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
    	String sto = df.format(new Date());
    	try
    	{
        	String sql="insert into ranklist values('"+sto+"',"+time+")";
        	sld.execSQL(sql);
    	}
		catch(Exception e)
		{
    		e.printStackTrace();
		}
    }
    
    //查询的方法
    public void query()
    {
    	String[] row = new String[2];
    	try
    	{
        	String sql="select rdate,rtime from ranklist order by rtime";    	
        	Cursor cur=sld.rawQuery(sql, null);
        	int i=1;
        	while(cur.moveToNext())
        	{
        		row[0]=cur.getString(0);
        		row[1]=cur.getString(1);
        		for(int j=0;j<2;j++)
        		{
        			System.out.println(row[j]);
        		}
        		
    			TableRow tr = new TableRow(context);
    			
    			TextView[] tv = new TextView[3];
    			
    			
    			LinearLayout llrank = new LinearLayout(context);
    			llrank.setOrientation(LinearLayout.HORIZONTAL);

    			for(int j=0;j<3;j++)
    			{
    				tv[j] = new TextView(context);
    				tv[j].setTextColor(Color.WHITE);
    			}

    			String s = i+"";
    			for(int j=0;j<s.length();j++)
    			{
    				ImageView r0 = new ImageView(context);
        			r0.setAdjustViewBounds(true);
        			r0.setMaxHeight(40);
    				r0.setImageResource(R.drawable.d0+Integer.parseInt(""+s.charAt(j)));
    				llrank.addView(r0);
    			}
    			
    			tr.addView(llrank);
 
    			
    			LinearLayout lldate = new LinearLayout(context);
    			lldate.setOrientation(LinearLayout.HORIZONTAL);
    			tv[1].setText(row[0]);
//    			tr.addView(tv[1]);
    			
    			for(int k=0;k<row[0].length();k++)
    			{
					ImageView im = new ImageView(context);
	    			im.setAdjustViewBounds(true);
	    			im.setMaxHeight(40);
    				if(k==4||k==7)
    				{
    					im.setImageResource(R.drawable.dy);
    				}
    				else
    				{
    	    			im.setImageResource(R.drawable.d0+Integer.parseInt(""+row[0].charAt(k)));
    				}
    				lldate.addView(im);
    			}
    			tr.addView(lldate);
    			
    			Date tdate = new Date();
    			tdate.setTime(Long.parseLong(row[1]));
    			String stime = tdate.getMinutes()+":"+tdate.getSeconds();
    			//tv[2].setText(tdate.getMinutes()+":"+tdate.getSeconds());
    			//tr.addView(tv[2]);
    			LinearLayout lltime = new LinearLayout(context);
    			lltime.setOrientation(LinearLayout.HORIZONTAL);
    			tv[1].setText(row[0]);
    			
    			for(int k=0;k<stime.length();k++)
    			{
					ImageView im = new ImageView(context);
	    			im.setAdjustViewBounds(true);
	    			im.setMaxHeight(40);
    				if(stime.charAt(k) == ':')
    				{
    					im.setImageResource(R.drawable.dx);
    				}
    				else
    				{
    	    			im.setImageResource(R.drawable.d0+Integer.parseInt(""+stime.charAt(k)));
    				}
    				lltime.addView(im);
    			}
    			tr.addView(lltime);
    			
    			
    			
    			
    			rl.addView(tr);

        		i++;
        		if(i>10)
        		{
        			break;
        		}
        		
        	}
        	cur.close();	

    	}
		catch(Exception e)
		{
    		e.printStackTrace();
    	}

    }
    
    public void closeDatabase()
    {
    	try
    	{
	    	sld.close();    	
    	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
}
