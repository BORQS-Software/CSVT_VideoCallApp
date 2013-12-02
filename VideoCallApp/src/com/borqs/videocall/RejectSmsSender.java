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
import android.os.Message;
import android.provider.Settings;
import android.provider.Telephony.Sms;
import android.telephony.gsm.SmsManager;
import android.telephony.MSimSmsManager;
import android.telephony.MSimTelephonyManager;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;

public class RejectSmsSender{

	private static final String LOG_TAG = "VT/RejectSmsSender";
	
    public void doSend( String strAddr, String strMsg) {
	   mAddress = strAddr;
	   mMessage = strMsg;
	   if (MyLog.DEBUG) MyLog.d(LOG_TAG, "Send reject sms to:" + mAddress);
       new Thread(mSendSMSRunnable).run();
    }
   
   public RejectSmsSender() {  
	   mContext.registerReceiver(new BroadcastReceiver() {
           @Override
           public void onReceive(Context context, Intent intent) {
               switch (getResultCode()) {
                   case Activity.RESULT_OK:
                       Toast.makeText(context,
                           R.string.success_send_reject_sms,
                           Toast.LENGTH_LONG).show();
                       break;
                   case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                   case SmsManager.RESULT_ERROR_RADIO_OFF:
                   case SmsManager.RESULT_ERROR_NULL_PDU:
                       Toast.makeText(context,
                           R.string.fail_send_reject_sms,
                           Toast.LENGTH_LONG).show();
                       break;
               }
           }
       },new IntentFilter("com.android.phone.SMS_SENT"));
   }
   
   private Runnable mSendSMSRunnable = new Runnable() {
       public void run() {
    
    	   Intent intent = new Intent("com.android.phone.SMS_SENT");
    	   PendingIntent pending = PendingIntent.getBroadcast(
                 VideoCallApp.getInstance(),
                 0, 
                 intent, 
                 PendingIntent.FLAG_CANCEL_CURRENT);
               
             try
             { 
            	 ContentResolver cr = mContext.getContentResolver();
            	 
                 if (MyLog.DEBUG) MyLog.d(LOG_TAG,"[rejectWithSMS] return pending intent:" + pending);
                 //TODO: for test
                 if (MSimTelephonyManager.getDefault().isMultiSimEnabled()) {
                     MSimSmsManager.getDefault().sendTextMessage(
                             mAddress,
                             null,
                             mMessage,
                             pending, //this intent can not be null
                             null,
                             MSimSmsManager.getDefault().getPreferredSmsSubscription());
                 } else {
                     SmsManager.getDefault().sendTextMessage(
                             mAddress,
                             null,
                             mMessage,
                             pending, //this intent can not be null
                             null);
                 }
               
                   //save in sent box
                   ContentValues values = new ContentValues();
                   values.put(Sms.BODY, mMessage);
                   values.put(Sms.TYPE, Sms.MESSAGE_TYPE_SENT);
                   values.put(Sms.ADDRESS, mAddress);
                   cr.insert(Sms.Sent.CONTENT_URI, values);
              }
              catch (IllegalArgumentException ex)
              {
                 if (MyLog.DEBUG) MyLog.d(LOG_TAG,"[rejectWithSMS] got exception: " + ex);
              }
          }
       };
      
       private String mAddress;
       private String mMessage;
       private Context mContext = VideoCallApp.getInstance();
};
