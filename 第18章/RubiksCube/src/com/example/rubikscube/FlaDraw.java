package com.example.rubikscube;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import com.example.rubikscube.util.Constant;

public class FlaDraw {
	private FloatBuffer mVertexBuffer; //�����������ݻ���
	private FloatBuffer mTextureBuffer;
	
	int vCount = 0;
	public int textureId;
	
	public FlaDraw()
	{
		final float US = Constant.UNIT_SIZE;
		vCount = 6;
		float[] vertices = new float[]
		{
				-30*US,15*US,15*US,	//0
				-30*US,-15*US,15*US,	//1
				30*US,15*US,15*US,	//2				

				30*US,15*US,15*US,	//2	
				-30*US,-15*US,15*US,	//1			
				30*US,-15*US,15*US	//3
		};
		
		float textureCoors[] = new float[]
		{
				0,0,
				0,1,
				1,0,
				
				1,0,
				0,1,
				1,1
		};
		
		ByteBuffer cbb = ByteBuffer.allocateDirect(textureCoors.length*4);
		cbb.order(ByteOrder.nativeOrder());
		mTextureBuffer = cbb.asFloatBuffer();
		mTextureBuffer.put(textureCoors);
		mTextureBuffer.position(0);
		
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
		vbb.order(ByteOrder.nativeOrder());
		mVertexBuffer = vbb.asFloatBuffer();
		mVertexBuffer.put(vertices);
		mVertexBuffer.position(0);
	}
	
	public void drawSelf(GL10 gl, int i)
	{
		gl.glPushMatrix();
		
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
        gl.glBindTexture(GL10.GL_TEXTURE_2D, i); 
		
	    gl.glDrawArrays
	    (
	    		GL10.GL_TRIANGLES, 		//�������η�ʽ���
	    		0, 			 			//��ʼ����
	    		vCount					//���������
	    );
	    
		
		gl.glPopMatrix();
	}

}
