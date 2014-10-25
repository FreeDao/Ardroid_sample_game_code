package com.example.rubikscube;

import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;

import android.text.format.Time;
import static com.example.rubikscube.util.Constant.*;

public class TimeThread extends Thread{
	Time t = new Time();
	long ms= 0;
	long tmpms = 0;
	int mst = 0;
	TextureRect s1 = new TextureRect(-1,UNIT_SIZE*2,UNIT_SIZE*4);
	int timeTextId[] = new int[10];
	int txTextId;
	boolean isStop = true;
	MySurfaceView msv;
	
	public void TimeThreadcreat(GL10 gl,MySurfaceView msv)
	{
		this.msv = msv;
		ms = tmpms;
		t.set(ms);
		for(int i=0;i<10;i++)
		{
			timeTextId[i] = initTexture(gl,msv.getResources().openRawResource(R.drawable.d0+i));
		}
		txTextId = initTexture(gl,msv.getResources().openRawResource(R.drawable.dx));
	}
	
	public void timestop()
	{
		isStop = true;
		ms= 0;
		tmpms = 0;
		mst = 0;
	}
	
	public void restart()
	{
		isStop = false;
		ms= 0;
		tmpms = 0;
		mst = 0;
		msv.canmove = true;
	}
	
	public void drawTime(GL10 gl)
	{
		if(t.minute<10)
		{
			s1.drawSelf(gl, timeTextId[0]);
			gl.glTranslatef(UNIT_SIZE*4, 0, 0);
			s1.drawSelf(gl, timeTextId[t.minute]);
		}
		else
		{
			s1.drawSelf(gl, timeTextId[t.minute/10]);
			gl.glTranslatef(UNIT_SIZE*4, 0, 0);
			s1.drawSelf(gl, timeTextId[t.minute%10]);
		}
		gl.glTranslatef(UNIT_SIZE*4, 0, 0);
		s1.drawSelf(gl,txTextId);
		gl.glTranslatef(UNIT_SIZE*4, 0, 0);
		if(t.second<10)
		{
			s1.drawSelf(gl, timeTextId[0]);
			gl.glTranslatef(UNIT_SIZE*4, 0, 0);
			s1.drawSelf(gl, timeTextId[t.second]);
		}
		else
		{
			s1.drawSelf(gl, timeTextId[t.second/10]);
			gl.glTranslatef(UNIT_SIZE*4, 0, 0);
			s1.drawSelf(gl, timeTextId[t.second%10]);
		}
		gl.glTranslatef(UNIT_SIZE*4, 0, 0);
		s1.drawSelf(gl,txTextId);
		gl.glTranslatef(UNIT_SIZE*4, 0, 0);
		s1.drawSelf(gl, timeTextId[mst%10]);
	}
	
	@Override
	public void run() {
		while(true)
		{
			t.set(ms);
			
			if(!isStop)
			{
				ms+=100;
				mst++;
				try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else
			{
				try {
					sleep(100);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	
}
