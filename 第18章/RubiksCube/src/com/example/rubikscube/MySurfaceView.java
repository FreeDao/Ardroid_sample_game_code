package com.example.rubikscube;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.example.rubikscube.TextureRect;
import com.example.rubikscube.util.IntersectantUtil;
import com.example.rubikscube.util.MatrixUtil;
import com.example.rubikscube.util.Quaternion;
import com.example.rubikscube.util.Vector3f;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.SoundPool;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import static com.example.rubikscube.util.Constant.*;

public class MySurfaceView extends GLSurfaceView {
	SceneRenderer sr;

	private float mPreviousY;// 上次的触控位置Y坐标
	private float mPreviousX;// 上次的触控位置X坐标
	private final float TOUCH_SCALE_FACTOR = 3.141593f / 400;// 角度缩放比例
	float[] dcjirParams = {0,0,2,0};
	
	public float screenWidth;// 屏幕宽带
	public float screenHeight;// 屏幕高度
	public float ratio;// 屏幕宽高比
	float left;
	float right;
	float top;
	float bottom;
	float near;
	float far;
	
	Quaternion quaternionTotal = Quaternion.getIdentityQuaternion();
	float angleCurr = 0;// 当前的旋转角度
	float currAxisX = 0;// 当前旋转轴向量的X分量
	float currAxisZ = 0;// 当前旋转轴向量的Z分量
	float currAxisY = 0;// 当前旋转轴向量的Y分量
	
	public int textureId;// 纹理的名称ID
	public int flaNow = 0;
	public int textId = 0;

	Vector3f down;
	Vector3f up;
	int x;
	int y;
	int color = 7;

	boolean Mmove = false;
	boolean scal = false;
	boolean ishelp = false;
	boolean helpStart = false;
	boolean canmove = false;

	float mx;
	float my;
	float px;
	float py;
	float scals;
	float s = -9.3f;
	float olds = -9.3f;
	static CubeActivity cubeActivity;
	static SoundPool soundPool;//声音池
	static HashMap<Integer, Integer> soundPoolMap; //声音池中声音ID与自定义声音ID的Map
	
	
	TimeThread tt;

	public MySurfaceView(CubeActivity cubeActivity,boolean ishelp) {
		super(cubeActivity);
		MySurfaceView.cubeActivity = cubeActivity;
		sr = new SceneRenderer();
		setRenderer(sr);
		setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
		this.ishelp = ishelp;
		if(!ishelp)
		{
			tt = new TimeThread();
			tt.start();
		}
		initSound();
		Vector3f tmpAxis = new Vector3f(1, -1, 0);
		float tmpAngrad = (float)Math.PI/4;
		Quaternion tmpQuaternion = new Quaternion();
		tmpQuaternion.setToRotateAboutAxis(tmpAxis, tmpAngrad);
		quaternionTotal = quaternionTotal.cross(tmpQuaternion);
		Vector3f axis = quaternionTotal.getRotationAxis();
		float angrad = quaternionTotal.getRotationAngle();
		currAxisX = axis.x;
		currAxisY = axis.y;
		currAxisZ = axis.z;
		angleCurr = (float) Math.toDegrees(angrad);
	}
	
	static public void initSound()
    {
		//声音池
		soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
	    soundPoolMap = new HashMap<Integer, Integer>();   
	    
	    //吃东西音乐
	    soundPoolMap.put(1, soundPool.load(cubeActivity, R.raw.row, 1));	    
    }

    public static void playSound(int sound, int loop) 
    {
	    AudioManager mgr = (AudioManager)cubeActivity.getSystemService(Context.AUDIO_SERVICE);   
	    float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);   
	    float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);       
	    float volume = streamVolumeCurrent / streamVolumeMax;   
	    
	    soundPool.play(soundPoolMap.get(sound), volume, volume, 1, loop, 1f);
	}
    
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		


		int action = e.getAction() & MotionEvent.ACTION_MASK;
		// 获取主、辅点id（down时主辅点id皆正确，up时辅点id正确，主点id要查询Map中剩下的一个点的id）
		int id = (e.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >>> MotionEvent.ACTION_POINTER_ID_SHIFT;

		float y = e.getY(id);
		float x = e.getX(id);
		
		if(ishelp)
		{
			
			if(action == MotionEvent.ACTION_DOWN)
			{
				bwait = !bwait;
			}
			return true;
		}
		
		Vector3f start;// 起点
		Vector3f end;// 终点
		switch (action) {
		case MotionEvent.ACTION_MOVE:
			float dy = y - mPreviousY;// 计算触控笔Y位移
			float dx = x - mPreviousX;// 计算触控笔X位移
			mx = e.getX(0);
			my = e.getY(0);
			if (scal) {
				try {
					px = e.getX(1);
					py = e.getY(1);
				} catch (IllegalArgumentException ex) {
					ex.printStackTrace(); 
				}
				float t = (float) Math.sqrt((px - mx) * (px - mx) + (py - my)
						* (py - my))
						- scals;
				if(t>0&&(olds+t/40)<-8.8)
				{
					s = olds + t / 40;
				}
				else if(t<0&&(olds+t/40)>-14.6f)
				{
					s = olds + t / 40;
				}
			} else if (Mmove) {
				//activity.setContentView(R.layout.main);
				Vector3f tmpAxis = new Vector3f(dy, dx, 0);
				// 根据运动的距离计算出球需要滚动的角度
				float tmpAngrad = (float) (Math.sqrt(dy * dy + dx * dx) * TOUCH_SCALE_FACTOR);
				// 创建临时四元数
				Quaternion tmpQuaternion = new Quaternion();
				// 通过旋转轴和旋转角度设置四元数的值
				tmpQuaternion.setToRotateAboutAxis(tmpAxis, tmpAngrad);
				// 将临时四元数叉乘总的四元数
				quaternionTotal = quaternionTotal.cross(tmpQuaternion);
				// 获取当前总的旋转轴
				Vector3f axis = quaternionTotal.getRotationAxis();
				// 获取当前总的旋转角
				float angrad = quaternionTotal.getRotationAngle();
				// 将当前姿态对应的总的旋转角度与轴数据记录进成员变量
				currAxisX = axis.x;
				currAxisY = axis.y;
				currAxisZ = axis.z;
				angleCurr = (float) Math.toDegrees(angrad);
				order();
			}
			break;
		case MotionEvent.ACTION_POINTER_UP:
			scal = false;
			olds = s;
			break;
		case MotionEvent.ACTION_UP:
			if(!Mmove && !scal&&!canmove)
			{
				cubeActivity.handler.sendEmptyMessage(1);
			}
			if (!Mmove && !scal&&canmove) {
				Vector3f[] AB = IntersectantUtil.calculateABPosition(x, // 触控点X坐标
						y, // 触控点Y坐标
						screenWidth, // 屏幕宽度
						screenHeight, // 屏幕长度
						-left, // 视角left、top绝对值
						top, near, // 视角near、far值
						far);
				// 射线AB(世界坐标系)
				start = AB[0];// 起点
				end = AB[1];// 终点

				isOutXRange(start, end, color);

				sr.cc.row(color, this.x, this.y, down, up);
				if(sr.cc.cubeData.isReduction())
				{
					new RankingLoad(tt.ms);
					tt.timestop();
					cubeActivity.showDialog(0);
					
				}
				color = 7;
			}
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			scal = true;
			Mmove = false;
			px = x;
			py = y;
			scals = (float) Math.sqrt((px - mx) * (px - mx) + (py - my)
					* (py - my));
			break;
		case MotionEvent.ACTION_DOWN:
			mx = x;
			my = y;
			Vector3f[] AB = IntersectantUtil.calculateABPosition(x, // 触控点X坐标
					y, // 触控点Y坐标
					screenWidth, // 屏幕宽度
					screenHeight, // 屏幕长度
					-left, // 视角left、top绝对值
					top, near, // 视角near、far值
					far);

			// 射线AB(世界坐标系)
			start = AB[0];// 起点
			end = AB[1];// 终点

			if (isInXRange(start, end)) {
				Mmove = false;
			} else {
				Mmove = true;
			}
			
			break;

		}
		mPreviousY = y;// 记录触控笔位置
		mPreviousX = x;// 记录触控笔位置
		return true;
	}
	
	

	class SceneRenderer implements Renderer {

		CubesControl cc;
		CubeData cd;
		
		TextureRect t1;//纹理矩形1
		TextureRect t2;//纹理矩形1
		TextureRect t3;//纹理矩形1
		TextureRect bg;//纹理矩形1
		TextureRect zoom;//纹理矩形1
		TextureRect hpp;//帮助播放暂停按钮
		
		
		int baseTextureId;//最底层矩形的不透明纹理的纹理ID
		
		int wlWidth=512;//文字纹理宽度
		int wlHeight=1024;//文字纹理高度
		int StrtexId1=-1;
		int StrtexId2=-1;
		int StrtexId3=-1;
		int StrtexId4=-1;
		int StrtexId5=-1;
		int handTextureId = -1;
		int zoomAddTextureId = -1;
		int zoomSubTextureId = -1;
		int menuTextureId = -1;
		int hpauseTextureId = -1;
		int hplayTextureId = -1;
		
		int bgTextureId = -1;
		float fingerX = -100;
		float fingerY = -100;
		boolean isMenu = false;
		boolean isZoomAdd = false;
		boolean isZoomSub = false;
		
		ExecutorService threadPool=Executors.newFixedThreadPool(1);
		
		HelpThread ht;

		@Override
		public void onDrawFrame(GL10 gl) {
			
			// 清除颜色缓存于深度缓存
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
			// 设置当前矩阵为模式矩阵
			gl.glMatrixMode(GL10.GL_MODELVIEW);
			// 设置当前矩阵为单位矩阵
			gl.glLoadIdentity();
			
			gl.glPushMatrix();
            gl.glOrthof(-ratio, ratio, -1, 1, 1, 10);
            gl.glTranslatef(0f, 0f, -9f); 
            bg.drawSelf(gl,bgTextureId);
            bg.angleZ+=0.1;
            gl.glPopMatrix();
			
			
			gl.glPushMatrix();
			
            gl.glFrustumf(-ratio, ratio, -1, 1, 8f, 50f);
			gl.glTranslatef(0, 0f, s);

			gl.glPushMatrix();
			// 绕旋转轴旋转
			if (Math.abs(angleCurr) != 0
					&& (Math.abs(currAxisX) != 0 || Math.abs(currAxisY) != 0 || Math
							.abs(currAxisZ) != 0)) {
				gl.glRotatef(angleCurr, currAxisX, currAxisY, currAxisZ);
			}
			cc.drawSelf(gl);
			
			gl.glPopMatrix();
			gl.glPopMatrix();
			
			
			if(ishelp)
			{
				gl.glOrthof(-ratio, ratio, -1, 1, 1, 10);
	            gl.glPushMatrix();
	            
	            gl.glTranslatef(0f, 0.7f, -1.5f); 
	            if(textId == 0)
	            {
	            	t1.drawSelf(gl,StrtexId1);
	            }
	            else if(textId == 1)
	            {
	            	t1.drawSelf(gl,StrtexId2);
	            }
	            else if(textId == 2)
	            {
	            	t1.drawSelf(gl,StrtexId3);
	            }
	            else if(textId == 3)
	            {
	            	t1.drawSelf(gl,StrtexId4);
	            }
	            else if(textId == 4)
	            {
	            	t1.drawSelf(gl,StrtexId5);
	            }
	            gl.glPopMatrix();
	            

	            
	            if(isMenu)
	            {
		            gl.glPushMatrix();
		            gl.glTranslatef(0f, -0.2f, -1.5f);
	            	gl.glTranslatef(0.0f, -0.68f, 0.2f);
	            	t3.drawSelf(gl,menuTextureId);
	            	gl.glPopMatrix();
	            }
	            if(isZoomAdd)
	            {
		            gl.glPushMatrix();
		            gl.glTranslatef(0.2f, -0.15f, -1.5f);
	            	gl.glTranslatef(0.0f, -0.68f, 0.3f);
	            	zoom.drawSelf(gl, zoomAddTextureId);
	            	gl.glPopMatrix();
	            }
	            if(isZoomSub)
	            {
	            	gl.glPushMatrix();
	            	gl.glTranslatef(0.2f, -0.15f, -1.5f);
	            	gl.glTranslatef(0.0f, -0.68f, 0.3f);
	            	zoom.drawSelf(gl, zoomSubTextureId);
	            	gl.glPopMatrix();
	            }
	            
            	gl.glPushMatrix();
            	gl.glTranslatef(0f, -0.2f, -1.5f); 
            	gl.glTranslatef(fingerX, fingerY, 0.3f);
            	t2.drawSelf(gl,handTextureId);
            	gl.glPopMatrix();
            	            
	    		gl.glPushMatrix();
	    		gl.glTranslatef(-0.4f, -0.65f, -1.0f);      		
            	if(bwait)
            	{
            		hpp.drawSelf(gl,hplayTextureId);
            	}else
            	{
            		hpp.drawSelf(gl,hpauseTextureId);
            	}
            	gl.glPopMatrix();
	            

			}
			else
			{
				//画计时器
				gl.glPushMatrix();
				gl.glOrthof(-ratio, ratio, -1, 1, 1, 10);
				gl.glTranslatef(-0.35f, 0.8f, -1f);
				tt.drawTime(gl);			
				gl.glPopMatrix();
			}
			
		}

		@Override
		public void onSurfaceChanged(GL10 gl, int width, int height) {
			gl.glViewport(0, 0, width, height);
			gl.glMatrixMode(GL10.GL_PROJECTION);
			gl.glLoadIdentity();
			left = -ratio;
			right = ratio;
			bottom = -1;
			top = 1;
			near = 8f;
			far = 50f;
			MatrixUtil.setCamera(0, 0, 0, 0, 0, -1, 0, 1, 0);
			System.out.println("!!!!!!!!onSurfaceChanged");
		}

		@Override
		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
			System.out.println("!!!!!!!!onSurfaceCreated");
			gl.glDisable(GL10.GL_DITHER);
			gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
			gl.glClearColor(0f, 0f, 0f, 0);
			gl.glEnable(GL10.GL_DEPTH_TEST);
			gl.glShadeModel(GL10.GL_SMOOTH);
			gl.glFrustumf(-ratio, ratio, -1, 1, 8f, 50f);
	        gl.glEnable(GL10.GL_BLEND);
	        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
			gl.glEnable(GL10.GL_LIGHT0);
			gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION,dcjirParams,0);
			bgTextureId = initTexture(gl,R.drawable.bg0+(int)(Math.random()*4));
			if(STYLE == 0)
			{
				textureId = initTexture(gl,R.drawable.cube);
			}
			else
			{
				textureId = initTexture(gl,R.drawable.cube2);
			}
			bg=new TextureRect(baseTextureId,ratio*2f,ratio*2f); 
			if(!ishelp)
			{
				cc = new CubesControl(textureId,ORDER);
				
				if(cd!=null)
				{
					cc.cubeData = cd.copy();
					cc.reSetColor();
				}
				tt.TimeThreadcreat(gl, MySurfaceView.this);
				if(cc.cubeData.isReduction())
				{
					new UpsetThread(cc,tt).start();
				}
				bwait = false;
			}
			if(ishelp)
			{
				t1=new TextureRect(baseTextureId,ratio,ratio/2.3f); 
				t2=new TextureRect(baseTextureId,ratio/5,ratio/5*2f); 
				t3=new TextureRect(baseTextureId,ratio,ratio/4f); 
				hpp = new TextureRect(baseTextureId,ratio/5,ratio/5);
				zoom = new TextureRect(baseTextureId,ratio/5*2f,ratio/5*2f);
				cc = new CubesControl(textureId,3);
	        	//生成文字纹理
	        	StrtexId1=initTexture(gl,R.drawable.t1);
	        	StrtexId2=initTexture(gl,R.drawable.t2);
	        	StrtexId3=initTexture(gl,R.drawable.t3);
	        	StrtexId4=initTexture(gl,R.drawable.t4);
	        	StrtexId5=initTexture(gl,R.drawable.t5);
	        	handTextureId = initTexture(gl,R.drawable.finger);
	        	zoomAddTextureId = initTexture(gl,R.drawable.fangda);
	        	zoomSubTextureId = initTexture(gl,R.drawable.suoxiao);
	        	menuTextureId = initTexture(gl,R.drawable.menu);
	        	hplayTextureId = initTexture(gl,R.drawable.hplay);
	        	hpauseTextureId = initTexture(gl,R.drawable.hpause);

	        	if(helpStart&&!threadPool.isTerminated())
	        	{
		        	ht = new HelpThread(MySurfaceView.this);
		        	threadPool.execute(ht);
		        	//ht.start();
		        	helpStart = false;
	        	}
	        	bwait = false;
	        	new Thread()
	        	{

					@Override
					public void run() {
						
						while(!ht.helpstop)
						{
							if(bwait)
							{
									ht.interrupt();
							}
							else
							{
								if(ht.isInterrupted())
								ht.notify();
							}
							try {
								sleep(20);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
	        		
	        	}.start();
			}
			
			order();
		}
	}
	
	public int initTexture(GL10 gl,Bitmap bitmapTmp)//textureId
	{
		//生成纹理ID
		int[] textures = new int[1];
		gl.glGenTextures(1, textures, 0);    
		int currTextureId=textures[0];    
		gl.glBindTexture(GL10.GL_TEXTURE_2D, currTextureId);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,GL10.GL_NEAREST);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_MAG_FILTER,GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,GL10.GL_CLAMP_TO_EDGE);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,GL10.GL_CLAMP_TO_EDGE);        
        
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmapTmp, 0);
        bitmapTmp.recycle(); 
        
        return currTextureId;
	}

	public int initTexture(GL10 gl,int drawableId) {
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
		GLUtils.texImage2D(GL10.GL_TEXTURE_2D, // 纹理类型，在OpenGL
												// ES中必须为GL10.GL_TEXTURE_2D
				0, // 纹理的层次，0表示基本图像层，可以理解为直接贴图
				bitmapTmp, // 纹理图像
				0 // 纹理边框尺寸
		);
		bitmapTmp.recycle(); // 纹理加载成功后释放图片
		return itextureId;

	}
	

	public void order()
	{
		// 射线AB(世界坐标系)
		// 初始化一个变换矩阵
		MatrixUtil.setInitStack();
		// 平移坐标系到此照片要绘制的位置
		MatrixUtil.translate(0, 0f, s);
		// 旋转坐标系到此照片对应的角度
		MatrixUtil.rotate(angleCurr, currAxisX, currAxisY, currAxisZ);
		// 获取此照片的总变换矩阵
		float[] m = MatrixUtil.currMatrix;
		// 将射线start->end的起点与终点变换到物体坐标系
		Vector3f start = MatrixUtil.fromGToO(scen, m);
		if(sr.cc!=null)
		{
			for(int i=0;i<6;i++)
			{
				
					sr.cc.sall.get(i).s = start.minus(cen[sr.cc.sall.get(i).surId]).module();
						
			}
			Collections.sort(sr.cc.sall);
		}
	}

	public boolean isInXRange(Vector3f start, Vector3f end) {
		// 初始化一个变换矩阵
		MatrixUtil.setInitStack();
		// 平移坐标系到此照片要绘制的位置
		MatrixUtil.translate(0, 0f, s);
		// 旋转坐标系到此照片对应的角度
		MatrixUtil.rotate(angleCurr, currAxisX, currAxisY, currAxisZ);
		// 获取此照片的总变换矩阵
		float[] m = MatrixUtil.currMatrix;
		// 将射线start->end的起点与终点变换到物体坐标系
		start = MatrixUtil.fromGToO(start, m);
		end = MatrixUtil.fromGToO(end, m);
		// 计算出射线参数方程中的d，即end-start
		Vector3f dv = end.minus(start);

		ArrayList<Vector3f> doub = new ArrayList<Vector3f>();
		
		
		if(ORDER == 2)
		{
			float t = (UNIT_SIZE*10 - start.z) / dv.z;
			// 根据t计算出交点的X、Y坐标值
			float xd = start.x + t * dv.x;
			float yd = start.y + t * dv.y;
			float zd;

			if (xd > -UNIT_SIZE*10 && xd < UNIT_SIZE*10 && yd > -UNIT_SIZE*10 && yd < UNIT_SIZE*10) {
				Vector3f p = new Vector3f(xd, yd, UNIT_SIZE*10);
				doub.add(p);
			}

			// 根据交点的z坐标为0计算出参数t
			t = (-UNIT_SIZE*10 - start.z) / dv.z;
			// 根据t计算出交点的X、Y坐标值
			xd = start.x + t * dv.x;
			yd = start.y + t * dv.y;

			if (xd > -UNIT_SIZE*10 && xd < UNIT_SIZE*10 && yd > -UNIT_SIZE*10 && yd < UNIT_SIZE*10) {
				Vector3f p = new Vector3f(xd, yd, -UNIT_SIZE*10);
				doub.add(p);
			}

			t = (UNIT_SIZE*10 - start.x) / dv.x;
			yd = start.y + t * dv.y;
			zd = start.z + t * dv.z;

			if (zd > -UNIT_SIZE*10 && zd < UNIT_SIZE*10 && yd > -UNIT_SIZE*10 && yd < UNIT_SIZE*10) {
				Vector3f p = new Vector3f(UNIT_SIZE*10, yd, zd);
				doub.add(p);
			}

			t = (-UNIT_SIZE*10 - start.x) / dv.x;
			yd = start.y + t * dv.y;
			zd = start.z + t * dv.z;

			if (zd > -UNIT_SIZE*10 && zd < UNIT_SIZE*10 && yd > -UNIT_SIZE*10 && yd < UNIT_SIZE*10) {
				Vector3f p = new Vector3f(-UNIT_SIZE*10, yd, zd);
				doub.add(p);
			}

			t = (UNIT_SIZE*10 - start.y) / dv.y;
			xd = start.x + t * dv.x;
			zd = start.z + t * dv.z;

			if (zd > -UNIT_SIZE*10 && zd < UNIT_SIZE*10 && xd > -UNIT_SIZE*10 && xd < UNIT_SIZE*10) {
				Vector3f p = new Vector3f(xd, UNIT_SIZE*10, zd);
				doub.add(p);
			}

			t = (-UNIT_SIZE*10 - start.y) / dv.y;
			xd = start.x + t * dv.x;
			zd = start.z + t * dv.z;

			if (zd > -UNIT_SIZE*10 && zd < UNIT_SIZE*10 && xd > -UNIT_SIZE*10 && xd < UNIT_SIZE*10) {
				Vector3f p = new Vector3f(xd, -UNIT_SIZE*10, zd);
				doub.add(p);
			}

			Vector3f p = null;

			if (doub.size() == 2) {
				if (doub.get(0).minus(start).module() < doub.get(1).minus(start)
						.module()) {
					p = doub.get(0);
				} else {
					p = doub.get(1);
				}
				down = p;
				int x;
				int y;

				if (p.x == UNIT_SIZE*10) {

					x = (int) ((p.y + UNIT_SIZE*10) / 0.3f);
					y = (int) ((p.z + UNIT_SIZE*10) / 0.3f);
					if(x==1)
					{
						x=2;
					}
					if(y==1)
					{
						y=2;
					}
					this.x = 2 - x;
					this.y = 2 - y;
					color = 1;

				} else if (p.x == -UNIT_SIZE*10) {
					x = (int) ((p.y + UNIT_SIZE*10) / 0.3f);
					y = (int) ((p.z + UNIT_SIZE*10) / 0.3f);
					if(x==1)
					{
						x=2;
					}
					if(y==1)
					{
						y=2;
					}
					this.x = 2 - x;
					this.y = y;
					color = 3;
				} else if (p.y == UNIT_SIZE*10) {
					x = (int) ((p.x + UNIT_SIZE*10) / 0.3f);
					y = (int) ((p.z + UNIT_SIZE*10) / 0.3f);
					if(x==1)
					{
						x=2;
					}
					if(y==1)
					{
						y=2;
					}
					this.x = y;
					this.y = x;
					color = 4;
				} else if (p.y == -UNIT_SIZE*10) {
					x = (int) ((p.x + UNIT_SIZE*10) / 0.3f);
					y = (int) ((p.z + UNIT_SIZE*10) / 0.3f);
					if(x==1)
					{
						x=2;
					}
					if(y==1)
					{
						y=2;
					}
					this.x = 2 - y;
					this.y = x;
					color = 5;
				} else if (p.z == UNIT_SIZE*10) {
					x = (int) ((p.x + UNIT_SIZE*10) / 0.3f);
					y = (int) ((p.y + UNIT_SIZE*10) / 0.3f);
					if(x==1)
					{
						x=2;
					}
					if(y==1)
					{
						y=2;
					}
					this.x = 2 - y;
					this.y = x;
					color = 0;
				} else if (p.z == -UNIT_SIZE*10) {
					x = (int) ((p.x + UNIT_SIZE*10) / 0.3f);
					y = (int) ((p.y + UNIT_SIZE*10) / 0.3f);
					if(x==1)
					{
						x=2;
					}
					if(y==1)
					{
						y=2;
					}
					this.x = 2 - y;
					this.y = 2 - x;
					color = 2;
				} else {
					System.out.println("error1");
				}

				return true;
			} else {
				return false;
			}

		}
		else
		{
			float t = (UNIT_SIZE*15 - start.z) / dv.z;
			float xd = start.x + t * dv.x;
			float yd = start.y + t * dv.y;
			float zd;
	
			if (xd > -UNIT_SIZE*15 && xd < UNIT_SIZE*15 && yd > -UNIT_SIZE*15 && yd < UNIT_SIZE*15) {
				Vector3f p = new Vector3f(xd, yd, UNIT_SIZE*15);
				doub.add(p);
			}
	
			t = (-UNIT_SIZE*15 - start.z) / dv.z;
			xd = start.x + t * dv.x;
			yd = start.y + t * dv.y;
	
			if (xd > -UNIT_SIZE*15 && xd < UNIT_SIZE*15 && yd > -UNIT_SIZE*15 && yd < UNIT_SIZE*15) {
				Vector3f p = new Vector3f(xd, yd, -UNIT_SIZE*15);
				doub.add(p);
			}
	
			t = (UNIT_SIZE*15 - start.x) / dv.x;
			yd = start.y + t * dv.y;
			zd = start.z + t * dv.z;
	
			if (zd > -UNIT_SIZE*15 && zd < UNIT_SIZE*15 && yd > -UNIT_SIZE*15 && yd < UNIT_SIZE*15) {
				Vector3f p = new Vector3f(UNIT_SIZE*15, yd, zd);
				doub.add(p);
			}
	
			t = (-UNIT_SIZE*15 - start.x) / dv.x;
			yd = start.y + t * dv.y;
			zd = start.z + t * dv.z;
	
			if (zd > -UNIT_SIZE*15 && zd < UNIT_SIZE*15 && yd > -UNIT_SIZE*15 && yd < UNIT_SIZE*15) {
				Vector3f p = new Vector3f(-UNIT_SIZE*15, yd, zd);
				doub.add(p);
			}
	
			t = (UNIT_SIZE*15 - start.y) / dv.y;
			xd = start.x + t * dv.x;
			zd = start.z + t * dv.z;
	
			if (zd > -UNIT_SIZE*15 && zd < UNIT_SIZE*15 && xd > -UNIT_SIZE*15 && xd < UNIT_SIZE*15) {
				Vector3f p = new Vector3f(xd, UNIT_SIZE*15, zd);
				doub.add(p);
			}
	
			t = (-UNIT_SIZE*15 - start.y) / dv.y;
			xd = start.x + t * dv.x;
			zd = start.z + t * dv.z;
	
			if (zd > -UNIT_SIZE*15 && zd < UNIT_SIZE*15 && xd > -UNIT_SIZE*15 && xd < UNIT_SIZE*15) {
				Vector3f p = new Vector3f(xd, -UNIT_SIZE*15, zd);
				doub.add(p);
			}
	
			Vector3f p = null;
	
			if (doub.size() == 2) {
				if (doub.get(0).minus(start).module() < doub.get(1).minus(start)
						.module()) {
					p = doub.get(0);
				} else {
					p = doub.get(1);
				}
				down = p;
				int x;
				int y;
	
				if (p.x == UNIT_SIZE*15) {
	
					x = (int) ((p.y + UNIT_SIZE*15) / 0.3f);
					y = (int) ((p.z + UNIT_SIZE*15) / 0.3f);
					this.x = 2 - x;
					this.y = 2 - y;
					color = 1;
	
				} else if (p.x == -UNIT_SIZE*15) {
					x = (int) ((p.y + UNIT_SIZE*15) / 0.3f);
					y = (int) ((p.z + UNIT_SIZE*15) / 0.3f);
					this.x = 2 - x;
					this.y = y;
					color = 3;
				} else if (p.y == UNIT_SIZE*15) {
					x = (int) ((p.x + UNIT_SIZE*15) / 0.3f);
					y = (int) ((p.z + UNIT_SIZE*15) / 0.3f);
					this.x = y;
					this.y = x;
					color = 4;
				} else if (p.y == -UNIT_SIZE*15) {
					x = (int) ((p.x + UNIT_SIZE*15) / 0.3f);
					y = (int) ((p.z + UNIT_SIZE*15) / 0.3f);
					this.x = 2 - y;
					this.y = x;
					color = 5;
				} else if (p.z == UNIT_SIZE*15) {
					x = (int) ((p.x + UNIT_SIZE*15) / 0.3f);
					y = (int) ((p.y + UNIT_SIZE*15) / 0.3f);
	
					this.x = 2 - y;
					this.y = x;
					color = 0;
				} else if (p.z == -UNIT_SIZE*15) {
					x = (int) ((p.x + UNIT_SIZE*15) / 0.3f);
					y = (int) ((p.y + UNIT_SIZE*15) / 0.3f);
	
					this.x = 2 - y;
					this.y = 2 - x;
					color = 2;
				} else {
					System.out.println("error1");
				}
	
				return true;
			} else {
				System.out.println("魔方外");
				return false;
			}
		}

		// 判断交点是不是在照片矩形范围内
	}

	public Vector3f isOutXRange(Vector3f start, Vector3f end, int color) {
		// 初始化一个变换矩阵
		MatrixUtil.setInitStack();
		// 平移坐标系到此照片要绘制的位置
		MatrixUtil.translate(0, 0f, s);
		// 旋转坐标系到此照片对应的角度
		MatrixUtil.rotate(angleCurr, currAxisX, currAxisY, currAxisZ);
		// 获取此照片的总变换矩阵
		float[] m = MatrixUtil.currMatrix;
		// 将射线start->end的起点与终点变换到物体坐标系
		start = MatrixUtil.fromGToO(start, m);
		end = MatrixUtil.fromGToO(end, m);
		// 计算出射线参数方程中的d，即end-start
		Vector3f dv = end.minus(start);

		float t = 0;
		// 根据t计算出交点的X、Y坐标值
		float xd = 0;
		float yd = 0;
		float zd = 0;
		Vector3f up = null;

		if(ORDER == 2)
		{
			switch (color) {
			case 0:
				t = (UNIT_SIZE*10 - start.z) / dv.z;
				xd = start.x + t * dv.x;
				yd = start.y + t * dv.y;
				up = new Vector3f(xd, yd, UNIT_SIZE*10);
				break;
			case 1:
				t = (UNIT_SIZE*10 - start.x) / dv.x;
				yd = start.y + t * dv.y;
				zd = start.z + t * dv.z;
				up = new Vector3f(UNIT_SIZE*10, yd, zd);
				break;
			case 2:
				t = (-UNIT_SIZE*10 - start.z) / dv.z;
				xd = start.x + t * dv.x;
				yd = start.y + t * dv.y;
				up = new Vector3f(xd, yd, -UNIT_SIZE*10);
				break;
			case 3:
				t = (-UNIT_SIZE*10 - start.x) / dv.x;
				yd = start.y + t * dv.y;
				zd = start.z + t * dv.z;
				up = new Vector3f(-UNIT_SIZE*10, yd, zd);
				break;
			case 4:
				t = (UNIT_SIZE*10 - start.y) / dv.y;
				xd = start.x + t * dv.x;
				zd = start.z + t * dv.z;
				up = new Vector3f(xd, UNIT_SIZE*10, zd);
				break;
			case 5:
				t = (-UNIT_SIZE*10 - start.y) / dv.y;
				xd = start.x + t * dv.x;
				zd = start.z + t * dv.z;
				up = new Vector3f(xd, -UNIT_SIZE*10, zd);
				break;
			}
			this.up = up;
			return up;
		}else
		{
			switch (color) {
			case 0:
				t = (UNIT_SIZE*15 - start.z) / dv.z;
				xd = start.x + t * dv.x;
				yd = start.y + t * dv.y;
				up = new Vector3f(xd, yd, UNIT_SIZE*15);
				break;
			case 1:
				t = (UNIT_SIZE*15 - start.x) / dv.x;
				yd = start.y + t * dv.y;
				zd = start.z + t * dv.z;
				up = new Vector3f(UNIT_SIZE*15, yd, zd);
				break;
			case 2:
				t = (-UNIT_SIZE*15 - start.z) / dv.z;
				xd = start.x + t * dv.x;
				yd = start.y + t * dv.y;
				up = new Vector3f(xd, yd, -UNIT_SIZE*15);
				break;
			case 3:
				t = (-UNIT_SIZE*15 - start.x) / dv.x;
				yd = start.y + t * dv.y;
				zd = start.z + t * dv.z;
				up = new Vector3f(-UNIT_SIZE*15, yd, zd);
				break;
			case 4:
				t = (UNIT_SIZE*15 - start.y) / dv.y;
				xd = start.x + t * dv.x;
				zd = start.z + t * dv.z;
				up = new Vector3f(xd, UNIT_SIZE*15, zd);
				break;
			case 5:
				t = (-UNIT_SIZE*15 - start.y) / dv.y;
				xd = start.x + t * dv.x;
				zd = start.z + t * dv.z;
				up = new Vector3f(xd, -UNIT_SIZE*15, zd);
				break;
			}
			this.up = up;
			return up;
		}
	}

	
	@Override
	public void onPause() {
		super.onPause();
		if(sr.cd!=null)
		{
			sr.cd = sr.cc.cubeData.copy();
		}
		if(tt!=null)
		{
			tt.tmpms = tt.ms;
		}
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		super.surfaceDestroyed(holder);
		if(sr.cd!=null)
		{
		sr.cd = sr.cc.cubeData.copy();
		}
		if(tt!=null)
		{
			tt.tmpms = tt.ms;
		}
		if(sr.ht!=null)
		{
			sr.ht.interrupt();
			sr.ht.helpstop = true;
		}
		sr.threadPool.shutdownNow();


	}

	@Override
	public void onResume() {

		super.onResume();
	}
}
