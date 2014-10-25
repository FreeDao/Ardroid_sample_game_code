package com.baina.tower.allactivity;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class DatabaseContentProvider  extends ContentProvider{
	private static final UriMatcher um;				//用于匹配的UriMatcher
	static{
		um=new UriMatcher(UriMatcher.NO_MATCH);		//增加Uri，用于修改
		um.addURI("com.baina.hextower", "tower/#", 1);
	}
	SQLiteDatabase sld;								//创建数据库对象
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;									//删除数据
	}
	@Override
	public String getType(Uri uri) {				//得到Uri类型
		return null;			
	}
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		return null;								//插入数据
	}
	@Override
	public boolean onCreate() {						//打开数据库
		sld=SQLiteDatabase.openDatabase
    	(
    			"/data/data/com.baina.tower.allactivity/mydb", 	//数据库所在路径
    			null, 											//CursorFactory
    			SQLiteDatabase.OPEN_READWRITE|SQLiteDatabase.CREATE_IF_NECESSARY 
    															//读写、若不存在则创建
    	);
		return false;
	}
	@Override										//查询数据
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		return null;
	}
	@Override										//修改数据
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		switch(um.match(uri)){
		  case 1:
			  long rowid = ContentUris.parseId(uri);//得到行号
			  String where = "gamecount = "+rowid;	//组合sql语句
			  sld.update(
					  "hextower", 
					  values, 
					  where, 
					  selectionArgs
					  );
		}		
		return 0;
	}
}
