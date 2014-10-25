package com.bn.box2d.jx;
import org.jbox2d.collision.CircleDef;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

import static com.bn.box2d.jx.Constant.*;

//����������״�Ĺ�����
public class Box2DUtil 
{
	//�������������(��ɫ)
	public static MyPolygonColor createPolygon
	(  
		float x,//x����
		float y,//y����
	    float[][] points,//������
        boolean isStatic,//�Ƿ�Ϊ��ֹ��
        World world,//����
        int color,//��ɫ
        float angle
    )
	{    
		//�����������������
		PolygonDef shape = new PolygonDef();   
		//�����ܶ�
		if(isStatic)
		{
			shape.density = 0;
		}   
		else
		{
			shape.density = 1f;
		}   
		//����Ħ��ϵ��
		shape.friction = 0.3f;   
		//����������ʧ�ʣ�������
		shape.restitution = 0f;   

		for(float[] fa:points)
		{
			shape.addVertex(new Vec2(fa[0]/RATE,fa[1]/RATE));
		}
		
		//����������������   
		BodyDef bodyDef = new BodyDef();   
		//����λ��
		bodyDef.position.set(x/RATE, y/RATE);  
		bodyDef.angle=angle;
		
		//�������д�������
		Body bodyTemp= world.createBody(bodyDef); 
		//ָ��������״
		bodyTemp.createShape(shape);   
		bodyTemp.setMassFromShapes(); 
		
		return new MyPolygonColor(bodyTemp,color,points);
	}   

	//����Բ�Σ���ɫ��
	public static MyCircleColor createCircle
	(
		float x,//x����
		float y,//y����
		float radius,//�뾶
		World world,//����
		int color,//��ɫ
		boolean isStatic//�Ƿ�Ϊ��ֹ��
	)
	{   
		//����Բ��������
		CircleDef shape = new CircleDef();  
		//�����ܶ�
		if(isStatic)
		{
			shape.density = 0;
		}   
		else
		{
			shape.density = 1f;
		}  		
		//����Ħ��ϵ��
		shape.friction = 0.3f;   
		//����������ʧ�ʣ�������
		shape.restitution = 0f;   
		//���ð뾶
		shape.radius = radius/RATE;   
		
		//����������������   
		BodyDef bodyDef = new BodyDef(); 
		//����λ��
		bodyDef.position.set(x/RATE, y/RATE);   
		//�������д�������
		Body bodyTemp = world.createBody(bodyDef); 
		//ָ��������״
		bodyTemp.createShape(shape);   
		bodyTemp.setMassFromShapes();  		
		return new MyCircleColor(bodyTemp,radius,color);
	} 
	//�������������(��ɫ)
	public static TuoPan createTuoPan
	(  
		float x,//x����
		float y,//y����
	    float[][][] recpoints,//�ֲ�ľ�������
	    float[][] pathpoints,//·��������
        boolean isStatic,//�Ƿ�Ϊ��ֹ��
        World world,//����
        int color//��ɫ
    )
	{    
		//�����������������
		PolygonDef shape = new PolygonDef();   
		//�����ܶ�
		if(isStatic)
		{
			shape.density = 0;
		}   
		else
		{
			shape.density = 1.0f;
		}   
		//����Ħ��ϵ��
		shape.friction = 0.0f;   
		//����������ʧ�ʣ�������
		shape.restitution = 0f;   
		float points[][] = new float[4][2];
		points=recpoints[0];
		for(float[] fa:points)
		{
			shape.addVertex(new Vec2(fa[0]/RATE,fa[1]/RATE));
		}
		//����������������   
		BodyDef bodyDef = new BodyDef();   
		//����λ��
		bodyDef.position.set(x/RATE, y/RATE);  
		
		//�������д�������
		Body bodyTemp= world.createBody(bodyDef); 
		//ָ��������״
		bodyTemp.createShape(shape);  
		
		//�����������������
		PolygonDef shape1 = new PolygonDef();   //�ڶ���shape
		//�����ܶ�
		if(isStatic)
		{
			shape1.density = 0;
		}   
		else
		{
			shape1.density = 1.0f;
		}   
		//����Ħ��ϵ��
		shape1.friction = 0.0f;   
		//����������ʧ�ʣ�������
		shape1.restitution = 0f;   
		points=recpoints[1];
		for(float[] fa:points)
		{
			shape1.addVertex(new Vec2(fa[0]/RATE,fa[1]/RATE));
		}		
		//ָ��������״
		bodyTemp.createShape(shape1); 
		
		//�����������������
		PolygonDef shape2 = new PolygonDef();   //������shape
		//�����ܶ�
		if(isStatic)
		{
			shape2.density = 0;
		}   
		else
		{
			shape2.density = 1.0f;
		}   
		//����Ħ��ϵ��
		shape2.friction = 0.0f;   
		//����������ʧ�ʣ�������
		shape2.restitution = 0f;   
		points=recpoints[2];
		for(float[] fa:points)
		{
			shape2.addVertex(new Vec2(fa[0]/RATE,fa[1]/RATE));
		}		
		//ָ��������״
		bodyTemp.createShape(shape2); 
		
		
		//�����������������
		PolygonDef shape3 = new PolygonDef();   //���ĸ�shape
		//�����ܶ�
		if(isStatic)
		{
			shape3.density = 0;
		}   
		else
		{
			shape3.density = 1.0f;
		}   
		//����Ħ��ϵ��
		shape3.friction = 0.0f;   
		//����������ʧ�ʣ�������
		shape3.restitution = 0f;   
		points=recpoints[3];
		for(float[] fa:points)
		{
			shape3.addVertex(new Vec2(fa[0]/RATE,fa[1]/RATE));
		}		
		//ָ��������״
		bodyTemp.createShape(shape3); 
		
		bodyTemp.setMassFromShapes(); 
		
		return new TuoPan(bodyTemp,color,pathpoints);
	} 
	//�������������(��ɫ)
	public static TuoPan createTuoPan_fenkai
	(  
		float x,//x����
		float y,//y����
	    float[][][] recpoints,//�ֲ�ľ�������
	    float[][] pathpoints,//·��������
        boolean isStatic,//�Ƿ�Ϊ��ֹ��
        World world,//����
        int color//��ɫ
    )
	{    
		//�����������������
		PolygonDef shape = new PolygonDef();   
		//�����ܶ�
		if(isStatic)
		{
			shape.density = 0;
		}   
		else
		{
			shape.density = 1.0f;
		}   
		//����Ħ��ϵ��
		shape.friction = 0.0f;   
		//����������ʧ�ʣ�������
		shape.restitution = 0f;   
		float points[][] = new float[4][2];
		points=recpoints[0];
		for(float[] fa:points)
		{
			shape.addVertex(new Vec2(fa[0]/RATE,fa[1]/RATE));
		}
		//����������������   
		BodyDef bodyDef = new BodyDef();   
		//����λ��
		bodyDef.position.set(x/RATE, y/RATE);  
		
		//�������д�������
		Body bodyTemp= world.createBody(bodyDef); 
		//ָ��������״
		bodyTemp.createShape(shape);  
		
		//�����������������
		PolygonDef shape1 = new PolygonDef();   //�ڶ���shape
		//�����ܶ�
		if(isStatic)
		{
			shape1.density = 0;
		}   
		else
		{
			shape1.density = 1.0f;
		}   
		//����Ħ��ϵ��
		shape1.friction = 0.0f;   
		//����������ʧ�ʣ�������
		shape1.restitution = 0f;   
		points=recpoints[1];
		for(float[] fa:points)
		{
			shape1.addVertex(new Vec2(fa[0]/RATE,fa[1]/RATE));
		}		
		//ָ��������״
		bodyTemp.createShape(shape1); 
		
		//�����������������
		PolygonDef shape2 = new PolygonDef();   //������shape
		//�����ܶ�
		if(isStatic)
		{
			shape2.density = 0;
		}   
		else
		{
			shape2.density = 1.0f;
		}   
		//����Ħ��ϵ��
		shape2.friction = 0.0f;   
		//����������ʧ�ʣ�������
		shape2.restitution = 0f;   
		points=recpoints[2];
		for(float[] fa:points)
		{
			shape2.addVertex(new Vec2(fa[0]/RATE,fa[1]/RATE));
		}		
		//ָ��������״
		bodyTemp.createShape(shape2); 
		
		bodyTemp.setMassFromShapes(); 
		
		return new TuoPan(bodyTemp,color,pathpoints);
	} 
}
