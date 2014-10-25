package com.example.rubikscube.util;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.opengl.GLUtils;


public class Constant 
{
	//单位长度
    public static final float UNIT_SIZE=0.03f;
    
    public static int MS_PER_FRAME=1;//多少毫秒一帧动画
    
    public static int ORDER = 3; //魔方阶数
    public static int STYLE = 0; //魔方阶数
    
    public static boolean bbgm = true;
    public static boolean bse = false;
    public static boolean bwait = false;
    
    public static int[][][] CHANGE=
	{
    	{
			{6,7,0,1},
			{8,9,2,3},
			{10,11,4,5}
    	},
    	{
			{12,13,0,1},
			{14,15,2,3},
			{16,17,4,5}
    	},
		{
			{11,10,0,1},
			{9,8,2,3},
			{7,6,4,5}
		},
		{
			{17,16,0,1},
			{15,14,2,3},
			{13,12,4,5}
		},
		{
			{6,7,16,17},
			{8,9,14,15},
			{10,11,12,13}
		},
		{
			{6,7,13,12},
			{8,9,15,14},
			{10,11,17,16}
		}
	};
    
    public static Vector3f po[] = new Vector3f[12];
    
    static
    {
    	po[0] = new Vector3f(-1,1,0);
    	po[1] = new Vector3f(-1,-1,0);
    	po[2] = new Vector3f(1,1,0);
    	po[3] = new Vector3f(1,-1,0);
    	
    	po[4] = new Vector3f(0,1,1);
    	po[5] = new Vector3f(0,-1,1);
    	po[6] = new Vector3f(0,1,-1);
    	po[7] = new Vector3f(0,-1,-1);
    	
    	po[8] = new Vector3f(-1,0,-1);
    	po[9] = new Vector3f(-1,0,1);
    	po[10] = new Vector3f(1,0,-1);
    	po[11] = new Vector3f(1,0,1);
    	
    }
    
    public static Vector3f cen[] = new Vector3f[6];
    
    static
    {
    	cen[0] = new Vector3f(0,0,1);
    	cen[1] = new Vector3f(1,0,0);
    	cen[2] = new Vector3f(0,0,-1);
    	cen[3] = new Vector3f(-1,0,0);
    	cen[4] = new Vector3f(0,1,0);
    	cen[5] = new Vector3f(0,-1,0);
    	
    }
    
    public static Vector3f scen = new Vector3f(0,0,5);
    
    static public int initTexture(GL10 gl,InputStream is) {
		// 生成纹理ID
		int[] textures = new int[1];
		gl.glGenTextures(1, // 产生的纹理id的数量
				textures, // 纹理id的数组
				0 // 偏移量
		);
		int itextureId = textures[0];
		gl.glBindTexture(GL10.GL_TEXTURE_2D, itextureId);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
				GL10.GL_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER,
				GL10.GL_LINEAR);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,
				GL10.GL_CLAMP_TO_EDGE);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,
				GL10.GL_CLAMP_TO_EDGE);

		Bitmap bitmapTmp;
		try {
			bitmapTmp = BitmapFactory.decodeStream(is);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, // 纹理类型，在OpenGL
												// ES中必须为GL10.GL_TEXTURE_2D
				0, // 纹理的层次，0表示基本图像层，可以理解为直接贴图
				bitmapTmp, // 纹理图像
				0 // 纹理边框尺寸
		);
		bitmapTmp.recycle(); // 纹理加载成功后释放图片
		return itextureId;

	}

}
