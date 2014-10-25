package com.example.rubikscube;

import com.example.rubikscube.util.Quaternion;
import com.example.rubikscube.util.Vector3f;

import static com.example.rubikscube.util.Constant.*;

public class HelpThread extends Thread{
	MySurfaceView ms;
	UpsetThread ut;
	ReSetCubeThread rs;
	boolean helpstop = false;
	
	public HelpThread(MySurfaceView ms)
	{
		this.ms = ms;
	}
	public void run()
	{
		try 
		{
			while(!helpstop)
			{
				ms.currAxisX = 0;
				ms.currAxisY = 0;
				ms.currAxisZ = 0;
				ms.angleCurr = 0;
				ms.quaternionTotal = Quaternion.getIdentityQuaternion();
				ms.textId = 0;
				sleep(1000);
				ms.sr.fingerX=0.2f;
				ms.sr.fingerY=-0.5f;
				sleep(500);
				for(int i=0;i<90;i++)
				{
					if(!bwait&&!helpstop)
					{
						Vector3f tmpAxis = new Vector3f(1, -1, 0);
						float tmpAngrad = (float)Math.PI/(4*90);
						Quaternion tmpQuaternion = new Quaternion();
						tmpQuaternion.setToRotateAboutAxis(tmpAxis, tmpAngrad);
						ms.quaternionTotal = ms.quaternionTotal.cross(tmpQuaternion);
						Vector3f axis = ms.quaternionTotal.getRotationAxis();
						float angrad = ms.quaternionTotal.getRotationAngle();
						ms.currAxisX = axis.x;
						ms.currAxisY = axis.y;
						ms.currAxisZ = axis.z;
						ms.angleCurr = (float) Math.toDegrees(angrad);
						
						ms.sr.fingerX-=0.004;
						ms.sr.fingerY-=0.004;
						ms.order();
					}
					else
					{
						i--;
					}
					sleep(10);
				}
				sleep(500);			
				ms.sr.fingerX=100f;
				ms.sr.fingerY=100f;
				sleep(500);	
				ms.sr.fingerX=-0.2f;
				ms.sr.fingerY=-0.7f;
				sleep(500);
				for(int i=0;i<90;i++)
				{
					if(!bwait&&!helpstop)
					{
						Vector3f tmpAxis = new Vector3f(0, 1, 0);
						float tmpAngrad = (float)Math.PI/(3.8f*90);
						Quaternion tmpQuaternion = new Quaternion();
						tmpQuaternion.setToRotateAboutAxis(tmpAxis, tmpAngrad);
						ms.quaternionTotal = ms.quaternionTotal.cross(tmpQuaternion);
						Vector3f axis = ms.quaternionTotal.getRotationAxis();
						float angrad = ms.quaternionTotal.getRotationAngle();
						ms.currAxisX = axis.x;
						ms.currAxisY = axis.y;
						ms.currAxisZ = axis.z;
						ms.angleCurr = (float) Math.toDegrees(angrad);
						
						ms.sr.fingerX+=0.005;
						ms.order();
					}
					else
					{
						i--;
					}
					sleep(10);
				}
				sleep(500);
				ms.textId = -1;
				
				sleep(500);
				ms.textId = 4;
				sleep(500);
				ms.sr.fingerX=100f;
				ms.sr.fingerY=100f;
				ms.sr.isZoomSub = true;
				sleep(500);
				for(int i=0;i<70;i++)
				{
					if(!bwait&&!helpstop)
					{
						ms.s-=0.08;
					}
					else
					{
						i--;
					}
					sleep(10);
				}
				sleep(500);
				ms.sr.isZoomAdd = true;
				sleep(500);
				ms.sr.isZoomSub = false;
				sleep(500);
				for(int i=0;i<70;i++)
				{
					if(!bwait&&!helpstop)
					{
						ms.s+=0.08;
					}
					else
					{
						i--;
					}
					sleep(10);
				}

				sleep(500);
				ms.textId = 1;
				sleep(500);			
				ms.sr.isZoomAdd = false;
				sleep(500);	
				ms.sr.fingerX=-0.1f;
				ms.sr.fingerY=0.05f;
				sleep(500);
				for(int i=0;i<90;i++)
				{
					if(!bwait&&!helpstop)
					{
					ms.sr.fingerX+=0.006;
					ms.sr.fingerY-=0.001;
					}
					else
					{
						i--;
					}
					sleep(10);
				}
				sleep(500);
				ms.sr.fingerX=100f;
				ms.sr.fingerY=100f;
				new RotateThread(1,ms.sr.cc).run();
				sleep(500);	
				ms.sr.fingerX=-0.05f;
				ms.sr.fingerY=0.05f;
				sleep(500);
				for(int i=0;i<90;i++)
				{
					if(!bwait&&!helpstop)
					{
					ms.sr.fingerX-=0.0015;
					ms.sr.fingerY-=0.0055;
					}
					else
					{
						i--;
					}
					sleep(10);
				}
				sleep(500);
				ms.sr.fingerX=100f;
				ms.sr.fingerY=100f;
				new RotateThread(7,ms.sr.cc).run();
				
				sleep(500);
				ms.textId = -1;
				sleep(500);
				ms.textId = 2;
				ms.sr.isMenu = true;
				sleep(1000);
				ms.sr.fingerX=-0.3f;
				ms.sr.fingerY=-0.9f;
				sleep(1000);
				ms.sr.isMenu = false;
				ms.sr.fingerX=100f;
				ms.sr.fingerY=100f;
				ut = new UpsetThread(ms.sr.cc,ms.tt);
				ut.start();
				for(int i=0;i<450;i++)
				{
					if(!bwait&&!helpstop)
					{
					Vector3f tmpAxis = new Vector3f(0, 1, 0);
					float tmpAngrad = (float) ((2*Math.PI)/(450));
					Quaternion tmpQuaternion = new Quaternion();
					tmpQuaternion.setToRotateAboutAxis(tmpAxis, tmpAngrad);
					ms.quaternionTotal = ms.quaternionTotal.cross(tmpQuaternion);
					Vector3f axis = ms.quaternionTotal.getRotationAxis();
					float angrad = ms.quaternionTotal.getRotationAngle();
					ms.currAxisX = axis.x;
					ms.currAxisY = axis.y;
					ms.currAxisZ = axis.z;
					ms.angleCurr = (float) Math.toDegrees(angrad);
					ms.order();
					}
					else
					{
						i--;
					}
					sleep(10);
				}
				
				sleep(500);
				ms.textId = -1;
				sleep(500);
				ms.textId = 3;
				ms.sr.isMenu = true;
				sleep(1000);
				ms.sr.fingerX=0.5f;
				ms.sr.fingerY=-0.9f;
				sleep(1000);
				ms.sr.isMenu = false;
				ms.sr.fingerX=100f;
				ms.sr.fingerY=100f;
				
				
				CubeData t = ms.sr.cc.cubeData.copy();
				String array = ms.sr.cc.cubeData.reSet(3);
				ms.sr.cc.cubeData = t.copy();
				ReSetCubeThread rs = new ReSetCubeThread(array,ms.sr.cc);
				rs.start();
				
				for(int i=0;i<1800;i++)
				{
					if(!bwait&&!helpstop)
					{
					Vector3f tmpAxis = new Vector3f(0, 1, 0);
					float tmpAngrad = (float) ((9*Math.PI)/(1800));
					Quaternion tmpQuaternion = new Quaternion();
					tmpQuaternion.setToRotateAboutAxis(tmpAxis, tmpAngrad);
					ms.quaternionTotal = ms.quaternionTotal.cross(tmpQuaternion);
					Vector3f axis = ms.quaternionTotal.getRotationAxis();
					float angrad = ms.quaternionTotal.getRotationAngle();
					ms.currAxisX = axis.x;
					ms.currAxisY = axis.y;
					ms.currAxisZ = axis.z;
					ms.angleCurr = (float) Math.toDegrees(angrad);
					ms.order();
					}
					else
					{
						i--;
					}
					sleep(10);
					
				}
			
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
