package com.wyf.hl;
import java.util.ArrayList;
import java.util.HashMap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import static com.wyf.hl.Map.*;

public class MySurfaceView extends SurfaceView 
implements SurfaceHolder.Callback  //实现生命周期回调接口
{
	SurfaceViewExampleActivity activity;
	Paint paint;//画笔
	LBX lbx=new LBX();	
	
	int sfId=0;//算法编号 0-深度优先算法  1-广度优先算法  2-广度优先 A*算法  3-Dijkstra算法   4-Dijkstra A*算法
	int targetId=2;//目标编号
	Game game=new Game(this);

	public MySurfaceView(SurfaceViewExampleActivity activity) 
	{
		super(activity);
		this.activity = activity;
		this.getHolder().addCallback(this);//设置生命周期回调接口的实现者
		paint = new Paint();//创建画笔
		paint.setAntiAlias(true);//打开抗锯齿		
	} 

	public void onDraw(Canvas canvas)
	{		
		//绘制地图
		lbx.drawMap(canvas, paint);
		
		//绘制寻找过程
		ArrayList<int[][]> searchProcess=game.searchProcess;
		for(int k=0;k<searchProcess.size();k++)
		{
			int[][] edge=searchProcess.get(k);  
			paint.setARGB(255,0, 0, 0);
			float[] p1=lbx.getPosition(edge[0][1], edge[0][0]);
			float[] p2=lbx.getPosition(edge[1][1], edge[1][0]);
			
			canvas.drawLine
			(
				p1[0],
				p1[1], 
				p2[0], 
				p2[1], 
				paint
			);
		}			

		
		//绘制结果路径
		if(
			sfId==0||
			sfId==1||
			sfId==2
		)
		{//"深度优先","广度优先","广度优先 A*" 绘制
			if(game.pathFlag)
			{
				paint.setPathEffect(PathUtil.getDirectionDashEffect());
				HashMap<String,int[][]> hm=game.hm;		
				int[] temp=game.target;
				int count=0;//路径长度计数器			
				
				while(true)
				{
					int[][] tempA=hm.get(temp[0]+":"+temp[1]);
					paint.setARGB(255,0, 0, 0);						    
				    paint.setStrokeWidth(9);				    
				    paint.setStrokeCap(Cap.ROUND);
				    paint.setStrokeJoin(Join.ROUND);
				    float[] p1=lbx.getPosition(tempA[0][1], tempA[0][0]);
					float[] p2=lbx.getPosition(tempA[1][1], tempA[1][0]);					
					canvas.drawLine
					(
						p2[0], 
						p2[1],
						p1[0],
						p1[1],						 
						paint
					);
					
					count++;
					//判断有否到出发点
					if(tempA[1][0]==game.source[0]&&tempA[1][1]==game.source[1])
					{
						break;
					}
					
					temp=tempA[1];			
				}
				
				if(!activity.msg.contains("\n"))
				{
					activity.msg=activity.msg+"\n路径长度："+count;	
					activity.hd.sendEmptyMessage(0);	
				}
			}			
		}
		else if(
		  sfId==3||
		  sfId==4
		)
		{
			//"Dijkstra"绘制
		    if(game.pathFlag)
		    {
		    	paint.setPathEffect(PathUtil.getDirectionDashEffect());
		    	HashMap<String,ArrayList<int[][]>> hmPath=game.hmPath;
				ArrayList<int[][]> alPath=hmPath.get(game.target[0]+":"+game.target[1]);
				for(int[][] tempA:alPath)
				{				
					paint.setARGB(255,0, 0, 0);						    
				    paint.setStrokeWidth(9);				    
				    paint.setStrokeCap(Cap.ROUND);
				    paint.setStrokeJoin(Join.ROUND);
				    float[] p1=lbx.getPosition(tempA[0][1], tempA[0][0]);
					float[] p2=lbx.getPosition(tempA[1][1], tempA[1][0]);					
					canvas.drawLine
					(
						p1[0],
						p1[1], 
						p2[0], 
						p2[1], 
						paint
					);	
				}
				if(!activity.msg.contains("\n"))
				{
					activity.msg=activity.msg+"\n路径长度："+alPath.size();
					activity.hd.sendEmptyMessage(0);
				}
		    }
		}
		
		paint.setPathEffect(null);   
		//绘制起点
		paint.setARGB(255, 255, 0, 0);
		float[] position=lbx.getPosition(source[1], source[0]);
		paint.setStyle(Style.FILL);
		paint.setTextSize(40);
	    canvas.drawText("S",position[0]-10, position[1]+12, paint);
	    //绘制终点
		paint.setARGB(255, 0, 255, 0);
		position=lbx.getPosition(target[targetId][1], target[targetId][0]);
		paint.setStyle(Style.FILL);
		paint.setTextSize(40);
	    canvas.drawText("T",position[0]-10, position[1]+14, paint);
	    
	    
	}

	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		  
	}

	public void surfaceCreated(SurfaceHolder holder) {//创建时被调用
		repaint();
	}
	
	public void doSearch()
	{
		game.algorithmId=sfId;
		game.runAlgorithm();
	}
	
	public void repaint()
	{
		SurfaceHolder holder=this.getHolder();
		Canvas canvas = holder.lockCanvas();//获取画布
		try{
			synchronized(holder){
				onDraw(canvas);//绘制
			}			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(canvas != null){
				holder.unlockCanvasAndPost(canvas);
			}
		}
	}

	public void surfaceDestroyed(SurfaceHolder arg0) {//销毁时被调用

	}
}