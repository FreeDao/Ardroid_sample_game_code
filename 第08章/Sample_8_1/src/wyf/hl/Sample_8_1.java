package wyf.hl;

import android.app.Activity;
import android.os.Bundle;

public class Sample_8_1 extends Activity {
	
	MapView gameView;//�Լ�ʵ�ֵĵ�ͼView
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);				//���õ�ǰ��ʾ���û�����
        gameView = (MapView) findViewById(R.id.gameView);//�õ�GameView������
        
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