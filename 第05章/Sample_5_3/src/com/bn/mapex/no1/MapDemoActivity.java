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
	//北京天安门广场中央经纬度
	public LatLng BEIJING=new LatLng(39.9083,116.3975);  
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);  
	        
	        if (mMap == null) 
	        {//如果map为空则获取
	            mMap = ((SupportMapFragment)(this.getSupportFragmentManager().findFragmentById(R.id.map))).getMap();
	            if (mMap != null) 
	            {
	            	//给map添加点击监听器
	            	mMap.setOnMapClickListener(this);
	            }
	        }
	        //设置地图中心点
	        mMap.moveCamera(CameraUpdateFactory.newLatLng(BEIJING));
	        //设置地图缩放比
	        mMap.moveCamera(CameraUpdateFactory.zoomTo(14));
	        //给地图添加气球点击监听器
	        mMap.setOnMarkerClickListener(this);
	    }
	
	@Override
	public void onMapClick(LatLng arg0) 
	{//地图被点击监听器的实现
		 //在地图被点击的位置添加气球
		 addMarkersToMap(arg0);
	}
	
	@Override
    public boolean onMarkerClick(final Marker marker) 
	{//气球被点击的监听器
		if (marker.equals(marker)) 
		{
		   //可以在此处做一些相应的处理工作			
		}
		return false;
	}
	
	//在地图上的指定位置添加气球
    public void addMarkersToMap(LatLng latlng)
    {
    	MyMarker=mMap.addMarker(new MarkerOptions()
         .position(latlng)
         .title("地理坐标为：")  
         .snippet("纬度："+(double)(Math.round(latlng.latitude*10000)/10000.0)+
        		 "经度："+(double)(Math.round(latlng.longitude*10000)/10000.0))
	     //.icon(BitmapDescriptorFactory.fromResource(R.drawable.ballon)));//使用资源图片
         .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));//使用系统默认图片
    }
}
