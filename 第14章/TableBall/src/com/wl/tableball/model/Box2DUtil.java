package com.wl.tableball.model;

import org.jbox2d.collision.CircleDef;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;
import android.graphics.Bitmap;
import com.wl.tableball.game.GameView;
import com.wl.tableball.util.Constant;

public class Box2DUtil {

	public static Ball createBall(World world,float x,float y,float radius,boolean isStatic,Bitmap bitmap,GameView gameview)
	{
		CircleDef ballshapdef=new CircleDef();
		if(isStatic)
		{
			ballshapdef.density=0;
		}
		else
		{
			ballshapdef.density=1.0f;//密度
		}
		
		ballshapdef.friction=0f;//摩擦力
		ballshapdef.restitution=Constant.ball_restitution;//反贪补偿
		ballshapdef.radius=radius-Constant.BODYOFF;//半径
		
		BodyDef bodydef=new BodyDef();//初始化body
		Vec2 v=new Vec2(x,y);//圆心坐标
		bodydef.position.set(v);//添加
		
		Body body=world.createBody(bodydef);
		body.createShape(ballshapdef);
		
		//设了一下球的初速度
		body.setMassFromShapes();//设置质量
		return new Ball(body,bitmap,gameview);//返回MyBall
	}
	
	public static Hole createHole(World world,float x,float y,float radius,boolean isStatic,Bitmap bitmap,GameView gameview)
	{
		CircleDef ballshapdef=new CircleDef();
		if(isStatic)
		{
			ballshapdef.density=0;
		}
		else
		{
			ballshapdef.density=1.0f;//密度
		}
		
		ballshapdef.friction=0f;//摩擦力
		ballshapdef.restitution=Constant.ball_restitution;//反贪补偿
		ballshapdef.radius=radius-Constant.BODYOFF;//半径
		
		BodyDef bodydef=new BodyDef();//初始化body
		Vec2 v=new Vec2(x,y);//圆心坐标
		bodydef.position.set(v);//添加
		
		Body body=world.createBody(bodydef);
		body.createShape(ballshapdef);
		
		//设了一下球的初速度
		body.setMassFromShapes();//设置质量
		return new Hole(body,bitmap,gameview);//返回MyBall
	}
	
	public static Rec creatRec(World world,float x,float y,float halfweight,float halfHeight,boolean isStatic ,Bitmap bitmap,GameView gameview)
	{
		PolygonDef recshap=new PolygonDef();//多边形
		recshap.setAsBox(halfweight+Constant.BODYOFF, halfHeight+Constant.BODYOFF);//矩形
		if(isStatic)
		{
			recshap.density=0;
		}
		else
		{
			recshap.density=1.0f;//密度
		}
		recshap.friction=0f;//摩擦
		recshap.restitution=0.2f;
		
		BodyDef bodydef=new BodyDef();
		Vec2 v=new Vec2(x,y);
		bodydef.position.set(v);//中心坐标
		
		Body body=world.createBody(bodydef);
		body.createShape(recshap);
		body.setMassFromShapes();
		
		return new Rec(body,bitmap,gameview);//返回MyRec
	}
	
	//创建FalshHole对象，最后一个参数是它隔多长时间进行闪一下
	public static FalshHole createFalshHole
			(
				World world,float x,float y,float radius,boolean isStatic,Bitmap bitmap,
				int timeDeadSpan,//洞不起作用的时间
				int timeLiveSpan,//洞起作用的时间
				GameView gameview
			)
	{
		CircleDef ballshapdef=new CircleDef();
		if(isStatic)
		{
			ballshapdef.density=0;
		}
		else
		{
			ballshapdef.density=1.0f;//密度
		}
		
		ballshapdef.friction=0f;//摩擦力
		ballshapdef.restitution=Constant.ball_restitution;//反贪补偿
		ballshapdef.radius=radius-Constant.BODYOFF;//半径
		ballshapdef.isSensor=true;//这样球可以穿过
		
		BodyDef bodydef=new BodyDef();//初始化body
		Vec2 v=new Vec2(x,y);//圆心坐标
		bodydef.position.set(v);//添加
		
		Body body=world.createBody(bodydef);
		body.createShape(ballshapdef);
		body.setMassFromShapes();//设置质量
		return new FalshHole(body,bitmap,timeDeadSpan,timeLiveSpan,gameview);//返回MyBall
	}
	
	
}
