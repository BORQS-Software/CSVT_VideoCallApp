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

import android.provider.Settings;
//import android.app.AlddertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import com.android.internal.app.AlertActivity;
import com.android.internal.app.AlertController;

public class FallBackSetting extends AlertActivity implements DialogInterface.OnClickListener {
    
    public static final String VIDEOCALL_FALL_BACK_SETTING = "videocall_fallback_setting";

    public static final int FALL_BACK_MANUAL = 0;
    public static final int FALL_BACK_AUTO_VOICE = 1;
    public static final int FALL_BACK_AUTO_REJECT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int fbi = Settings.System.getInt(getContentResolver(), VIDEOCALL_FALL_BACK_SETTING, 0);

        // Set up the "dialog"
        final AlertController.AlertParams p = mAlertParams;
        //p.mIconId = com.android.internal.R.drawable.ic_dialog_usb;
        p.mTitle = getString( R.string.fallback_setting_label);
        //p.mMessage = getString(com.android.internal.R.string.usb_storage_message);
        //p.mPositiveButtonText = getString(com.android.internal.R.string.usb_storage_button_mount);
        //p.mPositiveButtonListener = this;
        
        p.mItems = getResources().getTextArray(R.array.fallback_setting_items);
        p.mOnClickListener = new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int whichButton) {
            	if (MyLog.DEBUG) MyLog.d("FallbackSetting", "Get click notification: " + whichButton);
                int i = whichButton;
                //TODO: check the value
                boolean ret = Settings.System.putInt(getContentResolver(), VIDEOCALL_FALL_BACK_SETTING, i);
                if (MyLog.DEBUG) MyLog.d("FallbackSetting", "save result: " + i + " ,result:" + ret);
                finish();
            } };   
        p.mCheckedItem = fbi;
        p.mIsSingleChoice = true;
        
        p.mNegativeButtonText = getString( R.string.cancel_fallback_setting);

        p.mNegativeButtonListener = this;
        setupAlert();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * //Cancel 
     */
    public void onClick(DialogInterface dialog, int which) {

        // No matter what, finish the activity
        finish();
    }    
}

