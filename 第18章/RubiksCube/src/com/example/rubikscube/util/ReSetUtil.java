package com.example.rubikscube.util;

import com.example.rubikscube.CubeData;
import com.example.rubikscube.Surface;

public class ReSetUtil {

	public static void F2LRestore(CubeData cube)//ħ���߼���ԭF2L�����Ϊ��ɫ
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
			int subscript_of_up_c[][]={{2,0},{0,0},{0,2},{2,2}};//��
			int subscript_of_up_l[][]={{2,1},{1,0},{0,1},{1,2}};//��
			String s[]= {"f","l","b","r"};
			String ch;
			String chr;
			String chf;
			
			int n = 0;
			
			boolean bturn = false;
			boolean tturn = false;
			
			for(int j=0,i=0;j<4;j++,i++)//�ĸ�����
			{
				//System.out.println("i="+i+" j="+j);
				tturn = false;
				//�����ǰ������Ӧ�Ķ�������½��ǰ׿�
				if(cube.up.s[subscript_of_up_c[i%4][0]][subscript_of_up_c[i%4][1]]==cube.down.s[1][1])
				{
					n=0;
					while(sur[(i+n)%4].s[0][0]!=sur[(i+n+1)%4].s[1][1])
					{
						cube.MoveCubeData("u",1);
						n++;
					}
					
					//s[(i+n)%4]Ϊ��ǰ��ʽ����
					if(sur[(i+n+1)%4].s[1][1]==sur[(i+n+2)%4].s[0][1] &&
						cube.up.s[subscript_of_up_l[(i+n+2)%4][0]][subscript_of_up_l[(i+n+2)%4][1]]==sur[(i+n)%4].s[1][1])//11
					{
						System.out.println("@11$");
						ch=s[(i+n+1)%4];//chΪ��ǰ��ʽǰ�棬��f
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
						ch=s[(i+n)%4];//chΪ��ǰ��ʽ���棬��r
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
						ch=s[(i+n)%4];//chΪ��ǰ��ʽ���棬��r
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
						ch=s[(i+n+1)%4];//chΪ��ǰ��ʽǰ�棬��f
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
						ch=s[(i+n+1)%4];//chΪ��ǰ��ʽǰ�棬��f
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
						ch=s[(i+n)%4];//chΪ��ǰ��ʽ���棬��r
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
						ch=s[(i+n)%4];//chΪ��ǰ��ʽ���棬��r
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
						chr=s[(i+n)%4];//chΪ��ǰ��ʽ���棬��r
						chf=s[(i+n+1)%4];//chΪ��ǰ��ʽǰ�棬��f
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
					//���Ϲ�ʽ11��12��13��14��34��35��36��37�ж�
					
				}
				else if(sur[(i+1)%4].s[0][2]==cube.down.s[1][1])//�����ǰ���0��2λ��Ϊ��ɫ//˳
				{
	
					n=0;
					while(sur[(i+n)%4].s[0][0]!=sur[(i+n)%4].s[1][1])
					{
						cube.MoveCubeData("u",1);
						n++;
					}
					
					//2�Ź�ʽ
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
	
					//4�Ź�ʽ
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
	
					//6�Ź�ʽ
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
	
					//8�Ź�ʽ
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
	
	
	
					//10�Ź�ʽ
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
	
					//21�Ź�ʽ
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
	
					//22�Ź�ʽ
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
	
	
	
	
					//25�Ź�ʽ
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
	
	
					//���Ϲ�ʽ2��4��6��8��10��21��22��25�ж�
	
				}
				else if(sur[i%4].s[0][0]==cube.down.s[1][1])//�����ǰ���0��0λ��Ϊ��ɫ//��
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
						ch=s[(i+n+1)%4];//chΪ��ǰ��ʽǰ�棬��f
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
						ch=s[(i+n)%4];//chΪ��ǰ��ʽ���棬��r
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
						ch=s[(i+n)%4];//chΪ��ǰ��ʽ���棬��r
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
						ch=s[(i+n+1)%4];//chΪ��ǰ��ʽǰ�棬��f
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
					//9�Ź�ʽ
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
	
	
					//23�Ź�ʽ
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
	
					//24�Ź�ʽ
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
	
					//26�Ź�ʽ
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
					//���Ϲ�ʽ1��3��5��7��9��23��24��26�ж�
					
					
				}//����
				
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
		
			}//�Ĳ���
			
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
			
			
			//���ϣ����а�ɫ�ǿ��ڶ�����������Ӧ���Ҳ�ڶ�����ԭ������ 
			//		��û�з�����������������������ж�ģ��
			//���£���Ѱ������������������ƶ������㲢������ѭ���ٴν��������ѭ��
			
//			if(!bturn)
//			{
		
				for(int i=0;i<4;i++)//�ĸ�����
				{
					//�����ǰ������Ӧ�Ķ�������½��ǰ׿�
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
							System.out.println("����ڲ�����");
	
							if(sur[(i+n)%4].s[1][0]==rc&&sur[(i+n+1)%4].s[1][2]==lc)//��ʽ19
							{
								System.out.println("19$");
								ch=s[(i+n)%4];//chΪ��ǰ����ʽ���桱
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
							else if(sur[(i+n)%4].s[1][0]==lc&&sur[(i+n+1)%4].s[1][2]==rc)//��ʽ20
							{
								System.out.println("20$");
								ch=s[(i+n)%4];//chΪ��ǰ����ʽ���桱
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
					
					else if(sur[(i+1)%4].s[0][2]==cube.down.s[1][1])//�����ǰ���0��2λ��Ϊ��ɫ//˳
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
	
							if(sur[(i+n)%4].s[1][0]==rc&&sur[(i+n+1)%4].s[1][2]==lc)//��ʽ16
							{
								System.out.println("16$");
								ch=s[(i+n)%4];//chΪ��ǰ����ʽ���桱
								//UrUR
								cube.MoveCubeData("u",3);
								cube.MoveCubeData(ch,1);
								cube.MoveCubeData("u",3);
								cube.MoveCubeData(ch,3);
								break;
							}
							else if(sur[(i+n)%4].s[1][0]==lc&&sur[(i+n+1)%4].s[1][2]==rc)//��ʽ18
							{
								System.out.println("18$");
								ch=s[(i+n)%4];//chΪ��ǰ����ʽ���桱
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
					else if(sur[i%4].s[0][0]==cube.down.s[1][1])//�����ǰ���0��0λ��Ϊ��ɫ//��
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
	
							if(sur[(i+n)%4].s[1][0]==rc&&sur[(i+n+1)%4].s[1][2]==lc)//��ʽ15
							{
								System.out.println("15$");
								ch=s[(i+n)%4];//chΪ��ǰ����ʽ���桱
								//UruuR
								cube.MoveCubeData("u",3);
								cube.MoveCubeData(ch,1);
								cube.MoveCubeData("u",2);
								cube.MoveCubeData(ch,3);
								break;
							}
							else if(sur[(i+n)%4].s[1][0]==lc&&sur[(i+n+1)%4].s[1][2]==rc)//��ʽ17
							{
								System.out.println("17$");
								ch=s[(i+n+1)%4];//chΪ��ǰ����ʽ���桱
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
					
					
					
					else if(cube.down.s[subscript_of_down[i%4][0]][subscript_of_down[i%4][1]] == cube.down.s[1][1])//�׵�
					{
						if(sur[i%4].s[1][0]==sur[i%4].s[2][0]&&sur[(i+1)%4].s[1][2]==sur[(i+1)%4].s[2][2])//�ѻ�ԭ
						{
							if(sur[i%4].s[1][0]==sur[i%4].s[1][1]&&sur[(i+1)%4].s[1][2]==sur[(i+1)%4].s[1][1])
							{
								System.out.println("�ѻ�ԭ1");
								//�ѻ�ԭ
								continue;
							}
							else//�ó�
							{
								System.out.println("�ó�1");
								ch = s[i%4];
								cube.MoveCubeData(ch, 1);
								cube.MoveCubeData("u", 1);
								cube.MoveCubeData(ch, 3);
							}
						}
						//��ʽ31
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
								//��ʽ32
								
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
								//��ʽ33
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
							if(n==4)//���˵׿�Ų������
							{
								System.out.println("�ó�2");
								ch = s[i%4];
								cube.MoveCubeData(ch, 1);
								cube.MoveCubeData("u", 1);
								cube.MoveCubeData(ch, 3);
							}
						}
						break;
					}
					else if(sur[(i+1)%4].s[2][2]==cube.down.s[1][1])//��˳
					{
						//��ʽ28
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
						//��ʽ30//e
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
								//��ʽ40
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
								//��ʽ41
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
							if(n==4)//���˵׿�Ų������
							{
								System.out.println("�ó�3");
								ch = s[(i)%4];
								cube.MoveCubeData(ch, 1);
								cube.MoveCubeData("u", 1);
								cube.MoveCubeData(ch, 3);
							}
						}
						break;
					}
					else if(sur[i%4].s[2][0]==cube.down.s[1][1])//����
					{
						//��ʽ27e
						if(sur[i%4].s[1][0]==sur[(i+1)%4].s[2][2]
								&&sur[(i+1)%4].s[1][2]==cube.down.s[subscript_of_down[i%4][0]][subscript_of_down[i%4][1]])
						{
							System.out.println("$27");
							ch = s[i%4];
							cube.MoveCubeData(ch, 1);
							cube.MoveCubeData("u", 3);
							cube.MoveCubeData(ch, 3);
						}
						//��ʽ29
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
								//��ʽ38e
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
								//��ʽ39
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
							if(n==4)//���˵׿�Ų������
							{
								System.out.println("�ó�3");
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
		
	}//����
		
		public static void DownCross(CubeData cube)//������ƴ��һ��ʮ��
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
				System.out.println("ʮ��");
				for(int i=0;i<4;i++)
				{
					System.out.println("ʮ����");
					if(cube.down.s[subscript_of_down[i][0]][subscript_of_down[i][1]]==cube.down.s[1][1]
						&&sur[i].s[2][1]!=sur[i].s[1][1])
					{
						ch = s[i];				
						cube.MoveCubeData(ch,2);
					}//�������Ϊ����ɫλ�ò���
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
					}//�����ǵ�������ڶ�������
					if(sur[i].s[0][1]==cube.down.s[1][1])//���������ǵ���ɫ�����
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
					if(sur[i].s[1][0]==cube.down.s[1][1])//���������ǵ���ɫ�����
					{
						ch = s[(i+1)%4];
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,1);
					}
					if(sur[i].s[1][2]==cube.down.s[1][1])//���������ǵ���ɫ�����
					{
						ch = s[(i+3)%4];
						cube.MoveCubeData(ch,1);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,3);
					}
					if(sur[i].s[2][1]==cube.down.s[1][1])//��������ǵ���ɫ�����
					{
						ch = s[i];
						cube.MoveCubeData(ch,1);
						ch = s[(i+1)%4];
						cube.MoveCubeData(ch,3);
						cube.MoveCubeData("u",1);
						cube.MoveCubeData(ch,1);
						ch = s[i];
						cube.MoveCubeData(ch,3);
					}//�����ǲ������ɫ�ǵ���ɫ�����
				}
			}
		}

	public static void DownCornerRestore(CubeData cube)// �׽ǻ�ԭ
	{
		while (!((cube.down.s[0][0] == cube.down.s[1][1]
				&& cube.front.s[2][0] == cube.front.s[1][1] && cube.left.s[2][2] == cube.left.s[1][1])
				&& (cube.down.s[0][2] == cube.down.s[1][1]
						&& cube.front.s[2][2] == cube.front.s[1][1] && cube.right.s[2][0] == cube.right.s[1][1])
				&& (cube.down.s[2][0] == cube.down.s[1][1]
						&& cube.right.s[2][0] == cube.right.s[1][1] && cube.back.s[2][2] == cube.back.s[1][1]) && (cube.down.s[2][2] == cube.down.s[1][1]
				&& cube.back.s[2][0] == cube.back.s[1][1] && cube.right.s[2][2] == cube.right.s[1][1])))// ֱ���׽�ȫ����λ
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
				}// ����ǿ���ɫ��λ����λ�ò���
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
				}// �����е׽�ɫ������
				if (sur[i%4].s[0][0] == cube.down.s[1][1])// �������Ͻ��ǵ���ɫ�����
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
				if (sur[i%4].s[0][2] == cube.down.s[1][1])// �������Ͻ��ǵ���ɫ�����
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
				if (sur[i%4].s[2][0] == cube.down.s[1][1])// �������½��ǵ���ɫ�����
				{
					ch = s[i%4];
					cube.MoveCubeData(ch, 1);
					cube.MoveCubeData("u", 1);
					cube.MoveCubeData(ch, 3);
				}
				if (sur[i%4].s[2][2] == cube.down.s[1][1])// �������½��ǵ���ɫ�����
				{
					ch = s[i%4];
					cube.MoveCubeData(ch, 3);
					cube.MoveCubeData("u", 3);
					cube.MoveCubeData(ch, 1);
				}// �����е���ɫ��
			}
		}
	}

	static void CentreEdgeRestore(CubeData cube)// �����λ
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

	public static void UpCross(CubeData cube)// ����ʮ��
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
						&& cube.up.s[subscript_of_up[(i + 3) % 4][0]][subscript_of_up[(i + 3) % 4][1]] != cube.up.s[1][1]) {// �γ�һ����"L"
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
						&& cube.up.s[subscript_of_up[(i + 2) % 4][0]][subscript_of_up[(i + 2) % 4][1]] != cube.up.s[1][1]) {// �γ�һ����"һ"
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

	public static void UpSurfaceCornerRestore(CubeData cube)// ������λ
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
						&& (sur[i%4].s[0][0] == cube.up.s[1][1] && sur[i%4].s[0][2] == cube.up.s[1][1])) {// ʮ���ͣ�ǰ��������ͬɫ��
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
						&& cube.up.s[subscript_of_up[(i + 2) % 4][0]][subscript_of_up[(i + 2) % 4][1]] != cube.up.s[1][1]) {// ��ͷ�����µ���
					if (sur[i%4].s[0][0] != cube.up.s[1][1])// ǰ��������ɫ
					{
						ch = s[(i + 3) % 4];
						cube.MoveCubeData(ch, 3);
						cube.MoveCubeData("u", 2);
						cube.MoveCubeData(ch, 1);
						cube.MoveCubeData("u", 1);
						cube.MoveCubeData(ch, 3);
						cube.MoveCubeData("u", 1);
						cube.MoveCubeData(ch, 1);
					} else// ǰ������ͬɫ
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
						&& cube.up.s[subscript_of_up[(i + 2) % 4][0]][subscript_of_up[(i + 2) % 4][1]] == cube.up.s[1][1]) {// ������
					if (sur[i%4].s[0][0] == cube.up.s[1][1]
							&& sur[i%4].s[0][2] == cube.up.s[1][1]) {// ǰ��������ͬɫ
						ch = s[(i + 1) % 4];
						cube.MoveCubeData(ch, 3);
						cube.MoveCubeData("u", 2);
						cube.MoveCubeData(ch, 1);
						cube.MoveCubeData("u", 1);
						cube.MoveCubeData(ch, 3);
						cube.MoveCubeData("u", 1);
						cube.MoveCubeData(ch, 1);
					} else {// ǰ����������ɫ
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
						&& cube.up.s[subscript_of_up[(i + 2) % 4][0]][subscript_of_up[(i + 2) % 4][1]] != cube.up.s[1][1]) {// ˫����
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

	public static void UpCornerRestore(CubeData cube)// ���ǻ�ԭ
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

	public static void UpEdgeRestore(CubeData cube)// ���⻹ԭ
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
