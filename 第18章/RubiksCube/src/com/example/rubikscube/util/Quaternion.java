package com.example.rubikscube.util;
/*
 * 代表四元数的类
 */
public class Quaternion {

	float w, x, y, z;
	public static Quaternion getIdentityQuaternion()
	{
		return  new Quaternion(1f, 0f, 0f, 0f);
	}

	public Quaternion() {	}
	public Quaternion(float w, float x, float y, float z) 
	{
		this.w = w;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void setToRotateAboutAxis(Vector3f axis, float theta){
		axis.normalize();
		float thetaOver2 = theta/2f;
		float sinThetaOver2 = (float) Math.sin(thetaOver2);
		w = (float) Math.cos(thetaOver2);
		x = axis.x * sinThetaOver2;
		y = axis.y * sinThetaOver2;
		z = axis.z * sinThetaOver2;
	}
	public float getRotationAngle(){
		float thetaOver2 = (float) Math.acos(w);
		return thetaOver2 * 2f;
	}
	public Vector3f getRotationAxis(){
		float sinThetaOver2Sq = 1.0f - w*w;
		if(sinThetaOver2Sq <= 0.0f){
			return new Vector3f(1.0f, 0f, 0f);
		}
		float oneOversinThetaOver2 = (float) (1.0f/Math.sqrt(sinThetaOver2Sq));
		return new Vector3f(
				x * oneOversinThetaOver2,
				y * oneOversinThetaOver2,
				z * oneOversinThetaOver2
				);
	}
	public Quaternion cross(Quaternion a){
		Quaternion result = new Quaternion();
		
		result.w = w*a.w - x*a.x - y*a.y - z*a.z;
		result.x = w*a.x + x*a.w + z*a.y - y*a.z;
		result.y = w*a.y + y*a.w + x*a.z - z*a.x;
		result.z = w*a.z + z*a.w + y*a.x - x*a.y;
		
		return result;
	}
}
