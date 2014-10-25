package com.example.rubikscube.util;

import com.example.rubikscube.CubeData;
import com.example.rubikscube.Surface;

public class ReSetUtil {

	public static void F2LRestore(CubeData cube)//魔方高级还原F2L设底面为白色
	{
		
		while(
				!(
					(
					   (cube.down.s[0][0]==cube.down.s[1][1]&&cube.front.s[2][0]==cube.front.s[1][1]&&cube.left.s[2][2]==cube.left.s[1][1])
					&& (cube.down.s[0][2]==cube.down.s[1][1]&&cube.front.s[2][2]==cube.front.s[1][1]&&cube.right.s[2][0]==cube.right.s[1][1])
					&& (cube.down.s[2][0]==cube.down.s[1][1]&&cube.right.s[2][0]==cube.right.s[1][1]&&cube.back.s[2][2]==cube.back.s[1][1])
					&& (cube.down.s[2][2]==cube.down.s[1][1]&& cube.back.s[2][0]== cube.back.s[1][1]&&cube.right.s[2][2]==cube.right.s[1][1]) 
					)
					&&
					(
						(cube.front.s[1][0]==cube.front.s[1][1]&&cube.front.s[1][2]==cube.front.s[1][1])
					&&  (cube.left.s[1][0]==cube.left.s[1][1]&&cube.left.s[1][2]==cube.left.s[1][1])
					&&  (cube.back.s[1][0]==cube.back.s[1][1]&&cube.back.s[1][2]==cube.back.s[1][1])
					&&  (cube.right.s[1][0]==cube.right.s[1][1]&&cube.right.s[1][2]==cube.right.s[1][1])
					)
				)
			)
		{
			Surface sur[] = new Surface[4];
			sur[0] = cube.front;
			sur[1] = cube.left;
			sur[2] = cube.back;
			sur[3] = cube.right;
			int subscript_of_down[][]={{0,0},{2,0},{2,2},{0,2}};
			int subscript_of_up_c[][]={{2,0},{0,0},{0,2},{2,2}};//角
			int subscript_of_up_l[][]={{2,1},{1,0},{0,1},{1,2}};//楞
			String s[]= {"f","l","b","r"};
			String ch;
			String chr;
			String chf;
			
			int n = 0;
			
			boolean bturn = false;
			boolean tturn = false;
			
			for(int j=0,i=0;j<4;j++,i++)//四个部分
			{
				//System.out.println("i="+i+" j="+j);
				tturn = false;
				//如果当前面所对应的顶面的左下角是白块
				if(cube.up.s[subscript_of_up_c[i%4][0]][subscript_of_up_c[i%4][1]]==cube.down.s[1][1])
				{
					n=0;
					while(sur[(i+n)%4].s[0][0]!=sur[(i+n+1)%4].s[1][1])
					{
						cube.MoveCubeData("u",1);
						n++;
					}
					
					//s[(i+n)%4]为当前公式右面
					if(sur[(i+n+1)%4].s[1][1]==sur[(i+n+2)%4].s[0][1] &&
						cube.up.s[subscript_of_up_l[(i+n+2)%4][0]][subscript_of_up_l[(i+n+2)%4][1]]==sur[(i+n)%4].s[1][1])//11
					{
						System.out.println("@11$");
						ch=s[(i+n+1)%4];//ch为当前公式前面，即f
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",2);
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,1);
						bturn = true;
						tturn = true;
					}
					else if(sur[(i+n)%4].s[1][1]==sur[(i+n+3)%4].s[0][1] &&
							cube.up.s[subscript_of_up_l[(i+n+3)%4][0]][subscript_of_up_l[(i+n+3)%4][1]]==sur[(i+n+1)%4].s[1][1])//12
					{
						System.out.println("@12$");
						ch=s[(i+n)%4];//ch为当前公式右面，即r
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",2);
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(ch,3);
						bturn = true;
						tturn = true;
	
					}
					else if(sur[(i+n)%4].s[1][1]==sur[(i+n+2)%4].s[0][1] &&
							cube.up.s[subscript_of_up_l[(i+n+2)%4][0]][subscript_of_up_l[(i+n+2)%4][1]]==sur[(i+n+1)%4].s[1][1])//13
					{
						System.out.println("@13$");
						ch=s[(i+n)%4];//ch为当前公式右面，即r
						cube.MoveCubeData("u",2);
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(ch,3);
						bturn = true;
						tturn = true;
	
					}
					else if(sur[(i+n+1)%4].s[1][1]==sur[(i+n+3)%4].s[0][1] &&
							cube.up.s[subscript_of_up_l[(i+n+3)%4][0]][subscript_of_up_l[(i+n+3)%4][1]]==sur[(i+n)%4].s[1][1])//14
					{
						System.out.println("@14$");
						ch=s[(i+n+1)%4];//ch为当前公式前面，即f
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",2);
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(ch,1);
						bturn = true;
						tturn = true;
	
					}
					else if(sur[(i+n+1)%4].s[1][1]==sur[(i+n+1)%4].s[0][1] &&
							cube.up.s[subscript_of_up_l[(i+n+1)%4][0]][subscript_of_up_l[(i+n+1)%4][1]]==sur[(i+n)%4].s[1][1])//34
					{
						System.out.println("@34$");
						ch=s[(i+n+1)%4];//ch为当前公式前面，即f
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",2);
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(ch,1);
						bturn = true;
						tturn = true;
	
					}
					else if(sur[(i+n)%4].s[1][1]==sur[(i+n+1)%4].s[0][1] &&
							cube.up.s[subscript_of_up_l[(i+n+1)%4][0]][subscript_of_up_l[(i+n+1)%4][1]]==sur[(i+n+1)%4].s[1][1])//35
					{
						System.out.println("@35$");
						ch=s[(i+n)%4];//ch为当前公式右面，即r
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",2);
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,3);
						bturn = true;
						tturn = true;
	
					}
					else if(sur[(i+n)%4].s[1][1]==sur[(i+n)%4].s[0][1] &&
							cube.up.s[subscript_of_up_l[(i+n)%4][0]][subscript_of_up_l[(i+n)%4][1]]==sur[(i+n+1)%4].s[1][1])//36
					{
						System.out.println("@36$");
						ch=s[(i+n)%4];//ch为当前公式右面，即r
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",2);
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,3);
						bturn = true;
						tturn = true;
						
					}
					else if(sur[(i+n+1)%4].s[1][1]==sur[(i+n)%4].s[0][1] &&
							cube.up.s[subscript_of_up_l[(i+n)%4][0]][subscript_of_up_l[(i+n)%4][1]]==sur[(i+n)%4].s[1][1])//37
					{
						System.out.println("@37$");
						chr=s[(i+n)%4];//ch为当前公式右面，即r
						chf=s[(i+n+1)%4];//ch为当前公式前面，即f
						cube.MoveCubeData(chr,1);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(chr,3);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(chr,1);
						cube.MoveCubeData("u",2);
						cube.MoveCubeData(chr,3);
						cube.MoveCubeData("u",1);
						
						cube.MoveCubeData(chf,3);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(chf,1);
						bturn = true;
						tturn = true;
						
					}
					//以上公式11，12，13，14，34，35，36，37判断
					
				}
				else if(sur[(i+1)%4].s[0][2]==cube.down.s[1][1])//如果当前面的0，2位置为白色//顺
				{
	
					n=0;
					while(sur[(i+n)%4].s[0][0]!=sur[(i+n)%4].s[1][1])
					{
						cube.MoveCubeData("u",1);
						n++;
					}
					
					//2号公式
					if(sur[(i+n)%4].s[0][1]==sur[(i+n)%4].s[1][1]
					   &&sur[(i+n+1)%4].s[1][1]==cube.up.s[subscript_of_up_l[(i+n)%4][0]][subscript_of_up_l[(i+n)%4][1]])
					{
						System.out.println("@2$");
						ch=s[(i+n)%4];
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(ch,3);
						bturn = true;
						tturn = true;
					}
	
					//4号公式
					else if(sur[(i+n+2)%4].s[0][1]==sur[(i+n+1)%4].s[1][1]
					   &&sur[(i+n)%4].s[1][1]==cube.up.s[subscript_of_up_l[(i+n+2)%4][0]][subscript_of_up_l[(i+n+2)%4][1]])
					{
						System.out.println("@4$");
						ch=s[(i+n+1)%4];
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(ch,1);
						bturn = true;
						tturn = true;
					}
	
					//6号公式
					else if(sur[(i+n+3)%4].s[0][1]==sur[(i+n+1)%4].s[1][1]
					   &&sur[(i+n)%4].s[1][1]==cube.up.s[subscript_of_up_l[(i+n+3)%4][0]][subscript_of_up_l[(i+n+3)%4][1]])
					{
						System.out.println("@6$");
						ch=s[(i+n)%4];
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(s[(i+n+1)%4],3);
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(s[(i+n+1)%4],1);
						bturn = true;
						tturn = true;
					}
	
					//8号公式
					else if(sur[(i+n+3)%4].s[0][1]==sur[(i+n)%4].s[1][1]
					   &&sur[(i+n+1)%4].s[1][1]==cube.up.s[subscript_of_up_l[(i+n+3)%4][0]][subscript_of_up_l[(i+n+3)%4][1]])
					{
						System.out.println("@8$");
						ch=s[(i+n)%4];
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",2);
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(ch,3);
						bturn = true;
						tturn = true;
					}
	
	
	
					//10号公式
					else if(sur[(i+n)%4].s[1][1]==sur[(i+n+2)%4].s[0][1]
					   &&sur[(i+n+1)%4].s[1][1]==cube.up.s[subscript_of_up_l[(i+n+2)%4][0]][subscript_of_up_l[(i+n+2)%4][1]])
					{
						System.out.println("@10$");
						ch=s[(i+n)%4];
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",2);
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",2);
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(ch,3);
						bturn = true;
						tturn = true;
					}
	
					//21号公式
					else if(sur[(i+n)%4].s[0][1]==sur[(i+n+1)%4].s[1][1]
					   &&sur[(i+n)%4].s[1][1]==cube.up.s[subscript_of_up_l[(i+n)%4][0]][subscript_of_up_l[(i+n)%4][1]])
					{
						System.out.println("@21$");
						ch=s[(i+n)%4];
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",2);
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(s[(i+n+1)%4],3);
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(s[(i+n+1)%4],1);
						bturn = true;
						tturn = true;
					}
	
					//22号公式
					else if(sur[(i+n+1)%4].s[0][1]==sur[(i+n+1)%4].s[1][1]
					   &&sur[(i+n)%4].s[1][1]==cube.up.s[subscript_of_up_l[(i+n+1)%4][0]][subscript_of_up_l[(i+n+1)%4][1]])
					{
						System.out.println("@22$");
						ch=s[(i+n+1)%4];
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(ch,1);
						bturn = true;
						tturn = true;
					}
	
	
	
	
					//25号公式
					else if(sur[(i+n)%4].s[1][1]==sur[(i+n+1)%4].s[0][1]
					   &&sur[(i+n+1)%4].s[1][1]==cube.up.s[subscript_of_up_l[(i+n+1)%4][0]][subscript_of_up_l[(i+n+1)%4][1]])
					{
						System.out.println("@25$");
						ch=s[(i+n)%4];
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",2);
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(ch,3);
						bturn = true;
						tturn = true;
					}
	
	
					//以上公式2，4，6，8，10，21，22，25判断
	
				}
				else if(sur[i%4].s[0][0]==cube.down.s[1][1])//如果当前面的0，0位置为白色//逆
				{
					n=0;
					while(sur[(i+n+1)%4].s[0][2]!=sur[(i+n+1)%4].s[1][1])
					{
						cube.MoveCubeData("u",1);
						n++;
					}
	
					if(sur[(i+n+1)%4].s[1][1]==sur[(i+n+1)%4].s[0][1] &&
						cube.up.s[subscript_of_up_l[(i+n+1)%4][0]][subscript_of_up_l[(i+n+1)%4][1]]==sur[(i+n)%4].s[1][1])//1  
					{
						System.out.println("@1$");
						ch=s[(i+n+1)%4];//ch为当前公式前面，即f
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,1);
						bturn = true;
						tturn = true;
					}
					else if(sur[(i+n)%4].s[1][1]==sur[(i+n+3)%4].s[0][1] &&
							cube.up.s[subscript_of_up_l[(i+n+3)%4][0]][subscript_of_up_l[(i+n+3)%4][1]]==sur[(i+n+1)%4].s[1][1])//3  
					{
						System.out.println("@3$");
						ch=s[(i+n)%4];//ch为当前公式右面，即r
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,3);
						bturn = true;
						tturn = true;
					}
					else if(sur[(i+n)%4].s[1][1]==sur[(i+n+2)%4].s[0][1] &&
							cube.up.s[subscript_of_up_l[(i+n+2)%4][0]][subscript_of_up_l[(i+n+2)%4][1]]==sur[(i+n+1)%4].s[1][1])//5 
					{
						System.out.println("@5$");
						ch=s[(i+n)%4];//ch为当前公式右面，即r
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,3);
						bturn = true;
						tturn = true;
					}
					else if(sur[(i+n+1)%4].s[1][1]==sur[(i+n+2)%4].s[0][1] &&
							cube.up.s[subscript_of_up_l[(i+n+2)%4][0]][subscript_of_up_l[(i+n+2)%4][1]]==sur[(i+n)%4].s[1][1])//7  
					{
						System.out.println("@7$");
						ch=s[(i+n+1)%4];//ch为当前公式前面，即f
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",2);
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,1);
						bturn = true;
						tturn = true;
					}
					
					
					//++++++++
					//9号公式
					else if(sur[(i+n+1)%4].s[1][1]==sur[(i+n+3)%4].s[0][1]
					   &&sur[(i+n)%4].s[1][1]==cube.up.s[subscript_of_up_l[(i+n+3)%4][0]][subscript_of_up_l[(i+n+3)%4][1]])
					{
						System.out.println("@9$");
						ch=s[(i+n+1)%4];
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",2);
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",2);
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,1);
						bturn = true;
						tturn = true;
					}
	
	
					//23号公式
					else if(sur[(i+n+1)%4].s[0][1]==sur[(i+n)%4].s[1][1]
					   &&sur[(i+n+1)%4].s[1][1]==cube.up.s[subscript_of_up_l[(i+n+1)%4][0]][subscript_of_up_l[(i+n+1)%4][1]])
					{
						System.out.println("@23$");
						ch=s[(i+n)%4];
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",1);
						
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",1);
						
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(ch,3);
						bturn = true;
						tturn = true;
					}
	
					//24号公式
					else if(sur[(i+n)%4].s[0][1]==sur[(i+n)%4].s[1][1]
					   &&sur[(i+n+1)%4].s[1][1]==cube.up.s[subscript_of_up_l[(i+n)%4][0]][subscript_of_up_l[(i+n)%4][1]])
					{
						System.out.println("@24$");
						ch=s[(i+n)%4];
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,3);
						bturn = true;
						tturn = true;
					}
	
					//26号公式
					else if(sur[(i+n)%4].s[0][1]==sur[(i+n+1)%4].s[1][1]
					   &&sur[(i+n)%4].s[1][1]==cube.up.s[subscript_of_up_l[(i+n)%4][0]][subscript_of_up_l[(i+n)%4][1]])
					{
						System.out.println("@26$");
						ch=s[(i+n)%4];
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",2);
						cube.MoveCubeData(s[(i+n+1)%4],3);
						cube.MoveCubeData("u",3);
						cube.MoveCubeData(s[(i+n+1)%4],1);
						bturn = true;
						tturn = true;
					}
					//以上公式1，3，5，7，9，23，24，26判断
					
					
				}//顶逆
				
				if(tturn)
				{
					j = -1;
					i = -1;
					n = 0;
				}
				else
				{
					i = i+n;
					n = 0;
				}
		
			}//四部分
			
			if(
					(
					   (cube.down.s[0][0]==cube.down.s[1][1]&&cube.front.s[2][0]==cube.front.s[1][1]&&cube.left.s[2][2]==cube.left.s[1][1])
					&& (cube.down.s[0][2]==cube.down.s[1][1]&&cube.front.s[2][2]==cube.front.s[1][1]&&cube.right.s[2][0]==cube.right.s[1][1])
					&& (cube.down.s[2][0]==cube.down.s[1][1]&&cube.right.s[2][0]==cube.right.s[1][1]&&cube.back.s[2][2]==cube.back.s[1][1])
					&& (cube.down.s[2][2]==cube.down.s[1][1]&& cube.back.s[2][0]== cube.back.s[1][1]&&cube.right.s[2][2]==cube.right.s[1][1]) 
					)
					&&
					(
						(cube.front.s[1][0]==cube.front.s[1][1]&&cube.front.s[1][2]==cube.front.s[1][1])
					&&  (cube.left.s[1][0]==cube.left.s[1][1]&&cube.left.s[1][2]==cube.left.s[1][1])
					&&  (cube.back.s[1][0]==cube.back.s[1][1]&&cube.back.s[1][2]==cube.back.s[1][1])
					&&  (cube.right.s[1][0]==cube.right.s[1][1]&&cube.right.s[1][2]==cube.right.s[1][1])
					)
				)break;
			
			
			//以上：若有白色角块在顶层且其所对应楞块也在顶层则还原此两块 
			//		若没有符合以上条件的则进入以下判断模块
			//以下：搜寻其他情况，若有则将其移动到顶层并跳出此循环再次进入上面的循环
			
//			if(!bturn)
//			{
		
				for(int i=0;i<4;i++)//四个部分
				{
					//如果当前面所对应的顶面的左下角是白块
					if(cube.up.s[subscript_of_up_c[i%4][0]][subscript_of_up_c[i%4][1]]==cube.down.s[1][1])
					{
						n = 0;
						while(sur[(i+n)%4].s[0][0]!=sur[(i+n+1)%4].s[1][1])
						{
							cube.MoveCubeData("u",1);
							n++;
						}
						int rc = sur[(i+n)%4].s[1][1];
						int lc = sur[(i+n+1)%4].s[1][1];
						while(true)
						{
							System.out.println("棱块在侧棱上");
	
							if(sur[(i+n)%4].s[1][0]==rc&&sur[(i+n+1)%4].s[1][2]==lc)//公式19
							{
								System.out.println("19$");
								ch=s[(i+n)%4];//ch为当前“公式右面”
								//ruRUruR
								cube.MoveCubeData(ch,1);
								cube.MoveCubeData("u",1);
								cube.MoveCubeData(ch,3);
								cube.MoveCubeData("u",3);
								cube.MoveCubeData(ch,1);
								cube.MoveCubeData("u",1);
								cube.MoveCubeData(ch,3);
								break;
							}
							else if(sur[(i+n)%4].s[1][0]==lc&&sur[(i+n+1)%4].s[1][2]==rc)//公式20
							{
								System.out.println("20$");
								ch=s[(i+n)%4];//ch为当前“公式右面”
								//rUR
								cube.MoveCubeData(ch,1);
								cube.MoveCubeData("u",3);
								cube.MoveCubeData(ch,3);
								break;
							}
							else
							{
								cube.MoveCubeData("u",1);
								n++;
							}
						}
						break;
					}
					
					else if(sur[(i+1)%4].s[0][2]==cube.down.s[1][1])//如果当前面的0，2位置为白色//顺
					{
	
						n=0;
						while(sur[(i+n)%4].s[0][0]!=sur[(i+n)%4].s[1][1])
						{
							cube.MoveCubeData("u",1);
							n++;
						}
						int rc = sur[(i+n)%4].s[1][1];
						int lc = sur[(i+n+1)%4].s[1][1];
						while(true)
						{
	
							if(sur[(i+n)%4].s[1][0]==rc&&sur[(i+n+1)%4].s[1][2]==lc)//公式16
							{
								System.out.println("16$");
								ch=s[(i+n)%4];//ch为当前“公式右面”
								//UrUR
								cube.MoveCubeData("u",3);
								cube.MoveCubeData(ch,1);
								cube.MoveCubeData("u",3);
								cube.MoveCubeData(ch,3);
								break;
							}
							else if(sur[(i+n)%4].s[1][0]==lc&&sur[(i+n+1)%4].s[1][2]==rc)//公式18
							{
								System.out.println("18$");
								ch=s[(i+n)%4];//ch为当前“公式右面”
								//UruR
								cube.MoveCubeData("u",3);
								cube.MoveCubeData(ch,1);
								cube.MoveCubeData("u",1);
								cube.MoveCubeData(ch,3);
								break;
							}
							else
							{
								cube.MoveCubeData("u",1);
								n++;
							}
						}
						break;
					
					}
					else if(sur[i%4].s[0][0]==cube.down.s[1][1])//如果当前面的0，0位置为白色//逆
					{
						n=0;
						while(sur[(i+n+1)%4].s[0][2]!=sur[(i+n+1)%4].s[1][1])
						{
							cube.MoveCubeData("u",1);
							n++;
						}
						
						int rc = sur[(i+n)%4].s[1][1];
						int lc = sur[(i+n+1)%4].s[1][1];
						while(true)
						{
	
							if(sur[(i+n)%4].s[1][0]==rc&&sur[(i+n+1)%4].s[1][2]==lc)//公式15
							{
								System.out.println("15$");
								ch=s[(i+n)%4];//ch为当前“公式右面”
								//UruuR
								cube.MoveCubeData("u",3);
								cube.MoveCubeData(ch,1);
								cube.MoveCubeData("u",2);
								cube.MoveCubeData(ch,3);
								break;
							}
							else if(sur[(i+n)%4].s[1][0]==lc&&sur[(i+n+1)%4].s[1][2]==rc)//公式17
							{
								System.out.println("17$");
								ch=s[(i+n+1)%4];//ch为当前“公式右面”
								//uRUr
								cube.MoveCubeData("u",1);
								cube.MoveCubeData(ch,3);
								cube.MoveCubeData("u",3);
								cube.MoveCubeData(ch,1);
								break;
							}
							else
							{
								cube.MoveCubeData("u",1);
								n++;
							}
						}
						break;
					}				
					
					
					
					else if(cube.down.s[subscript_of_down[i%4][0]][subscript_of_down[i%4][1]] == cube.down.s[1][1])//底底
					{
						if(sur[i%4].s[1][0]==sur[i%4].s[2][0]&&sur[(i+1)%4].s[1][2]==sur[(i+1)%4].s[2][2])//已还原
						{
							if(sur[i%4].s[1][0]==sur[i%4].s[1][1]&&sur[(i+1)%4].s[1][2]==sur[(i+1)%4].s[1][1])
							{
								System.out.println("已还原1");
								//已还原
								continue;
							}
							else//拿出
							{
								System.out.println("拿出1");
								ch = s[i%4];
								cube.MoveCubeData(ch, 1);
								cube.MoveCubeData("u", 1);
								cube.MoveCubeData(ch, 3);
							}
						}
						//公式31
						else if(sur[i%4].s[1][0]==sur[(i+1)%4].s[2][2]&&sur[i%4].s[2][0]==sur[(i+1)%4].s[1][2])
						{
							System.out.println("$31");
							ch = s[i%4];
							cube.MoveCubeData(ch, 1);
							cube.MoveCubeData("u", 2);
							cube.MoveCubeData(ch, 3);
							cube.MoveCubeData("u", 1);
							cube.MoveCubeData(ch, 1);
							cube.MoveCubeData("u", 2);
							cube.MoveCubeData(ch, 3);
						}
						else
						{
							n=0;
							while(n<4)
							{
								//公式32
								
								if(sur[(i+1)%4].s[0][1]==sur[(i+1)%4].s[2][2] 
									&& sur[(i)%4].s[2][0]==cube.up.s[subscript_of_up_l[(i+1)%4][0]][subscript_of_up_l[(i+1)%4][1]])
								{
									System.out.println("$32");
									ch = s[i%4];
									cube.MoveCubeData("u", 1);
									cube.MoveCubeData(ch, 1);
									cube.MoveCubeData("u", 3);
									cube.MoveCubeData(ch, 3);
									break;
								}
								//公式33
								else if(sur[i%4].s[2][0]==sur[i%4].s[0][1] 
										&& sur[(i+1)%4].s[2][2]==cube.up.s[subscript_of_up_l[i%4][0]][subscript_of_up_l[i%4][1]])
								{
									System.out.println("$33");
									ch = s[(i+1)%4];
									cube.MoveCubeData("u", 3);
									cube.MoveCubeData(ch, 3);
									cube.MoveCubeData("u", 1);
									cube.MoveCubeData(ch, 1);
									break;
								}
								else
								{
									cube.MoveCubeData("u",1);
									n++;
								}
							}
							if(n==4)//将此底块挪至顶层
							{
								System.out.println("拿出2");
								ch = s[i%4];
								cube.MoveCubeData(ch, 1);
								cube.MoveCubeData("u", 1);
								cube.MoveCubeData(ch, 3);
							}
						}
						break;
					}
					else if(sur[(i+1)%4].s[2][2]==cube.down.s[1][1])//底顺
					{
						//公式28
						if(sur[i%4].s[2][0]==sur[(i+1)%4].s[1][2]&&sur[i%4].s[1][0]==cube.down.s[subscript_of_down[i%4][0]][subscript_of_down[i%4][1]])
						{
							System.out.println("$28");
							ch = s[i%4];
							cube.MoveCubeData(ch, 1);
							cube.MoveCubeData("u", 1);
							cube.MoveCubeData(ch, 3);
							cube.MoveCubeData("u", 3);
							cube.MoveCubeData(ch, 1);
							cube.MoveCubeData("u", 2);
							cube.MoveCubeData(ch, 3);
						}
						//公式30//e
						else if(sur[i%4].s[2][0]==sur[i%4].s[1][0]&&sur[(i+1)%4].s[1][2]==cube.down.s[subscript_of_down[i%4][0]][subscript_of_down[i%4][1]])
						{
							System.out.println("$30");
							ch = s[i%4];
							cube.MoveCubeData(ch, 1);
							cube.MoveCubeData("u", 3);
							cube.MoveCubeData(ch, 3);
						}
						else
						{
							n=0;
							while(n<4)
							{
								//公式40
								if(sur[(i)%4].s[2][0]==cube.up.s[subscript_of_up_l[(i)%4][0]][subscript_of_up_l[(i)%4][1]] 
									&& cube.down.s[subscript_of_down[(i)%4][0]][subscript_of_down[(i)%4][1]]==sur[(i)%4].s[0][1])
								{
									System.out.println("$40");
									ch = s[i%4];
									cube.MoveCubeData(ch, 1);
									cube.MoveCubeData("u", 3);
									cube.MoveCubeData(ch, 3);
									break;
								}
								//公式41
								else if(sur[(i)%4].s[2][0]==sur[(i+1)%4].s[0][1] 
										&& cube.down.s[subscript_of_down[(i)%4][0]][subscript_of_down[(i)%4][1]]==cube.up.s[subscript_of_up_l[(i+1)%4][0]][subscript_of_up_l[(i+1)%4][1]])
								{
									System.out.println("$41");
									ch = s[(i+1)%4];
									cube.MoveCubeData(ch, 3);
									cube.MoveCubeData("u", 3);
									cube.MoveCubeData(ch, 1);
									break;
								}
								else
								{
									cube.MoveCubeData("u",1);
									n++;
								}
							}
							if(n==4)//将此底块挪至顶层
							{
								System.out.println("拿出3");
								ch = s[(i)%4];
								cube.MoveCubeData(ch, 1);
								cube.MoveCubeData("u", 1);
								cube.MoveCubeData(ch, 3);
							}
						}
						break;
					}
					else if(sur[i%4].s[2][0]==cube.down.s[1][1])//底逆
					{
						//公式27e
						if(sur[i%4].s[1][0]==sur[(i+1)%4].s[2][2]
								&&sur[(i+1)%4].s[1][2]==cube.down.s[subscript_of_down[i%4][0]][subscript_of_down[i%4][1]])
						{
							System.out.println("$27");
							ch = s[i%4];
							cube.MoveCubeData(ch, 1);
							cube.MoveCubeData("u", 3);
							cube.MoveCubeData(ch, 3);
						}
						//公式29
						else if(sur[i%4].s[1][0]==cube.down.s[subscript_of_down[i%4][0]][subscript_of_down[i%4][1]]
								&&sur[(i+1)%4].s[1][2]==sur[(i+1)%4].s[2][2])
						{
							System.out.println("$29");
							chr = s[i%4];
							chf = s[(i+3)%4];
							cube.MoveCubeData(chr, 2);
							cube.MoveCubeData(chf, 1);
							cube.MoveCubeData("u", 1);
							cube.MoveCubeData(chf, 3);
							cube.MoveCubeData("u", 3);
							cube.MoveCubeData(chr, 3);
							cube.MoveCubeData("u", 3);
							cube.MoveCubeData(chr, 3);
						}
						else
						{
							n=0;
							while(n<4)
							{
								//公式38e
								if(sur[(i+1)%4].s[2][2]==cube.up.s[subscript_of_up_l[(i+1)%4][0]][subscript_of_up_l[(i+1)%4][1]] 
									&& cube.down.s[subscript_of_down[(i)%4][0]][subscript_of_down[(i)%4][1]]==sur[(i+1)%4].s[0][1])
								{
									System.out.println("$38");
									ch = s[(i+1)%4];
									cube.MoveCubeData(ch, 3);
									cube.MoveCubeData("u", 1);
									cube.MoveCubeData(ch, 1);
									break;
								}
								//公式39
								else if(sur[(i+1)%4].s[2][2]==sur[(i)%4].s[0][1] 
										&& cube.down.s[subscript_of_down[(i)%4][0]][subscript_of_down[(i)%4][1]]==cube.up.s[subscript_of_up_l[(i)%4][0]][subscript_of_up_l[(i)%4][1]])
								{
									System.out.println("$39");
									ch = s[i%4];
									cube.MoveCubeData(ch, 1);
									cube.MoveCubeData("u", 1);
									cube.MoveCubeData(ch, 3);
									break;
								}
								else
								{
									cube.MoveCubeData("u",1);
									n++;
								}
							}
							if(n==4)//将此底块挪至顶层
							{
								System.out.println("拿出3");
								ch = s[(i)%4];
								cube.MoveCubeData(ch, 1);
								cube.MoveCubeData("u", 1);
								cube.MoveCubeData(ch, 3);
							}
						}
						break;
					}
					
					
				}//for
				
			//}//if()

		}//while()
		
	}//函数
		
		public static void DownCross(CubeData cube)//将底面拼出一个十字
		{
			while(!((cube.down.s[0][1]==cube.down.s[1][1]&&cube.front.s[2][1]==cube.front.s[1][1])
				&&(cube.down.s[1][0]==cube.down.s[1][1]&&cube.left.s[2][1]==cube.left.s[1][1])
				&&(cube.down.s[1][2]==cube.down.s[1][1]&&cube.right.s[2][1]==cube.right.s[1][1])
				&&(cube.down.s[2][1]==cube.down.s[1][1]&&cube.back.s[2][1]==cube.back.s[1][1])))
			{
				Surface sur[] = new Surface[4];
				sur[0] = cube.front;
				sur[1] = cube.left;
				sur[2] = cube.back;
				sur[3] = cube.right;
				String s[]= {"f","l","b","r"};
				int subscript_of_down[][]={{0,1},{1,0},{2,1},{1,2}};
				int subscript_of_up[][]={{2,1},{1,0},{0,1},{1,2}};
				String ch;
				int n;
				System.out.println("十字");
				for(int i=0;i<4;i++)
				{
					System.out.println("十字内");
					if(cube.down.s[subscript_of_down[i][0]][subscript_of_down[i][1]]==cube.down.s[1][1]
						&&sur[i].s[2][1]!=sur[i].s[1][1])
					{
						ch = s[i];				
						cube.MoveCubeData(ch,2);
					}//底面棱块为底面色位置不对
					if(cube.up.s[subscript_of_up[i][0]][subscript_of_up[i][1]]==cube.down.s[1][1])
					{
						n=0;
						while(sur[(i+n)%4].s[1][1]!=sur[(i+n)%4].s[0][1])
						{
							cube.MoveCubeData("u",1);
							n++;
						}
						ch = s[(i+n)%4];
						cube.MoveCubeData(ch,2);
					}//以上是底面棱块在顶面的情况
					if(sur[i].s[0][1]==cube.down.s[1][1])//侧面上棱是底面色的情况
					{
						n=0;
						while(sur[(i+n+1)%4].s[1][1]!=cube.up.s[subscript_of_up[(i+n)%4][0]][subscript_of_up[(i+n)%4][1]])
						{
							cube.MoveCubeData("u",1);
							n++;
						}
						ch = s[(i+n)%4];
						cube.MoveCubeData(ch,3);
						ch = s[(n+1+i)%4];
						cube.MoveCubeData(ch,1);
						ch = s[(i+n)%4];
						cube.MoveCubeData(ch,1);
					}
					if(sur[i].s[1][0]==cube.down.s[1][1])//侧面左棱是底面色的情况
					{
						ch = s[(i+1)%4];
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,1);
					}
					if(sur[i].s[1][2]==cube.down.s[1][1])//侧面右棱是底面色的情况
					{
						ch = s[(i+3)%4];
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,3);
					}
					if(sur[i].s[2][1]==cube.down.s[1][1])//侧面底棱是底面色的情况
					{
						ch = s[i];
						cube.MoveCubeData(ch,1);
						ch = s[(i+1)%4];
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,1);
						ch = s[i];
						cube.MoveCubeData(ch,3);
					}//以上是侧面棱块色是底面色的情况
				}
			}
		}

	public static void DownCornerRestore(CubeData cube)// 底角还原
	{
		while (!((cube.down.s[0][0] == cube.down.s[1][1]
				&& cube.front.s[2][0] == cube.front.s[1][1] && cube.left.s[2][2] == cube.left.s[1][1])
				&& (cube.down.s[0][2] == cube.down.s[1][1]
						&& cube.front.s[2][2] == cube.front.s[1][1] && cube.right.s[2][0] == cube.right.s[1][1])
				&& (cube.down.s[2][0] == cube.down.s[1][1]
						&& cube.right.s[2][0] == cube.right.s[1][1] && cube.back.s[2][2] == cube.back.s[1][1]) && (cube.down.s[2][2] == cube.down.s[1][1]
				&& cube.back.s[2][0] == cube.back.s[1][1] && cube.right.s[2][2] == cube.right.s[1][1])))// 直到底角全部归位
		{
			Surface sur[] = new Surface[4];
			sur[0] = cube.front;
			sur[1] = cube.left;
			sur[2] = cube.back;
			sur[3] = cube.right;
			String s[] = { "f", "l", "b", "r" };
			int subscript_of_down[][] = { { 0, 0 }, { 2, 0 }, { 2, 2 },
					{ 0, 2 } };
			int subscript_of_up[][] = { { 2, 0 }, { 0, 0 }, { 0, 2 }, { 2, 2 } };
			String ch;
			int n;
			for (int i = 0; i < 4; i++) {
				if (cube.down.s[subscript_of_down[i%4][0]][subscript_of_down[i%4][1]] == cube.down.s[1][1]
						&& (sur[i%4].s[2][0] != sur[i%4].s[1][1] || sur[(i + 1) % 4].s[2][2] != sur[(i + 1) % 4].s[1][1])) {
					ch = s[i%4];
					cube.MoveCubeData(ch, 1);
					cube.MoveCubeData("u", 1);
					cube.MoveCubeData(ch, 3);
				}// 底面角块颜色归位但是位置不对
				if (cube.up.s[subscript_of_up[i%4][0]][subscript_of_up[i%4][1]] == cube.down.s[1][1]) {
					n = 0;
					while (sur[(i + n) % 4].s[0][0] != sur[(i + n + 1) % 4].s[1][1]) {
						cube.MoveCubeData("u", 1);
						n++;
					}
					ch = s[(i + n) % 4];
					cube.MoveCubeData(ch, 1);
					cube.MoveCubeData("u", 3);
					cube.MoveCubeData(ch, 3);
					cube.MoveCubeData("u", 2);
				}// 顶面有底角色块的情况
				if (sur[i%4].s[0][0] == cube.down.s[1][1])// 侧面左上角是底面色的情况
				{
					n = 0;
					while (sur[(i + n + 1) % 4].s[1][1] != sur[(i + n + 1) % 4].s[0][2]) {
						cube.MoveCubeData("u", 1);
						n++;
					}
					ch = s[(n + i) % 4];
					cube.MoveCubeData(ch, 1);
					cube.MoveCubeData("u", 1);
					cube.MoveCubeData(ch, 3);
				}
				if (sur[i%4].s[0][2] == cube.down.s[1][1])// 侧面右上角是底面色的情况
				{
					n = 0;
					while (sur[(i + n + 3) % 4].s[1][1] != sur[(i + n + 3) % 4].s[0][0]) {
						cube.MoveCubeData("u", 1);
						n++;
					}
					ch = s[(n + i) % 4];
					cube.MoveCubeData(ch, 3);
					cube.MoveCubeData("u", 3);
					cube.MoveCubeData(ch, 1);
				}
				if (sur[i%4].s[2][0] == cube.down.s[1][1])// 侧面左下角是底面色的情况
				{
					ch = s[i%4];
					cube.MoveCubeData(ch, 1);
					cube.MoveCubeData("u", 1);
					cube.MoveCubeData(ch, 3);
				}
				if (sur[i%4].s[2][2] == cube.down.s[1][1])// 侧面右下角是底面色的情况
				{
					ch = s[i%4];
					cube.MoveCubeData(ch, 3);
					cube.MoveCubeData("u", 3);
					cube.MoveCubeData(ch, 1);
				}// 侧面有底面色块
			}
		}
	}

	static void CentreEdgeRestore(CubeData cube)// 中棱归位
	{
		while (!((cube.front.s[1][0] == cube.front.s[1][1] && cube.front.s[1][2] == cube.front.s[1][1])
				&& (cube.left.s[1][0] == cube.left.s[1][1] && cube.left.s[1][2] == cube.left.s[1][1])
				&& (cube.back.s[1][0] == cube.back.s[1][1] && cube.back.s[1][2] == cube.back.s[1][1]) && (cube.right.s[1][0] == cube.right.s[1][1] && cube.right.s[1][2] == cube.right.s[1][1]))) {
			Surface sur[] = new Surface[4];
			sur[0] = cube.front;
			sur[1] = cube.left;
			sur[2] = cube.back;
			sur[3] = cube.right;
			String s[] = { "f", "l", "b", "r" };
			int subscript_of_up[][] = { { 2, 1 }, { 1, 0 }, { 0, 1 }, { 1, 2 } };
			String ch;
			int n;
			for (int i = 0; i < 4; i++) {
				if (!(sur[i%4].s[1][0] == sur[i%4].s[1][1] && sur[(i + 1) % 4].s[1][2] == sur[(i + 1) % 4].s[1][1])
						&& (sur[i%4].s[1][0] != cube.up.s[1][1] && sur[(i + 1) % 4].s[1][2] != cube.up.s[1][1])) {
					ch = s[i%4];
					cube.MoveCubeData(ch, 3);
					cube.MoveCubeData("u", 3);
					cube.MoveCubeData(ch, 3);
					cube.MoveCubeData("u", 3);
					cube.MoveCubeData(ch, 3);
					cube.MoveCubeData("u", 1);
					cube.MoveCubeData(ch, 1);
					cube.MoveCubeData("u", 1);
					cube.MoveCubeData(ch, 1);
					cube.MoveCubeData("u", 2);
				}
				if (sur[i%4].s[0][1] != cube.up.s[1][1]
						&& cube.up.s[subscript_of_up[i%4][0]][subscript_of_up[i%4][1]] != cube.up.s[1][1]) {
					n = 0;
					while (sur[(i + n) % 4].s[0][1] != sur[(i + n) % 4].s[1][1]) {
						n++;
						cube.MoveCubeData("u", 1);
					}
					if (cube.up.s[subscript_of_up[(i + n) % 4][0]][subscript_of_up[(i + n) % 4][1]] == sur[(i
							+ n + 3) % 4].s[1][1]) {
						ch = s[(i + n) % 4];
						cube.MoveCubeData(ch, 1);
						cube.MoveCubeData("u", 1);
						cube.MoveCubeData(ch, 1);
						cube.MoveCubeData("u", 1);
						cube.MoveCubeData(ch, 1);
						cube.MoveCubeData("u", 3);
						cube.MoveCubeData(ch, 3);
						cube.MoveCubeData("u", 3);
						cube.MoveCubeData(ch, 3);
					}
					if (cube.up.s[subscript_of_up[(i + n) % 4][0]][subscript_of_up[(i + n) % 4][1]] == sur[(i
							+ n + 1) % 4].s[1][1]) {
						ch = s[(i + n) % 4];
						cube.MoveCubeData(ch, 3);
						cube.MoveCubeData("u", 3);
						cube.MoveCubeData(ch, 3);
						cube.MoveCubeData("u", 3);
						cube.MoveCubeData(ch, 3);
						cube.MoveCubeData("u", 1);
						cube.MoveCubeData(ch, 1);
						cube.MoveCubeData("u", 1);
						cube.MoveCubeData(ch, 1);
					}
				}
			}
		}
	}

	public static void UpCross(CubeData cube)// 顶面十字
	{
		while (!(cube.up.s[0][1] == cube.up.s[1][1]
				&& cube.up.s[1][0] == cube.up.s[1][1]
				&& cube.up.s[1][2] == cube.up.s[1][1] && cube.up.s[2][1] == cube.up.s[1][1])) {

			Surface sur[] = new Surface[4];
			sur[0] = cube.front;
			sur[1] = cube.left;
			sur[2] = cube.back;
			sur[3] = cube.right;
			String s[] = { "f", "l", "b", "r" };
			int subscript_of_up[][] = { { 2, 1 }, { 1, 0 }, { 0, 1 }, { 1, 2 } };
			String ch;
			if (cube.up.s[0][1] != cube.up.s[1][1]
					&& cube.up.s[1][0] != cube.up.s[1][1]
					&& cube.up.s[1][2] != cube.up.s[1][1]
					&& cube.up.s[2][1] != cube.up.s[1][1]) {
				cube.MoveCubeData("f", 1);
				cube.MoveCubeData("r", 1);
				cube.MoveCubeData("u", 1);
				cube.MoveCubeData("r", 3);
				cube.MoveCubeData("u", 3);
				cube.MoveCubeData("f", 3);
			}
			for (int i = 0; i < 4; i++) {
				if (cube.up.s[subscript_of_up[(i + 1) % 4][0]][subscript_of_up[(i + 1) % 4][1]] == cube.up.s[1][1]
						&& cube.up.s[subscript_of_up[(i + 2) % 4][0]][subscript_of_up[(i + 2) % 4][1]] == cube.up.s[1][1]
						&& cube.up.s[subscript_of_up[i%4][0]][subscript_of_up[i%4][1]] != cube.up.s[1][1]
						&& cube.up.s[subscript_of_up[(i + 3) % 4][0]][subscript_of_up[(i + 3) % 4][1]] != cube.up.s[1][1]) {// 形成一个倒"L"
					ch = s[i%4];
					cube.MoveCubeData(ch, 1);
					ch = s[(i + 3) % 4];
					cube.MoveCubeData(ch, 1);
					cube.MoveCubeData("u", 1);
					cube.MoveCubeData(ch, 3);
					cube.MoveCubeData("u", 3);
					ch = s[i%4];
					cube.MoveCubeData(ch, 3);
				}
				if (cube.up.s[subscript_of_up[(i + 1) % 4][0]][subscript_of_up[(i + 1) % 4][1]] == cube.up.s[1][1]
						&& cube.up.s[subscript_of_up[(i + 3) % 4][0]][subscript_of_up[(i + 3) % 4][1]] == cube.up.s[1][1]
						&& cube.up.s[subscript_of_up[i%4][0]][subscript_of_up[i%4][1]] != cube.up.s[1][1]
						&& cube.up.s[subscript_of_up[(i + 2) % 4][0]][subscript_of_up[(i + 2) % 4][1]] != cube.up.s[1][1]) {// 形成一个横"一"
					ch = s[i%4];
					cube.MoveCubeData(ch, 1);
					ch = s[(i + 3) % 4];
					cube.MoveCubeData(ch, 1);
					cube.MoveCubeData("u", 1);
					cube.MoveCubeData(ch, 3);
					cube.MoveCubeData("u", 3);
					ch = s[i%4];
					cube.MoveCubeData(ch, 3);
				}
			}
		}
	}

	public static void UpSurfaceCornerRestore(CubeData cube)// 顶角面位
	{
		while (!(cube.up.s[0][0] == cube.up.s[1][1]
				&& cube.up.s[0][2] == cube.up.s[1][1]
				&& cube.up.s[2][0] == cube.up.s[1][1] && cube.up.s[2][2] == cube.up.s[1][1])) {
			Surface sur[] = new Surface[4];
			sur[0] = cube.front;
			sur[1] = cube.left;
			sur[2] = cube.back;
			sur[3] = cube.right;
			String s[] = { "f", "l", "b", "r" };
			int subscript_of_up[][] = { { 2, 0 }, { 0, 0 }, { 0, 2 }, { 2, 2 } };
			String ch;
			int n;
			for (int i = 0; i < 4; i++) {
				if ((cube.up.s[0][0] != cube.up.s[1][1]
						&& cube.up.s[0][2] != cube.up.s[1][1]
						&& cube.up.s[2][0] != cube.up.s[1][1] && cube.up.s[2][2] != cube.up.s[1][1])
						&& (sur[i%4].s[0][0] == cube.up.s[1][1] && sur[i%4].s[0][2] == cube.up.s[1][1])) {// 十字型（前左右与上同色）
					n = 0;
					while (sur[(i + n) % 4].s[0][1] != sur[(i + n) % 4].s[1][1]) {
						cube.MoveCubeData("u", 1);
						n++;
					}
					ch = s[(i + n + 3) % 4];
					cube.MoveCubeData(ch, 3);
					cube.MoveCubeData("u", 2);
					cube.MoveCubeData(ch, 1);
					cube.MoveCubeData("u", 1);
					cube.MoveCubeData(ch, 3);
					cube.MoveCubeData("u", 1);
					cube.MoveCubeData(ch, 1);
				}
				if (cube.up.s[subscript_of_up[(i + 3) % 4][0]][subscript_of_up[(i + 3) % 4][1]] == cube.up.s[1][1]
						&& cube.up.s[subscript_of_up[i%4][0]][subscript_of_up[i%4][1]] != cube.up.s[1][1]
						&& cube.up.s[subscript_of_up[(i + 1) % 4][0]][subscript_of_up[(i + 1) % 4][1]] != cube.up.s[1][1]
						&& cube.up.s[subscript_of_up[(i + 2) % 4][0]][subscript_of_up[(i + 2) % 4][1]] != cube.up.s[1][1]) {// 鱼头朝右下的鱼
					if (sur[i%4].s[0][0] != cube.up.s[1][1])// 前左与上异色
					{
						ch = s[(i + 3) % 4];
						cube.MoveCubeData(ch, 3);
						cube.MoveCubeData("u", 2);
						cube.MoveCubeData(ch, 1);
						cube.MoveCubeData("u", 1);
						cube.MoveCubeData(ch, 3);
						cube.MoveCubeData("u", 1);
						cube.MoveCubeData(ch, 1);
					} else// 前左与上同色
					{
						cube.MoveCubeData("u", 3);
						ch = s[(i + 3) % 4];
						cube.MoveCubeData(ch, 1);
						cube.MoveCubeData("u", 2);
						cube.MoveCubeData(ch, 3);
						cube.MoveCubeData("u", 3);
						cube.MoveCubeData(ch, 1);
						cube.MoveCubeData("u", 3);
						cube.MoveCubeData(ch, 3);
					}
				}
				if (cube.up.s[subscript_of_up[(i + 1) % 4][0]][subscript_of_up[(i + 1) % 4][1]] == cube.up.s[1][1]
						&& cube.up.s[subscript_of_up[i%4][0]][subscript_of_up[i%4][1]] != cube.up.s[1][1]
						&& cube.up.s[subscript_of_up[(i + 3) % 4][0]][subscript_of_up[(i + 3) % 4][1]] != cube.up.s[1][1]
						&& cube.up.s[subscript_of_up[(i + 2) % 4][0]][subscript_of_up[(i + 2) % 4][1]] == cube.up.s[1][1]) {// 大炮型
					if (sur[i%4].s[0][0] == cube.up.s[1][1]
							&& sur[i%4].s[0][2] == cube.up.s[1][1]) {// 前左右与上同色
						ch = s[(i + 1) % 4];
						cube.MoveCubeData(ch, 3);
						cube.MoveCubeData("u", 2);
						cube.MoveCubeData(ch, 1);
						cube.MoveCubeData("u", 1);
						cube.MoveCubeData(ch, 3);
						cube.MoveCubeData("u", 1);
						cube.MoveCubeData(ch, 1);
					} else {// 前左右与上异色
						ch = s[(i + 2) % 4];
						cube.MoveCubeData(ch, 3);
						cube.MoveCubeData("u", 2);
						cube.MoveCubeData(ch, 1);
						cube.MoveCubeData("u", 1);
						cube.MoveCubeData(ch, 3);
						cube.MoveCubeData("u", 1);
						cube.MoveCubeData(ch, 1);
					}
				}
				if (cube.up.s[subscript_of_up[(i + 3) % 4][0]][subscript_of_up[(i + 3) % 4][1]] == cube.up.s[1][1]
						&& cube.up.s[subscript_of_up[i%4][0]][subscript_of_up[i%4][1]] != cube.up.s[1][1]
						&& cube.up.s[subscript_of_up[(i + 1) % 4][0]][subscript_of_up[(i + 1) % 4][1]] == cube.up.s[1][1]
						&& cube.up.s[subscript_of_up[(i + 2) % 4][0]][subscript_of_up[(i + 2) % 4][1]] != cube.up.s[1][1]) {// 双凌型
					cube.MoveCubeData("u", 3);
					ch = s[(i + 3) % 4];
					cube.MoveCubeData(ch, 3);
					cube.MoveCubeData("u", 2);
					cube.MoveCubeData(ch, 1);
					cube.MoveCubeData("u", 1);
					cube.MoveCubeData(ch, 3);
					cube.MoveCubeData("u", 1);
					cube.MoveCubeData(ch, 1);
				}
			}
		}
	}

	public static void UpCornerRestore(CubeData cube)// 顶角还原
	{
		while (cube.front.s[0][0] != cube.front.s[1][1])
			cube.MoveCubeData("u", 1);
		while (!((cube.front.s[0][0] == cube.front.s[1][1] && cube.front.s[0][2] == cube.front.s[1][1])
				&& (cube.left.s[0][0] == cube.left.s[1][1] && cube.left.s[0][2] == cube.left.s[1][1])
				&& (cube.back.s[0][0] == cube.back.s[1][1] && cube.back.s[0][2] == cube.back.s[1][1]) && (cube.right.s[0][0] == cube.right.s[1][1] && cube.right.s[0][2] == cube.right.s[1][1]))) {
			Surface sur[] = new Surface[4];
			sur[0] = cube.front;
			sur[1] = cube.left;
			sur[2] = cube.back;
			sur[3] = cube.right;
			String s[] = { "f", "l", "b", "r" };
			int n = 0;
			String ch;
			int i;
			for (i = 0; i < 4; i++) {
				n = 0;
				if (sur[i%4].s[0][0] == sur[i%4].s[0][2]) {
					while (sur[(i + n) % 4].s[0][0] != sur[(i + n) % 4].s[1][1]) {
						cube.MoveCubeData("u", 1);
						n++;
					}
					break;
				}
			}
			ch = s[(i + n + 3) % 4];
			cube.MoveCubeData(ch, 1);
			ch = s[(i + n + 2) % 4];
			cube.MoveCubeData(ch, 3);
			ch = s[(i + n + 3) % 4];
			cube.MoveCubeData(ch, 1);
			ch = s[(i + n) % 4];
			cube.MoveCubeData(ch, 2);
			ch = s[(i + n + 3) % 4];
			cube.MoveCubeData(ch, 3);
			ch = s[(i + n + 2) % 4];
			cube.MoveCubeData(ch, 1);
			ch = s[(i + n + 3) % 4];
			cube.MoveCubeData(ch, 1);
			ch = s[(i + n) % 4];
			cube.MoveCubeData(ch, 2);
			ch = s[(i + n + 3) % 4];
			cube.MoveCubeData(ch, 2);
			while (cube.front.s[0][0] != cube.front.s[1][1])
				cube.MoveCubeData("u", 1);
		}
	}

	public static void UpEdgeRestore(CubeData cube)// 顶棱还原
	{
		while (cube.front.s[0][0] != cube.front.s[1][1])
			cube.MoveCubeData("u", 1);
		while (!(cube.front.s[0][1] == cube.front.s[1][1]
				&& cube.left.s[0][1] == cube.left.s[1][1]
				&& cube.back.s[0][1] == cube.back.s[1][1] && cube.right.s[0][1] == cube.right.s[1][1])) {
			Surface sur[] = new Surface[4];
			sur[0] = cube.front;
			sur[1] = cube.left;
			sur[2] = cube.back;
			sur[3] = cube.right;
			String s[] = { "f", "l", "b", "r" };
			int n = 0;
			String ch;
			int i;
			for (i = 0; i < 4; i++) {
				n = 0;
				if (sur[i%4].s[0][0] == sur[i%4].s[0][1]
						&& sur[i%4].s[0][1] == sur[i%4].s[0][2]) {
					while (sur[(i + n) % 4].s[0][0] != sur[(i + n) % 4].s[1][1]) {
						cube.MoveCubeData("u", 1);
						n++;
					}
					break;
				}
			}
			ch = s[(i + n + 1) % 4];
			cube.MoveCubeData(ch, 1);
			cube.MoveCubeData("u", 3);
			cube.MoveCubeData(ch, 1);
			cube.MoveCubeData("u", 1);
			cube.MoveCubeData(ch, 1);
			cube.MoveCubeData("u", 1);
			cube.MoveCubeData(ch, 1);
			cube.MoveCubeData("u", 3);
			cube.MoveCubeData(ch, 3);
			cube.MoveCubeData("u", 3);
			cube.MoveCubeData(ch, 2);
			while (cube.front.s[1][1] != cube.front.s[2][2])
				cube.MoveCubeData("u", 1);
		}
	}

}
