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

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.internal.app.AlertActivity;
import com.android.internal.app.AlertController;

public class GeneralModeAlertDialog extends AlertActivity implements DialogInterface.OnClickListener {
	final static String PROMPT_RES_ID = "prompt_res_id";
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        Intent intent = getIntent();
        int promptResID = intent.getIntExtra(PROMPT_RES_ID, R.string.call_failed_prompt);

        final AlertController.AlertParams p = mAlertParams;
        p.mMessage = this.getString(promptResID);
        p.mIconId = R.drawable.cmcc_dialog_information;
		p.mTitle = this.getString(R.string.alert_dialog_title) ;
        p.mPositiveButtonText = getString(android.R.string.ok);
        p.mPositiveButtonListener = this;
        setupAlert();
	}
    public void onDestroy()
    {

        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
	 public void onClick(DialogInterface dialog, int which) {
		 this.dismiss();
	    }   
   
}
