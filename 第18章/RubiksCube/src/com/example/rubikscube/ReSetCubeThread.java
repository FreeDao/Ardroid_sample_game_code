package com.example.rubikscube;

import static com.example.rubikscube.util.Constant.*;

public class ReSetCubeThread extends Thread{
	String array;
	String u4 = "u{4}";
	String u3 = "u{3}";
	String Uu = "Uu";
	String uU = "uU";
	String l3 = "l{3}";
	String r3 = "r{3}";
	String f3 = "f{3}";
	String d3 = "d{3}";
	CubesControl cc;
	
	public ReSetCubeThread(String array,CubesControl cc)
	{
		this.cc = cc;
		array = array.replaceAll(u4, "");
		array = array.replaceAll(u3, "U");
		array = array.replaceAll(Uu, "");
		array = array.replaceAll(uU, "");
		array = array.replaceAll(l3, "L");
		array = array.replaceAll(r3, "R");
		array = array.replaceAll(f3, "F");
		array = array.replaceAll(d3, "D");
		this.array = array;
		System.out.println(array);
		System.out.println("length="+array.length());
		
	}

	@Override
	public void run() {
		for(int i=0;i<array.length();i++)
		{
			if(!bwait)
			{
				MoveCubeData(""+array.charAt(i));
			}
			else
			{
				i--;
			}
			try {
				sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	void MoveCubeData(String sur)
	{	
		if(sur.equals("f"))
		{
			new RotateThread(13,cc).run();
		}
		if(sur.equals("F"))
		{
			new RotateThread(12,cc).run();
		}
		if(sur.equals("b"))
		{
			new RotateThread(16,cc).run();
		}
		if(sur.equals("B"))
		{
			new RotateThread(17,cc).run();
		}
		if(sur.equals("r"))
		{
			new RotateThread(10,cc).run();
		}
		if(sur.equals("R"))
		{
			new RotateThread(11,cc).run();
		}
		if(sur.equals("l"))
		{
			new RotateThread(7,cc).run();
		}
		if(sur.equals("L"))
		{
			new RotateThread(6,cc).run();
		}
		if(sur.equals("u"))
		{
			new RotateThread(0,cc).run();
		}
		if(sur.equals("U"))
		{
			new RotateThread(1,cc).run();
		}
		if(sur.equals("d"))
		{
			new RotateThread(5,cc).run();
		}
		if(sur.equals("D"))
		{
			new RotateThread(4,cc).run();
		}
		if(sur.equals("mr"))
		{
			new RotateThread(8,cc).run();
		}
		if(sur.equals("mf"))
		{
			new RotateThread(15,cc).run();
		}
		if(sur.equals("mu"))
		{
			new RotateThread(2,cc).run();
		}
	}
	
}
