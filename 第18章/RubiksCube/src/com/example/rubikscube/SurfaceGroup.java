package com.example.rubikscube;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;


public class SurfaceGroup implements Comparable<SurfaceGroup>
{
	ArrayList<CubeSurfDraw> cg = new ArrayList<CubeSurfDraw>();
	int surId = 0;
	float s;
	public SurfaceGroup(int surId)
	{
		this.surId = surId;
	}
	public void drawSelf(GL10 gl)
	{
		for(CubeSurfDraw c:cg)
		{
			c.drawSelf(gl);
		}
	}
	public void twoorSelf(GL10 gl)
	{
		for(CubeSurfDraw c:cg)
		{
			if(c.oldX!=0&&c.oldY!=0&&c.oldZ!=0)
				c.drawSelf(gl);
		}
	}
	@Override
	public int compareTo(SurfaceGroup an) {
		if(an.s>s)
		{
			return 1;
		}else if(an.s<s)
		{
			return -1;
		}
		return 0;
	}
	
}
