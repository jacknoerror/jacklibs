package com.jacktao.ui.custom.getter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.jacktao.R;
import com.nostra13.universalimageloader.core.ImageLoader;

public class ViewPagerGetter extends JackGetter implements ViewPager.OnPageChangeListener {
	public interface IvInVpImpl{
		String getImage();
		View.OnClickListener getListener();
	}
	
	private ViewPager mViewPager;
	private ArrayList<ImageView> spotList;
	private LinearLayout spotLayout;

	public ViewPagerGetter(Context context) {
		super(context);
	}

	public final ViewPager getmViewPager() {
		return mViewPager;
	}

	public final ArrayList<ImageView> getSpotList() {
		return spotList;
	}

	public void init(ViewPager viewPager, LinearLayout spotLayout	){
		this.mViewPager = viewPager;
		this.spotLayout = spotLayout;
	}
	/**
	 * 
	 */
	public void setup(	IvInVpImpl[] iiviArr) {
		if(null==spotLayout||null==mViewPager) throw new IllegalStateException("请先调用init方法,设置viewPager和spotLayout");
		List<View> vList = new ArrayList<View>();
		spotList = new ArrayList<ImageView>();
		spotLayout.removeAllViews();//0121
		for (IvInVpImpl iiv : iiviArr) {// cpProduct.getProductImg()
			ImageView img = new ImageView(mContext);
			img.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT));
			img.setScaleType(ScaleType.CENTER_CROP);
			ImageLoader.getInstance().displayImage(iiv.getImage(), img);
			vList.add(img);
			img.setOnClickListener(iiv.getListener());
			// spot
			FrameLayout layoutWithImg = new FrameLayout(mContext);
			layoutWithImg.setPadding(5, 5, 5, 5);
			ImageView im = new ImageView(mContext); 
			im.setImageResource(R.drawable.spot_selector_big);
			layoutWithImg.addView(im);
			spotLayout.addView(layoutWithImg);
			spotList.add(im);
		}
		setSpotSelected(0);
		if (spotList.size() <= 1)
			spotLayout.setVisibility(View.INVISIBLE);// 只有一个则不显示
		mViewPager.setAdapter(new MyPagerAdapater(vList));
		mViewPager.setOnPageChangeListener(this);
	}

	private void setSpotSelected(int index) {
		if (mViewPager == null || spotList == null || spotList.size() == 0)
			return;
		View viewSelected = (View) mViewPager.getTag();
		if (viewSelected != null) {
			viewSelected.setSelected(false);
		}
		spotList.get(index).setSelected(true);
		spotList.get(index).requestFocus();
		mViewPager.setTag(spotList.get(index));
	}


	@Override
	public void onPageScrollStateChanged(int arg0) {
		// 1.2.0
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int arg0) {
		if (arg0 >= spotList.size() || arg0 < 0 || spotList.size() <= 1)
			return;
		setSpotSelected(arg0);
	}

	public class MyPagerAdapater extends PagerAdapter {
		List<View> viewList;

		public MyPagerAdapater(List<View> viewList) {
			this.viewList = viewList;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public int getCount() {
			return this.viewList.size();
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView(this.viewList.get(position));
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return position + "";
		}

		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager) container).addView(this.viewList.get(position));
			return this.viewList.get(position);
		}
	}
}
