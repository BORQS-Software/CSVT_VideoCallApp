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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class IPServerReceiver extends BroadcastReceiver {

	// static final String ACTION = "android.intent.action.BOOT_COMPLETED";
	static final String INTENT_STARTUP = "com.borqs.videocall.action.StartupVTIpConnect";
	static final String INTENT_SHUTDOWN ="com.borqs.videocall.action.ShutdownVTIpConnect";
	
	static final String TAG = "VT/VIIpConnectReceiver";
	public synchronized void onReceive(Context context, Intent intent) {
		if (MyLog.DEBUG) MyLog.v(TAG, intent.getAction());
		if( intent.getAction().equals(INTENT_SHUTDOWN)){
			IPServerService.stopService();
		}
		else {  //BOOT_COMPLETED or INTENT_STARTUP
			IPServerService.startService( context);
        }
	}
}
