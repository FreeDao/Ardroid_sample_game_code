package com.bn.game.chap11.ex14;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;
//顶点法画圆，不限制顶点数
public class Ball {
	private FloatBuffer   vertexBuffer;//顶点坐标数据缓冲
	private FloatBuffer   textureBuffer;//顶点纹理数据缓冲
	private FloatBuffer   normalBuffer;//顶点法向量数据缓冲
    int vCount=0;//顶点个数
    float size;//尺寸
    float angdegColSpan;//球纵向切分角度
    float angdegRowSpan;//球横向切分角度
    float xAngle=0;//绕z轴旋转的角度
    float yAngle=0;//绕y轴旋转的角度
    float zAngle=0;//绕z轴旋转的角度
    int textureId;//纹理id
    float r;
	public Ball(float scale,float r, int nCol ,int nRow, int textureId) {//大小，半径，高度，边数，纹理id
		this.textureId=textureId;
		//改变尺寸
		size=Constant.UNIT_SIZE*scale;
		r*=size;
		//成员变量初始化
		this.r=r;
		angdegColSpan=360.0f/nCol;
		angdegRowSpan=180.0f/nRow;
		vCount=3*nCol*nRow*2;//顶点个数，共有nColumn*nRow*2个三角形，每个三角形都有三个顶点
		//坐标数据初始化
		float[] vertices=new float[vCount*3];
		float[] textures=new float[vCount*2];//顶点纹理S、T坐标值数组
		//坐标数据初始化
		int count=0;
		int stCount=0;
		for(float angdegCol=0;Math.ceil(angdegCol)<360;angdegCol+=angdegColSpan)
		{
			double angradCol=Math.toRadians(angdegCol);//当前列弧度
			double angradColNext=Math.toRadians(angdegCol+angdegColSpan);//下一列弧度
			for(float angdegRow=0;Math.ceil(angdegRow)<180;angdegRow+=angdegRowSpan)
			{
				double angradRow=Math.toRadians(angdegRow);//当前行弧度
				double angradRowNext=Math.toRadians(angdegRow+angdegRowSpan);//下一行弧度
				float rCircle=(float) (r*Math.sin(angradRow));//当前行上圆的半径
				float rCircleNext=(float) (r*Math.sin(angradRowNext));//下一行上圆的半径
				
				//当前行，当前列---0
				vertices[count++]=(float) (-rCircle*Math.sin(angradCol));
				vertices[count++]=(float) (r*Math.cos(angradRow));
				vertices[count++]=(float) (-rCircle*Math.cos(angradCol));
				
				textures[stCount++]=(float) (angradCol/(2*Math.PI));//st坐标
				textures[stCount++]=(float) (angradRow/Math.PI);
				//下一行，当前列---2
				vertices[count++]=(float) (-rCircleNext*Math.sin(angradCol));
				vertices[count++]=(float) (r*Math.cos(angradRowNext));
				vertices[count++]=(float) (-rCircleNext*Math.cos(angradCol));
				
				textures[stCount++]=(float) (angradCol/(2*Math.PI));//st坐标
				textures[stCount++]=(float) (angradRowNext/Math.PI);
				//下一行，下一列---3
				vertices[count++]=(float) (-rCircleNext*Math.sin(angradColNext));
				vertices[count++]=(float) (r*Math.cos(angradRowNext));
				vertices[count++]=(float) (-rCircleNext*Math.cos(angradColNext));
				
				textures[stCount++]=(float) (angradColNext/(2*Math.PI));//st坐标
				textures[stCount++]=(float) (angradRowNext/Math.PI);
				
				
				//当前行，当前列---0
				vertices[count++]=(float) (-rCircle*Math.sin(angradCol));
				vertices[count++]=(float) (r*Math.cos(angradRow));
				vertices[count++]=(float) (-rCircle*Math.cos(angradCol));

				textures[stCount++]=(float) (angradCol/(2*Math.PI));//st坐标
				textures[stCount++]=(float) (angradRow/Math.PI);
				//下一行，下一列---3
				vertices[count++]=(float) (-rCircleNext*Math.sin(angradColNext));
				vertices[count++]=(float) (r*Math.cos(angradRowNext));
				vertices[count++]=(float) (-rCircleNext*Math.cos(angradColNext));

				textures[stCount++]=(float) (angradColNext/(2*Math.PI));//st坐标
				textures[stCount++]=(float) (angradRowNext/Math.PI);
				//当前行，下一列---1
				vertices[count++]=(float) (-rCircle*Math.sin(angradColNext));
				vertices[count++]=(float) (r*Math.cos(angradRow));
				vertices[count++]=(float) (-rCircle*Math.cos(angradColNext));
				
				textures[stCount++]=(float) (angradColNext/(2*Math.PI));//st坐标
				textures[stCount++]=(float) (angradRow/Math.PI);
			}
		}
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);//创建顶点坐标数据缓冲
        vbb.order(ByteOrder.nativeOrder());//设置字节顺序
        vertexBuffer = vbb.asFloatBuffer();//转换为float型缓冲
        vertexBuffer.put(vertices);//向缓冲区中放入顶点坐标数据
        vertexBuffer.position(0);//设置缓冲区起始位置
        //法向量数据初始化        
        VectorUtil.normalizeAllVectors(vertices);//规格化法向量
        
        ByteBuffer nbb = ByteBuffer.allocateDirect(vertices.length*4);//创建顶点法向量数据缓冲
        nbb.order(ByteOrder.nativeOrder());//设置字节顺序
        normalBuffer = nbb.asFloatBuffer();//转换为float型缓冲
        normalBuffer.put(vertices);//向缓冲区中放入顶点法向量数据
        normalBuffer.position(0);//设置缓冲区起始位置
        //st坐标数据初始化
        ByteBuffer tbb = ByteBuffer.allocateDirect(textures.length*4);//创建顶点纹理数据缓冲
        tbb.order(ByteOrder.nativeOrder());//设置字节顺序
        textureBuffer = tbb.asFloatBuffer();//转换为float型缓冲
        textureBuffer.put(textures);//向缓冲区中放入顶点纹理数据
        textureBuffer.position(0);//设置缓冲区起始位置
	}
    public void drawSelf(GL10 gl)
    {        
    	gl.glPushMatrix();
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);//启用顶点坐标数组
        gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);//启用顶点法向量数组
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);//启用顶点纹理数组
        //绕轴旋转
        gl.glRotatef(xAngle, 1, 0, 0);
        gl.glRotatef(yAngle, 0, 1, 0);
        gl.glRotatef(zAngle, 0, 0, 1);
		//为画笔指定顶点坐标数据
        gl.glVertexPointer
        (
        		3,				//每个顶点的坐标数量为3  xyz 
        		GL10.GL_FLOAT,	//顶点坐标值的类型为 GL_FLOAT
        		0, 				//连续顶点坐标数据之间的间隔
        		vertexBuffer	//顶点坐标数据
        );
        //为画笔指定顶点法向量数据
        gl.glNormalPointer(GL10.GL_FLOAT, 0, normalBuffer);
        
        //为画笔指定纹理ST坐标缓冲
        gl.glEnable(GL10.GL_TEXTURE_2D); //开启纹理
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);//为画笔指定纹理ST坐标缓冲
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);//绑定当前纹理
        //绘制图形
        gl.glDrawArrays
        (
        		GL10.GL_TRIANGLES, 		//以三角形方式填充
        		0, 			 			//开始点编号
        		vCount					//顶点的数量
        );
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);//禁用顶点坐标数组
        gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);//禁用顶点法向量数组
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);//禁用顶点纹理数组
        gl.glPopMatrix();
    }
}
