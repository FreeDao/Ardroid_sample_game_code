package com.example.rubikscube.util;

/*
 * 代表3D空间中向量的类
 */
public class Vector3f {
	//向量的三个分量
	public float x;
	public float y;
	public float z;
	//构造器
	public Vector3f(float x, float y, float z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	//将向量规格化的方法
	public void normalize(){
		float mod = module();
		//保证模不为零时再规格化向量
		if(mod != 0){
			x = x/mod;
			y = y/mod;
			z = z/mod;
		}
	}
	//求向量的模的方法
	public float module(){
		return (float) Math.sqrt(x*x + y*y + z*z);
	}
	//减法
	public Vector3f minus(Vector3f v){
		return new Vector3f(this.x-v.x,this.y-v.y,this.z-v.z);
	}
	
	public Vector3f fu(){
		return new Vector3f(-x,-y,-z);
	}
	
	public boolean tongxiang(Vector3f v)
	{
		Vector3f vt = this;
		vt.normalize();
		v.normalize();
		if(vt.x==v.x&&vt.y==v.y&&vt.z==v.z)
		{
			return true;
		}
		return false;
	}
	public Vector3f crossProduct(Vector3f v)
	{
		return new Vector3f(
				y*v.z-z*v.y,
				z*v.x-x*v.z,
				x*v.y-y*v.x
				);
	}
	
}
