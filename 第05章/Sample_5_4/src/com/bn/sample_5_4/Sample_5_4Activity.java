package com.bn.sample_5_4;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sample_5_4Activity extends Activity {
	private BluetoothAdapter btAdapter = null;// ��������������
	private MyService myService = null;// Service����
	private EditText outEt;// �����еĿؼ�����
	private Button sendBtn;
	private String connectedNameStr = null;// �����ӵ��豸����
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // ��ȡ��������������
		btAdapter = BluetoothAdapter.getDefaultAdapter();
    }
	@Override
	protected void onStart() {
		super.onStart();
		// �������û�п�������ʾ�������������˳�Activity
		if(!btAdapter.isEnabled()){
			Toast.makeText(this, "���ȿ���������", Toast.LENGTH_LONG).show();
			finish();
		}else{//�����ʼ������ؼ�
			if(myService==null){
				initChat();
			}
		}
	}
	@Override
	public synchronized void onResume() {
		super.onResume();		
		if (myService == null) {// ����������Service
			// ���ServiceΪ��״̬
			if (myService.getState() == MyService.STATE_NONE) {
				myService.start();// ����Service
			}
		}
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (myService != null) {// ֹͣService
			myService.stop();
		}
	}
	private void initChat(){
		outEt=(EditText)this.findViewById(R.id.editText);//��ȡ�༭�ı��������
		sendBtn=(Button)this.findViewById(R.id.btn_send);//��ȡ��ť������
		sendBtn.setOnClickListener(new OnClickListener(){//Ϊ���Ͱ�ť��Ӽ�����
			@Override
			public void onClick(View v) {
				//��ñ༭�ı�����ı����ݣ���������Ϣ
				String msg=outEt.getText().toString();
				sendMessage(msg);
			}});
		myService = new MyService(this, mHandler);// ����Service����
	}
	private void sendMessage(String msg){
		// �ȼ���Ƿ��Ѿ����ӵ��豸
		if (myService.getState() != MyService.STATE_CONNECTED) {
			Toast.makeText(this, "δ���ӵ��豸��", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		if(msg.length()>0){//�豸�Ѿ�����
			byte[] send = msg.getBytes();// ��ȡ������Ϣ���ֽ�����
			myService.write(send);//����
			outEt.setText("");//��ձ༭��
		}
	}
	// �����Service��������Ϣ��Handler
	private final Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
			case Constant.MSG_READ:
				byte[] readBuf = (byte[]) msg.obj;
				// �������յ���Ϣ���ַ���
				String readMessage = new String(readBuf, 0, msg.arg1);//��ϵͳĬ�ϵ��ַ������ֽ������һ������ת��Ϊ�ַ���
				Toast.makeText(Sample_5_4Activity.this,
						connectedNameStr + ":  " + readMessage,
						Toast.LENGTH_LONG).show();//��ʾ���ĸ��豸���յ�ʲô�����ַ���
				break;
			case Constant.MSG_DEVICE_NAME:
				// ��ȡ�����ӵ��豸���ƣ���������ʾ��Ϣ
				connectedNameStr = msg.getData().getString(
						Constant.DEVICE_NAME);
				Toast.makeText(getApplicationContext(),
						"�����ӵ� " + connectedNameStr, Toast.LENGTH_SHORT)
						.show();
				break;
			}
		}
	};
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case Constant.REQUEST_CODE:               //data�����˷�������
			// ����豸�б�Activity����һ�����ӵ��豸
			if (resultCode == Activity.RESULT_OK) {
				// ��ȡ�豸��MAC��ַ
				String address = data.getExtras().getString(
						MyDeviceListActivity.EXTRA_DEVICE_ADDR);
				// ��ȡBLuetoothDevice����,���ݸ�����address
				BluetoothDevice device = btAdapter.getRemoteDevice(address);
				myService.connect(device);// ���Ӹ��豸
			}
			break;
		}
	}
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// �����豸�б�Activity�����豸
		Intent serverIntent = new Intent(this, MyDeviceListActivity.class);
		startActivityForResult(serverIntent, Constant.REQUEST_CODE);
		return true;
	}
}