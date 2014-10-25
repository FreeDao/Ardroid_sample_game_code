package com.baina.tower.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;

public class UpdateBitmap {

	 //����ͼƬ������
	 public static Bitmap liangDu(Bitmap bitmap,float f)
	 {
		 Bitmap show = bitmap; //�����ԭʼ��ͼƬ
		 int wi =show.getWidth(); //�õ����
		 int he =show.getHeight(); //�õ��߶�
		 Bitmap bmp =Bitmap.createBitmap(wi, he,Bitmap.Config.ARGB_8888); 
		 //����һ����ͬ�ߴ�Ŀɱ��λͼ��,���ڻ��Ƶ�ɫ���ͼƬ
		 Canvas canvas = new Canvas(bmp); //�õ����ʶ���
		 Paint paint = new Paint(); //�½�paint
		 paint.setAntiAlias(true); //���ÿ����,Ҳ���Ǳ�Ե��ƽ������
		 ColorMatrix cm1=new ColorMatrix(); //������ɫ�任�ľ���android λͼ��ɫ�仯������Ҫ�ǿ��ö������ 
		 ColorMatrix cm3=new ColorMatrix(); 
		 cm1.reset(); //��ΪĬ��ֵ

		 //f ��ʾ���ȱ�����ȡֵС��1����ʾ���ȼ���������������ǿ

		 cm3.reset(); 
		cm3.setScale(f, f, f, 1); //�졢�̡�������������ͬ�ı���,���һ������1��ʾ͸���Ȳ����仯���˺�����ϸ˵���ο� android doc
		 cm1.postConcat(cm3); //Ч������
		 paint.setColorFilter(new ColorMatrixColorFilter(cm1));//������ɫ�任Ч��
		 canvas.drawBitmap(show,0, 0, paint); //����ɫ�仯���ͼƬ������´�����λͼ��
		 return bmp; //�����µ�λͼ��Ҳ����ɫ������ͼƬ
	 }
}