package com.zyz;

import java.util.ArrayList;

import static com.zyz.Constant.*;

import com.zyz.Particle;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback
{
	public Sample7_4 activity;
	public Paint paint;//����
	public DrawThread dt;//�����߳�
	
	/*�������һ����ʷ����*/
	public int mouse_x=BOTTLE_X;
	public int mouse_y=BOTTLE_Y;
	/*ƿ�ӵ����һ����ʷ����*/
	public int bottle_x=BOTTLE_X;
	public int bottle_y=BOTTLE_Y;
	/*�������һ����ʷ����*/
	private int d_x=0;
	private int d_y=0;
	/*ƿ�ӵ����һ����ʷ�ٶȺ�Ŀǰ���ٶ�*/
	private int bottle_vx1=0;
	private int bottle_vx2=0;
	private int bottle_vy1=0;
	private int bottle_vy2=0;
	/*ƿ�ӵļ��ٶȣ�������*/
	private int bottle_fx=0;
	private int bottle_fy=0;
	private ArrayList<Particle> particles;//��������
	private int press=20;//���ӷ�������
	private int numParticles=0;//���Ӹ���
	
	public GameView(Sample7_4 activity)
	{
		super(activity);
		this.activity=activity;
		
		paint = new Paint();//��������
		paint.setAntiAlias(true);//�򿪿����
		particles=new ArrayList<Particle>();
		
		dt=new DrawThread(this);
		dt.start();
	}
	
	long startOld=0;
	@Override
	public void onDraw(Canvas canvas)
	{
		long startNew=System.nanoTime();
		double fps=1.0/((startNew-startOld)/1000000000.0);
		System.out.println("FPS:"+fps);
		startOld=startNew;
		if(canvas==null)
		{
			return;
		}
		canvas.save();

		canvas.drawARGB(255,255, 255, 255);
		
		if(press>0)
		{
			pour();//�������Ӻ���
			press--;
		}
		bottleMove();//ƿ���ƶ�
		particleMove();//��ƿ�����ӽ��м���
		myDraw(canvas,paint);//������λ�û�������
		
		canvas.restore();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {}

	@Override
	public void surfaceCreated(SurfaceHolder holder) 
	{
		repaint();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {}
	
	public void repaint() 
	{
		SurfaceHolder holder=this.getHolder();
		Canvas canvas = holder.lockCanvas();//��ȡ����
		try
		{
			synchronized(holder)
			{
				onDraw(canvas);//����
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(canvas != null)
			{
				holder.unlockCanvasAndPost(canvas);
			}
		}
	}
	
	/*������λ�û������Ӽ�ƿ��*/
	private void myDraw(Canvas canvas,Paint paint)
	{
		
		canvas.drawRect(bottle_x, bottle_y, bottle_x+BOTTLE_WIDTH, bottle_y+BOTTLE_HEIGHT, paint);//����ƿ��
		
		paint.setColor(Color.WHITE);
		for(int i=0;i<numParticles;i++)
		{
			Particle p=particles.get(i);
			canvas.drawCircle(p.x+BOTTLE_X, p.y+BOTTLE_Y, 4, paint);
		}
		paint.reset();
	}
	
	/*����һ������ һ������������*/
	private void pour()
	{
		for(int i=-4;i<=4;i++)
		{
			particles.add(numParticles++, new Particle(200 + i * 32, 5));
			particles.get(numParticles - 1).vy = 5;
		}
	}
	
	public void bottleMove()
	{
		bottle_vx2=mouse_x-d_x-bottle_x;//��¼�ٶ�f
		bottle_x=mouse_x-d_x;//��λ�ñ��浽��ʷ
		bottle_fx=(bottle_vx2-bottle_vx1)/5;//��¼����
		bottle_vx1=bottle_vx2;//������ʷ�ٶ�
		bottle_vy2=mouse_y-d_y-bottle_y;//����ͬ��
		bottle_y=mouse_y-d_y;
		bottle_fy=(bottle_vy2-bottle_vy1)/5;
		bottle_vy1=bottle_vy2;
	}
	
	/*ִ��һ�����ӵļ���*/
	private void particleMove()
	{
		int i;//��ѭ���м����
		int j;//��ѭ���м����
		float dist;//����
		Particle pi;//��ѭ��������
		Particle pj;//��ѭ��������
		float dx;//������x����
		float dy;//������y����
		float weight;//�⻬�˺���ֵ�����ֵ��
		float pressure;//ѹ����С
		float viscosity;//ճ��������С
		/*��ѭ�������ӷ������ڹ⻬�˰뾶��Χ�ڵ����ӻ�Ϊ�ھ�*/
		for (i = 0; i < numParticles; i++)
		{
			pi = particles.get(i);//ȡ��ѭ��Ҫ����������
			pi.numNeighbors = 0;//�������ھ�����
			pi.density = 0;//�������ܶ����Գ�ʼ��
			/*��ѭ���������ֹ������ѭ������*/
			for (j = 0; j < i; j++)
			{
				pj = particles.get(j);//ȡ��ѭ������
				dist = (float)(Math.pow(pi.x - pj.x, 2) + Math.pow(pi.y - pj.y, 2));//�����������ӵľ���ƽ��
				/*����������Ӿ����ڷ�Χ�� �����ж�*/
				if (dist < RANGE2)
				{
					dist = (float)Math.sqrt(dist);//�������
					weight = (float)Math.pow(1 - dist / RANGE, 2);//����⻬�˺�����ֵ
					//��ֵ�ۼӵ��������ӵ��ܶ���
					pi.density += weight;
					pj.density += weight;
					//����Ϊ�ھ�
					pi.neighbors.add(pi.numNeighbors++,pj);
					pj.neighbors.add(pj.numNeighbors++,pi);
				}
			}
		}
		/*������ӵ�ѹ��*/
		for (i = 0; i < numParticles; i++)
		{
			pi = particles.get(i);
			if (pi.density < DENSITY) pi.density = DENSITY;
			pi.pressure = pi.density - DENSITY;//����ѹ������K(�����ܶ�-��̬�ܶ�)��KĬ��Ϊ1
		}
		/*�����������ܱ�����ӵ�ѹ��������*/
		for (i = 0; i < numParticles; i++)
		{
			pi = particles.get(i);
			//��ʼ�����ӵ�����
			pi.fx = 0;
			pi.fy = 0;
			//ѭ���ڼ��������Ӹ�������ӵ�ѹ��������
			for (j = 0; j < pi.numNeighbors; j++)
			{
				pj = pi.neighbors.get(j);
				//�����������ӵ������
				dx = pi.x - pj.x;
				dy = pi.y - pj.y;
				dist = (float)(Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)));//�����������ӵľ���
				weight = 1 - dist / RANGE;//�⻬�˺�����һ��΢�ֺ�
				pressure = weight * (pi.pressure + pj.pressure) / (2 * pj.density) * PRESSURE;//�������Ӷ����ӵ�ѹ������
				/*��ѹ���ֳ�����������ֵ�ۼƵ�������*/
				dist = 1 / dist;
				dx *= dist;
				dy *= dist;
				pi.fx += dx * pressure;
				pi.fy += dy * pressure;
				viscosity = weight / pj.density * VISCOSITY;//������ٶ��������ϵ��
				//������������ٶȵ�����
				dx = pi.vx - pj.vx;
				dy = pi.vy - pj.vy;
				//�����ٶ����� �ó����Ӷ����ӵ�ѹ�� �ۼ�������
				pi.fx -= dx * viscosity;
				pi.fy -= dy * viscosity;
			}
		}
		for (i=0; i<numParticles; i++)
		{
			pi=particles.get(i);
			//�����ⲿƿ�����������䷴���ۼ�
			pi.fx-=bottle_fx;
			pi.fy-=bottle_fy;
			pi.fx-=GRAVITY*GRAVITY_X;
			pi.fy+=GRAVITY*GRAVITY_Y;
			pi.move(this);//���������ó��ٶȺ�λ��
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent e)//��Ļ�����¼�
	{
		switch(e.getAction())
		{
		case MotionEvent.ACTION_MOVE:
			mouse_x=(int) e.getX();
			mouse_y=(int) e.getY();
			break;
		case MotionEvent.ACTION_DOWN:
			mouse_x=(int) e.getX();
			mouse_y=(int) e.getY();
			d_x=(int) (e.getX()-bottle_x);
			d_y=(int) (e.getY()-bottle_y);
			break;
		}
		
		return true;
	}
}
