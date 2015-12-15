package com.zenix.me.baseproject.Activities.Util;

import android.util.Log;

import com.zenix.me.baseproject.Activities.App.Constants;

/**
 * Created by me on 15-Dec-15.
 */
public class AppLogger {

    public static void e(String TAG, String message){
        if(Constants.LOGGER_ENABLED)
            Log.e(""+TAG, ""+message);
    }

    public static void d(String TAG, String message){
        if(Constants.LOGGER_ENABLED)
            Log.d("" + TAG, "" + message);
    }

    public static void v(String TAG, String message){
        if(Constants.LOGGER_ENABLED)
            Log.v("" + TAG, "" + message);
    }

    public static void i(String TAG, String message){
        if(Constants.LOGGER_ENABLED)
            Log.i(""+TAG, ""+message);
    }


}
