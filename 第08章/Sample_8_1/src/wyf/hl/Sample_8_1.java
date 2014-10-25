package wyf.hl;

import android.app.Activity;
import android.os.Bundle;

public class Sample_8_1 extends Activity {
	
	MapView gameView;//自己实现的地图View
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);				//设置当前显示的用户界面
        gameView = (MapView) findViewById(R.id.gameView);//得到GameView的引用
        
    }

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
    
}