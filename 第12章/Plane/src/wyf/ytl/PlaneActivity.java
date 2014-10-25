package wyf.ytl;

import android.app.Activity;//引入相关类
import android.content.pm.ActivityInfo;
import android.os.Bundle;//引入相关类
import android.os.Handler;//引入相关类
import android.os.Looper;//引入相关类
import android.os.Message;//引入相关类
import android.view.Display;
import android.view.KeyEvent;//引入相关类
import android.view.Window;//引入相关类
import android.view.WindowManager;//引入相关类
public class PlaneActivity extends Activity{
	GameView gameView;//GameView的引用
	WelcomeView welcomeView;//WelcomeView的引用
	FailView failView;//游戏失败界面的引用
	HelpView helpView;//HelpView的引用
	WinView winView;//胜利界面的引用
	ProcessView processView;//进度条界面的引用
	boolean isSound = true;//是否播放声音
	public ScreenScaleResult screenScaleResult;
	public boolean isClickHelp = false;//是否点击了帮助的确定按钮
	Handler myHandler = new Handler(){//用来更新UI线程中的控件
        public void handleMessage(Message msg) {
        	if(msg.what == 1){//游戏失败，玩家飞机坠毁
        		if(gameView != null){
        			gameView.stopThread();
        			gameView = null;
        		}
        		initFailView();//切换到FialView
        	}
        	else if(msg.what == 2){//切换到GameView
        		if(welcomeView != null){
        			welcomeView.stopThread();
        			welcomeView = null;//释放欢迎界面
        		}
        		if(processView != null){
        			processView = null;//释放加载界面
        		}
            	processView = new ProcessView(PlaneActivity.this,2);//初始化进度条并切换到进度条View
            	PlaneActivity.this.setContentView(processView);
            	new Thread(){//线程
            		public void run(){
            			Looper.prepare();
            			gameView = new GameView(PlaneActivity.this);//初始化GameView
            			Looper.loop();
            		}
            	}.start();//启动线程
        	}
        	else if(msg.what == 3){//WelcomeView发来的消息，切换到HelpView
        		initHelpView();//切换到HelpView界面
        	}
        	else if(msg.what == 4){
        		if(helpView != null){
        			helpView = null;
        		}
        		toWelcomeView();//切换到WelcomeView界面 
        	}
        	else if(msg.what == 5){
        		if(gameView != null){
        			gameView.stopThread();
        			gameView = null;
        		}
        		initWinView();//切换到WinView界面 
        	}
        	else if(msg.what == 6){
        		toGameView();//去游戏界面
        	}
        	else if(msg.what == 7){
        		if(welcomeView != null){//释放欢迎界面
        			welcomeView = null;
        		}
        		if(helpView != null){
        			helpView.stopThread();
        			helpView = null;
        		}
        		if(processView != null){//释放加载界面
        			processView = null;
        		}
        		
            	welcomeView = new WelcomeView(PlaneActivity.this);//初始化WelcomeView
                welcomeView.status=4;
            	welcomeView.background2Y=-50;
            	toWelcomeView();//切换到WelcomeView界面 
            	
        	}
        }
	};
    public void onCreate(Bundle savedInstanceState) {//创建是被创建
        super.onCreate(savedInstanceState);
		//全屏
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//设置为横屏模式
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		WindowManager windowManager = this.getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		ConstantUtil.PMX1 = display.getWidth();
		ConstantUtil.PMY1 = display.getHeight();
		//屏幕自适应
		screenScaleResult = ScreenScaleUtil.calScale(ConstantUtil.PMX1,ConstantUtil.PMY1);
		
		//存储画布相关数据
		ConstantUtil.LOX = screenScaleResult.lucX;
		ConstantUtil.LOY = screenScaleResult.lucY;
		ConstantUtil.RADIO = screenScaleResult.ratio;
    	processView = new ProcessView(this,1);//初始化进度条并切换到进度条View
    	this.setContentView(processView);//设置加载界面
    	
    	new Thread(){//线程
    		public void run(){
    			Looper.prepare();
    			welcomeView = new WelcomeView(PlaneActivity.this);//初始化WelcomeView
    			Looper.loop();
    		}
    	}.start();//启动线程
    }
    public void toWelcomeView(){//切换到欢迎界面     	
    	this.setContentView(welcomeView);
    }
    public void toGameView(){//初始游戏界面
    	this.setContentView(gameView);
    }
    public void initHelpView(){//初始帮助界面
    	helpView = new HelpView(this);
    	this.setContentView(helpView);
    }
    public void initFailView(){//初始游戏失败界面
    	failView = new FailView(this);
    	this.setContentView(failView);
    }
    public void initWinView(){//初始胜利界面
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
    	    	welcomeView = new WelcomeView(PlaneActivity.this);//初始化WelcomeView
    	    	welcomeView.status=4;
    	    	welcomeView.background2Y=-50;
    	    	toWelcomeView();
    	    	return true;
    		}else if(helpView != null){
    			helpView.stopThread();
				helpView = null;
				welcomeView = new WelcomeView(PlaneActivity.this);//初始化WelcomeView
    			welcomeView.status=4;
    			welcomeView.background2Y=-50;
    			toWelcomeView();
    			return true;
			}
        	
        }
	    //继续执行父类的其他点击事件
	    return super.onKeyDown(keyCode, event);
	}
}