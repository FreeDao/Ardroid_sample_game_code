package com.baina.tower.game;

import java.util.HashMap;
import java.util.PriorityQueue;
import com.baina.tower.constant.Map;
import com.baina.tower.utils.AStarComparator;
import com.baina.tower.view.GameSurfaceView;


public class Game 
{
	transient GameSurfaceView gp;
	PriorityQueue<int[][]> astarQueue=new PriorityQueue<int[][]>(100,new AStarComparator(this));//A*�����ȼ�����
	HashMap<String,int[][]> hm=new HashMap<String,int[][]>();//���·����¼
	int mapNum;
	int[][] visited=new int[Map.MAP_DATA[0].length][Map.MAP_DATA[0][0].length];//0 δȥ�� 1 ȥ��
	boolean pathFlag=false;//true �ҵ���·��
	public int[][] map_tower =  new int[Map.MAP_DATA[0].length][Map.MAP_DATA[0][0].length]; //�����������󣬹���Ѱ��·��
	public int[] source=Map.source[mapNum];//������  col,row
	public int[] target= Map.target[mapNum];//Ŀ��� col,row
	private boolean isUpdate = false;//������������־��ͼ������֮���Ƿ���Ҫ����·��
	int[][][] sequenceZ=	{			//col,row
			{							//ż����
			{0,1},
			{-1,1},
			{1,0},
			{-1,0},
			{0,-1},
			{-1,-1},		
		},
		{							//������

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
		target=Map.target[gp.mapNum];//Ŀ��� col,row
		//���Ƶ�ͼ����ֹ����֮���޸ĳ�����
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

	public synchronized HashMap<String, int[][]> BFSAStar(int[][] map_temp,int x, int y)//������� A*�㷨
	{
	//����Ϊ�����÷�����ͼ����ǡ����ĺ�������
		boolean flag=true;
		int[][] start={{x,y},{x,y}};				//��ʼ״̬
		astarQueue.offer(start);
		int count=0;
		while(flag){					
			int[][] currentEdge=astarQueue.poll();	//�Ӷ���ȡ����
			int[] tempTarget=currentEdge[1];		//ȡ���˱ߵ�Ŀ�ĵ�
								//�ж�Ŀ�ĵ��Ƿ�ȥ������ȥ����ֱ�ӽ����´�ѭ��
			if(visited[tempTarget[1]][tempTarget[0]]!=0){
				continue;
			}
			count++;
								//��ʶĿ�ĵ�Ϊ���ʹ�
			visited[tempTarget[1]][tempTarget[0]]=visited[currentEdge[0][1]][currentEdge[0][0]]+1;				
								//��¼����ʱĿ�ĵ�ĸ��ڵ�
			hm.put(tempTarget[0]+":"+tempTarget[1],new int[][]{currentEdge[1],currentEdge[0]});
								//�ж��з��ҵ�Ŀ�ĵ�
			if(tempTarget[0]==target[0]&&tempTarget[1]==target[1]){
				break;
			}
			int currCol=tempTarget[0];					//�����п��ܵı������ȼ�����
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