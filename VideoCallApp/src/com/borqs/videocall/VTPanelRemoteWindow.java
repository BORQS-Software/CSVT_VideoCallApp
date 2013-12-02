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
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class VTPanelRemoteWindow extends VTPanelComponent{
	private static final String TAG = "VTPanelRemoteWindow";
	
	public VTPanelRemoteWindow(Context context, AttributeSet attrs) {
		super(context, attrs);
		initLayoutParam(context, attrs);
	}

	@Override
	void InitVisibility() {
		// TODO Auto-generated method stub
		mVisibilities[VTPanel.VTPANEL_MODE_KEYPAD_LOCAL] = View.VISIBLE;
		mVisibilities[VTPanel.VTPANEL_MODE_KEYPAD_REMOTE] = View.VISIBLE;
		mVisibilities[VTPanel.VTPANEL_MODE_LOCAL] = View.VISIBLE;
		mVisibilities[VTPanel.VTPANEL_MODE_ANSWERIN_CONNECTING] = View.GONE;
		mVisibilities[VTPanel.VTPANEL_MODE_REMOTE] = View.VISIBLE;
		mVisibilities[VTPanel.VTPANEL_MODE_DAILOUT_CONNECTING] = View.GONE;	
		mVisibilities[VTPanel.VTPANEL_MODE_REMOTE_ONLY] = View.VISIBLE;
		mVisibilities[VTPanel.VTPANEL_MODE_KEYPAD_REMOTE_ONLY] = View.VISIBLE;
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		int action = ev.getAction();
		
		if (mPanel == null) {
			throw new IllegalArgumentException(
					"vtpanel does not exist");
		}
		
		VTPanel.IPanelManager manager = mPanel.getPanelManager();
		if (manager == null) {
			// panel is not built yet
			return false;
		}
		
		if (action == MotionEvent.ACTION_DOWN) {
			manager.onTouchDownEvent(this, ev);
		} else if (action == MotionEvent.ACTION_CANCEL) {
			manager.onTouchCancelEvent(this, ev);
		} else if (mhasTouchDown && action == MotionEvent.ACTION_UP){
			// switch to control button mode
			manager.onTouchCancelEvent(this, ev);
			manager.onClickEvent(this);			
		}
		return super.dispatchTouchEvent(ev);
	}	
	
	private void log(String info) {
		if (DBG) {
			if (MyLog.DEBUG) MyLog.d(TAG, info);
		}
	}
}
