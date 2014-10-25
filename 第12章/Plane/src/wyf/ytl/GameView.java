package wyf.ytl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 
 * ����Ϸ����
 *
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback{
	int selectMap = 1;//�ڼ���
	PlaneActivity activity;
	private TutorialThread thread;//ˢ֡���߳�
	GameViewBackGroundThread gameThread;//���������߳�
	PlanMoveThread planMoveThread;//�ɻ��˶��߳�//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	MoveThread moveThread;//�ƶ�������߳� 
	ExplodeThread explodeThread;//��ը��֡���߳� 
	
	int backGroundIX = 0;//����ͼ��x����
	int i = 0;//����ͼ������
	int cloudX = 470;//�Ʋʵ�X���� 
	
	Bitmap battleback;//�����Ĵ�ͼԪ
	Bitmap[] battlebacks = new Bitmap[ConstantUtil.pictureCount];//װ�ָ��Ժ��ͼƬ
	Bitmap cloud;//�Ʋ�
	Bitmap enemyPlane1;//�л�1
	Bitmap enemyPlane2;//�л�2
	Bitmap enemyPlane3;//�л�3
	Bitmap enemyPlane4;//�л�4
	Bitmap playBit;
	Bitmap pauseBit;
	
	Bitmap[] number = new Bitmap[10];//��������
	
	int[] explodesID = new int[]{//��ը������֡
		R.drawable.explode1,
		R.drawable.explode2,
		R.drawable.explode3,
		R.drawable.explode4,
		R.drawable.explode5,
		R.drawable.explode6,
	};
	Bitmap[] explodes = new Bitmap[explodesID.length];//��ը������
	
	Bitmap hullBackground;//��ʾ�����ı���ͼƬ
	Bitmap hull;//������ͼƬ
	Bitmap life;//Ѫ���ͼƬ
	Bitmap changebullet;
	
	Bitmap fireBullet;  //�����ӵ���ͼƬ
	Bitmap direction;   //�������Ʒ����ͼƬ
	
	int status = 1;//��Ϸ��״̬1��ʾ��Ϸ��,2��ʾ��Ϸʧ�ܼ��ҷ��ɻ�û��������
	
	Paint paint;//����
	Plane plane = new Plane(50, 140, 1, 0, ConstantUtil.life, this);//��ʼ���ҷ��ɻ� 

	ArrayList<Bullet> badBollets = new ArrayList<Bullet>();//�з��ɻ��������ӵ�
	ArrayList<Bullet> goodBollets = new ArrayList<Bullet>();//�ҷ��ɻ��������ӵ�
	ArrayList<Explode> explodeList = new ArrayList<Explode>();//��ը
	ArrayList<ChangeBullet> changeBollets = new ArrayList<ChangeBullet>();//���˸ı�ǹ������
	ArrayList<EnemyPlane> enemyPlanes;//�з��ķɻ�
	ArrayList<Life> lifes;//���Ѫ��
	SoundPool soundPool;//����
	HashMap<Integer, Integer> soundPoolMap; 
	HashMap<Integer,FingerTouch> hm=new HashMap<Integer,FingerTouch>();
	MediaPlayer mMediaPlayer; 
	boolean pause;
	public GameView(PlaneActivity activity) {//������ 
		super(activity);
		this.activity = activity;//activity������
		initSounds();
		mMediaPlayer = MediaPlayer.create(activity, R.raw.gamestart);
		mMediaPlayer.setLooping(true);
		
        getHolder().addCallback(this);//ע��ӿ�
        this.thread = new TutorialThread(getHolder(), this);//��ʼ��ˢ֡�߳�
        this.gameThread = new GameViewBackGroundThread(this);//��ʼ�����������߳�

       this.planMoveThread = new PlanMoveThread(activity,this);//////////////////////////////////////////////////////////////////////////////////////////////////
        this.moveThread = new MoveThread(this);
        this.explodeThread = new ExplodeThread(this);
        if(activity.processView != null){
        	activity.processView.process += 20;
        }
        if(this.selectMap == 1){ 
        	enemyPlanes = Maps.getFirst();//ȡ��һ�صĵл�
        	lifes = Maps.getFirstLife();//ȡ��һ�صĵ�Ѫ��
        	changeBollets = Maps.getFirstBollet();//ȡ��һ�س��˸ı�ǹ�������б�
        }
        initBitmap();//��ʼ������ͼƬ
        if(activity.isSound){
        	mMediaPlayer.start();
        }
        if(activity.processView != null){
        	activity.processView.process += 20;
        }
	}
	public void initSounds(){
	     soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
	     soundPoolMap = new HashMap<Integer, Integer>();   
	     soundPoolMap.put(1, soundPool.load(getContext(), R.raw.bulletsound1, 1));
	     soundPoolMap.put(2, soundPool.load(getContext(), R.raw.explode, 1));
	        if(activity.processView != null){
	        	activity.processView.process += 20;
	        }
	     soundPoolMap.put(3, soundPool.load(getContext(), R.raw.dead, 1));
	        if(activity.processView != null){
	        	activity.processView.process += 20;
	        }
	} 
	public void playSound(int sound, int loop) {
	    AudioManager mgr = (AudioManager)getContext().getSystemService(Context.AUDIO_SERVICE);   
	    float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);   
	    float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);       
	    float volume = streamVolumeCurrent / streamVolumeMax;   
	    
	    soundPool.play(soundPoolMap.get(sound), volume, volume, 1, loop, 1f);
	}
	public void initBitmap(){//��ʼ������ͼƬ
        if(activity.processView != null){
        	activity.processView.process += 20;
        }
		paint = new Paint();
		paint.setColor(Color.BLACK);
		battleback = BitmapFactory.decodeResource(getResources(), R.drawable.battleback);//�󱳾�ͼƬ
		cloud = BitmapFactory.decodeResource(getResources(), R.drawable.cloud);//�Ʋ�
		for(int i=0; i<battlebacks.length; i++){//�г�СͼƬ
			battlebacks[i] = Bitmap.createBitmap(battleback, ConstantUtil.pictureWidth*i, 0, ConstantUtil.pictureWidth, ConstantUtil.pictureHeight);
		}
		battleback = null;//�ͷŵ���ͼ
		enemyPlane1 = BitmapFactory.decodeResource(getResources(), R.drawable.plane4);//�л�1��ͼƬ
		enemyPlane2 = BitmapFactory.decodeResource(getResources(), R.drawable.plane5);//�л�2��ͼƬ
		enemyPlane3 = BitmapFactory.decodeResource(getResources(), R.drawable.plane6);//�л�3��ͼƬ
		enemyPlane4 = BitmapFactory.decodeResource(getResources(), R.drawable.plane7);//�л�4��ͼƬ
		
		
		hullBackground = BitmapFactory.decodeResource(getResources(),R.drawable.hullbackground);
		hull = BitmapFactory.decodeResource(getResources(), R.drawable.hull);
		life = BitmapFactory.decodeResource(getResources(), R.drawable.life);
		changebullet = BitmapFactory.decodeResource(getResources(), R.drawable.changebullet);
		
		number[0] = BitmapFactory.decodeResource(getResources(), R.drawable.number0);
		number[1] = BitmapFactory.decodeResource(getResources(), R.drawable.number1);
		number[2] = BitmapFactory.decodeResource(getResources(), R.drawable.number2);
		number[3] = BitmapFactory.decodeResource(getResources(), R.drawable.number3);
		number[4] = BitmapFactory.decodeResource(getResources(), R.drawable.number4);
		number[5] = BitmapFactory.decodeResource(getResources(), R.drawable.number5);
		number[6] = BitmapFactory.decodeResource(getResources(), R.drawable.number6);
		number[7] = BitmapFactory.decodeResource(getResources(), R.drawable.number7);
		number[8] = BitmapFactory.decodeResource(getResources(), R.drawable.number8);
		number[9] = BitmapFactory.decodeResource(getResources(), R.drawable.number9);
		
		fireBullet = BitmapFactory.decodeResource(getResources(), R.drawable.firebullet);
		direction = BitmapFactory.decodeResource(getResources(), R.drawable.direction);
		playBit =  BitmapFactory.decodeResource(getResources(), R.drawable.hplay);
		pauseBit =  BitmapFactory.decodeResource(getResources(), R.drawable.hpause);
		for(int i=0; i<explodes.length; i++){//��ʼ����ըͼƬ
			explodes[i] = BitmapFactory.decodeResource(getResources(), explodesID[i]);
		}
		
		for(EnemyPlane ep : enemyPlanes){//Ϊ�л���ʼ��ͼƬ
			if(ep.type == 1){
				ep.bitmap = enemyPlane1;
			}
			else if(ep.type == 2){
				ep.bitmap = enemyPlane2;
			}
			else if(ep.type == 3){
				ep.bitmap = enemyPlane3;
			}
			else if(ep.type == 4){
				ep.bitmap = enemyPlane4;
			}
		}
		for(Life l : lifes){//ΪѪ���ʼ��ͼƬ
			l.bitmap = life;
		}
		
		for(ChangeBullet cb : changeBollets){//Ϊ���˸ı�ǹ�������ʼ��ͼƬ
			cb.bitmap = changebullet;
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent e)
	{
		//��ȡ���صĶ������
		int action=e.getAction()&MotionEvent.ACTION_MASK;
		//��ȡ��������id��downʱ������id����ȷ��upʱ����id��ȷ������idҪ��ѯMap��ʣ�µ�һ�����id��
		int id=(e.getAction()&MotionEvent.ACTION_POINTER_ID_MASK)>>>MotionEvent.ACTION_POINTER_ID_SHIFT;	
		float x = e.getX(id);
		float y = e.getY(id);
		
		switch(action)									//>>>����˼���޷�������
		{
			case MotionEvent.ACTION_DOWN: //����down 
				hm.put(id, new FingerTouch(e.getX(id),e.getY(id),inWhichRect(x, y)));
				if(x>(416+ConstantUtil.LOX)*ConstantUtil.RADIO
						&&x<(playBit.getWidth()+416+ConstantUtil.LOX)*ConstantUtil.RADIO
						&&y>(ConstantUtil.LOY)*ConstantUtil.RADIO
						&&y<(playBit.getHeight()+ConstantUtil.LOY)*ConstantUtil.RADIO)//����������ϵİ�ť
				{
						pause = !pause; 
						return true;
						
				}
				break;
			case MotionEvent.ACTION_POINTER_DOWN://����down
					if(id<e.getPointerCount()-1)
					{
						//���������˳���൱�ڸ�������
						HashMap<Integer,FingerTouch> hmTemp=new HashMap<Integer,FingerTouch>();
						Set<Integer> ks=hm.keySet();
						for(int i:ks)
						{
							if(i<id)
							{
								hmTemp.put(i, hm.get(i));
							}
							else
							{
								hmTemp.put(i+1, hm.get(i));
							}
						}
						hm=hmTemp;					
					}
					//��Map�м�¼һ���µ�
					hm.put(id, new FingerTouch(e.getX(id),e.getY(id),inWhichRect(e.getX(id), e.getY(id))));
			break;
			case MotionEvent.ACTION_MOVE: //��/����move  2
				//������/����Move��������λ��
				Set<Integer> ks=hm.keySet();
				for(int i:ks)
				{
					hm.get(i).setLocation(e.getX(i), e.getY(i));
					hm.get(i).setType(inWhichRect(e.getX(i), e.getY(i)));
					setPlanState(i,true);
				}
				break;
			case MotionEvent.ACTION_UP: //����up
				switch(hm.get(id).getType()){
				case ConstantUtil.RECT_FIRE: planMoveThread.KEY_A=false;break;
				case ConstantUtil.RECT_UP:planMoveThread.KEY_UP=false;break;
				case ConstantUtil.RECT_DOWN:planMoveThread.KEY_DOWN=false;break;
				case ConstantUtil.RECT_RIGHT:planMoveThread.KEY_RIGHT=false;break;
				case ConstantUtil.RECT_LEFT:planMoveThread.KEY_LEFT=false;break;
				}
				hm.clear();
				break;
			case MotionEvent.ACTION_POINTER_UP: //����up
				switch(hm.get(id).getType()){
				case ConstantUtil.RECT_FIRE: planMoveThread.KEY_A=false;break;
				case ConstantUtil.RECT_UP:planMoveThread.KEY_UP=false;break;
				case ConstantUtil.RECT_DOWN:planMoveThread.KEY_DOWN=false;break;
				case ConstantUtil.RECT_RIGHT:planMoveThread.KEY_RIGHT=false;break;
				case ConstantUtil.RECT_LEFT:planMoveThread.KEY_LEFT=false;break;
				}
				hm.remove(id);
				//�������ǰ˳��������
				HashMap<Integer,FingerTouch> hmTemp=new HashMap<Integer,FingerTouch>();
				ks=hm.keySet();
				for(int i:ks)
				{
					if(i>id)
					{
						hmTemp.put(i-1, hm.get(i));
					}
					else
					{
						hmTemp.put(i, hm.get(i));
					}
				}
				hm=hmTemp;
			break;
		}
		Set<Integer> ks=hm.keySet();
		for(int i:ks)
		{
			int type = hm.get(i).getType();
			if(type == ConstantUtil.RECT_FIRE){planMoveThread.KEY_A=true;
			}
			if(type == ConstantUtil.RECT_UP){planMoveThread.KEY_UP=true;
			}
			if(type == ConstantUtil.RECT_DOWN){planMoveThread.KEY_DOWN=true;
			}
			if(type == ConstantUtil.RECT_LEFT){planMoveThread.KEY_LEFT=true;
			}
			if(type == ConstantUtil.RECT_RIGHT){planMoveThread.KEY_RIGHT=true;
			}
		}
		return true;
	}
	
	public void onDraw(Canvas canvas){//�Լ�д�Ļ��Ʒ���,������д��
		canvas.save();
		canvas.translate(ConstantUtil.LOX, ConstantUtil.LOY);
		canvas.scale(ConstantUtil.RADIO, ConstantUtil.RADIO);
		canvas.clipRect(0, 0, 480, 320);
		//ÿ�λ���֮ǰҪ��������
		paint.setColor(Color.BLACK);
		paint.setStyle(Style.FILL);
		canvas.drawRect(-10, -10, ConstantUtil.screenWidth+10, ConstantUtil.screenHeight+10, paint);
		//����������z��ģ��󻭵ĻḲ��ǰ�滭��
		int backGroundIX=this.backGroundIX;
		int i=this.i;
		int cloudX = this.cloudX;
		
		//���i��������
		if(backGroundIX>0){
			int n=(backGroundIX/ConstantUtil.pictureWidth)+((backGroundIX%ConstantUtil.pictureWidth==0)?0:1);//����i�����м���ͼ
			for(int j=1;j<=n;j++){
				canvas.drawBitmap(
			      battlebacks[(i-j+ConstantUtil.pictureCount)%ConstantUtil.pictureCount], 
			      backGroundIX-ConstantUtil.pictureWidth*j, 
			      ConstantUtil.top, 
			      paint
			     );
			}
		}

		//���i�Լ�
		canvas.drawBitmap(battlebacks[i], backGroundIX, ConstantUtil.top, paint);
		
		//���i�Ҳ������
		if(backGroundIX<ConstantUtil.screenWidth-ConstantUtil.pictureWidth){
			int k=ConstantUtil.screenWidth-(backGroundIX+ConstantUtil.pictureWidth);
			int n=(k/ConstantUtil.pictureWidth)+((k%ConstantUtil.pictureWidth==0)?0:1);//����i�����м���ͼ
			for(int j=1;j<=n;j++){
				canvas.drawBitmap(
						battlebacks[(i+j)%ConstantUtil.pictureCount], 
						backGroundIX+ConstantUtil.pictureWidth*j, 
						ConstantUtil.top, 
						paint
				);
			}
		}	

		plane.draw(canvas);//����ҷɻ�
		if(status == 1 || status == 3){//��Ϸ��ʱ,�ؿ���ʱ
			try{//�����ҷ��ӵ�
				for(Bullet b:goodBollets){
					b.draw(canvas);
				}			
			}
			catch(Exception e){  
			}
			try{//���Ƶз��ɻ�
				for(EnemyPlane ep:enemyPlanes){
					if(ep.status == true){
						ep.draw(canvas);
					}
				}
			}
			catch(Exception e){
			} 
			try{//���Ƶз��ӵ�  
				for(Bullet b:badBollets){
					b.draw(canvas);
				}
			}
			catch(Exception e){
			}	
			try{
				for(ChangeBullet cb : changeBollets){//���Ƴ��˸ı�ǹ������
					if(cb.status == true){
						cb.draw(canvas);
					}
				}
			}
			catch(Exception e){
			}
			try{//����Ѫ��
				for(Life l : lifes){
					if(l.status == true){
						l.draw(canvas);
					}
				}
			}
			catch(Exception e){
			}
			try{//���Ʊ�ը
				for(Explode e : explodeList){
					e.draw(canvas);
				}
			}
			catch(Exception e){
			}
		}
		
		if(cloudX>-cloud.getWidth() && cloudX<ConstantUtil.screenWidth){//�����Ʋ�
			canvas.drawBitmap(cloud, cloudX, ConstantUtil.top, paint);
		}
		
		canvas.drawBitmap(hullBackground, 0, 10, paint);//���������ı���

		for(int j=0; j<((5-plane.life)<0?5:plane.life); j++){//���Ʊ�ʾ������С����
			canvas.drawBitmap(hull, 95+11*j, 13, paint);
		}

		String timeStr = gameThread.touchTime/10+"";//ת�����ַ���
    	for(int c=0;c<timeStr.length();c++){//ѭ������ʱ��
    		int tempScore=timeStr.charAt(c)-'0';
    		canvas.drawBitmap(number[tempScore], 350+c*22, 12, paint);
    	}
		//���Ʒ����ӵ���ͼƬ�ͷ���ͼƬ
		canvas.drawBitmap(fireBullet, 5, ConstantUtil.screenHeight - fireBullet.getHeight()-5, paint);
		canvas.drawBitmap(direction, ConstantUtil.screenWidth- direction.getWidth()-5, ConstantUtil.screenHeight - direction.getHeight()-5, paint);
		if(pause)canvas.drawBitmap(playBit, 416,0, paint);
		else canvas.drawBitmap(pauseBit, 416,0, paint);
		canvas.restore();  //�ָ�����
	}
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}
	public void surfaceCreated(SurfaceHolder holder) {//����ʱ������Ӧ����
        this.thread.setFlag(true);//����ˢ֡�߳�
        this.thread.start();
        
        this.gameThread.setFlag(true);
        this.gameThread.start();//�������������߳�
        
        this.planMoveThread.setFlag(true);/////////////////////////////////////////////////////////////////////////////////////////
        planMoveThread.start();//�������̼����߳�/////////////////////////////////////////////////////////////////////////////////////
        
        this.moveThread.setFlag(true);
        moveThread.start();//���������ƶ�����ƶ��߳�
        
        this.explodeThread.setFlag(true);
        explodeThread.start();
	}
	public void surfaceDestroyed(SurfaceHolder holder) {//�ݻ�ʱ�ͷ���Ӧ����
        soundPool.release();
        soundPool=null;
        stopThread();
	}
	
	public void stopThread(){
		boolean retry = true;
		this.thread.setFlag(false);
        this.gameThread.setFlag(false);
        this.planMoveThread.setFlag(false);///////////////////////////////////////////////////////////////////////////////////////////////////
        this.moveThread.setFlag(false);
        this.explodeThread.setFlag(false);
        while (retry) {
            try {
            	planMoveThread.join();//////////////////////////////////////////////////////////////////////////////////////////////////////////////
                thread.join();
                gameThread.join();
                moveThread.join();
                explodeThread.join();
                retry = false;
            } 
            catch (InterruptedException e) {//���ϵ�ѭ����ֱ��ˢ֡�߳̽���
            }
        }
	}
	
	public int  inWhichRect(float x, float y){
		if(x>(ConstantUtil.screenWidth- direction.getWidth()+30+ConstantUtil.LOX/ConstantUtil.RADIO)*ConstantUtil.RADIO
				&&x<(ConstantUtil.screenWidth- direction.getWidth()+110+ConstantUtil.LOX/ConstantUtil.RADIO)*ConstantUtil.RADIO
				&&y>(ConstantUtil.screenHeight- direction.getHeight()+ConstantUtil.LOY/ConstantUtil.RADIO)*ConstantUtil.RADIO
				&&y<(ConstantUtil.screenHeight- direction.getHeight()+40+ConstantUtil.LOY/ConstantUtil.RADIO)*ConstantUtil.RADIO)//����������ϵİ�ť
			{
				return ConstantUtil.RECT_UP;
				
			}
			else if(x>(ConstantUtil.screenWidth- direction.getWidth()+30+ConstantUtil.LOX/ConstantUtil.RADIO)*ConstantUtil.RADIO
					&&x<(ConstantUtil.screenWidth- direction.getWidth()+110+ConstantUtil.LOX/ConstantUtil.RADIO)*ConstantUtil.RADIO
					&&y>(ConstantUtil.screenHeight- direction.getHeight()+90+ConstantUtil.LOY/ConstantUtil.RADIO)*ConstantUtil.RADIO
					&&y<(ConstantUtil.screenHeight- direction.getHeight()+128+ConstantUtil.LOY/ConstantUtil.RADIO)*ConstantUtil.RADIO)//����������µİ�ť
			{
				return ConstantUtil.RECT_DOWN;
			}
			else if(x>(ConstantUtil.screenWidth- direction.getWidth()+ConstantUtil.LOX/ConstantUtil.RADIO)*ConstantUtil.RADIO
					&&x<(ConstantUtil.screenWidth- direction.getWidth()+40+ConstantUtil.LOX/ConstantUtil.RADIO)*ConstantUtil.RADIO
					&&y>(ConstantUtil.screenHeight- direction.getHeight()+30+ConstantUtil.LOY/ConstantUtil.RADIO)*ConstantUtil.RADIO
					&&y<(ConstantUtil.screenHeight- direction.getHeight()+90+ConstantUtil.LOY/ConstantUtil.RADIO)*ConstantUtil.RADIO)//�����������İ�ť
			{
				return ConstantUtil.RECT_LEFT;
			}
			else if(x>(ConstantUtil.screenWidth- direction.getWidth()+90+ConstantUtil.LOX/ConstantUtil.RADIO)*ConstantUtil.RADIO
					&&x<(ConstantUtil.screenWidth- direction.getWidth()+125+ConstantUtil.LOX/ConstantUtil.RADIO)*ConstantUtil.RADIO
					&&y>(ConstantUtil.screenHeight- direction.getHeight()+30+ConstantUtil.LOY/ConstantUtil.RADIO)*ConstantUtil.RADIO
					&&y<(ConstantUtil.screenHeight- direction.getHeight()+90+ConstantUtil.LOY/ConstantUtil.RADIO)*ConstantUtil.RADIO)//����������ҵİ�ť
			{
				return ConstantUtil.RECT_RIGHT;
			}
			else if(x>(0 + ConstantUtil.LOX/ConstantUtil.RADIO)*ConstantUtil.RADIO
					&&x<(0 + fireBullet.getWidth()+ConstantUtil.LOX/ConstantUtil.RADIO)*ConstantUtil.RADIO
					&&y>(ConstantUtil.screenHeight - fireBullet.getHeight()+ConstantUtil.LOY/ConstantUtil.RADIO)*ConstantUtil.RADIO
					&&y<(ConstantUtil.screenHeight+ConstantUtil.LOY/ConstantUtil.RADIO)*ConstantUtil.RADIO)//�����������İ�ť
			{
				return ConstantUtil.RECT_FIRE;
			}
		return ConstantUtil.RECT_SPACE;
		
	}
	
	public void setPlanState(int id,boolean isTF){
		switch(hm.get(id).getOldType()){
		case ConstantUtil.RECT_FIRE: planMoveThread.KEY_A=!isTF;break;
		case ConstantUtil.RECT_UP:planMoveThread.KEY_UP=!isTF;break;
		case ConstantUtil.RECT_DOWN:planMoveThread.KEY_DOWN=!isTF;break;
		case ConstantUtil.RECT_RIGHT:planMoveThread.KEY_RIGHT=!isTF;break;
		case ConstantUtil.RECT_LEFT:planMoveThread.KEY_LEFT=!isTF;break;
		}
		switch(hm.get(id).getType()){
		case ConstantUtil.RECT_FIRE: planMoveThread.KEY_A=isTF;break;
		case ConstantUtil.RECT_UP:planMoveThread.KEY_UP=isTF;break;
		case ConstantUtil.RECT_DOWN:planMoveThread.KEY_DOWN=isTF;break;
		case ConstantUtil.RECT_RIGHT:planMoveThread.KEY_RIGHT=isTF;break;
		case ConstantUtil.RECT_LEFT:planMoveThread.KEY_LEFT=isTF;break;
		}
	}
	class TutorialThread extends Thread{//ˢ֡�߳�
		private int sleepSpan = 30;//˯�ߵĺ����� 
		private SurfaceHolder surfaceHolder;
		private GameView gameView;
		private boolean flag = false;
        public TutorialThread(SurfaceHolder surfaceHolder, GameView gameView) {//������
            this.surfaceHolder = surfaceHolder;
            this.gameView = gameView;
        }
        public void setFlag(boolean flag) {//����ѭ�����λ
        	this.flag = flag;
        }
		@Override
		public void run() {
			Canvas c;
            while (this.flag) {
                c = null;
                try {
                	// �����������������ڴ�Ҫ��Ƚϸߵ�����£����������ҪΪnull
                    c = this.surfaceHolder.lockCanvas(null);
                    synchronized (this.surfaceHolder) {
                    	gameView.onDraw(c);//����
                    }
                } finally {
                    if (c != null) {
                    	//������Ļ��ʾ����
                        this.surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
                try{
                	Thread.sleep(sleepSpan);//˯��ָ��������
                }
                catch(Exception e){
                	e.printStackTrace();//��ӡ��ջ��Ϣ
                }
            }
		}
	}
}