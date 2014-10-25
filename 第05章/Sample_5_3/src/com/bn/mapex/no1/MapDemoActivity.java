package com.bn.mapex.no1;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;        
import com.google.android.gms.maps.model.MarkerOptions;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.annotation.SuppressLint;
import com.google.android.gms.maps.*;

public class MapDemoActivity extends FragmentActivity  
		implements OnMapClickListener,OnMarkerClickListener
{
	public GoogleMap mMap;
	public Marker MyMarker;
	//�����찲�Ź㳡���뾭γ��
	public LatLng BEIJING=new LatLng(39.9083,116.3975);  
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);  
	        
	        if (mMap == null) 
	        {//���mapΪ�����ȡ
	            mMap = ((SupportMapFragment)(this.getSupportFragmentManager().findFragmentById(R.id.map))).getMap();
	            if (mMap != null) 
	            {
	            	//��map��ӵ��������
	            	mMap.setOnMapClickListener(this);
	            }
	        }
	        //���õ�ͼ���ĵ�
	        mMap.moveCamera(CameraUpdateFactory.newLatLng(BEIJING));
	        //���õ�ͼ���ű�
	        mMap.moveCamera(CameraUpdateFactory.zoomTo(14));
	        //����ͼ���������������
	        mMap.setOnMarkerClickListener(this);
	    }
	
	@Override
	public void onMapClick(LatLng arg0) 
	{//��ͼ�������������ʵ��
		 //�ڵ�ͼ�������λ���������
		 addMarkersToMap(arg0);
	}
	
	@Override
    public boolean onMarkerClick(final Marker marker) 
	{//���򱻵���ļ�����
		if (marker.equals(marker)) 
		{
		   //�����ڴ˴���һЩ��Ӧ�Ĵ�����			
		}
		return false;
	}
	
	//�ڵ�ͼ�ϵ�ָ��λ���������
    public void addMarkersToMap(LatLng latlng)
    {
    	MyMarker=mMap.addMarker(new MarkerOptions()
         .position(latlng)
         .title("��������Ϊ��")  
         .snippet("γ�ȣ�"+(double)(Math.round(latlng.latitude*10000)/10000.0)+
        		 "���ȣ�"+(double)(Math.round(latlng.longitude*10000)/10000.0))
	     //.icon(BitmapDescriptorFactory.fromResource(R.drawable.ballon)));//ʹ����ԴͼƬ
         .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));//ʹ��ϵͳĬ��ͼƬ
    }
}
