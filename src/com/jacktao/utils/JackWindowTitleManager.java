package com.jacktao.utils;

import android.app.ActionBar;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jacktao.R;

/**
 * (1) requestWindowFeature()要在setContentView()之前调用�?
 * (2) 设置各种Feature，是具有排它性的，一旦设置，后续不可更改为别的类型；
 * @author taotao
 * @Date 2014-9-15
 */
public class JackWindowTitleManager {
	private static int mmColor;

	private static int mbBack;

	final String TAG = getClass().getSimpleName();
	
	Activity mActivity;

	public interface JackTitleConst {
		public static final int CUSTOMTITLE_ID_BACK = R.id.title_leftimg;
//		public static final int CUSTOMTITLE_ID_RIGHT1 = R.id.title_righttv;
//		public static final int CUSTOMTITLE_ID_RIGHT2 = R.id.title_rightimg2;
		public static final int CUSTOMTITLE_ID_RIGHTLAYOUT = R.id.layout_title_right;
		public static final int CUSTOMTITLE_ID_MIDTEXT = R.id.title_midtext;
	}

	private ActionBar actionBar;


	public static void init(int mainColor, int backBtnSrc){
		mmColor = mainColor;
		mbBack = backBtnSrc;
	}
	
	public JackWindowTitleManager(Activity mActivity) {
		super();
		this.mActivity = mActivity;
		if(null==mActivity)return;
		actionBar = mActivity.getActionBar();
		actionBar.setCustomView(R.layout.layout_title);// 
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.getCustomView().setBackgroundResource(mmColor);
	}

	public void initBackBtn(){
		initBackBtn(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(null!=mActivity){
					mActivity.finish();
				}
			}
		});
	}
	
	public void initBackBtn(View.OnClickListener listener){
		if(mbBack>0)setComponent(R.id.title_leftimg, mbBack);
		setComponent(R.id.title_leftimg, null!=listener);
		setComponent(R.id.title_leftimg, listener);
	}
	
	
	/**
	 * 
	 */
	public void setComponent(int id, boolean show){
		try{
			actionBar.getCustomView().findViewById(id).setVisibility(show?View.VISIBLE:View.INVISIBLE);
		}catch(Exception e){
			Log.e(TAG, "titleView exception");
		}
	}
	public void setComponent(int id, String text){
		try{
			TextView tv = (TextView)actionBar.getCustomView().findViewById(id);
			tv.setText(text);
		}catch(Exception e){
			Log.e(TAG, "titleView exception");
		}
	}
	
	public void setComponent(int id,View.OnClickListener listener) {
		try{
			View view = actionBar.getCustomView().findViewById(id);
			view.setOnClickListener(listener);
			if(null!=listener) view.setVisibility(View.VISIBLE);
		}catch(Exception e){
			Log.e(TAG, "titleView exception");
		}
	}
	public void setComponent(int id, int resId) {
		try{
			ImageView img = (ImageView) actionBar.getCustomView().findViewById(id);
			img.setImageResource(resId);
			if(resId>0) img.setVisibility(View.VISIBLE);
		}catch(Exception e){
			Log.e(TAG, "titleView exception");
		}
	}

	public void addRight(View view){
		try{
			ViewGroup vg = (ViewGroup) actionBar.getCustomView().findViewById(R.id.layout_title_right);
			vg.addView(view);
		}catch(Exception e){
			Log.e(TAG, "titleView exception");
		}
	}
	
	
}
