package com.wyf.hl;
import java.util.*;

import static com.wyf.hl.Map.*;

public class Game
{
	MySurfaceView gp;
	int algorithmId=0;//�㷨���� 0--�������
	ArrayList<int[][]> searchProcess=new ArrayList<int[][]>();//��������
	Stack<int[][]> stack=new Stack<int[][]>();//�����������ջ
	Queue<int[][]> queue=new LinkedList<int[][]>();//����������ö���
	PriorityQueue<int[][]> astarQueue=new PriorityQueue<int[][]>(100,new AStarComparator(this));//A*�����ȼ�����
	HashMap<String,int[][]> hm=new HashMap<String,int[][]>();//���·����¼
	int[][] visited=new int[MAP_DATA.length][MAP_DATA[0].length];//0 δȥ�� 1 ȥ��
	int[][] length=new int[MAP_DATA.length][MAP_DATA[0].length];//��¼·������ for Dijkstra
	//��¼��ÿ��������·�� for Dijkstra
	HashMap<String,ArrayList<int[][]>> hmPath=new HashMap<String,ArrayList<int[][]>>();
	
	boolean pathFlag=false;//true �ҵ���·��
	int timeSpan=10;//ʱ����

	int[][] map=Map.MAP_DATA;//��Ҫ�����ĵ�ͼ	
	int[] source=Map.source;//������  col,row
	int[] target;//Ŀ��� col,row
	
	int[][][] sequenceZ=//col,row
	{
		{//ż����
			{-1,-1},
			{0,-1},
			{1,0},
			{-1,0},
			{-1,1},
			{0,1},
		},
		{//������	
			{0,-1},
			{1,-1},
			{-1,0},
			{1,0},
			{0,1},
			{1,1},
		}
	};
	
	public Game(MySurfaceView gp)
	{
		this.gp=gp;
		target=Map.target[gp.targetId];//Ŀ��� col,row
	}
	
	public void clearState()
	{
		searchProcess.clear();
		stack.clear();
		queue.clear();
		astarQueue.clear();
		hm.clear();
		visited=new int[MAP_DATA.length][MAP_DATA[0].length];
		pathFlag=false;	
		hmPath.clear();
		
		for(int i=0;i<length.length;i++)
		{
			for(int j=0;j<length[0].length;j++)
			{
				length[i][j]=9999;//��ʼ·������Ϊ�����붼�����ܵ���ô��	
			}
		}	
	}
	
	public void runAlgorithm()
	{
		clearState();
		
		switch(algorithmId)
		{
			case 0://��������㷨
			  DFS();
			break;
			case 1://��������㷨			
			  BFS();
			break;
			case 2://������� A*�㷨
			  BFSAStar();
			break;
			case 3://Dijkstra�㷨
			  Dijkstra();
			break;
			case 4://Dijkstra A*�㷨
			  DijkstraAStar();
			break;
		}		
	}
	
	public void DFS()//��������㷨
	{
		new Thread()
		{
			public void run()
			{
				boolean flag=true;
				int[][] start=//��ʼ״̬
				{
					{source[0],source[1]},
					{source[0],source[1]}
				};
				stack.push(start);
				int count=0;//����������
				while(flag)
				{
					
					int[][] currentEdge=stack.pop();//��ջ��ȡ����
					int[] tempTarget=currentEdge[1];//ȡ���˱ߵ�Ŀ�ĵ�
					
					//�ж�Ŀ�ĵ��Ƿ�ȥ������ȥ����ֱ�ӽ����´�ѭ��
					if(visited[tempTarget[1]][tempTarget[0]]==1)
					{
						continue;
					}
					count++;
					//��ʶĿ�ĵ�Ϊ���ʹ�
					visited[tempTarget[1]][tempTarget[0]]=1;
					
					//����ʱĿ�ĵ��������������
					searchProcess.add(currentEdge);
					//��¼����ʱĿ�ĵ�ĸ��ڵ�
					hm.put(tempTarget[0]+":"+tempTarget[1],new int[][]{currentEdge[1],currentEdge[0]});
					
					gp.repaint();
					
					try{Thread.sleep(timeSpan);}catch(Exception e){e.printStackTrace();}
					
					//�ж��з��ҵ�Ŀ�ĵ�
					if(tempTarget[0]==target[0]&&tempTarget[1]==target[1])
					{
						break;
					}
					
					//�����п��ܵı���ջ
					int currCol=tempTarget[0];
					int currRow=tempTarget[1];
					
					int[][] sequence=null;
					if(currRow%2==0)
					{
						sequence=sequenceZ[0];
					}
					else
					{
						sequence=sequenceZ[1];
					}
					
					for(int[] rc:sequence)
					{
						int i=rc[1];
						int j=rc[0];
						if(i==0&&j==0){continue;}
						if(currRow+i>=0&&currRow+i<MAP_DATA.length&&currCol+j>=0&&currCol+j<MAP_DATA[0].length&&
						map[currRow+i][currCol+j]!=1)
						{
							int[][] tempEdge=
							{
								{tempTarget[0],tempTarget[1]},
								{currCol+j,currRow+i}
							};
							stack.push(tempEdge);
						}
					}
				}
				pathFlag=true;	
				gp.activity.msg="ʹ�ò�����"+count;
				gp.repaint();
			}
		}.start();		
	}
	
	public void BFS()//��������㷨
	{
		new Thread()
		{
			public void run()
			{
				int count=0;//����������
				boolean flag=true;
				int[][] start=//��ʼ״̬
				{
					{source[0],source[1]},
					{source[0],source[1]}
				};
				queue.offer(start);
				
				while(flag)
				{					
					int[][] currentEdge=queue.poll();//�Ӷ���ȡ����
					int[] tempTarget=currentEdge[1];//ȡ���˱ߵ�Ŀ�ĵ�
					
					//�ж�Ŀ�ĵ��Ƿ�ȥ������ȥ����ֱ�ӽ����´�ѭ��
					if(visited[tempTarget[1]][tempTarget[0]]==1)
					{
						continue;
					}
					count++;
					//��ʶĿ�ĵ�Ϊ���ʹ�
					visited[tempTarget[1]][tempTarget[0]]=1;
					
					//����ʱĿ�ĵ��������������
					searchProcess.add(currentEdge);
					//��¼����ʱĿ�ĵ�ĸ��ڵ�
					hm.put(tempTarget[0]+":"+tempTarget[1],new int[][]{currentEdge[1],currentEdge[0]});
					
					gp.repaint();
					
					try{Thread.sleep(timeSpan);}catch(Exception e){e.printStackTrace();}
					
					//�ж��з��ҵ�Ŀ�ĵ�
					if(tempTarget[0]==target[0]&&tempTarget[1]==target[1])
					{
						break;
					}
					
					//�����п��ܵı������
					int currCol=tempTarget[0];
					int currRow=tempTarget[1];
					
					int[][] sequence=null;
					if(currRow%2==0)
					{
						sequence=sequenceZ[0];
					}
					else
					{
						sequence=sequenceZ[1];
					}
					
					for(int[] rc:sequence)
					{
						int i=rc[1];
						int j=rc[0];
						
						if(i==0&&j==0){continue;}
						if(currRow+i>=0&&currRow+i<MAP_DATA.length&&currCol+j>=0&&currCol+j<MAP_DATA[0].length&&
						map[currRow+i][currCol+j]!=1)
						{
							int[][] tempEdge=
							{
								{tempTarget[0],tempTarget[1]},
								{currCol+j,currRow+i}
							};
							queue.offer(tempEdge);
						}
					}
				}
				pathFlag=true;	
				gp.activity.msg="ʹ�ò�����"+count;
				gp.repaint();
			}
		}.start();				
	}
	
	public void BFSAStar()//������� A*�㷨
	{
		new Thread()
		{
			public void run()
			{
				boolean flag=true;
				int[][] start=//��ʼ״̬
				{
					{source[0],source[1]},
					{source[0],source[1]}
				};
				astarQueue.offer(start);
				int count=0;
				while(flag)
				{					
					int[][] currentEdge=astarQueue.poll();//�Ӷ���ȡ����
					int[] tempTarget=currentEdge[1];//ȡ���˱ߵ�Ŀ�ĵ�
					
					//�ж�Ŀ�ĵ��Ƿ�ȥ������ȥ����ֱ�ӽ����´�ѭ��
					if(visited[tempTarget[1]][tempTarget[0]]!=0)
					{
						continue;
					}
					count++;
					//��ʶĿ�ĵ�Ϊ���ʹ�
					visited[tempTarget[1]][tempTarget[0]]=visited[currentEdge[0][1]][currentEdge[0][0]]+1;				
					//����ʱĿ�ĵ��������������
					searchProcess.add(currentEdge);
					//��¼����ʱĿ�ĵ�ĸ��ڵ�
					hm.put(tempTarget[0]+":"+tempTarget[1],new int[][]{currentEdge[1],currentEdge[0]});
					
					gp.repaint();
					
					try{Thread.sleep(timeSpan);}catch(Exception e){e.printStackTrace();}
					
					//�ж��з��ҵ�Ŀ�ĵ�
					if(tempTarget[0]==target[0]&&tempTarget[1]==target[1])
					{
						break;
					}
					
					//�����п��ܵı������ȼ�����
					int currCol=tempTarget[0];
					int currRow=tempTarget[1];
					
					int[][] sequence=null;
					if(currRow%2==0)
					{
						sequence=sequenceZ[0];
					}
					else
					{
						sequence=sequenceZ[1];
					}
					
					for(int[] rc:sequence)
					{
						int i=rc[1];
						int j=rc[0];
						if(i==0&&j==0){continue;}
						if(currRow+i>=0&&currRow+i<MAP_DATA.length&&currCol+j>=0&&currCol+j<MAP_DATA[0].length&&
						map[currRow+i][currCol+j]!=1)
						{
							int[][] tempEdge=
							{
								{tempTarget[0],tempTarget[1]},
								{currCol+j,currRow+i}
							};
							astarQueue.offer(tempEdge);
						}						
					}
				}
				pathFlag=true;	
				gp.activity.msg="ʹ�ò�����"+count;
				gp.repaint();			
			}
		}.start();				
	}
	
	public void Dijkstra()//Dijkstra�㷨
	{
		new Thread()
		{
			public void run()
			{
				int count=0;//����������
				boolean flag=true;//����ѭ������
				//��ʼ��
				int[] start={source[0],source[1]};//col,row	
				visited[source[1]][source[0]]=1;
				//����˵����п��Ե�����·��������
				
				int[][] sequence=null;
				if(start[1]%2==0)
				{
					sequence=sequenceZ[0];
				}
				else
				{
					sequence=sequenceZ[1];
				}
				
				for(int[] rowcol:sequence)
				{					
					int trow=start[1]+rowcol[1];
					int tcol=start[0]+rowcol[0];
					if(trow<0||trow>=MAP_DATA.length||tcol<0||tcol>=MAP_DATA[0].length)continue;
					if(map[trow][tcol]!=0)continue;
					
					//��¼·������
					length[trow][tcol]=1;
					
					//����·��					
					String key=tcol+":"+trow;
					ArrayList<int[][]> al=new ArrayList<int[][]>();
					al.add(new int[][]{{start[0],start[1]},{tcol,trow}});
					hmPath.put(key,al);	
					
					//��ȥ���ĵ��¼			
					searchProcess.add(new int[][]{{start[0],start[1]},{tcol,trow}});
					count++;			
				}
				gp.repaint();
				outer:while(flag)
				{					
					//�ҵ���ǰ��չ��K Ҫ����չ��KΪ�ӿ�ʼ�㵽�˵�Ŀǰ·����̣��Ҵ˵�δ�����
					int[] k=new int[2];
					int minLen=9999;
					for(int i=0;i<visited.length;i++)
					{
						for(int j=0;j<visited[0].length;j++)
						{
							if(visited[i][j]==0)
							{
								if(minLen>length[i][j])
								{
									minLen=length[i][j];
									k[0]=j;//col
									k[1]=i;//row
								}
							}
						}
					}
					
					//����ȥ���ĵ�
					visited[k[1]][k[0]]=1;					
					
					//�ػ�					
					gp.repaint();
					
					//ȡ����ʼ�㵽K��·������
					int dk=length[k[1]][k[0]];
					//ȡ����ʼ�㵽K��·��
					ArrayList<int[][]> al=hmPath.get(k[0]+":"+k[1]);
					
					sequence=null;
					if(k[1]%2==0)
					{
						sequence=sequenceZ[0];
					}
					else
					{
						sequence=sequenceZ[1];
					}
					
					//ѭ����������K����ֱ�ӵ��ĵ㵽��ʼ���·������
					for(int[] rowcol:sequence)
					{
						//������µ�Ҫ����ĵ������
						int trow=k[1]+rowcol[1];
						int tcol=k[0]+rowcol[0];
						
						//��Ҫ����ĵ㳬����ͼ�߽���ͼ�ϴ�λ��Ϊ�ϰ�������������˵�
						if(trow<0||trow>=MAP_DATA.length||tcol<0||tcol>=MAP_DATA[0].length)continue;
						if(map[trow][tcol]!=0)continue;
						
						//ȡ����ʼ�㵽�˵��·������
						int dj=length[trow][tcol];
						//���㾭K�㵽�˵��·������						
						int dkPluskj=dk+1;
						
						//����K�㵽�˵��·�����ȱ�ԭ����С���޸ĵ��˵��·��
						if(dj>dkPluskj)
						{
							String key=tcol+":"+trow;
							//��¡��ʼ�㵽K��·��
							@SuppressWarnings("unchecked")
							ArrayList<int[][]> tempal=(ArrayList<int[][]>)al.clone();
							//��·���м���һ����K���˵�
							tempal.add(new int[][]{{k[0],k[1]},{tcol,trow}});
							//����·������Ϊ�ӿ�ʼ�㵽�˵��·��
							hmPath.put(key,tempal);
							//�޸ĵ��ӿ�ʼ�㵽�˵��·������							
							length[trow][tcol]=dkPluskj;
							
							//���˵��δ�����·�������򽫴˵���뿼����̼�¼
							if(dj==9999)
							{
								//��ȥ���ĵ��¼			
								searchProcess.add(new int[][]{{k[0],k[1]},{tcol,trow}});
								count++;
							}
						}
						
						//���Ƿ��ҵ�Ŀ�ĵ�
						if(tcol==target[0]&&trow==target[1])
						{
							pathFlag=true;
							gp.activity.msg="ʹ�ò�����"+count;
							gp.repaint();	
							break outer;
						}
					}										
					try{Thread.sleep(timeSpan);}catch(Exception e){e.printStackTrace();}				
				}								
			}
		}.start();					
	}
	
	public void DijkstraAStar()//Dijkstra A*�㷨
	{
		new Thread()
		{
			public void run()
			{
				int count=0;//����������
				boolean flag=true;//����ѭ������
				//��ʼ��
				int[] start={source[0],source[1]};//col,row	
				visited[source[1]][source[0]]=1;
				
				int[][] sequence=null;
				if(source[1]%2==0)
				{
					sequence=sequenceZ[0];
				}
				else
				{
					sequence=sequenceZ[1];
				}
				
				//����˵����п��Ե�����·��������
				for(int[] rowcol:sequence)
				{					
					int trow=start[1]+rowcol[1];
					int tcol=start[0]+rowcol[0];
					if(trow<0||trow>=MAP_DATA.length||tcol<0||tcol>=MAP_DATA[0].length)continue;
					if(map[trow][tcol]!=0)continue;
					
					//��¼·������
					length[trow][tcol]=1;
					
					//����·��					
					String key=tcol+":"+trow;
					ArrayList<int[][]> al=new ArrayList<int[][]>();
					al.add(new int[][]{{start[0],start[1]},{tcol,trow}});
					hmPath.put(key,al);	
					
					//��ȥ���ĵ��¼			
					searchProcess.add(new int[][]{{start[0],start[1]},{tcol,trow}});					
					count++;			
				}				
				gp.repaint();
				outer:while(flag)
				{					
					int[] k=new int[2];
					int minLen=9999;
					boolean iniFlag=true;
					for(int i=0;i<visited.length;i++)
					{
						for(int j=0;j<visited[0].length;j++)
						{
							if(visited[i][j]==0)
							{
								//����ͨDijkstra�㷨�����𲿷�=========begin=================================
								if(length[i][j]!=9999)
								{
									if(iniFlag)
									{//��һ���ҵ��Ŀ��ܵ�
										minLen=length[i][j]+
										(int)Math.sqrt((j-target[0])*(j-target[0])+(i-target[1])*(i-target[1]));
										k[0]=j;//col
										k[1]=i;//row
										iniFlag=!iniFlag;
									}
									else
									{
										int tempLen=length[i][j]+
										(int)Math.sqrt((j-target[0])*(j-target[0])+(i-target[1])*(i-target[1]));
										if(minLen>tempLen)
										{
											minLen=tempLen;
											k[0]=j;//col
											k[1]=i;//row
										}
									}
								}
								//����ͨDijkstra�㷨�����𲿷�==========end==================================
							}
						}
					}
					//����ȥ���ĵ�
					visited[k[1]][k[0]]=1;					
					
					//�ػ�					
					gp.repaint();
					
					int dk=length[k[1]][k[0]];
					ArrayList<int[][]> al=hmPath.get(k[0]+":"+k[1]);
					
					sequence=null;
					if(k[1]%2==0)
					{
						sequence=sequenceZ[0];
					}
					else
					{
						sequence=sequenceZ[1];
					}
					
					for(int[] rowcol:sequence)
					{
						int trow=k[1]+rowcol[1];
						int tcol=k[0]+rowcol[0];
						
						if(trow<0||trow>=MAP_DATA.length||tcol<0||tcol>=MAP_DATA[0].length)continue;
						if(map[trow][tcol]!=0)continue;
						
						int dj=length[trow][tcol];						
						int dkPluskj=dk+1;
						if(dj>dkPluskj)
						{
							String key=tcol+":"+trow;
							@SuppressWarnings("unchecked")
							ArrayList<int[][]> tempal=(ArrayList<int[][]>)al.clone();
							tempal.add(new int[][]{{k[0],k[1]},{tcol,trow}});
							hmPath.put(key,tempal);							
							length[trow][tcol]=dkPluskj;
							
							if(dj==9999)
							{
								//��ȥ���ĵ��¼			
								searchProcess.add(new int[][]{{k[0],k[1]},{tcol,trow}});								
								count++;
							}
						}
						
						//���Ƿ��ҵ�Ŀ�ĵ�
						if(tcol==target[0]&&trow==target[1])
						{
							pathFlag=true;
							gp.activity.msg="ʹ�ò�����"+count;
							gp.repaint();
							break outer;
						}
					}										
					try{Thread.sleep(timeSpan);}catch(Exception e){e.printStackTrace();}				
				}								
			}
		}.start();					
	}	
}