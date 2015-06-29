package com.jacktao.utils;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class JackLocationManager implements LocationListener {
	
	private static JackLocationManager jlMana;
	private static Context mContext;
	private JackLocationManager(){
		lm = (LocationManager)mContext. getSystemService(Context.LOCATION_SERVICE);
		mLocListener = this;
	}
	public static JackLocationManager getInstance(Context context){
		if(null==jlMana) {
			mContext = context;
			jlMana = new JackLocationManager();
		}
		return jlMana;
	}
	
	private LocationManager lm;
	private String mProviderName;
	private LocationListener mLocListener;
	private Location mLocation;
	
	public Location getLocation(){
		return mLocation;
	}
	
	public void makeLocationUpdates(){
		if(null==mProviderName) findProviderName()	;
		if(null!=lm&&null!=mProviderName) lm.requestLocationUpdates(mProviderName, 0, 0, mLocListener);
	}

	public void removeLocationUpdates(){
		if(null!=lm) lm.removeUpdates(mLocListener);
	}
	
	
	
	private void findProviderName() {
		Criteria criteria = new Criteria();
		criteria.setCostAllowed(false); // 设置位置服务免费
		criteria.setAccuracy(Criteria.ACCURACY_COARSE); // 设置水平位置精度
		// getBestProvider 只有允许访问调用活动的位置供应商将被返回
		mProviderName = lm.getBestProvider(criteria, true);
		Log.i("findProviderName", "------位置服务：" + mProviderName);
	
	}
	@Override
	public void onLocationChanged(Location location) {
		mLocation = location;
		Log.i("onLocationChanged", mLocation.getLatitude()+"+"+mLocation.getLongitude());
	}
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}
	@Override
	public void onProviderEnabled(String provider) {//not showing?
		JackUtils.showToast(mContext, "provider enable");
	}
	@Override
	public void onProviderDisabled(String provider) {//not showing?
		JackUtils.showToast(mContext, "provider disable");
	}
	
}
