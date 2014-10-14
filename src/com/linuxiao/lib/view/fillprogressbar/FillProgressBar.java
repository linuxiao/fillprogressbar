/**
 * @Title: FillProgressBar.java
 * @Description: TODO
 * @author: linuxiao
 * Email: linuxiao@gmail.com
 * @date 2014年10月13日 下午5:25:59
 */
package com.linuxiao.lib.view.fillprogressbar;

import java.text.NumberFormat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * @author linuxiao
 * Email: linuxiao@gmail.com
 * @date 2014年10月13日 下午5:25:59
 *
 */
public class FillProgressBar extends FrameLayout {
	private FillBarView mBarView;
	private View mContentView;
	private float progress = 0.5f;
	/**
	 * 获取progress
	 * @return progress progress
	 */
	public float getProgress() {
		return progress;
	}

	/**
	 * 设置progress
	 * @param progress progress
	 */
	public void setProgress(float progress) {
		this.progress = progress;
		mBarView.setProgress(progress);
	}
	/**
	 * 获取mContentView
	 * @return mContentView mContentView
	 */
	public View getContentView() {
		if (mContentView == null&&getChildCount()>1) {
			mContentView = getChildAt(1);
		}
		return mContentView;
	}

	/**
	 * 设置mContentView
	 * @param mContentView mContentView
	 */
	public void setContentView(View mContentView) {
		this.mContentView = mContentView;
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public FillProgressBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs, defStyle);
	}

	/**
	 * 初始化
	 * @Title: init
	 * @Description: TODO
	 * @param context
	 * @param attrs
	 * @param defStyle    
	 */
	private void init(Context context, AttributeSet attrs, int defStyle) {
		// TODO Auto-generated method stub
		mBarView = new FillBarView(getContext());
		addView(mBarView);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public FillProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs, 0);
	}

	/**
	 * @param context
	 */
	public FillProgressBar(Context context) {
		super(context);
		init(context, null, 0);
	}
	/* (non-Javadoc)
	 * @see android.widget.FrameLayout#onLayout(boolean, int, int, int, int)
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r,
			int b) {
		mBarView.bringToFront();
		for (int i = 0; i < getChildCount(); i++) {
			View view = getChildAt(i);
			view.layout(l, t, r, b);
		}
	}
	private class FillBarView extends View{

		private Paint[] mPaint;
		private float progress = 0.5f;
		/**
		 * 获取progress
		 * @return progress progress
		 */
		public float getProgress() {
			return progress;
		}

		/**
		 * 设置progress
		 * @param progress progress
		 */
		public void setProgress(float progress) {
			this.progress = progress;
			postInvalidate();
		}
		/**
		 * @param context
		 * @param attrs
		 * @param defStyleAttr
		 */
		public FillBarView(Context context, AttributeSet attrs, int defStyleAttr) {
			super(context, attrs, defStyleAttr);
			init(context, attrs, defStyleAttr);
		}

		/**
		 * @param context
		 * @param attrs
		 */
		public FillBarView(Context context, AttributeSet attrs) {
			super(context, attrs);
			init(context, attrs, 0);
		}

		/**
		 * @param context
		 */
		public FillBarView(Context context) {
			super(context);
			init(context, null, 0);
		}
		private void init(Context context, AttributeSet attrs, int defStyle) {
			mPaint = new Paint[2];
			Paint titlePaint = new Paint();
			titlePaint.setAntiAlias(true);
			titlePaint.setTextSize(25);
			titlePaint.setColor(Color.WHITE);
			mPaint[0] = titlePaint;
			Paint proBarPaint = new Paint();
			proBarPaint.setColor(Color.GRAY);
			proBarPaint.setAlpha(150);
			
			proBarPaint.setAntiAlias(true);
			mPaint[1] = proBarPaint;
			
		}
		/* (non-Javadoc)
		 * @see android.view.View#onDraw(android.graphics.Canvas)
		 */
		@Override
		protected void onDraw(Canvas canvas) {
			//super.onDraw(canvas);
			int w = canvas.getWidth();
			int h = canvas.getHeight();
			
			RectF oval = new RectF(0,0 , w, h*(1-progress));
			canvas.drawRect(oval, mPaint[1]);
			NumberFormat num = NumberFormat.getPercentInstance(); 
			num.setMaximumIntegerDigits(3); 
			num.setMaximumFractionDigits(2);
			String title = num.format(progress);
			int l = (int) mPaint[1].measureText(title);
			if (title.equals("100%")) {
				title = "";
			}
			canvas.drawText(title, w/2 - l/2, h/2-l/2, mPaint[0]);
		}
	}

}
