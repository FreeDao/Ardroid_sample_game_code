package com.example.rubikscube.util;
import android.opengl.Matrix;

//存储系统矩阵状态的类
public class MatrixUtil 
{
    public static float[] mVMatrix = new float[16]; 
    public static float[] currMatrix;
    public static void setInitStack()
    {
    	currMatrix=new float[16];
    	Matrix.setRotateM(currMatrix, 0, 0, 1, 0, 0);
    } 
    public static void translate(float x,float y,float z)
    {
    	Matrix.translateM(currMatrix, 0, x, y, z);
    }
    public static void rotate(float angle,float x,float y,float z)
    {
    	Matrix.rotateM(currMatrix,0,angle,x,y,z);
    }    
    public static void setCamera
    (
    		float cx,	//摄像机位置x
    		float cy,   //摄像机位置y
    		float cz,   //摄像机位置z
    		float tx,   //摄像机目标点x
    		float ty,   //摄像机目标点y
    		float tz,   //摄像机目标点z
    		float upx,  //摄像机UP向量X分量
    		float upy,  //摄像机UP向量Y分量
    		float upz   //摄像机UP向量Z分量		
    )
    {    	
    	Matrix.setLookAtM
        (
        		mVMatrix, 
        		0, 
        		cx,
        		cy,
        		cz,
        		tx,
        		ty,
        		tz,
        		upx,
        		upy,
        		upz
        );
    }
	
	public static Vector3f fromGToO(Vector3f v,float[] m)
	{
		float[] invM = new float[16];
		Matrix.invertM(invM, 0, m, 0);
		float[] preP = new float[4];
		Matrix.multiplyMV(preP, 0, invM, 0, new float[]{v.x,v.y,v.z,1}, 0);
		return new Vector3f(preP[0],preP[1],preP[2]);
	}
}
