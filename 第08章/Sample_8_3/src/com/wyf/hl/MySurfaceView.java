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
implements SurfaceHolder.Callback  //ʵ���������ڻص��ӿ�
{
	SurfaceViewExampleActivity activity;
	Paint paint;//����
	LBX lbx=new LBX();	
	
	int sfId=0;//�㷨��� 0-��������㷨  1-��������㷨  2-������� A*�㷨  3-Dijkstra�㷨   4-Dijkstra A*�㷨
	int targetId=2;//Ŀ����
	Game game=new Game(this);

	public MySurfaceView(SurfaceViewExampleActivity activity) 
	{
		super(activity);
		this.activity = activity;
		this.getHolder().addCallback(this);//�����������ڻص��ӿڵ�ʵ����
		paint = new Paint();//��������
		paint.setAntiAlias(true);//�򿪿����		
	} 

	public void onDraw(Canvas canvas)
	{		
		//���Ƶ�ͼ
		lbx.drawMap(canvas, paint);
		
		//����Ѱ�ҹ���
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

		
		//���ƽ��·��
		if(
			sfId==0||
			sfId==1||
			sfId==2
		)
		{//"�������","�������","������� A*" ����
			if(game.pathFlag)
			{
				paint.setPathEffect(PathUtil.getDirectionDashEffect());
				HashMap<String,int[][]> hm=game.hm;		
				int[] temp=game.target;
				int count=0;//·�����ȼ�����			
				
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
					//�ж��з񵽳�����
					if(tempA[1][0]==game.source[0]&&tempA[1][1]==game.source[1])
					{
						break;
					}
					
					temp=tempA[1];			
				}
				
				if(!activity.msg.contains("\n"))
				{
					activity.msg=activity.msg+"\n·�����ȣ�"+count;	
					activity.hd.sendEmptyMessage(0);	
				}
			}			
		}
		else if(
		  sfId==3||
		  sfId==4
		)
		{
			//"Dijkstra"����
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
					activity.msg=activity.msg+"\n·�����ȣ�"+alPath.size();
					activity.hd.sendEmptyMessage(0);
				}
		    }
		}
		
		paint.setPathEffect(null);   
		//�������
		paint.setARGB(255, 255, 0, 0);
		float[] position=lbx.getPosition(source[1], source[0]);
		paint.setStyle(Style.FILL);
		paint.setTextSize(40);
	    canvas.drawText("S",position[0]-10, position[1]+12, paint);
	    //�����յ�
		paint.setARGB(255, 0, 255, 0);
		position=lbx.getPosition(target[targetId][1], target[targetId][0]);
		paint.setStyle(Style.FILL);
		paint.setTextSize(40);
	    canvas.drawText("T",position[0]-10, position[1]+14, paint);
	    
	    
	}

	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		  
	}

	public void surfaceCreated(SurfaceHolder holder) {//����ʱ������
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
		Canvas canvas = holder.lockCanvas();//��ȡ����
		try{
			synchronized(holder){
				onDraw(canvas);//����
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

	public void surfaceDestroyed(SurfaceHolder arg0) {//����ʱ������

	}
}