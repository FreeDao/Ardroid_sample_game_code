package com.example.rubikscube;

import static com.example.rubikscube.util.Constant.*;

public class RotateThread extends Thread {

	int function;// ¶¯»­±àºÅ

	int angle;
	CubesControl cc;

	public RotateThread(int function,CubesControl cc) {
		this.function = function;
		this.cc = cc;
	}

	public void run() {
		if(bse)
		{
			MySurfaceView.playSound(1, 0);
		}
		switch (function) {
		case 0:
			cc.cubeData.manMoveCubeData("u", 1);
			angle = 0;
			while (angle < 90) {
				for (int i = 0; i < cc.cubesurfaceGroup.length; i++) {
					if (cc.cubesurfaceGroup[i].oldY == 1) {
						cc.cubesurfaceGroup[i].angleY--;

					}
				}

				angle++;
				try {
					Thread.sleep(MS_PER_FRAME);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		case 1:
			cc.cubeData.manMoveCubeData("u", 3);
			angle = 0;
			while (angle < 90) {
				for (int i = 0; i < cc.cubesurfaceGroup.length; i++) {
					if (cc.cubesurfaceGroup[i].oldY == 1) {
						cc.cubesurfaceGroup[i].angleY++;

					}
				}
				angle++;
				try {
					Thread.sleep(MS_PER_FRAME);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		case 2:
			cc.cubeData.manMoveCubeData("mu", 1);
			angle = 0;
			while (angle < 90) {
				for (int i = 0; i < cc.cubesurfaceGroup.length; i++) {
					if (cc.cubesurfaceGroup[i].oldY == 0) {
						cc.cubesurfaceGroup[i].angleY--;

					}
				}
				angle++;
				try {
					Thread.sleep(MS_PER_FRAME);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		case 3:
			cc.cubeData.manMoveCubeData("mu", 3);
			angle = 0;
			while (angle < 90) {
				for (int i = 0; i < cc.cubesurfaceGroup.length; i++) {
					if (cc.cubesurfaceGroup[i].oldY == 0) {
						cc.cubesurfaceGroup[i].angleY++;

					}
				}
				angle++;
				try {
					Thread.sleep(MS_PER_FRAME);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		case 4:
			cc.cubeData.manMoveCubeData("d", 3);
			angle = 0;
			while (angle < 90) {
				for (int i = 0; i < cc.cubesurfaceGroup.length; i++) {
					if (cc.cubesurfaceGroup[i].oldY == -1) {
						cc.cubesurfaceGroup[i].angleY--;

					}
				}
				angle++;
				try {
					Thread.sleep(MS_PER_FRAME);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		case 5:
			cc.cubeData.manMoveCubeData("d", 1);
			angle = 0;
			while (angle < 90) {
				for (int i = 0; i < cc.cubesurfaceGroup.length; i++) {
					if (cc.cubesurfaceGroup[i].oldY == -1) {
						cc.cubesurfaceGroup[i].angleY++;

					}
				}
				angle++;
				try {
					Thread.sleep(MS_PER_FRAME);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;

		case 6:
			cc.cubeData.manMoveCubeData("l", 3);
			angle = 0;
			while (angle < 90) {
				for (int i = 0; i < cc.cubesurfaceGroup.length; i++) {
					if (cc.cubesurfaceGroup[i].oldX == -1) {
						cc.cubesurfaceGroup[i].angleX--;
					}
				}
				angle++;
				try {
					Thread.sleep(MS_PER_FRAME);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		case 7:
			cc.cubeData.manMoveCubeData("l", 1);
			angle = 0;
			while (angle < 90) {
				for (int i = 0; i < cc.cubesurfaceGroup.length; i++) {
					if (cc.cubesurfaceGroup[i].oldX == -1) {
						cc.cubesurfaceGroup[i].angleX++;
					}
				}
				angle++;
				try {
					Thread.sleep(MS_PER_FRAME);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		case 8:
			cc.cubeData.manMoveCubeData("mr", 1);
			angle = 0;
			while (angle < 90) {
				for (int i = 0; i < cc.cubesurfaceGroup.length; i++) {
					if (cc.cubesurfaceGroup[i].oldX == 0) {
						cc.cubesurfaceGroup[i].angleX--;
					}
				}
				angle++;
				try {
					Thread.sleep(MS_PER_FRAME);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		case 9:
			cc.cubeData.manMoveCubeData("mr", 3);
			angle = 0;
			while (angle < 90) {
				for (int i = 0; i < cc.cubesurfaceGroup.length; i++) {
					if (cc.cubesurfaceGroup[i].oldX == 0) {
						cc.cubesurfaceGroup[i].angleX++;
					}
				}
				angle++;
				try {
					Thread.sleep(MS_PER_FRAME);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		case 10:
			cc.cubeData.manMoveCubeData("r", 1);
			angle = 0;
			while (angle < 90) {
				for (int i = 0; i < cc.cubesurfaceGroup.length; i++) {
					if (cc.cubesurfaceGroup[i].oldX == 1) {
						cc.cubesurfaceGroup[i].angleX--;
					}
				}
				angle++;
				try {
					Thread.sleep(MS_PER_FRAME);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;		
		case 11:
			cc.cubeData.manMoveCubeData("r", 3);
			angle = 0;
			while (angle < 90) {
				for (int i = 0; i < cc.cubesurfaceGroup.length; i++) {
					if (cc.cubesurfaceGroup[i].oldX == 1) {
						cc.cubesurfaceGroup[i].angleX++;

					}
				}
				angle++;
				try {
					Thread.sleep(MS_PER_FRAME);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		
		
		case 12:
			cc.cubeData.manMoveCubeData("f", 3);
			angle = 0;
			while (angle < 90) {
				for (int i = 0; i < cc.cubesurfaceGroup.length; i++) {
					if (cc.cubesurfaceGroup[i].oldZ == 1) {
						cc.cubesurfaceGroup[i].angleZ++;
					}
				}
				angle++;
				try {
					Thread.sleep(MS_PER_FRAME);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		case 13:
			cc.cubeData.manMoveCubeData("f", 1);
			angle = 0;
			while (angle < 90) {
				for (int i = 0; i < cc.cubesurfaceGroup.length; i++) {
					if (cc.cubesurfaceGroup[i].oldZ == 1) {
						cc.cubesurfaceGroup[i].angleZ--;

					}
				}
				angle++;
				try {
					Thread.sleep(MS_PER_FRAME);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		case 14:
			cc.cubeData.manMoveCubeData("mf", 3);
			angle = 0;
			while (angle < 90) {
				for (int i = 0; i < cc.cubesurfaceGroup.length; i++) {
					if (cc.cubesurfaceGroup[i].oldZ == 0) {
						cc.cubesurfaceGroup[i].angleZ++;

					}
				}
				angle++;
				try {
					Thread.sleep(MS_PER_FRAME);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		case 15:
			cc.cubeData.manMoveCubeData("mf", 1);
			angle = 0;
			while (angle < 90) {
				for (int i = 0; i < cc.cubesurfaceGroup.length; i++) {
					if (cc.cubesurfaceGroup[i].oldZ == 0) {
						cc.cubesurfaceGroup[i].angleZ--;

					}
				}
				angle++;
				try {
					Thread.sleep(MS_PER_FRAME);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		case 16:
			cc.cubeData.manMoveCubeData("b", 1);
			angle = 0;
			while (angle < 90) {
				for (int i = 0; i < cc.cubesurfaceGroup.length; i++) {
					if (cc.cubesurfaceGroup[i].oldZ == -1) {
						cc.cubesurfaceGroup[i].angleZ++;

					}
				}
				angle++;
				try {
					Thread.sleep(MS_PER_FRAME);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		case 17:
			cc.cubeData.manMoveCubeData("b", 3);
			angle = 0;
			while (angle < 90) {
				for (int i = 0; i < cc.cubesurfaceGroup.length; i++) {
					if (cc.cubesurfaceGroup[i].oldZ == -1) {
						cc.cubesurfaceGroup[i].angleZ--;

					}
				}
				angle++;
				try {
					Thread.sleep(MS_PER_FRAME);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			break;
		}
		

		for (int i = 0; i < cc.cubesurfaceGroup.length; i++) {

			cc.cubesurfaceGroup[i].angleX = 0;
			cc.cubesurfaceGroup[i].angleY = 0;
			cc.cubesurfaceGroup[i].angleZ = 0;
		}
		
		cc.reSetColor();
		
	}

}
