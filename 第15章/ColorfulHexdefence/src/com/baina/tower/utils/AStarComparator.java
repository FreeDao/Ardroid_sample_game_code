package com.baina.tower.utils;
import java.io.Serializable;
import java.util.*;

import com.baina.tower.game.Game;
public class AStarComparator implements Comparator<int[][]>
{	
	Game game;
	
	public AStarComparator(Game game)
	{
		this.game=game;
	}
	
	public int compare(int[][] o1,int[][] o2)
	{
		int[] t1=o1[1];
		int[] t2=o2[1];
		int[] target=game.target;
		//门特卡罗距离，遍历节点和目标点之间的距离
		int a=game.getVisited(o1[0][1], o1[0][0])+Math.abs(t1[0]-target[0])+Math.abs(t1[1]-target[1]);
		int b=game.getVisited(o2[0][1], o2[0][0])+Math.abs(t2[0]-target[0])+Math.abs(t2[1]-target[1]);
		//处理方向标问题
		if(game.map_tower[o1[0][1]][o1[0][0]]>=6 && game.map_tower[o1[0][1]][o1[0][0]]<=11){
			a+=2;
		}
		if(game.map_tower[o2[0][1]][o2[0][0]]>=6 && game.map_tower[o2[0][1]][o2[0][0]]<=11){
			b+=2;
		}
		return a-b;
	}
	
	/*public boolean equals(Object obj)
	{

		return false;
	}*/
}