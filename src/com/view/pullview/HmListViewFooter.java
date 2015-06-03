package com.view.pullview;

import com.jacktao.utils.JackUtils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * 描述：加载更多Footer View�?
 *
 * @author xufx
 */
public class HmListViewFooter extends LinearLayout {
	
	/** The m context. */
	private Context mContext;
	
	/** The m state. */
    private int mState = -1;
	
	/** The Constant STATE_READY. */
	public final static int STATE_READY = 1;
	
	/** The Constant STATE_LOADING. */
	public final static int STATE_LOADING = 2;
	
	/** The Constant STATE_NO. */
	public final static int STATE_NO = 3;
	
	/** The Constant STATE_EMPTY. */
	public final static int STATE_EMPTY = 4;

	/** The footer view. */
	private LinearLayout footerView;
	
	/** The footer progress bar. */
	private ProgressBar footerProgressBar;
	
	/** The footer text view. */
	private TextView footerTextView;
	
	/** The footer content height. */
	private int footerHeight;
	
	/**
	 * Instantiates a new ab list view footer.
	 *
	 * @param context the context
	 */
	public HmListViewFooter(Context context) {
		super(context);
		initView(context);
	}

	/**
	 * Instantiates a new ab list view footer.
	 *
	 * @param context the context
	 * @param attrs the attrs
	 */
	public HmListViewFooter(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
		setState(STATE_READY);
	}
	
	/**
	 * Inits the view.
	 *
	 * @param context the context
	 */
	private void initView(Context context) {
		mContext = context;
		
		//底部刷新
		footerView  = new LinearLayout(context);  
		//设置布局 水平方向  
		footerView.setOrientation(LinearLayout.HORIZONTAL);
		footerView.setGravity(Gravity.CENTER); 
		footerView.setMinimumHeight(100);
		footerTextView = new TextView(context);  
		footerTextView.setGravity(Gravity.CENTER_VERTICAL);
		setTextColor(Color.rgb(107, 107, 107));
//		HmViewUtil.setTextSize(footerTextView,30);
		
		footerView.setPadding(0, 10, 0, 10);
		
		footerProgressBar = new ProgressBar(context,null,android.R.attr.progressBarStyle);
		footerProgressBar.setVisibility(View.GONE);
		
		LinearLayout.LayoutParams layoutParamsWW = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		layoutParamsWW.gravity = Gravity.CENTER;
		layoutParamsWW.width =50;// HmViewUtil.scaleValue(mContext, 50);
		layoutParamsWW.height = 50;//HmViewUtil.scaleValue(mContext, 50);
		layoutParamsWW.rightMargin = 10;//HmViewUtil.scaleValue(mContext, 10);
		footerView.addView(footerProgressBar,layoutParamsWW);
		
		LinearLayout.LayoutParams layoutParamsWW1 = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		footerView.addView(footerTextView,layoutParamsWW1);
		
		LinearLayout.LayoutParams layoutParamsFW = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		addView(footerView,layoutParamsFW);
		
		//获取View的高�?		JackUtils.measureView(this);
		footerHeight = this.getMeasuredHeight();
	}

	/**
	 * 设置当前状�?.
	 *
	 * @param state the new state
	 */
	public void setState(int state) {
		
		if (state == STATE_READY) {
			footerView.setVisibility(View.VISIBLE);
			footerTextView.setVisibility(View.VISIBLE);
			footerProgressBar.setVisibility(View.GONE);
			footerTextView.setText("载入更多");
		} else if (state == STATE_LOADING) {
			footerView.setVisibility(View.VISIBLE);
			footerTextView.setVisibility(View.VISIBLE);
			footerProgressBar.setVisibility(View.VISIBLE);
			footerTextView.setText("正在加载...");
		}else if(state == STATE_NO){
			footerView.setVisibility(View.GONE);
			footerTextView.setVisibility(View.VISIBLE);
			footerProgressBar.setVisibility(View.GONE);
			footerTextView.setText("没有了！");
		}else if(state == STATE_EMPTY){
			footerView.setVisibility(View.GONE);
			footerTextView.setVisibility(View.GONE);
			footerProgressBar.setVisibility(View.GONE);
			footerTextView.setText("没有数据");
		}
		mState = state;
	}
	
	/**
	 * Gets the visiable height.
	 * @return the visiable height
	 */
	public int getVisiableHeight() {
		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)footerView.getLayoutParams();
		return lp.height;
	}

	/**
	 * 隐藏footerView.
	 */
	public void hide() {
		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) footerView.getLayoutParams();
		lp.height = 0;
		footerView.setLayoutParams(lp);
		footerView.setVisibility(View.GONE);
	}

	/**
	 * 显示footerView.
	 */
	public void show() {
		footerView.setVisibility(View.VISIBLE);
		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) footerView.getLayoutParams();
		lp.height = LayoutParams.WRAP_CONTENT;
		footerView.setLayoutParams(lp);
	}

	
	/**
	 * 描述：设置字体颜�?
	 *
	 * @param color the new text color
	 */
	public void setTextColor(int color){
		footerTextView.setTextColor(color);
	}
	
	/**
	 * 描述：设置字体大�?
	 *
	 * @param size the new text size
	 */
	public void setTextSize(int size){
		footerTextView.setTextSize(size);
	}
	
	/**
	 * 描述：设置背景颜�?
	 *
	 * @param color the new background color
	 */
	public void setBackgroundColor(int color){
		footerView.setBackgroundColor(color);
	}

	/**
	 * 描述：获取Footer ProgressBar，用于设置自定义样式.
	 *
	 * @return the footer progress bar
	 */
	public ProgressBar getFooterProgressBar() {
		return footerProgressBar;
	}

	/**
	 * 描述：设置Footer ProgressBar样式.
	 *
	 * @param indeterminateDrawable the new footer progress bar drawable
	 */
	public void setFooterProgressBarDrawable(Drawable indeterminateDrawable) {
		footerProgressBar.setIndeterminateDrawable(indeterminateDrawable);
	}

	/**
	 * 描述：获取高�?
	 *
	 * @return the footer height
	 */
	public int getFooterHeight() {
		return footerHeight;
	}
	
	/**
	 * 设置高度.
	 *
	 * @param height 新的高度
	 */
	public void setVisiableHeight(int height) {
		if (height < 0) height = 0;
		LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) footerView.getLayoutParams();
		lp.height = height;
		footerView.setLayoutParams(lp);
	}
	
	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public int getState(){
	    return mState;
	}
	

}
