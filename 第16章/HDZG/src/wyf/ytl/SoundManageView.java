package wyf.ytl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SoundManageView extends SurfaceView implements SurfaceHolder.Callback {
	private DrawThread drawThread;//刷帧的线程
	
	HDZGActivity activity;
	Bitmap soundBackground;
	Bitmap panel_back;
	Bitmap open;
	Bitmap close;
	Bitmap buttonBackGround;
	Paint paint;
	int type;
	public SoundManageView(HDZGActivity activity, int type) {
		super(activity);
		this.activity = activity;
		this.type = type;
        getHolder().addCallback(this);
        this.drawThread = new DrawThread(getHolder(), this);//初始化刷帧线程
        initBitmap();
	}
	
	public void initBitmap(){
		paint = new Paint(); 
		paint.setARGB(255, 42, 48, 103);//设置字体颜色
		paint.setAntiAlias(true);//抗锯齿
		soundBackground = BitmapFactory.decodeResource(getResources(), R.drawable.sound_back );
		panel_back = BitmapFactory.decodeResource(getResources(), R.drawable.panel_back);
		open = BitmapFactory.decodeResource(getResources(), R.drawable.open);
		close = BitmapFactory.decodeResource(getResources(), R.drawable.close);
		Bitmap menu_item = BitmapFactory.decodeResource(getResources(), R.drawable.buttons);
		buttonBackGround = Bitmap.createBitmap(menu_item, 0, 0, 60, 30);
	}

	public void onDraw(Canvas canvas){
		canvas.save();
		canvas.translate(ConstantUtil.LOX, ConstantUtil.LOY);
		canvas.scale(ConstantUtil.RADIO, ConstantUtil.RADIO);
		canvas.clipRect(0,0,320,480);
		canvas.drawColor(Color.WHITE);

		canvas.drawBitmap(panel_back, 0, 0, paint);
		canvas.drawBitmap(soundBackground, 40, 121, paint);
		paint.setTextSize(23);//设置文字大小
		canvas.drawText("音效设置", 110, 40, paint);
		
		if(activity.isBackSound){
			canvas.drawBitmap(close, 200, 140, paint);
		}
		else{
			canvas.drawBitmap(open, 200, 140, paint);
		}
		if(activity.isStartSound){
			canvas.drawBitmap(close, 200, 193, paint);
		}
		else{
			canvas.drawBitmap(open, 200, 193, paint);
		}
		if(activity.isBattleSound){
			canvas.drawBitmap(close, 200,252, paint);
		}
		else{
			canvas.drawBitmap(open, 200, 252, paint); 
		}
		if(activity.isEnvironmentSound){
			canvas.drawBitmap(close, 200, 310, paint);
		}
		else{
			canvas.drawBitmap(open, 200, 310, paint);
		}
		
		canvas.drawBitmap(buttonBackGround, 130, 430, paint);
		paint.setTextSize(18);//设置文字大小
		canvas.drawText("确定", 142, 452, paint);
		canvas.restore();
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			int x = (int) event.getX();
			int y = (int) event.getY();
			if(x>(200+ConstantUtil.LOX/ConstantUtil.RADIO)*ConstantUtil.RADIO 
				&& x<(260+ConstantUtil.LOX/ConstantUtil.RADIO)*ConstantUtil.RADIO
				&& y>(140+ConstantUtil.LOY/ConstantUtil.RADIO)*ConstantUtil.RADIO 
				&& y<(170+ConstantUtil.LOY/ConstantUtil.RADIO)*ConstantUtil.RADIO){//背景音乐
				activity.isBackSound = !activity.isBackSound;
				if(type == 6){//gameView来的
					if(activity.isBackSound && !activity.gameView.mMediaPlayer.isPlaying()){
						activity.gameView.mMediaPlayer.start();
					}
					if(!activity.isBackSound && activity.gameView.mMediaPlayer.isPlaying()){
						activity.gameView.mMediaPlayer.pause();
					}
				}
			}
			else if(x>(200+ConstantUtil.LOX/ConstantUtil.RADIO)*ConstantUtil.RADIO 
					&& x<(260+ConstantUtil.LOX/ConstantUtil.RADIO)*ConstantUtil.RADIO 
					&& y>(193+ConstantUtil.LOY/ConstantUtil.RADIO)*ConstantUtil.RADIO 
					&& y<(223+ConstantUtil.LOY/ConstantUtil.RADIO)*ConstantUtil.RADIO){//开场音乐
				activity.isStartSound = !activity.isStartSound; 
				if(type == 5){//menu来的
					if(activity.isStartSound && !activity.menuView.mMediaPlayer.isPlaying()){
						activity.menuView.mMediaPlayer.start();
					}
					if(!activity.isStartSound && activity.menuView.mMediaPlayer.isPlaying()){
						activity.menuView.mMediaPlayer.pause();
					}
				}
			}
			else if(x>(200+ConstantUtil.LOX/ConstantUtil.RADIO)*ConstantUtil.RADIO 
					&& x<(260+ConstantUtil.LOX/ConstantUtil.RADIO)*ConstantUtil.RADIO 
					&& y>(252+ConstantUtil.LOY/ConstantUtil.RADIO)*ConstantUtil.RADIO 
					&& y<(282+ConstantUtil.LOY/ConstantUtil.RADIO)*ConstantUtil.RADIO){//战斗音乐
				activity.isBattleSound = !activity.isBattleSound;
			}
			else if(x>(200+ConstantUtil.LOX/ConstantUtil.RADIO)*ConstantUtil.RADIO 
					&& x<(260+ConstantUtil.LOX/ConstantUtil.RADIO)*ConstantUtil.RADIO 
					&& y>(310+ConstantUtil.LOY/ConstantUtil.RADIO)*ConstantUtil.RADIO 
					&& y<(340+ConstantUtil.LOY/ConstantUtil.RADIO)*ConstantUtil.RADIO){//环境音效
				activity.isEnvironmentSound = !activity.isEnvironmentSound; 
			}
			else if(x>(130+ConstantUtil.LOX/ConstantUtil.RADIO)*ConstantUtil.RADIO 
					&& x<(190+ConstantUtil.LOX/ConstantUtil.RADIO)*ConstantUtil.RADIO 
					&& y>(430+ConstantUtil.LOY/ConstantUtil.RADIO)*ConstantUtil.RADIO 
					&& y<(470+ConstantUtil.LOY/ConstantUtil.RADIO)*ConstantUtil.RADIO){//确定按钮
				activity.myHandler.sendEmptyMessage(type);
			}
		}
		return true;
	}
	
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
        this.drawThread.setFlag(true);
        this.drawThread.start();
	}

	public void surfaceCreated(SurfaceHolder holder) {
		
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        drawThread.setFlag(false);
        while (retry) {
            try {
                drawThread.join();
                retry = false;
            } 
            catch (InterruptedException e) {//不断地循环，直到刷帧线程结束
            }
        }
	}
	class DrawThread extends Thread{//刷帧线程
		private int sleepSpan = 300;//睡眠的毫秒数 
		private SurfaceHolder surfaceHolder;
		private SoundManageView soundManageView;
		private boolean flag = true;
        public DrawThread(SurfaceHolder surfaceHolder, SoundManageView soundManageView) {//构造器
        	super.setName("==SoundManagerView.DrawThread");
            this.surfaceHolder = surfaceHolder;
            this.soundManageView = soundManageView;
        }
        
        public void setFlag(boolean flag) {//设置循环标记位
        	this.flag = flag;
        }
        
		public void run() {
			Canvas c;
			while(flag){
	                c = null;
	                try {
	                	// 锁定整个画布，在内存要求比较高的情况下，建议参数不要为null
	                    c = this.surfaceHolder.lockCanvas(null);
	                    synchronized (this.surfaceHolder) {
	                    	soundManageView.onDraw(c);
	                    }
	                } finally {
	                    if (c != null) {
	                    	//更新屏幕显示内容
	                        this.surfaceHolder.unlockCanvasAndPost(c);
	                    }
	                }
	                try{
	                	Thread.sleep(sleepSpan);//睡眠指定毫秒数
	                }
	                catch(Exception e){
	                	e.printStackTrace();
	                }
			  }
		}
	}
}
