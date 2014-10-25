package com.example.rubikscube;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import com.example.rubikscube.util.Constant;

public class CubeSurfDraw {
	private FloatBuffer mVertexBuffer; //顶点坐标数据缓冲
	private FloatBuffer mNormalBuffer;
	private FloatBuffer mTextureBuffer;
	
	int vCount = 0;
	
	float angleZ=0;//实时旋转扰动角度   绕Z轴
	float angleX=0;//实时旋转扰动角度   绕X轴
	float angleY=0;//实时旋转扰动角度   绕Y轴
	
	int oldX ;
	int oldY ;
	int oldZ ;	
	
	int cx = 0;
	int cy = 0;
	int cz = 0;
	
	int sur = 0;
	int color = 0;
	
	public int textureId;
	
	public CubeSurfDraw(int x,int y,int z,int sur,int textureId,int ro)
	{
		final float US = Constant.UNIT_SIZE;
		this.textureId = textureId;
		
		oldX = x;
		oldY = y;
		oldZ = z;
		this.sur = sur;
		vCount = 6;
		float[] vertices = null;
		float[] twovertices = null;
		switch(sur)
		{
			case 0://front
				vertices = new float[]
				{
					(10*x-5)*US,(10*y+5)*US,(10*z+5)*US,	//0
					(10*x-5)*US,(10*y-5)*US,(10*z+5)*US,	//1
					(10*x+5)*US,(10*y+5)*US,(10*z+5)*US,	//2				
	
					(10*x+5)*US,(10*y+5)*US,(10*z+5)*US,	//2
					(10*x-5)*US,(10*y-5)*US,(10*z+5)*US,	//1				
					(10*x+5)*US,(10*y-5)*US,(10*z+5)*US,	//3
				};
				twovertices = new float[]
				{
					(5*x-5)*US,(5*y+5)*US,(5*z+5)*US,	//0
					(5*x-5)*US,(5*y-5)*US,(5*z+5)*US,	//1
					(5*x+5)*US,(5*y+5)*US,(5*z+5)*US,	//2				
		
					(5*x+5)*US,(5*y+5)*US,(5*z+5)*US,	//2
					(5*x-5)*US,(5*y-5)*US,(5*z+5)*US,	//1				
					(5*x+5)*US,(5*y-5)*US,(5*z+5)*US,	//3
				};
				break;
			case 1://right
				vertices = new float[]
				{
					(10*x+5)*US,(10*y+5)*US,(10*z+5)*US,	//2
					(10*x+5)*US,(10*y-5)*US,(10*z+5)*US,	//3
					(10*x+5)*US,(10*y+5)*US,(10*z-5)*US,	//4
					
					(10*x+5)*US,(10*y+5)*US,(10*z-5)*US,	//4				
					(10*x+5)*US,(10*y-5)*US,(10*z+5)*US,	//3
					(10*x+5)*US,(10*y-5)*US,(10*z-5)*US,	//5
				};
				twovertices = new float[]
				{
					(5*x+5)*US,(5*y+5)*US,(5*z+5)*US,	//2
					(5*x+5)*US,(5*y-5)*US,(5*z+5)*US,	//3
					(5*x+5)*US,(5*y+5)*US,(5*z-5)*US,	//4
					
					(5*x+5)*US,(5*y+5)*US,(5*z-5)*US,	//4				
					(5*x+5)*US,(5*y-5)*US,(5*z+5)*US,	//3
					(5*x+5)*US,(5*y-5)*US,(5*z-5)*US,	//5
				};
				break;
				
			case 2://back
				vertices = new float[]
				{
					(10*x+5)*US,(10*y+5)*US,(10*z-5)*US,	//4
					(10*x+5)*US,(10*y-5)*US,(10*z-5)*US,	//5
					(10*x-5)*US,(10*y+5)*US,(10*z-5)*US,	//6
					
					(10*x-5)*US,(10*y+5)*US,(10*z-5)*US,	//6				
					(10*x+5)*US,(10*y-5)*US,(10*z-5)*US,	//5
					(10*x-5)*US,(10*y-5)*US,(10*z-5)*US,	//7
				};
				twovertices = new float[]
				{
					(5*x+5)*US,(5*y+5)*US,(5*z-5)*US,	//4
					(5*x+5)*US,(5*y-5)*US,(5*z-5)*US,	//5
					(5*x-5)*US,(5*y+5)*US,(5*z-5)*US,	//6
					
					(5*x-5)*US,(5*y+5)*US,(5*z-5)*US,	//6				
					(5*x+5)*US,(5*y-5)*US,(5*z-5)*US,	//5
					(5*x-5)*US,(5*y-5)*US,(5*z-5)*US,	//7
				};
				break;
			case 3://left
				vertices = new float[]
				{
					(10*x-5)*US,(10*y+5)*US,(10*z-5)*US,	//6
					(10*x-5)*US,(10*y-5)*US,(10*z-5)*US,	//7
					(10*x-5)*US,(10*y+5)*US,(10*z+5)*US,	//0
					
					(10*x-5)*US,(10*y+5)*US,(10*z+5)*US,	//0
					(10*x-5)*US,(10*y-5)*US,(10*z-5)*US,	//7
					(10*x-5)*US,(10*y-5)*US,(10*z+5)*US,	//1
				};
				twovertices = new float[]
				{
					(5*x-5)*US,(5*y+5)*US,(5*z-5)*US,	//6
					(5*x-5)*US,(5*y-5)*US,(5*z-5)*US,	//7
					(5*x-5)*US,(5*y+5)*US,(5*z+5)*US,	//0
					
					(5*x-5)*US,(5*y+5)*US,(5*z+5)*US,	//0
					(5*x-5)*US,(5*y-5)*US,(5*z-5)*US,	//7
					(5*x-5)*US,(5*y-5)*US,(5*z+5)*US,	//1
				};
				break;
			case 4://up
				vertices = new float[]
				{
					(10*x-5)*US,(10*y+5)*US,(10*z-5)*US,	//6
					(10*x-5)*US,(10*y+5)*US,(10*z+5)*US,	//0
					(10*x+5)*US,(10*y+5)*US,(10*z-5)*US,	//4
					
					(10*x+5)*US,(10*y+5)*US,(10*z-5)*US,	//4
					(10*x-5)*US,(10*y+5)*US,(10*z+5)*US,	//0
					(10*x+5)*US,(10*y+5)*US,(10*z+5)*US,	//2		
				};
				twovertices = new float[]
				{
					(5*x-5)*US,(5*y+5)*US,(5*z-5)*US,	//6
					(5*x-5)*US,(5*y+5)*US,(5*z+5)*US,	//0
					(5*x+5)*US,(5*y+5)*US,(5*z-5)*US,	//4
					
					(5*x+5)*US,(5*y+5)*US,(5*z-5)*US,	//4
					(5*x-5)*US,(5*y+5)*US,(5*z+5)*US,	//0
					(5*x+5)*US,(5*y+5)*US,(5*z+5)*US,	//2
				};
				break;
			case 5://down
				vertices = new float[]
				{
					(10*x-5)*US,(10*y-5)*US,(10*z+5)*US,	//1
					(10*x-5)*US,(10*y-5)*US,(10*z-5)*US,	//7
					(10*x+5)*US,(10*y-5)*US,(10*z+5)*US,	//3
					
					(10*x+5)*US,(10*y-5)*US,(10*z+5)*US,	//3
					(10*x-5)*US,(10*y-5)*US,(10*z-5)*US,	//7
					(10*x+5)*US,(10*y-5)*US,(10*z-5)*US,	//5
				};
				twovertices = new float[]
				{
					(5*x-5)*US,(5*y-5)*US,(5*z+5)*US,	//1
					(5*x-5)*US,(5*y-5)*US,(5*z-5)*US,	//7
					(5*x+5)*US,(5*y-5)*US,(5*z+5)*US,	//3
					
					(5*x+5)*US,(5*y-5)*US,(5*z+5)*US,	//3
					(5*x-5)*US,(5*y-5)*US,(5*z-5)*US,	//7
					(5*x+5)*US,(5*y-5)*US,(5*z-5)*US,	//5
				};
				break;

		}
	
		
		
		if(ro == 3)
		{
			ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
			vbb.order(ByteOrder.nativeOrder());
			mVertexBuffer = vbb.asFloatBuffer();
			mVertexBuffer.put(vertices);
			mVertexBuffer.position(0);
		}
		else if(ro == 2)
		{
			ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
			vbb.order(ByteOrder.nativeOrder());
			mVertexBuffer = vbb.asFloatBuffer();
			mVertexBuffer.put(twovertices);
			mVertexBuffer.position(0);
		}
	}
	
	public void drawSelf(GL10 gl)
	{
		gl.glPushMatrix();
		
		gl.glRotatef(angleX, 1, 0, 0);//X轴旋转扰动
		gl.glRotatef(angleY, 0, 1, 0);//Y轴旋转扰动
		gl.glRotatef(angleZ, 0, 0, 1);//Z轴旋转扰动
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);
		
        //开启纹理
		gl.glEnable(GL10.GL_TEXTURE_2D); 

        //允许使用纹理数组
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        //为画笔指定纹理uv坐标数据
        gl.glTexCoordPointer
        (
        		2, 					//每个顶点两个纹理坐标数据 S、T
        		GL10.GL_FLOAT, 		//数据类型
        		0, 					//连续纹理坐标数据之间的间隔
        		mTextureBuffer		//纹理坐标数据
        );
        //为画笔绑定指定名称ID纹理		
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);   
 
		
	    gl.glDrawArrays
	    (
	    		GL10.GL_TRIANGLES, 		//以三角形方式填充
	    		0, 			 			//开始点编号
	    		vCount					//顶点的数量
	    );
	    
	    gl.glPopMatrix();
    
	}
	
	public void textureSet()
	{
		float UT = 0.125f;	
		
		float textureCoors[] = new float[]
		{	
			color*UT,0,
			color*UT,1,
			(color+1)*UT,0,
			
			(color+1)*UT,0,
			color*UT,1,
			(color+1)*UT,1,
		};
		
		ByteBuffer cbb = ByteBuffer.allocateDirect(textureCoors.length*4);
		cbb.order(ByteOrder.nativeOrder());
		mTextureBuffer = cbb.asFloatBuffer();
		mTextureBuffer.put(textureCoors);
		mTextureBuffer.position(0);
	}

}
