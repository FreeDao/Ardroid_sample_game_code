package com.example.rubikscube;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import com.example.rubikscube.util.Constant;

public class CubeSurfDraw {
	private FloatBuffer mVertexBuffer; //�����������ݻ���
	private FloatBuffer mNormalBuffer;
	private FloatBuffer mTextureBuffer;
	
	int vCount = 0;
	
	float angleZ=0;//ʵʱ��ת�Ŷ��Ƕ�   ��Z��
	float angleX=0;//ʵʱ��ת�Ŷ��Ƕ�   ��X��
	float angleY=0;//ʵʱ��ת�Ŷ��Ƕ�   ��Y��
	
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
		
		gl.glRotatef(angleX, 1, 0, 0);//X����ת�Ŷ�
		gl.glRotatef(angleY, 0, 1, 0);//Y����ת�Ŷ�
		gl.glRotatef(angleZ, 0, 0, 1);//Z����ת�Ŷ�
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);
		
        //��������
		gl.glEnable(GL10.GL_TEXTURE_2D); 

        //����ʹ����������
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        //Ϊ����ָ������uv��������
        gl.glTexCoordPointer
        (
        		2, 					//ÿ���������������������� S��T
        		GL10.GL_FLOAT, 		//��������
        		0, 					//����������������֮��ļ��
        		mTextureBuffer		//������������
        );
        //Ϊ���ʰ�ָ������ID����		
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);   
 
		
	    gl.glDrawArrays
	    (
	    		GL10.GL_TRIANGLES, 		//�������η�ʽ���
	    		0, 			 			//��ʼ����
	    		vCount					//���������
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
