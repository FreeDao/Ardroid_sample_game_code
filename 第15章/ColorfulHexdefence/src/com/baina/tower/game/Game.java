package com.baina.tower.game;

import java.util.HashMap;
import java.util.PriorityQueue;
import com.baina.tower.constant.Map;
import com.baina.tower.utils.AStarComparator;
import com.baina.tower.view.GameSurfaceView;


public class Game 
{
	transient GameSurfaceView gp;
	PriorityQueue<int[][]> astarQueue=new PriorityQueue<int[][]>(100,new AStarComparator(this));//A*用优先级队列
	HashMap<String,int[][]> hm=new HashMap<String,int[][]>();//结果路径记录
	int mapNum;
	int[][] visited=new int[Map.MAP_DATA[0].length][Map.MAP_DATA[0][0].length];//0 未去过 1 去过
	boolean pathFlag=false;//true 找到了路径
	public int[][] map_tower =  new int[Map.MAP_DATA[0].length][Map.MAP_DATA[0][0].length]; //用于增加塔后，怪物寻找路径
	public int[] source=Map.source[mapNum];//出发点  col,row
	public int[] target= Map.target[mapNum];//目标点 col,row
	private boolean isUpdate = false;//这里是用来标志地图加入塔之后是否需要更新路线
	int[][][] sequenceZ=	{			//col,row
			{							//偶数行
			{0,1},
			{-1,1},
			{1,0},
			{-1,0},
			{0,-1},
			{-1,-1},		
		},
		{							//奇数行

			{1,1},
			{1,0},                  
			{0,1},
			{-1,0},
			{1,-1},
			{0,-1},
			
		}
	};	
	
	public Game(GameSurfaceView gp)
	{
		this.gp=gp;
		this.mapNum = gp.mapNum;
		source=Map.source[mapNum];
		target=Map.target[gp.mapNum];//目标点 col,row
		//复制地图，防止放塔之后修改常量类
		for(int i=0;i<Map.MAP_DATA[0].length;i++){
			for(int j=0; j<Map.MAP_DATA[0][0].length;j++){
				map_tower[i][j] = Map.MAP_DATA[mapNum][i][j];
			}
		}
	}
	
	public void clearState()
	{
		astarQueue.clear();
		hm.clear();
		visited=new int[Map.MAP_DATA[0].length][Map.MAP_DATA[0][0].length];
		pathFlag=false;		
	}

	public synchronized HashMap<String, int[][]> BFSAStar(int[][] map_temp,int x, int y)//广度优先 A*算法
	{
	//参数为传给该方法地图，当恰怪物的横纵坐标
		boolean flag=true;
		int[][] start={{x,y},{x,y}};				//开始状态
		astarQueue.offer(start);
		int count=0;
		while(flag){					
			int[][] currentEdge=astarQueue.poll();	//从队首取出边
			int[] tempTarget=currentEdge[1];		//取出此边的目的点
								//判断目的点是否去过，若去过则直接进入下次循环
			if(visited[tempTarget[1]][tempTarget[0]]!=0){
				continue;
			}
			count++;
								//标识目的点为访问过
			visited[tempTarget[1]][tempTarget[0]]=visited[currentEdge[0][1]][currentEdge[0][0]]+1;				
								//记录此临时目的点的父节点
			hm.put(tempTarget[0]+":"+tempTarget[1],new int[][]{currentEdge[1],currentEdge[0]});
								//判断有否找到目的点
			if(tempTarget[0]==target[0]&&tempTarget[1]==target[1]){
				break;
			}
			int currCol=tempTarget[0];					//将所有可能的边入优先级队列
			int currRow=tempTarget[1];
			int[][] sequence=null;
			if(currRow%2==0){
				sequence=sequenceZ[0];
			}else{
				sequence=sequenceZ[1];
			}
			for(int[] rc:sequence){
				int i=rc[1];
				int j=rc[0];
				if(i==0&&j==0){continue;}
				if(currRow+i>=0&&currRow+i<Map.MAP_DATA[0].length&&currCol+j>=0&&currCol+j<Map.MAP_DATA[0][0].length&&
						map_temp[currRow+i][currCol+j]!=1){
					int[][] tempEdge={
						{tempTarget[0],tempTarget[1]},
						{currCol+j,currRow+i}};
					astarQueue.offer(tempEdge);
				}						
			}
		}
		pathFlag=true;	
		return hm;
	}
	
	public boolean isGetInWay(int x, int y){
		int[][] temp = new int[map_tower.length][map_tower[0].length];
		for(int i=0; i<map_tower.length; i++){
			for(int j=0; j<map_tower[0].length; j++){
				temp[i][j] = map_tower[i][j];
			}
		}
		temp[x][y] = 1;
		boolean isGetInWay = false;
		try{
			
			BFSAStar(temp,source[0],source[1]);
			clearState();
		}catch(NullPointerException e){
			clearState();
			isGetInWay = true;	
		}finally{
			clearState();
		}
		return isGetInWay;
	}
	public HashMap<String, int[][]> getHm() {
		return hm;
	}

	public void setHm(HashMap<String, int[][]> hm) {
		this.hm = hm;
	}

	public boolean isPathFlag() {
		return pathFlag;
	}

	public void setPathFlag(boolean pathFlag) {
		this.pathFlag = pathFlag;
	}
	public int getVisited(int x, int y){
		
		return visited[x][y];
	}

	public void setMap_Tower(int x, int y) {
		
		this.map_tower[x][y] = 1;
	}		
	public int[][] getMap_Tower() {
		
		return this.map_tower;
	}
	public boolean isUpdate() {
		return isUpdate;
	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	
}