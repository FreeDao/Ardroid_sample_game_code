package wyf.ytl;

//���ڼ�¼���ص����ꡢ�����ƴ��ص����
public class FingerTouch 
{
   //���ص�X��Y����
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
