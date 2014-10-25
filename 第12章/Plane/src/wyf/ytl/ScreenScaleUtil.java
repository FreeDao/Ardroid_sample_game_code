package wyf.ytl;

//计算缩放情况的工具类
public class ScreenScaleUtil
{
	static final float sHpWidth=480;
	static final float sHpHeight=320;
	static final float whHpRatio=sHpWidth/sHpHeight;
	
	
	static final float sSpWidth=320;
	static final float sSpHeight=480;
	static final float whSpRatio=sSpWidth/sSpHeight;
	
	
	public static ScreenScaleResult calScale
	(
		float targetWidth,	
		float targetHeight	
	)
	{
		ScreenScaleResult result=null;
		ScreenOrien so=null;
		
		if(targetWidth>targetHeight)
		{
			so=ScreenOrien.HP;
		}
		else
		{
			so=ScreenOrien.SP;
		}
		


		if(so==ScreenOrien.HP)
		{

			float targetRatio=targetWidth/targetHeight;
			
			if(targetRatio>whHpRatio)
			{
		    
			    float ratio=targetHeight/sHpHeight;
			    float realTargetWidth=sHpWidth*ratio;
			    float lcuX=(targetWidth-realTargetWidth)/2.0f;
			    float lcuY=0;
			    result=new ScreenScaleResult((int)lcuX,(int)lcuY,ratio,so);	
			}
			else
			{
	
				float ratio=targetWidth/sHpWidth;
				float realTargetHeight=sHpHeight*ratio;
				float lcuX=0;
				float lcuY=(targetHeight-realTargetHeight)/2.0f;
				result=new ScreenScaleResult((int)lcuX,(int)lcuY,ratio,so);	
			}
		}
		

		if(so==ScreenOrien.SP)
		{

			float targetRatio=targetWidth/targetHeight;
			
			if(targetRatio>whSpRatio)
			{
	    
			    float ratio=targetHeight/sSpHeight;
			    float realTargetWidth=sSpWidth*ratio;
			    float lcuX=(targetWidth-realTargetWidth)/2.0f;
			    float lcuY=0;
			    result=new ScreenScaleResult((int)lcuX,(int)lcuY,ratio,so);	
			}
			else
			{

				float ratio=targetWidth/sSpWidth;
				float realTargetHeight=sSpHeight*ratio;
			    float lcuX=0;
				float lcuY=(targetHeight-realTargetHeight)/2.0f;
				result=new ScreenScaleResult((int)lcuX,(int)lcuY,ratio,so);	
			}
			
		}
		
		return result;
	}
}