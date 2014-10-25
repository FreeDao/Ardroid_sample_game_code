package com.baina.tower.allactivity;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class DatabaseContentProvider  extends ContentProvider{
	private static final UriMatcher um;				//����ƥ���UriMatcher
	static{
		um=new UriMatcher(UriMatcher.NO_MATCH);		//����Uri�������޸�
		um.addURI("com.baina.hextower", "tower/#", 1);
	}
	SQLiteDatabase sld;								//�������ݿ����
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;									//ɾ������
	}
	@Override
	public String getType(Uri uri) {				//�õ�Uri����
		return null;			
	}
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		return null;								//��������
	}
	@Override
	public boolean onCreate() {						//�����ݿ�
		sld=SQLiteDatabase.openDatabase
    	(
    			"/data/data/com.baina.tower.allactivity/mydb", 	//���ݿ�����·��
    			null, 											//CursorFactory
    			SQLiteDatabase.OPEN_READWRITE|SQLiteDatabase.CREATE_IF_NECESSARY 
    															//��д�����������򴴽�
    	);
		return false;
	}
	@Override										//��ѯ����
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		return null;
	}
	@Override										//�޸�����
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		switch(um.match(uri)){
		  case 1:
			  long rowid = ContentUris.parseId(uri);//�õ��к�
			  String where = "gamecount = "+rowid;	//���sql���
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
