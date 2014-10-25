package wyf.ytl;
public class GameDog {
	Sample_9_2 activity = null;		//activity������
	private State currentState;		//���ó���ĳ�ʼ��ǰ״̬Ϊ��ͨ״̬
	public GameDog(Sample_9_2 activity){//������
		this.activity = activity;
		currentState=new CommonState(activity);		//����ĳ�ʼ��ǰ״̬
	}
	public boolean updateState(MasterAction ma){	//��������������״̬�ķ���
		State beforeState=currentState;
		currentState=currentState.toNextState(ma);
		return !(beforeState==currentState);	//����true��ʾ״̬�л��ɹ�
	}
}