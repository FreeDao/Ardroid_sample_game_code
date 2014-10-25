package wyf.ytl;

import android.app.Activity;//���������
import android.content.pm.ActivityInfo;
import android.os.Bundle;//���������
import android.os.Handler;//���������
import android.os.Looper;//���������
import android.os.Message;//���������
import android.view.Display;
import android.view.KeyEvent;//���������
import android.view.Window;//���������
import android.view.WindowManager;//���������
public class PlaneActivity extends Activity{
	GameView gameView;//GameView������
	WelcomeView welcomeView;//WelcomeView������
	FailView failView;//��Ϸʧ�ܽ��������
	HelpView helpView;//HelpView������
	WinView winView;//ʤ�����������
	ProcessView processView;//���������������
	boolean isSound = true;//�Ƿ񲥷�����
	public ScreenScaleResult screenScaleResult;
	public boolean isClickHelp = false;//�Ƿ����˰�����ȷ����ť
	Handler myHandler = new Handler(){//��������UI�߳��еĿؼ�
        public void handleMessage(Message msg) {
        	if(msg.what == 1){//��Ϸʧ�ܣ���ҷɻ�׹��
        		if(gameView != null){
        			gameView.stopThread();
        			gameView = null;
        		}
        		initFailView();//�л���FialView
        	}
        	else if(msg.what == 2){//�л���GameView
        		if(welcomeView != null){
        			welcomeView.stopThread();
        			welcomeView = null;//�ͷŻ�ӭ����
        		}
        		if(processView != null){
        			processView = null;//�ͷż��ؽ���
        		}
            	processView = new ProcessView(PlaneActivity.this,2);//��ʼ�����������л���������View
            	PlaneActivity.this.setContentView(processView);
            	new Thread(){//�߳�
            		public void run(){
            			Looper.prepare();
            			gameView = new GameView(PlaneActivity.this);//��ʼ��GameView
            			Looper.loop();
            		}
            	}.start();//�����߳�
        	}
        	else if(msg.what == 3){//WelcomeView��������Ϣ���л���HelpView
        		initHelpView();//�л���HelpView����
        	}
        	else if(msg.what == 4){
        		if(helpView != null){
        			helpView = null;
        		}
        		toWelcomeView();//�л���WelcomeView���� 
        	}
        	else if(msg.what == 5){
        		if(gameView != null){
        			gameView.stopThread();
        			gameView = null;
        		}
        		initWinView();//�л���WinView���� 
        	}
        	else if(msg.what == 6){
        		toGameView();//ȥ��Ϸ����
        	}
        	else if(msg.what == 7){
        		if(welcomeView != null){//�ͷŻ�ӭ����
        			welcomeView = null;
        		}
        		if(helpView != null){
        			helpView.stopThread();
        			helpView = null;
        		}
        		if(processView != null){//�ͷż��ؽ���
        			processView = null;
        		}
        		
            	welcomeView = new WelcomeView(PlaneActivity.this);//��ʼ��WelcomeView
                welcomeView.status=4;
            	welcomeView.background2Y=-50;
            	toWelcomeView();//�л���WelcomeView���� 
            	
        	}
        }
	};
    public void onCreate(Bundle savedInstanceState) {//�����Ǳ�����
        super.onCreate(savedInstanceState);
		//ȫ��
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//����Ϊ����ģʽ
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		WindowManager windowManager = this.getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		ConstantUtil.PMX1 = display.getWidth();
		ConstantUtil.PMY1 = display.getHeight();
		//��Ļ����Ӧ
		screenScaleResult = ScreenScaleUtil.calScale(ConstantUtil.PMX1,ConstantUtil.PMY1);
		
		//�洢�����������
		ConstantUtil.LOX = screenScaleResult.lucX;
		ConstantUtil.LOY = screenScaleResult.lucY;
		ConstantUtil.RADIO = screenScaleResult.ratio;
    	processView = new ProcessView(this,1);//��ʼ�����������л���������View
    	this.setContentView(processView);//���ü��ؽ���
    	
    	new Thread(){//�߳�
    		public void run(){
    			Looper.prepare();
    			welcomeView = new WelcomeView(PlaneActivity.this);//��ʼ��WelcomeView
    			Looper.loop();
    		}
    	}.start();//�����߳�
    }
    public void toWelcomeView(){//�л�����ӭ����     	
    	this.setContentView(welcomeView);
    }
    public void toGameView(){//��ʼ��Ϸ����
    	this.setContentView(gameView);
    }
    public void initHelpView(){//��ʼ��������
    	helpView = new HelpView(this);
    	this.setContentView(helpView);
    }
    public void initFailView(){//��ʼ��Ϸʧ�ܽ���
    	failView = new FailView(this);
    	this.setContentView(failView);
    }
    public void initWinView(){//��ʼʤ������
    	winView = new WinView(this);
    	this.setContentView(winView);
    }
    
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if(keyCode == KeyEvent.KEYCODE_BACK)
        {
			if(gameView != null){
				gameView.stopThread();
    			gameView.mMediaPlayer.stop();
    			gameView.mMediaPlayer.release();
    			gameView=null;
    	    	welcomeView = new WelcomeView(PlaneActivity.this);//��ʼ��WelcomeView
    	    	welcomeView.status=4;
    	    	welcomeView.background2Y=-50;
    	    	toWelcomeView();
    	    	return true;
    		}else if(helpView != null){
    			helpView.stopThread();
				helpView = null;
				welcomeView = new WelcomeView(PlaneActivity.this);//��ʼ��WelcomeView
    			welcomeView.status=4;
    			welcomeView.background2Y=-50;
    			toWelcomeView();
    			return true;
			}
        	
        }
	    //����ִ�и������������¼�
	    return super.onKeyDown(keyCode, event);
	}
}