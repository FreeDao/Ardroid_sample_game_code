package com.wyf.hl;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Style;
import static com.wyf.hl.Map.*;

//表示六边形的类
public class LBX 
{
	final float yGlobalOffset=30;//地图全局总Y偏移量
	final float xGlobalOffset=30;//地图全局总Y偏移量
	final float xStartA=0;
	final float xStartB=0+h;
	
	Path mPatha = new Path();//多边形路径-三角形1
	
	public LBX()
	{		
		//初始化三角形路径
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
			float xStart=(row%2==0)?xStartA:xStartB;
			
			for(int col=0;col<MAP_DATA[row].length;col++)
			{
				float xOffset=xStart+col*2*h +xGlobalOffset;
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
		float xStart=(row%2==0)?xStartA:xStartB;
		float xOffset=xStart+col*2*h + xGlobalOffset;
		
		return new float[]{xOffset,yOffset};
	}
	
}
