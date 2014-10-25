package wyf.ytl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class AboutView extends SurfaceView implements SurfaceHolder.Callback{
	Bitmap bmpAbout;
	Bitmap bmpButton;
	HDZGActivity activity;
	public AboutView(HDZGActivity activity) {
		super(activity);
		this.activity = activity;
		this.getHolder().addCallback(this);
		bmpAbout = BitmapFactory.decodeResource(getResources(), R.drawable.about);
		Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.buttons);
		bmpButton = Bitmap.createBitmap(bmp, 0, 0, 60, 30);
	} 
	
	public void onDraw(Canvas canvas){
		canvas.save();
		canvas.translate(ConstantUtil.LOX, ConstantUtil.LOY);
		canvas.scale(ConstantUtil.RADIO, ConstantUtil.RADIO);
		canvas.clipRect(0,0,320,480);
		Paint paint = new Paint();
		paint.setTextSize(18);
		paint.setAntiAlias(true);
		canvas.drawBitmap(bmpAbout, 0, 0, null);
		canvas.drawBitmap(bmpButton, 240,430,null);
		canvas.drawText("����", 250, 450, paint);
		canvas.restore();
	}

	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		
	}

	public void surfaceCreated(SurfaceHolder holder) {//����ʱ������
		Canvas canvas = holder.lockCanvas();
		try{
			synchronized(holder){
				onDraw(canvas);//����
			}			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(canvas != null){
				holder.unlockCanvasAndPost(canvas);
			}
		}
	}

	public void surfaceDestroyed(SurfaceHolder arg0) {

	}

	public boolean onTouchEvent(MotionEvent event) {
		
		if(event.getAction() == MotionEvent.ACTION_DOWN){//����ǰ����¼�
			int x = (int)event.getX();
			int y = (int)event.getY();
			if(x>(240+ConstantUtil.LOX/ConstantUtil.RADIO)*ConstantUtil.RADIO && 
				x<(300+ConstantUtil.LOX/ConstantUtil.RADIO)*ConstantUtil.RADIO && 
				y>(430+ConstantUtil.LOY/ConstantUtil.RADIO)*ConstantUtil.RADIO&& 
				y<(460+ConstantUtil.LOY/ConstantUtil.RADIO)*ConstantUtil.RADIO){//�����˷��ؼ�
				activity.myHandler.sendEmptyMessage(12);
			}
		}
		return true;
	}
}