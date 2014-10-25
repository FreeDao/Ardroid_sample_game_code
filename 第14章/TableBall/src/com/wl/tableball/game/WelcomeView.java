package com.wl.tableball.game;


import com.wl.tableball.util.PicLoadUtil;
import static com.wl.tableball.util.Constant.*;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class WelcomeView extends SurfaceView implements Callback {
	
	TableBallActivity activity;//activity的引用
	Paint paint;      //画笔
	int currentAlpha;  //当前的不透明值
	int sleepSpan=150;      //动画的时延ms
	Bitmap logo;  //logo图片引用
	float currentX;      //图片位置
	float currentY;
	
	public WelcomeView(TableBallActivity activity){
		super(activity);
		this.activity=activity;
		this.getHolder().addCallback(this);//设置生命周期回调接口的实现者
		paint=new Paint();//创建画笔
		paint.setAntiAlias(true);//打开抗锯齿
		logo=PicLoadUtil.loadBM(getResources(),"androidheli.png" );//加载图片
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		//绘制黑填充矩形清背景
		paint.setColor(Color.BLACK);//设置画笔颜色
		paint.setAlpha(255);//设置不透明度为255
		canvas.drawRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, paint);
		//进行平面贴图
		if(logo==null)return;
		paint.setAlpha(currentAlpha);
		canvas.drawBitmap(logo, currentX, currentY, paint);	
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		currentX=SCREEN_WIDTH/2-logo.getWidth()/2;
		currentY=SCREEN_HEIGHT/2-logo.getHeight()/2;
		
		new Thread(){
			public void run() {
				
				SurfaceHolder mholder=WelcomeView.this.getHolder();//获取回调接口
				for(int i=255;i>-10;i-=10){
					
					if(i<0)//如果当前不透明度小于零
					{
						i=0;//将不透明度置为零
					}
					currentAlpha=i;
					Canvas canvas=mholder.lockCanvas();//获取画布
					try {
						synchronized (mholder) //同步
						{
							onDraw(canvas);//进行绘制绘制
						}
						Thread.sleep(200);
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						if(canvas!=null)
						{
							mholder.unlockCanvasAndPost(canvas);
						}
					}
					
				}
				while(!LOAD_FINISH)
				{
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				activity.hd.sendEmptyMessage(1);
			}
		}.start();

	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

}
