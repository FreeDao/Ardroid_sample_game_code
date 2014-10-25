package wyf.ytl;

enum ScreenOrien
{
	HP,  
	SP   
}


public class ScreenScaleResult
{
	int lucX;
	int lucY;
	public float ratio;
	ScreenOrien so;
	
	public ScreenScaleResult(int lucX,int lucY,float ratio,ScreenOrien so)
	{
		this.lucX=lucX;
		this.lucY=lucY;
		this.ratio=ratio;
		this.so=so;
	}
	
	public String toString()
	{
		return "lucX="+lucX+", lucY="+lucY+", ratio=, "+so;
	}
}