package wyf.ytl;


/**
 * 
 * ����Ϊ���ؼ����߳���
 * ��ʱ��⵱ǰ���ز����ķɻ���״̬
 * Ȼ�����״̬������Ӧ�Ĵ���
 * 
 */
public class PlanMoveThread extends Thread{
	int span = 20;//˯�ߵĺ�����
	int countMove = 0;//�ɻ��ƶ��ļ�����
	int countFine = 0;//�ɻ����ӵ��ļ�����
	int moveN = 3;//ÿ����ѭ���ƶ�һ��
	int fineN = 5;//ÿ���ѭ����һ���ӵ�
	PlaneActivity activity;//Activity������
	private boolean flag = true;//ѭ����־
	public boolean KEY_UP = false;//���ϼ��Ƿ񱻰���
	public boolean KEY_DOWN = false;//���¼��Ƿ񱻰���
	public boolean KEY_LEFT = false;//����ļ�������
	public boolean KEY_RIGHT = false;//���ҵļ�������
	public boolean KEY_A = false;//A�ļ�������
	public boolean KEY_B = false;//B�ļ�������
	GameView gameView = null;
	public PlanMoveThread(PlaneActivity activity,GameView gameView){//������
		this.activity = activity;
		this.gameView = gameView;
	}
	public void setFlag(boolean flag){//���ñ�־λ
		this.flag = flag;
	}
	public void run(){//��д�ķ���
		while(flag){
			if(gameView.pause)continue;
			
			if(activity.gameView.status == 1 || activity.gameView.status == 3){
				if(countMove == 0){//ÿmoveN���ƶ�һ��
					if(KEY_UP == true){//���ϼ�������
						if(!((activity.gameView.plane.getY() - activity.gameView.plane.getSpan()) < ConstantUtil.top)){
							activity.gameView.plane.setY(activity.gameView.plane.getY() - activity.gameView.plane.getSpan());
						}
						activity.gameView.plane.setDir(ConstantUtil.DIR_UP);
					}
					if(KEY_DOWN == true){//���¼�������
						if(!((activity.gameView.plane.getY() + activity.gameView.plane.getSpan()) > ConstantUtil.screenHeight - activity.gameView.plane.bitmap1.getHeight())){
							activity.gameView.plane.setY(activity.gameView.plane.getY() + activity.gameView.plane.getSpan());
						}
						activity.gameView.plane.setDir(ConstantUtil.DIR_DOWN);
					}
					if(KEY_LEFT == true){//�����������
						if(!((activity.gameView.plane.getX() - activity.gameView.plane.getSpan()) < -40)){
							activity.gameView.plane.setX(activity.gameView.plane.getX() - activity.gameView.plane.getSpan());
						}
						activity.gameView.plane.setDir(ConstantUtil.DIR_LEFT);
					}
					if(KEY_RIGHT == true){//���Ҽ�������
						if(!((activity.gameView.plane.getX() + activity.gameView.plane.getSpan()) > ConstantUtil.screenWidth - activity.gameView.plane.bitmap1.getWidth())){
							activity.gameView.plane.setX(activity.gameView.plane.getX() + activity.gameView.plane.getSpan());
						}
						activity.gameView.plane.setDir(ConstantUtil.DIR_RIGHT);
					}
					if(KEY_RIGHT == false && KEY_LEFT == false && KEY_DOWN == false && KEY_UP == false){
						activity.gameView.plane.setDir(ConstantUtil.DIR_STOP);
					}
					if(countFine == 0){//ÿfineN��һ���ӵ�
						if(KEY_A == true){//A��������
							activity.gameView.plane.fire();
						}
						if(KEY_B == true){//B��������
		
						}
					}
				}
				countMove = (countMove+1)%moveN;
				countFine = (countFine+1)%fineN;
			}
			try{
				Thread.sleep(span);//˯��ָ��������
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}