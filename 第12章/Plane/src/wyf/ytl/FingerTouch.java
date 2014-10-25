package wyf.ytl;

//用于记录触控点坐标、及绘制触控点的类
public class FingerTouch 
{
   //触控点X、Y坐标
   float x;
   float y;
   int type;
   int oldType;
   public FingerTouch(float x,float y,int type)
   {
	   this.x=x;
	   this.y=y;
	   this.type = type;
	   this.oldType = type;
   }
   
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.oldType = this.type;
		this.type = type;
	}
	public int  getOldType(){
		return this.oldType;
	}
	public void setLocation(float x2, float y2) {
		this.x=x2;
		this.y = y2;
		
	}
   

}
