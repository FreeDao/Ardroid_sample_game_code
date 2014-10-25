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
	            	// 锁定整个画布，在内存要求比较高的情况下，建议参数不要为null
	                c = this.surfaceHolder.lockCanvas(null);
	                synchronized (this.surfaceHolder) 
	                {
	                	mv.draw(c);//绘制
	                }
	            } 
            	finally 
	            {
	                if (c != null) 
	                {
	                	//并释放锁
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
