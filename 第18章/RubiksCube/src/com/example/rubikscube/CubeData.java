package com.example.rubikscube;

import static com.example.rubikscube.util.ReSetUtil.*;

import com.example.rubikscube.util.Constant;

public class CubeData {
	public Surface up = new Surface(5); //��
	public Surface down = new Surface(3); //��
	public Surface front = new Surface(4);//��
	public Surface back = new Surface(1);//��
	public Surface left = new Surface(6);//��
	public Surface right = new Surface(2);//��
	
	String array;//�洢����
	
	CubeData copy()
	{
		CubeData n = new CubeData();
		n.up = up.copy();
		n.down = down.copy();
		n.front = front.copy();
		n.back = back.copy();
		n.left = left.copy();
		n.right = right.copy();
		return n;
	}
	
	String reSet(int or)
	{
		if(or==3)
		{
			array = "";
			DownCross(this);//�ײ�ʮ��
			F2LRestore(this);
			UpCross(this);//����ʮ��
			UpSurfaceCornerRestore(this);//������λ
			UpCornerRestore(this);//���ǻ�ԭ
			UpEdgeRestore(this);//���⻹ԭ
			return array;
		}
		else
		{
			array = "";
			DownCornerRestore(this);
			UpSurfaceCornerRestore(this);//������λ
			UpCornerRestore(this);//���ǻ�ԭ
			return array;
		}
	}
	
	public boolean isReduction()
	{
		if(Constant.ORDER==3)
		{

			if(up.isRe3()&&down.isRe3()&&front.isRe3()&&back.isRe3()&&right.isRe3()&&left.isRe3())
				return true;
			return false;
		}
		else
		{
			if(up.isRe2()&&down.isRe2()&&front.isRe2()&&back.isRe2()&&right.isRe2()&&left.isRe2())
				return true;
			return false;
		}
	}
	
	void clockwise(Surface sur,int i)
	{
		Surface t;
		for(;i>0;i--)
		{
			t = sur.copy();
			sur.s[0][0]=t.s[2][0];
			sur.s[0][1]=t.s[1][0];
			sur.s[0][2]=t.s[0][0];

			sur.s[1][0]=t.s[2][1];
			sur.s[1][1]=t.s[1][1];
			sur.s[1][2]=t.s[0][1];
			
			sur.s[2][0]=t.s[2][2];
			sur.s[2][1]=t.s[1][2];
			sur.s[2][2]=t.s[0][2];
		}
	}
	
	void F(int i)//��ħ��������˳ʱ��תi��
	{
		CubeData n;
		for(;i>0;i--)
		{
			n=copy();
			clockwise(front,1);
			right.s[0][0]=n.up.s[2][0];
			right.s[1][0]=n.up.s[2][1];
			right.s[2][0]=n.up.s[2][2];
			down.s[0][0]=n.right.s[2][0];
			down.s[0][1]=n.right.s[1][0];
			down.s[0][2]=n.right.s[0][0];
			left.s[0][2]=n.down.s[0][0];
			left.s[1][2]=n.down.s[0][1];
			left.s[2][2]=n.down.s[0][2];
			up.s[2][0]=n.left.s[2][2];
			up.s[2][1]=n.left.s[1][2];
			up.s[2][2]=n.left.s[0][2];
		}
	}
	
	void B(int i)//��ħ���ı���˳ʱ��תi��
	{
		CubeData n;
		for(;i>0;i--)
		{
			n=copy();
			clockwise(back,1);
			right.s[0][2]=n.down.s[2][2];
			right.s[1][2]=n.down.s[2][1];
			right.s[2][2]=n.down.s[2][0];
			down.s[2][0]=n.left.s[0][0];
			down.s[2][1]=n.left.s[1][0];
			down.s[2][2]=n.left.s[2][0];
			left.s[0][0]=n.up.s[0][2];
			left.s[1][0]=n.up.s[0][1];
			left.s[2][0]=n.up.s[0][0];
			up.s[0][0]=n.right.s[0][2];
			up.s[0][1]=n.right.s[1][2];
			up.s[0][2]=n.right.s[2][2];
		}
	}

	void R(int i)//��ħ��������˳ʱ��תi��
	{
		CubeData n;
		for(;i>0;i--)
		{
			n=copy();
			clockwise(right,1);
			up.s[0][2]=n.front.s[0][2];
			up.s[1][2]=n.front.s[1][2];
			up.s[2][2]=n.front.s[2][2];
			front.s[0][2]=n.down.s[0][2];
			front.s[1][2]=n.down.s[1][2];
			front.s[2][2]=n.down.s[2][2];
			down.s[0][2]=n.back.s[2][0];
			down.s[1][2]=n.back.s[1][0];
			down.s[2][2]=n.back.s[0][0];
			back.s[2][0]=n.up.s[0][2];
			back.s[1][0]=n.up.s[1][2];
			back.s[0][0]=n.up.s[2][2];
		}
	}

	void L(int i)//��ħ��������˳ʱ��תi��
	{
		CubeData n;
		for(;i>0;i--)
		{
			n=copy();
			clockwise(left,1);
			up.s[0][0]=n.back.s[2][2];
			up.s[1][0]=n.back.s[1][2];
			up.s[2][0]=n.back.s[0][2];
			back.s[0][2]=n.down.s[2][0];
			back.s[1][2]=n.down.s[1][0];
			back.s[2][2]=n.down.s[0][0];
			down.s[0][0]=n.front.s[0][0];
			down.s[1][0]=n.front.s[1][0];
			down.s[2][0]=n.front.s[2][0];
			front.s[0][0]=n.up.s[0][0];
			front.s[1][0]=n.up.s[1][0];
			front.s[2][0]=n.up.s[2][0];
		}
	}

	void U(int i)//��ħ��������˳ʱ��תi��
	{
		CubeData n;
		for(;i>0;i--)
		{
			n=copy();
			clockwise(up,1);
			front.s[0][0]=n.right.s[0][0];
			front.s[0][1]=n.right.s[0][1];
			front.s[0][2]=n.right.s[0][2];
			right.s[0][0]=n.back.s[0][0];
			right.s[0][1]=n.back.s[0][1];
			right.s[0][2]=n.back.s[0][2];
			back.s[0][0]=n.left.s[0][0];
			back.s[0][1]=n.left.s[0][1];
			back.s[0][2]=n.left.s[0][2];
			left.s[0][0]=n.front.s[0][0];
			left.s[0][1]=n.front.s[0][1];
			left.s[0][2]=n.front.s[0][2];
		}
	}

	void D(int i)//��ħ���ĵ���˳ʱ��תi��
	{
		CubeData n;
		for(;i>0;i--)
		{
			n=copy();
			clockwise(down,1);
			front.s[2][0]=n.left.s[2][0];
			front.s[2][1]=n.left.s[2][1];
			front.s[2][2]=n.left.s[2][2];
			left.s[2][0]=n.back.s[2][0];
			left.s[2][1]=n.back.s[2][1];
			left.s[2][2]=n.back.s[2][2];
			back.s[2][0]=n.right.s[2][0];
			back.s[2][1]=n.right.s[2][1];
			back.s[2][2]=n.right.s[2][2];
			right.s[2][0]=n.front.s[2][0];
			right.s[2][1]=n.front.s[2][1];
			right.s[2][2]=n.front.s[2][2];
		}
	}

	void MR(int i)//��ħ�������������֮�����������Ϊ��׼˳ʱ����ת1��
	{
		CubeData n;
		for(;i>0;i--)
		{
			n=copy();
			up.s[0][1]=n.front.s[0][1];
			up.s[1][1]=n.front.s[1][1];
			up.s[2][1]=n.front.s[2][1];
			front.s[0][1]=n.down.s[0][1];
			front.s[1][1]=n.down.s[1][1];
			front.s[2][1]=n.down.s[2][1];
			down.s[0][1]=n.back.s[2][1];
			down.s[1][1]=n.back.s[1][1];
			down.s[2][1]=n.back.s[0][1];
			back.s[2][1]=n.up.s[0][1];
			back.s[1][1]=n.up.s[1][1];
			back.s[0][1]=n.up.s[2][1];
		}
	}

	void MF(int i)//��ħ����ǰ��ͺ���֮�������ǰ��Ϊ��׼˳ʱ����תi��
	{
		CubeData n;
		for(;i>0;i--)
		{
			n=copy();
			right.s[0][1]=n.up.s[1][0];
			right.s[1][1]=n.up.s[1][1];
			right.s[2][1]=n.up.s[1][2];
			up.s[1][0]=n.left.s[2][1];
			up.s[1][1]=n.left.s[1][1];
			up.s[1][2]=n.left.s[0][1];
			left.s[0][1]=n.down.s[1][0];
			left.s[1][1]=n.down.s[1][1];
			left.s[2][1]=n.down.s[1][2];
			down.s[1][0]=n.right.s[2][1];
			down.s[1][1]=n.right.s[1][1];
			down.s[1][2]=n.right.s[0][1];
		}
	}

	void MU(int i)//��ħ��������͵���֮�����������Ϊ��׼˳ʱ����תi��
	{
		CubeData n;
		for(;i>0;i--)
		{
			n=copy();
			front.s[1][0]=n.right.s[1][0];
			front.s[1][1]=n.right.s[1][1];
			front.s[1][2]=n.right.s[1][2];
			right.s[1][0]=n.back.s[1][0];
			right.s[1][1]=n.back.s[1][1];
			right.s[1][2]=n.back.s[1][2];
			back.s[1][0]=n.left.s[1][0];
			back.s[1][1]=n.left.s[1][1];
			back.s[1][2]=n.left.s[1][2];
			left.s[1][0]=n.front.s[1][0];
			left.s[1][1]=n.front.s[1][1];
			left.s[1][2]=n.front.s[1][2];
		}
	}
	
	public void MoveCubeData(String sur,int i)
	{
		if(i!=3)
		{
			for(int j=i;j>0;j--)
			{
				array+=sur;
			}
		}
		else
		{
			array+=sur.toUpperCase();
		}
		
		if(sur.equals("f"))
			F(i);//��ħ��������˳ʱ����תi��
		if(sur.equals("b"))
			B(i);//��ħ���ı���˳ʱ����תi��
		if(sur.equals("r"))
			R(i);//��ħ��������˳ʱ����תi��
		if(sur.equals("l"))
			L(i);//��ħ��������˳ʱ����תi��
		if(sur.equals("u"))
			U(i);//��ħ��������˳ʱ����תi��
		if(sur.equals("d"))
			D(i);//��ħ���ĵ���˳ʱ����תi��
		if(sur.equals("mr"))
			MR(i);//��ħ�������������֮�����������Ϊ��׼˳ʱ����תi��
		if(sur.equals("mf"))
			MF(i);//��ħ����ǰ��ͺ���֮�������ǰ��Ϊ��׼˳ʱ����תi��
		if(sur.equals("mu"))
			MU(i);//��ħ��������͵���֮�����������Ϊ��׼˳ʱ����תi��
	}
	
	void manMoveCubeData(String sur,int i)//��������cbת��ħ��m
	{
		if(sur.equals("f"))
			F(i);//��ħ��������˳ʱ����תi��
		if(sur.equals("b"))
			B(i);//��ħ���ı���˳ʱ����תi��
		if(sur.equals("r"))
			R(i);//��ħ��������˳ʱ����תi��
		if(sur.equals("l"))
			L(i);//��ħ��������˳ʱ����תi��
		if(sur.equals("u"))
			U(i);//��ħ��������˳ʱ����תi��
		if(sur.equals("d"))
			D(i);//��ħ���ĵ���˳ʱ����תi��
		if(sur.equals("mr"))
			MR(i);//��ħ�������������֮�����������Ϊ��׼˳ʱ����תi��
		if(sur.equals("mf"))
			MF(i);//��ħ����ǰ��ͺ���֮�������ǰ��Ϊ��׼˳ʱ����תi��
		if(sur.equals("mu"))
			MU(i);//��ħ��������͵���֮�����������Ϊ��׼˳ʱ����תi��
		
	}
}