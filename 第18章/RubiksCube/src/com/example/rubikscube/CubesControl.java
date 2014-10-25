package com.example.rubikscube;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import com.example.rubikscube.util.Constant;
import com.example.rubikscube.util.Vector3f;

public class CubesControl {
	public CubeSurfDraw cubesurfaceGroup[] = new CubeSurfDraw[54];
	public CubeData cubeData = new CubeData();
	public ArrayList<SurfaceGroup> sall = new ArrayList<SurfaceGroup>();

	static int ii = 0;
	int or;

	public CubesControl(int textureId,int or) {
		this.or = or;
		int num = 0;
		for(int i=0;i<6;i++)
		{
			sall.add(new SurfaceGroup(i));
		}
		
		for (int i = -1; i < 2; i++)
			for (int j = -1; j < 2; j++)
				for (int k = -1; k < 2; k++) {

					if(i==1)
					{
						cubesurfaceGroup[num++] = new CubeSurfDraw(i, j, k, 1,textureId,or);
						sall.get(1).cg.add(cubesurfaceGroup[num-1]);
					}
					else if(i==-1)
					{
						cubesurfaceGroup[num++] = new CubeSurfDraw(i, j, k, 3,textureId,or);
						sall.get(3).cg.add(cubesurfaceGroup[num-1]);
					}
					if(j==1)
					{
						cubesurfaceGroup[num++] = new CubeSurfDraw(i, j, k, 4,textureId,or);
						sall.get(4).cg.add(cubesurfaceGroup[num-1]);
					}
					else if(j==-1)
					{
						cubesurfaceGroup[num++] = new CubeSurfDraw(i, j, k, 5,textureId,or);
						sall.get(5).cg.add(cubesurfaceGroup[num-1]);
					}
					if(k==1)
					{
						cubesurfaceGroup[num++] = new CubeSurfDraw(i, j, k, 0,textureId,or);
						sall.get(0).cg.add(cubesurfaceGroup[num-1]);
					}
					else if(k==-1)
					{
						cubesurfaceGroup[num++] = new CubeSurfDraw(i, j, k, 2,textureId,or);
						sall.get(2).cg.add(cubesurfaceGroup[num-1]);
					}

				}

		reSetColor();
	}

	public void drawSelf(GL10 gl) {// 绘制数组中的方块
		gl.glPushMatrix();
		if(or == 3)
		{
			for(SurfaceGroup sg:sall)
			{
				sg.drawSelf(gl);
			}
		}
		else if(or == 2)
		{
			for(SurfaceGroup sg:sall)
			{				
				sg.twoorSelf(gl);
			}

		}
		

		gl.glPopMatrix();
	}

	public ReSetCubeThread reSetCube() {
		CubeData t = cubeData.copy();
		String array = cubeData.reSet(or);
		cubeData = t.copy();
		ReSetCubeThread rs = new ReSetCubeThread(array,this);
		rs.run();
		return rs;
	}

	public void reSetColor() {
		for (int i = 0; i < cubesurfaceGroup.length; i++) {
			int sur = cubesurfaceGroup[i].sur;
			int x = cubesurfaceGroup[i].oldX;
			int y = cubesurfaceGroup[i].oldY;
			int z = cubesurfaceGroup[i].oldZ;
			
			switch(sur)
			{
				case 0:
					cubesurfaceGroup[i].color = cubeData.front.s[1 - y][1 + x];
					break;
				case 1:
					cubesurfaceGroup[i].color = cubeData.right.s[1 - y][1 - z];
					break;
				case 2:
					cubesurfaceGroup[i].color = cubeData.back.s[1 - y][1 - x];
					break;
				case 3:
					cubesurfaceGroup[i].color = cubeData.left.s[1 - y][1 + z];
					break;
				case 4:
					cubesurfaceGroup[i].color = cubeData.up.s[1 + z][1 + x];
					break;
				case 5:
					cubesurfaceGroup[i].color = cubeData.down.s[1 - z][1 + x];
					break;
			}
			
			cubesurfaceGroup[i].textureSet();

		}
	}

	public int row(int color, int x, int y, Vector3f down, Vector3f up) {
		switch (color) {
		case 0:
			if (isInTriangle(Constant.po[0], Constant.po[2], down, up)) {
				ii = Constant.CHANGE[0][y][0];
			} else if (isInTriangle(Constant.po[3], Constant.po[1], down, up)) {
				ii = Constant.CHANGE[0][y][1];
			} else if (isInTriangle(Constant.po[1], Constant.po[0], down, up)) {
				ii = Constant.CHANGE[0][x][2];
			} else if (isInTriangle(Constant.po[2], Constant.po[3], down, up)) {
				ii = Constant.CHANGE[0][x][3];
			}
			new RotateThread(ii,this).run();
			break;
		case 1:
			if (isInTriangle(Constant.po[4], Constant.po[6], down, up)) {
				ii = Constant.CHANGE[1][y][0];
			} else if (isInTriangle(Constant.po[7], Constant.po[5], down, up)) {
				ii = Constant.CHANGE[1][y][1];
			} else if (isInTriangle(Constant.po[5], Constant.po[4], down, up)) {
				ii = Constant.CHANGE[1][x][2];
			} else if (isInTriangle(Constant.po[6], Constant.po[7], down, up)) {
				ii = Constant.CHANGE[1][x][3];
			} else {
				System.out.println("error");
			}
			new RotateThread(ii,this).run();
			break;
		case 2:
			if (isInTriangle(Constant.po[2], Constant.po[0], down, up)) {
				ii = Constant.CHANGE[2][y][0];
			} else if (isInTriangle(Constant.po[1], Constant.po[3], down, up)) {
				ii = Constant.CHANGE[2][y][1];
			} else if (isInTriangle(Constant.po[3], Constant.po[2], down, up)) {
				ii = Constant.CHANGE[2][x][2];
			} else if (isInTriangle(Constant.po[0], Constant.po[1], down, up)) {
				ii = Constant.CHANGE[2][x][3];
			}
			new RotateThread(ii,this).run();
			break;
		case 3:
			if (isInTriangle(Constant.po[6], Constant.po[4], down, up)) {
				ii = Constant.CHANGE[3][y][0];
			} else if (isInTriangle(Constant.po[5], Constant.po[7], down, up)) {
				ii = Constant.CHANGE[3][y][1];
			} else if (isInTriangle(Constant.po[7], Constant.po[6], down, up)) {
				ii = Constant.CHANGE[3][x][2];
			} else if (isInTriangle(Constant.po[4], Constant.po[5], down, up)) {
				ii = Constant.CHANGE[3][x][3];
			}
			new RotateThread(ii,this).run();
			break;
		case 4:
			if (isInTriangle(Constant.po[8], Constant.po[10], down, up)) {
				ii = Constant.CHANGE[4][y][0];
			} else if (isInTriangle(Constant.po[11], Constant.po[9], down, up)) {
				ii = Constant.CHANGE[4][y][1];
			} else if (isInTriangle(Constant.po[9], Constant.po[8], down, up)) {
				ii = Constant.CHANGE[4][x][2];
			} else if (isInTriangle(Constant.po[10], Constant.po[11], down, up)) {
				ii = Constant.CHANGE[4][x][3];
			}
			new RotateThread(ii,this).run();
			break;
		case 5:
			if (isInTriangle(Constant.po[9], Constant.po[11], down, up)) {
				ii = Constant.CHANGE[5][y][0];
			} else if (isInTriangle(Constant.po[10], Constant.po[8], down, up)) {
				ii = Constant.CHANGE[5][y][1];
			} else if (isInTriangle(Constant.po[8], Constant.po[9], down, up)) {
				ii = Constant.CHANGE[5][x][2];
			} else if (isInTriangle(Constant.po[11], Constant.po[10], down, up)) {
				ii = Constant.CHANGE[5][x][3];
			}
			new RotateThread(ii,this).run();
			break;
		}

		return ii;
	}

	// 判断一个点是否在三角形内的方法
	// 基本算法思想是首先求要被判断的点到三角形三个顶点的矢量1、2、3
	// 然后三个矢量求叉积，若三个叉积同号则点位于三角形内，否则位于三角形外
	public boolean isInTriangle(Vector3f p1, Vector3f p2, Vector3f down,
			Vector3f up) {

		Vector3f p3;
		p1 = p1.fu();
		p2 = p2.fu();
		p3 = up.minus(down);
		Vector3f tv1;
		Vector3f tv2;
		Vector3f tv3;

		tv1 = p1.crossProduct(p2);
		tv2 = p2.crossProduct(p3);
		tv3 = p3.crossProduct(p1);

		if (tv1.tongxiang(tv2) && tv2.tongxiang(tv3)) {
			return true;
		}
		return false;

	}
	

}
