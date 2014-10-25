package com.bn.box2d.jx;

import java.util.ArrayList;
import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.joints.GearJoint;
import org.jbox2d.dynamics.joints.GearJointDef;
import org.jbox2d.dynamics.joints.PrismaticJoint;
import org.jbox2d.dynamics.joints.PrismaticJointDef;
import org.jbox2d.dynamics.joints.PulleyJointDef;
import org.jbox2d.dynamics.joints.RevoluteJoint;
import org.jbox2d.dynamics.joints.RevoluteJointDef;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import static com.bn.box2d.jx.Constant.*;

public class MyBox2dActivity extends Activity {
	AABB worldAABB;// ���� һ��������ײ������
	World world;
	// �����б�
	ArrayList<MyBody> bl = new ArrayList<MyBody>();
	// �����б�2 �������
	ArrayList<MyBody> b2 = new ArrayList<MyBody>();
	// �����б�2 �������
	ArrayList<MyBody> b3 = new ArrayList<MyBody>();
	// �����б�2 �������
	ArrayList<MyBody> b4 = new ArrayList<MyBody>();
	// �����б�2 �������
	ArrayList<MyBody> b5 = new ArrayList<MyBody>();
	// �����ת�ؽ�
	RevoluteJoint rj1;
	// �Ҳ���ֵ���ת�ؽ�
	RevoluteJoint rj2;
	// �ϲ���ֵ���ת�ؽ�
	RevoluteJoint rj3;
	// �ϲ���ֵ���ת�ؽ�
	RevoluteJoint rj_zuoce;
	
	// ������ƶ��ؽ�
	PrismaticJoint pj1;
	// ������ƶ��ؽ�
	PrismaticJoint pj2;
	// ������ƶ��ؽ�
	PrismaticJoint pj3;
	//���ֹؽ�
	GearJoint gj1;

	MyCircleColor ball;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ����Ϊȫ��
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// ����Ϊ����ģʽ
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// ��ȡ��Ļ�ߴ�
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		if (dm.widthPixels < dm.heightPixels) {
			SCREEN_WIDTH = dm.widthPixels;
			SCREEN_HEIGHT = dm.heightPixels;
		} else {
			SCREEN_WIDTH = dm.heightPixels;
			SCREEN_HEIGHT = dm.widthPixels;
		}
		Constant.ScaleSR();

		worldAABB = new AABB();

		// ���½磬����Ļ�����Ϸ�Ϊ ԭ�㣬��������ĸ��嵽����Ļ�ı�Ե�Ļ�����ֹͣģ��
		worldAABB.lowerBound.set(-100.0f, -100.0f);
		worldAABB.upperBound.set(100.0f, 100.0f);// ע������ʹ�õ�����ʵ����ĵ�λ

		Vec2 gravity = new Vec2(0.0f, 0.0f);
		boolean doSleep = true;
		// ��������
		world = new World(worldAABB, gravity, doSleep);

		MyPolygonColor guidao1 = Box2DUtil.createPolygon((150+x)*ratio,//���1
				(280+y)*ratio, new float[][] { { (0+x)*ratio, (0+y)*ratio },
						{ (300+x)*ratio, (0+y)*ratio }, { (300+x)*ratio, (kd+y)*ratio },
						{ (0+x)*ratio, (kd+y)*ratio } }, true, world, 0xff0000ff,
				-(float) (Math.PI / 7));
		bl.add(guidao1);

		MyPolygonColor guidao2 = Box2DUtil.createPolygon((41+x)*ratio,//���2
				(300+y)*ratio, new float[][] { { (0+x)*ratio, (0+y)*ratio },
						{ (220+x)*ratio, (0+y)*ratio }, { (220+x)*ratio, (kd+y)*ratio },
						{ (0+x)*ratio, (kd+y)*ratio } }, true, world, 0xff0000ff,
				(float) (Math.PI / 4));
		bl.add(guidao2);

		MyPolygonColor guidao3 = Box2DUtil.createPolygon((240+x)*ratio,//���3
				(375f+y)*ratio, new float[][] { {  (0+x)*ratio, (0+y)*ratio },
						{ (20+x)*ratio, (0+y)*ratio}, { (20+x)*ratio, (220+y)*ratio },
						{ (0+x)*ratio, (220+y)*ratio} }, false, world, 0xff0000ff,
				-(float) (0));
		bl.add(guidao3);
		
		MyPolygonColor guidao3_zhongxin = Box2DUtil.createPolygon((240+x)*ratio,//���3����
				(0f+y)*ratio, new float[][] { {(0+x)*ratio, (0+y)*ratio },
						{ (20+x)*ratio, (0+y)*ratio }, { (20+x)*ratio, (kd+y)*ratio },
						{ (0+x)*ratio, (kd+y)*ratio } }, true, world, 0xff0000ff,
				-(float) (0));
		b4.add(guidao3_zhongxin);
		
		MyPolygonColor xuangua_fangkuai = Box2DUtil.createPolygon((380+x)*ratio,
				(475f+y)*ratio, new float[][] { {(0+x)*ratio, (0+y)*ratio },
						{ (40+x)*ratio, (0+y)*ratio }, { (40+x)*ratio, (40+y)*ratio },
						{ (0+x)*ratio, (40+y)*ratio } }, false, world, 0xff0000ff,
				-(float) (0));
		b4.add(xuangua_fangkuai);
		
		MyPolygonColor xuangua_fangkuai_zuo = Box2DUtil.createPolygon((378+x)*ratio,//���3����
				(275f+y)*ratio, new float[][] { { (0+x)*ratio, (0+y)*ratio },
						{ (2+x)*ratio, (0+y)*ratio }, { (2+x)*ratio, (400+y)*ratio },
						{ (0+x)*ratio, (400+y)*ratio } }, true, world, 0xff0000ff,
				-(float) (0));
		b4.add(xuangua_fangkuai_zuo);
		MyPolygonColor xuangua_fangkuai_you = Box2DUtil.createPolygon((420+x)*ratio,//���3����
				(275f+y)*ratio, new float[][] { { (0+x)*ratio, (0+y)*ratio },
						{ (2+x)*ratio, (0+y)*ratio }, { (2+x)*ratio, (400+y)*ratio },
						{ (0+x)*ratio, (400+y)*ratio } }, true, world, 0xff0000ff,
				-(float) (0));
		b4.add(xuangua_fangkuai_you);
		PrismaticJointDef pjd_shangxia = new PrismaticJointDef();
		pjd_shangxia.initialize(guidao3_zhongxin.body, guidao3.body, guidao3.body.getWorldCenter(),
				new Vec2(0, 1));
		pj2 = (PrismaticJoint) world.createJoint(pjd_shangxia);
		
		// �������̶ֹ�����
		MyPolygonColor clt_zuoce = Box2DUtil.createPolygon((280F+x)*ratio,
				(500+y)*ratio, new float[][] { { (0+x)*ratio, (0+y)*ratio }, { (kd+x)*ratio, (0+y)*ratio },
						{ (kd+x)*ratio, (kd+y)*ratio }, { (0+x)*ratio, (kd+y)*ratio } }, true, world, 0xff0000ff, 0f);
		b4.add(clt_zuoce);
		// ����������
		MyCircleColor cl_zuoce = Box2DUtil.createCircle((290+2*x)*ratio, (510+2*y)*ratio, 1.5f * kd*ratio, world,
				Color.MAGENTA, false);
		b4.add(cl_zuoce);
		
		// ��ת�ؽ�
		RevoluteJointDef rjd_zuoce = new RevoluteJointDef();
		rjd_zuoce.initialize(clt_zuoce.body, cl_zuoce.body, cl_zuoce.body.getWorldCenter());
		rjd_zuoce.lowerAngle = (float) (-1);
		rjd_zuoce.upperAngle = (float) (1);
		rjd_zuoce.enableLimit = true;
		rjd_zuoce.maxMotorTorque = 100000;
		rjd_zuoce.motorSpeed = (float) (-Math.PI / 3);
		rjd_zuoce.enableMotor = true;
		rj_zuoce = (RevoluteJoint) world.createJoint(rjd_zuoce);
		
		// �������ĳ��ֹؽ�
		GearJointDef gjd_zuoce = new GearJointDef();
		gjd_zuoce.body1 = cl_zuoce.body;
		gjd_zuoce.body2 = guidao3.body;
		gjd_zuoce.joint1 = rj_zuoce;
		gjd_zuoce.joint2 = pj2;
		gjd_zuoce.ratio = (0.35f/(2.0f*kd)*RATE);
		world.createJoint(gjd_zuoce);
		
	    PulleyJointDef pjd2=new PulleyJointDef();
	    pjd2.initialize
	    (
	      guidao3.body,
	      xuangua_fangkuai.body,
	      new Vec2((250+x)*ratio, (700+y)*ratio), 
	      new Vec2((400+x)*ratio, (700+y)*ratio), 
	      guidao3.body.getWorldCenter(), 
	      xuangua_fangkuai.body.getWorldCenter(),
	      1f
	    );
	    world.createJoint(pjd2);
		MyPolygonColor xuangua_fangkuai_zhongxin = Box2DUtil.createPolygon((420+x)*ratio,//���3����
				(275f+y)*ratio, new float[][] { { (0+x)*ratio, (0+y)*ratio},
						{ (2+x)*ratio, (0+y)*ratio }, { (2+x)*ratio, (400+y)*ratio },
						{ (0+x)*ratio, (400+y)*ratio } }, true, world, 0xff0000ff,
				-(float) (0));
		b4.add(xuangua_fangkuai_zhongxin);
		PrismaticJointDef pjd_shangxia1 = new PrismaticJointDef();
		pjd_shangxia1.initialize(xuangua_fangkuai_zhongxin.body, xuangua_fangkuai.body, xuangua_fangkuai_zhongxin.body.getWorldCenter(),
				new Vec2(0, 1));
		pj3 = (PrismaticJoint) world.createJoint(pjd_shangxia1);
		GearJointDef gjd_zhongjian = new GearJointDef();
		gjd_zhongjian.body1 = cl_zuoce.body;
		gjd_zhongjian.body2 = xuangua_fangkuai.body;
		gjd_zhongjian.joint1 = rj_zuoce;
		gjd_zhongjian.joint2 = pj3;
		gjd_zhongjian.ratio = (-0.35f/(2.0f*kd)*RATE);
		world.createJoint(gjd_zhongjian);

		MyPolygonColor guidao4 = Box2DUtil.createPolygon((41+x)*ratio,//���4
				(582f+y)*ratio, new float[][] { { (0+x)*ratio, (0+y)*ratio },
						{ (290+x)*ratio, (0+y)*ratio }, { (290+x)*ratio, (kd+y)*ratio },
						{ (0+x)*ratio, (kd+y)*ratio } }, true, world, 0xff0000ff,
				(float) (Math.PI / 5));
		bl.add(guidao4);
		MyPolygonColor guidao5 = Box2DUtil.createPolygon((270+x)*ratio, (750+y)*ratio//���5
				, new float[][] { { (0+x)*ratio, (0+y)*ratio },
						{ (154+x)*ratio, (0+y)*ratio },
						{ (154+x)*ratio, (kd+y)*ratio }, { (0+x)*ratio, (kd+y)*ratio } }, true, world,
				0xff0000ff, 0f);
		bl.add(guidao5);


		TuoPan tuopan = Box2DUtil.createTuoPan((432f+x)*ratio,//����
				(800f+y)*ratio, new float[][][] {
						{ { (0+x)*ratio, (0+y)*ratio }, { (20+x)*ratio, (0+y)*ratio }, { (20+x)*ratio, (40+y)*ratio }, { (0+x)*ratio, (40+y)*ratio }, },
						{ { (20+x)*ratio, (20+y)*ratio }, { (60+x)*ratio, (20+y)*ratio }, { (60+x)*ratio, (40+y)*ratio }, { (20+x)*ratio, (40+y)*ratio }, },
						{ { (60+x)*ratio, (0+y)*ratio }, { (80+x)*ratio, (0+y)*ratio }, { (80+x)*ratio, (40+y)*ratio }, { (60+x)*ratio, (40+y)*ratio } },
						{ { (30+x)*ratio, (40+y)*ratio }, { (50+x)*ratio, (40+y)*ratio }, { (50+x)*ratio, (140+y)*ratio }, { (30+x)*ratio, (140+y)*ratio } } },
				new float[][] { { (0+x)*ratio, (0+y)*ratio }, { (20+x)*ratio, (0+y)*ratio }, { (20+x)*ratio, (20+y)*ratio }, { (60+x)*ratio, (20+y)*ratio },
						{ (60+x)*ratio, (0+y)*ratio }, { (80+x)*ratio, (0+y)*ratio }, { (80+x)*ratio, (40+y)*ratio }, { (50+x)*ratio, (40+y)*ratio },
						{ (50+x)*ratio, (1000+y)*ratio }, { (30+x)*ratio, (1000+y)*ratio }, { (30+x)*ratio, (40+y)*ratio }, { (0+x)*ratio, (40+y)*ratio },
						{ (0+x)*ratio, (0+y)*ratio } }, false, world, 0xff0000ff);
		b2.add(tuopan);

		// �������̶ֹ�����
		MyPolygonColor clt1 = Box2DUtil.createPolygon((421.8F+x)*ratio,
				(880+y)*ratio, new float[][] { { (0+x)*ratio, (0+y)*ratio }, { (kd+x)*ratio, (0+y)*ratio },
						{ (kd+x)*ratio, (kd+y)*ratio }, { (0+x)*ratio, (kd+y)*ratio } }, true, world, 0xff0000ff, 0f);
		b3.add(clt1);
		// ����������
		MyCircleColor cl1 = Box2DUtil.createCircle((432.8f+x)*ratio, (890+y)*ratio, 1.5f * kd*ratio, world,
				Color.MAGENTA, false);
		b3.add(cl1);

		// ���������ֵ���ת�ؽ�
		RevoluteJointDef rjd2 = new RevoluteJointDef();
		rjd2.initialize(clt1.body, cl1.body, cl1.body.getWorldCenter());
		rjd2.lowerAngle = (float) (-6.28);
		rjd2.upperAngle = (float) (6.28);
		rjd2.enableLimit = true;
		rjd2.maxMotorTorque = 100000;
		rjd2.motorSpeed = (float) (-Math.PI / 3);
		rjd2.enableMotor = true;
		rj2 = (RevoluteJoint) world.createJoint(rjd2);

		// ��ֱ����̶�����
		MyPolygonColor clt2 = Box2DUtil.createPolygon(
				(461.5f+x)*ratio, (0+y)*ratio,
				new float[][] { { (0+x)*ratio, (0+y)*ratio }, { (kd+x)*ratio, (0+y)*ratio }, { (kd+x)*ratio, (kd+y)*ratio }, { (0+x)*ratio, (kd+y)*ratio } },
				true, world, 0xff0000ff, 0f);
		bl.add(clt2);
		// �ƶ��ؽ�
		PrismaticJointDef pjd = new PrismaticJointDef();
		pjd.initialize(clt2.body, tuopan.body, clt2.body.getWorldCenter(),
				new Vec2(0, 1));
		pj1 = (PrismaticJoint) world.createJoint(pjd);

		// �����Ҳ�ĳ��ֹؽ�
		GearJointDef gjd = new GearJointDef();
		gjd.body1 = cl1.body;
		gjd.body2 = clt2.body;
		gjd.joint1 = rj2;
		gjd.joint2 = pj1;
		gjd.ratio = (-0.35f / (2.0f * kd) * RATE);
		gj1=(GearJoint) world.createJoint(gjd);
		
        ball=Box2DUtil.createCircle
        (clt2.body.getWorldCenter().x*RATE, (770+y)*ratio, kd*ratio, world,Color.RED,false);
        bl.add(ball); 
		
		GameView gv = new GameView(this);
		setContentView(gv);
	}
}
