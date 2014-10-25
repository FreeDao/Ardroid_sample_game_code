package com.wyf.hl;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Style;
import static com.wyf.hl.Map.*;

//表示六边形的类
public class LBX 
{
	final float yGlobalOffset=8;//地图全局总Y偏移量
	final float xGlobalOffset=200;
	final float xStartA=0;		//六边形奇数行的偏移
	final float xStartB=0+h;	//六边形偶数行的偏移
	
	Path mPatha = new Path();//多边形路径-六边形形1
	
	public LBX()
	{		
		//初始化六边形路径
		mPatha.moveTo(0, -a);
	    mPatha.lineTo(h, -b);
	    mPatha.lineTo(h, b);
	    mPatha.lineTo(0, a);
	    mPatha.lineTo(-h, b);
	    mPatha.lineTo(-h, -b);
	    mPatha.lineTo(0, -a);
	}
	
	public void drawSelf(Canvas canvas,Paint paint,float xOffset,float yOffset,int[] color)
	{
		canvas.save();
		canvas.translate(xOffset, yOffset);
		//绘制多边形
		paint.setARGB(color[0], color[1], color[2], color[3]);//设置画笔颜色	
		paint.setStyle(Style.FILL);
		canvas.drawPath(mPatha, paint);	
		paint.setARGB(255, 128, 128, 128);//设置画笔颜色	
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(2);
		canvas.drawPath(mPatha, paint);	
		canvas.restore();
	}
	
	public void drawMap(Canvas canvas,Paint paint)
	{
		int[] colorBlack=new int[]{255,0,0,0};
		int[] colorWhite=new int[]{255,255,255,255};
		
		for(int row=0;row<MAP_DATA.length;row++)
		{
			float yOffset=row*(a+b)+a+yGlobalOffset;
			float xStart=((row%2==0)?xStartA:xStartB)+xGlobalOffset;
			
			for(int col=0;col<MAP_DATA[row].length;col++)
			{
				float xOffset=xStart+col*2*h;
				int[] color=null;
				if(MAP_DATA[row][col]==1)
				{
					color=colorBlack;
				}
				else
				{
					color=colorWhite;
				}
				drawSelf(canvas,paint,xOffset,yOffset,color);
			}
		}
	}
	
	//根据行列给定六边形中心点坐标
	public float[] getPosition(int row,int col)
	{		
		float yOffset=row*(a+b)+a+yGlobalOffset;
		float xStart=((row%2==0)?xStartA:xStartB)+xGlobalOffset;
		float xOffset=xStart+col*2*h;
		return new float[]{xOffset,yOffset};
	}
	
}
