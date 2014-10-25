package wyf.hl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class MapView extends View{
	int span = 85;							//������
	int[][] map = MapList.map;				//��ͼ����			
	int row = map.length;					//��ͼ������
	int col = map[0].length;				//��ͼ������
	Paint paint = new Paint();				//��������
	public MapView(Context context, AttributeSet attrs) {//������
		super(context, attrs);
	}
	protected void onDraw(Canvas canvas) {	//��д�Ļ��Ʒ���
		super.onDraw(canvas);				//ʵ�ָ��෽��
		canvas.drawColor(Color.GRAY);		//����ɫ
		paint.setColor(Color.BLACK);		//������ɫ
		paint.setStyle(Style.STROKE);		//���÷��
		canvas.drawRect(5, 5, 436, 436, paint);//���ƾ���
		for(int i=0; i<row; i++){			//���Ƶ�ͼ��
			for(int j=0; j<col; j++){
				switch(map[i][j]){			//�õ���ͼ�����ж�Ӧֵ
				case 0:						//��Ϊ0ʱ�����Ƹ÷���Ϊ��ɫ
					paint.setColor(Color.WHITE);
					paint.setStyle(Style.FILL);
					canvas.drawRect(6+j*(span+1), 6+i*(span+1), 6+j*(span+1)+span, 6+i*(span+1)+span, paint);
					break;
				case 1:						//��Ϊ1ʱ�����Ƹ÷���Ϊ��ɫ
					paint.setColor(Color.BLACK);
					paint.setStyle(Style.FILL);
					canvas.drawRect(6+j*(span+1), 6+i*(span+1), 6+j*(span+1)+span, 6+i*(span+1)+span, paint);
					break;
				default:					//δ��������Ҫ�����������������Ƶ�ͼ����ʾ���ݣ�����ֻ��������
					break;
				}
			}
		}
	}

	
	
	
}