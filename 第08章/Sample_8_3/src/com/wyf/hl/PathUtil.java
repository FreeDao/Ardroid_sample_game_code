package com.wyf.hl;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathDashPathEffect;

public class PathUtil 
{
	//创建用于绘制虚线的单元路径的方法
	static  Path makePathDash() 
	{
        Path p = new Path();
        p.moveTo(4, 0);
        p.lineTo(0, -4);
        p.lineTo(8, -4);
        p.lineTo(12, 0);
        p.lineTo(8, 4);
        p.lineTo(0, 4);
        return p;
    }
	
	static PathEffect  getDirectionDashEffect()
	{
		PathEffect result=null;
		
		result=new PathDashPathEffect
		(
				makePathDash(), //形状
				13, //间距
				0,//首绘制偏移量
				PathDashPathEffect.Style.ROTATE
		);
		
		return result;
	}
}
