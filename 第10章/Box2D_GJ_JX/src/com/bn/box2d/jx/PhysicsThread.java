package com.bn.box2d.jx;
import static com.bn.box2d.jx.Constant.*;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.joints.GearJoint;
import org.jbox2d.dynamics.joints.GearJointDef;
import org.jbox2d.dynamics.joints.PrismaticJoint;
import org.jbox2d.dynamics.joints.PrismaticJointDef;
import org.jbox2d.dynamics.joints.RevoluteJoint;
import org.jbox2d.dynamics.joints.RevoluteJointDef;

import android.graphics.Color;

public class PhysicsThread extends Thread
{
	RevoluteJoint rj2;
	RevoluteJoint rj3;
	// 捶打的移动关节
	PrismaticJoint pj1;
	//齿轮关节
	GearJoint gj1;
	GameView gv;
	public PhysicsThread(GameView gv)
	{
		this.gv=gv;
	}
	@Override
	public void run()
	{
		while(PHYSICS_THREAD_FLAG)
		{
			try
			{
				gv.activity.world.step(TIME_STEP, ITERA);//开始模拟
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
			float angle=gv.activity.rj_zuoce.getJointAngle();//使左侧的齿轮来回旋转
			if(angle<(float) (-0.6*ratio))
			{
				gv.activity.rj_zuoce.setMotorSpeed((float)(Math.PI/3));
			}
			else if(angle>(float) (0.6*ratio))
			{
				gv.activity.rj_zuoce.setMotorSpeed((float)(-Math.PI/3));
			}
			if(gv.activity.rj2!=null)
			{
				float angle_right=gv.activity.rj2.getJointAngle();//使右侧的齿轮来回旋转
				if(angle_right<(float) (-6.27*ratio))
				{
					zuigao=true;
					gv.activity.rj2.setMotorSpeed((float)(0));
				}
				else if(angle_right>(float)(4*ratio))
				{
					gv.activity.rj2.setMotorSpeed((float)(-Math.PI/2));
				}
			}

			if(rj2!=null)
			{
				float angle_right_new =rj2.getJointAngle();//使右侧的齿轮来回旋转
				if(angle_right_new>(float) (6.2*ratio))
				{
					zuidi=true;
					TuoPan2_shanchu=false;
				}
			}
			if(rj3!=null)
			{
				float angle_right_new =rj3.getJointAngle();//使右侧的齿轮来回旋转
				if(angle_right_new<(float) (-6.2*ratio))
				{
					zuigao=true;
					SHANCHU=false;
				}
			}
			
			if(zuigao&&!SHANCHU)
			{
				gv.activity.world.destroyBody(gv.activity.b2.get(0).body);
				gv.activity.b2.remove(0);
				zuigao=false;
				SHANCHU=true;
				if(rj3!=null)
				{
					rj3=null;
				}
				TuoPan tuopan = Box2DUtil.createTuoPan_fenkai((432+x)*ratio, (80+y)*ratio,
						new float[][][] {
								{ { (0+x)*ratio, (0+y)*ratio }, { (20+x)*ratio, (0+y)*ratio }, { (20+x)*ratio, (40+y)*ratio }, { (0+x)*ratio, (40+y)*ratio }, },
								{ { (20+x)*ratio, (20+y)*ratio }, { (60+x)*ratio, (20+y)*ratio }, { (60+x)*ratio, (40+y)*ratio }, { (20+x)*ratio, (40+y)*ratio }, },
								{ { (60+x)*ratio, (0+y)*ratio }, { (80+x)*ratio, (0+y)*ratio }, { (80+x)*ratio, (40+y)*ratio }, { (60+x)*ratio, (40+y)*ratio } },
								},
						new float[][] { { (0+x)*ratio, (0+y)*ratio }, { (20+x)*ratio, (0+y)*ratio }, { (20+x)*ratio, (20+y)*ratio }, { (60+x)*ratio, (20+y)*ratio },
								{ (60+x)*ratio, (0+y)*ratio }, { (80+x)*ratio, (0+y)*ratio }, { (80+x)*ratio, (40+y)*ratio }, 
								{ (0+x)*ratio, (40+y)*ratio },
								{ (0+x)*ratio, (0+y)*ratio } }, false, gv.activity.world, 0xff0000ff);
				gv.activity.b2.add(tuopan);
				
				MyPolygonColor zhijia = Box2DUtil.createPolygon((461.5f+x)*ratio,
						(110+y)*ratio, new float[][] { { (0+x)*ratio, (0+y)*ratio },
								{ (kd+x)*ratio, (0+y)*ratio }, { (kd+x)*ratio, (960+y)*ratio },
								{ (0+x)*ratio, (960+y)*ratio } }, true, gv.activity.world, 0xff0000ff,
						0f);
				gv.activity.b2.add(zhijia);
				// 旋转关节
				RevoluteJointDef rjd_xinzeng = new RevoluteJointDef();
				rjd_xinzeng.initialize(zhijia.body, tuopan.body, new Vec2(zhijia.body.getPosition().x+kd/2/RATE,zhijia.body.getPosition().y));
				rjd_xinzeng.lowerAngle = (float) (-1.8*ratio);
				rjd_xinzeng.upperAngle = (float) (0.01*ratio);
				rjd_xinzeng.enableLimit = true;
				rjd_xinzeng.maxMotorTorque = 100000;
				rjd_xinzeng.motorSpeed = (float) (-Math.PI / 2);
				rjd_xinzeng.enableMotor = true;
				gv.activity.rj3 = (RevoluteJoint) gv.activity.world.createJoint(rjd_xinzeng);
			}


			if(TuoPan_ZhuanHui&&!TuoPan1_shanchu)
			{
				gv.activity.world.destroyBody(gv.activity.b2.get(0).body);
				gv.activity.b2.remove(0);
				gv.activity.world.destroyBody(gv.activity.b2.get(0).body);
				gv.activity.b2.remove(0);
				
				gv.activity.world.destroyBody(gv.activity.b3.get(0).body);
				gv.activity.b3.remove(0);
				gv.activity.world.destroyBody(gv.activity.b3.get(0).body);
				gv.activity.b3.remove(0);
				
				TuoPan_ZhuanHui=false;
				TuoPan1_shanchu=true;
				gv.activity.rj3=null;
				if(gv.activity.rj2!=null)
				{
					gv.activity.rj2=null;
				}
				
				TuoPan tuopan = Box2DUtil.createTuoPan((432f+x)*ratio,
						(80f+y)*ratio, new float[][][] {
								{ { (0+x)*ratio, (0+y)*ratio }, { (20+x)*ratio, (0+y)*ratio }, { (20+x)*ratio, (40+y)*ratio }, { (0+x)*ratio, (40+y)*ratio }, },
								{ { (20+x)*ratio, (20+y)*ratio }, { (60+x)*ratio, (20+y)*ratio }, { (60+x)*ratio, (40+y)*ratio }, { (20+x)*ratio, (40+y)*ratio }, },
								{ { (60+x)*ratio, (0+y)*ratio }, { (80+x)*ratio, (0+y)*ratio }, { (80+x)*ratio, (40+y)*ratio }, { (60+x)*ratio, (40+y)*ratio } },
								{ { (30+x)*ratio, (40+y)*ratio }, { (50+x)*ratio, (40+y)*ratio }, { (50+x)*ratio, (140+y)*ratio }, { (30+x)*ratio, (140+y)*ratio } }
								},
						new float[][] { { (0+x)*ratio, (0+y)*ratio }, { (20+x)*ratio, (0+y)*ratio }, { (20+x)*ratio, (20+y)*ratio }, { (60+x)*ratio, (20+y)*ratio },
								{ (60+x)*ratio, (0+y)*ratio }, { (80+x)*ratio, (0+y)*ratio }, { (80+x)*ratio, (40+y)*ratio }, 
								{ (50+x)*ratio, (40+y)*ratio },{ (50+x)*ratio, (1000+y)*ratio }, { (30+x)*ratio, (1000+y)*ratio }, { (30+x)*ratio, (40+y)*ratio }, 
								{ (0+x)*ratio, (40+y)*ratio },
								{ (0+x)*ratio, (0+y)*ratio } }, false, gv.activity.world, 0xff0000ff);
				gv.activity.b2.add(tuopan);
				
				
				
				// 创建齿轮固定矩形
				MyPolygonColor clt1 = Box2DUtil.createPolygon((421.8F+x)*ratio,
						(880+y)*ratio, new float[][] { { (0+x)*ratio, (0+y)*ratio }, { (kd+x)*ratio, (0+y)*ratio },
								{ (kd+x)*ratio, (kd+y)*ratio }, { (0+x)*ratio, (kd+y)*ratio } }, true, gv.activity.world, 0xff0000ff, 0f);
				gv.activity.b3.add(clt1);
				// 创建左侧齿轮
				MyCircleColor cl1 = Box2DUtil.createCircle((432.8f+x)*ratio, (890+y)*ratio, 1.5f * kd*ratio, gv.activity.world,
						Color.MAGENTA, false);
				gv.activity.b3.add(cl1);
				
				// 创建左侧齿轮的旋转关节
				RevoluteJointDef rjd2 = new RevoluteJointDef();
				rjd2.initialize(clt1.body, cl1.body, cl1.body.getWorldCenter());
				rjd2.lowerAngle = (float) (-6.28*ratio);
				rjd2.upperAngle = (float) (6.28*ratio);
				rjd2.enableLimit = true;
				rjd2.maxMotorTorque = 100000;
				rjd2.motorSpeed = (float) (Math.PI / 3.4);
				rjd2.enableMotor = true;
				rj2 = (RevoluteJoint) gv.activity.world.createJoint(rjd2);
				
				

				// 移动关节
				PrismaticJointDef pjd = new PrismaticJointDef();
				pjd.initialize(gv.activity.bl.get(5).body, tuopan.body, gv.activity.bl.get(5).body.getWorldCenter(),
						new Vec2(0, 1));
				pj1 = (PrismaticJoint) gv.activity.world.createJoint(pjd);

				// 创建左侧的齿轮关节
				GearJointDef gjd = new GearJointDef();
				gjd.body1 = gv.activity.b3.get(1).body;
				gjd.body2 = tuopan.body;
				gjd.joint1 = rj2;
				gjd.joint2 = pj1;
				gjd.ratio = (-0.35f / (2.0f * kd) * RATE);
				gj1=(GearJoint) gv.activity.world.createJoint(gjd);
			}
			if(zuidi&&!TuoPan2_shanchu&&rj3==null)
			{
				zuidi=false;
				TuoPan2_shanchu=true;
				gv.activity.world.destroyBody(gv.activity.b2.get(0).body);
				gv.activity.b2.remove(0);
				gv.activity.world.destroyBody(gv.activity.b3.get(0).body);
				gv.activity.b3.remove(0);
				gv.activity.world.destroyBody(gv.activity.b3.get(0).body);
				gv.activity.b3.remove(0);
				rj2=null;
				
				TuoPan tuopan = Box2DUtil.createTuoPan((432+x)*ratio, (800+y)*ratio,
						new float[][][] {
								{ { (0+x)*ratio, (0+y)*ratio }, { (20+x)*ratio, (0+y)*ratio }, { (20+x)*ratio, (40+y)*ratio }, { (0+x)*ratio, (40+y)*ratio }, },
								{ { (20+x)*ratio, (20+y)*ratio }, { (60+x)*ratio, (20+y)*ratio }, { (60+x)*ratio, (40+y)*ratio }, { (20+x)*ratio, (40+y)*ratio }, },
								{ { (60+x)*ratio, (0+y)*ratio }, { (80+x)*ratio, (0+y)*ratio }, { (80+x)*ratio, (40+y)*ratio }, { (60+x)*ratio, (40+y)*ratio } },
								{ { (30+x)*ratio, (40+y)*ratio }, { (50+x)*ratio, (40+y)*ratio }, { (50+x)*ratio, (140+y)*ratio }, { (30+x)*ratio, (140+y)*ratio } } },
						new float[][] { { (0+x)*ratio, (0+y)*ratio }, { (20+x)*ratio, (0+y)*ratio }, { (20+x)*ratio, (20+y)*ratio }, { (60+x)*ratio, (20+y)*ratio },
								{ (60+x)*ratio, (0+y)*ratio }, { (80+x)*ratio, (0+y)*ratio }, { (80+x)*ratio, (40+y)*ratio }, { (50+x)*ratio, (40+y)*ratio },
								{ (50+x)*ratio, (1000+y)*ratio }, { (30+x)*ratio, (1000+y)*ratio }, { (30+x)*ratio, (40+y)*ratio }, { (0+x)*ratio, (40+y)*ratio },
								{ (0+x)*ratio, (0+y)*ratio } }, false, gv.activity.world, 0xff0000ff);
				gv.activity.b2.add(tuopan);
				// 创建齿轮固定矩形
				MyPolygonColor clt1 = Box2DUtil.createPolygon((421.8f+x)*ratio, (880+y)*ratio
						, new float[][] { { (0+x)*ratio, (0+y)*ratio }, { (kd+x)*ratio, (0+y)*ratio },
								{ (kd+x)*ratio, (kd+y)*ratio }, { (0+x)*ratio, (kd+y)*ratio } }, true, gv.activity.world, 0xff0000ff, 0f);
				gv.activity.b3.add(clt1);
				// 创建左侧齿轮
				MyCircleColor cl1 = Box2DUtil.createCircle((432.8f+x)*ratio, (890+y)*ratio, 1.5f * kd*ratio, gv.activity.world,
						Color.MAGENTA, false);
				gv.activity.b3.add(cl1);
				
				
				
				
				// 创建左侧齿轮的旋转关节
				RevoluteJointDef rjd2 = new RevoluteJointDef();
				rjd2.initialize(clt1.body, cl1.body, cl1.body.getWorldCenter());
				rjd2.lowerAngle = (float) (-6.28*ratio);
				rjd2.upperAngle = (float) (6.28*ratio);
				rjd2.enableLimit = true;
				rjd2.maxMotorTorque = 100000;
				rjd2.motorSpeed = (float) (-Math.PI / 3);
				rjd2.enableMotor = true;
				rj3 = (RevoluteJoint) gv.activity.world.createJoint(rjd2);
				
				

				// 移动关节
				PrismaticJointDef pjd = new PrismaticJointDef();
				pjd.initialize(gv.activity.bl.get(5).body, tuopan.body, gv.activity.bl.get(5).body.getWorldCenter(),
						new Vec2(0, 1));
				pj1 = (PrismaticJoint) gv.activity.world.createJoint(pjd);

				// 创建左侧的齿轮关节
				GearJointDef gjd = new GearJointDef();
				gjd.body1 = gv.activity.b3.get(1).body;
				gjd.body2 = tuopan.body;
				gjd.joint1 = rj3;
				gjd.joint2 = pj1;
				gjd.ratio = (-0.35f / (2.0f * kd) * RATE);
				gj1=(GearJoint) gv.activity.world.createJoint(gjd);
				MyCircleColor ball=Box2DUtil.createCircle
		        (gv.activity.bl.get(5).body.getWorldCenter().x*RATE, (770+y)*ratio, kd*ratio, gv.activity.world,Color.RED,false);
				gv.activity.world.destroyBody(gv.activity.bl.get(6).body);
				gv.activity.bl.remove(6);
		        gv.activity.bl.add(ball); 
				
			}
		}
	}
}
