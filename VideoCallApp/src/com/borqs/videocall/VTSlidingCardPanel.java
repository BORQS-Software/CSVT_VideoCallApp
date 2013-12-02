/* BORQS Software Solutions Pvt Ltd. CONFIDENTIAL
* Copyright (c) 2012 All rights reserved.
*
* The source code contained or described herein and all documents
* related to the source code ("Material") are owned by BORQS Software
* Solutions Pvt Ltd. No part of the Material may be used,copied,
* reproduced, modified, published, uploaded,posted, transmitted,
* distributed, or disclosed in any way without BORQS Software
* Solutions Pvt Ltd. prior written permission.
*
* No license under any patent, copyright, trade secret or other
* intellectual property right is granted to or conferred upon you
* by disclosure or delivery of the Materials, either expressly, by
* implication, inducement, estoppel or otherwise. Any license
* under such intellectual property rights must be express and
* approved by BORQS Software Solutions Pvt Ltd. in writing.
*
*/

package com.borqs.videocall;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;

/**
 * 
 * Panel to draw sliding card
 *
 */

public class VTSlidingCardPanel extends LinearLayout {
	static final String TAG = "VTSlidingCardPanel";
	private final boolean DBG = true;
	private Context mContext;
	
	private int mUpLimitTop;
	private int mDownLimitTop;
	private int mMidPosTop; 

	public VTSlidingCardPanel(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.VTSlidingCardPanel);		
		mContext = context;

		mUpLimitTop = (int) a.getDimension(R.styleable.VTSlidingCardPanel_up_limit_top,	0.0f);
		mDownLimitTop = (int) a.getDimension(R.styleable.VTSlidingCardPanel_down_limit_top,	0.0f);
		mMidPosTop = (int) a.getDimension(R.styleable.VTSlidingCardPanel_mid_pos_top,	0.0f);
		log("mUpLimitTop: " + mUpLimitTop);
		log("mDownLimitTop: " + mDownLimitTop);
		log("mMidPosTop: " + mMidPosTop);
		a.recycle();		
	}	
	
	/* interfaces to get position value */
	public int getUpLimitTop() {
		return mUpLimitTop;
	}
	
	public int getDownLimitTop() {
		return mDownLimitTop;
	}
	
	public int getMidPosTop() {
		return mMidPosTop;
	}
	
	private void log(String info) {
		if (DBG) {
			if (MyLog.DEBUG) MyLog.d(TAG, info);			
		}
	}
}
