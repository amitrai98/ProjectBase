package com.zenix.me.baseproject.Activities.App;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.zenix.me.baseproject.Activities.Util.AppLogger;

/**
 * Created by me on 15-Dec-15.
 */
public class AppInitalizer extends Application{

    private static Context appContext = null;

    private static RequestQueue requestQueue = null;

    private static final String TAG = AppInitalizer.class.getSimpleName();

    private static AppInitalizer instance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appContext = getApplicationContext();
        requestQueue = Volley.newRequestQueue(this);
    }

    /**
     * returns app context
     * @return
     */
    public static Context getContext(){
        if (appContext != null)
            return appContext;
        else
            return null;
    }

    /**
     * Adds the specified request to the global queue, if tag is specified
     * then it is used else Default TAG is used.
     */
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);

        AppLogger.d("Adding request to queue: %s", req.getUrl());

        getRequestQueue().add(req);
    }

    /**
     * returns the instance of volley request queue
     * @return
     */
    private RequestQueue getRequestQueue(){
        return requestQueue;
    }

    public static AppInitalizer getInstance() {
        return instance;
    }

}
