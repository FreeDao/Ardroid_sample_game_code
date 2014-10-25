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
	public Paint paint;//画笔
	public DrawThread dt;//绘制线程
	
	/*鼠标的最近一次历史坐标*/
	public int mouse_x=BOTTLE_X;
	public int mouse_y=BOTTLE_Y;
	/*瓶子的最近一次历史坐标*/
	public int bottle_x=BOTTLE_X;
	public int bottle_y=BOTTLE_Y;
	/*鼠标的最近一次历史坐标*/
	private int d_x=0;
	private int d_y=0;
	/*瓶子的最近一次历史速度和目前的速度*/
	private int bottle_vx1=0;
	private int bottle_vx2=0;
	private int bottle_vy1=0;
	private int bottle_vy2=0;
	/*瓶子的加速度（受力）*/
	private int bottle_fx=0;
	private int bottle_fy=0;
	private ArrayList<Particle> particles;//粒子数组
	private int press=20;//粒子发射组数
	private int numParticles=0;//粒子个数
	
	public GameView(Sample7_4 activity)
	{
		super(activity);
		this.activity=activity;
		
		paint = new Paint();//创建画笔
		paint.setAntiAlias(true);//打开抗锯齿
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
			pour();//发射粒子函数
			press--;
		}
		bottleMove();//瓶子移动
		particleMove();//对瓶内粒子进行计算
		myDraw(canvas,paint);//在最新位置绘制粒子
		
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
		Canvas canvas = holder.lockCanvas();//获取画布
		try
		{
			synchronized(holder)
			{
				onDraw(canvas);//绘制
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
	
	/*在最新位置绘制粒子及瓶子*/
	private void myDraw(Canvas canvas,Paint paint)
	{
		
		canvas.drawRect(bottle_x, bottle_y, bottle_x+BOTTLE_WIDTH, bottle_y+BOTTLE_HEIGHT, paint);//绘制瓶子
		
		paint.setColor(Color.WHITE);
		for(int i=0;i<numParticles;i++)
		{
			Particle p=particles.get(i);
			canvas.drawCircle(p.x+BOTTLE_X, p.y+BOTTLE_Y, 4, paint);
		}
		paint.reset();
	}
	
	/*发射一组粒子 一组粒子有六个*/
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
		bottle_vx2=mouse_x-d_x-bottle_x;//记录速度f
		bottle_x=mouse_x-d_x;//将位置保存到历史
		bottle_fx=(bottle_vx2-bottle_vx1)/5;//记录受力
		bottle_vx1=bottle_vx2;//保存历史速度
		bottle_vy2=mouse_y-d_y-bottle_y;//以下同理
		bottle_y=mouse_y-d_y;
		bottle_fy=(bottle_vy2-bottle_vy1)/5;
		bottle_vy1=bottle_vy2;
	}
	
	/*执行一次粒子的计算*/
	private void particleMove()
	{
		int i;//外循环中间变量
		int j;//内循环中间变量
		float dist;//距离
		Particle pi;//外循环的粒子
		Particle pj;//内循环的粒子
		float dx;//向量的x分量
		float dy;//向量的y分量
		float weight;//光滑核函数值（或变值）
		float pressure;//压力大小
		float viscosity;//粘稠阻力大小
		/*该循环将粒子分区，在光滑核半径范围内的粒子互为邻居*/
		for (i = 0; i < numParticles; i++)
		{
			pi = particles.get(i);//取外循环要操作的粒子
			pi.numNeighbors = 0;//将粒子邻居至空
			pi.density = 0;//将粒子密度属性初始化
			/*内循环，用握手规则减少循环次数*/
			for (j = 0; j < i; j++)
			{
				pj = particles.get(j);//取内循环粒子
				dist = (float)(Math.pow(pi.x - pj.x, 2) + Math.pow(pi.y - pj.y, 2));//计算两个粒子的距离平方
				/*如果两个粒子距离在范围内 进入判断*/
				if (dist < RANGE2)
				{
					dist = (float)Math.sqrt(dist);//算出距离
					weight = (float)Math.pow(1 - dist / RANGE, 2);//算出光滑核函数的值
					//将值累加到两个粒子的密度里
					pi.density += weight;
					pj.density += weight;
					//互加为邻居
					pi.neighbors.add(pi.numNeighbors++,pj);
					pj.neighbors.add(pj.numNeighbors++,pi);
				}
			}
		}
		/*算出粒子的压力*/
		for (i = 0; i < numParticles; i++)
		{
			pi = particles.get(i);
			if (pi.density < DENSITY) pi.density = DENSITY;
			pi.pressure = pi.density - DENSITY;//粒子压力等于K(粒子密度-静态密度)，K默认为1
		}
		/*计算粒子所受别的粒子的压力和阻力*/
		for (i = 0; i < numParticles; i++)
		{
			pi = particles.get(i);
			//初始化粒子的受力
			pi.fx = 0;
			pi.fy = 0;
			//循环内计算别的粒子给这个粒子的压力和阻力
			for (j = 0; j < pi.numNeighbors; j++)
			{
				pj = pi.neighbors.get(j);
				//计算两个粒子的坐标差
				dx = pi.x - pj.x;
				dy = pi.y - pj.y;
				dist = (float)(Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)));//计算两个粒子的距离
				weight = 1 - dist / RANGE;//光滑核函数的一阶微分和
				pressure = weight * (pi.pressure + pj.pressure) / (2 * pj.density) * PRESSURE;//计算粒子对粒子的压力标量
				/*将压力分成向量的两个值累计到受力中*/
				dist = 1 / dist;
				dx *= dist;
				dy *= dist;
				pi.fx += dx * pressure;
				pi.fy += dy * pressure;
				viscosity = weight / pj.density * VISCOSITY;//计算除速度外的阻力系数
				//计算粒子相对速度的向量
				dx = pi.vx - pj.vx;
				dy = pi.vy - pj.vy;
				//乘入速度因子 得出粒子对粒子的压力 累加入受力
				pi.fx -= dx * viscosity;
				pi.fy -= dy * viscosity;
			}
		}
		for (i=0; i<numParticles; i++)
		{
			pi=particles.get(i);
			//考虑外部瓶子受力，将其反向累计
			pi.fx-=bottle_fx;
			pi.fy-=bottle_fy;
			pi.fx-=GRAVITY*GRAVITY_X;
			pi.fy+=GRAVITY*GRAVITY_Y;
			pi.move(this);//将受力作用成速度和位移
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent e)//屏幕触控事件
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
