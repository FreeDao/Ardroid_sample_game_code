package com.bn.sample4_11;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
public class MyViewDrawThread extends Thread
{
	boolean flag = true;
	boolean pauseFlag=false;
	MyView mv;
	SurfaceHolder surfaceHolder;
	public MyViewDrawThread(MyView myView)
	{
		this.mv = myView;
		this.surfaceHolder = myView.getHolder();
	}
	public void run()
	{
		Canvas c;
        while (this.flag) 
        {
            c = null;
            if(!pauseFlag)
            {
            	try 
            	{
	            	// �����������������ڴ�Ҫ��Ƚϸߵ�����£����������ҪΪnull
	                c = this.surfaceHolder.lockCanvas(null);
	                synchronized (this.surfaceHolder) 
	                {
	                	mv.draw(c);//����
	                }
	            } 
            	finally 
	            {
	                if (c != null) 
	                {
	                	//���ͷ���
	                    this.surfaceHolder.unlockCanvasAndPost(c);
	                }
	            }
            }
	            
            try{
            	Thread.sleep(50);
            }
            catch(Exception e)
            {
            	e.printStackTrace();
            }
        }
	}
}
