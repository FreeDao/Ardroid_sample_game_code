package com.example.rubikscube;

public class Surface
{
	public int s[][] = new int[3][3];
	
	Surface(int c)
	{
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
			{
				s[i][j] = c;
			}
	}
	
	Surface copy()
	{
		Surface t = new Surface(0);
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
			{
				t.s[i][j] = s[i][j];
			}
		return t;
	}
	
	public boolean isRe3()
	{
		if(s[1][1]==s[0][0]&&s[1][1]==s[0][1]&&s[1][1]==s[0][2]&&s[1][1]==s[1][0]&&s[1][1]==s[1][2]&&
				s[1][1]==s[2][0]&&s[1][1]==s[2][1]&&s[1][1]==s[2][2])
			return true;
		return false;
	}
	public boolean isRe2()
	{
		if(s[0][0]==s[0][2]&&s[0][0]==s[2][0]&&s[0][0]==s[2][2])
			return true;
		return false;
	}
}