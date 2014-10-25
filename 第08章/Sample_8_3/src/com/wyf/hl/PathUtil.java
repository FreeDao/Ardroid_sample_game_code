package com.wyf.hl;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathDashPathEffect;

public class PathUtil 
{
	//�������ڻ������ߵĵ�Ԫ·���ķ���
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
				makePathDash(), //��״
				13, //���
				0,//�׻���ƫ����
				PathDashPathEffect.Style.ROTATE
		);
		
		return result;
	}
}
