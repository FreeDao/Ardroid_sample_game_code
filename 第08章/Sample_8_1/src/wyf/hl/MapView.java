package wyf.hl;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class MapView extends View{
	int span = 85;							//方格宽度
	int[][] map = MapList.map;				//地图数组			
	int row = map.length;					//地图的行数
	int col = map[0].length;				//地图的列数
	Paint paint = new Paint();				//画笔引用
	public MapView(Context context, AttributeSet attrs) {//构造器
		super(context, attrs);
	}
	protected void onDraw(Canvas canvas) {	//重写的绘制方法
		super.onDraw(canvas);				//实现父类方法
		canvas.drawColor(Color.GRAY);		//背景色
		paint.setColor(Color.BLACK);		//设置颜色
		paint.setStyle(Style.STROKE);		//设置风格
		canvas.drawRect(5, 5, 436, 436, paint);//绘制矩形
		for(int i=0; i<row; i++){			//绘制地图块
			for(int j=0; j<col; j++){
				switch(map[i][j]){			//得到地图数组中对应值
				case 0:						//当为0时，绘制该方格为白色
					paint.setColor(Color.WHITE);
					paint.setStyle(Style.FILL);
					canvas.drawRect(6+j*(span+1), 6+i*(span+1), 6+j*(span+1)+span, 6+i*(span+1)+span, paint);
					break;
				case 1:						//当为1时，绘制该方格为黑色
					paint.setColor(Color.BLACK);
					paint.setStyle(Style.FILL);
					canvas.drawRect(6+j*(span+1), 6+i*(span+1), 6+j*(span+1)+span, 6+i*(span+1)+span, paint);
					break;
				default:					//未来根据需要加入其它数据来控制地图的显示内容，这里只给出两种
					break;
				}
			}
		}
	}

	
	
	
}