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

import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;


public class CallTime extends Handler {
    private static final String TAG = "VT/CallTime";
    private static final boolean DBG = false;

    private long mBaseTime = -1;
    private boolean mTimerRunning = false;
    final private long mInterval = 1000;  //update every second
	final private long mIntervalRound = mInterval / 2;
    private PeriodicTimerCallback mTimerCallback;
    private OnTickListener mListener;

    interface OnTickListener {
        void onTickForCallTimeElapsed(long timeElapsed);
    }

    public CallTime( OnTickListener listener) {
        mListener = listener;
        mTimerCallback = new PeriodicTimerCallback();
    }

    void startTimer(){
    	log("start timer, basetime:" + mBaseTime);
        mBaseTime = SystemClock.uptimeMillis();
    	long nextReport = mBaseTime + mInterval; 
    	mTimerRunning = true;
    	postAtTime(mTimerCallback, nextReport);
    }
    
    /* package */ 
    private void periodicUpdateTimer() {
    	
    	if( mTimerRunning == false){
    		if (DBG) log("timer not start yet, fake case, just return.");
    		return;
    	}
    	
    	long now = SystemClock.uptimeMillis();
    	long elapsed = ( now - mBaseTime + mIntervalRound)/mInterval;
    	
    	mListener.onTickForCallTimeElapsed( elapsed);
    	
    	long nextReport = mBaseTime + ( elapsed + 1)*mInterval;
    	postAtTime(mTimerCallback, nextReport);
    	if(DBG)log("now: " + now + " nextReport:" + nextReport);

    }

    void cancelTimer() {
        if (DBG) log("cancelTimer()......");
        mTimerRunning = false;
        removeCallbacks(mTimerCallback);
    }
    
    private static void log(String msg) {
        if (MyLog.DEBUG) MyLog.d(TAG, "[CallTime] " + msg);
    }

    private class PeriodicTimerCallback implements Runnable {
        PeriodicTimerCallback() {	
        }
        
        public void run() {
            periodicUpdateTimer();
        }
    }

	/* return call during in format: 'long' */
	long getCallDuration() {
		if (MyLog.DEBUG) MyLog.d(TAG, "getCallDuration...");
		if(!mTimerRunning)
			return 0;
		long DisconnectTime = SystemClock.uptimeMillis();
		return ( DisconnectTime - mBaseTime)/1000;
	}
}
