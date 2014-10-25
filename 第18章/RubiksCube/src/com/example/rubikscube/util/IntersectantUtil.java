package com.example.rubikscube.util;
public class IntersectantUtil {	

	public static Vector3f[] calculateABPosition
	(
		float x,//触屏X坐标
		float y,//触屏Y坐标
		float w,// 屏幕宽度
		float h,// 屏幕高度
		float left,//视角left、right绝对值
		float top,//视角top、bottom绝对值
		float near,//视角near值
		float far//视角far值
	)
	{
		float x0=x-w/2;
		float y0=h/2-y;		
		float xNear=2*x0*left/w;
		float yNear=2*y0*top/h;
		float ratio=far/near;
		float xFar=ratio*xNear;
		float yFar=ratio*yNear;
        float ax=xNear;
        float ay=yNear;
        float az=-near;
        float bx=xFar;
        float by=yFar;
        float bz=-far; 
     
		Vector3f start = MatrixUtil.fromGToO
		(
			new Vector3f( ax, ay, az ),
			MatrixUtil.mVMatrix
		);
		Vector3f end = MatrixUtil.fromGToO
		(
			new Vector3f( bx, by, bz ),
			MatrixUtil.mVMatrix
		);
		return new Vector3f[] {
			start,
			end
		};
	}
}