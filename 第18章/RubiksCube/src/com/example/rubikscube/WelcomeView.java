package com.example.rubikscube;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.opengl.GLSurfaceView.Renderer;

public class WelcomeView extends GLSurfaceView{
	private SceneRenderer sr;
	
	public int []flaTex = new int[33];
	public int flaNow = 0;
	CubeActivity context;

	public WelcomeView(CubeActivity context) {
		super(context);
		this.context = context;
		sr = new SceneRenderer();
		setRenderer(sr);
		setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}
	
	private class SceneRenderer implements Renderer {
		
		FlaDraw fd;

		@Override
		public void onDrawFrame(GL10 gl) {
			// �����ɫ��������Ȼ���
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
			// ���õ�ǰ����Ϊģʽ����
			gl.glMatrixMode(GL10.GL_MODELVIEW);
			// ���õ�ǰ����Ϊ��λ����
			gl.glLoadIdentity();
			
			gl.glTranslatef(0, 0f, -9.3f);
			
			fd.drawSelf(gl,flaNow);
		}

		@Override
		public void onSurfaceChanged(GL10 gl, int width, int height) {
			gl.glViewport(0, 0, width, height);
			gl.glMatrixMode(GL10.GL_PROJECTION);
			gl.glLoadIdentity();

			float radio = (float) width/height;
			gl.glFrustumf(-radio, radio, -1, 1, 8f, 50f);

		}

		@Override
		public void onSurfaceCreated(GL10 gl, EGLConfig arg1) {
			gl.glDisable(GL10.GL_DITHER);
			gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
			gl.glClearColor(0f, 0f, 0f, 0);
			gl.glEnable(GL10.GL_DEPTH_TEST);
			gl.glShadeModel(GL10.GL_SMOOTH);
			gl.glEnable(GL10.GL_CULL_FACE);
			
			for(int i=0;i<flaTex.length;i++)
			{
				flaTex[i] = initTexture(gl,R.drawable.m00001+i);
			}
			flaNow = flaTex[flaTex.length-1];
			fd = new FlaDraw();
			runfla(gl);
		}
	}
	
	public int initTexture(GL10 gl,int drawableId) {
		
		// ��������ID
		int[] textures = new int[1];
		gl.glGenTextures(1, // ����������id������
				textures, // ����id������
				0 // ƫ����
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

		InputStream is = this.getResources().openRawResource(drawableId);
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
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, // �������ͣ���OpenGL
												// ES�б���ΪGL10.GL_TEXTURE_2D
				0, // ����Ĳ�Σ�0��ʾ����ͼ��㣬�������Ϊֱ����ͼ
				bitmapTmp, // ����ͼ��
				0 // ����߿�ߴ�
		);
		bitmapTmp.recycle(); // ������سɹ����ͷ�ͼƬ
		return itextureId;

	}
	
	
	public void runfla(GL10 gl)
	{
		
		new Thread()
		{
			@Override
			public void run()
			{
				for(int i=0;i<flaTex.length;i++)
				{
					flaNow = flaTex[flaTex.length-i-1];
					try {
						sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				try {
					sleep(1500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				context.handler.sendEmptyMessage(0);
			}
		}.start();
	}

}
