package com.baina.tower.utils;
import static com.baina.tower.constant.Map.MAP_DATA;
import static com.baina.tower.constant.Map.a;
import static com.baina.tower.constant.Map.b;
import static com.baina.tower.constant.Map.h;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;

import com.baina.tower.constant.Map;
import com.baina.tower.mapCapability.Blood_Down;
import com.baina.tower.mapCapability.Blood_Up;
import com.baina.tower.mapCapability.CapabilityList;
import com.baina.tower.mapCapability.Go_Direction;
import com.baina.tower.mapCapability.Speed_Down;
import com.baina.tower.mapCapability.Speed_Up;
import com.baina.tower.view.GameSurfaceView;

//表示六边形的类
public class LBX
{
	 final static float yGlobalOffset=60;//地图全局总Y偏移量
	 final static float xGlobalOffset=25;
	 final static float xStartA=0;
	 final static float xStartB=0+h;
	 private static float [][]tempR;
	 private static float [][]tempC;
	
	 GameSurfaceView mv;
	 Path mPatha = new Path();//多边形路径-三角形1
	 Bitmap black;
	 Bitmap start_bit;
	 Bitmap blood_up;
	 Bitmap blood_down;
	 Bitmap speed_up;
	 Bitmap speed_down;
	 Bitmap turn_num1;
	 Bitmap turn_num2;
	 Bitmap turn_num3;
	 Bitmap turn_num4;
	 Bitmap turn_num5;
	 Bitmap turn_num6;
	 Bitmap end_bit;
	public int mapNum;
	int start[];
	int end[];
	
	CapabilityList capability_list;
	
	/*//爆炸动画帧索引
	public int anmiIndex=0;
	public Bitmap[] baoZhaShuZu;*/
	Thread tt;//绘制出发点的动画的线程
	public float[] temp3;
	public float[] temp2;
	public LBX(Bitmap black,GameSurfaceView mv)
	{

		Init(black,mv);
		PositionInit();
		InitLBX();
	}
	
	public void InitLBX(){
		mPatha.moveTo(b, h);
	    mPatha.lineTo(a, 0);
	    mPatha.lineTo(b, -h);
	    mPatha.lineTo(-b, -h);
	    mPatha.lineTo(-a, 0);
	    mPatha.lineTo(-b, h);
	    mPatha.lineTo(b, h);
	}
	
	public void Init(Bitmap black,GameSurfaceView mv){
		this.mv = mv;
		mapNum = mv.mapNum;
		capability_list = mv.capability_list;
		start = mv.game.source;
		end =  mv.game.target;
		start_bit = mv.getLBXBit()[1];
		end_bit = mv.getLBXBit()[2];
		blood_up = mv.getLBXBit()[3];
		blood_down = mv.getLBXBit()[4];
		speed_up = mv.getLBXBit()[5];
		speed_down = mv.getLBXBit()[6];
		turn_num1 = mv.getLBXBit()[7];
		turn_num2 = mv.getLBXBit()[8];
		turn_num3 = mv.getLBXBit()[9];
		turn_num4 = mv.getLBXBit()[10];
		turn_num5 = mv.getLBXBit()[11];
		turn_num6 = mv.getLBXBit()[12];
		this.black = black;
	   // baoZhaShuZu = new Bitmap[]{mv.baoZha1,mv.baoZha2,mv.baoZha3,mv.baoZha4,mv.baoZha5,mv.baoZha6};
	}
	
	public void drawSelf(Canvas canvas,Paint paint,float xOffset,float yOffset,int[] color)
	{
		canvas.save();
		canvas.translate(xOffset, yOffset);
		//绘制多边形
		paint.setARGB(color[0], color[1], color[2], color[3]);//设置画笔颜色	
		canvas.drawPath(mPatha, paint);	
		paint.setARGB(120, 120, 120, 120);//设置画笔颜色	
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(2);
		canvas.drawPath(mPatha, paint);	
		canvas.restore();
	}
	
	public void drawMap(Canvas canvas,Paint paint)
	{
		int[] colorBlack=new int[]{255,0,0,0};

		
		for(int row=0;row<MAP_DATA[0].length;row++)
		{
			
			for(int col=0;col<MAP_DATA[0][0].length;col++)
			{
				float[]temp = getPosition(row, col);
				int[] color=colorBlack;
				drawSelf(canvas,paint, temp[0], temp[1], color);
				
				if(MAP_DATA[mapNum][row][col]==1)
					canvas.drawBitmap(black, temp[0]-black.getWidth()/2, temp[1]-black.getHeight()/2, paint);
			
			}
		}
		temp2 = getPosition(start[1],start[0]);
		temp3 = getPosition(end[1],end[0]);		
		canvas.drawBitmap(start_bit, temp2[0]-start_bit.getWidth()/2, temp2[1]-start_bit.getHeight()/2, paint);
		if(!mv.isLiang)
			canvas.drawBitmap(end_bit, temp3[0]-end_bit.getWidth()/2, temp3[1]-end_bit.getHeight()/2, paint);
		else
		{
			canvas.drawBitmap(UpdateBitmap.liangDu(end_bit,5f), temp3[0]-end_bit.getWidth()/2, temp3[1]-end_bit.getHeight()/2, paint);
			mv.isLiang = false;   
		}
			  
		
//		//绘制出怪的动画
//		if(mv.drawStartDemo)
//		{
//			canvas.drawBitmap(baoZhaShuZu[anmiIndex], temp2[0]-baoZhaShuZu[anmiIndex].getWidth()/2, 
//					temp2[1]-baoZhaShuZu[anmiIndex].getHeight()/2, paint);
//			
//		}

		
	}
	
	//根据行列给定六边形中心点坐标
	public static float[] getPosition(int row,int col)
	{		
		return new float[]{tempC[row][col],tempR[row][col]};
	}
	
	
	public void PositionInit(){
		tempR = new float[Map.MAP_DATA[0].length][Map.MAP_DATA[0][0].length];
		tempC = new float[Map.MAP_DATA[0].length][Map.MAP_DATA[0][0].length];
		
		for(int i=0; i<Map.MAP_DATA[0].length; i++){
			for(int j=0; j<Map.MAP_DATA[0][0].length; j++){
				
				float yStart=(i%2==0)?xStartA:xStartB;
				float yOffset=yStart+j*2*h+yGlobalOffset;
				float xOffset =i *(a+b)+xGlobalOffset;
				tempR[i][j]=yOffset;
				tempC[i][j]=xOffset;
				
				switch(MAP_DATA[mapNum][i][j])
				{
				case 1:
			
					break;
				case 2: 
					capability_list.add(new Speed_Down(speed_down,i,j));
					break;
				case 3: 
					capability_list.add(new Speed_Up(speed_up,i,j));
					break;
				case 4: 
					capability_list.add(new Blood_Down(blood_down,i,j));
					break;
				case 5: 
					capability_list.add(new Blood_Up(blood_up,i,j));
					break;
				case 6: 
					capability_list.add(new Go_Direction(turn_num1,i,j,6));
					break;
				case 7: 
					capability_list.add(new Go_Direction(turn_num2,i,j,7));
					break;
				case 8: 
					capability_list.add(new Go_Direction(turn_num3,i,j,8));
					break;
				case 9: 
					capability_list.add(new Go_Direction(turn_num4,i,j,9));
					break;
				case 10: 
					capability_list.add(new Go_Direction(turn_num5,i,j,10));
					break;
				case 11: 
					capability_list.add(new Go_Direction(turn_num6,i,j,11));
					break;
				}
			}
				
		}
		
	}
	
	public static int[] getRowcol(float x, float y){
		int row =Math.round( (x-xGlobalOffset)/(a+b));
		float yStart=(row%2==0)?xStartA:xStartB;
		int col = Math.round((y-yStart-yGlobalOffset)/(2*h));
		if(row>11 || col >14 || row<0|| col <0){
			//选中炮塔之后，如果触控的区域超过地图的范围，则炮塔在左上角进行绘制
			return new int[]{0,0};
		}
		return new int[]{row,col};
	}
	
	
	
	
}
